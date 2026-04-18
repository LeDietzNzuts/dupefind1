package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import io.github.fabricators_of_create.porting_lib.transfer.item.SlottedStackStorage;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_3218;
import net.minecraft.class_6880;
import net.minecraft.class_9288;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_9793;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.upgrades.ITickableUpgrade;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeWrapperBase;
import net.p3pp3rf1y.sophisticatedcore.util.ComponentItemHandler;

public class JukeboxUpgradeWrapper extends UpgradeWrapperBase<JukeboxUpgradeWrapper, JukeboxUpgradeItem> implements ITickableUpgrade {
   private static final int KEEP_ALIVE_SEND_INTERVAL = 5;
   private final ComponentItemHandler discInventory;
   private long lastKeepAliveSendTime = 0L;
   private boolean isPlaying;
   private final LinkedList<Integer> playlist = new LinkedList<>();
   private final LinkedList<Integer> history = new LinkedList<>();
   private final Set<Integer> discsRemoved = new HashSet<>();
   private final Set<Integer> discsAdded = new HashSet<>();
   @Nullable
   private class_1297 entityPlaying = null;
   @Nullable
   private class_1937 levelPlaying = null;
   @Nullable
   private class_2338 posPlaying = null;
   private final Runnable onFinishedCallback = this::onDiscFinished;

