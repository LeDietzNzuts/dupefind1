package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1299;
import net.minecraft.class_1540;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2622;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_9297;
import net.minecraft.class_9334;
import net.minecraft.class_2586.class_9473;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_9323.class_9324;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.IBrushable;
import noobanidus.mods.lootr.common.api.ILootrBlockEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.SimpleLootrInstance;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.integration.digsite_workshop.IModdedBrushItem;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinFallingBlockEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrBrushableBlockEntity extends class_2586 implements ILootrBlockEntity, IBrushable {
   private final SimpleLootrInstance simpleLootrInstance = new SimpleLootrInstance(this::getVisualOpeners, 1);
   @Nullable
   private UUID brushingPlayer;
   @Nullable
   private class_1657 brushingPlayerEntity;
   private int brushCount;
   private long brushCountResetsAtTick;
   private long coolDownEndsAtTick;
   private class_1799 item = class_1799.field_8037;
   @Nullable
   private class_2350 hitDirection;
   @Nullable
   private class_5321<class_52> lootTable;
   private long lootTableSeed;

   public LootrBrushableBlockEntity(class_2338 blockPos, class_2680 blockState) {
      super(LootrRegistry.getBrushableBlockEntity(), blockPos, blockState);
   }

   private class_2487 getFallData(class_7874 provider) {
      class_2487 tag = new class_2487();
      this.method_11007(tag, provider);
      return tag;
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      if (this.method_11010().method_26164(LootrTags.Blocks.SANDS)) {
         return LootrRegistry.getSandTrigger();
      } else {
         return this.method_11010().method_26164(LootrTags.Blocks.GRAVELS) ? LootrRegistry.getGravelTrigger() : null;
      }
   }

   @Override
   public boolean IBrushable$brush(long l, class_1657 player, class_2350 direction) {
      class_1799 brushItem = this.getBrushItem(player);
      if (brushItem.method_7909() instanceof IModdedBrushItem moddedBrushItem) {
         this.coolDownEndsAtTick = this.coolDownEndsAtTick - (10L - moddedBrushItem.lootr$getBrushingSpeed());
      }

      class_1657 brushingPlayer = this.getBrushingPlayer();
      if (brushingPlayer != null) {
         if (player != brushingPlayer) {
            return false;
         }

         if (!this.hasLootAvailable((class_3222)player)) {
            return false;
         }
      } else {
         if (!this.hasLootAvailable((class_3222)player)) {
            this.brushingPlayer = null;
            this.brushingPlayerEntity = null;
            return false;
         }

         this.brushingPlayerEntity = player;
         this.brushingPlayer = null;
      }

      if (!this.simpleLootrInstance.hasBeenOpened()) {
         this.simpleLootrInstance.setHasBeenOpened();
         this.markChanged();
      }

      if (this.hitDirection == null) {
         this.hitDirection = direction;
      }

      this.brushCountResetsAtTick = l + 40L;
      if (l >= this.coolDownEndsAtTick && this.field_11863 instanceof class_3218) {
         this.coolDownEndsAtTick = l + 10L;
         int i = this.getCompletionState();
         if (++this.brushCount >= 10) {
            this.brushingCompleted(player);
            return true;
         } else {
            this.field_11863.method_39279(this.method_11016(), this.method_11010().method_26204(), 2);
            int j = this.getCompletionState();
            if (i != j) {
               class_2680 blockState = this.method_11010();
               class_2680 blockState2 = (class_2680)blockState.method_11657(class_2741.field_42836, j);
               this.field_11863.method_8652(this.method_11016(), blockState2, 3);
            }

            return false;
         }
      } else {
         return false;
      }
   }

   private class_1799 getBrushItem(class_1657 player) {
      if (player.method_6047().method_7909() instanceof IModdedBrushItem) {
         return player.method_6047();
      } else {
         return player.method_6079().method_7909() instanceof IModdedBrushItem ? player.method_6079() : class_1799.field_8037;
      }
   }

   private void brushingCompleted(class_1657 player) {
      if (this.field_11863 != null && this.field_11863.method_8503() != null) {
         this.dropContent(player);
         this.performTrigger((class_3222)player);
         boolean shouldUpdate = false;
         if (!this.hasServerOpened(player)) {
            player.method_7259(LootrRegistry.getLootedStat());
            LootrRegistry.getStatTrigger().trigger((class_3222)player);
         }

         if (this.addOpener(player)) {
            this.performOpen((class_3222)player);
            shouldUpdate = true;
         }

         if (shouldUpdate) {
            this.performUpdate((class_3222)player);
         }

         if (LootrAPI.isCustomTrapped() && this.isInfoReferenceInventory()) {
            class_2248 block = this.method_11010().method_26204();
            this.field_11863.method_8452(this.method_11016(), block);
            this.field_11863.method_8452(this.method_11016().method_10074(), block);
         }
      }
   }

   private void dropContent(class_1657 player) {
      if (this.field_11863 != null && this.field_11863.method_8503() != null) {
         class_1799 theItem = this.popItem(player);
         if (!theItem.method_7960()) {
            double d = class_1299.field_6052.method_17685();
            double e = 1.0 - d;
            double f = d / 2.0;
            class_2350 direction = Objects.requireNonNullElse(this.hitDirection, class_2350.field_11036);
            class_2338 blockPos = this.field_11867.method_10079(direction, 1);
            double g = blockPos.method_10263() + 0.5 * e + f;
            double h = blockPos.method_10264() + 0.5 + class_1299.field_6052.method_17686() / 2.0F;
            double i = blockPos.method_10260() + 0.5 * e + f;
            class_1542 itemEntity = new class_1542(this.field_11863, g, h, i, theItem.method_7971(this.field_11863.field_9229.method_43048(21) + 10));
            itemEntity.method_18799(class_243.field_1353);
            this.field_11863.method_8649(itemEntity);
            this.item = class_1799.field_8037;
         }
      }
   }

   @Override
   public void IBrushable$checkReset() {
      if (this.field_11863 != null) {
         if (this.brushCount != 0 && this.field_11863.method_8510() >= this.brushCountResetsAtTick) {
            int i = this.getCompletionState();
            this.brushCount = Math.max(0, this.brushCount - 2);
            int j = this.getCompletionState();
            if (i != j) {
               this.field_11863.method_8652(this.method_11016(), (class_2680)this.method_11010().method_11657(class_2741.field_42836, j), 3);
            }

            this.brushCountResetsAtTick = this.field_11863.method_8510() + 4L;
         }

         if (this.brushCount == 0) {
            this.brushingPlayer = null;
            this.brushingPlayerEntity = null;
            this.hitDirection = null;
            this.brushCountResetsAtTick = 0L;
            this.coolDownEndsAtTick = 0L;
         } else {
            this.field_11863.method_39279(this.method_11016(), this.method_11010().method_26204(), 2);
         }
      }
   }

   private void tryLoadLootTable(class_2487 compoundTag) {
      if (compoundTag.method_10545("LootTable")) {
         this.lootTable = class_5321.method_29179(class_7924.field_50079, class_2960.method_60654(compoundTag.method_10558("LootTable")));
      }

      if (compoundTag.method_10545("LootTableSeed")) {
         this.lootTableSeed = compoundTag.method_10537("LootTableSeed");
      }
   }

   private void trySaveLootTable(class_2487 compoundTag) {
      if (this.lootTable != null) {
         compoundTag.method_10582("LootTable", this.lootTable.method_29177().toString());
         if (this.lootTableSeed != 0L) {
            compoundTag.method_10544("LootTableSeed", this.lootTableSeed);
         }
      }
   }

   @Override
   public void setLootTableInternal(class_5321<class_52> lootTable, long seed) {
      this.lootTable = lootTable;
      this.lootTableSeed = seed;
   }

   @Nullable
   public class_1657 getBrushingPlayer() {
      if (this.brushingPlayerEntity != null) {
         if (this.brushingPlayer != null) {
            this.brushingPlayer = null;
         }

         return this.brushingPlayerEntity;
      } else {
         if (this.brushingPlayer != null && this.field_11863 != null) {
            this.brushingPlayerEntity = this.field_11863.method_18470(this.brushingPlayer);
            this.brushingPlayer = null;
         }

         return null;
      }
   }

   public class_2487 method_16887(class_7874 provider) {
      class_2487 compoundTag = super.method_16887(provider);
      if (this.hitDirection != null) {
         compoundTag.method_10569("hit_direction", this.hitDirection.ordinal());
      }

      class_1657 player = this.getBrushingPlayer();
      if (player != null) {
         compoundTag.method_25927("brushing_player", player.method_5667());
         if (this.item.method_7960()) {
            this.item = this.getItem(player);
         }

         if (!this.item.method_7960()) {
            compoundTag.method_10566("item", this.item.method_57358(provider));
         }
      }

      this.simpleLootrInstance.fillUpdateTag(compoundTag, provider, this.field_11863 != null && this.field_11863.method_8608());
      return compoundTag;
   }

   public class_2622 getUpdatePacket() {
      return class_2622.method_39026(this, class_2586::method_16887);
   }

   protected void method_11014(class_2487 compoundTag, class_7874 provider) {
      super.method_11014(compoundTag, provider);
      this.tryLoadLootTable(compoundTag);
      if (compoundTag.method_10545("hit_direction")) {
         this.hitDirection = class_2350.values()[compoundTag.method_10550("hit_direction")];
      }

      if (compoundTag.method_25928("brushing_player")) {
         this.brushingPlayer = compoundTag.method_25926("brushing_player");
      } else {
         this.brushingPlayer = null;
      }

      this.brushingPlayerEntity = null;
      if (this.brushingPlayer != null && compoundTag.method_10545("item")) {
         this.item = class_1799.method_57359(provider, compoundTag.method_10562("item"));
      } else {
         this.item = class_1799.field_8037;
      }

      this.simpleLootrInstance.loadAdditional(compoundTag, provider);
   }

   protected void method_11007(class_2487 compoundTag, class_7874 provider) {
      super.method_11007(compoundTag, provider);
      this.trySaveLootTable(compoundTag);
      this.simpleLootrInstance.saveAdditional(compoundTag, provider, this.field_11863 != null && this.field_11863.method_8608());
   }

   private int getCompletionState() {
      if (this.brushCount == 0) {
         return 0;
      } else if (this.brushCount < 3) {
         return 1;
      } else {
         return this.brushCount < 6 ? 2 : 3;
      }
   }

   @Nullable
   public class_2350 getHitDirection() {
      return this.hitDirection;
   }

   public class_1799 getItem() {
      return this.item;
   }

   public boolean isBrushingPlayer(class_1657 player) {
      class_1657 brushingPlayer = this.getBrushingPlayer();
      return brushingPlayer != null && brushingPlayer == player;
   }

   public class_1799 getItem(class_1657 player) {
      boolean clientSide = player.method_37908().method_8608();
      if (this.isBrushingPlayer(player)) {
         if (clientSide) {
            return this.item;
         }

         if (this.item.method_7960()) {
            ILootrInventory inventory = LootrAPI.getInventory(this, (class_3222)player);
            if (inventory == null) {
               this.item = class_1799.field_8037;
            } else {
               this.item = inventory.method_5438(0);
            }
         }
      } else {
         this.item = class_1799.field_8037;
      }

      return this.item;
   }

   private class_1799 popItem(class_1657 player) {
      if (player.method_37908().method_8608()) {
         return this.getItem(player);
      } else {
         class_1799 theItem = this.getItem(player);
         if (!theItem.method_7960()) {
            ILootrInventory inventory = LootrAPI.getInventory(this, (class_3222)player);
            if (inventory != null) {
               inventory.method_5447(0, class_1799.field_8037);
               inventory.method_5431();
            }

            this.item = class_1799.field_8037;
         }

         return theItem;
      }
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.simpleLootrInstance.getClientOpeners();
   }

   @Override
   public boolean isClientOpened() {
      return this.simpleLootrInstance.isClientOpened();
   }

   @Override
   public void setClientOpened(boolean opened) {
      this.simpleLootrInstance.setClientOpened(opened);
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.CHEST;
   }

   @Override
   public ILootrType getInfoNewType() {
      if (this.method_11010().method_26164(LootrTags.Blocks.SANDS)) {
         return BuiltInLootrTypes.SAND;
      } else if (this.method_11010().method_26164(LootrTags.Blocks.GRAVELS)) {
         return BuiltInLootrTypes.GRAVEL;
      } else {
         LootrAPI.LOG
            .error(
               "Brushable {} at {} in {} is neither sand nor gravel and does not provide ILootrType. Defaulting to 'simple'",
               this.method_11010(),
               this.method_11016(),
               this.getInfoDimension()
            );
         return BuiltInLootrTypes.SIMPLE;
      }
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.simpleLootrInstance.getInfoUUID();
   }

   @Override
   public String getInfoKey() {
      return this.simpleLootrInstance.getInfoKey();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.simpleLootrInstance.hasBeenOpened();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return (Integer)this.method_11010().method_11654(class_2741.field_42836) > 0;
   }

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return this.method_11016();
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return null;
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.field_11863.method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return 1;
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.simpleLootrInstance.getCustomInventory();
   }

   @Override
   public void setInfoReferenceInventory(class_2371<class_1799> reference) {
      this.simpleLootrInstance.setCustomInventory(reference);
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return this.isInfoReferenceInventoryInternal(this.simpleLootrInstance.isCustomInventory());
   }

   @Nullable
   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.lootTable;
   }

   @Override
   public long getInfoLootSeed() {
      return this.lootTableSeed;
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.field_11863;
   }

   public void method_57569(class_2487 compoundTag) {
      super.method_57569(compoundTag);
      compoundTag.method_10551("LootTable");
      compoundTag.method_10551("LootTableSeed");
   }

   protected void method_57568(class_9473 dataComponentInput) {
      super.method_57568(dataComponentInput);
      class_9297 loot = (class_9297)dataComponentInput.method_58694(class_9334.field_49626);
      if (loot != null && loot.comp_2414() != null) {
         this.lootTable = loot.comp_2414();
         this.lootTableSeed = loot.comp_2415();
      }
   }

   protected void method_57567(class_9324 builder) {
      super.method_57567(builder);
      if (this.lootTable != null) {
         builder.method_57840(class_9334.field_49626, new class_9297(this.lootTable, this.lootTableSeed));
      }
   }

   public static void fall(class_3218 level, class_2338 blockPos, class_2680 blockState, LootrBrushableBlockEntity brushableBlockEntity) {
      if (!LootrAPI.canBrushablesSelfSupport()) {
         class_1540 fallingBlockEntity = new class_1540(class_1299.field_6089, level);
         double d = blockPos.method_10263() + 0.5;
         double e = blockPos.method_10264();
         double f = blockPos.method_10260() + 0.5;
         ((AccessorMixinFallingBlockEntity)fallingBlockEntity)
            .lootr$setBlockState(
               blockState.method_28498(class_2741.field_12508) ? (class_2680)blockState.method_11657(class_2741.field_12508, Boolean.FALSE) : blockState
            );
         fallingBlockEntity.field_23807 = true;
         fallingBlockEntity.method_5814(d, e, f);
         fallingBlockEntity.method_18799(class_243.field_1353);
         fallingBlockEntity.field_6014 = d;
         fallingBlockEntity.field_6036 = e;
         fallingBlockEntity.field_5969 = f;
         fallingBlockEntity.field_7194 = brushableBlockEntity.getFallData(level.method_30349());
         fallingBlockEntity.method_6963(fallingBlockEntity.method_24515());
         fallingBlockEntity.field_7193 = false;
         level.method_8652(blockPos, blockState.method_26227().method_15759(), 3);
         level.method_8649(fallingBlockEntity);
      }
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrBrushableBlockEntity> {
      public ILootrBlockEntity apply(LootrBrushableBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getBrushableBlockEntity();
      }
   }
}
