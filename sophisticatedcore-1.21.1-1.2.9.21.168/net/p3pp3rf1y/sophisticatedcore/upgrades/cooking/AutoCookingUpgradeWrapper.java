package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1874;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_3859;
import net.minecraft.class_3861;
import net.minecraft.class_3862;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;

public class AutoCookingUpgradeWrapper<W extends AutoCookingUpgradeWrapper<W, U, R>, U extends UpgradeItemBase<W> & IAutoCookingUpgradeItem, R extends class_1874>
   extends UpgradeWrapperBase<W, U>
   implements ITickableUpgrade,
   ICookingUpgrade<R> {
   private static final int NOTHING_TO_DO_COOLDOWN = 10;
   private static final int NO_INVENTORY_SPACE_COOLDOWN = 60;
   private final FilterLogic inputFilterLogic;
   private final FilterLogic fuelFilterLogic;
   private final CookingLogic<R> cookingLogic;
   private final Predicate<class_1799> isValidInput;
   private final Predicate<class_1799> isValidFuel;
   private final class_3956<R> recipeType;
   private int outputCooldown = 0;
   private int fuelCooldown = 0;
   private int inputCooldown = 0;

   public AutoCookingUpgradeWrapper(
      IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler, class_3956<R> recipeType, float burnTimeModifier
   ) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.recipeType = recipeType;
      AutoCookingUpgradeConfig autoCookingUpgradeConfig = this.upgradeItem.getAutoCookingUpgradeConfig();
      this.inputFilterLogic = new FilterLogic(
         upgrade,
         upgradeSaveHandler,
         (Integer)autoCookingUpgradeConfig.inputFilterSlots.get(),
         s -> RecipeHelper.getCookingRecipe(s, recipeType).isPresent(),
         ModCoreDataComponents.INPUT_FILTER_ATTRIBUTES
      );
      this.fuelFilterLogic = new FilterLogic(
         upgrade,
         upgradeSaveHandler,
         (Integer)autoCookingUpgradeConfig.fuelFilterSlots.get(),
         s -> s.getBurnTime(recipeType) > 0,
         ModCoreDataComponents.FUEL_FILTER_ATTRIBUTES
      );
      this.fuelFilterLogic.setAllowByDefault(true);
      this.fuelFilterLogic.setEmptyAllowListMatchesEverything();
      this.isValidInput = s -> RecipeHelper.getCookingRecipe(s, recipeType).isPresent() && this.inputFilterLogic.matchesFilter(s);
      this.isValidFuel = s -> s.getBurnTime(recipeType) > 0 && this.fuelFilterLogic.matchesFilter(s);
      this.cookingLogic = new CookingLogic<>(
         upgrade, upgradeSaveHandler, this.isValidFuel, this.isValidInput, autoCookingUpgradeConfig, recipeType, burnTimeModifier
      );
   }

   @Override
   public void setEnabled(boolean enabled) {
      if (!enabled) {
         this.pauseAndRemoveRenderInfo();
      }

      super.setEnabled(enabled);
   }

   private void pauseAndRemoveRenderInfo() {
      this.cookingLogic.pause();
      RenderInfo renderInfo = this.storageWrapper.getRenderInfo();
      renderInfo.removeUpgradeRenderData(CookingUpgradeRenderData.TYPE);
   }

   @Override
   public void onBeforeRemoved() {
      this.pauseAndRemoveRenderInfo();
   }

   private void tryPushingOutput() {
      if (this.outputCooldown > 0) {
         this.outputCooldown--;
      } else {
         class_1799 output = this.cookingLogic.getCookOutput();
         IItemHandlerSimpleInserter inventory = this.storageWrapper.getInventoryForUpgradeProcessing();
         if (!output.method_7960() && inventory.insertItem(output, true).method_7947() < output.method_7947()) {
            class_1799 ret = inventory.insertItem(output, false);
            this.cookingLogic.getCookingInventory().extractItem(2, output.method_7947() - ret.method_7947(), false);
         } else {
            this.outputCooldown = 60;
         }

         class_1799 fuel = this.cookingLogic.getFuel();
         if (!fuel.method_7960() && fuel.getBurnTime(this.recipeType) <= 0 && inventory.insertItem(fuel, true).method_7947() < fuel.method_7947()) {
            class_1799 ret = inventory.insertItem(fuel, false);
            this.cookingLogic.getCookingInventory().extractItem(1, fuel.method_7947() - ret.method_7947(), false);
         }
      }
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.isInCooldown(level)) {
         this.tryPushingOutput();
         this.tryPullingFuel();
         this.tryPullingInput();
         if (!this.cookingLogic.tick(level) && this.outputCooldown <= 0 && this.fuelCooldown <= 0 && this.inputCooldown <= 0) {
            this.setCooldown(level, 10);
         }

         boolean isBurning = this.cookingLogic.isBurning(level);
         RenderInfo renderInfo = this.storageWrapper.getRenderInfo();
         if (renderInfo.getUpgradeRenderData(CookingUpgradeRenderData.TYPE).map(CookingUpgradeRenderData::isBurning).orElse(false) != isBurning) {
            if (isBurning) {
               renderInfo.setUpgradeRenderData(CookingUpgradeRenderData.TYPE, new CookingUpgradeRenderData(true));
            } else {
               renderInfo.removeUpgradeRenderData(CookingUpgradeRenderData.TYPE);
            }
         }
      }
   }

   private void tryPullingInput() {
      if (this.inputCooldown > 0) {
         this.inputCooldown--;
      } else {
         if (this.tryPullingGetUnsucessful(this.cookingLogic.getCookInput(), this.cookingLogic::setCookInput, this.isValidInput)) {
            this.inputCooldown = 60;
         }
      }
   }

   private void tryPullingFuel() {
      if (this.fuelCooldown > 0) {
         this.fuelCooldown--;
      } else {
         if (this.tryPullingGetUnsucessful(this.cookingLogic.getFuel(), this.cookingLogic::setFuel, this.isValidFuel)) {
            this.fuelCooldown = 60;
         }
      }
   }

   private boolean tryPullingGetUnsucessful(class_1799 stack, Consumer<class_1799> setSlot, Predicate<class_1799> isItemValid) {
      IItemHandlerSimpleInserter inventory = this.storageWrapper.getInventoryForUpgradeProcessing();
      class_1799 toExtract;
      if (stack.method_7960()) {
         AtomicReference<class_1799> ret = new AtomicReference<>(class_1799.field_8037);
         InventoryHelper.iterate(inventory, (slot, st) -> {
            if (isItemValid.test(st)) {
               ret.set(st.method_7972());
            }
         }, () -> !ret.get().method_7960());
         if (ret.get().method_7960()) {
            return true;
         }

         toExtract = ret.get();
         toExtract.method_7939(toExtract.method_7914());
      } else {
         if (stack.method_7947() == stack.method_7914() || !isItemValid.test(stack)) {
            return true;
         }

         toExtract = stack.method_7972();
         toExtract.method_7939(stack.method_7914() - stack.method_7947());
      }

      Transaction ctx = Transaction.openOuter();

      label62: {
         boolean toSet;
         try {
            long extracted = inventory.extract(ItemVariant.of(toExtract), toExtract.method_7947(), ctx);
            if (extracted > 0L) {
               class_1799 toSetx = toExtract.method_46651((int)extracted);
               toSetx.method_7933(stack.method_7947());
               setSlot.accept(toSetx);
               ctx.commit();
               break label62;
            }

            toSet = true;
         } catch (Throwable var11) {
            if (ctx != null) {
               try {
                  ctx.close();
               } catch (Throwable var10) {
                  var11.addSuppressed(var10);
               }
            }

            throw var11;
         }

         if (ctx != null) {
            ctx.close();
         }

         return toSet;
      }

      if (ctx != null) {
         ctx.close();
      }

      return false;
   }

   @Override
   public CookingLogic<R> getCookingLogic() {
      return this.cookingLogic;
   }

   public FilterLogic getInputFilterLogic() {
      return this.inputFilterLogic;
   }

   public FilterLogic getFuelFilterLogic() {
      return this.fuelFilterLogic;
   }

   public static class AutoBlastingUpgradeWrapper
      extends AutoCookingUpgradeWrapper<AutoCookingUpgradeWrapper.AutoBlastingUpgradeWrapper, AutoBlastingUpgradeItem, class_3859> {
      public AutoBlastingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17547, 0.5F);
      }
   }

   public static class AutoSmeltingUpgradeWrapper
      extends AutoCookingUpgradeWrapper<AutoCookingUpgradeWrapper.AutoSmeltingUpgradeWrapper, AutoSmeltingUpgradeItem, class_3861> {
      public AutoSmeltingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17546, 1.0F);
      }
   }

   public static class AutoSmokingUpgradeWrapper
      extends AutoCookingUpgradeWrapper<AutoCookingUpgradeWrapper.AutoSmokingUpgradeWrapper, AutoSmokingUpgradeItem, class_3862> {
      public AutoSmokingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17548, 0.5F);
      }
   }
}
