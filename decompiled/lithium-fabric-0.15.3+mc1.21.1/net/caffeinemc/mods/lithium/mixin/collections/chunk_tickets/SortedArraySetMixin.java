package net.caffeinemc.mods.lithium.mixin.collections.chunk_tickets;

import java.util.Collection;
import java.util.function.Predicate;
import net.minecraft.class_4706;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4706.class)
public abstract class SortedArraySetMixin<T> implements Collection<T> {
   @Shadow
   int field_21564;
   @Shadow
   T[] field_21563;

   @Override
   public boolean removeIf(Predicate<? super T> filter) {
      T[] arr = this.field_21563;
      int writeLim = this.field_21564;
      int writeIdx = 0;

      for (int readIdx = 0; readIdx < writeLim; readIdx++) {
         T obj = arr[readIdx];
         if (!filter.test(obj)) {
            if (writeIdx != readIdx) {
               arr[writeIdx] = obj;
            }

            writeIdx++;
         }
      }

      this.field_21564 = writeIdx;
      return writeLim != writeIdx;
   }
}