   protected JukeboxUpgradeWrapper(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      super(storageWrapper, upgrade, upgradeSaveHandler);
      this.discInventory = new ComponentItemHandler(upgrade, class_9334.field_49622, this.upgradeItem.getNumberOfSlots()) {
         @Override
         protected void onContentsChanged(int slot, class_1799 oldStack, class_1799 newStack) {
            super.onContentsChanged(slot, oldStack, newStack);
            JukeboxUpgradeWrapper.this.save();
            if (oldStack.method_7960() && !newStack.method_7960()) {
               JukeboxUpgradeWrapper.this.discsAdded.add(slot);
               JukeboxUpgradeWrapper.this.discsRemoved.remove(slot);
            } else if (!oldStack.method_7960() && newStack.method_7960()) {
               JukeboxUpgradeWrapper.this.discsRemoved.add(slot);
               JukeboxUpgradeWrapper.this.discsAdded.remove(slot);
            }
         }

         @Override
         public boolean isItemValid(int slot, class_1799 stack) {
            return stack.method_7960() || stack.method_57826(class_9334.field_52175);
         }
      };
      this.isPlaying = (Boolean)upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.IS_PLAYING, false);
   }

   public boolean isShuffleEnabled() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.SHUFFLE, false);
   }

   public void setShuffleEnabled(boolean shuffleEnabled) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.SHUFFLE, shuffleEnabled);
      this.save();
      this.initPlaylist(true);
   }

   public RepeatMode getRepeatMode() {
      return (RepeatMode)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.REPEAT_MODE, RepeatMode.NO);
   }

   public void setRepeatMode(RepeatMode repeatMode) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.REPEAT_MODE, repeatMode);
      this.save();
   }

   public class_1799 getDisc() {
      return this.getDiscSlotActive() > -1 ? this.discInventory.getStackInSlot(this.getDiscSlotActive()) : class_1799.field_8037;
   }

   public int getDiscSlotActive() {
      return (Integer)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.DISC_SLOT_ACTIVE, -1);
   }

   private void setDiscSlotActive(int discSlotActive) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.DISC_SLOT_ACTIVE, discSlotActive);
      this.save();
   }

   public void play(class_1937 level, class_2338 pos) {
      if (!this.isPlaying) {
         this.levelPlaying = level;
         this.posPlaying = pos;
         this.playNext();
      }
   }

   public void play(class_1297 entity) {
      if (!this.isPlaying) {
         this.entityPlaying = entity;
         this.playNext();
      }
   }

   private void playDisc() {
      class_1937 level = this.entityPlaying != null ? this.entityPlaying.method_37908() : this.levelPlaying;
      if (level instanceof class_3218 serverLevel && (this.posPlaying != null || this.entityPlaying != null)) {
         if (!this.getDisc().method_7960()) {
            this.storageWrapper
               .getContentsUuid()
               .ifPresent(
                  storageUuid -> this.getJukeboxSongHolder(level)
                     .ifPresent(
                        song -> {
                           if (this.entityPlaying != null) {
                              ServerStorageSoundHandler.startPlayingDisc(
                                 serverLevel,
                                 this.entityPlaying.method_19538(),
                                 storageUuid,
                                 this.entityPlaying.method_5628(),
                                 this.getDisc(),
                                 (class_6880<class_9793>)song,
                                 this.onFinishedCallback
                              );
                           } else {
                              ServerStorageSoundHandler.startPlayingDisc(
                                 serverLevel, this.posPlaying, storageUuid, this.getDisc(), (class_6880<class_9793>)song, this.onFinishedCallback
                              );
                           }

                           this.upgrade
                              .sophisticatedCore_set(ModCoreDataComponents.DISC_FINISH_TIME, level.method_8510() + ((class_9793)song.comp_349()).method_60750());
                        }
                     )
               );
            this.setIsPlaying(true);
         }
      }
   }

   public Optional<class_6880<class_9793>> getJukeboxSongHolder(class_1937 level) {
      return class_9793.method_60753(level.method_30349(), this.getDisc());
   }

   private void onDiscFinished() {
      if (this.getRepeatMode() == RepeatMode.ONE) {
         this.playDisc();
      } else if (this.getRepeatMode() == RepeatMode.ALL) {
         this.playNext();
      } else {
         this.playNext(false);
      }
   }

   private void setIsPlaying(boolean playing) {
      this.isPlaying = playing;
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.IS_PLAYING, playing);
      if (this.isPlaying) {
         this.storageWrapper.getRenderInfo().setUpgradeRenderData(JukeboxUpgradeRenderData.TYPE, new JukeboxUpgradeRenderData(true));
      } else {
         this.removeRenderData();
         this.setDiscSlotActive(-1);
      }

      this.save();
   }

   private void removeRenderData() {
      this.storageWrapper.getRenderInfo().removeUpgradeRenderData(JukeboxUpgradeRenderData.TYPE);
   }

   public void stop(class_1309 entity) {
      if (entity.method_37908() instanceof class_3218) {
         this.storageWrapper
            .getContentsUuid()
            .ifPresent(storageUuid -> ServerStorageSoundHandler.stopPlayingDisc(entity.method_37908(), entity.method_19538(), storageUuid));
         this.setIsPlaying(false);
         this.upgrade.sophisticatedCore_remove(ModCoreDataComponents.DISC_FINISH_TIME);
         this.setDiscSlotActive(-1);
         this.playlist.clear();
         this.history.clear();
      }
   }

   public SlottedStackStorage getDiscInventory() {
      return this.discInventory;
   }

   @Override
   public void tick(@Nullable class_1297 entity, class_1937 level, class_2338 pos) {
      if (!level.method_8608()) {
         if (!this.discsRemoved.isEmpty()) {
            this.discsRemoved.forEach(index -> {
               this.playlist.remove(index);
               this.history.remove(index);
            });
            this.discsRemoved.clear();
         }

         if (!this.discsAdded.isEmpty()) {
            this.playlist.addAll(this.discsAdded);
            this.discsAdded.clear();
         }
      }

      if (this.isPlaying && this.lastKeepAliveSendTime < level.method_8510() - 5L) {
         this.storageWrapper
            .getContentsUuid()
            .ifPresent(
               storageUuid -> ServerStorageSoundHandler.updateKeepAlive(
                  storageUuid, level, entity != null ? entity.method_19538() : class_243.method_24953(pos), () -> this.setIsPlaying(false)
               )
            );
         this.lastKeepAliveSendTime = level.method_8510();
      }
   }

   public boolean isPlaying() {
      return this.isPlaying;
   }

   @Override
   public void onBeforeRemoved() {
      this.removeRenderData();
   }

   public void next() {
      if (this.isPlaying) {
         this.playNext();
      }
   }

   public void playNext() {
      this.playNext(true);
   }

   public void playNext(boolean startOverIfAtTheEnd) {
      if (this.playlist.isEmpty() && startOverIfAtTheEnd) {
         this.initPlaylist(false);
      }

      if (!this.playlist.isEmpty()) {
         if (this.getDiscSlotActive() != -1) {
            this.history.add(this.getDiscSlotActive());
            if (this.history.size() > this.discInventory.getSlotCount()) {
               this.history.poll();
            }
         }

         Integer discIndex = this.playlist.poll();
         if (discIndex != null) {
            this.setDiscSlotActive(discIndex);
            this.playDisc();
         }
      }
   }

   private void initPlaylist(boolean excludeActive) {
      this.playlist.clear();

      for (int i = 0; i < this.discInventory.getSlotCount(); i++) {
         if (!this.discInventory.getStackInSlot(i).method_7960() && (!excludeActive || !this.isPlaying || i != this.getDiscSlotActive())) {
            this.playlist.add(i);
         }
      }

      if (this.isShuffleEnabled()) {
         Collections.shuffle(this.playlist);
      }
   }

   public void previous() {
      if (this.isPlaying) {
         this.playPrevious();
      }
   }

   public void playPrevious() {
      if (!this.history.isEmpty()) {
         this.playlist.addFirst(this.getDiscSlotActive());
         Integer discIndex = this.history.pollLast();
         if (discIndex != null) {
            this.setDiscSlotActive(discIndex);
            this.playDisc();
         }
      }
   }

   public long getDiscFinishTime() {
      return (Long)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.DISC_FINISH_TIME, 0L);
   }
}
