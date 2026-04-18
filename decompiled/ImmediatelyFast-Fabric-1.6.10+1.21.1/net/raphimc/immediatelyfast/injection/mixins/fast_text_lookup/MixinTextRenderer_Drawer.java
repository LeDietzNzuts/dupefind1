package net.raphimc.immediatelyfast.injection.mixins.fast_text_lookup;

import net.minecraft.class_1921;
import net.minecraft.class_287;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_377;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_327.class_5232;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_5232.class)
public abstract class MixinTextRenderer_Drawer {
   @Unique
   private class_1921 immediatelyFast$lastRenderLayer;
   @Unique
   private class_4588 immediatelyFast$lastVertexConsumer;
   @Unique
   private class_2960 immediatelyFast$lastFont;
   @Unique
   private class_377 immediatelyFast$lastFontStorage;

   @Redirect(
      method = "accept(ILnet/minecraft/class_2583;I)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4597;getBuffer(Lnet/minecraft/class_1921;)Lnet/minecraft/class_4588;")
   )
   private class_4588 reduceGetBufferCalls(class_4597 instance, class_1921 renderLayer) {
      boolean isBufferInvalid = this.immediatelyFast$lastVertexConsumer instanceof class_287 bufferBuilder && !bufferBuilder.field_1556;
      if (!isBufferInvalid && this.immediatelyFast$lastRenderLayer == renderLayer) {
         return this.immediatelyFast$lastVertexConsumer;
      } else {
         this.immediatelyFast$lastRenderLayer = renderLayer;
         return this.immediatelyFast$lastVertexConsumer = instance.getBuffer(renderLayer);
      }
   }

   @Redirect(
      method = "accept(ILnet/minecraft/class_2583;I)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_327;method_27526(Lnet/minecraft/class_2960;)Lnet/minecraft/class_377;")
   )
   private class_377 reduceGetFontStorageCalls(class_327 instance, class_2960 id) {
      if (this.immediatelyFast$lastFont == id) {
         return this.immediatelyFast$lastFontStorage;
      } else {
         this.immediatelyFast$lastFont = id;
         return this.immediatelyFast$lastFontStorage = instance.method_27526(id);
      }
   }
}
