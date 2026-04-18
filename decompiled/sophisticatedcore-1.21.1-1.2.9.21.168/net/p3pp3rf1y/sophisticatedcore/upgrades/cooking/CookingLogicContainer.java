package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1874;
import net.minecraft.class_1937;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;

public class CookingLogicContainer<T extends class_1874> {
   private final Supplier<CookingLogic<T>> supplyCoookingLogic;
   private final List<class_1735> smeltingSlots = new ArrayList<>();

   public CookingLogicContainer(Supplier<CookingLogic<T>> supplyCoookingLogic, Consumer<class_1735> addSlot) {
      this.supplyCoookingLogic = supplyCoookingLogic;
      this.addSmeltingSlot(addSlot, new CookingLogicContainer.CookingSlot(() -> supplyCoookingLogic.get().getCookingInventory(), 0, -100, -100));
      this.addSmeltingSlot(addSlot, new CookingLogicContainer.CookingSlot(() -> supplyCoookingLogic.get().getCookingInventory(), 1, -100, -100));
      this.addSmeltingSlot(addSlot, new SlotSuppliedHandler(() -> supplyCoookingLogic.get().getCookingInventory(), 2, -100, -100) {
         @Override
         public boolean method_7680(class_1799 stack) {
            return false;
         }
      });
   }

   private void addSmeltingSlot(Consumer<class_1735> addSlot, class_1735 slot) {
      addSlot.accept(slot);
      this.smeltingSlots.add(slot);
   }

   public int getBurnTimeTotal() {
      return this.supplyCoookingLogic.get().getBurnTimeTotal();
   }

   public long getBurnTimeFinish() {
      return this.supplyCoookingLogic.get().getBurnTimeFinish();
   }

   public long getCookTimeFinish() {
      return this.supplyCoookingLogic.get().getCookTimeFinish();
   }

   public int getCookTimeTotal() {
      return this.supplyCoookingLogic.get().getCookTimeTotal();
   }

   public boolean isCooking() {
      return this.supplyCoookingLogic.get().isCooking();
   }

   public boolean isBurning(class_1937 level) {
      return this.supplyCoookingLogic.get().isBurning(level);
   }

   public List<class_1735> getCookingSlots() {
      return this.smeltingSlots;
   }

   private static class CookingSlot extends SlotSuppliedHandler {
      public CookingSlot(Supplier<SlottedStackStorage> itemHandlerSupplier, int slot, int xPosition, int yPosition) {
         super(itemHandlerSupplier, slot, xPosition, yPosition);
      }

      @Override
      public int method_7676(class_1799 stack) {
         return stack.method_7914();
      }
   }
}
