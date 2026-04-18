package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import java.util.function.Consumer;
import javax.annotation.Nullable;
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
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;

public abstract class CookingUpgradeWrapper<W extends CookingUpgradeWrapper<W, U, R>, U extends UpgradeItemBase<W> & ICookingUpgradeItem, R extends class_1874>
   extends UpgradeWrapperBase<W, U>
   implements ITickableUpgrade,
   ICookingUpgrade<R> {
   private static final int NOTHING_TO_DO_COOLDOWN = 10;
   protected final CookingLogic<R> cookingLogic;

   protected CookingUpgradeWrapper(
      IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler, class_3956<R> recipeType, float burnTimeModifier
   ) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.cookingLogic = new CookingLogic<>(upgrade, upgradeSaveHandler, this.upgradeItem.getCookingUpgradeConfig(), recipeType, burnTimeModifier);
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.isInCooldown(level)) {
         if (!this.cookingLogic.tick(level)) {
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

   @Override
   public void setEnabled(boolean enabled) {
      if (!enabled) {
         this.pauseAndRemoveRenderInfo();
      }

      super.setEnabled(enabled);
   }

   @Override
   public void onBeforeRemoved() {
      this.pauseAndRemoveRenderInfo();
   }

   private void pauseAndRemoveRenderInfo() {
      this.cookingLogic.pause();
      RenderInfo renderInfo = this.storageWrapper.getRenderInfo();
      renderInfo.removeUpgradeRenderData(CookingUpgradeRenderData.TYPE);
   }

   @Override
   public CookingLogic<R> getCookingLogic() {
      return this.cookingLogic;
   }

   public static class BlastingUpgradeWrapper extends CookingUpgradeWrapper<CookingUpgradeWrapper.BlastingUpgradeWrapper, BlastingUpgradeItem, class_3859> {
      public BlastingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17547, 0.5F);
      }
   }

   public static class SmeltingUpgradeWrapper extends CookingUpgradeWrapper<CookingUpgradeWrapper.SmeltingUpgradeWrapper, SmeltingUpgradeItem, class_3861> {
      public SmeltingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17546, 1.0F);
      }
   }

   public static class SmokingUpgradeWrapper extends CookingUpgradeWrapper<CookingUpgradeWrapper.SmokingUpgradeWrapper, SmokingUpgradeItem, class_3862> {
      public SmokingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
         super(storageWrapper, upgrade, upgradeSaveHandler, class_3956.field_17548, 0.5F);
      }
   }
}
