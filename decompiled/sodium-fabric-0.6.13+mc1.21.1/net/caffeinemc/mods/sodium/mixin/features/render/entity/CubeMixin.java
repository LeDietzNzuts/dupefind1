package net.caffeinemc.mods.sodium.mixin.features.render.entity;

import java.util.Set;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.client.render.immediate.model.EntityRenderer;
import net.caffeinemc.mods.sodium.client.render.immediate.model.ModelCuboid;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.minecraft.class_2350;
import net.minecraft.class_4588;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_628;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_628.class)
public class CubeMixin {
   @Mutable
   @Shadow
   @Final
   public float field_3645;
   @Unique
   private ModelCuboid sodium$cuboid;

   @Redirect(
      method = "<init>(IIFFFFFFFFFZFFLjava/util/Set;)V",
      at = @At(value = "FIELD", opcode = 181, target = "Lnet/minecraft/class_630$class_628;field_3645:F", ordinal = 0)
   )
   private void onInit(
      class_628 instance,
      float value,
      int u,
      int v,
      float x,
      float y,
      float z,
      float sizeX,
      float sizeY,
      float sizeZ,
      float extraX,
      float extraY,
      float extraZ,
      boolean mirror,
      float textureWidth,
      float textureHeight,
      Set<class_2350> renderDirections
   ) {
      this.sodium$cuboid = new ModelCuboid(u, v, x, y, z, sizeX, sizeY, sizeZ, extraX, extraY, extraZ, mirror, textureWidth, textureHeight, renderDirections);
      this.field_3645 = value;
   }

   @Inject(
      method = "method_32089(Lnet/minecraft/class_4587$class_4665;Lnet/minecraft/class_4588;III)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4587$class_4665;method_23761()Lorg/joml/Matrix4f;"),
      cancellable = true
   )
   private void onCompile(class_4665 pose, class_4588 buffer, int light, int overlay, int color, CallbackInfo ci) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(buffer);
      if (writer != null) {
         ci.cancel();
         EntityRenderer.renderCuboid(pose, writer, this.sodium$cuboid, light, overlay, ColorARGB.toABGR(color));
      }
   }
}
