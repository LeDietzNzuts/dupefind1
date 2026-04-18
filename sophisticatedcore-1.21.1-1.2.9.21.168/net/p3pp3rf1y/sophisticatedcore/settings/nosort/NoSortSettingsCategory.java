package net.p3pp3rf1y.sophisticatedcore.settings.nosort;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import net.minecraft.class_1767;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.ISlotColorCategory;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class NoSortSettingsCategory implements ISettingsCategory<NoSortSettingsCategory>, ISlotColorCategory {
   public static final String NAME = "no_sort";
   private static final String COLOR_TAG = "color";
   private static final String SELECTED_SLOTS_TAG = "selectedSlots";
   private class_2487 categoryNbt;
   private final Consumer<class_2487> saveNbt;
   private final Set<Integer> selectedSlots = new HashSet<>();
   private class_1767 color = class_1767.field_7961;

   public NoSortSettingsCategory(class_2487 categoryNbt, Consumer<class_2487> saveNbt) {
      this.categoryNbt = categoryNbt;
      this.saveNbt = saveNbt;
      this.deserialize();
   }

   private void deserialize() {
      for (int slotNumber : this.categoryNbt.method_10561("selectedSlots")) {
         this.selectedSlots.add(slotNumber);
      }

      NBTHelper.getInt(this.categoryNbt, "color").ifPresent(c -> this.color = class_1767.method_7791(c));
   }

   public boolean isSlotSelected(int slotNumber) {
      return this.selectedSlots.contains(slotNumber);
   }

   public void unselectAllSlots() {
      this.selectedSlots.clear();
      this.serializeSelectedSlots();
   }

   public void selectSlots(int minSlot, int maxSlot) {
      for (int slot = minSlot; slot < maxSlot; slot++) {
         this.selectedSlots.add(slot);
      }

      this.serializeSelectedSlots();
   }

   public void selectSlot(int slotNumber) {
      this.selectSlots(slotNumber, slotNumber + 1);
   }

   public void unselectSlot(int slotNumber) {
      this.selectedSlots.remove(slotNumber);
      this.serializeSelectedSlots();
   }

   private void serializeSelectedSlots() {
      int[] slots = new int[this.selectedSlots.size()];
      int i = 0;

      for (int slotNumber : this.selectedSlots) {
         slots[i++] = slotNumber;
      }

      this.categoryNbt.method_10539("selectedSlots", slots);
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

   @Override
   public Optional<Integer> getSlotColor(int slotNumber) {
      return this.selectedSlots.contains(slotNumber) ? Optional.of(this.color.method_7787()) : Optional.empty();
   }

   public Set<Integer> getNoSortSlots() {
      return this.selectedSlots;
   }

   @Override
   public void reloadFrom(class_2487 categoryNbt) {
      this.categoryNbt = categoryNbt;
      this.selectedSlots.clear();
      this.color = class_1767.field_7961;
      this.deserialize();
   }

   public void overwriteWith(NoSortSettingsCategory otherCategory) {
      this.selectedSlots.clear();
      this.selectedSlots.addAll(otherCategory.getNoSortSlots());
      this.serializeSelectedSlots();
      this.setColor(otherCategory.getColor());
   }

   @Override
   public boolean isLargerThanNumberOfSlots(int slots) {
      return this.selectedSlots.stream().anyMatch(slotIndex -> slotIndex >= slots);
   }

   public void copyTo(NoSortSettingsCategory otherCategory, int startFromSlot, int slotOffset) {
      this.selectedSlots.forEach(slotIndex -> {
         if (slotIndex >= startFromSlot) {
            otherCategory.selectedSlots.add(slotIndex + slotOffset);
         }
      });
      otherCategory.serializeSelectedSlots();
   }

   @Override
   public void deleteSlotSettingsFrom(int slotIndex) {
      this.selectedSlots.removeIf(slot -> slot >= slotIndex);
      this.serializeSelectedSlots();
   }
}
