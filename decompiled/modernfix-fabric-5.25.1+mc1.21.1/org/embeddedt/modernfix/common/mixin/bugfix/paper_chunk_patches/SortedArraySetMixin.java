package org.embeddedt.modernfix.common.mixin.bugfix.paper_chunk_patches;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.function.Predicate;
import net.minecraft.class_4706;
import org.embeddedt.modernfix.annotation.RequiresMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4706.class)
@RequiresMod("!moonrise")
public abstract class SortedArraySetMixin<T> extends AbstractSet<T> {
   @Shadow
   private int field_21564;
   @Shadow
   private T[] field_21563;

   @Override
   public boolean removeIf(Predicate<? super T> filter) {
      int i = 0;
      int len = this.field_21564;

      for (T[] backingArray = this.field_21563; i < len; i++) {
         if (filter.test(backingArray[i])) {
            int lastIndex;
            for (lastIndex = i; i < len; i++) {
               T curr = backingArray[i];
               if (!filter.test(curr)) {
                  backingArray[lastIndex++] = curr;
               }
            }

            Arrays.fill(backingArray, lastIndex, len, null);
            this.field_21564 = lastIndex;
            return true;
         }
      }

      return false;
   }
}
