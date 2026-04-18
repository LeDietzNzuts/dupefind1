package net.p3pp3rf1y.sophisticatedcore.compat.chipped;

import com.google.common.base.Suppliers;
import earth.terrarium.chipped.common.recipes.ChippedRecipe;
import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1731;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2487;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3914;
import net.minecraft.class_3915;
import net.minecraft.class_3956;
import net.minecraft.class_8786;
import net.minecraft.class_9696;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotSuppliedHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.CraftingItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;
import net.p3pp3rf1y.sophisticatedcore.util.SimpleItemContent;

public class BlockTransformationRecipeContainer {
   private static final String DATA_SELECTED_RECIPE_INDEX = "selectedRecipeIndex";
   private final class_3956<ChippedRecipe> recipeType;
   private final class_1735 inputSlot;
   private final IServerUpdater serverUpdater;
   private final class_1735 outputSlot;
   private final class_1731 resultInventory = new class_1731();
   @Nullable
   private class_8786<ChippedRecipe> recipe = null;
   private Supplier<List<class_1799>> results = Collections::emptyList;
   private final class_3915 selectedRecipe = class_3915.method_17403();
   private class_1792 inputItem = class_1802.field_8162;
   private final CraftingItemHandler inputInventory;
   private Runnable inventoryUpdateListener = () -> {};
   private final Supplier<Optional<SimpleItemContent>> getLastSelectedResult;
   private final Consumer<class_1799> setLastSelectedResult;
   private long lastOnTake = -1L;

   public BlockTransformationRecipeContainer(
      BlockTransformationUpgradeContainer upgradeContainer,
      class_3956<ChippedRecipe> recipeType,
      Consumer<class_1735> addSlot,
      IServerUpdater serverUpdater,
      class_3914 worldPosCallable
   ) {
      this.inputSlot = new SlotSuppliedHandler(upgradeContainer.getUpgradeWrapper()::getInputInventory, 0, -1, -1) {
         public void method_7668() {
            super.method_7668();
            BlockTransformationRecipeContainer.this.onCraftMatrixChanged(BlockTransformationRecipeContainer.this.inputInventory);
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
      this.recipeType = recipeType;
      this.serverUpdater = serverUpdater;
      addSlot.accept(this.inputSlot);
      this.inputInventory = new CraftingItemHandler(upgradeContainer.getUpgradeWrapper()::getInputInventory, this::onCraftMatrixChanged);
      this.outputSlot = new BlockTransformationRecipeContainer.ResultSlot(worldPosCallable);
      addSlot.accept(this.outputSlot);
      this.getLastSelectedResult = upgradeContainer.getUpgradeWrapper()::getResult;
      this.setLastSelectedResult = upgradeContainer.getUpgradeWrapper()::setResult;
      this.onCraftMatrixChanged(this.inputInventory);
   }

   private void onCraftMatrixChanged(class_1263 inventoryIn) {
      class_1799 itemstack = this.inputSlot.method_7677();
      if (itemstack.method_7909() != this.inputItem) {
         this.inputItem = itemstack.method_7909();
         this.updateRecipe(inventoryIn, itemstack);
      }

      this.inventoryUpdateListener.run();
   }

   private void updateRecipe(class_1263 inventory, class_1799 stack) {
      this.recipe = null;
      this.selectedRecipe.method_17404(-1);
      this.outputSlot.method_7673(class_1799.field_8037);
      if (!stack.method_7960()) {
         class_1799 inputStack = inventory.method_5438(0);
         RecipeHelper.getRecipesOfType(this.recipeType, new class_9696(inputStack)).stream().findFirst().ifPresent(r -> {
            this.recipe = (class_8786<ChippedRecipe>)r;
            this.results = Suppliers.memoize(() -> ((ChippedRecipe)this.recipe.comp_1933()).getResults(inputStack).toList());
            this.getLastSelectedResult.get().ifPresent(lastSelectedResult -> {
               int i = 0;

               for (class_1799 result : this.results.get()) {
                  if (lastSelectedResult.isSameItemSameComponents(result)) {
                     this.selectedRecipe.method_17404(i);
                     this.updateRecipeResultSlot();
                     return;
                  }

                  i++;
               }
            });
         });
      } else {
         this.results = Collections::emptyList;
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

   public List<class_1799> getResults() {
      return this.results.get();
   }

   public int getSelectedRecipe() {
      return this.selectedRecipe.method_17407();
   }

   public boolean hasItemsInInputSlot() {
      return this.inputSlot.method_7681() && this.recipe != null;
   }

   public boolean selectRecipeIndex(int recipeIndex) {
      if (this.recipe != null && this.isIndexInRecipeBounds(recipeIndex)) {
         this.selectedRecipe.method_17404(recipeIndex);
         this.setLastSelectedResult.accept(this.results.get().get(recipeIndex));
         this.updateRecipeResultSlot();
         this.serverUpdater.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "selectedRecipeIndex", recipeIndex));
      }

      return true;
   }

   private boolean isIndexInRecipeBounds(int index) {
      return this.recipe != null && index >= 0 && index < ((ChippedRecipe)this.recipe.comp_1933()).getResults(this.inputInventory.method_5438(0)).count();
   }

   private void updateRecipeResultSlot() {
      if (this.recipe != null && this.isIndexInRecipeBounds(this.selectedRecipe.method_17407())) {
         ((ChippedRecipe)this.recipe.comp_1933())
            .getResults(this.inputInventory.method_5438(0))
            .skip(this.selectedRecipe.method_17407())
            .findFirst()
            .ifPresent(stack -> this.outputSlot.method_7673(stack.method_7972()));
         this.resultInventory.method_7662(this.recipe);
      } else {
         this.outputSlot.method_7673(class_1799.field_8037);
      }
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("selectedRecipeIndex")) {
         this.selectRecipeIndex(data.method_10550("selectedRecipeIndex"));
      }
   }

   public boolean isNotResultSlot(class_1735 slot) {
      return slot != this.outputSlot;
   }

   private class ResultSlot extends class_1735 {
      private final class_3914 worldPosCallable;

      public ResultSlot(class_3914 worldPosCallable) {
         super(BlockTransformationRecipeContainer.this.resultInventory, 1, -1, -1);
         this.worldPosCallable = worldPosCallable;
      }

      public boolean method_7680(class_1799 stack) {
         return false;
      }

      public void method_7667(class_1657 thePlayer, class_1799 stack) {
         stack.method_7982(thePlayer.method_37908(), thePlayer, stack.method_7947());
         BlockTransformationRecipeContainer.this.resultInventory
            .method_7664(thePlayer, List.of(BlockTransformationRecipeContainer.this.inputSlot.method_7677()));
         class_1799 itemstack = BlockTransformationRecipeContainer.this.inputSlot.method_7671(1);
         if (!itemstack.method_7960()) {
            BlockTransformationRecipeContainer.this.updateRecipeResultSlot();
         }

         this.worldPosCallable.method_17393((world, pos) -> {
            long l = world.method_8510();
            if (BlockTransformationRecipeContainer.this.lastOnTake != l) {
               world.method_8396(null, pos, class_3417.field_17710, class_3419.field_15245, 1.0F, 1.0F);
               BlockTransformationRecipeContainer.this.lastOnTake = l;
            }
         });
         super.method_7667(thePlayer, stack);
      }
   }
}
