package net.caffeinemc.mods.lithium.mixin.math.fast_util;

import net.minecraft.class_2350;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_2350.class)
public class DirectionMixin {
   @Shadow
   @Final
   private static class_2350[] field_11040;
   @Shadow
   @Final
   private int field_11031;

   @Overwrite
   public class_2350 method_10153() {
      return field_11040[this.field_11031];
   }

   @Overwrite
   public static class_2350 method_10162(class_5819 rand) {
      return field_11040[rand.method_43048(field_11040.length)];
   }
}
