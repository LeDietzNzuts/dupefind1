package org.embeddedt.modernfix.common.mixin.perf.compact_mojang_registries;

import java.util.Map;
import net.minecraft.class_2370;
import net.minecraft.class_5321;
import net.minecraft.class_9248;
import org.embeddedt.modernfix.annotation.IgnoreOutsideDev;
import org.embeddedt.modernfix.registry.LifecycleMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2370.class)
@IgnoreOutsideDev
public abstract class MappedRegistryMixin<T> {
   @Shadow
   @Final
   @Mutable
   private Map<class_5321<T>, class_9248> field_49135;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void replaceStorage(CallbackInfo ci) {
      this.field_49135 = new LifecycleMap();
   }
}
