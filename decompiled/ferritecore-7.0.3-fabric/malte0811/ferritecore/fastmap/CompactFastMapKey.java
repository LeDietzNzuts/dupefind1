package malte0811.ferritecore.fastmap;

import net.minecraft.class_2769;

public class CompactFastMapKey<T extends Comparable<T>> extends FastMapKey<T> {
   private final int mapFactor;

   CompactFastMapKey(class_2769<T> property, int mapFactor) {
      super(property);
      this.mapFactor = mapFactor;
   }

   @Override
   public T getValue(int mapIndex) {
      int index = mapIndex / this.mapFactor % this.numValues();
      return this.byInternalIndex(index);
   }

   @Override
   public int replaceIn(int mapIndex, Comparable<?> newValue) {
      int lowerData = mapIndex % this.mapFactor;
      int upperFactor = this.mapFactor * this.numValues();
      int upperData = mapIndex - mapIndex % upperFactor;
      int internalIndex = this.getInternalIndex(newValue);
      return internalIndex >= 0 && internalIndex < this.numValues() ? lowerData + this.mapFactor * internalIndex + upperData : -1;
   }

   @Override
   public int toPartialMapIndex(Comparable<?> value) {
      return this.mapFactor * this.getInternalIndex(value);
   }

   @Override
   public int getFactorToNext() {
      return this.numValues();
   }
}
