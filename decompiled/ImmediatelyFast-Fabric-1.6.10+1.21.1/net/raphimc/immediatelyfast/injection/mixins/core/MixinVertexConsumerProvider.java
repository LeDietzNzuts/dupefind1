package net.raphimc.immediatelyfast.injection.mixins.core;

import java.util.SequencedMap;
import net.minecraft.class_1921;
import net.minecraft.class_4597;
import net.minecraft.class_9799;
import net.minecraft.class_4597.class_4598;
import net.raphimc.immediatelyfast.ImmediatelyFast;
import net.raphimc.immediatelyfast.feature.core.BatchableBufferSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_4597.class)
public interface MixinVertexConsumerProvider {
   @Overwrite
   static class_4598 method_22992(SequencedMap<class_1921, class_9799> layerBuffers, class_9799 fallbackBuffer) {
      return (class_4598)(ImmediatelyFast.config.debug_only_and_not_recommended_disable_universal_batching
         ? new class_4598(fallbackBuffer, layerBuffers)
         : new BatchableBufferSource(fallbackBuffer, layerBuffers));
   }
}
