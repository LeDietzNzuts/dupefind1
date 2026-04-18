package net.p3pp3rf1y.sophisticatedbackpacks.common.gui;

import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackAccessLogger;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.UUIDDeduplicator;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.client.gui.SBPTranslationHelper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BackpackContentsPayload;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ISyncedContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SophisticatedMenuProvider;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeHandler;
import net.p3pp3rf1y.sophisticatedcore.util.NoopStorageWrapper;

public class BackpackContainer extends StorageContainerMenuBase<IBackpackWrapper> implements ISyncedContainer {
   private final BackpackContext backpackContext;

   public BackpackContainer(int windowId, class_1657 player, BackpackContext backpackContext) {
      super(
         ModItems.BACKPACK_CONTAINER_TYPE.get(),
         windowId,
         player,
         backpackContext.getBackpackWrapper(player),
         backpackContext.getParentBackpackWrapper(player).orElse(NoopStorageWrapper.INSTANCE),
         backpackContext.getBackpackSlotIndex(),
         backpackContext.shouldLockBackpackSlot(player)
      );
      this.backpackContext = backpackContext;
      ((IBackpackWrapper)this.storageWrapper)
         .getContentsUuid()
         .ifPresent(
            backpackUuid -> {
               class_1799 backpack = ((IBackpackWrapper)this.storageWrapper).getBackpack();
               BackpackAccessLogger.logPlayerAccess(
                  player,
                  backpack.method_7909(),
                  backpackUuid,
                  backpack.method_7964().getString(),
                  ((IBackpackWrapper)this.storageWrapper).getMainColor(),
                  ((IBackpackWrapper)this.storageWrapper).getAccentColor(),
                  ((IBackpackWrapper)this.storageWrapper).getColumnsTaken()
               );
               if (!player.method_37908().method_8608()) {
                  UUIDDeduplicator.checkForDuplicateBackpacksAndRemoveTheirUUID(player, backpackUuid, ((IBackpackWrapper)this.storageWrapper).getBackpack());
               }
            }
         );
   }

   public Optional<class_2338> getBlockPosition() {
      BackpackContext.ContextType type = this.backpackContext.getType();
      return type != BackpackContext.ContextType.BLOCK_BACKPACK && type != BackpackContext.ContextType.BLOCK_SUB_BACKPACK
         ? Optional.empty()
         : Optional.of(this.backpackContext.getBackpackPosition(this.player));
   }

   public Optional<class_1297> getEntity() {
      return Optional.of(this.player);
   }

   protected void sendStorageSettingsToClient() {
      if (!this.player.method_37908().field_9236) {
         ((IBackpackWrapper)this.storageWrapper).getContentsUuid().ifPresent(uuid -> {
            class_2487 settingsContents = new class_2487();
            class_2487 settingsNbt = ((IBackpackWrapper)this.storageWrapper).getSettingsHandler().getNbt();
            if (!settingsNbt.method_33133()) {
               settingsContents.method_10566("settings", settingsNbt);
               if (this.player instanceof class_3222 serverPlayer) {
                  PacketDistributor.sendToPlayer(serverPlayer, new BackpackContentsPayload(uuid, settingsContents));
               }
            }
         });
      }
   }

   protected StorageContainerMenuBase<IBackpackWrapper>.StorageUpgradeSlot instantiateUpgradeSlot(UpgradeHandler upgradeHandler, int slotIndex) {
      return new BackpackContainer.BackpackUpgradeSlot(upgradeHandler, slotIndex);
   }

   public boolean method_7597(class_1657 player) {
      return this.backpackContext.canInteractWith(player);
   }

   public static BackpackContainer fromBuffer(int windowId, class_1661 playerInventory, class_2540 buffer) {
      return new BackpackContainer(windowId, playerInventory.field_7546, BackpackContext.fromBuffer(buffer, playerInventory.field_7546.method_37908()));
   }

   public BackpackContext getBackpackContext() {
      return this.backpackContext;
   }

   public void openSettings() {
      if (this.isClientSide()) {
         this.sendToServer(data -> data.method_10582("action", "openSettings"));
      } else {
         this.player
            .sophisticatedCore_openMenu(
               new SophisticatedMenuProvider(
                  (w, p, pl) -> new BackpackSettingsContainerMenu(w, pl, this.backpackContext),
                  class_2561.method_43471(SBPTranslationHelper.INSTANCE.translGui("settings.title")),
                  false
               ),
               this.backpackContext::toBuffer
            );
      }
   }

   protected boolean storageItemHasChanged() {
      return this.backpackContext.getBackpackWrapper(this.player) != this.storageWrapper;
   }

   public boolean detectSettingsChangeAndReload() {
      return ((IBackpackWrapper)this.storageWrapper).getContentsUuid().map(uuid -> {
         BackpackStorage storage = BackpackStorage.get();
         if (storage.removeUpdatedBackpackSettingsFlag(uuid)) {
            ((IBackpackWrapper)this.storageWrapper).getSettingsHandler().reloadFrom(storage.getOrCreateBackpackContents(uuid));
            this.refreshInventorySlotsIfNeeded();
            return true;
         } else {
            return false;
         }
      }).orElse(false);
   }

   protected boolean shouldSlotItemBeDroppedFromStorage(class_1735 slot) {
      return slot.method_7677().method_7909() instanceof BackpackItem
         && !((IBackpackWrapper)this.storageWrapper).getInventoryHandler().isItemValid(0, slot.method_7677());
   }

   public class BackpackUpgradeSlot extends StorageContainerMenuBase<IBackpackWrapper>.StorageUpgradeSlot {
      public BackpackUpgradeSlot(UpgradeHandler upgradeHandler, int slotIndex) {
         super(BackpackContainer.this, upgradeHandler, slotIndex);
      }

      protected void onUpgradeChanged() {
         super.onUpgradeChanged();
         BackpackContainer.this.backpackContext.onUpgradeChanged(BackpackContainer.this.player);
      }
   }
}
