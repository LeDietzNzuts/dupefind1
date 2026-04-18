package net.caffeinemc.mods.sodium.client.render.chunk;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_4587;
import net.minecraft.class_4587.class_4665;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public record ChunkRenderMatrices(Matrix4fc projection, Matrix4fc modelView) {
   public static ChunkRenderMatrices from(class_4587 stack) {
      class_4665 entry = stack.method_23760();
      return new ChunkRenderMatrices(new Matrix4f(RenderSystem.getProjectionMatrix()), new Matrix4f(entry.method_23761()));
   }
}
