package vectorwing.farmersdelight.common.registry;

import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.class_1959;
import net.minecraft.class_5321;
import net.minecraft.class_6796;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_2893.class_2895;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.tag.ModTags;

public class ModBiomeModifiers {
   private static final class_5321<class_6796> BROWN_COLONY = modFeature("patch_brown_mushroom_colony");
   private static final class_5321<class_6796> RED_COLONY = modFeature("patch_red_mushroom_colony");
   private static final class_5321<class_6796> WILD_CABBAGE = modFeature("patch_wild_cabbages");
   private static final class_5321<class_6796> WILD_BEETROOT = modFeature("patch_wild_beetroots");
   private static final class_5321<class_6796> WILD_CARROTS = modFeature("patch_wild_carrots");
   private static final class_5321<class_6796> WILD_ONIONS = modFeature("patch_wild_onions");
   private static final class_5321<class_6796> WILD_TOMATOES = modFeature("patch_wild_tomatoes");
   private static final class_5321<class_6796> WILD_POTATOES = modFeature("patch_wild_potatoes");
   private static final class_5321<class_6796> WILD_RICE = modFeature("patch_wild_rice");

   @NotNull
   private static class_5321<class_6796> modFeature(String red_colony) {
      return class_5321.method_29179(class_7924.field_41245, FarmersDelight.res(red_colony));
   }

   public static void init() {
      BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ModTags.HAS_BROWN_MUSHROOM_COLONY), class_2895.field_13178, BROWN_COLONY);
      BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ModTags.HAS_RED_MUSHROOM_COLONY), class_2895.field_13178, RED_COLONY);
      BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ModTags.HAS_WILD_CABBAGE), class_2895.field_13178, WILD_CABBAGE);
      BiomeModifications.addFeature(new ModBiomeModifiers.FDBiomeSelector(ModTags.HAS_WILD_BEETROOTS), class_2895.field_13178, WILD_BEETROOT);
      BiomeModifications.addFeature(
         new ModBiomeModifiers.FDBiomeSelector(0.4F, 0.9F, ModTags.WILD_CARROTS_WHITELIST, ModTags.WILD_CARROTS_BLACKLIST),
         class_2895.field_13178,
         WILD_CARROTS
      );
      BiomeModifications.addFeature(
         new ModBiomeModifiers.FDBiomeSelector(0.4F, 0.9F, ModTags.WILD_ONIONS_WHITELIST, ModTags.WILD_ONIONS_BLACKLIST), class_2895.field_13178, WILD_ONIONS
      );
      BiomeModifications.addFeature(
         new ModBiomeModifiers.FDBiomeSelector(0.1F, 0.3F, ModTags.WILD_POTATOES_WHITELIST, ModTags.WILD_POTATOES_BLACKLIST),
         class_2895.field_13178,
         WILD_POTATOES
      );
      BiomeModifications.addFeature(
         new ModBiomeModifiers.FDBiomeSelector(-4.0F, 4.0F, ModTags.WILD_RICE_WHITELIST, ModTags.WILD_RICE_BLACKLIST), class_2895.field_13178, WILD_RICE
      );
      BiomeModifications.addFeature(
         new ModBiomeModifiers.FDBiomeSelector(-4.0F, 4.0F, ModTags.WILD_TOMATOES_WHITELIST, ModTags.WILD_TOMATOES_BLACKLIST),
         class_2895.field_13178,
         WILD_TOMATOES
      );
   }

   public record FDBiomeSelector(float minTemperature, float maxTemperature, class_6862<class_1959> allowed, @Nullable class_6862<class_1959> denied)
      implements Predicate<BiomeSelectionContext> {
      public FDBiomeSelector(class_6862<class_1959> tagKey) {
         this(-4.0F, 4.0F, tagKey, null);
      }

      public boolean test(BiomeSelectionContext context) {
         class_6880<class_1959> biome = context.getBiomeRegistryEntry();
         float temp = ((class_1959)biome.comp_349()).method_8712();
         return this.denied != null && biome.method_40220(this.denied)
            ? false
            : biome.method_40220(this.allowed) && temp >= this.minTemperature && temp <= this.maxTemperature;
      }
   }
}
