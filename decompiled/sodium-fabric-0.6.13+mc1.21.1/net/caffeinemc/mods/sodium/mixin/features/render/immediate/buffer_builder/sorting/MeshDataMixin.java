package net.caffeinemc.mods.sodium.mixin.features.render.immediate.buffer_builder.sorting;

import java.nio.ByteBuffer;
import net.minecraft.class_293;
import net.minecraft.class_9801;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_9801.class)
public abstract class MeshDataMixin {
   @Overwrite
   private static Vector3f[] method_60820(ByteBuffer buffer, int vertices, class_293 format) {
      int vertexStride = format.method_1362();
      int primitiveCount = vertices / 4;
      Vector3f[] centers = new Vector3f[primitiveCount];

      for (int index = 0; index < primitiveCount; index++) {
         long v1 = MemoryUtil.memAddress(buffer, (index * 4 + 0) * vertexStride);
         long v2 = MemoryUtil.memAddress(buffer, (index * 4 + 2) * vertexStride);
         float x1 = MemoryUtil.memGetFloat(v1 + 0L);
         float y1 = MemoryUtil.memGetFloat(v1 + 4L);
         float z1 = MemoryUtil.memGetFloat(v1 + 8L);
         float x2 = MemoryUtil.memGetFloat(v2 + 0L);
         float y2 = MemoryUtil.memGetFloat(v2 + 4L);
         float z2 = MemoryUtil.memGetFloat(v2 + 8L);
         centers[index] = new Vector3f((x1 + x2) * 0.5F, (y1 + y2) * 0.5F, (z1 + z2) * 0.5F);
      }

      return centers;
   }
}
