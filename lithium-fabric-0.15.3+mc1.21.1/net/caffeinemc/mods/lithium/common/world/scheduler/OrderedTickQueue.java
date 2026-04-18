package net.caffeinemc.mods.lithium.common.world.scheduler;

import it.unimi.dsi.fastutil.HashCommon;
import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.class_6760;

public class OrderedTickQueue<T> extends AbstractQueue<class_6760<T>> {
   private static final int INITIAL_CAPACITY = 16;
   private static final Comparator<class_6760<?>> COMPARATOR = Comparator.comparingLong(class_6760::comp_256);
   private class_6760<T>[] arr;
   private int lastIndexExclusive;
   private int firstIndex;
   private long currentMaxSubTickOrder = Long.MIN_VALUE;
   private boolean isSorted;
   private class_6760<T> unsortedPeekResult;

   public OrderedTickQueue(int capacity) {
      this.arr = new class_6760[capacity];
      this.lastIndexExclusive = 0;
      this.isSorted = true;
      this.unsortedPeekResult = null;
      this.firstIndex = 0;
   }

   public OrderedTickQueue() {
      this(16);
   }

   @Override
   public void clear() {
      Arrays.fill(this.arr, null);
      this.lastIndexExclusive = 0;
      this.firstIndex = 0;
      this.currentMaxSubTickOrder = Long.MIN_VALUE;
      this.isSorted = true;
      this.unsortedPeekResult = null;
   }

   @Override
   public Iterator<class_6760<T>> iterator() {
      if (this.isEmpty()) {
         return Collections.emptyIterator();
      } else {
         this.sort();
         return new Iterator<class_6760<T>>() {
            int nextIndex = OrderedTickQueue.this.firstIndex;

            @Override
            public boolean hasNext() {
               return this.nextIndex < OrderedTickQueue.this.lastIndexExclusive;
            }

            public class_6760<T> next() {
               return OrderedTickQueue.this.arr[this.nextIndex++];
            }
         };
      }
   }

   public class_6760<T> poll() {
      if (this.isEmpty()) {
         return null;
      } else {
         if (!this.isSorted) {
            this.sort();
         }

         int polledIndex = this.firstIndex++;
         class_6760<T>[] ticks = this.arr;
         class_6760<T> nextTick = ticks[polledIndex];
         ticks[polledIndex] = null;
         return nextTick;
      }
   }

   public class_6760<T> peek() {
      if (!this.isSorted) {
         return this.unsortedPeekResult;
      } else {
         return this.lastIndexExclusive > this.firstIndex ? this.getTickAtIndex(this.firstIndex) : null;
      }
   }

   public boolean offer(class_6760<T> tick) {
      if (this.lastIndexExclusive >= this.arr.length) {
         this.arr = copyArray(this.arr, HashCommon.nextPowerOfTwo(this.arr.length + 1));
      }

      if (tick.comp_256() <= this.currentMaxSubTickOrder) {
         class_6760<T> firstTick = this.isSorted ? (this.size() > 0 ? this.arr[this.firstIndex] : null) : this.unsortedPeekResult;
         this.isSorted = false;
         this.unsortedPeekResult = firstTick != null && tick.comp_256() >= firstTick.comp_256() ? firstTick : tick;
      } else {
         this.currentMaxSubTickOrder = tick.comp_256();
      }

      this.arr[this.lastIndexExclusive++] = tick;
      return true;
   }

   @Override
   public int size() {
      return this.lastIndexExclusive - this.firstIndex;
   }

   private void handleCompaction(int size) {
      if (this.arr.length > 16 && size < this.arr.length / 2) {
         this.arr = copyArray(this.arr, size);
      } else {
         Arrays.fill(this.arr, size, this.arr.length, null);
      }

      this.firstIndex = 0;
      this.lastIndexExclusive = size;
      if (size != 0 && this.isSorted) {
         class_6760<T> tick = this.arr[size - 1];
         this.currentMaxSubTickOrder = tick == null ? Long.MIN_VALUE : tick.comp_256();
      } else {
         this.currentMaxSubTickOrder = Long.MIN_VALUE;
      }
   }

   public void sort() {
      if (!this.isSorted) {
         this.removeNullsAndConsumed();
         Arrays.sort(this.arr, this.firstIndex, this.lastIndexExclusive, COMPARATOR);
         this.isSorted = true;
         this.unsortedPeekResult = null;
      }
   }

   public void removeNullsAndConsumed() {
      int src = this.firstIndex;

      int dst;
      for (dst = 0; src < this.lastIndexExclusive; src++) {
         class_6760<T> orderedTick = this.arr[src];
         if (orderedTick != null) {
            this.arr[dst] = orderedTick;
            dst++;
         }
      }

      this.handleCompaction(dst);
   }

   public class_6760<T> getTickAtIndex(int index) {
      if (!this.isSorted) {
         throw new IllegalStateException("Unexpected access on unsorted queue!");
      } else {
         return this.arr[index];
      }
   }

   public void setTickAtIndex(int index, class_6760<T> tick) {
      if (!this.isSorted) {
         throw new IllegalStateException("Unexpected access on unsorted queue!");
      } else {
         this.arr[index] = tick;
      }
   }

   private static <T> class_6760<T>[] copyArray(class_6760<T>[] src, int size) {
      class_6760<T>[] copy = new class_6760[Math.max(16, size)];
      if (size != 0) {
         System.arraycopy(src, 0, copy, 0, Math.min(src.length, size));
      }

      return copy;
   }

   @Override
   public boolean isEmpty() {
      return this.lastIndexExclusive <= this.firstIndex;
   }
}
