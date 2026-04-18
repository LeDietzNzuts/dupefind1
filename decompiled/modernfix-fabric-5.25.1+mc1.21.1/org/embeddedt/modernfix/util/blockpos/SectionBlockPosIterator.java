package org.embeddedt.modernfix.util.blockpos;

import java.util.Iterator;
import java.util.NoSuchElementException;
import net.minecraft.class_2338;
import net.minecraft.class_2338.class_2339;

public class SectionBlockPosIterator implements Iterator<class_2338> {
   private final class_2339 pos = new class_2339();
   private int index = 0;
   private final int baseX;
   private final int baseY;
   private final int baseZ;

   public SectionBlockPosIterator(int baseX, int baseY, int baseZ) {
      this.baseX = baseX;
      this.baseY = baseY;
      this.baseZ = baseZ;
   }

   public SectionBlockPosIterator(class_2338 pos) {
      this(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   @Override
   public boolean hasNext() {
      return this.index < 4096;
   }

   public class_2338 next() {
      int i = this.index;
      if (i >= 4096) {
         throw new NoSuchElementException();
      } else {
         this.index = i + 1;
         class_2339 pos = this.pos;
         pos.method_10103(this.baseX + (i & 15), this.baseY + (i >> 8 & 15), this.baseZ + (i >> 4 & 15));
         return pos;
      }
   }
}
