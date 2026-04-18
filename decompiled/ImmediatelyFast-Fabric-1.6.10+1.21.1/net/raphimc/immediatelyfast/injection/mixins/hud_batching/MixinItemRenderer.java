package net.raphimc.immediatelyfast.injection.mixins.hud_batching;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.class_1087;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_811;
import net.minecraft.class_918;
import net.raphimc.immediatelyfast.feature.batching.HudBatchingBufferSource;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_918.class)
public abstract class MixinItemRenderer {
   @WrapMethod(
      method = "method_23179(Lnet/minecraft/class_1799;Lnet/minecraft/class_811;ZLnet/minecraft/class_4587;Lnet/minecraft/class_4597;IILnet/minecraft/class_1087;)V"
   )
   private void renderItem(
      class_1799 stack,
      class_811 renderMode,
      boolean leftHanded,
      class_4587 matrices,
      class_4597 vertexConsumers,
      int light,
      int overlay,
      class_1087 model,
      Operation<Void> original
   ) {
      if (vertexConsumers instanceof HudBatchingBufferSource hudBatchingBufferSource) {
         hudBatchingBufferSource.setRenderingItem(true);
      }

      try {
         original.call(new Object[]{stack, renderMode, leftHanded, matrices, vertexConsumers, light, overlay, model});
      } finally {
         if (vertexConsumers instanceof HudBatchingBufferSource hudBatchingBufferSource) {
            hudBatchingBufferSource.setRenderingItem(false);
         }
      }
   }
}
