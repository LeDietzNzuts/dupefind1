package net.p3pp3rf1y.sophisticatedcore.settings.memory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_2487;
import net.minecraft.class_2519;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.inventory.ItemStackKey;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class MemorySettingsCategory implements ISettingsCategory<MemorySettingsCategory> {
   public static final String NAME = "memory";
   private static final String SLOT_FILTER_ITEMS_TAG = "slotFilterItems";
   private static final String SLOT_FILTER_STACKS_TAG = "slotFilterStacks";
   private static final String IGNORE_NBT_TAG = "ignoreNbt";
   private final Supplier<InventoryHandler> inventoryHandlerSupplier;
   private class_2487 categoryNbt;
   private final Consumer<class_2487> saveNbt;
   private final Map<Integer, class_1792> slotFilterItems = new LinkedHashMap<>();
   private final Map<Integer, ItemStackKey> slotFilterStacks = new LinkedHashMap<>();
   private final Map<class_1792, Set<Integer>> filterItemSlots = new HashMap<>();
   private final Map<Integer, Set<Integer>> filterStackSlots = new HashMap<>();
   private boolean ignoreNbt = true;
   private Consumer<class_1792> onItemAdded = i -> {};
   private Consumer<Integer> onStackAdded = i -> {};
   private Consumer<class_1792> onItemRemoved = i -> {};
   private Consumer<Integer> onStackRemoved = i -> {};

   public MemorySettingsCategory(Supplier<InventoryHandler> inventoryHandlerSupplier, class_2487 categoryNbt, Consumer<class_2487> saveNbt) {
      this.inventoryHandlerSupplier = inventoryHandlerSupplier;
      this.categoryNbt = categoryNbt;
      this.saveNbt = saveNbt;
      this.deserialize();
   }

   private void deserialize() {
      NBTHelper.getMap(
            this.categoryNbt, "slotFilterItems", Integer::valueOf, (k, v) -> class_7923.field_41178.method_17966(class_2960.method_60654(v.method_10714()))
         )
         .ifPresent(map -> map.forEach(this::addSlotItem));
      NBTHelper.getMap(
            this.categoryNbt,
            "slotFilterStacks",
            Integer::valueOf,
            (k, v) -> v instanceof class_2487 tag
               ? RegistryHelper.getRegistryAccess().flatMap(registryAccess -> class_1799.method_57360(registryAccess, tag))
               : Optional.empty()
         )
         .ifPresent(map -> map.forEach(this::addSlotStack));
      this.ignoreNbt = NBTHelper.getBoolean(this.categoryNbt, "ignoreNbt").orElse(true);
   }

   public boolean matchesFilter(int slotNumber, class_1799 stack) {
      if (this.slotFilterItems.containsKey(slotNumber)) {
         return !stack.method_7960() && stack.method_7909() == this.slotFilterItems.get(slotNumber);
      } else {
         return !this.slotFilterStacks.containsKey(slotNumber) ? true : !stack.method_7960() && this.slotFilterStacks.get(slotNumber).matches(stack);
      }
   }

   public Optional<class_1799> getSlotFilterStack(int slotNumber, boolean copy) {
      if (this.slotFilterItems.containsKey(slotNumber)) {
         return Optional.of(new class_1799((class_1935)this.slotFilterItems.get(slotNumber)));
      } else if (this.slotFilterStacks.containsKey(slotNumber)) {
         class_1799 filterStack = this.slotFilterStacks.get(slotNumber).getStack();
         return Optional.of(copy ? filterStack.method_7972() : filterStack);
      } else {
         return Optional.empty();
      }
   }

   public boolean isSlotSelected(int slotNumber) {
      return this.slotFilterItems.containsKey(slotNumber) || this.slotFilterStacks.containsKey(slotNumber);
   }

   public void unselectAllSlots() {
      this.unselectAllFilterItemSlots();
      this.unselectAllFilteStackSlots();
      this.serializeFilterItems();
   }

   private void unselectAllFilteStackSlots() {
      this.filterStackSlots.keySet().forEach(i -> this.onStackRemoved.accept(i));
      this.slotFilterStacks.clear();
      this.filterStackSlots.clear();
   }

   private void unselectAllFilterItemSlots() {
      this.filterItemSlots.keySet().forEach(i -> this.onItemRemoved.accept(i));
      this.slotFilterItems.clear();
      this.filterItemSlots.clear();
   }

   public void selectSlots(int minSlot, int maxSlot) {
      for (int slot = minSlot; slot < maxSlot; slot++) {
         InventoryHandler inventoryHandler = this.getInventoryHandler();
         if (slot < inventoryHandler.getSlotCount()) {
            class_1799 stackInSlot = inventoryHandler.getStackInSlot(slot);
            if (!stackInSlot.method_7960()) {
               if (this.ignoreNbt) {
                  class_1792 item = stackInSlot.method_7909();
                  this.addSlotItem(slot, item);
               } else {
                  this.addSlotStack(slot, stackInSlot);
               }
            } else {
               class_1792 filterItem = inventoryHandler.getFilterItem(slot);
               if (filterItem != class_1802.field_8162) {
                  if (this.ignoreNbt) {
                     this.addSlotItem(slot, filterItem);
                  } else {
                     this.addSlotStack(slot, new class_1799(filterItem));
                  }
               }
            }
         }
      }

      this.serializeFilterItems();
   }

   private InventoryHandler getInventoryHandler() {
      return this.inventoryHandlerSupplier.get();
   }

   private void addSlotItem(int slot, class_1792 item) {
      this.slotFilterItems.put(slot, item);
      this.filterItemSlots.computeIfAbsent(item, k -> {
         this.onItemAdded.accept(k);
         return new TreeSet<>();
      }).add(slot);
   }

   private void addSlotStack(int slot, class_1799 stack) {
      ItemStackKey isk = ItemStackKey.of(stack);
      this.slotFilterStacks.put(slot, isk);
      int stackHash = isk.hashCode();
      this.filterStackSlots.computeIfAbsent(stackHash, k -> {
         this.onStackAdded.accept(stackHash);
         return new TreeSet<>();
      }).add(slot);
   }

   public void selectSlot(int slotNumber) {
      this.selectSlots(slotNumber, slotNumber + 1);
   }

   public void unselectSlot(int slotNumber) {
      this.unselectFilterItemSlot(slotNumber);
      this.unselectFilterStackSlot(slotNumber);
      this.serializeFilterItems();
   }

   private void unselectFilterItemSlot(int slotNumber) {
      if (this.slotFilterItems.containsKey(slotNumber)) {
         class_1792 item = this.slotFilterItems.remove(slotNumber);
         Set<Integer> itemSlots = this.filterItemSlots.get(item);
         itemSlots.remove(slotNumber);
         if (itemSlots.isEmpty()) {
            this.filterItemSlots.remove(item);
            this.onItemRemoved.accept(item);
         }
      }
   }

   private void unselectFilterStackSlot(int slotNumber) {
      if (this.slotFilterStacks.containsKey(slotNumber)) {
         ItemStackKey isk = this.slotFilterStacks.remove(slotNumber);
         int stackHash = isk.hashCode();
         Set<Integer> stackSlots = this.filterStackSlots.get(stackHash);
         stackSlots.remove(slotNumber);
         if (stackSlots.isEmpty()) {
            this.filterStackSlots.remove(stackHash);
            this.onStackRemoved.accept(stackHash);
         }
      }
   }

   public boolean ignoresNbt() {
      return this.ignoreNbt;
   }

   public void setIgnoreNbt(boolean ignoreNbt) {
      if (this.ignoreNbt != ignoreNbt) {
         Set<Integer> slotIndexes = this.getSlotIndexes();
         if (this.ignoreNbt && !ignoreNbt) {
            this.slotFilterItems.forEach((slot, item) -> {
               class_1799 stack = this.inventoryHandlerSupplier.get().getStackInSlot(slot);
               if (stack.method_7960()) {
                  stack = new class_1799(item);
               }

               this.addSlotStack(slot, stack);
            });
            this.unselectAllFilterItemSlots();
         } else {
            this.slotFilterStacks.forEach((slot, isk) -> this.addSlotItem(slot, isk.getStack().method_7909()));
            this.unselectAllFilteStackSlots();
         }

         this.serializeFilterItems();
         this.ignoreNbt = ignoreNbt;
         this.serializeIgnoreNbt();
         slotIndexes.forEach(this::selectSlot);
      }
   }

   private void serializeIgnoreNbt() {
      this.categoryNbt.method_10556("ignoreNbt", this.ignoreNbt);
      this.saveNbt.accept(this.categoryNbt);
   }

   private void serializeFilterItems() {
      NBTHelper.putMap(
         this.categoryNbt,
         "slotFilterItems",
         this.slotFilterItems,
         String::valueOf,
         i -> class_2519.method_23256(class_7923.field_41178.method_10221(i).toString())
      );
      NBTHelper.putMap(
         this.categoryNbt,
         "slotFilterStacks",
         this.slotFilterStacks,
         String::valueOf,
         isk -> RegistryHelper.getRegistryAccess().map(registryAccess -> isk.stack().method_57375(registryAccess)).orElse(new class_2487())
      );
      this.saveNbt.accept(this.categoryNbt);
   }

   @Override
   public void reloadFrom(class_2487 categoryNbt) {
      this.categoryNbt = categoryNbt;
      this.slotFilterItems.clear();
      this.filterItemSlots.clear();
      this.slotFilterStacks.clear();
      this.filterStackSlots.clear();
      this.deserialize();
   }

   public void overwriteWith(MemorySettingsCategory otherCategory) {
      this.unselectAllSlots();
      this.ignoreNbt = otherCategory.ignoreNbt;
      if (this.ignoreNbt) {
         this.overwriteFilterItems(otherCategory);
      } else {
         this.overwriteFilterStacks(otherCategory);
      }

      this.serializeIgnoreNbt();
      this.serializeFilterItems();
   }

   private void overwriteFilterStacks(MemorySettingsCategory otherCategory) {
      InventoryHandler inventoryHandler = this.getInventoryHandler();
      otherCategory.slotFilterStacks.forEach((slot, isk) -> {
         if (slot < inventoryHandler.getSlotCount()) {
            class_1799 stackInSlot = inventoryHandler.getStackInSlot(slot);
            if (stackInSlot.method_7960() || otherCategory.matchesFilter(slot, stackInSlot)) {
               this.addSlotStack(slot, isk.getStack());
            }
         }
      });
   }

   private void overwriteFilterItems(MemorySettingsCategory otherCategory) {
      InventoryHandler inventoryHandler = this.getInventoryHandler();
      otherCategory.slotFilterItems.forEach((slot, item) -> {
         if (slot < inventoryHandler.getSlotCount()) {
            class_1799 stackInSlot = inventoryHandler.getStackInSlot(slot);
            if (stackInSlot.method_7960() || otherCategory.matchesFilter(slot, stackInSlot)) {
               this.addSlotItem(slot, item);
            }
         }
      });
   }

   public Set<Integer> getSlotIndexes() {
      HashSet<Integer> slots = new HashSet<>(this.slotFilterItems.keySet());
      slots.addAll(this.slotFilterStacks.keySet());
      return slots;
   }

   public Map<class_1792, Set<Integer>> getFilterItemSlots() {
      return this.filterItemSlots;
   }

   public Map<Integer, Set<Integer>> getFilterStackSlots() {
      return this.filterStackSlots;
   }

   public boolean matchesFilter(class_1799 stack) {
      return this.filterItemSlots.containsKey(stack.method_7909())
         || !this.filterStackSlots.isEmpty() && this.filterStackSlots.containsKey(class_1799.method_57355(stack));
   }

   public void registerListeners(
      Consumer<class_1792> onItemAdded, Consumer<class_1792> onItemRemoved, Consumer<Integer> onStackAdded, Consumer<Integer> onStackRemoved
   ) {
      this.onItemAdded = onItemAdded;
      this.onItemRemoved = onItemRemoved;
      this.onStackAdded = onStackAdded;
      this.onStackRemoved = onStackRemoved;
   }

   public void unregisterListeners() {
      this.onItemAdded = i -> {};
      this.onItemRemoved = i -> {};
      this.onStackAdded = i -> {};
      this.onStackRemoved = i -> {};
   }

   public void setFilter(int slot, class_1799 filter) {
      InventoryHandler inventoryHandler = this.getInventoryHandler();
      if (slot < inventoryHandler.getSlotCount()) {
         class_1799 stackInSlot = inventoryHandler.getStackInSlot(slot);
         if (stackInSlot.method_7960()) {
            if (this.ignoreNbt) {
               class_1792 item = filter.method_7909();
               this.addSlotItem(slot, item);
            } else {
               this.addSlotStack(slot, filter);
            }
         }
      }

      this.serializeFilterItems();
   }

   @Override
   public boolean isLargerThanNumberOfSlots(int slots) {
      return this.slotFilterItems.keySet().stream().anyMatch(slotIndex -> slotIndex >= slots)
         || this.slotFilterStacks.keySet().stream().anyMatch(slotIndex -> slotIndex >= slots);
   }

   public void copyTo(MemorySettingsCategory otherCategory, int startFromSlot, int slotOffset) {
      this.slotFilterItems.forEach((slotIndex, item) -> {
         if (slotIndex >= startFromSlot) {
            otherCategory.slotFilterItems.put(slotIndex + slotOffset, item);
         }
      });
      this.slotFilterStacks.forEach((slotIndex, isk) -> {
         if (slotIndex >= startFromSlot) {
            otherCategory.slotFilterStacks.put(slotIndex + slotOffset, isk);
         }
      });
      otherCategory.serializeFilterItems();
   }

   @Override
   public void deleteSlotSettingsFrom(int slotIndex) {
      this.slotFilterItems.entrySet().removeIf(e -> e.getKey() >= slotIndex);
      this.slotFilterStacks.entrySet().removeIf(e -> e.getKey() >= slotIndex);
      this.serializeFilterItems();
   }
}
