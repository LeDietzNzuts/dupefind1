package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1860;
import net.minecraft.class_1874;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_3956;
import net.minecraft.class_8786;
import net.minecraft.class_9288;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class CookingLogic<T extends class_1874> {
   private final class_1799 upgrade;
   private final Consumer<class_1799> saveHandler;
   private CookingLogic<T>.CookingComponentItemHandler cookingInventory = null;
   public static final int COOK_INPUT_SLOT = 0;
   public static final int COOK_OUTPUT_SLOT = 2;
   public static final int FUEL_SLOT = 1;
   @Nullable
   private class_8786<T> cookingRecipe = null;
   private boolean cookingRecipeInitialized = false;
   private final float burnTimeModifier;
   private final Predicate<class_1799> isFuel;
   private final Predicate<class_1799> isInput;
   private final double cookingSpeedMultiplier;
   private final double fuelEfficiencyMultiplier;
   private final class_3956<T> recipeType;
   private boolean paused = false;
   private long remainingCookTime = 0L;
   private long remainingBurnTime = 0L;

   public CookingLogic(
      class_1799 upgrade, Consumer<class_1799> saveHandler, CookingUpgradeConfig cookingUpgradeConfig, class_3956<T> recipeType, float burnTimeModifier
   ) {
      this(
         upgrade,
         saveHandler,
         s -> getBurnTime(s, burnTimeModifier) > 0,
         s -> RecipeHelper.getCookingRecipe(s, recipeType).isPresent(),
         cookingUpgradeConfig,
         recipeType,
         burnTimeModifier
      );
   }

   public CookingLogic(
      class_1799 upgrade,
      Consumer<class_1799> saveHandler,
      Predicate<class_1799> isFuel,
      Predicate<class_1799> isInput,
      CookingUpgradeConfig cookingUpgradeConfig,
      class_3956<T> recipeType,
      float burnTimeModifier
   ) {
      this.upgrade = upgrade;
      this.saveHandler = saveHandler;
      this.isFuel = isFuel;
      this.isInput = isInput;
      this.cookingSpeedMultiplier = (Double)cookingUpgradeConfig.cookingSpeedMultiplier.get();
      this.fuelEfficiencyMultiplier = (Double)cookingUpgradeConfig.fuelEfficiencyMultiplier.get();
      this.recipeType = recipeType;
      this.burnTimeModifier = burnTimeModifier;
   }

   private void save() {
      this.saveHandler.accept(this.upgrade);
   }

   public boolean tick(class_1937 level) {
      this.updateTimes(level);
      AtomicBoolean didSomething = new AtomicBoolean(true);
      if (this.isBurning(level) || this.readyToStartCooking()) {
         Optional<T> fr = this.getCookingRecipe();
         if (fr.isEmpty() && this.isCooking()) {
            this.setIsCooking(false);
         }

         fr.ifPresent(recipe -> {
            this.updateFuel(level, (T)recipe);
            if (this.isBurning(level) && this.canSmelt(recipe, level)) {
               this.updateCookingProgress(level, (T)recipe);
            } else if (!this.isBurning(level)) {
               didSomething.set(false);
            }
         });
      }

      if (!this.isBurning(level) && this.isCooking()) {
         this.updateCookingCooldown(level);
      } else {
         didSomething.set(false);
      }

      return didSomething.get();
   }

   private void updateTimes(class_1937 level) {
      if (this.paused) {
         this.unpause(level);
      } else {
         if (this.isBurning(level)) {
            this.remainingBurnTime = this.getBurnTimeFinish() - level.method_8510();
         } else {
            this.remainingBurnTime = 0L;
         }

         if (this.isCooking()) {
            this.remainingCookTime = this.getCookTimeFinish() - level.method_8510();
         } else {
            this.remainingCookTime = 0L;
         }
      }
   }

   private void unpause(class_1937 level) {
      this.paused = false;
      if (this.remainingBurnTime > 0L) {
         this.setBurnTimeFinish(level.method_8510() + this.remainingBurnTime);
      }

      if (this.remainingCookTime > 0L) {
         this.setCookTimeFinish(level.method_8510() + this.remainingCookTime);
         this.setIsCooking(true);
      }
   }

   public boolean isBurning(class_1937 level) {
      return this.getBurnTimeFinish() >= level.method_8510();
   }

   private Optional<T> getCookingRecipe() {
      if (!this.cookingRecipeInitialized) {
         this.cookingRecipe = RecipeHelper.<T>getCookingRecipe(this.getCookInput(), this.recipeType).orElse(null);
         this.cookingRecipeInitialized = true;
      }

      return this.cookingRecipe != null ? Optional.of((T)this.cookingRecipe.comp_1933()) : Optional.empty();
   }

   private void updateCookingCooldown(class_1937 level) {
      if (this.getRemainingCookTime(level) + 2 > this.getCookTimeTotal()) {
         this.setIsCooking(false);
      } else {
         this.setCookTimeFinish(level.method_8510() + Math.min(this.getRemainingCookTime(level) + 2, this.getCookTimeTotal()));
      }
   }

   private void updateCookingProgress(class_1937 level, T cookingRecipe) {
      if (this.isCooking() && this.finishedCooking(level)) {
         this.smelt(cookingRecipe, level);
         if (this.canSmelt(cookingRecipe, level)) {
            this.setCookTime(level, (int)(cookingRecipe.method_8167() * (1.0 / this.cookingSpeedMultiplier)));
         } else {
            this.setIsCooking(false);
         }
      } else if (!this.isCooking()) {
         this.setIsCooking(true);
         this.setCookTime(level, (int)(cookingRecipe.method_8167() * (1.0 / this.cookingSpeedMultiplier)));
      }
   }

   private boolean finishedCooking(class_1937 level) {
      return this.getCookTimeFinish() <= level.method_8510();
   }

   private boolean readyToStartCooking() {
      return !this.getFuel().method_7960() && !this.getCookInput().method_7960();
   }

   private void smelt(class_1860<?> recipe, class_1937 level) {
      if (this.canSmelt(recipe, level)) {
         class_1799 input = this.getCookInput();
         class_1799 recipeOutput = recipe.method_8110(level.method_30349());
         class_1799 output = this.getCookOutput();
         if (output.method_7960()) {
            this.setCookOutput(recipeOutput.method_7972());
         } else if (output.method_7909() == recipeOutput.method_7909()) {
            output.method_7933(recipeOutput.method_7947());
            this.setCookOutput(output);
         }

         if (input.method_7909() == class_2246.field_10562.method_8389()
            && !this.getFuel().method_7960()
            && this.getFuel().method_7909() == class_1802.field_8550) {
            this.setFuel(new class_1799(class_1802.field_8705));
         }

         input.method_7934(1);
         this.setCookInput(input);
      }
   }

   public void setCookInput(class_1799 input) {
      this.cookingInventory.setStackInSlot(0, input);
   }

   private void setCookOutput(class_1799 stack) {
      this.getCookingInventory().setStackInSlot(2, stack);
   }

   private int getRemainingCookTime(class_1937 level) {
      return (int)(this.getCookTimeFinish() - level.method_8510());
   }

   private void setCookTime(class_1937 level, int cookTime) {
      this.setCookTimeFinish(level.method_8510() + cookTime);
      this.setCookTimeTotal(cookTime);
   }

   public void pause() {
      this.paused = true;
      this.setCookTimeFinish(0L);
      this.setIsCooking(false);
      this.setBurnTimeFinish(0L);
   }

   private void updateFuel(class_1937 level, T cookingRecipe) {
      class_1799 fuel = this.getFuel();
      if (!this.isBurning(level) && this.canSmelt(cookingRecipe, level)) {
         if (getBurnTime(fuel, this.burnTimeModifier) <= 0) {
            return;
         }

         this.setBurnTime(level, (int)(getBurnTime(fuel, this.burnTimeModifier) * this.fuelEfficiencyMultiplier / this.cookingSpeedMultiplier));
         if (this.isBurning(level)) {
            if (fuel.method_7909().method_7857()) {
               this.setFuelWithoutValidation(fuel.getRecipeRemainder());
            } else if (!fuel.method_7960()) {
               fuel.method_7934(1);
               this.setFuel(fuel);
               if (fuel.method_7960()) {
                  this.setFuel(fuel.getRecipeRemainder());
               }
            }
         }
      }
   }

   private void setBurnTime(class_1937 level, int burnTime) {
      this.setBurnTimeFinish(level.method_8510() + burnTime);
      this.setBurnTimeTotal(burnTime);
   }

   protected boolean canSmelt(class_1860<?> cookingRecipe, class_1937 level) {
      if (this.getCookInput().method_7960()) {
         return false;
      } else {
         class_1799 recipeOutput = cookingRecipe.method_8110(level.method_30349());
         if (recipeOutput.method_7960()) {
            return false;
         } else {
            class_1799 output = this.getCookOutput();
            if (output.method_7960()) {
               return true;
            } else if (output.method_7909() != recipeOutput.method_7909()) {
               return false;
            } else {
               return output.method_7947() + recipeOutput.method_7947() <= 99 && output.method_7947() + recipeOutput.method_7947() <= output.method_7914()
                  ? true
                  : output.method_7947() + recipeOutput.method_7947() <= recipeOutput.method_7914();
            }
         }
      }
   }

   private static int getBurnTime(class_1799 fuel, float burnTimeModifier) {
      return (int)(((Integer)Objects.requireNonNullElse((Integer)FuelRegistry.INSTANCE.get(fuel.method_7909()), 0)).intValue() * burnTimeModifier);
   }

   public class_1799 getCookOutput() {
      return this.getCookingInventory().getStackInSlot(2);
   }

   public class_1799 getCookInput() {
      return this.getCookingInventory().getStackInSlot(0);
   }

   public class_1799 getFuel() {
      return this.getCookingInventory().getStackInSlot(1);
   }

   public void setFuel(class_1799 fuel) {
      this.getCookingInventory().setStackInSlot(1, fuel);
   }

   private void setFuelWithoutValidation(class_1799 fuel) {
      this.getCookingInventory().setStackInSlotWithoutValidation(1, fuel);
   }

   public CookingLogic<T>.CookingComponentItemHandler getCookingInventory() {
      if (this.cookingInventory == null) {
         this.cookingInventory = new CookingLogic.CookingComponentItemHandler();
      }

      return this.cookingInventory;
   }

   public long getBurnTimeFinish() {
      return (Long)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.BURN_TIME_FINISH, 0L);
   }

   private void setBurnTimeFinish(long burnTimeFinish) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.BURN_TIME_FINISH, burnTimeFinish);
      this.save();
   }

   public int getBurnTimeTotal() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.BURN_TIME_TOTAL, 0);
   }

   private void setBurnTimeTotal(int burnTimeTotal) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.BURN_TIME_TOTAL, burnTimeTotal);
      this.save();
   }

   public long getCookTimeFinish() {
      return (Long)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.COOK_TIME_FINISH, -1L);
   }

   private void setCookTimeFinish(long cookTimeFinish) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.COOK_TIME_FINISH, cookTimeFinish);
      this.save();
   }

   public int getCookTimeTotal() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.COOK_TIME_TOTAL, 0);
   }

   private void setCookTimeTotal(int cookTimeTotal) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.COOK_TIME_TOTAL, cookTimeTotal);
      this.save();
   }

   public boolean isCooking() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.IS_COOKING, false);
   }

   private void setIsCooking(boolean isCooking) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.IS_COOKING, isCooking);
      this.save();
   }

   public class CookingComponentItemHandler extends ComponentItemHandler {
      public CookingComponentItemHandler() {
         super(CookingLogic.this.upgrade, ModCoreDataComponents.COOKING_INVENTORY.get(), 3);
      }

      @Override
      protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
         super.onContentsChanged(slot, oldStack, newStack);
         CookingLogic.this.save();
         if (slot == 0) {
            CookingLogic.this.cookingRecipeInitialized = false;
         }
      }

      @Override
      public boolean isItemValid(int slot, class_1799 stack) {
         if (!stack.method_7960() && !class_1799.method_31577(this.getStackInSlot(slot), stack)) {
            return switch (slot) {
               case 0 -> CookingLogic.this.isInput.test(stack);
               case 1 -> CookingLogic.this.isFuel.test(stack);
               default -> true;
            };
         } else {
            return true;
         }
      }

      public void setStackInSlotWithoutValidation(int slot, class_1799 stack) {
         class_9288 contents = this.getContents();
         class_1799 existing = this.getStackFromContents(contents, slot);
         if (!class_1799.method_7973(stack, existing)) {
            this.updateContents(contents, stack, slot);
         }
      }
   }
}
