package noobanidus.mods.lootr.common.api.data.blockentity;

import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2624;
import net.minecraft.class_2680;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8934;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.replacement.BlockReplacementMap;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface ILootrBlockEntity extends ILootrInfoProvider {
   static <T extends class_2586> void ticker(class_1937 level, class_2338 pos, class_2680 state, T blockEntity) {
      ILootrBlockEntity var5 = LootrAPI.resolveBlockEntity(blockEntity);
      if (var5 instanceof ILootrBlockEntity && (level.method_8608() || var5.hasLootTable())) {
         var5.defaultTick(level, pos, state);
      }
   }

   default boolean hasLootTable() {
      return this.getInfoLootTable() != null;
   }

   default void defaultTick(class_1937 level, class_2338 pos, class_2680 state) {
      if (!level.method_8608()) {
         LootrAPI.handleProviderTick(this);
      } else {
         LootrAPI.handleProviderClientTick(this);
      }
   }

   default class_2586 asBlockEntity() {
      if (this instanceof class_2586 entity) {
         return entity;
      } else {
         throw new NullPointerException("ILootrBlockEntity implementation is not a BlockEntity and doesn't provide asBlockEntity()!");
      }
   }

   @Deprecated
   @Override
   default ILootrInfo.LootrInfoType getInfoType() {
      return ILootrInfo.LootrInfoType.CONTAINER_BLOCK_ENTITY;
   }

   default void updatePacketViaForce() {
      this.updatePacketViaForce(this.asBlockEntity());
   }

   default void updatePacketViaForce(class_2586 entity) {
      if (entity.method_10997() instanceof class_3218 level) {
         class_2596<?> packet = entity.method_38235();
         if (packet != null) {
            level.method_14178()
               .field_17254
               .method_17210(new class_1923(entity.method_11016()), false)
               .forEach(player -> player.field_13987.method_14364(packet));
         }
      }
   }

   @Override
   default void performOpen(class_3222 player) {
      PlatformAPI.performBlockOpen(this, player);
   }

   @Override
   default void performOpen() {
      PlatformAPI.performBlockOpen(this);
   }

   @Override
   default void performClose(class_3222 player) {
      PlatformAPI.performBlockClose(this, player);
   }

   @Override
   default void performClose() {
      PlatformAPI.performBlockClose(this);
   }

   @Override
   default void performDecay() {
      class_1937 level = this.getInfoLevel();
      if (level != null && !level.method_8608()) {
         class_2680 stateAt = level.method_8320(this.getInfoPos());
         boolean replaceWhenDecayed = LootrAPI.shouldReplaceWhenDecayed();
         level.method_22352(this.getInfoPos(), !replaceWhenDecayed);
         if (replaceWhenDecayed) {
            class_2248 replacementBlock = this.getReplacementBlock();
            if (replacementBlock != null) {
               class_2680 replacementState = replacementBlock.method_9564();

               for (class_2769<?> prop : replacementState.method_28501()) {
                  if (stateAt.method_28498(prop)) {
                     replacementState = BlockReplacementMap.safeReplace(replacementState, stateAt, prop);
                  }
               }

               level.method_8652(this.getInfoPos(), replacementState, 2);
            }
         }
      }
   }

   @Override
   default void performUpdate(class_3222 player) {
      this.performUpdate();
   }

   @Override
   default void performUpdate() {
      this.markChanged();
      this.updatePacketViaForce();
   }

   @Internal
   default void setLootTableInternal(class_5321<class_52> lootTable, long seed) {
      if (this instanceof class_8934 container) {
         container.method_54867(lootTable, seed);
      } else {
         throw new NotImplementedException("setLootTableInternal called on ILootrBlockEntity that is not a RandomizableContainer without overriding!");
      }
   }

   @Override
   default boolean canPlayerOpen(class_3222 player) {
      if (this instanceof class_2624 bce) {
         LockMessageSuppression.setSuppressableLock(true);
         boolean result = bce.method_17489(player);
         LockMessageSuppression.setSuppressableLock(false);
         return result;
      } else {
         return true;
      }
   }

   @Override
   default void informPlayerCannotOpen(class_3222 player) {
      if (this instanceof class_2624 bce) {
         LockMessageSuppression.setSuppressableLock(false);
         bce.method_17489(player);
      }
   }
}
