package net.caffeinemc.mods.lithium.mixin.world.inline_height;

import java.util.function.Supplier;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2874;
import net.minecraft.class_5269;
import net.minecraft.class_5321;
import net.minecraft.class_5455;
import net.minecraft.class_5539;
import net.minecraft.class_6880;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_5539 {
   private int bottomY;
   private int height;
   private int topYInclusive;

   @Inject(
      method = "<init>(Lnet/minecraft/class_5269;Lnet/minecraft/class_5321;Lnet/minecraft/class_5455;Lnet/minecraft/class_6880;Ljava/util/function/Supplier;ZZJI)V",
      at = @At("RETURN")
   )
   private void initHeightCache(
      class_5269 properties,
      class_5321<?> registryRef,
      class_5455 registryManager,
      class_6880<class_2874> dimensionEntry,
      Supplier<?> profiler,
      boolean isClient,
      boolean debugWorld,
      long biomeAccess,
      int maxChainedNeighborUpdates,
      CallbackInfo ci
   ) {
      this.height = ((class_2874)dimensionEntry.comp_349()).comp_652();
      this.bottomY = ((class_2874)dimensionEntry.comp_349()).comp_651();
      this.topYInclusive = this.bottomY + this.height - 1;
   }

   public int method_31605() {
      return this.height;
   }

   public int method_31607() {
      return this.bottomY;
   }

   public int method_32890() {
      return (this.topYInclusive >> 4) + 1 - (this.bottomY >> 4);
   }

   public int method_32891() {
      return this.bottomY >> 4;
   }

   public int method_31597() {
      return (this.topYInclusive >> 4) + 1;
   }

   public boolean method_31606(class_2338 pos) {
      int y = pos.method_10264();
      return y < this.bottomY || y > this.topYInclusive;
   }

   public boolean method_31601(int y) {
      return y < this.bottomY || y > this.topYInclusive;
   }

   public int method_31602(int y) {
      return (y >> 4) - (this.bottomY >> 4);
   }

   public int method_31603(int coord) {
      return coord - (this.bottomY >> 4);
   }

   public int method_31604(int index) {
      return index + (this.bottomY >> 4);
   }

   public int method_31600() {
      return this.topYInclusive + 1;
   }
}
