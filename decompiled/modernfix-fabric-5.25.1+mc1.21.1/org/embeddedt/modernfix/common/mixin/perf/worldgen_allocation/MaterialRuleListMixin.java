package org.embeddedt.modernfix.common.mixin.perf.worldgen_allocation;

import java.util.List;
import net.minecraft.class_2680;
import net.minecraft.class_6582;
import net.minecraft.class_6568.class_6569;
import net.minecraft.class_6910.class_6912;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_6582.class, priority = 100)
public class MaterialRuleListMixin {
   @Shadow
   @Final
   private List<class_6569> comp_437;

   @Overwrite
   @Nullable
   public class_2680 calculate(class_6912 arg) {
      class_2680 state = null;
      int s = this.comp_437.size();

      for (int i = 0; state == null && i < s; i++) {
         class_6569 blockStateFiller = this.comp_437.get(i);
         state = blockStateFiller.calculate(arg);
      }

      return state;
   }
}
