package net.caffeinemc.mods.sodium.mixin.features.render.immediate.buffer_builder.intrinsics;

import net.caffeinemc.mods.sodium.api.texture.SpriteUtil;
import net.caffeinemc.mods.sodium.api.util.ColorABGR;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.render.immediate.model.BakedModelEncoder;
import net.minecraft.class_287;
import net.minecraft.class_4588;
import net.minecraft.class_777;
import net.minecraft.class_4587.class_4665;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_287.class)
public abstract class BufferBuilderMixin implements class_4588 {
   @Shadow
   @Final
   private boolean field_21594;

   public void method_22919(class_4665 matrices, class_777 bakedQuad, float r, float g, float b, float a, int light, int overlay) {
      if (!this.field_21594) {
         super.method_22919(matrices, bakedQuad, r, g, b, a, light, overlay);
         if (bakedQuad.method_35788() != null) {
            SpriteUtil.INSTANCE.markSpriteActive(bakedQuad.method_35788());
         }
      } else if (bakedQuad.method_3357().length >= 32) {
         VertexBufferWriter writer = VertexBufferWriter.of(this);
         ModelQuadView quad = (ModelQuadView)bakedQuad;
         int color = ColorABGR.pack(r, g, b, a);
         BakedModelEncoder.writeQuadVertices(writer, matrices, quad, color, light, overlay, false);
         if (quad.getSprite() != null) {
            SpriteUtil.INSTANCE.markSpriteActive(quad.getSprite());
         }
      }
   }

   public void method_22920(
      class_4665 matrices, class_777 bakedQuad, float[] brightnessTable, float r, float g, float b, float a, int[] light, int overlay, boolean colorize
   ) {
      if (!this.field_21594) {
         super.method_22920(matrices, bakedQuad, brightnessTable, r, g, b, a, light, overlay, colorize);
         if (bakedQuad.method_35788() != null) {
            SpriteUtil.INSTANCE.markSpriteActive(bakedQuad.method_35788());
         }
      } else if (bakedQuad.method_3357().length >= 32) {
         VertexBufferWriter writer = VertexBufferWriter.of(this);
         ModelQuadView quad = (ModelQuadView)bakedQuad;
         BakedModelEncoder.writeQuadVertices(writer, matrices, quad, r, g, b, a, brightnessTable, colorize, light, overlay);
         if (quad.getSprite() != null) {
            SpriteUtil.INSTANCE.markSpriteActive(quad.getSprite());
         }
      }
   }
}
