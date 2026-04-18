package net.caffeinemc.mods.sodium.api.vertex.buffer;

import net.caffeinemc.mods.sodium.api.memory.MemoryIntrinsics;
import net.minecraft.class_293;
import net.minecraft.class_4588;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.MemoryStack;

public interface VertexBufferWriter {
   static VertexBufferWriter of(class_4588 consumer) {
      if (consumer instanceof VertexBufferWriter writer && writer.canUseIntrinsics()) {
         return writer;
      } else {
         throw createUnsupportedVertexConsumerThrowable(consumer);
      }
   }

   @Nullable
   static VertexBufferWriter tryOf(class_4588 consumer) {
      return consumer instanceof VertexBufferWriter writer && writer.canUseIntrinsics() ? writer : null;
   }

   private static RuntimeException createUnsupportedVertexConsumerThrowable(class_4588 consumer) {
      Class<? extends class_4588> clazz = consumer.getClass();
      String name = clazz.getName();
      return new IllegalArgumentException(
         "The class %s does not implement interface VertexBufferWriter, which is required for compatibility with Sodium (see: https://github.com/CaffeineMC/sodium/issues/1620)"
            .formatted(name)
      );
   }

   void push(MemoryStack var1, long var2, int var4, class_293 var5);

   default boolean canUseIntrinsics() {
      return true;
   }

   static void copyInto(VertexBufferWriter writer, MemoryStack stack, long ptr, int count, class_293 format) {
      int length = count * format.method_1362();
      long copy = stack.nmalloc(length);
      MemoryIntrinsics.copyMemory(ptr, copy, length);
      writer.push(stack, copy, count, format);
   }
}
