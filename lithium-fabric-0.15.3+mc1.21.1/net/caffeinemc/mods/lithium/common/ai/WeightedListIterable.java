package net.caffeinemc.mods.lithium.common.ai;

import java.util.Iterator;
import net.minecraft.class_6032;
import net.minecraft.class_6032.class_6033;

public interface WeightedListIterable<U> extends Iterable<U> {
   @Override
   Iterator<U> iterator();

   static <T> Iterable<? extends T> cast(class_6032<T> list) {
      return (WeightedListIterable)list;
   }

   public static class ListIterator<U> implements Iterator<U> {
      private final Iterator<class_6033<? extends U>> inner;

      public ListIterator(Iterator<class_6033<? extends U>> inner) {
         this.inner = inner;
      }

      @Override
      public boolean hasNext() {
         return this.inner.hasNext();
      }

      @Override
      public U next() {
         return (U)this.inner.next().method_35095();
      }
   }
}
