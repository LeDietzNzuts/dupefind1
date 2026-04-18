package net.p3pp3rf1y.sophisticatedcore.compat.chipped;

import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_2487;
import net.minecraft.class_3914;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class BlockTransformationUpgradeContainer extends UpgradeContainerBase<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeContainer> {
   private static final String DATA_SHIFT_CLICK_INTO_STORAGE = "shiftClickIntoStorage";
   private final BlockTransformationRecipeContainer recipeContainer;

   public BlockTransformationUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      BlockTransformationUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);
      class_3914 worldPosCallable = player.method_37908().field_9236
         ? class_3914.field_17304
         : class_3914.method_17392(player.method_37908(), player.method_24515());
      this.recipeContainer = new BlockTransformationRecipeContainer(this, upgradeWrapper.getRecipeType(), this.slots::add, this, worldPosCallable);
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("shiftClickIntoStorage")) {
         this.setShiftClickIntoStorage(data.method_10577("shiftClickIntoStorage"));
      } else {
         this.recipeContainer.handlePacket(data);
      }
   }

   public boolean shouldShiftClickIntoStorage() {
      return this.upgradeWrapper.shouldShiftClickIntoStorage();
   }

   public void setShiftClickIntoStorage(boolean shiftClickIntoStorage) {
      this.upgradeWrapper.setShiftClickIntoStorage(shiftClickIntoStorage);
      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "shiftClickIntoStorage", shiftClickIntoStorage));
   }

   public BlockTransformationRecipeContainer getRecipeContainer() {
      return this.recipeContainer;
   }

   @Override
   public boolean mergeIntoStorageFirst(class_1735 slot) {
      return this.recipeContainer.isNotResultSlot(slot) || this.shouldShiftClickIntoStorage();
   }

   @Override
   public boolean allowsPickupAll(class_1735 slot) {
      return this.recipeContainer.isNotResultSlot(slot);
   }
}
