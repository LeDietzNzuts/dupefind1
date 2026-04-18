package net.caffeinemc.mods.sodium.client.render.chunk.shader;

import com.mojang.blaze3d.platform.GlStateManager;
import java.util.EnumMap;
import java.util.Map;
import net.caffeinemc.mods.sodium.client.gl.device.GLRenderDevice;
import net.caffeinemc.mods.sodium.client.gl.shader.uniform.GlUniformFloat2v;
import net.caffeinemc.mods.sodium.client.gl.shader.uniform.GlUniformFloat3v;
import net.caffeinemc.mods.sodium.client.gl.shader.uniform.GlUniformInt;
import net.caffeinemc.mods.sodium.client.gl.shader.uniform.GlUniformMatrix4f;
import net.caffeinemc.mods.sodium.client.util.TextureUtil;
import net.caffeinemc.mods.sodium.mixin.core.render.texture.TextureAtlasAccessor;
import net.minecraft.class_1059;
import net.minecraft.class_310;
import org.joml.Matrix4fc;

public class DefaultShaderInterface implements ChunkShaderInterface {
   private final Map<ChunkShaderTextureSlot, GlUniformInt> uniformTextures;
   private final GlUniformMatrix4f uniformModelViewMatrix;
   private final GlUniformMatrix4f uniformProjectionMatrix;
   private final GlUniformFloat3v uniformRegionOffset;
   private final GlUniformFloat2v uniformTexCoordShrink;
   private final ChunkShaderFogComponent fogShader;

   public DefaultShaderInterface(ShaderBindingContext context, ChunkShaderOptions options) {
      this.uniformModelViewMatrix = context.bindUniform("u_ModelViewMatrix", GlUniformMatrix4f::new);
      this.uniformProjectionMatrix = context.bindUniform("u_ProjectionMatrix", GlUniformMatrix4f::new);
      this.uniformRegionOffset = context.bindUniform("u_RegionOffset", GlUniformFloat3v::new);
      this.uniformTexCoordShrink = context.bindUniform("u_TexCoordShrink", GlUniformFloat2v::new);
      this.uniformTextures = new EnumMap<>(ChunkShaderTextureSlot.class);
      this.uniformTextures.put(ChunkShaderTextureSlot.BLOCK, context.bindUniform("u_BlockTex", GlUniformInt::new));
      this.uniformTextures.put(ChunkShaderTextureSlot.LIGHT, context.bindUniform("u_LightTex", GlUniformInt::new));
      this.fogShader = options.fog().getFactory().apply(context);
   }

   @Override
   public void setupState() {
      this.bindTexture(ChunkShaderTextureSlot.BLOCK, TextureUtil.getBlockTextureId());
      this.bindTexture(ChunkShaderTextureSlot.LIGHT, TextureUtil.getLightTextureId());
      TextureAtlasAccessor textureAtlas = (TextureAtlasAccessor)class_310.method_1551().method_1531().method_4619(class_1059.field_5275);
      double subTexelPrecision = 1 << GLRenderDevice.INSTANCE.getSubTexelPrecisionBits();
      double subTexelOffset = 3.0517578E-5F;
      this.uniformTexCoordShrink
         .set(
            (float)(subTexelOffset - 1.0 / textureAtlas.getField_43113() / subTexelPrecision),
            (float)(subTexelOffset - 1.0 / textureAtlas.getField_43114() / subTexelPrecision)
         );
      this.fogShader.setup();
   }

   @Override
   public void resetState() {
   }

   @Deprecated(forRemoval = true)
   private void bindTexture(ChunkShaderTextureSlot slot, int textureId) {
      GlStateManager._activeTexture(33984 + slot.ordinal());
      GlStateManager._bindTexture(textureId);
      GlUniformInt uniform = this.uniformTextures.get(slot);
      uniform.setInt(slot.ordinal());
   }

   @Override
   public void setProjectionMatrix(Matrix4fc matrix) {
      this.uniformProjectionMatrix.set(matrix);
   }

   @Override
   public void setModelViewMatrix(Matrix4fc matrix) {
      this.uniformModelViewMatrix.set(matrix);
   }

   @Override
   public void setRegionOffset(float x, float y, float z) {
      this.uniformRegionOffset.set(x, y, z);
   }
}
