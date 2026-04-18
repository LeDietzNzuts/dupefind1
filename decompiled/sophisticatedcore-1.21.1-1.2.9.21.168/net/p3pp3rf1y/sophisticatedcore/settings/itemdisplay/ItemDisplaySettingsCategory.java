package net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1767;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2487;
import net.minecraft.class_2497;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.renderdata.DisplaySide;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.ISlotColorCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.MathHelper;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class ItemDisplaySettingsCategory implements ISettingsCategory<ItemDisplaySettingsCategory>, ISlotColorCategory {
   public static final String NAME = "item_display";
   private static final String SLOT_TAG = "slot";
   private static final String ROTATION_TAG = "rotation";
   private static final String SLOTS_TAG = "slots";
   private static final String ROTATIONS_TAG = "rotations";
   private static final String COLOR_TAG = "color";
   private static final String DISPLAY_SIDE_TAG = "displaySide";
   private final Supplier<InventoryHandler> inventoryHandlerSupplier;
   private final Supplier<RenderInfo> renderInfoSupplier;
   private class_2487 categoryNbt;
   private final Consumer<class_2487> saveNbt;
   private final int itemNumberLimit;
   private final Supplier<MemorySettingsCategory> getMemorySettings;
   private class_1767 color = class_1767.field_7964;
   private final List<Integer> slotIndexes = new LinkedList<>();
   private Map<Integer, Integer> slotRotations = new HashMap<>();
   private DisplaySide displaySide = DisplaySide.FRONT;

   public ItemDisplaySettingsCategory(
      Supplier<InventoryHandler> inventoryHandlerSupplier,
      Supplier<RenderInfo> renderInfoSupplier,
      class_2487 categoryNbt,
      Consumer<class_2487> saveNbt,
      int itemNumberLimit,
      Supplier<MemorySettingsCategory> getMemorySettings
   ) {
      this.inventoryHandlerSupplier = inventoryHandlerSupplier;
      this.renderInfoSupplier = renderInfoSupplier;
      this.categoryNbt = categoryNbt;
      this.saveNbt = saveNbt;
      this.itemNumberLimit = itemNumberLimit;
      this.getMemorySettings = getMemorySettings;
      this.deserialize();
   }

   public int getItemNumberLimit() {
      return this.itemNumberLimit;
   }

   public void unselectSlot(int slotIndex) {
      int orderIndex = this.slotIndexes.indexOf(slotIndex);
      this.slotIndexes.remove(orderIndex);
      this.slotRotations.remove(slotIndex);
      if (this.slotIndexes.isEmpty()) {
         this.categoryNbt.method_10551("slots");
         this.categoryNbt.method_10551("rotations");
      }

      this.serializeSlotIndexes();
      this.updateFullRenderInfo();
   }

   private boolean haveRenderedItemsChanged() {
      List<RenderInfo.DisplayItem> previousDisplayItems = this.renderInfoSupplier.get().getItemDisplayRenderInfo().getDisplayItems();
      List<Integer> inaccessibleSlots = this.renderInfoSupplier.get().getItemDisplayRenderInfo().getInaccessibleSlots();
      if (previousDisplayItems.size() != this.slotIndexes.size()) {
         return true;
      } else {
         int i = 0;
         InventoryHandler inventoryHandler = this.inventoryHandlerSupplier.get();

         for (int slotIndex : this.slotIndexes) {
            class_1799 newItem = this.getSlotItemCopy(slotIndex).orElse(class_1799.field_8037);
            class_1799 stack = previousDisplayItems.get(i).getItem();
            if (class_1799.method_57355(newItem) != class_1799.method_57355(stack)
               || inaccessibleSlots.contains(slotIndex) == inventoryHandler.isSlotAccessible(slotIndex)) {
               return true;
            }

            i++;
         }

         if (this.renderInfoSupplier.get().showsCountsAndFillRatios()) {
            List<Integer> previousSlotCounts = this.renderInfoSupplier.get().getItemDisplayRenderInfo().getSlotCounts();
            List<Float> previousSlotFillRatios = this.renderInfoSupplier.get().getItemDisplayRenderInfo().getSlotFillRatios();
            List<Integer> previousInfiniteSlots = this.renderInfoSupplier.get().getItemDisplayRenderInfo().getInfiniteSlots();
            if (previousSlotCounts.size() != inventoryHandler.getSlotCount() || previousSlotFillRatios.size() != inventoryHandler.getSlotCount()) {
               return true;
            }

            for (int slotIndex = 0; slotIndex < inventoryHandler.getSlotCount(); slotIndex++) {
               int previousSlotCount = previousSlotCounts.get(slotIndex);
               float previousSlotFillRatio = previousSlotFillRatios.get(slotIndex);
               class_1799 stack = inventoryHandler.getStackInSlot(slotIndex);
               float currentSlotFillRatio = calculateSlotFillRatio(stack, inventoryHandler, slotIndex);
               if (previousSlotCount != stack.method_7947()
                  || previousInfiniteSlots.contains(slotIndex) != inventoryHandler.isInfinite(slotIndex)
                  || !MathHelper.epsilonEquals(previousSlotFillRatio, currentSlotFillRatio)) {
                  return true;
               }
            }
         }

         return i != previousDisplayItems.size();
      }
   }

   private void updateFullRenderInfo() {
      List<RenderInfo.DisplayItem> displayItems = new ArrayList<>();
      List<Integer> inaccessibleSlots = new ArrayList<>();
      InventoryHandler inventoryHandler = this.inventoryHandlerSupplier.get();

      for (int slotIndex : this.slotIndexes) {
         displayItems.add(
            new RenderInfo.DisplayItem(
               this.getSlotItemCopy(slotIndex).orElse(class_1799.field_8037), this.slotRotations.getOrDefault(slotIndex, 0), slotIndex, this.displaySide
            )
         );
         if (!inventoryHandler.isSlotAccessible(slotIndex)) {
            inaccessibleSlots.add(slotIndex);
         }
      }

      List<Integer> slotCounts = new ArrayList<>();
      List<Float> slotFillRatios = new ArrayList<>();
      List<Integer> infiniteSlots = new ArrayList<>();
      if (this.renderInfoSupplier.get().showsCountsAndFillRatios()) {
         for (int slotIndexx = 0; slotIndexx < inventoryHandler.getSlotCount(); slotIndexx++) {
            class_1799 stack = inventoryHandler.getStackInSlot(slotIndexx);
            slotCounts.add(stack.method_7947());
            slotFillRatios.add(calculateSlotFillRatio(stack, inventoryHandler, slotIndexx));
            if (inventoryHandler.isInfinite(slotIndexx)) {
               infiniteSlots.add(slotIndexx);
            }
         }
      }

      this.renderInfoSupplier.get().refreshItemDisplayRenderInfo(displayItems, inaccessibleSlots, infiniteSlots, slotCounts, slotFillRatios);
   }

   private static float calculateSlotFillRatio(class_1799 stack, InventoryHandler inventoryHandler, int slotIndex) {
      return stack.method_7960() ? 0.0F : (float)stack.method_7947() / inventoryHandler.getStackLimit(slotIndex, stack);
   }

   private Optional<class_1799> getSlotItemCopy(int slotIndex) {
      class_1799 slotStack = this.inventoryHandlerSupplier.get().getStackInSlot(slotIndex);
      if (slotStack.method_7960()) {
         class_1792 filterItem = this.inventoryHandlerSupplier.get().getFilterItem(slotIndex);
         return filterItem != class_1802.field_8162
            ? Optional.of(new class_1799(filterItem))
            : this.getMemorySettings.get().getSlotFilterStack(slotIndex, true);
      } else {
         class_1799 stackCopy = slotStack.method_7972();
         stackCopy.method_7939(1);
         return Optional.of(stackCopy);
      }
   }

   public void selectSlot(int slotIndex) {
      if (this.slotIndexes.size() + 1 <= this.itemNumberLimit) {
         this.slotIndexes.add(slotIndex);
         this.serializeSlotIndexes();
         this.updateFullRenderInfo();
      }
   }

   private void serializeSlotIndexes() {
      this.categoryNbt.method_10572("slots", this.slotIndexes);
      this.saveNbt.accept(this.categoryNbt);
   }

   public List<Integer> getSlots() {
      return this.slotIndexes;
   }

   public int getRotation(int slotIndex) {
      return this.slotRotations.getOrDefault(slotIndex, 0);
   }

   public void rotate(int slotIndex, boolean clockwise) {
      if (this.slotIndexes.contains(slotIndex)) {
         int rotation = this.getRotation(slotIndex);
         rotation = (rotation + (clockwise ? 1 : -1) * 45 + 360) % 360;
         this.slotRotations.put(slotIndex, rotation);
         this.serializeRotations();
         this.updateFullRenderInfo();
      }
   }

   private void serializeRotations() {
      NBTHelper.putMap(this.categoryNbt, "rotations", this.slotRotations, String::valueOf, class_2497::method_23247);
      this.saveNbt.accept(this.categoryNbt);
   }

   public void setColor(class_1767 color) {
      this.color = color;
      this.categoryNbt.method_10569("color", color.method_7789());
      this.saveNbt.accept(this.categoryNbt);
   }

   public class_1767 getColor() {
      return this.color;
   }

   public DisplaySide getDisplaySide() {
      return this.displaySide;
   }

   public void setDisplaySide(DisplaySide displaySide) {
      this.displaySide = displaySide;
      this.categoryNbt.method_10582("displaySide", displaySide.method_15434());
      this.saveNbt.accept(this.categoryNbt);
      this.updateFullRenderInfo();
   }

   @Override
   public void reloadFrom(class_2487 categoryNbt) {
      this.categoryNbt = categoryNbt;
      this.deserialize();
   }

   public void overwriteWith(ItemDisplaySettingsCategory otherCategory) {
      this.slotIndexes.clear();
      this.slotIndexes.addAll(otherCategory.getSlots());
      this.serializeSlotIndexes();
      this.slotRotations.clear();
      this.slotRotations.putAll(otherCategory.slotRotations);
      this.serializeRotations();
      this.setColor(otherCategory.getColor());
      this.itemsChanged();
   }

   private void deserialize() {
      this.slotIndexes.clear();
      NBTHelper.getIntArray(this.categoryNbt, "slots").ifPresent(slots -> {
         for (int slot : slots) {
            this.slotIndexes.add(slot);
         }
      });
      this.slotRotations = NBTHelper.<Integer, Integer>getMap(
            this.categoryNbt, "rotations", Integer::valueOf, (k, v) -> Optional.of(((class_2497)v).method_10701())
         )
         .orElseGet(HashMap::new);
      this.color = NBTHelper.getInt(this.categoryNbt, "color").<class_1767>map(class_1767::method_7791).orElse(class_1767.field_7964);
      NBTHelper.getInt(this.categoryNbt, "slot").ifPresent(e -> {
         this.slotIndexes.add(e);
         this.categoryNbt.method_10551("slot");
         this.serializeSlotIndexes();
      });
      NBTHelper.getInt(this.categoryNbt, "rotation").ifPresent(r -> {
         if (!this.slotIndexes.isEmpty()) {
            this.slotRotations.put(this.slotIndexes.iterator().next(), r);
         }

         this.categoryNbt.method_10551("rotation");
         this.serializeRotations();
      });
      NBTHelper.getEnumConstant(this.categoryNbt, "displaySide", DisplaySide::fromName).ifPresent(ds -> this.displaySide = ds);
   }

   public void itemChanged(int changedSlotIndex) {
      if (SophisticatedCore.getCurrentServer() != null && SophisticatedCore.getCurrentServer().method_18854() && this.slotIndexes.contains(changedSlotIndex)) {
         if (this.haveRenderedItemsChanged()) {
            this.updateFullRenderInfo();
         }
      }
   }

   public void itemsChanged() {
      if (this.haveRenderedItemsChanged()) {
         this.updateFullRenderInfo();
      }
   }

   @Override
   public Optional<Integer> getSlotColor(int slotNumber) {
      return this.slotIndexes.contains(slotNumber) ? Optional.of(this.color.method_7787()) : Optional.empty();
   }

   public void selectSlots(int minSlot, int maxSlot) {
      for (int slotIndex = minSlot; slotIndex < maxSlot; slotIndex++) {
         if (this.slotIndexes.size() + 1 > this.itemNumberLimit) {
            return;
         }

         this.slotIndexes.add(slotIndex);
      }

      this.serializeSlotIndexes();
      this.updateFullRenderInfo();
   }

   @Override
   public boolean isLargerThanNumberOfSlots(int slots) {
      return this.slotIndexes.stream().anyMatch(slotIndex -> slotIndex >= slots);
   }

   public void copyTo(ItemDisplaySettingsCategory otherCategory, int startFromSlot, int slotOffset) {
   }

   @Override
   public void deleteSlotSettingsFrom(int slotIndex) {
      this.slotIndexes.removeIf(slot -> slot >= slotIndex);
      this.serializeSlotIndexes();
   }
}
