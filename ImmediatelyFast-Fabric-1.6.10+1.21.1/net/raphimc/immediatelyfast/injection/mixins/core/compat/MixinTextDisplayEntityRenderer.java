package net.raphimc.immediatelyfast.injection.mixins.core.compat;

import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4597.class_4598;
import net.minecraft.class_8113.class_8123;
import net.minecraft.class_8113.class_8123.class_8230;
import net.minecraft.class_8138.class_8141;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_8141.class)
public abstract class MixinTextDisplayEntityRenderer {
   @Inject(
      method = "method_49056(Lnet/minecraft/class_8113$class_8123;Lnet/minecraft/class_8113$class_8123$class_8230;Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;IF)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_8113$class_8123$class_8125;comp_1247()Ljava/util/List;", ordinal = 1)
   )
   private void drawBackgroundImmediately(
      class_8123 textDisplayEntity, class_8230 data, class_4587 matrixStack, class_4597 vertexConsumerProvider, int i, float f, CallbackInfo ci
   ) {
      if ((data.comp_1338() & 2) != 0 && vertexConsumerProvider instanceof class_4598 immediate) {
         immediate.method_22993();
      }
   }
}
