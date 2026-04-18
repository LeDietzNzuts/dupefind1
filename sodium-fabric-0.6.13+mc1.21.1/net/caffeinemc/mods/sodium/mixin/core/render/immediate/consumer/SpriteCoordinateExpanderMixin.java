package net.caffeinemc.mods.sodium.mixin.core.render.immediate.consumer;

import net.caffeinemc.mods.sodium.api.vertex.attributes.common.TextureAttribute;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.class_1058;
import net.minecraft.class_293;
import net.minecraft.class_296;
import net.minecraft.class_4588;
import net.minecraft.class_4723;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4723.class)
public class SpriteCoordinateExpanderMixin implements VertexBufferWriter {
   @Shadow
   @Final
   private class_4588 field_21730;
   @Unique
   private boolean canUseIntrinsics;
   @Unique
   private float minU;
   @Unique
   private float minV;
   @Unique
   private float maxU;
   @Unique
   private float maxV;

   @Inject(method = "<init>(Lnet/minecraft/class_4588;Lnet/minecraft/class_1058;)V", at = @At("RETURN"))
   private void onInit(class_4588 delegate, class_1058 sprite, CallbackInfo ci) {
      this.minU = sprite.method_4594();
      this.minV = sprite.method_4593();
      this.maxU = sprite.method_4577();
      this.maxV = sprite.method_4575();
      this.canUseIntrinsics = VertexBufferWriter.tryOf(this.field_21730) != null;
   }

   @Override
   public boolean canUseIntrinsics() {
      return this.canUseIntrinsics;
   }

   @Override
   public void push(MemoryStack stack, long ptr, int count, class_293 format) {
      transform(ptr, count, format, this.minU, this.minV, this.maxU, this.maxV);
      VertexBufferWriter.of(this.field_21730).push(stack, ptr, count, format);
   }

   @Unique
   private static void transform(long ptr, int count, class_293 format, float minU, float minV, float maxU, float maxV) {
      long stride = format.method_1362();
      long offsetUV = format.method_60835(class_296.field_52109);
      float w = maxU - minU;
      float h = maxV - minV;

      for (int vertexIndex = 0; vertexIndex < count; vertexIndex++) {
         float u = TextureAttribute.getU(ptr + offsetUV);
         float v = TextureAttribute.getV(ptr + offsetUV);
         float ut = minU + w * u;
         float vt = minV + h * v;
         TextureAttribute.put(ptr + offsetUV, ut, vt);
         ptr += stride;
      }
   }
}
