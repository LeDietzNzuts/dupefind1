package org.embeddedt.modernfix.common.mixin.perf.reduce_blockstate_cache_rebuilds;

import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_4970.class)
public interface BlockBehaviourInvoker {
   @Invoker
   class_3610 invokeGetFluidState(class_2680 var1);

   @Invoker
   boolean invokeIsRandomlyTicking(class_2680 var1);
}
