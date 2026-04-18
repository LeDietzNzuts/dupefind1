package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1829;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.registry.IRegistryDataLoader;

public class SwordRegistry {
   private static final Set<class_1792> SWORD_ITEMS = new HashSet<>();
   private static final Map<String, Set<Predicate<class_1799>>> MOD_SWORD_MATCHERS = new HashMap<>();
   private static final Set<Predicate<class_1799>> SWORD_MATCHERS = new HashSet<>();

   private SwordRegistry() {
   }

   public static boolean isSword(class_1799 stack) {
      if (SWORD_ITEMS.contains(stack.method_7909())) {
         return true;
      } else if (stack.method_7909() instanceof class_1829) {
         SWORD_ITEMS.add(stack.method_7909());
         return true;
      } else {
         class_2960 registryName = class_7923.field_41178.method_10221(stack.method_7909());
         if (registryName == class_7923.field_41178.method_10137()) {
            return false;
         } else {
            for (Predicate<class_1799> swordMatcher : SWORD_MATCHERS) {
               if (swordMatcher.test(stack)) {
                  return true;
               }
            }

            String modId = registryName.method_12836();
            if (!MOD_SWORD_MATCHERS.containsKey(modId)) {
               return false;
            } else {
               for (Predicate<class_1799> matcher : MOD_SWORD_MATCHERS.get(modId)) {
                  if (matcher.test(stack)) {
                     return true;
                  }
               }

               return false;
            }
         }
      }
   }

   public static class SwordsLoader implements IRegistryDataLoader {
      @Override
      public String getName() {
         return "swords";
      }

      @Override
      public void parse(JsonObject json, @Nullable String modId) {
         for (JsonElement jsonElement : json.getAsJsonArray("swords")) {
            if (jsonElement.isJsonPrimitive()) {
               this.parseSword(jsonElement.getAsString());
            } else {
               this.parseSwordMatcher(modId, jsonElement);
            }
         }
      }

      private void parseSwordMatcher(@Nullable String modId, JsonElement jsonElement) {
         Matchers.getItemMatcher(jsonElement).ifPresent(swordMatcher -> {
            if (modId != null) {
               SwordRegistry.MOD_SWORD_MATCHERS.computeIfAbsent(modId, m -> new HashSet<>()).add((Predicate<class_1799>)swordMatcher);
            } else {
               SwordRegistry.SWORD_MATCHERS.add((Predicate<class_1799>)swordMatcher);
            }
         });
      }

      private void parseSword(String swordName) {
         class_7923.field_41178.method_17966(class_2960.method_60654(swordName)).ifPresentOrElse(SwordRegistry.SWORD_ITEMS::add, () -> {
            String modId = swordName.split(":")[0];
            if (!FabricLoader.getInstance().isModLoaded(modId)) {
               SophisticatedBackpacks.LOGGER.debug("Mod {} isn't loaded skipping load of sword {}", modId, swordName);
            } else {
               SophisticatedBackpacks.LOGGER.warn("Mod {} is loaded and yet sword {} doesn't exist in registry, skipping its load", modId, swordName);
            }
         });
      }

      @Override
      public void clear() {
         SwordRegistry.SWORD_ITEMS.clear();
         SwordRegistry.SWORD_MATCHERS.clear();
         SwordRegistry.MOD_SWORD_MATCHERS.clear();
      }
   }
}
