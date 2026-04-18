package org.embeddedt.modernfix.common.mixin.perf.remove_biome_temperature_cache;

import net.minecraft.class_1959;
import net.minecraft.class_2338;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_1959.class)
public abstract class BiomeMixin {
   @Shadow
   protected abstract float method_8707(class_2338 var1);

   @Overwrite
   private float method_21740(class_2338 pos) {
      return this.method_8707(pos);
   }
}
