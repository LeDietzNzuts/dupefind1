package net.p3pp3rf1y.sophisticatedcore.upgrades.stonecutter;

import com.google.common.collect.Lists;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1731;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3914;
import net.minecraft.class_3915;
import net.minecraft.class_3956;
import net.minecraft.class_3975;
import net.minecraft.class_8786;
import net.minecraft.class_9696;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.CraftingItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class StonecutterRecipeContainer {
   private static final String DATA_SELECTED_RECIPE_INDEX = "selectedRecipeIndex";
   private final class_1735 inputSlot;
   private final IServerUpdater serverUpdater;
   private final class_1937 level;
   private final class_1735 outputSlot;
   private final class_1731 resultInventory = new class_1731();
   private List<class_8786<class_3975>> recipes = Lists.newArrayList();
   private final class_3915 selectedRecipe = class_3915.method_17403();
   private class_1792 inputItem = class_1802.field_8162;
   private final CraftingItemHandler inputInventory;
   private Runnable inventoryUpdateListener = () -> {};
   private final Supplier<Optional<class_2960>> getLastSelectedRecipeId;
   private final Consumer<class_2960> setLastSelectedRecipeId;
   private long lastOnTake = -1L;

   public StonecutterRecipeContainer(
      StonecutterUpgradeContainer upgradeContainer, Consumer<class_1735> addSlot, IServerUpdater serverUpdater, class_3914 worldPosCallable, class_1937 level
   ) {
      this.inputSlot = new SlotSuppliedHandler(upgradeContainer.getUpgradeWrapper()::getInputInventory, 0, -1, -1) {
         public void method_7668() {
            super.method_7668();
            StonecutterRecipeContainer.this.onCraftMatrixChanged(StonecutterRecipeContainer.this.inputInventory);
         }

         @Override
         public class_1799 method_7671(int amount) {
            class_1799 ret = super.method_7671(amount);
            if (this.method_7677().method_7960()) {
               this.method_7668();
            }

            return ret;
         }
      };
      this.serverUpdater = serverUpdater;
      this.level = level;
      addSlot.accept(this.inputSlot);
      this.inputInventory = new CraftingItemHandler(upgradeContainer.getUpgradeWrapper()::getInputInventory, this::onCraftMatrixChanged);
      this.outputSlot = new StonecutterRecipeContainer.ResultSlot(worldPosCallable);
      addSlot.accept(this.outputSlot);
      this.getLastSelectedRecipeId = upgradeContainer.getUpgradeWrapper()::getRecipeId;
      this.setLastSelectedRecipeId = upgradeContainer.getUpgradeWrapper()::setRecipeId;
      this.onCraftMatrixChanged(this.inputInventory);
   }

   private void onCraftMatrixChanged(class_1263 inventory) {
      class_1799 itemstack = this.inputSlot.method_7677();
      if (itemstack.method_7909() != this.inputItem) {
         this.inputItem = itemstack.method_7909();
         this.updateAvailableRecipes(inventory, itemstack);
      }

      this.inventoryUpdateListener.run();
   }

   private void updateAvailableRecipes(class_1263 inventory, class_1799 stack) {
      this.recipes.clear();
      this.selectedRecipe.method_17404(-1);
      this.outputSlot.method_7673(class_1799.field_8037);
      if (!stack.method_7960()) {
         this.recipes = RecipeHelper.getRecipesOfType(class_3956.field_17641, new class_9696(inventory.method_5438(0)));
         this.getLastSelectedRecipeId.get().ifPresent(id -> {
            for (int i = 0; i < this.recipes.size(); i++) {
               if (this.recipes.get(i).comp_1932().equals(id)) {
                  this.selectedRecipe.method_17404(i);
                  this.updateRecipeResultSlot();
               }
            }
         });
      }
   }

   public class_1735 getInputSlot() {
      return this.inputSlot;
   }

   public class_1735 getOutputSlot() {
      return this.outputSlot;
   }

   public void setInventoryUpdateListener(Runnable listenerIn) {
      this.inventoryUpdateListener = listenerIn;
   }

   public List<class_8786<class_3975>> getRecipeList() {
      return this.recipes;
   }

   public int getSelectedRecipe() {
      return this.selectedRecipe.method_17407();
   }

   public boolean hasItemsInInputSlot() {
      return this.inputSlot.method_7681() && !this.recipes.isEmpty();
   }

   public boolean selectRecipe(int recipeIndex) {
      if (this.isIndexInRecipeBounds(recipeIndex)) {
         this.selectedRecipe.method_17404(recipeIndex);
         this.setLastSelectedRecipeId.accept(this.recipes.get(recipeIndex).comp_1932());
         this.updateRecipeResultSlot();
         this.serverUpdater.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "selectedRecipeIndex", recipeIndex));
      }

      return true;
   }

   private boolean isIndexInRecipeBounds(int index) {
      return index >= 0 && index < this.recipes.size();
   }

   private void updateRecipeResultSlot() {
      if (!this.recipes.isEmpty() && this.isIndexInRecipeBounds(this.selectedRecipe.method_17407())) {
         class_8786<class_3975> stonecuttingrecipe = this.recipes.get(this.selectedRecipe.method_17407());
         this.resultInventory.method_7662(stonecuttingrecipe);
         this.outputSlot
            .method_7673(
               ((class_3975)stonecuttingrecipe.comp_1933()).method_59998(new class_9696(this.inputInventory.method_5438(0)), this.level.method_30349())
            );
      } else {
         this.outputSlot.method_7673(class_1799.field_8037);
      }
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("selectedRecipeIndex")) {
         this.selectRecipe(data.method_10550("selectedRecipeIndex"));
      }
   }

   public boolean isNotResultSlot(class_1735 slot) {
      return slot != this.outputSlot;
   }

   private class ResultSlot extends class_1735 {
      private final class_3914 worldPosCallable;

      public ResultSlot(class_3914 worldPosCallable) {
         super(StonecutterRecipeContainer.this.resultInventory, 1, -1, -1);
         this.worldPosCallable = worldPosCallable;
      }

      public boolean method_7680(class_1799 stack) {
         return false;
      }

      public void method_7667(class_1657 player, class_1799 stack) {
         stack.method_7982(player.method_37908(), player, stack.method_7947());
         StonecutterRecipeContainer.this.resultInventory.method_7664(player, List.of(StonecutterRecipeContainer.this.inputSlot.method_7677()));
         class_1799 itemstack = StonecutterRecipeContainer.this.inputSlot.method_7671(1);
         if (!itemstack.method_7960()) {
            StonecutterRecipeContainer.this.updateRecipeResultSlot();
         }

         this.worldPosCallable.method_17393((world, pos) -> {
            long l = world.method_8510();
            if (StonecutterRecipeContainer.this.lastOnTake != l) {
               world.method_8396(null, pos, class_3417.field_17710, class_3419.field_15245, 1.0F, 1.0F);
               StonecutterRecipeContainer.this.lastOnTake = l;
            }
         });
         super.method_7667(player, stack);
      }
   }
}
