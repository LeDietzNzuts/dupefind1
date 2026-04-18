package org.embeddedt.modernfix.common.mixin.perf.worldgen_allocation;

import java.util.List;
import net.minecraft.class_2680;
import net.minecraft.class_6686.class_6709;
import net.minecraft.class_6686.class_6715;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_6709.class, priority = 100)
public class SequenceRuleMixin {
   @Shadow
   @Final
   private List<class_6715> comp_208;

   @Overwrite
   public class_2680 tryApply(int x, int y, int z) {
      int s = this.comp_208.size();

      for (int i = 0; i < s; i++) {
         class_2680 state = this.comp_208.get(i).tryApply(x, y, z);
         if (state != null) {
            return state;
         }
      }

      return null;
   }
}
