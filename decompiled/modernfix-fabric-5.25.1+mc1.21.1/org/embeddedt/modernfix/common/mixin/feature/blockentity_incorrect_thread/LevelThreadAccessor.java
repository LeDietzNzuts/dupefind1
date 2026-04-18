package org.embeddedt.modernfix.common.mixin.feature.blockentity_incorrect_thread;

import net.minecraft.class_1937;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1937.class)
public interface LevelThreadAccessor {
   @Accessor
   Thread getThread();
}
