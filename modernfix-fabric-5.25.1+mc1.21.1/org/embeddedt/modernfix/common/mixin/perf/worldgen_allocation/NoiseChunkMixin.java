package org.embeddedt.modernfix.common.mixin.perf.worldgen_allocation;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.class_6568;
import net.minecraft.class_6910;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_6568.class, priority = 100)
public abstract class NoiseChunkMixin {
   @Shadow
   @Final
   @Mutable
   private Map<class_6910, class_6910> field_36582 = new Object2ObjectOpenHashMap();

   @Shadow
   protected abstract class_6910 method_40533(class_6910 var1);

   @Overwrite
   protected class_6910 method_40529(class_6910 unwrapped) {
      class_6910 func = this.field_36582.get(unwrapped);
      if (func == null) {
         func = this.method_40533(unwrapped);
         this.field_36582.put(unwrapped, func);
      }

      return func;
   }
}
