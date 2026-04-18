package net.p3pp3rf1y.sophisticatedcore.upgrades.magnet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1303;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_3611;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;
import net.p3pp3rf1y.sophisticatedcore.inventory.IItemHandlerSimpleInserter;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ContentsFilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IContentsFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IPickupResponseUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.XpHelper;

public class MagnetUpgradeWrapper
   extends UpgradeWrapperBase<MagnetUpgradeWrapper, MagnetUpgradeItem>
   implements IContentsFilteredUpgrade,
   ITickableUpgrade,
   IPickupResponseUpgrade {
   private static final String PREVENT_REMOTE_MOVEMENT = "PreventRemoteMovement";
   private static final String ALLOW_MACHINE_MOVEMENT = "AllowMachineRemoteMovement";
   private static final int COOLDOWN_TICKS = 10;
   private static final int FULL_COOLDOWN_TICKS = 40;
   private final ContentsFilterLogic filterLogic;
   private static final Set<IMagnetPreventionChecker> magnetCheckers = new HashSet<>();

   public static void addMagnetPreventionChecker(IMagnetPreventionChecker checker) {
      magnetCheckers.add(checker);
   }

   public MagnetUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new ContentsFilterLogic(
         upgrade,
         upgradeSaveHandler,
         this.upgradeItem.getFilterSlotCount(),
         storageWrapper::getInventoryHandler,
         storageWrapper.getSettingsHandler().getTypeCategory(MemorySettingsCategory.class),
         ModCoreDataComponents.FILTER_ATTRIBUTES
      );
   }

   @Override
   public ContentsFilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   @Override
   public class_1799 pickup(class_1937 level, class_1799 stack, boolean simulate) {
      return this.shouldPickupItems() && this.filterLogic.matchesFilter(stack)
         ? this.storageWrapper.getInventoryForUpgradeProcessing().insertItem(stack, simulate)
         : stack;
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.isInCooldown(level)) {
         int cooldown = this.shouldPickupItems() ? this.pickupItems(entity, level, pos) : 40;
         if (this.shouldPickupXp() && this.canFillStorageWithXp()) {
            cooldown = Math.min(cooldown, this.pickupXpOrbs(entity, level, pos));
         }

         this.setCooldown(level, cooldown);
      }
   }

   private boolean canFillStorageWithXp() {
      return this.storageWrapper
         .getFluidHandler()
         .map(fluidHandler -> fluidHandler.simulateInsert(ModFluids.EXPERIENCE_TAG, 81000L, (class_3611)ModFluids.XP_STILL.get(), null) > 0L)
         .orElse(false);
   }

   private int pickupXpOrbs(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      List<class_1303> xpEntities = level.method_8390(class_1303.class, new class_238(pos).method_1014(this.upgradeItem.getRadius()), e -> true);
      if (xpEntities.isEmpty()) {
         return 10;
      } else {
         int cooldown = 10;

         for (class_1303 xpOrb : xpEntities) {
            if (xpOrb.method_5805() && !this.canNotPickup(xpOrb, entity) && !this.tryToFillTank(xpOrb, entity, level)) {
               cooldown = 40;
               break;
            }
         }

         return cooldown;
      }
   }

   private boolean tryToFillTank(class_1303 xpOrb, @Nullable class_1297 entity, class_1937 level) {
      long amountToTransfer = XpHelper.experienceToLiquid(xpOrb.method_5919());
      return this.storageWrapper
         .getFluidHandler()
         .map(
            fluidHandler -> {
               Transaction outer = Transaction.openOuter();

               long amountAdded;
               try {
                  amountAdded = fluidHandler.insert(ModFluids.EXPERIENCE_TAG, amountToTransfer, (class_3611)ModFluids.XP_STILL.get(), outer);
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

               if (amountAdded > 0L) {
                  class_243 pos = xpOrb.method_19538();
                  xpOrb.method_31472();
                  class_1657 player = entity instanceof class_1657 ? (class_1657)entity : null;
                  if (player != null) {
                     playXpPickupSound(level, player);
                  }

                  if (amountToTransfer > amountAdded) {
                     level.method_8649(
                        new class_1303(
                           level, pos.method_10216(), pos.method_10214(), pos.method_10215(), (int)XpHelper.liquidToExperience(amountToTransfer - amountAdded)
                        )
                     );
                  }

                  return true;
               } else {
                  return false;
               }
            }
         )
         .orElse(false);
   }

   private int pickupItems(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      List<class_1542> itemEntities = level.method_18023(class_1299.field_6052, new class_238(pos).method_1014(this.upgradeItem.getRadius()), e -> true);
      if (itemEntities.isEmpty()) {
         return 10;
      } else {
         int cooldown = 10;
         class_1657 player = entity instanceof class_1657 ? (class_1657)entity : null;

         for (class_1542 itemEntity : itemEntities) {
            if (itemEntity.method_5805() && this.filterLogic.matchesFilter(itemEntity.method_6983()) && !this.canNotPickup(itemEntity, entity)) {
               if (this.tryToInsertItem(player, itemEntity)) {
                  if (player != null) {
                     playItemPickupSound(level, player);
                  }
               } else {
                  cooldown = 40;
               }
            }
         }

         return cooldown;
      }
   }

   private static void playItemPickupSound(class_1937 level, @Nonnull class_1657 player) {
      level.method_43128(
         null,
         player.method_23317(),
         player.method_23318(),
         player.method_23321(),
         class_3417.field_15197,
         class_3419.field_15248,
         0.2F,
         (level.field_9229.method_43057() - level.field_9229.method_43057()) * 1.4F + 2.0F
      );
   }

   private static void playXpPickupSound(class_1937 level, @Nonnull class_1657 player) {
      level.method_43128(
         null,
         player.method_23317(),
         player.method_23318(),
         player.method_23321(),
         class_3417.field_14627,
         class_3419.field_15248,
         0.1F,
         (level.field_9229.method_43057() - level.field_9229.method_43057()) * 0.35F + 0.9F
      );
   }

   private boolean isBlockedBySomething(class_1297 entity) {
      for (IMagnetPreventionChecker checker : magnetCheckers) {
         if (checker.isBlocked(entity)) {
            return true;
         }
      }

      return false;
   }

   private boolean canNotPickup(class_1297 pickedUpEntity, @Nullable class_1297 entity) {
      if (this.isBlockedBySomething(pickedUpEntity)) {
         return true;
      } else {
         class_2487 data = pickedUpEntity.getSophisticatedCustomData();
         return entity != null
            ? data.method_10545("PreventRemoteMovement")
            : data.method_10545("PreventRemoteMovement") && !data.method_10545("AllowMachineRemoteMovement");
      }
   }

   private boolean tryToInsertItem(@Nullable class_1657 player, class_1542 itemEntity) {
      class_1799 stack = itemEntity.method_6983();
      IItemHandlerSimpleInserter inventory = this.storageWrapper.getInventoryForUpgradeProcessing();
      class_1799 remaining = inventory.insertItem(stack, true);
      boolean insertedSomething = false;
      if (remaining.method_7947() != stack.method_7947()) {
         insertedSomething = true;
         int originalCount = stack.method_7947();
         class_1792 item = stack.method_7909();
         remaining = inventory.insertItem(stack, false);
         itemEntity.method_6979(remaining);
         if (player != null) {
            player.method_7342(class_3468.field_15392.method_14956(item), originalCount - remaining.method_7947());
         }
      }

      return insertedSomething;
   }

   public void setPickupItems(boolean pickupItems) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.PICKUP_ITEMS, pickupItems);
      this.save();
   }

   public boolean shouldPickupItems() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.PICKUP_ITEMS, true);
   }

   public void setPickupXp(boolean pickupXp) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.PICKUP_XP, pickupXp);
      this.save();
   }

   public boolean shouldPickupXp() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.PICKUP_XP, true);
   }
}
