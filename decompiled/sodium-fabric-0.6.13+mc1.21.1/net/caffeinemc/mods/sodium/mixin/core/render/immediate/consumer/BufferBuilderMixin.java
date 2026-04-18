package net.caffeinemc.mods.sodium.mixin.core.render.immediate.consumer;

import net.caffeinemc.mods.sodium.api.memory.MemoryIntrinsics;
import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.caffeinemc.mods.sodium.api.vertex.serializer.VertexSerializerRegistry;
import net.caffeinemc.mods.sodium.client.render.vertex.buffer.BufferBuilderExtension;
import net.minecraft.class_287;
import net.minecraft.class_293;
import net.minecraft.class_9799;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_287.class)
public abstract class BufferBuilderMixin implements VertexBufferWriter, BufferBuilderExtension {
   @Shadow
   private int field_1554;
   @Shadow
   @Final
   private int field_52074;
   @Shadow
   private long field_52072;
   @Shadow
   @Final
   private class_9799 field_52071;
   @Shadow
   private int field_52077;
   @Shadow
   @Final
   private class_293 field_1565;

   @Override
   public void sodium$duplicateVertex() {
      if (this.field_1554 != 0) {
         long head = this.field_52071.method_60808(this.field_52074);
         MemoryIntrinsics.copyMemory(head - this.field_52074, head, this.field_52074);
         this.field_1554++;
      }
   }

   @Override
   public void push(MemoryStack stack, long src, int count, class_293 format) {
      int length = count * this.field_52074;
      long dst = this.field_52071.method_60808(length);
      if (format == this.field_1565) {
         MemoryIntrinsics.copyMemory(src, dst, length);
      } else {
         this.copySlow(src, dst, count, format);
      }

      this.field_1554 += count;
      this.field_52072 = dst + length - this.field_52074;
      this.field_52077 = 0;
   }

   @Unique
   private void copySlow(long src, long dst, int count, class_293 format) {
      VertexSerializerRegistry.instance().get(format, this.field_1565).serialize(src, dst, count);
   }
}
