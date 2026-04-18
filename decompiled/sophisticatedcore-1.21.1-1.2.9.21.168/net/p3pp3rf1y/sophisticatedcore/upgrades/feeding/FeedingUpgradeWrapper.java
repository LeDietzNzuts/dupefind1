package net.p3pp3rf1y.sophisticatedcore.upgrades.feeding;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_4174;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.inventory.ITrackedContentsItemHandler;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IFilteredUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.Capabilities;
import net.p3pp3rf1y.sophisticatedcore.util.CapabilityHelper;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public class FeedingUpgradeWrapper extends UpgradeWrapperBase<FeedingUpgradeWrapper, FeedingUpgradeItem> implements ITickableUpgrade, IFilteredUpgrade {
   private static final int COOLDOWN = 100;
   private static final int STILL_HUNGRY_COOLDOWN = 10;
   private static final int FEEDING_RANGE = 3;
   private final FilterLogic filterLogic;

   public FeedingUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.filterLogic = new FilterLogic(
         upgrade,
         upgradeSaveHandler,
         this.upgradeItem.getFilterSlotCount(),
         s -> s.method_57826(class_9334.field_50075),
         ModCoreDataComponents.FILTER_ATTRIBUTES
      );
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!this.isInCooldown(level)) {
         boolean hungryPlayer = false;
         if (!(entity instanceof class_1657)) {
            AtomicBoolean stillHungryPlayer = new AtomicBoolean(false);
            level.method_18023(class_1299.field_6097, new class_238(pos).method_1014(3.0), p -> true)
               .forEach(p -> stillHungryPlayer.set(stillHungryPlayer.get() || this.feedPlayerAndGetHungry(p, level)));
            hungryPlayer = stillHungryPlayer.get();
         } else if (this.feedPlayerAndGetHungry((class_1657)entity, level)) {
            hungryPlayer = true;
         }

         if (hungryPlayer) {
            this.setCooldown(level, 10);
         } else {
            this.setCooldown(level, 100);
         }
      }
   }

   private boolean feedPlayerAndGetHungry(class_1657 player, class_1937 level) {
      int hungerLevel = 20 - player.method_7344().method_7586();
      return hungerLevel == 0 ? false : this.tryFeedingFoodFromStorage(level, hungerLevel, player) && player.method_7344().method_7586() < 20;
   }

   private boolean tryFeedingFoodFromStorage(class_1937 level, int hungerLevel, class_1657 player) {
      ITrackedContentsItemHandler inventory = this.storageWrapper.getInventoryForUpgradeProcessing();
      return InventoryHelper.iterate(
         inventory, (slot, stack) -> this.tryFeedingStack(level, hungerLevel, player, slot, stack, inventory), () -> false, ret -> ret
      );
   }

   private boolean tryFeedingStack(class_1937 level, int hungerLevel, class_1657 player, Integer slot, class_1799 stack, ITrackedContentsItemHandler inventory) {
      boolean isHurt = player.method_6032() < player.method_6063() - 0.1F;
      if (isEdible(stack, player)
         && this.filterLogic.matchesFilter(stack)
         && (this.isHungryEnoughForFood(hungerLevel, stack, player) || this.shouldFeedImmediatelyWhenHurt() && hungerLevel > 0 && isHurt)) {
         class_1799 mainHandItem = player.method_6047();
         player.method_6122(class_1268.field_5808, stack);
         class_1799 singleItemCopy = stack.method_7972();
         singleItemCopy.method_7939(1);
         if (singleItemCopy.method_7913(level, player, class_1268.field_5808).method_5467() == class_1269.field_21466) {
            stack.method_7934(1);
            inventory.setStackInSlot(slot, stack);
            class_1799 resultItem = singleItemCopy.method_7909().method_7861(singleItemCopy, level, player);
            if (!resultItem.method_7960()) {
               class_1799 insertResult = inventory.insertItem(resultItem, false);
               if (!insertResult.method_7960()) {
                  CapabilityHelper.runOnCapability(
                     player, Capabilities.ItemHandler.ENTITY, null, playerInventory -> InventoryHelper.insertOrDropItem(player, insertResult, playerInventory)
                  );
               }
            }

            player.method_6122(class_1268.field_5808, mainHandItem);
            return true;
         }

         player.method_6122(class_1268.field_5808, mainHandItem);
      }

      return false;
   }

   private static boolean isEdible(class_1799 stack, class_1309 player) {
      if (!stack.method_57826(class_9334.field_50075)) {
         return false;
      } else {
         class_4174 foodProperties = stack.method_7909().getFoodProperties(stack, player);
         return foodProperties != null && foodProperties.comp_2491() >= 1;
      }
   }

   private boolean isHungryEnoughForFood(int hungerLevel, class_1799 stack, class_1657 player) {
      class_4174 foodProperties = stack.method_7909().getFoodProperties(stack, player);
      if (foodProperties == null) {
         return false;
      } else {
         HungerLevel feedAtHungerLevel = this.getFeedAtHungerLevel();
         if (feedAtHungerLevel == HungerLevel.ANY) {
            return true;
         } else {
            int nutrition = foodProperties.comp_2491();
            return (feedAtHungerLevel == HungerLevel.HALF ? nutrition / 2 : nutrition) <= hungerLevel;
         }
      }
   }

   @Override
   public FilterLogic getFilterLogic() {
      return this.filterLogic;
   }

   public HungerLevel getFeedAtHungerLevel() {
      return (HungerLevel)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.FEED_AT_HUNGER_LEVEL, HungerLevel.HALF);
   }

   public void setFeedAtHungerLevel(HungerLevel hungerLevel) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.FEED_AT_HUNGER_LEVEL, hungerLevel);
      this.save();
   }

   public boolean shouldFeedImmediatelyWhenHurt() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.FEED_IMMEDIATELY_WHEN_HURT, true);
   }

   public void setFeedImmediatelyWhenHurt(boolean feedImmediatelyWhenHurt) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.FEED_IMMEDIATELY_WHEN_HURT, feedImmediatelyWhenHurt);
      this.save();
   }
}
