package net.caffeinemc.mods.sodium.mixin.core.render.immediate.consumer;

import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.util.NormI8;
import net.caffeinemc.mods.sodium.api.vertex.attributes.common.ColorAttribute;
import net.caffeinemc.mods.sodium.api.vertex.attributes.common.TextureAttribute;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.class_2350;
import net.minecraft.class_293;
import net.minecraft.class_296;
import net.minecraft.class_4583;
import net.minecraft.class_4588;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4583.class)
public class SheetedDecalTextureGeneratorMixin implements VertexBufferWriter {
   @Shadow
   @Final
   private class_4588 field_20866;
   @Shadow
   @Final
   private Matrix3f field_21054;
   @Shadow
   @Final
   private Matrix4f field_21053;
   @Shadow
   @Final
   private float field_41091;
   @Unique
   private boolean canUseIntrinsics;

   @Inject(method = "<init>(Lnet/minecraft/class_4588;Lnet/minecraft/class_4587$class_4665;F)V", at = @At("RETURN"))
   private void onInit(CallbackInfo ci) {
      this.canUseIntrinsics = VertexBufferWriter.tryOf(this.field_20866) != null;
   }

   @Override
   public boolean canUseIntrinsics() {
      return this.canUseIntrinsics;
   }

   @Override
   public void push(MemoryStack stack, long ptr, int count, class_293 format) {
      transform(ptr, count, format, this.field_21054, this.field_21053, this.field_41091);
      VertexBufferWriter.of(this.field_20866).push(stack, ptr, count, format);
   }

   @Unique
   private static void transform(long ptr, int count, class_293 format, Matrix3f inverseNormalMatrix, Matrix4f inverseTextureMatrix, float textureScale) {
      long stride = format.method_1362();
      int offsetPosition = format.method_60835(class_296.field_52107);
      int offsetColor = format.method_60835(class_296.field_52108);
      int offsetNormal = format.method_60835(class_296.field_52113);
      int offsetTexture = format.method_60835(class_296.field_52109);
      int color = ColorABGR.pack(1.0F, 1.0F, 1.0F, 1.0F);
      Vector3f normal = new Vector3f(Float.NaN);
      Vector4f position = new Vector4f(Float.NaN);

      for (int vertexIndex = 0; vertexIndex < count; vertexIndex++) {
         position.x = MemoryUtil.memGetFloat(ptr + offsetPosition + 0L);
         position.y = MemoryUtil.memGetFloat(ptr + offsetPosition + 4L);
         position.z = MemoryUtil.memGetFloat(ptr + offsetPosition + 8L);
         position.w = 1.0F;
         int packedNormal = MemoryUtil.memGetInt(ptr + offsetNormal);
         normal.x = NormI8.unpackX(packedNormal);
         normal.y = NormI8.unpackY(packedNormal);
         normal.z = NormI8.unpackZ(packedNormal);
         Vector3f transformedNormal = inverseNormalMatrix.transform(normal);
         class_2350 direction = class_2350.method_10147(transformedNormal.x(), transformedNormal.y(), transformedNormal.z());
         Vector4f transformedTexture = inverseTextureMatrix.transform(position);
         transformedTexture.rotateY((float) Math.PI);
         transformedTexture.rotateX((float) (-Math.PI / 2));
         transformedTexture.rotate(direction.method_23224());
         float textureU = -transformedTexture.x() * textureScale;
         float textureV = -transformedTexture.y() * textureScale;
         ColorAttribute.set(ptr + offsetColor, color);
         TextureAttribute.put(ptr + offsetTexture, textureU, textureV);
         ptr += stride;
      }
   }
}
