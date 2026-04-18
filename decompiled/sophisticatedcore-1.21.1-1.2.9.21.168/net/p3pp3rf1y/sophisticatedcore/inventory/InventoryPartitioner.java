package net.p3pp3rf1y.sophisticatedcore.inventory;

import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.class_1792;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2519;
import net.minecraft.class_2520;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.SlotRange;

public class InventoryPartitioner {
   public static final String BASE_INDEXES_TAG = "baseIndexes";
   private IInventoryPartHandler[] inventoryPartHandlers;
   private int[] baseIndexes;
   private final InventoryHandler parent;

   public InventoryPartitioner(class_2487 tag, InventoryHandler parent, Supplier<MemorySettingsCategory> getMemorySettings) {
      this.parent = parent;
      this.deserializeNBT(tag, getMemorySettings);
   }

   private int getIndexForSlot(int slot) {
      if (slot < 0) {
         return -1;
      } else {
         int i;
         for (i = 0; i < this.baseIndexes.length; i++) {
            if (slot - this.baseIndexes[i] < 0) {
               return i - 1;
            }
         }

         return i - 1;
      }
   }

   public IInventoryPartHandler getPartBySlot(int slot) {
      if (slot >= 0 && slot < this.parent.getSlotCount()) {
         int index = this.getIndexForSlot(slot);
         return index >= 0 && index < this.inventoryPartHandlers.length ? this.inventoryPartHandlers[index] : IInventoryPartHandler.EMPTY;
      } else {
         return IInventoryPartHandler.EMPTY;
      }
   }

   @Nullable
   public Pair<class_2960, class_2960> getNoItemIcon(int slot) {
      return this.getPartBySlot(slot).getNoItemIcon(slot);
   }

