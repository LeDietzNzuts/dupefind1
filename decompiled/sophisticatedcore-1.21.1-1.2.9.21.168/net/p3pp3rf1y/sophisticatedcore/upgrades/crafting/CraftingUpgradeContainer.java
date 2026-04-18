package net.p3pp3rf1y.sophisticatedcore.upgrades.crafting;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1731;
import net.minecraft.class_1734;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3955;
import net.minecraft.class_3956;
import net.minecraft.class_8566;
import net.minecraft.class_8786;
import net.minecraft.class_9694.class_9765;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class CraftingUpgradeContainer extends UpgradeContainerBase<CraftingUpgradeWrapper, CraftingUpgradeContainer> implements ICraftingContainer {
   private static final String DATA_SHIFT_CLICK_INTO_STORAGE = "shiftClickIntoStorage";
   private static final String DATA_SELECT_RESULT = "selectResult";
   private final class_1731 craftResult = new class_1731();
   private final CraftingItemHandler craftMatrix;
   private final class_1734 craftingResultSlot;
   @Nullable
   private class_8786<class_3955> lastRecipe = null;
   private List<class_8786<class_3955>> matchedCraftingRecipes = new ArrayList<>();
   private final List<class_1799> matchedCraftingResults = new ArrayList<>();
   private int selectedCraftingResultIndex = 0;

   public CraftingUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      CraftingUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<CraftingUpgradeWrapper, CraftingUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);

      int slot;
      for (slot = 0; slot < upgradeWrapper.getInventory().getSlotCount(); slot++) {
         this.slots
            .add(
               new SlotSuppliedHandler(upgradeWrapper::getInventory, slot, -100, -100) {
                  public void method_7668() {
                     super.method_7668();
                     CraftingUpgradeContainer.this.updateCraftingResult(
                        player.method_37908(),
                        player,
                        CraftingUpgradeContainer.this.craftMatrix,
                        CraftingUpgradeContainer.this.craftResult,
                        CraftingUpgradeContainer.this.craftingResultSlot
                     );
                     CraftingUpgradeContainer.this.craftMatrix.method_5431();
                  }

                  @Override
                  public boolean method_7674(class_1657 player) {
                     return this.method_7677().method_7960() || super.method_7674(player);
                  }
               }
            );
      }

      this.craftMatrix = new CraftingItemHandler(upgradeWrapper::getInventory, this::onCraftMatrixChanged);
      this.craftingResultSlot = new class_1734(player, this.craftMatrix, this.craftResult, slot, -100, -100) {
         public void method_7667(class_1657 thePlayer, class_1799 stack) {
            if (!thePlayer.method_37908().method_8608()) {
               class_1799 remainingStack = this.method_7677();
               this.method_7669(stack);
               List<class_1799> remainingItems;
               if (CraftingUpgradeContainer.this.lastRecipe != null
                  && ((class_3955)CraftingUpgradeContainer.this.lastRecipe.comp_1933())
                     .method_8115(CraftingUpgradeContainer.this.craftMatrix.method_59961(), player.method_37908())) {
                  remainingItems = ((class_3955)CraftingUpgradeContainer.this.lastRecipe.comp_1933())
                     .method_8111(CraftingUpgradeContainer.this.craftMatrix.method_59961());
               } else {
                  remainingItems = class_2371.method_10213(CraftingUpgradeContainer.this.craftMatrix.method_5439(), class_1799.field_8037);
               }

               class_9765 craftingInput = CraftingUpgradeContainer.this.craftMatrix.method_60501();
               int remaininItemsIndex = 0;

               for (int row = craftingInput.comp_2797(); row < craftingInput.comp_2797() + craftingInput.comp_2795().method_59992(); row++) {
                  for (int col = craftingInput.comp_2796(); col < craftingInput.comp_2796() + craftingInput.comp_2795().method_59991(); col++) {
                     int i = row * CraftingUpgradeContainer.this.craftMatrix.method_17398() + col;
                     if (remaininItemsIndex >= 9) {
                        this.logErrorAndDropRemainingItems(remaininItemsIndex, remainingItems);
                        break;
                     }

                     class_1799 recipeInputStack = CraftingUpgradeContainer.this.craftMatrix.method_5438(i);
                     class_1799 remainingItemStack = remainingItems.get(remaininItemsIndex);
                     if (!recipeInputStack.method_7960()) {
                        CraftingUpgradeContainer.this.craftMatrix.method_5434(i, 1);
                        recipeInputStack = CraftingUpgradeContainer.this.craftMatrix.method_5438(i);
                     }

                     if (!remainingItemStack.method_7960()) {
                        if (recipeInputStack.method_7960()) {
                           CraftingUpgradeContainer.this.craftMatrix.method_5447(i, remainingItemStack);
                        } else if (class_1799.method_31577(recipeInputStack, remainingItemStack)) {
                           remainingItemStack.method_7933(recipeInputStack.method_7947());
                           CraftingUpgradeContainer.this.craftMatrix.method_5447(i, remainingItemStack);
                        } else if (!player.method_31548().method_7394(remainingItemStack)) {
                           player.method_7328(remainingItemStack, false);
                        }
                     }

                     remaininItemsIndex++;
                  }
               }

               if (!remainingStack.method_7960()) {
                  player.method_7328(remainingStack, false);
               }
            }
         }

         private void logErrorAndDropRemainingItems(int remaininItemsIndex, List<class_1799> remainingItems) {
            for (int j = remaininItemsIndex; j < remainingItems.size(); j++) {
               class_1799 remaining = remainingItems.get(j);
               if (!remaining.method_7960()) {
                  player.method_7328(remaining, false);
               }
            }

            SophisticatedCore.LOGGER
               .error(
                  "Recipe "
                     + (CraftingUpgradeContainer.this.lastRecipe != null ? CraftingUpgradeContainer.this.lastRecipe.comp_1932() : "[unknown]")
                     + " returned more than 9 remaining items, ignoring the rest!"
               );
         }

         public void method_7673(class_1799 stack) {
            this.field_7871.method_5447(this.method_34266(), stack);
            this.method_7668();
         }

         public void method_7668() {
            super.method_7668();
            if (player.method_37908().method_8608()) {
               CraftingUpgradeContainer.this.matchedCraftingRecipes.clear();
               CraftingUpgradeContainer.this.matchedCraftingResults.clear();
               if (!this.method_7677().method_7960()) {
                  CraftingUpgradeContainer.this.matchedCraftingRecipes = RecipeHelper.safeGetRecipesFor(
                     class_3956.field_17545, CraftingUpgradeContainer.this.craftMatrix.method_59961(), player.method_37908()
                  );
                  int resultIndex = 0;

                  for (class_8786<class_3955> craftingRecipe : CraftingUpgradeContainer.this.matchedCraftingRecipes) {
                     class_1799 result = ((class_3955)craftingRecipe.comp_1933())
                        .method_8116(CraftingUpgradeContainer.this.craftMatrix.method_59961(), player.method_37908().method_30349());
                     CraftingUpgradeContainer.this.matchedCraftingResults.add(result);
                     if (class_1799.method_31577(this.method_7677(), result)) {
                        CraftingUpgradeContainer.this.selectedCraftingResultIndex = resultIndex;
                     }

                     resultIndex++;
                  }
               }
            }
         }
      };
      this.slots.add(this.craftingResultSlot);
   }

   @Override
   public void onInit() {
      super.onInit();
      this.onCraftMatrixChanged(this.craftMatrix);
   }

   private void onCraftMatrixChanged(class_1263 iInventory) {
      this.updateCraftingResult(this.player.method_37908(), this.player, this.craftMatrix, this.craftResult, this.craftingResultSlot);
   }

   private void updateCraftingResult(class_1937 level, class_1657 player, class_8566 inventory, class_1731 inventoryResult, class_1734 craftingResultSlot) {
      if (!level.field_9236) {
         class_3222 serverplayerentity = (class_3222)player;
         class_1799 itemstack = class_1799.field_8037;
         if (this.lastRecipe != null && ((class_3955)this.lastRecipe.comp_1933()).method_8115(inventory.method_59961(), level)) {
            itemstack = ((class_3955)this.lastRecipe.comp_1933()).method_8116(inventory.method_59961(), level.method_30349());
         } else {
            List<class_8786<class_3955>> recipes = RecipeHelper.safeGetRecipesFor(class_3956.field_17545, inventory.method_59961(), level);
            if (!recipes.isEmpty()) {
               this.matchedCraftingRecipes = recipes;
               this.matchedCraftingResults.clear();
               this.selectedCraftingResultIndex = 0;
               class_8786<class_3955> craftingRecipe = this.matchedCraftingRecipes.get(0);
               if (inventoryResult.method_7665(level, serverplayerentity, craftingRecipe)) {
                  this.lastRecipe = craftingRecipe;
                  itemstack = ((class_3955)this.lastRecipe.comp_1933()).method_8116(inventory.method_59961(), level.method_30349());
                  this.matchedCraftingResults.add(itemstack.method_7972());
               } else {
                  this.lastRecipe = null;
               }

               for (int i = 1; i < this.matchedCraftingRecipes.size(); i++) {
                  this.matchedCraftingResults
                     .add(((class_3955)this.matchedCraftingRecipes.get(i).comp_1933()).method_8116(inventory.method_59961(), level.method_30349()));
               }
            }
         }

         craftingResultSlot.method_7673(itemstack);
      }
   }

   public List<class_1799> getMatchedCraftingResults() {
      return this.matchedCraftingResults;
   }

   public void selectNextCraftingResult() {
      if (this.matchedCraftingResults.size() > 1) {
         this.selectCraftingResult((this.selectedCraftingResultIndex + 1) % this.matchedCraftingResults.size());
      }
   }

   public void selectPreviousCraftingResult() {
      if (this.matchedCraftingResults.size() > 1) {
         this.selectCraftingResult((this.selectedCraftingResultIndex + this.matchedCraftingResults.size() - 1) % this.matchedCraftingResults.size());
      }
   }

   public void selectCraftingResult(int resultIndex) {
      if (resultIndex >= 0 && resultIndex < this.matchedCraftingResults.size()) {
         if (this.player instanceof class_3222 serverPlayer) {
            this.selectedCraftingResultIndex = resultIndex;
            this.lastRecipe = this.matchedCraftingRecipes.get(resultIndex);
            class_1799 result = this.matchedCraftingResults.get(resultIndex).method_7972();
            this.craftingResultSlot.method_7673(result);
            this.craftResult.method_7665(this.player.method_37908(), serverPlayer, this.lastRecipe);
         } else {
            this.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "selectResult", resultIndex));
         }
      }
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("shiftClickIntoStorage")) {
         this.setShiftClickIntoStorage(data.method_10577("shiftClickIntoStorage"));
      } else if (data.method_10545("selectResult")) {
         this.selectCraftingResult(data.method_10550("selectResult"));
      }
   }

   @Override
   public class_1799 getSlotStackToTransfer(class_1735 slot) {
      if (slot == this.craftingResultSlot) {
         class_1799 slotStack = slot.method_7677();
         slotStack.method_7909().method_54465(slotStack, this.player.method_37908(), this.player);
         return slotStack;
      } else {
         return super.getSlotStackToTransfer(slot);
      }
   }

   @Override
   public List<class_1735> getRecipeSlots() {
      return this.slots.subList(0, 9);
   }

   @Override
   public class_1263 getCraftMatrix() {
      return this.craftMatrix;
   }

   @Override
   public void setRecipeUsed(class_2960 recipeId) {
      if (this.lastRecipe == null || !this.lastRecipe.comp_1932().equals(recipeId)) {
         this.player
            .method_37908()
            .method_8433()
            .method_8130(recipeId)
            .filter(r -> r.comp_1933().method_17716() == class_3956.field_17545)
            .map(r -> (class_8786)r)
            .ifPresent(recipe -> {
               this.lastRecipe = (class_8786<class_3955>)recipe;

               for (int i = 0; i < this.matchedCraftingRecipes.size(); i++) {
                  if (this.matchedCraftingRecipes.get(i).comp_1932().equals(recipeId)) {
                     this.selectCraftingResult(i);
                     return;
                  }
               }
            });
      }
   }

   @Override
   public class_3956<?> getRecipeType() {
      return class_3956.field_17545;
   }

   public boolean shouldShiftClickIntoStorage() {
      return this.upgradeWrapper.shouldShiftClickIntoStorage();
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      this.upgradeWrapper.setShiftClickIntoStorage(shiftClickIntoStorage);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shiftClickIntoStorage", shiftClickIntoStorage));
   }

   @Override
   public boolean mergeIntoStorageFirst(class_1735 slot) {
      return !(slot instanceof class_1734) || this.shouldShiftClickIntoStorage();
   }

   @Override
   public boolean allowsPickupAll(class_1735 slot) {
      return slot != this.craftingResultSlot;
   }
}
