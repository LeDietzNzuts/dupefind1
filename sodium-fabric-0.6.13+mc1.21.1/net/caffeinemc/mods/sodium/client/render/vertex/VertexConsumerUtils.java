package net.caffeinemc.mods.sodium.client.render.vertex;

import javax.annotation.Nullable;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.class_4588;

public class VertexConsumerUtils {
   @Nullable
   public static VertexBufferWriter convertOrLog(class_4588 consumer) {
      VertexBufferWriter writer = VertexBufferWriter.tryOf(consumer);
      if (writer == null) {
         VertexConsumerTracker.logBadConsumer(consumer);
      }

      return writer;
   }
}
