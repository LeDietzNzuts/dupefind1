package net.caffeinemc.mods.sodium.mixin.core.render.immediate.consumer;

import net.caffeinemc.mods.sodium.api.vertex.buffer.VertexBufferWriter;
import net.minecraft.class_293;
import net.minecraft.class_4588;
import org.lwjgl.system.MemoryStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class VertexMultiConsumerMixin {
   @Mixin(targets = "net/minecraft/class_4720$class_4589")
   public static class DoubleMixin implements VertexBufferWriter {
      @Shadow
      @Final
      private class_4588 field_21685;
      @Shadow
      @Final
      private class_4588 field_21686;
      @Unique
      private boolean canUseIntrinsics;

      @Inject(method = "<init>(Lnet/minecraft/class_4588;Lnet/minecraft/class_4588;)V", at = @At("RETURN"))
      private void checkFullStatus(CallbackInfo ci) {
         this.canUseIntrinsics = VertexBufferWriter.tryOf(this.field_21685) != null && VertexBufferWriter.tryOf(this.field_21686) != null;
      }

      @Override
      public boolean canUseIntrinsics() {
         return this.canUseIntrinsics;
      }

      @Override
      public void push(MemoryStack stack, long ptr, int count, class_293 format) {
         VertexBufferWriter.copyInto(VertexBufferWriter.of(this.field_21685), stack, ptr, count, format);
         VertexBufferWriter.copyInto(VertexBufferWriter.of(this.field_21686), stack, ptr, count, format);
      }
   }

   @Mixin(targets = "net/minecraft/class_4720$class_6189")
   public static class MultipleMixin implements VertexBufferWriter {
      @Shadow
      @Final
      private class_4588[] comp_2847;
      @Unique
      private boolean canUseIntrinsics;

      @Inject(method = "<init>([Lnet/minecraft/class_4588;)V", at = @At("RETURN"))
      private void checkFullStatus(CallbackInfo ci) {
         this.canUseIntrinsics = this.allDelegatesSupportIntrinsics();
      }

      @Unique
      private boolean allDelegatesSupportIntrinsics() {
         for (class_4588 delegate : this.comp_2847) {
            if (VertexBufferWriter.tryOf(delegate) == null) {
               return false;
            }
         }

         return true;
      }

      @Override
      public boolean canUseIntrinsics() {
         return this.canUseIntrinsics;
      }

      @Override
      public void push(MemoryStack stack, long ptr, int count, class_293 format) {
         for (class_4588 delegate : this.comp_2847) {
            VertexBufferWriter.copyInto(VertexBufferWriter.of(delegate), stack, ptr, count, format);
         }
      }
   }
}
