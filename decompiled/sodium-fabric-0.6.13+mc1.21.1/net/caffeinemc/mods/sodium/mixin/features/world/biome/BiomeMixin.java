package net.caffeinemc.mods.sodium.mixin.features.world.biome;

import java.util.Optional;
import net.caffeinemc.mods.sodium.client.world.biome.BiomeColorMaps;
import net.minecraft.class_1959;
import net.minecraft.class_3532;
import net.minecraft.class_4763;
import net.minecraft.class_1959.class_5482;
import net.minecraft.class_4763.class_5486;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1959.class)
public abstract class BiomeMixin {
   @Shadow
   @Final
   private class_5482 field_26393;
   @Shadow
   @Final
   private class_4763 field_22039;
   @Unique
   private boolean hasCustomGrassColor;
   @Unique
   private int customGrassColor;
   @Unique
   private boolean hasCustomFoliageColor;
   @Unique
   private int customFoliageColor;
   @Unique
   private int defaultColorIndex;
   @Unique
   private class_4763 cachedSpecialEffects;

   @Inject(
      method = "<init>(Lnet/minecraft/class_1959$class_5482;Lnet/minecraft/class_4763;Lnet/minecraft/class_5485;Lnet/minecraft/class_5483;)V",
      at = @At("RETURN")
   )
   private void onInit(CallbackInfo ci) {
      this.setupColors();
   }

   @Unique
   private void setupColors() {
      this.cachedSpecialEffects = this.field_22039;
      Optional<Integer> grassColor = this.cachedSpecialEffects.method_30812();
      if (grassColor.isPresent()) {
         this.hasCustomGrassColor = true;
         this.customGrassColor = grassColor.get();
      } else {
         this.hasCustomGrassColor = false;
      }

      Optional<Integer> foliageColor = this.cachedSpecialEffects.method_30811();
      if (foliageColor.isPresent()) {
         this.hasCustomFoliageColor = true;
         this.customFoliageColor = foliageColor.get();
      } else {
         this.hasCustomFoliageColor = false;
      }

      this.defaultColorIndex = this.getDefaultColorIndex();
   }

   @Overwrite
   public int method_8711(double x, double z) {
      if (this.field_22039 != this.cachedSpecialEffects) {
         this.setupColors();
      }

      int color;
      if (this.hasCustomGrassColor) {
         color = this.customGrassColor;
      } else {
         color = BiomeColorMaps.getGrassColor(this.defaultColorIndex);
      }

      class_5486 modifier = this.cachedSpecialEffects.method_30814();
      if (modifier != class_5486.field_26426) {
         color = modifier.method_30823(x, z, color);
      }

      return color;
   }

   @Overwrite
   public int method_8698() {
      if (this.field_22039 != this.cachedSpecialEffects) {
         this.setupColors();
      }

      int color;
      if (this.hasCustomFoliageColor) {
         color = this.customFoliageColor;
      } else {
         color = BiomeColorMaps.getFoliageColor(this.defaultColorIndex);
      }

      return color;
   }

   @Unique
   private int getDefaultColorIndex() {
      double temperature = class_3532.method_15363(this.field_26393.comp_844(), 0.0F, 1.0F);
      double humidity = class_3532.method_15363(this.field_26393.comp_846(), 0.0F, 1.0F);
      return BiomeColorMaps.getIndex(temperature, humidity);
   }
}
