package net.caffeinemc.mods.lithium.common.util.collections;

import java.util.Comparator;
import net.minecraft.class_3228;
import net.minecraft.class_4706;

public class ChunkTicketSortedArraySet<T> extends class_4706<class_3228<?>> {
   private long minExpireTime = Long.MAX_VALUE;

   public ChunkTicketSortedArraySet(int initialCapacity) {
      super(initialCapacity, Comparator.naturalOrder());
   }

   public void addExpireTime(long time) {
      if (this.minExpireTime != Long.MAX_VALUE || this.isEmpty()) {
         this.minExpireTime = Math.min(this.minExpireTime, time);
      }
   }

   private void addExpireTimeInternal(long time) {
      this.minExpireTime = Math.min(this.minExpireTime, time);
   }

   public long getMinExpireTime() {
      if (this.minExpireTime == Long.MAX_VALUE) {
         this.recalculateExpireTime();
      }

      return this.minExpireTime;
   }

   public boolean remove(Object object) {
      this.invalidateExpireTime();
      return super.remove(object);
   }

   public void invalidateExpireTime() {
      this.minExpireTime = Long.MAX_VALUE;
   }

   public void recalculateExpireTime() {
      this.minExpireTime = Long.MAX_VALUE;

      for (class_3228<?> c : this) {
         this.addExpireTimeInternal(c.field_14024 + c.method_14281().method_20629());
      }
   }
}
