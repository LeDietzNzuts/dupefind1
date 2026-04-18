package net.p3pp3rf1y.sophisticatedbackpacks.common.gui;

import com.google.common.collect.ImmutableMap.Builder;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.network.SyncClientInfoPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryHandler;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.util.WorldHelper;

public abstract class BackpackContext {
   public static final String SUBBACKPACK_DISPLAY_NAME_PREFIX = "... > ";

   public abstract Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 var1);

   public abstract boolean shouldLockBackpackSlot(class_1657 var1);

   public abstract IBackpackWrapper getBackpackWrapper(class_1657 var1);

   public abstract int getBackpackSlotIndex();

   public abstract BackpackContext getSubBackpackContext(int var1);

   public abstract BackpackContext getParentBackpackContext();

   public abstract BackpackContext.ContextType getType();

   public void toBuffer(class_2540 buffer) {
      this.getType().toBuffer(buffer);
      this.addToBuffer(buffer);
   }

   public abstract void addToBuffer(class_2540 var1);

   public abstract boolean canInteractWith(class_1657 var1);

   public class_2338 getBackpackPosition(class_1657 playerEntity) {
      return playerEntity.method_24515();
   }

   public class_2561 getDisplayName(class_1657 player) {
      return this.getBackpackWrapper(player).getBackpack().method_7964();
   }

   public abstract void onUpgradeChanged(class_1657 var1);

   public static BackpackContext fromBuffer(class_2540 buffer, class_1937 level) {
      BackpackContext.ContextType type = BackpackContext.ContextType.fromBuffer(buffer);

      return switch (type) {
         case BLOCK_BACKPACK -> BackpackContext.Block.fromBuffer(buffer);
         case BLOCK_SUB_BACKPACK -> BackpackContext.BlockSubBackpack.fromBuffer(buffer);
         case ITEM_BACKPACK -> BackpackContext.Item.fromBuffer(buffer);
         case ITEM_SUB_BACKPACK -> BackpackContext.ItemSubBackpack.fromBuffer(buffer);
         case ANOTHER_PLAYER_BACKPACK -> BackpackContext.AnotherPlayer.fromBuffer(buffer, level);
         case ANOTHER_PLAYER_SUB_BACKPACK -> BackpackContext.AnotherPlayerSubBackpack.fromBuffer(buffer, level);
      };
   }

   public boolean wasOpenFromInventory() {
      return false;
   }

   public static class AnotherPlayer extends BackpackContext.Item {
      protected final class_1657 otherPlayer;

      public AnotherPlayer(String handlerName, String identifier, int backpackSlotIndex, class_1657 otherPlayer) {
         super(handlerName, identifier, backpackSlotIndex);
         this.otherPlayer = otherPlayer;
      }

      @Override
      public boolean shouldLockBackpackSlot(class_1657 player) {
         return false;
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         return super.getBackpackWrapper(this.otherPlayer);
      }

      @Override
      public BackpackContext getSubBackpackContext(int subBackpackSlotIndex) {
         return new BackpackContext.AnotherPlayerSubBackpack(this.otherPlayer, this.handlerName, this.identifier, this.backpackSlotIndex, subBackpackSlotIndex);
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         buffer.method_53002(this.otherPlayer.method_5628());
         buffer.method_10814(this.handlerName);
         buffer.method_10814(this.identifier);
         buffer.method_53002(this.backpackSlotIndex);
      }

      @Override
      public boolean canInteractWith(class_1657 player) {
         return player.method_5739(this.otherPlayer) < 8.0F;
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.ANOTHER_PLAYER_BACKPACK;
      }

      @Override
      public class_2561 getDisplayName(class_1657 player) {
         return super.getDisplayName(this.otherPlayer);
      }

      public static BackpackContext fromBuffer(class_2540 buffer, class_1937 level) {
         int playerId = buffer.readInt();
         class_1657 otherPlayer = (class_1657)level.method_8469(playerId);
         return new BackpackContext.AnotherPlayer(buffer.method_19772(), buffer.method_19772(), buffer.readInt(), Objects.requireNonNull(otherPlayer));
      }
   }

   public static class AnotherPlayerSubBackpack extends BackpackContext.AnotherPlayer {
      private final int subBackpackSlotIndex;
      @Nullable
      private IStorageWrapper parentWrapper;

      public AnotherPlayerSubBackpack(class_1657 otherPlayer, String handlerName, String identifier, int backpackSlotIndex, int subBackpackSlotIndex) {
         super(handlerName, identifier, backpackSlotIndex, otherPlayer);
         this.subBackpackSlotIndex = subBackpackSlotIndex;
      }

      @Override
      public Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 player) {
         if (this.parentWrapper == null) {
            this.parentWrapper = super.getBackpackWrapper(player);
         }

         return Optional.of(this.parentWrapper);
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         return this.getParentBackpackWrapper(this.otherPlayer)
            .map(
               parent -> BackpackWrapper.fromExistingData(parent.getInventoryHandler().getStackInSlot(this.subBackpackSlotIndex))
                  .orElse(IBackpackWrapper.Noop.INSTANCE)
            )
            .orElse(IBackpackWrapper.Noop.INSTANCE);
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         super.addToBuffer(buffer);
         buffer.method_53002(this.subBackpackSlotIndex);
      }

      @Override
      public BackpackContext getParentBackpackContext() {
         return new BackpackContext.AnotherPlayer(this.handlerName, this.identifier, this.backpackSlotIndex, this.otherPlayer);
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.ANOTHER_PLAYER_SUB_BACKPACK;
      }

      @Override
      public class_2561 getDisplayName(class_1657 player) {
         return class_2561.method_43470("... > " + super.getDisplayName(player).getString());
      }

      public static BackpackContext fromBuffer(class_2540 buffer, class_1937 level) {
         int playerId = buffer.readInt();
         class_1657 otherPlayer = (class_1657)level.method_8469(playerId);
         return new BackpackContext.AnotherPlayerSubBackpack(
            Objects.requireNonNull(otherPlayer), buffer.method_19772(), buffer.method_19772(), buffer.readInt(), buffer.readInt()
         );
      }

      @Override
      public void onUpgradeChanged(class_1657 player) {
      }
   }

   public static class Block extends BackpackContext {
      protected final class_2338 pos;

      public Block(class_2338 pos) {
         this.pos = pos;
      }

      @Override
      public class_2338 getBackpackPosition(class_1657 playerEntity) {
         return this.pos;
      }

      @Override
      public void onUpgradeChanged(class_1657 player) {
         if (!player.method_37908().field_9236) {
            WorldHelper.getBlockEntity(player.method_37908(), this.pos, BackpackBlockEntity.class).ifPresent(BackpackBlockEntity::refreshRenderState);
         }
      }

      @Override
      public Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 player) {
         return Optional.empty();
      }

      @Override
      public boolean shouldLockBackpackSlot(class_1657 player) {
         return false;
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         return WorldHelper.getBlockEntity(player.method_37908(), this.pos, BackpackBlockEntity.class)
            .map(BackpackBlockEntity::getBackpackWrapper)
            .orElse(IBackpackWrapper.Noop.INSTANCE);
      }

      @Override
      public int getBackpackSlotIndex() {
         return -1;
      }

      @Override
      public BackpackContext getSubBackpackContext(int subBackpackSlotIndex) {
         return new BackpackContext.BlockSubBackpack(this.pos, subBackpackSlotIndex);
      }

      @Override
      public BackpackContext getParentBackpackContext() {
         return this;
      }

      public static BackpackContext fromBuffer(class_2540 buffer) {
         return new BackpackContext.Block(class_2338.method_10092(buffer.readLong()));
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         buffer.method_52974(this.pos.method_10063());
      }

      @Override
      public boolean canInteractWith(class_1657 player) {
         return player.method_37908().method_8321(this.pos) instanceof BackpackBlockEntity
            && player.method_5649(this.pos.method_10263() + 0.5, this.pos.method_10264() + 0.5, this.pos.method_10260() + 0.5) <= 64.0;
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.BLOCK_BACKPACK;
      }
   }

   public static class BlockSubBackpack extends BackpackContext.Block {
      private final int subBackpackSlotIndex;
      @Nullable
      private IStorageWrapper parentWrapper;

      public BlockSubBackpack(class_2338 pos, int subBackpackSlotIndex) {
         super(pos);
         this.subBackpackSlotIndex = subBackpackSlotIndex;
      }

      @Override
      public Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 player) {
         if (this.parentWrapper == null) {
            this.parentWrapper = super.getBackpackWrapper(player);
         }

         return Optional.of(this.parentWrapper);
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         return this.getParentBackpackWrapper(player)
            .map(
               parent -> {
                  class_1799 stackInSlot = parent.getInventoryHandler().getStackInSlot(this.subBackpackSlotIndex);
                  return (IBackpackWrapper)(!(stackInSlot.method_7909() instanceof BackpackItem)
                     ? IBackpackWrapper.Noop.INSTANCE
                     : BackpackWrapper.fromStack(stackInSlot));
               }
            )
            .orElse(IBackpackWrapper.Noop.INSTANCE);
      }

      public static BackpackContext fromBuffer(class_2540 buffer) {
         return new BackpackContext.BlockSubBackpack(class_2338.method_10092(buffer.readLong()), buffer.readInt());
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         super.addToBuffer(buffer);
         buffer.method_53002(this.subBackpackSlotIndex);
      }

      @Override
      public BackpackContext getParentBackpackContext() {
         return new BackpackContext.Block(this.pos);
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.BLOCK_SUB_BACKPACK;
      }

      @Override
      public class_2561 getDisplayName(class_1657 player) {
         return class_2561.method_43470("... > " + super.getDisplayName(player).getString());
      }

      @Override
      public void onUpgradeChanged(class_1657 player) {
      }
   }

   public static enum ContextType {
      BLOCK_BACKPACK(0),
      BLOCK_SUB_BACKPACK(1),
      ITEM_BACKPACK(2),
      ITEM_SUB_BACKPACK(3),
      ANOTHER_PLAYER_BACKPACK(4),
      ANOTHER_PLAYER_SUB_BACKPACK(5);

      private final int id;
      private static final Map<Integer, BackpackContext.ContextType> ID_CONTEXTS;

      private ContextType(int id) {
         this.id = id;
      }

      public void toBuffer(class_2540 buffer) {
         buffer.method_52998(this.id);
      }

      public static BackpackContext.ContextType fromBuffer(class_2540 buffer) {
         return ID_CONTEXTS.getOrDefault(Integer.valueOf(buffer.readShort()), ITEM_BACKPACK);
      }

      static {
         Builder<Integer, BackpackContext.ContextType> builder = new Builder();

         for (BackpackContext.ContextType value : values()) {
            builder.put(value.id, value);
         }

         ID_CONTEXTS = builder.build();
      }
   }

   public static class Item extends BackpackContext {
      protected final String handlerName;
      protected final String identifier;
      protected final int backpackSlotIndex;
      private final boolean openFromInventory;

      public Item(String handlerName, int backpackSlotIndex) {
         this(handlerName, "", backpackSlotIndex);
      }

      public Item(String handlerName, String identifier, int backpackSlotIndex) {
         this(handlerName, identifier, backpackSlotIndex, false);
      }

      public Item(String handlerName, String identifier, int backpackSlotIndex, boolean openFromInventory) {
         this.handlerName = handlerName;
         this.identifier = identifier;
         this.backpackSlotIndex = backpackSlotIndex;
         this.openFromInventory = openFromInventory;
      }

      @Override
      public boolean wasOpenFromInventory() {
         return this.openFromInventory;
      }

      @Override
      public Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 player) {
         return Optional.empty();
      }

      @Override
      public boolean shouldLockBackpackSlot(class_1657 player) {
         return PlayerInventoryProvider.get().getPlayerInventoryHandler(this.handlerName).map(PlayerInventoryHandler::isVisibleInGui).orElse(false);
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         Optional<PlayerInventoryHandler> inventoryHandler = PlayerInventoryProvider.get().getPlayerInventoryHandler(this.handlerName);
         if (inventoryHandler.isEmpty()) {
            SophisticatedBackpacks.LOGGER.error("Error getting backpack wrapper - Unable to find inventory handler for \"{}\"", this.handlerName);
            return IBackpackWrapper.Noop.INSTANCE;
         } else {
            class_1799 backpackStack = inventoryHandler.get().getStackInSlot(player, this.identifier, this.backpackSlotIndex);
            if (backpackStack.method_7909() instanceof BackpackItem) {
               return BackpackWrapper.fromStack(backpackStack);
            } else {
               SophisticatedBackpacks.LOGGER.error("Error getting backpack wrapper - Stack isn't a backpack");
               return IBackpackWrapper.Noop.INSTANCE;
            }
         }
      }

      @Override
      public void onUpgradeChanged(class_1657 player) {
         if (!player.method_37908().field_9236 && this.handlerName.equals("main")) {
            IStorageWrapper backpackWrapper = this.getBackpackWrapper(player);
            class_2487 modificationSafeRenderInfoNbt = backpackWrapper.getRenderInfo().getNbt().method_10553();
            if (player instanceof class_3222 serverPlayer) {
               PacketDistributor.sendToPlayer(
                  serverPlayer, new SyncClientInfoPayload(this.backpackSlotIndex, modificationSafeRenderInfoNbt, backpackWrapper.getColumnsTaken())
               );
            }
         }
      }

      @Override
      public int getBackpackSlotIndex() {
         return this.backpackSlotIndex;
      }

      @Override
      public BackpackContext getSubBackpackContext(int subBackpackSlotIndex) {
         return new BackpackContext.ItemSubBackpack(this.handlerName, this.identifier, this.backpackSlotIndex, this.openFromInventory, subBackpackSlotIndex);
      }

      @Override
      public BackpackContext getParentBackpackContext() {
         return this;
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.ITEM_BACKPACK;
      }

      public static BackpackContext fromBuffer(class_2540 buffer) {
         return new BackpackContext.Item(buffer.method_19772(), buffer.method_19772(), buffer.readInt(), buffer.readBoolean());
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         buffer.method_10814(this.handlerName);
         buffer.method_10814(this.identifier);
         buffer.method_53002(this.backpackSlotIndex);
         buffer.method_52964(this.openFromInventory);
      }

      @Override
      public boolean canInteractWith(class_1657 player) {
         return true;
      }
   }

   public static class ItemSubBackpack extends BackpackContext.Item {
      private final int subBackpackSlotIndex;
      @Nullable
      private IStorageWrapper parentWrapper;

      public ItemSubBackpack(String handlerName, String identifier, int backpackSlotIndex, boolean parentOpenFromInventory, int subBackpackSlotIndex) {
         super(handlerName, identifier, backpackSlotIndex, parentOpenFromInventory);
         this.subBackpackSlotIndex = subBackpackSlotIndex;
      }

      @Override
      public Optional<IStorageWrapper> getParentBackpackWrapper(class_1657 player) {
         if (this.parentWrapper == null) {
            this.parentWrapper = super.getBackpackWrapper(player);
         }

         return Optional.of(this.parentWrapper);
      }

      @Override
      public IBackpackWrapper getBackpackWrapper(class_1657 player) {
         return this.getParentBackpackWrapper(player)
            .map(
               parent -> {
                  class_1799 stackInSlot = parent.getInventoryHandler().getStackInSlot(this.subBackpackSlotIndex);
                  return (IBackpackWrapper)(!(stackInSlot.method_7909() instanceof BackpackItem)
                     ? IBackpackWrapper.Noop.INSTANCE
                     : BackpackWrapper.fromStack(stackInSlot));
               }
            )
            .orElse(IBackpackWrapper.Noop.INSTANCE);
      }

      public static BackpackContext fromBuffer(class_2540 buffer) {
         return new BackpackContext.ItemSubBackpack(buffer.method_19772(), buffer.method_19772(), buffer.readInt(), buffer.readBoolean(), buffer.readInt());
      }

      @Override
      public void addToBuffer(class_2540 buffer) {
         super.addToBuffer(buffer);
         buffer.method_53002(this.subBackpackSlotIndex);
      }

      @Override
      public BackpackContext getParentBackpackContext() {
         return new BackpackContext.Item(this.handlerName, this.identifier, this.backpackSlotIndex, super.wasOpenFromInventory());
      }

      @Override
      public BackpackContext.ContextType getType() {
         return BackpackContext.ContextType.ITEM_SUB_BACKPACK;
      }

      @Override
      public class_2561 getDisplayName(class_1657 player) {
         return class_2561.method_43470("... > " + super.getDisplayName(player).getString());
      }

      @Override
      public void onUpgradeChanged(class_1657 player) {
      }
   }
}
