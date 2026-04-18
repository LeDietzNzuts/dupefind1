package net.p3pp3rf1y.sophisticatedcore.upgrades.xppump;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_3611;
import net.minecraft.class_9701;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageFluidHandler;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.XpHelper;

public class XpPumpUpgradeWrapper extends UpgradeWrapperBase<XpPumpUpgradeWrapper, XpPumpUpgradeItem> implements ITickableUpgrade {
   private static final int DEFAULT_LEVEL = 10;
   private static final int COOLDOWN = 5;
   private static final int ALL_LEVELS = 10000;
   private static final int PLAYER_SEARCH_RANGE = 3;
   private final XpPumpUpgradeConfig xpPumpUpgradeConfig = this.upgradeItem.getXpPumpUpgradeConfig();

   protected XpPumpUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if ((entity == null || entity instanceof class_1657) && !this.isInCooldown(level)) {
         if (entity == null) {
            class_238 searchBox = new class_238(pos).method_1014(3.0);

            for (class_1657 player : level.method_18456()) {
               if (searchBox.method_1008(player.method_23317(), player.method_23318(), player.method_23321())) {
                  this.interactWithPlayer(player);
                  this.mendItems(player);
               }
            }
         } else {
            class_1657 playerx = entity instanceof class_1657 ? (class_1657)entity : null;
            this.interactWithPlayer(playerx);
            this.mendItems(playerx);
         }

         this.setCooldown(level, 5);
      }
   }

   private void mendItems(class_1657 player) {
      if (!Boolean.FALSE.equals(this.xpPumpUpgradeConfig.mendingOn.get()) && this.shouldMendItems()) {
         class_1890.method_8204(class_9701.field_51652, player, class_1799::method_7986)
            .ifPresent(
               item -> {
                  class_1799 itemStack = item.comp_2682();
                  if (!itemStack.method_7960() && itemStack.method_7986() && itemStack.getXpRepairRatio() > 0.0F) {
                     float xpToTryDrain = Math.min(
                        (float)((Integer)this.xpPumpUpgradeConfig.maxXpPointsPerMending.get()).intValue(),
                        itemStack.method_7919() / itemStack.getXpRepairRatio()
                     );
                     if (xpToTryDrain > 0.0F) {
                        this.storageWrapper.getFluidHandler().ifPresent(fluidHandler -> {
                           Transaction outer = Transaction.openOuter();

                           FluidStack drained;
                           try {
                              drained = fluidHandler.extract(ModFluids.EXPERIENCE_TAG, XpHelper.experienceToLiquid(xpToTryDrain), outer, false);
                              outer.commit();
                           } catch (Throwable var8) {
                              if (outer != null) {
                                 try {
                                    outer.close();
                                 } catch (Throwable var7) {
                                    var8.addSuppressed(var7);
                                 }
                              }

                              throw var8;
                           }

                           if (outer != null) {
                              outer.close();
                           }

                           float xpDrained = XpHelper.liquidToExperience((int)drained.getAmount());
                           int durationToRepair = (int)(xpDrained * itemStack.getXpRepairRatio());
                           itemStack.method_7974(itemStack.method_7919() - durationToRepair);
                        });
                     }
                  }
               }
            );
      }
   }

   private void interactWithPlayer(class_1657 player) {
      this.storageWrapper
         .getFluidHandler()
         .ifPresent(
            fluidHandler -> {
               int level = this.getLevel();
               AutomationDirection direction = this.getDirection();
               if (direction != AutomationDirection.OFF) {
                  if (direction != AutomationDirection.INPUT && direction != AutomationDirection.KEEP
                     || level >= player.field_7520 && (level != player.field_7520 || !(player.field_7510 > 0.0F))) {
                     if ((direction == AutomationDirection.OUTPUT || direction == AutomationDirection.KEEP) && level > player.field_7520) {
                        this.tryGivePlayerExperienceFromTank(player, fluidHandler, level, false);
                     }
                  } else {
                     this.tryFillTankWithPlayerExperience(player, fluidHandler, level, false);
                  }
               }
            }
         );
   }

   private void tryGivePlayerExperienceFromTank(class_1657 player, IStorageFluidHandler fluidHandler, int stopAtLevel) {
      this.tryGivePlayerExperienceFromTank(player, fluidHandler, stopAtLevel, true);
   }

   private void tryGivePlayerExperienceFromTank(class_1657 player, IStorageFluidHandler fluidHandler, int stopAtLevel, boolean ignoreInOutLimit) {
      int maxXpPointsToGive = XpHelper.getExperienceForLevel(stopAtLevel) - XpHelper.getPlayerTotalExperience(player);
      Transaction outer = Transaction.openOuter();

      FluidStack drained;
      try {
         drained = fluidHandler.extract(ModFluids.EXPERIENCE_TAG, XpHelper.experienceToLiquid(maxXpPointsToGive), outer, ignoreInOutLimit);
         outer.commit();
      } catch (Throwable var11) {
         if (outer != null) {
            try {
               outer.close();
            } catch (Throwable var10) {
               var11.addSuppressed(var10);
            }
         }

         throw var11;
      }

      if (outer != null) {
         outer.close();
      }

      if (!drained.isEmpty()) {
         player.method_7255((int)XpHelper.liquidToExperience(drained.getAmount()));
      }
   }

   private void tryFillTankWithPlayerExperience(class_1657 player, IStorageFluidHandler fluidHandler, int stopAtLevel) {
      this.tryFillTankWithPlayerExperience(player, fluidHandler, stopAtLevel, true);
   }

   private void tryFillTankWithPlayerExperience(class_1657 player, IStorageFluidHandler fluidHandler, int stopAtLevel, boolean ignoreInOutLimit) {
      int maxXpPointsToTake = XpHelper.getPlayerTotalExperience(player) - XpHelper.getExperienceForLevel(stopAtLevel);
      Transaction outer = Transaction.openOuter();

      long filled;
      try {
         filled = fluidHandler.insert(
            ModFluids.EXPERIENCE_TAG, XpHelper.experienceToLiquid(maxXpPointsToTake), (class_3611)ModFluids.XP_STILL.get(), outer, ignoreInOutLimit
         );
         outer.commit();
      } catch (Throwable var12) {
         if (outer != null) {
            try {
               outer.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (outer != null) {
         outer.close();
      }

      if (filled > 0L) {
         player.method_7255((int)(-XpHelper.liquidToExperience(filled)));
      }
   }

   public void takeLevelsFromPlayer(class_1657 player) {
      this.storageWrapper
         .getFluidHandler()
         .ifPresent(fluidHandler -> this.tryFillTankWithPlayerExperience(player, fluidHandler, Math.max(player.field_7520 - this.getLevelsToStore(), 0)));
   }

   public void takeAllExperienceFromPlayer(class_1657 player) {
      this.storageWrapper.getFluidHandler().ifPresent(fluidHandler -> this.tryFillTankWithPlayerExperience(player, fluidHandler, 0));
   }

   public void giveLevelsToPlayer(class_1657 player) {
      this.storageWrapper
         .getFluidHandler()
         .ifPresent(fluidHandler -> this.tryGivePlayerExperienceFromTank(player, fluidHandler, player.field_7520 + this.getLevelsToTake()));
   }

   public void giveAllExperienceToPlayer(class_1657 player) {
      this.storageWrapper.getFluidHandler().ifPresent(fluidHandler -> this.tryGivePlayerExperienceFromTank(player, fluidHandler, 10000));
   }

   public AutomationDirection getDirection() {
      return (AutomationDirection)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.AUTOMATION_DIRECTION, AutomationDirection.INPUT);
   }

   public void setDirection(AutomationDirection direction) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.AUTOMATION_DIRECTION, direction);
      this.save();
   }

   public void setLevel(int level) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.LEVEL, level);
      this.save();
   }

   public int getLevel() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.LEVEL, 10);
   }

   public void setLevelsToStore(int levelsToStore) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.LEVELS_TO_STORE, levelsToStore);
      this.save();
   }

   public int getLevelsToStore() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.LEVELS_TO_STORE, 1);
   }

   public void setLevelsToTake(int levelsToTake) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.LEVELS_TO_TAKE, levelsToTake);
      this.save();
   }

   public int getLevelsToTake() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.LEVELS_TO_TAKE, 1);
   }

   public boolean shouldMendItems() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.MEND_ITEMS, true);
   }

   public void setMendItems(boolean mendItems) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.MEND_ITEMS, mendItems);
      this.save();
   }
}
