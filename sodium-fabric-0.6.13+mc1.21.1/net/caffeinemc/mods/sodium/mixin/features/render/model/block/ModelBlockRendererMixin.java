package net.caffeinemc.mods.sodium.mixin.features.render.model.block;

import java.util.List;
import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.client.model.quad.BakedQuadView;
import net.caffeinemc.mods.sodium.client.render.immediate.model.BakedModelEncoder;
import net.caffeinemc.mods.sodium.client.render.vertex.VertexConsumerUtils;
import net.caffeinemc.mods.sodium.client.util.DirectionUtil;
import net.minecraft.class_1087;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_4588;
import net.minecraft.class_5819;
import net.minecraft.class_6575;
import net.minecraft.class_777;
import net.minecraft.class_778;
import net.minecraft.class_4587.class_4665;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_778.class)
public class ModelBlockRendererMixin {
   @Unique
   private final class_5819 random = new class_6575(42L);

   @Unique
   private static void renderQuads(class_4665 matrices, VertexBufferWriter writer, int defaultColor, List<class_777> quads, int light, int overlay) {
      for (int i = 0; i < quads.size(); i++) {
         class_777 bakedQuad = quads.get(i);
         if (bakedQuad.method_3357().length >= 32) {
            BakedQuadView quad = (BakedQuadView)bakedQuad;
            int color = quad.hasColor() ? defaultColor : -1;
            BakedModelEncoder.writeQuadVertices(writer, matrices, quad, color, light, overlay, false);
            if (quad.getSprite() != null) {
               SpriteUtil.INSTANCE.markSpriteActive(quad.getSprite());
            }
         }
      }
   }

   @Inject(
      method = "method_3367(Lnet/minecraft/class_4587$class_4665;Lnet/minecraft/class_4588;Lnet/minecraft/class_2680;Lnet/minecraft/class_1087;FFFII)V",
      at = @At("HEAD"),
      cancellable = true
   )
   private void renderFast(
      class_4665 entry,
      class_4588 vertexConsumer,
      class_2680 blockState,
      class_1087 bakedModel,
      float red,
      float green,
      float blue,
      int light,
      int overlay,
      CallbackInfo ci
   ) {
      VertexBufferWriter writer = VertexConsumerUtils.convertOrLog(vertexConsumer);
      if (writer != null) {
         ci.cancel();
         class_5819 random = this.random;
         red = class_3532.method_15363(red, 0.0F, 1.0F);
         green = class_3532.method_15363(green, 0.0F, 1.0F);
         blue = class_3532.method_15363(blue, 0.0F, 1.0F);
         int defaultColor = ColorABGR.pack(red, green, blue, 1.0F);

         for (class_2350 direction : DirectionUtil.ALL_DIRECTIONS) {
            random.method_43052(42L);
            List<class_777> quads = bakedModel.method_4707(blockState, direction, random);
            if (!quads.isEmpty()) {
               renderQuads(entry, writer, defaultColor, quads, light, overlay);
            }
         }

         random.method_43052(42L);
         List<class_777> quads = bakedModel.method_4707(blockState, null, random);
         if (!quads.isEmpty()) {
            renderQuads(entry, writer, defaultColor, quads, light, overlay);
         }
      }
   }
}
