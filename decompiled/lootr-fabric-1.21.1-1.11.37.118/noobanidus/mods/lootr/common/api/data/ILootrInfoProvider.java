package noobanidus.mods.lootr.common.api.data;

import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1693;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_2621;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.IClientOpeners;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.blockentity.RandomizableContainerBlockEntityLootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.entity.AbstractMinecartContainerLootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import org.jetbrains.annotations.Nullable;

public interface ILootrInfoProvider extends ILootrInfo, IClientOpeners {
   static ILootrInfoProvider of(class_2338 pos, class_1937 level) {
      if (level.method_8608()) {
         return null;
      } else {
         class_2586 blockEntity = level.method_8321(pos);
         ILootrBlockEntity var5 = LootrAPI.resolveBlockEntity(blockEntity);
         if (var5 instanceof ILootrInfoProvider) {
            return var5;
         } else {
            return blockEntity instanceof ILootrInfoProvider provider ? provider : null;
         }
      }
   }

   static ILootrInfoProvider of(class_2621 blockEntity, UUID id) {
      ILootrBlockEntity var4 = LootrAPI.resolveBlockEntity(blockEntity);
      if (var4 instanceof ILootrInfoProvider) {
         return var4;
      } else {
         return (ILootrInfoProvider)(blockEntity instanceof ILootrInfoProvider provider
            ? provider
            : new RandomizableContainerBlockEntityLootrInfoProvider(blockEntity, id, ILootrInfo.generateInfoKey(id), null));
      }
   }

   static ILootrInfoProvider of(class_2621 blockEntity, UUID id, class_2371<class_1799> customInventory) {
      ILootrBlockEntity var5 = LootrAPI.resolveBlockEntity(blockEntity);
      if (var5 instanceof ILootrInfoProvider) {
         return var5;
      } else {
         return (ILootrInfoProvider)(blockEntity instanceof ILootrInfoProvider provider
            ? provider
            : new RandomizableContainerBlockEntityLootrInfoProvider(blockEntity, id, ILootrInfo.generateInfoKey(id), customInventory));
      }
   }

   static ILootrInfoProvider of(class_1693 minecart) {
      return (ILootrInfoProvider)(minecart instanceof ILootrInfoProvider provider
         ? provider
         : new AbstractMinecartContainerLootrInfoProvider(minecart, ILootrInfo.generateInfoKey(minecart.method_5667())));
   }

   @Deprecated
   static ILootrInfoProvider of(
      UUID id,
      class_2338 pos,
      int containerSize,
      class_5321<class_52> lootTable,
      long lootSeed,
      class_2561 displayName,
      class_5321<class_1937> dimension,
      class_2371<class_1799> customInventory,
      @Deprecated ILootrInfo.LootrInfoType type,
      @Deprecated LootrBlockType blockType
   ) {
      return new CustomLootrInfoProvider(
         id, ILootrInfo.generateInfoKey(id), pos, containerSize, lootTable, lootSeed, displayName, dimension, customInventory, type, blockType, null
      );
   }

   static ILootrInfoProvider of(
      UUID id,
      class_2338 pos,
      int containerSize,
      class_5321<class_52> lootTable,
      long lootSeed,
      class_2561 displayName,
      class_5321<class_1937> dimension,
      class_2371<class_1799> customInventory,
      ILootrType type
   ) {
      return new CustomLootrInfoProvider(
         id, ILootrInfo.generateInfoKey(id), pos, containerSize, lootTable, lootSeed, displayName, dimension, customInventory, null, null, type
      );
   }

   default int getPhysicalOpenerCount() {
      return -1;
   }

   @Override
   default Set<UUID> getVisualOpeners() {
      ILootrSavedData data = LootrAPI.getData(this);
      return data != null ? data.getVisualOpeners() : null;
   }

   @Override
   default Set<UUID> getActualOpeners() {
      ILootrSavedData data = LootrAPI.getData(this);
      return data != null ? data.getActualOpeners() : null;
   }

   default boolean hasLootAvailable(class_3222 player) {
      ILootrInventory inventory = LootrAPI.getInventory(this, player);
      if (inventory == null) {
         return false;
      } else {
         for (int i = 0; i < inventory.method_5439(); i++) {
            if (!inventory.method_5438(i).method_7960()) {
               return true;
            }
         }

         return false;
      }
   }

   @Nullable
   default IContainerTrigger getTrigger() {
      return null;
   }

   default void performTrigger(class_3222 player) {
      IContainerTrigger trigger = this.getTrigger();
      if (trigger != null && !this.hasServerOpened(player)) {
         trigger.trigger(player, this.getInfoUUID());
      }
   }

   default void performOpen(class_3222 player) {
   }

   default void performOpen() {
   }

   default void performClose(class_3222 player) {
   }

   default void performClose() {
   }

   default void performDecay() {
   }

   default void performRefresh() {
      ILootrSavedData data = LootrAPI.getData(this);
      if (data != null) {
         data.refresh();
         data.clearOpeners();
         NewTickingData.getRefreshData().clearTicking(LootrAPI.getServer(), this.getInfoUUID());
         this.markChanged();
      }
   }

   default void performUpdate(class_3222 player) {
   }

   default void performUpdate() {
   }

   @Override
   default void markDataChanged() {
      ILootrSavedData data = LootrAPI.getData(this);
      if (data != null) {
         data.markChanged();
      }
   }

   default class_243 getParticleCenter() {
      class_2338 pos = this.getInfoPos();
      return new class_243(pos.method_10263(), pos.method_10264(), pos.method_10260());
   }

   default double getParticleYOffset() {
      return 0.95;
   }

   default double[] getParticleXBounds() {
      return new double[]{0.25, 0.75};
   }

   default double[] getParticleZBounds() {
      return new double[]{0.25, 0.75};
   }
}
