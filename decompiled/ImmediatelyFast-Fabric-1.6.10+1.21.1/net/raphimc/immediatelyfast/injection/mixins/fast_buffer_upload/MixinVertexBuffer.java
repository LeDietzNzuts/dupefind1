package net.raphimc.immediatelyfast.injection.mixins.fast_buffer_upload;

import com.mojang.blaze3d.systems.RenderSystem;
import java.nio.ByteBuffer;
import net.minecraft.class_291;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import org.lwjgl.opengl.GL15C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = class_291.class, priority = 500)
public abstract class MixinVertexBuffer {
   @Unique
   private int immediatelyFast$vertexBufferSize;
   @Unique
   private int immediatelyFast$indexBufferSize;

   @Redirect(
      method = "method_43442(Lnet/minecraft/class_9801$class_4574;Ljava/nio/ByteBuffer;)Lnet/minecraft/class_293;",
      at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;glBufferData(ILjava/nio/ByteBuffer;I)V")
   )
   private void optimizeVertexDataUploading(int target, ByteBuffer data, int usage) {
      if (ImmediatelyFast.runtimeConfig.fast_buffer_upload && usage != 35044 && data.remaining() <= this.immediatelyFast$vertexBufferSize) {
         GL15C.glBufferSubData(target, 0L, data);
      } else {
         this.immediatelyFast$vertexBufferSize = data.remaining();
         RenderSystem.glBufferData(target, data, usage);
      }
   }

   @Redirect(
      method = {
            "method_60829(Lnet/minecraft/class_9799$class_9800;)V",
            "method_43443(Lnet/minecraft/class_9801$class_4574;Ljava/nio/ByteBuffer;)Lcom/mojang/blaze3d/systems/RenderSystem$class_5590;"
      },
      at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;glBufferData(ILjava/nio/ByteBuffer;I)V")
   )
   private void optimizeIndexDataUploading(int target, ByteBuffer data, int usage) {
      if (ImmediatelyFast.runtimeConfig.fast_buffer_upload && usage != 35044 && data.remaining() <= this.immediatelyFast$indexBufferSize) {
         GL15C.glBufferSubData(target, 0L, data);
      } else {
         this.immediatelyFast$indexBufferSize = data.remaining();
         RenderSystem.glBufferData(target, data, usage);
      }
   }
}
