package net.caffeinemc.mods.lithium.common.hopper;

import net.caffeinemc.mods.lithium.api.inventory.LithiumDefaultedList;
import net.caffeinemc.mods.lithium.common.block.entity.inventory_change_tracking.InventoryChangeTracker;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.caffeinemc.mods.lithium.mixin.block.hopper.NonNullListAccessor;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2586;
import net.minecraft.class_3532;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LithiumStackList extends class_2371<class_1799> implements LithiumDefaultedList, ChangeSubscriber.CountChangeSubscriber<class_1799> {
   final int maxCountPerStack;
   protected int cachedSignalStrength;
   private ComparatorUpdatePattern cachedComparatorUpdatePattern;
   private boolean signalStrengthOverride;
   private long modCount;
   private int occupiedSlots;
   private int fullSlots;
   LithiumDoubleStackList parent;
   InventoryChangeTracker nextInventoryModificationCallback;

   public LithiumStackList(class_2371<class_1799> original, int maxCountPerStack) {
      super(((NonNullListAccessor)original).getDelegate(), class_1799.field_8037);
      this.maxCountPerStack = maxCountPerStack;
      this.cachedSignalStrength = -1;
      this.cachedComparatorUpdatePattern = null;
      this.modCount = 0L;
      this.signalStrengthOverride = false;
      this.occupiedSlots = 0;
      this.fullSlots = 0;
      int size = this.size();

      for (int i = 0; i < size; i++) {
         class_1799 stack = (class_1799)this.get(i);
         if (!stack.method_7960()) {
            this.occupiedSlots++;
            if (stack.method_7914() <= stack.method_7947()) {
               this.fullSlots++;
            }

            ((ChangePublisher)stack).lithium$subscribe(this, i);
         }
      }

      this.nextInventoryModificationCallback = null;
   }

   public LithiumStackList(int maxCountPerStack) {
      super(null, class_1799.field_8037);
      this.maxCountPerStack = maxCountPerStack;
      this.cachedSignalStrength = -1;
      this.nextInventoryModificationCallback = null;
   }

   public long getModCount() {
      return this.modCount;
   }

   public void changedALot() {
      this.changed();
      this.occupiedSlots = 0;
      this.fullSlots = 0;
      int size = this.size();

      for (int i = 0; i < size; i++) {
         class_1799 stack = (class_1799)this.get(i);
         if (!stack.method_7960()) {
            this.occupiedSlots++;
            if (stack.method_7914() <= stack.method_7947()) {
               this.fullSlots++;
            }

            ((ChangePublisher)stack).lithium$unsubscribe(this);
         }
      }

      for (int ix = 0; ix < size; ix++) {
         class_1799 stack = (class_1799)this.get(ix);
         if (!stack.method_7960()) {
            ((ChangePublisher)stack).lithium$subscribe(this, ix);
         }
      }
   }

   public void changed() {
      this.cachedSignalStrength = -1;
      this.cachedComparatorUpdatePattern = null;
      this.modCount++;
      InventoryChangeTracker inventoryModificationCallback = this.nextInventoryModificationCallback;
      if (inventoryModificationCallback != null) {
         this.nextInventoryModificationCallback = null;
         inventoryModificationCallback.lithium$emitContentModified();
      }
   }

   public class_1799 set(int index, class_1799 element) {
      class_1799 previous = (class_1799)super.set(index, element);
      if (previous == element && !element.method_7960()) {
         boolean notSubscribed = ((ChangePublisher)previous).lithium$isSubscribedWithData(this, index);
         if (!notSubscribed) {
            previous = class_1799.field_8037;
         }
      }

      if (previous != element) {
         if (!previous.method_7960()) {
            ((ChangePublisher)previous).lithium$unsubscribeWithData(this, index);
         }

         if (!element.method_7960()) {
            ((ChangePublisher)element).lithium$subscribe(this, index);
         }

         this.occupiedSlots = this.occupiedSlots + ((previous.method_7960() ? 1 : 0) - (element.method_7960() ? 1 : 0));
         this.fullSlots = this.fullSlots
            + ((element.method_7947() >= element.method_7914() ? 1 : 0) - (previous.method_7947() >= previous.method_7914() ? 1 : 0));
         this.changed();
      }

      return previous;
   }

   public void add(int slot, class_1799 element) {
      super.add(slot, element);
      if (!element.method_7960()) {
         ((ChangePublisher)element).lithium$subscribe(this, this.indexOf(element));
      }

      this.changedALot();
   }

   public class_1799 remove(int index) {
      class_1799 previous = (class_1799)super.remove(index);
      if (!previous.method_7960()) {
         ((ChangePublisher)previous).lithium$unsubscribeWithData(this, index);
      }

      this.changedALot();
      return previous;
   }

   public void clear() {
      int size = this.size();

      for (int i = 0; i < size; i++) {
         class_1799 stack = (class_1799)this.get(i);
         if (!stack.method_7960()) {
            ((ChangePublisher)stack).lithium$unsubscribeWithData(this, i);
         }
      }

      super.clear();
      this.changedALot();
   }

   public boolean hasSignalStrengthOverride() {
      return this.signalStrengthOverride;
   }

   public int getSignalStrength(class_1263 inventory) {
      if (this.signalStrengthOverride) {
         return 0;
      } else {
         int signalStrength = this.cachedSignalStrength;
         return signalStrength == -1 ? (this.cachedSignalStrength = this.calculateSignalStrength(inventory.method_5439())) : signalStrength;
      }
   }

   int calculateSignalStrength(int inventorySize) {
      int i = 0;
      float f = 0.0F;
      inventorySize = Math.min(inventorySize, this.size());

      for (int j = 0; j < inventorySize; j++) {
         class_1799 itemStack = (class_1799)this.get(j);
         if (!itemStack.method_7960()) {
            f += (float)itemStack.method_7947() / Math.min(this.maxCountPerStack, itemStack.method_7914());
            i++;
         }
      }

      f /= inventorySize;
      return class_3532.method_15375(f * 14.0F) + (i > 0 ? 1 : 0);
   }

   public void setReducedSignalStrengthOverride() {
      this.signalStrengthOverride = true;
   }

   public void clearSignalStrengthOverride() {
      this.signalStrengthOverride = false;
   }

   public void runComparatorUpdatePatternOnFailedExtract(LithiumStackList masterStackList, class_1263 inventory) {
      if (inventory instanceof class_2586) {
         if (this.cachedComparatorUpdatePattern == null) {
            this.cachedComparatorUpdatePattern = HopperHelper.determineComparatorUpdatePattern(inventory, masterStackList);
         }

         this.cachedComparatorUpdatePattern.apply((class_2586)inventory, masterStackList);
      }
   }

   public boolean maybeSendsComparatorUpdatesOnFailedExtract() {
      return this.cachedComparatorUpdatePattern == null || this.cachedComparatorUpdatePattern != ComparatorUpdatePattern.NO_UPDATE;
   }

   public int getOccupiedSlots() {
      return this.occupiedSlots;
   }

   public int getFullSlots() {
      return this.fullSlots;
   }

   @Override
   public void changedInteractionConditions() {
      this.changed();
   }

   public void setNextInventoryModificationCallback(@NotNull InventoryChangeTracker nextInventoryModificationCallback) {
      if (this.nextInventoryModificationCallback != null && this.nextInventoryModificationCallback != nextInventoryModificationCallback) {
         this.nextInventoryModificationCallback.emitCallbackReplaced();
      }

      this.nextInventoryModificationCallback = nextInventoryModificationCallback;
   }

   public void removeInventoryModificationCallback(@NotNull InventoryChangeTracker inventoryModificationCallback) {
      if (this.nextInventoryModificationCallback != null && this.nextInventoryModificationCallback == inventoryModificationCallback) {
         this.nextInventoryModificationCallback = null;
      }
   }

   public void lithium$notify(@Nullable class_1799 publisher, int subscriberData) {
   }

   public void lithium$forceUnsubscribe(class_1799 publisher, int subscriberData) {
      throw new UnsupportedOperationException("Cannot force unsubscribe on a LithiumStackList!");
   }

   public void lithium$notifyCount(class_1799 stack, int index, int newCount) {
      assert stack == this.get(index);

      int count = stack.method_7947();
      if (newCount <= 0) {
         ((ChangePublisher)stack).lithium$unsubscribeWithData(this, index);
      }

      int maxCount = stack.method_7914();
      this.occupiedSlots -= newCount <= 0 ? 1 : 0;
      this.fullSlots += (newCount >= maxCount ? 1 : 0) - (count >= maxCount ? 1 : 0);
      this.changed();
   }
}
