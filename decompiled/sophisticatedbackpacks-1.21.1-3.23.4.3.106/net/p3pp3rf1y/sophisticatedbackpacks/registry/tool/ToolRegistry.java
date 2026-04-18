package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3518;
import net.minecraft.class_3545;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.IRegistryDataLoader;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class ToolRegistry {
   private static final String TOOLS_PROPERTY = "tools";
   private static final Set<String> modsWithMapping = new HashSet<>();
   private static final ToolRegistry.ToolMapping<class_2248, BlockContext> BLOCK_TOOL_MAPPING = new ToolRegistry.ToolMapping<>(
      class_7923.field_41175, BlockContext::getBlock
   );
   private static final ToolRegistry.ToolMapping<class_1299<?>, class_1297> ENTITY_TOOL_MAPPING = new ToolRegistry.ToolMapping<>(
      class_7923.field_41177, class_1297::method_5864
   );

   private ToolRegistry() {
   }

   public static boolean isToolForBlock(class_1799 stack, class_2248 block, class_1937 level, class_2680 blockState, class_2338 pos) {
      return BLOCK_TOOL_MAPPING.isToolFor(stack, block, () -> new BlockContext(level, blockState, block, pos));
   }

   public static boolean isToolForEntity(class_1799 stack, class_1297 entity) {
      return ENTITY_TOOL_MAPPING.isToolFor(stack, entity.method_5864(), () -> entity);
   }

   protected static class_3545<Set<class_1792>, Set<Predicate<class_1799>>> getItemsAndItemPredicates(Entry<String, JsonElement> property) {
      if (property.getValue().isJsonArray()) {
         JsonArray toolArray = class_3518.method_15252(property.getValue(), "");
         return getItemsAndItemPredicates(toolArray);
      } else {
         SophisticatedBackpacks.LOGGER.error("Invalid tools list - needs to be an array {}", property.getValue());
         return new class_3545(Collections.emptySet(), Collections.emptySet());
      }
   }

   protected static class_3545<Set<class_1792>, Set<Predicate<class_1799>>> getItemsAndItemPredicates(JsonArray toolArray) {
      Set<class_1792> items = new HashSet<>();
      Set<Predicate<class_1799>> itemPredicates = new HashSet<>();

      for (JsonElement jsonElement : toolArray) {
         if (jsonElement.isJsonPrimitive()) {
            class_2960 itemName = class_2960.method_60654(jsonElement.getAsString());
            if (!class_7923.field_41178.method_10250(itemName)) {
               SophisticatedBackpacks.LOGGER.debug("{} isn't loaded in item registry, skipping ...", itemName);
            }

            class_1792 item = (class_1792)class_7923.field_41178.method_10223(itemName);
            items.add(item);
         } else if (jsonElement.isJsonObject()) {
            Matchers.getItemMatcher(jsonElement).ifPresent(itemPredicates::add);
         }
      }

      return new class_3545(items, itemPredicates);
   }

   public static void addModWithMapping(String modId) {
      modsWithMapping.add(modId);
   }

   public static class BlockToolsLoader extends ToolRegistry.ToolsLoaderBase<class_2248, BlockContext> {
      public BlockToolsLoader() {
         super(
            Matchers.getBlockMatcherFactories(),
            ToolRegistry.BLOCK_TOOL_MAPPING,
            class_7923.field_41175,
            class_7923.field_41175::method_17966,
            "block_tools",
            "blocks"
         );
      }
   }

   public static class EntityToolsLoader extends ToolRegistry.ToolsLoaderBase<class_1299<?>, class_1297> {
      public EntityToolsLoader() {
         super(
            Matchers.getEntityMatcherFactories(),
            ToolRegistry.ENTITY_TOOL_MAPPING,
            class_7923.field_41177,
            class_7923.field_41177::method_17966,
            "entity_tools",
            "entities"
         );
      }
   }

   private static class ToolMapping<V, C> {
      private final class_2378<V> registry;
      private final Function<C, V> getObjectFromContext;
      private final Map<V, Set<class_1792>> notToolCache = new HashMap<>();
      private final Map<V, Set<class_1792>> objectTools = new HashMap<>();
      private final Map<V, Set<Predicate<class_1799>>> objectToolPredicates = new HashMap<>();
      private final Map<Predicate<C>, Set<class_1792>> objectPredicateTools = new HashMap<>();
      private final Map<Predicate<C>, Set<Predicate<class_1799>>> objectPredicateToolPredicates = new HashMap<>();

      public ToolMapping(class_2378<V> registry, Function<C, V> getObjectFromContext) {
         this.registry = registry;
         this.getObjectFromContext = getObjectFromContext;
      }

      private void addObjectPredicateTools(class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools, Predicate<C> predicate) {
         ((Set)tools.method_15442()).forEach(t -> this.objectPredicateTools.computeIfAbsent(predicate, p -> new HashSet<>()).add(t));
         ((Set)tools.method_15441()).forEach(tp -> this.objectPredicateToolPredicates.computeIfAbsent(predicate, p -> new HashSet<>()).add(tp));
      }

      private void addObjectTools(class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools, V object) {
         ((Set)tools.method_15442()).forEach(t -> this.objectTools.computeIfAbsent(object, b -> new HashSet<>()).add(t));
         ((Set)tools.method_15441()).forEach(tp -> this.objectToolPredicates.computeIfAbsent(object, b -> new HashSet<>()).add(tp));
      }

      public void clear() {
         this.notToolCache.clear();
         this.objectTools.clear();
         this.objectToolPredicates.clear();
         this.objectPredicateTools.clear();
         this.objectPredicateToolPredicates.clear();
      }

      public boolean isToolFor(class_1799 stack, V object, Supplier<C> getContext) {
         class_1792 item = stack.method_7909();
         if (this.objectTools.containsKey(object) && this.objectTools.get(object).contains(item)) {
            return true;
         } else if (this.notToolCache.containsKey(object) && this.notToolCache.get(object).contains(item)) {
            return false;
         } else if (this.tryToMatchAgainstObjectToolPredicates(stack, object)) {
            return true;
         } else {
            C context = getContext.get();
            if (this.tryToMatchAgainstObjectPredicateTools(item, context)) {
               return true;
            } else if (this.tryToMatchAgainstObjectPredicateToolPredicates(stack, context)) {
               return true;
            } else if (this.tryToMatchNoMappingMod(stack, object)) {
               return true;
            } else {
               this.notToolCache.computeIfAbsent(object, b -> new HashSet<>()).add(item);
               return false;
            }
         }
      }

      private boolean tryToMatchNoMappingMod(class_1799 stack, V object) {
         if (this.isNoMappingModAndNonStackableItemFromSameMod(stack, object)) {
            this.addObjectToolMapping(object, stack.method_7909());
            return true;
         } else {
            return false;
         }
      }

      private boolean isNoMappingModAndNonStackableItemFromSameMod(class_1799 stack, V object) {
         return RegistryHelper.getRegistryName(this.registry, object)
               .map(
                  rn -> !rn.method_12836().equals("minecraft")
                     && !ToolRegistry.modsWithMapping.contains(rn.method_12836())
                     && RegistryHelper.getRegistryName(class_7923.field_41178, stack.method_7909())
                        .map(itemRegistryName -> itemRegistryName.method_12836().equals(rn.method_12836()))
                        .orElse(false)
               )
               .orElse(false)
            && stack.method_7914() == 1;
      }

      private boolean tryToMatchAgainstObjectPredicateToolPredicates(class_1799 stack, C context) {
         for (Entry<Predicate<C>, Set<Predicate<class_1799>>> entry : this.objectPredicateToolPredicates.entrySet()) {
            if (entry.getKey().test(context)) {
               Set<Predicate<class_1799>> toolPredicates = entry.getValue();
               if (this.tryToMatchTools(stack, this.getObjectFromContext.apply(context), toolPredicates)) {
                  return true;
               }
            }
         }

         return false;
      }

      private boolean tryToMatchAgainstObjectToolPredicates(class_1799 stack, V object) {
         if (this.objectToolPredicates.containsKey(object)) {
            Set<Predicate<class_1799>> toolPredicates = this.objectToolPredicates.get(object);
            return this.tryToMatchTools(stack, object, toolPredicates);
         } else {
            return false;
         }
      }

      private boolean tryToMatchAgainstObjectPredicateTools(class_1792 item, C context) {
         for (Entry<Predicate<C>, Set<class_1792>> entry : this.objectPredicateTools.entrySet()) {
            if (entry.getKey().test(context) && entry.getValue().contains(item)) {
               this.addObjectToolMapping(this.getObjectFromContext.apply(context), item);
               return true;
            }
         }

         return false;
      }

      private boolean tryToMatchTools(class_1799 stack, V object, Set<Predicate<class_1799>> toolPredicates) {
         for (Predicate<class_1799> itemPredicate : toolPredicates) {
            if (itemPredicate.test(stack)) {
               this.objectTools.computeIfAbsent(object, b -> new HashSet<>()).add(stack.method_7909());
               return true;
            }
         }

         return false;
      }

      private void addObjectToolMapping(V block, class_1792 item) {
         this.objectTools.computeIfAbsent(block, b -> new HashSet<>()).add(item);
      }

      public Map<V, Set<class_1792>> getObjectTools() {
         return this.objectTools;
      }

      public void addModPredicateTools(String modId, class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools) {
         this.addObjectPredicateTools(tools, new ModMatcher<>(this.registry, modId, this.getObjectFromContext));
      }
   }

   private abstract static class ToolsLoaderBase<V, C> implements IRegistryDataLoader {
      private final List<IMatcherFactory<C>> objectMatcherFactories;
      private final ToolRegistry.ToolMapping<V, C> toolMapping;
      private final class_2378<V> registry;
      private final Function<class_2960, Optional<V>> getObjectFromRegistry;
      private final String name;
      private final String objectJsonArrayName;

      public ToolsLoaderBase(
         List<IMatcherFactory<C>> objectMatcherFactories,
         ToolRegistry.ToolMapping<V, C> toolMapping,
         class_2378<V> registry,
         Function<class_2960, Optional<V>> getObjectFromRegistry,
         String name,
         String objectJsonArrayName
      ) {
         this.objectMatcherFactories = objectMatcherFactories;
         this.toolMapping = toolMapping;
         this.registry = registry;
         this.getObjectFromRegistry = getObjectFromRegistry;
         this.name = name;
         this.objectJsonArrayName = objectJsonArrayName;
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Override
      public void parse(JsonObject json, @Nullable String modId) {
         for (JsonElement jsonElement : class_3518.method_15261(json, this.name)) {
            if (jsonElement.isJsonObject()) {
               JsonObject entry = jsonElement.getAsJsonObject();
               this.parseEntry(entry);
            }
         }

         this.toolMapping
            .getObjectTools()
            .keySet()
            .forEach(object -> RegistryHelper.getRegistryName(this.registry, object).ifPresent(rn -> ToolRegistry.modsWithMapping.add(rn.method_12836())));
      }

      @Override
      public void clear() {
         this.toolMapping.clear();
         ToolRegistry.modsWithMapping.clear();
      }

      private void parseEntry(JsonObject entry) {
         if (entry.size() == 1) {
            this.parseFromProperty(entry);
         } else if (entry.size() == 2 && entry.has(this.objectJsonArrayName) && entry.has("tools")) {
            this.parseFromArrays(class_3518.method_15261(entry, this.objectJsonArrayName), class_3518.method_15261(entry, "tools"));
         } else {
            SophisticatedBackpacks.LOGGER
               .error(
                  "Invalid block tools entry - needs to have either 1 array property with mod/entity name or \"{}\" and \"tools\" array properties {}",
                  this.objectJsonArrayName,
                  entry
               );
         }
      }

      private void parseFromArrays(JsonArray blocksArray, JsonArray toolsArray) {
         class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools = ToolRegistry.getItemsAndItemPredicates(toolsArray);
         if (!((Set)tools.method_15442()).isEmpty() || !((Set)tools.method_15441()).isEmpty()) {
            for (JsonElement jsonElement : blocksArray) {
               if (jsonElement.isJsonPrimitive() && jsonElement.getAsString().contains(":")) {
                  this.parseObjectEntry(tools, jsonElement.getAsString());
               } else {
                  this.parseObjectPredicateEntry(tools, jsonElement);
               }
            }
         }
      }

      private void parseObjectPredicateEntry(class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools, JsonElement jsonElement) {
         for (IMatcherFactory<C> blockMatcherFactory : this.objectMatcherFactories) {
            if (blockMatcherFactory.appliesTo(jsonElement)) {
               blockMatcherFactory.getPredicate(jsonElement).ifPresent(predicate -> this.toolMapping.addObjectPredicateTools(tools, (Predicate<C>)predicate));
               break;
            }
         }
      }

      private void parseObjectEntry(class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools, String objectName) {
         class_2960 registryName = class_2960.method_60654(objectName);
         Optional<V> objectOptional = this.getObjectFromRegistry.apply(registryName);
         if (objectOptional.isPresent()) {
            this.toolMapping.addObjectTools(tools, objectOptional.get());
         } else {
            SophisticatedBackpacks.LOGGER.debug("{} doesn't exist in registry, skipping ...", objectName);
         }
      }

      private void parseFromProperty(JsonObject entry) {
         for (Entry<String, JsonElement> property : entry.entrySet()) {
            if (property.getKey().contains(":")) {
               this.parseObjectTools(property);
            } else {
               this.parseModTools(property);
            }
         }
      }

      private void parseModTools(Entry<String, JsonElement> property) {
         String modId = property.getKey();
         if (!FabricLoader.getInstance().isModLoaded(modId)) {
            SophisticatedBackpacks.LOGGER.debug("{} mod isn't loaded, skipping ... {} ", modId, property);
         } else {
            class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools = ToolRegistry.getItemsAndItemPredicates(property);
            if (!((Set)tools.method_15442()).isEmpty() || !((Set)tools.method_15441()).isEmpty()) {
               this.toolMapping.addModPredicateTools(modId, tools);
            }
         }
      }

      private void parseObjectTools(Entry<String, JsonElement> property) {
         class_3545<Set<class_1792>, Set<Predicate<class_1799>>> tools = ToolRegistry.getItemsAndItemPredicates(property);
         if (!((Set)tools.method_15442()).isEmpty() || !((Set)tools.method_15441()).isEmpty()) {
            this.parseObjectEntry(tools, property.getKey());
         }
      }
   }
}
