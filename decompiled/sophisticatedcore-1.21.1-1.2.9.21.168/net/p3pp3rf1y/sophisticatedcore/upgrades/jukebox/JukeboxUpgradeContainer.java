package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1937;
import net.minecraft.class_2487;
import net.minecraft.class_6880;
import net.minecraft.class_9793;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SlotItemHandler;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class JukeboxUpgradeContainer extends UpgradeContainerBase<JukeboxUpgradeWrapper, JukeboxUpgradeContainer> {
   private static final String ACTION_DATA = "action";

   public JukeboxUpgradeContainer(
      class_1657 player,
      int upgradeContainerId,
      JukeboxUpgradeWrapper upgradeWrapper,
      UpgradeContainerType<JukeboxUpgradeWrapper, JukeboxUpgradeContainer> type
   ) {
      super(player, upgradeContainerId, upgradeWrapper, type);

      for (int slot = 0; slot < upgradeWrapper.getDiscInventory().getSlotCount(); slot++) {
         this.slots.add(new SlotItemHandler(upgradeWrapper.getDiscInventory(), slot, -100, -100) {
            public void method_7668() {
               super.method_7668();
               if (upgradeWrapper.isPlaying() && this.sophisticatedCore_getSlotIndex() == upgradeWrapper.getDiscSlotActive()) {
                  upgradeWrapper.stop(player);
               }
            }
         });
      }
   }

   @Override
   public void handlePacket(class_2487 data) {
      if (data.method_10545("action")) {
         String actionName = data.method_10558("action");
         switch (actionName) {
            case "play":
               if (this.player.field_7512 instanceof StorageContainerMenuBase<?> storageContainerMenu) {
                  storageContainerMenu.getBlockPosition()
                     .ifPresentOrElse(
                        pos -> this.upgradeWrapper.play(this.player.method_37908(), pos),
                        () -> this.upgradeWrapper.play(storageContainerMenu.getEntity().orElse(this.player))
                     );
               }
               break;
            case "stop":
               this.upgradeWrapper.stop(this.player);
               break;
            case "next":
               this.upgradeWrapper.next();
               break;
            case "previous":
               this.upgradeWrapper.previous();
         }
      }

      if (data.method_10545("shuffle")) {
         this.upgradeWrapper.setShuffleEnabled(data.method_10577("shuffle"));
      }

      if (data.method_10545("repeat")) {
         NBTHelper.getEnumConstant(data, "repeat", RepeatMode::fromName).ifPresent(this.upgradeWrapper::setRepeatMode);
      }
   }

   public void play() {
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", "play"));
   }

   public void stop() {
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", "stop"));
   }

   public void next() {
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", "next"));
   }

   public void previous() {
      this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", "previous"));
   }

   public boolean isShuffleEnabled() {
      return this.upgradeWrapper.isShuffleEnabled();
   }

   public void toggleShuffle() {
      boolean newValue = !this.upgradeWrapper.isShuffleEnabled();
      this.upgradeWrapper.setShuffleEnabled(newValue);
      this.sendBooleanToServer("shuffle", newValue);
   }

   public RepeatMode getRepeatMode() {
      return this.upgradeWrapper.getRepeatMode();
   }

   public void toggleRepeat() {
      RepeatMode newValue = this.upgradeWrapper.getRepeatMode().next();
      this.upgradeWrapper.setRepeatMode(newValue);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "repeat", newValue));
   }

   public Optional<class_1735> getDiscSlotActive() {
      int discSlotActive = this.upgradeWrapper.getDiscSlotActive();
      return discSlotActive > -1 ? Optional.of(this.slots.get(discSlotActive)) : Optional.empty();
   }

   public long getDiscFinishTime() {
      return this.upgradeWrapper.getDiscFinishTime();
   }

   public Optional<class_6880<class_9793>> getJukeboxSong(class_1937 level) {
      return this.upgradeWrapper.getJukeboxSongHolder(level);
   }
}
