package malte0811.ferritecore.fastmap;

import com.google.common.base.Preconditions;
import net.minecraft.class_2769;
import net.minecraft.class_3532;

public class BinaryFastMapKey<T extends Comparable<T>> extends FastMapKey<T> {
   private final byte firstBitInValue;
   private final byte firstBitAfterValue;

   public BinaryFastMapKey(class_2769<T> property, int mapFactor) {
      super(property);
      Preconditions.checkArgument(class_3532.method_15352(mapFactor));
      int addedFactor = class_3532.method_15339(this.numValues());
      Preconditions.checkState(this.numValues() <= addedFactor);
      Preconditions.checkState(addedFactor < 2 * this.numValues());
      int setBitInBaseFactor = class_3532.method_15351(mapFactor);
      int setBitInAddedFactor = class_3532.method_15351(addedFactor);
      Preconditions.checkState(setBitInBaseFactor + setBitInAddedFactor <= 31);
      this.firstBitInValue = (byte)setBitInBaseFactor;
      this.firstBitAfterValue = (byte)(setBitInBaseFactor + setBitInAddedFactor);
   }

   @Override
   public T getValue(int mapIndex) {
      int clearAbove = mapIndex & this.lowestNBits(this.firstBitAfterValue);
      return this.byInternalIndex(clearAbove >>> this.firstBitInValue);
   }

   @Override
   public int replaceIn(int mapIndex, Comparable<?> newValue) {
      int newPartialIndex = this.toPartialMapIndex(newValue);
      if (newPartialIndex < 0) {
         return -1;
      } else {
         int keepMask = ~this.lowestNBits(this.firstBitAfterValue) | this.lowestNBits(this.firstBitInValue);
         return keepMask & mapIndex | newPartialIndex;
      }
   }

   @Override
   public int toPartialMapIndex(Comparable<?> value) {
      int internalIndex = this.getInternalIndex(value);
      return internalIndex >= 0 && internalIndex < this.numValues() ? internalIndex << this.firstBitInValue : -1;
   }

   @Override
   public int getFactorToNext() {
      return 1 << this.firstBitAfterValue - this.firstBitInValue;
   }

   private int lowestNBits(byte n) {
      return n >= 32 ? -1 : (1 << n) - 1;
   }
}