   public void onSlotLimitChange() {
      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         inventoryPartHandler.onSlotLimitChange();
      }
   }

   public Set<Integer> getNoSortSlots() {
      Set<Integer> noSortSlots = new HashSet<>();

      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         noSortSlots.addAll(inventoryPartHandler.getNoSortSlots());
      }

      return noSortSlots;
   }

   public boolean isFilterItem(class_1792 item) {
      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         if (inventoryPartHandler.isFilterItem(item)) {
            return true;
         }
      }

      return false;
   }

   public Map<class_1792, Set<Integer>> getFilterItems() {
      Map<class_1792, Set<Integer>> filterItems = new HashMap<>();

      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         for (Entry<class_1792, Set<Integer>> entry : inventoryPartHandler.getFilterItems().entrySet()) {
            filterItems.computeIfAbsent(entry.getKey(), k -> new HashSet<>()).addAll(entry.getValue());
         }
      }

      return filterItems;
   }

   public void onInit() {
      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         inventoryPartHandler.onInit();
      }
   }

   public Optional<SlotRange> getFirstSpace(int maxNumberOfSlots) {
      for (int partIndex = 0; partIndex < this.inventoryPartHandlers.length; partIndex++) {
         if (this.inventoryPartHandlers[partIndex].canBeReplaced()) {
            int firstSlot = this.baseIndexes[partIndex];
            int numberOfSlots = this.baseIndexes.length > partIndex + 1 ? this.baseIndexes[partIndex + 1] - firstSlot : this.parent.getSlotCount() - firstSlot;
            numberOfSlots = Math.min(numberOfSlots, maxNumberOfSlots);
            return numberOfSlots > 0 ? Optional.of(new SlotRange(this.baseIndexes[partIndex], numberOfSlots)) : Optional.empty();
         }
      }

      return Optional.empty();
   }

   public void addInventoryPart(int inventorySlot, int numberOfSlots, IInventoryPartHandler inventoryPartHandler) {
      int index = this.getIndexForSlot(inventorySlot);
      if (index >= 0 && index < this.inventoryPartHandlers.length && this.baseIndexes[index] == inventorySlot) {
         List<IInventoryPartHandler> newParts = new ArrayList<>();
         List<Integer> newBaseIndexes = new ArrayList<>();

         for (int i = 0; i < index; i++) {
            newParts.add(this.inventoryPartHandlers[i]);
            newBaseIndexes.add(this.baseIndexes[i]);
         }

         newParts.add(inventoryPartHandler);
         newBaseIndexes.add(inventorySlot);
         int newNextSlot = inventorySlot + numberOfSlots;
         if (this.inventoryPartHandlers[index].getSlots() > newNextSlot) {
            newParts.add(new IInventoryPartHandler.Default(this.parent, this.parent.getSlotCount() - newNextSlot));
            newBaseIndexes.add(newNextSlot);
         }

         for (int i = index + 1; i < this.inventoryPartHandlers.length; i++) {
            newParts.add(this.inventoryPartHandlers[i]);
            newBaseIndexes.add(this.baseIndexes[i]);
         }

         this.updatePartsAndIndexesFromLists(newParts, newBaseIndexes);
         inventoryPartHandler.onInit();
         this.parent.onFilterItemsChanged();
      }
   }

   public void removeInventoryPart(int inventorySlot) {
      int index = this.getIndexForSlot(inventorySlot);
      if (index >= 0 && index < this.inventoryPartHandlers.length && this.baseIndexes[index] == inventorySlot) {
         if (this.inventoryPartHandlers.length == 1) {
            this.updatePartsAndIndexesFromLists(List.of(new IInventoryPartHandler.Default(this.parent, this.parent.getSlotCount())), List.of(0));
            this.parent.onFilterItemsChanged();
         } else {
            int slotsAtPartIndex = (this.baseIndexes.length > index + 1 ? this.baseIndexes[index + 1] : this.parent.getSlotCount()) - this.baseIndexes[index];
            List<IInventoryPartHandler> newParts = new ArrayList<>();
            List<Integer> newBaseIndexes = new ArrayList<>();
            boolean replacedNext = false;

            for (int i = 0; i < index; i++) {
               if (i == index - 1
                  && this.inventoryPartHandlers[i] instanceof IInventoryPartHandler.Default
                  && this.baseIndexes.length > index + 1
                  && this.inventoryPartHandlers[index + 1] instanceof IInventoryPartHandler.Default) {
                  newParts.add(
                     new IInventoryPartHandler.Default(
                        this.parent, this.inventoryPartHandlers[i].getSlots() + this.inventoryPartHandlers[index + 1].getSlots() + slotsAtPartIndex
                     )
                  );
                  newBaseIndexes.add(this.baseIndexes[i]);
                  replacedNext = true;
               } else {
                  newParts.add(this.inventoryPartHandlers[i]);
                  newBaseIndexes.add(this.baseIndexes[i]);
               }
            }

            if (!replacedNext && this.baseIndexes.length > index + 1) {
               if (this.inventoryPartHandlers[index + 1] instanceof IInventoryPartHandler.Default) {
                  newParts.add(new IInventoryPartHandler.Default(this.parent, this.inventoryPartHandlers[index + 1].getSlots() + slotsAtPartIndex));
                  newBaseIndexes.add(inventorySlot);
               } else {
                  newParts.add(new IInventoryPartHandler.Default(this.parent, slotsAtPartIndex));
                  newBaseIndexes.add(inventorySlot);
                  newParts.add(this.inventoryPartHandlers[index + 1]);
                  newBaseIndexes.add(this.baseIndexes[index + 1]);
               }
            }

            for (int ix = index + 2; ix < this.inventoryPartHandlers.length; ix++) {
               newParts.add(this.inventoryPartHandlers[ix]);
               newBaseIndexes.add(this.baseIndexes[ix]);
            }

            this.updatePartsAndIndexesFromLists(newParts, newBaseIndexes);
            this.parent.onFilterItemsChanged();
         }
      }
   }

   private void updatePartsAndIndexesFromLists(List<IInventoryPartHandler> newParts, List<Integer> newBaseIndexes) {
      this.inventoryPartHandlers = newParts.toArray(new IInventoryPartHandler[0]);
      this.baseIndexes = new int[newBaseIndexes.size()];

      for (int i = 0; i < newBaseIndexes.size(); i++) {
         this.baseIndexes[i] = newBaseIndexes.get(i);
      }

      this.parent.saveInventory();
   }

   public class_2487 serializeNBT() {
      class_2487 ret = new class_2487();
      ret.method_10539("baseIndexes", this.baseIndexes);
      class_2499 partNames = new class_2499();

      for (IInventoryPartHandler inventoryPartHandler : this.inventoryPartHandlers) {
         partNames.add(class_2519.method_23256(inventoryPartHandler.getName()));
      }

      ret.method_10566("inventoryPartNames", partNames);
      return ret;
   }

   private void deserializeNBT(class_2487 tag, Supplier<MemorySettingsCategory> getMemorySettings) {
      if (!tag.method_10545("baseIndexes")) {
         this.inventoryPartHandlers = new IInventoryPartHandler[]{new IInventoryPartHandler.Default(this.parent, this.parent.getSlotCount())};
         this.baseIndexes = new int[]{0};
      } else {
         this.baseIndexes = tag.method_10561("baseIndexes");
         this.inventoryPartHandlers = new IInventoryPartHandler[this.baseIndexes.length];
         class_2499 partNamesTag = tag.method_10554("inventoryPartNames", 8);
         int i = 0;

         for (class_2520 t : partNamesTag) {
            SlotRange slotRange = new SlotRange(
               this.baseIndexes[i], (i + 1 < this.baseIndexes.length ? this.baseIndexes[i + 1] : this.parent.getSlotCount()) - this.baseIndexes[i]
            );
            this.inventoryPartHandlers[i] = InventoryPartRegistry.instantiatePart(t.method_10714(), this.parent, slotRange, getMemorySettings);
            i++;
         }
      }
   }

   public boolean isInfinite(int slot) {
      return this.getPartBySlot(slot).isInfinite(slot);
   }
}
