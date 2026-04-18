package net.caffeinemc.mods.sodium.mixin.features.render.model.item;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.List;
import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.client.model.quad.BakedQuadView;
import net.caffeinemc.mods.sodium.client.render.immediate.model.BakedModelEncoder;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.caffeinemc.mods.sodium.client.util.DirectionUtil;
import net.minecraft.class_1799;
import net.minecraft.class_2350;
import net.minecraft.class_325;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5819;
import net.minecraft.class_6575;
import net.minecraft.class_777;
import net.minecraft.class_918;
import net.minecraft.class_4587.class_4665;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_918.class)
public class ItemRendererMixin {
   @Unique
   private final class_5819 random = new class_6575(42L);
   @Shadow
   @Final
   private class_325 field_4733;

   @WrapOperation(
      method = "method_23182(Lnet/minecraft/class_1087;Lnet/minecraft/class_1799;IILnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5819;method_43047()Lnet/minecraft/class_5819;")
   )
   private class_5819 renderModelFastRandom(Operation<class_5819> original) {
      return this.random;
   }

   @WrapOperation(
      method = "method_23182(Lnet/minecraft/class_1087;Lnet/minecraft/class_1799;IILnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2350;values()[Lnet/minecraft/class_2350;")
   )
   private class_2350[] renderModelFastDirections(Operation<class_5819> original) {
      return DirectionUtil.ALL_DIRECTIONS;
   }

   @WrapOperation(
      method = "method_23182(Lnet/minecraft/class_1087;Lnet/minecraft/class_1799;IILnet/minecraft/class_4587;Lnet/minecraft/class_4588;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_918;method_23180(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;Ljava/util/List;Lnet/minecraft/class_1799;II)V"
      )
   )
   private void renderModelFast(
      class_918 itemRenderer,
      class_4587 poseStack,
      class_4588 vertexConsumer,
      List<class_777> quads,
      class_1799 itemStack,
      int light,
      int overlay,
      Operation<Void> original
   ) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(vertexConsumer);
      if (writer == null) {
         original.call(new Object[]{itemRenderer, poseStack, vertexConsumer, quads, itemStack, light, overlay});
      } else {
         if (!quads.isEmpty()) {
            this.renderBakedItemQuads(poseStack.method_23760(), writer, quads, itemStack, light, overlay);
         }
      }
   }

   @Unique
   private void renderBakedItemQuads(class_4665 matrices, VertexBufferWriter writer, List<class_777> quads, class_1799 itemStack, int light, int overlay) {
      for (int i = 0; i < quads.size(); i++) {
         class_777 bakedQuad = quads.get(i);
         if (bakedQuad.method_3357().length >= 32) {
            BakedQuadView quad = (BakedQuadView)bakedQuad;
            int color = -1;
            if (quad.hasColor()) {
               color = ColorARGB.toABGR(this.field_4733.method_1704(itemStack, quad.getColorIndex()));
            }

            BakedModelEncoder.writeQuadVertices(writer, matrices, quad, color, light, overlay, BakedModelEncoder.shouldMultiplyAlpha());
            if (quad.getSprite() != null) {
               SpriteUtil.INSTANCE.markSpriteActive(quad.getSprite());
            }
         }
      }
   }
}
