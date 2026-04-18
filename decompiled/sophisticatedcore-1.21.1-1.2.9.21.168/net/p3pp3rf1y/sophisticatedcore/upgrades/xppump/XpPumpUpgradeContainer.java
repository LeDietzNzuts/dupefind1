package net.p3pp3rf1y.sophisticatedcore.upgrades.xppump;

import net.minecraft.class_1657;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class XpPumpUpgradeContainer extends UpgradeContainerBase<XpPumpUpgradeWrapper, XpPumpUpgradeContainer> {
   private static final String DATA_LEVEL = "level";
   private static final String DATA_DIRECTION = "direction";
   private static final String DATA_ACTION = "action";
   private static final String ACTION_TAKE_LEVELS = "take";
   private static final String ACTION_STORE_LEVELS_FROM_PLAYER = "store";
   private static final String ACTION_STORE_ALL_PLAYERS_EXPERIENCE = "storeAll";
   private static final String ACTION_TAKE_ALL_LEVELS = "takeAll";
   private static final String DATA_LEVELS_TO_STORE = "levelsToStore";
   private static final String DATA_LEVELS_TO_TAKE = "levelsToTake";
   private static final String DATA_MEND_ITEMS = "mendItems";

   public XpPumpUpgradeContainer(
      class_1657 player, int upgradeContainerId, XpPumpUpgradeWrapper upgradeWrapper, UpgradeContainerType<XpPumpUpgradeWrapper, XpPumpUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
   }

   public void setDirection(AutomationDirection direction) {
      this.upgradeWrapper.setDirection(direction);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "direction", direction));
   }

   public AutomationDirection getDirection() {
      return this.upgradeWrapper.getDirection();
   }

   public int getLevel() {
      return this.upgradeWrapper.getLevel();
   }

   public void setLevel(int level) {
      if (level >= 0) {
         this.upgradeWrapper.setLevel(level);
         this.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "level", level));
      }
   }

   public void setLevelsToStore(int levelsToStore) {
      if (levelsToStore >= 1) {
         this.upgradeWrapper.setLevelsToStore(levelsToStore);
         this.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "levelsToStore", levelsToStore));
      }
   }

   public void setLevelsToTake(int levelsToTake) {
      if (levelsToTake >= 1) {
         this.upgradeWrapper.setLevelsToTake(levelsToTake);
         this.sendDataToServer(() -> NBTHelper.putInt(new class_2487(), "levelsToTake", levelsToTake));
      }
   }

   public void takeLevels() {
      this.triggerAction("take");
   }

   public void storeLevels() {
      this.triggerAction("store");
   }

   public void storeAllExperience() {
      this.triggerAction("storeAll");
   }

   public void takeAllExperience() {
      this.triggerAction("takeAll");
   }

   private void triggerAction(String actionName) {
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", actionName));
   }

   public int getLevelsToStore() {
      return this.upgradeWrapper.getLevelsToStore();
   }

   public int getLevelsToTake() {
      return this.upgradeWrapper.getLevelsToTake();
   }

   public void setMendItems(boolean mendItems) {
      this.upgradeWrapper.setMendItems(mendItems);
      this.sendBooleanToServer("mendItems", mendItems);
   }

   public boolean shouldMendItems() {
      return this.upgradeWrapper.shouldMendItems();
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("direction")) {
         this.setDirection(AutomationDirection.fromName(data.method_10558("direction")));
      } else if (data.method_10545("level")) {
         this.setLevel(data.method_10550("level"));
      } else if (data.method_10545("levelsToStore")) {
         this.setLevelsToStore(data.method_10550("levelsToStore"));
      } else if (data.method_10545("levelsToTake")) {
         this.setLevelsToTake(data.method_10550("levelsToTake"));
      } else if (data.method_10545("mendItems")) {
         this.setMendItems(data.method_10577("mendItems"));
      } else if (data.method_10545("action")) {
         String var2 = data.method_10558("action");
         switch (var2) {
            case "take":
               this.upgradeWrapper.giveLevelsToPlayer(this.player);
               break;
            case "store":
               this.upgradeWrapper.takeLevelsFromPlayer(this.player);
               break;
            case "takeAll":
               this.upgradeWrapper.giveAllExperienceToPlayer(this.player);
               break;
            case "storeAll":
               this.upgradeWrapper.takeAllExperienceFromPlayer(this.player);
         }
      }
   }
}
