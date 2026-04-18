package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1321;
import net.minecraft.class_1429;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2241;
import net.minecraft.class_2960;
import net.minecraft.class_3518;
import net.minecraft.class_4466;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;

public class Matchers {
   private static final List<ItemMatcherFactory> ITEM_MATCHER_FACTORIES = new ArrayList<>();
   private static final List<IMatcherFactory<BlockContext>> BLOCK_MATCHER_FACTORIES = new ArrayList<>();
   private static final List<IMatcherFactory<class_1297>> ENTITY_MATCHER_FACTORIES = new ArrayList<>();

   private Matchers() {
   }

   static void addItemMatcherFactory(ItemMatcherFactory matcherFactory) {
      ITEM_MATCHER_FACTORIES.add(matcherFactory);
   }

   public static Optional<Predicate<class_1799>> getItemMatcher(JsonElement jsonElement) {
      for (ItemMatcherFactory itemMatcherFactory : ITEM_MATCHER_FACTORIES) {
         if (itemMatcherFactory.appliesTo(jsonElement)) {
            return itemMatcherFactory.getPredicate(jsonElement);
         }
      }

      return Optional.empty();
   }

   public static List<IMatcherFactory<BlockContext>> getBlockMatcherFactories() {
      return BLOCK_MATCHER_FACTORIES;
   }

   public static List<IMatcherFactory<class_1297>> getEntityMatcherFactories() {
      return ENTITY_MATCHER_FACTORIES;
   }

   static {
      addItemMatcherFactory(new ItemMatcherFactory("tag") {
         @Override
         protected Optional<Predicate<class_1799>> getPredicateFromObject(JsonObject jsonObject) {
            String tagName = class_3518.method_15265(jsonObject, "tag");
            class_6862<class_1792> tag = class_6862.method_40092(class_7924.field_41197, class_2960.method_60654(tagName));
            return Optional.of(new ItemTagMatcher(tag));
         }
      });
      addItemMatcherFactory(new ItemMatcherFactory("nocomponents") {
         @Override
         protected Optional<Predicate<class_1799>> getPredicateFromObject(JsonObject jsonObject) {
            class_2960 itemName = class_2960.method_60654(class_3518.method_15265(jsonObject, "item"));
            if (!class_7923.field_41178.method_10250(itemName)) {
               SophisticatedBackpacks.LOGGER.debug("{} isn't loaded in item registry, skipping ...", itemName);
            }

            class_1792 item = (class_1792)class_7923.field_41178.method_10223(itemName);
            return Optional.of(st -> st.method_7909() == item && st.method_57353().method_57837());
         }
      });
      BLOCK_MATCHER_FACTORIES.add(new IMatcherFactory<BlockContext>() {
         @Override
         public boolean appliesTo(JsonElement jsonElement) {
            return jsonElement.isJsonPrimitive();
         }

         @Override
         public Optional<Predicate<BlockContext>> getPredicate(JsonElement jsonElement) {
            String modId = jsonElement.getAsString();
            if (!FabricLoader.getInstance().isModLoaded(modId)) {
               SophisticatedBackpacks.LOGGER.debug("{} mod isn't loaded, skipping ...", modId);
               return Optional.empty();
            } else {
               return Optional.of(new ModMatcher<>(class_7923.field_41175, modId, BlockContext::getBlock));
            }
         }
      });
      BLOCK_MATCHER_FACTORIES.add(new TypedMatcherFactory<BlockContext>("all") {
         @Override
         protected Optional<Predicate<BlockContext>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(block -> true);
         }
      });
      BLOCK_MATCHER_FACTORIES.add(new TypedMatcherFactory<BlockContext>("rail") {
         @Override
         protected Optional<Predicate<BlockContext>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(blockContext -> blockContext.getBlock() instanceof class_2241);
         }
      });
      BLOCK_MATCHER_FACTORIES.add(new TypedMatcherFactory<BlockContext>("item_handler") {
         @Override
         protected Optional<Predicate<BlockContext>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(blockContext -> ItemStorage.SIDED.find(blockContext.getLevel(), blockContext.getPos(), null) != null);
         }
      });
      ENTITY_MATCHER_FACTORIES.add(new TypedMatcherFactory<class_1297>("animal") {
         @Override
         protected Optional<Predicate<class_1297>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(class_1429.class::isInstance);
         }
      });
      ENTITY_MATCHER_FACTORIES.add(new TypedMatcherFactory<class_1297>("living") {
         @Override
         protected Optional<Predicate<class_1297>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(class_1309.class::isInstance);
         }
      });
      ENTITY_MATCHER_FACTORIES.add(new TypedMatcherFactory<class_1297>("bee") {
         @Override
         protected Optional<Predicate<class_1297>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(class_4466.class::isInstance);
         }
      });
      ENTITY_MATCHER_FACTORIES.add(new TypedMatcherFactory<class_1297>("tameable") {
         @Override
         protected Optional<Predicate<class_1297>> getPredicateFromObject(JsonObject jsonObject) {
            return Optional.of(class_1321.class::isInstance);
         }
      });
   }
}
