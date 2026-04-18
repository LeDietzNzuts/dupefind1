package org.embeddedt.modernfix.common.mixin.perf.deduplicate_climate_parameters;

import net.minecraft.class_6544.class_4762;
import net.minecraft.class_6544.class_6546;
import org.embeddedt.modernfix.dedup.ClimateCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({class_6546.class, class_4762.class})
public class ParameterMixin {
   @Redirect(method = "*", at = @At(value = "NEW", target = "net/minecraft/world/level/biome/Climate$Parameter"), require = 0)
   private static class_6546 internParameterStatic(long min, long max) {
      return (class_6546)ClimateCache.MFIX_INTERNER.intern(new class_6546(min, max));
   }
}
