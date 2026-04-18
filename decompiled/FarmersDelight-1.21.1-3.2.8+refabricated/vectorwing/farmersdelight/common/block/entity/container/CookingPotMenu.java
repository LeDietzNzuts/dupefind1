package vectorwing.farmersdelight.common.block.entity.container;

import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1662;
import net.minecraft.class_1703;
import net.minecraft.class_1723;
import net.minecraft.class_1729;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_3913;
import net.minecraft.class_3914;
import net.minecraft.class_3919;
import net.minecraft.class_5421;
import net.minecraft.class_8786;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.inventory.ItemHandlerSlot;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;
import vectorwing.farmersdelight.refabricated.inventory.RecipeWrapper;

public class CookingPotMenu extends class_1729<RecipeWrapper, CookingPotRecipe> {
   public static final class_2960 EMPTY_CONTAINER_SLOT_BOWL = class_2960.method_60655("farmersdelight", "item/empty_container_slot_bowl");
   public final CookingPotBlockEntity blockEntity;
   public final ItemStackHandler inventory;
   private final class_3913 cookingPotData;
   private final class_3914 canInteractWithCallable;
   protected final class_1937 level;

   public CookingPotMenu(int windowId, class_1661 playerInventory, class_2338 data) {
      this(windowId, playerInventory, getTileEntity(playerInventory, data), new class_3919(4));
   }

   public CookingPotMenu(int windowId, class_1661 playerInventory, CookingPotBlockEntity blockEntity, class_3913 cookingPotDataIn) {
      super(ModMenuTypes.COOKING_POT.get(), windowId);
      this.blockEntity = blockEntity;
      this.inventory = blockEntity.getInventory();
      this.cookingPotData = cookingPotDataIn;
      this.level = playerInventory.field_7546.method_37908();
      this.canInteractWithCallable = class_3914.method_17392(blockEntity.method_10997(), blockEntity.method_11016());
      int startX = 8;
      int startY = 18;
      int inputStartX = 30;
      int inputStartY = 17;
      int borderSlotSize = 18;

      for (int row = 0; row < 2; row++) {
         for (int column = 0; column < 3; column++) {
            this.method_7621(new ItemHandlerSlot(this.inventory, row * 3 + column, inputStartX + column * borderSlotSize, inputStartY + row * borderSlotSize));
         }
      }

      this.method_7621(new CookingPotMealSlot(this.inventory, 6, 124, 26));
      this.method_7621(new ItemHandlerSlot(this.inventory, 7, 92, 55) {
         public Pair<class_2960, class_2960> method_7679() {
            return Pair.of(class_1723.field_21668, CookingPotMenu.EMPTY_CONTAINER_SLOT_BOWL);
         }
      });
      this.method_7621(new CookingPotResultSlot(playerInventory.field_7546, blockEntity, this.inventory, 8, 124, 55));
      int startPlayerInvY = startY * 4 + 12;

      for (int row = 0; row < 3; row++) {
         for (int column = 0; column < 9; column++) {
            this.method_7621(new class_1735(playerInventory, 9 + row * 9 + column, startX + column * borderSlotSize, startPlayerInvY + row * borderSlotSize));
         }
      }

      for (int column = 0; column < 9; column++) {
         this.method_7621(new class_1735(playerInventory, column, startX + column * borderSlotSize, 142));
      }

      this.method_17360(cookingPotDataIn);
   }

   private static CookingPotBlockEntity getTileEntity(class_1661 playerInventory, class_2338 data) {
      Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
      Objects.requireNonNull(data, "data cannot be null");
      class_2586 tileAtPos = playerInventory.field_7546.method_37908().method_8321(data);
      if (tileAtPos instanceof CookingPotBlockEntity) {
         return (CookingPotBlockEntity)tileAtPos;
      } else {
         throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
      }
   }

   public boolean method_7597(class_1657 playerIn) {
      return class_1703.method_17695(this.canInteractWithCallable, playerIn, ModBlocks.COOKING_POT.get());
   }

   public class_1799 method_7601(class_1657 playerIn, int index) {
      int indexMealDisplay = 6;
      int indexContainerInput = 7;
      int indexOutput = 8;
      int startPlayerInv = indexOutput + 1;
      int endPlayerInv = startPlayerInv + 36;
      class_1799 slotStackCopy = class_1799.field_8037;
      class_1735 slot = (class_1735)this.field_7761.get(index);
      if (slot.method_7681()) {
         class_1799 slotStack = slot.method_7677();
         slotStackCopy = slotStack.method_7972();
         if (index == indexOutput) {
            if (!this.method_7616(slotStack, startPlayerInv, endPlayerInv, true)) {
               return class_1799.field_8037;
            }
         } else if (index <= indexOutput) {
            if (!this.method_7616(slotStack, startPlayerInv, endPlayerInv, false)) {
               return class_1799.field_8037;
            }
         } else {
            boolean isValidContainer = slotStack.method_31573(ModTags.SERVING_CONTAINERS)
               || slotStack.method_31574(this.blockEntity.getContainer().method_7909());
            if (isValidContainer && !this.method_7616(slotStack, indexContainerInput, indexContainerInput + 1, false)) {
               return class_1799.field_8037;
            }

            if (!this.method_7616(slotStack, 0, indexMealDisplay, false)) {
               return class_1799.field_8037;
            }

            if (!this.method_7616(slotStack, indexContainerInput, indexOutput, false)) {
               return class_1799.field_8037;
            }
         }

         if (slotStack.method_7960()) {
            slot.method_7673(class_1799.field_8037);
         } else {
            slot.method_7668();
         }

         if (slotStack.method_7947() == slotStackCopy.method_7947()) {
            return class_1799.field_8037;
         }

         slot.method_7667(playerIn, slotStack);
      }

      return slotStackCopy;
   }

   public int getCookProgressionScaled() {
      int i = this.cookingPotData.method_17390(0);
      int j = this.cookingPotData.method_17390(1);
      return j != 0 && i != 0 ? i * 24 / j : 0;
   }

   public boolean isHeated() {
      return this.blockEntity.isHeated();
   }

   public void method_7654(class_1662 helper) {
      for (int i = 0; i < this.inventory.getSlotCount(); i++) {
         helper.method_7404(this.inventory.getStackInSlot(i));
      }
   }

   public void method_7657() {
      for (int i = 0; i < 6; i++) {
         this.inventory.setStackInSlot(i, class_1799.field_8037);
      }
   }

   public boolean method_7652(class_8786<CookingPotRecipe> recipe) {
      return ((CookingPotRecipe)recipe.comp_1933()).matches(new RecipeWrapper(this.inventory), this.level);
   }

   public int method_7655() {
      return 7;
   }

   public int method_7653() {
      return 3;
   }

   public int method_7656() {
      return 2;
   }

   public int method_7658() {
      return 7;
   }

   public class_5421 method_30264() {
      return class_5421.valueOf("FARMERSDELIGHT_COOKING");
   }

   public boolean method_32339(int slot) {
      return slot < this.method_7653() * this.method_7656();
   }
}
