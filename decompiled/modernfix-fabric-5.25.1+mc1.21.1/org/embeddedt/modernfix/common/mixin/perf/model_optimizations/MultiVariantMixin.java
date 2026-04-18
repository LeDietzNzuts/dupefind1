package org.embeddedt.modernfix.common.mixin.perf.model_optimizations;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.List;
import java.util.function.Function;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import net.minecraft.class_807;
import net.minecraft.class_813;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_807.class, priority = 700)
@ClientOnlyMixin
public abstract class MultiVariantMixin {
   @Shadow
   public abstract List<class_813> method_3497();

   @Overwrite
   public void method_45785(Function<class_2960, class_1100> modelGetter) {
      List<class_813> variants = this.method_3497();
      if (variants.size() == 1) {
         modelGetter.apply(variants.get(0).method_3510()).method_45785(modelGetter);
      } else if (variants.size() > 1) {
         ObjectOpenHashSet<class_2960> seenLocations = new ObjectOpenHashSet(variants.size());

         for (class_813 variant : variants) {
            class_2960 location = variant.method_3510();
            if (seenLocations.add(location)) {
               modelGetter.apply(location).method_45785(modelGetter);
            }
         }
      }
   }
}
