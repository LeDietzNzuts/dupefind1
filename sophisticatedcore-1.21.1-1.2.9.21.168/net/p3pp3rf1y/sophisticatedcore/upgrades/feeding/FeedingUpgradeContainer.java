package net.p3pp3rf1y.sophisticatedcore.upgrades.feeding;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class FeedingUpgradeContainer extends UpgradeContainerBase<FeedingUpgradeWrapper, FeedingUpgradeContainer> {
   private static final String DATA_HUNGER_LEVEL = "hungerLevel";
   private static final String DATA_FEED_IMMEDIATELY_WHEN_HURT = "feedImmediatelyWhenHurt";
   private final FilterLogicContainer<FilterLogic> filterLogicContainer = new FilterLogicContainer<>(
      () -> this.upgradeWrapper.getFilterLogic(), this, this.slots::add
   );

   public FeedingUpgradeContainer(
      class_1657 player, int containerId, FeedingUpgradeWrapper wrapper, UpgradeContainerType<FeedingUpgradeWrapper, FeedingUpgradeContainer> type
   ) {
      super(player, containerId, wrapper, type);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("hungerLevel")) {
         this.setFeedAtHungerLevel(HungerLevel.fromName(data.method_10558("hungerLevel")));
      } else if (data.method_10545("feedImmediatelyWhenHurt")) {
         this.setFeedImmediatelyWhenHurt(data.method_10577("feedImmediatelyWhenHurt"));
      }

      this.filterLogicContainer.handlePacket(data);
   }

   public FilterLogicContainer<FilterLogic> getFilterLogicContainer() {
      return this.filterLogicContainer;
   }

   public void setFeedAtHungerLevel(HungerLevel hungerLevel) {
      this.upgradeWrapper.setFeedAtHungerLevel(hungerLevel);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "hungerLevel", hungerLevel));
   }

   public HungerLevel getFeedAtHungerLevel() {
      return this.upgradeWrapper.getFeedAtHungerLevel();
   }

   public void setFeedImmediatelyWhenHurt(boolean feedImmediatelyWhenHurt) {
      this.upgradeWrapper.setFeedImmediatelyWhenHurt(feedImmediatelyWhenHurt);
      this.sendBooleanToServer("feedImmediatelyWhenHurt", feedImmediatelyWhenHurt);
   }

   public boolean shouldFeedImmediatelyWhenHurt() {
      return this.upgradeWrapper.shouldFeedImmediatelyWhenHurt();
   }
}
