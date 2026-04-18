package net.caffeinemc.mods.lithium.mixin.entity.fast_powder_snow_check;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1320;
import net.minecraft.class_1324;
import net.minecraft.class_1937;
import net.minecraft.class_2680;
import net.minecraft.class_6880;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 {
   @Shadow
   @Nullable
   public abstract class_1324 method_5996(class_6880<class_1320> var1);

   public LivingEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Redirect(method = "method_32325()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1309;method_43261()Lnet/minecraft/class_2680;"))
   private class_2680 delayGetBlockState(class_1309 instance) {
      return null;
   }

   @Redirect(method = "method_32325()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26215()Z"))
   private boolean delayAirTest(class_2680 instance) {
      return false;
   }

   @Redirect(
      method = "method_32325()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1309;method_5996(Lnet/minecraft/class_6880;)Lnet/minecraft/class_1324;")
   )
   private class_1324 doDelayedBlockStateAirTest(class_1309 instance, class_6880<class_1320> attribute) {
      return this.method_43261().method_26215() ? null : this.method_5996(attribute);
   }
}
