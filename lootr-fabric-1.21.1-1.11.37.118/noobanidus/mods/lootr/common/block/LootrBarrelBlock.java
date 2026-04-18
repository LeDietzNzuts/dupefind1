package noobanidus.mods.lootr.common.block;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3708;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_4970.class_2251;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrBarrelBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LootrBarrelBlock extends class_3708 {
   public LootrBarrelBlock(class_2251 p_49046_) {
      super(p_49046_);
   }

   public float method_9520() {
      return LootrAPI.getExplosionResistance(this, super.method_9520());
   }

   public void method_9536(class_2680 pState, class_1937 pLevel, class_2338 pPos, class_2680 pNewState, boolean pIsMoving) {
      if (!pState.method_27852(pNewState.method_26204())) {
         class_2586 blockentity = pLevel.method_8321(pPos);
         if (blockentity instanceof LootrBarrelBlockEntity) {
            pLevel.method_8455(pPos, this);
         }

         if (pState.method_31709() && (!pState.method_27852(pNewState.method_26204()) || !pNewState.method_31709())) {
            pLevel.method_8544(pPos);
         }
      }
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 trace) {
      if (!level.method_8608() && !player.method_7325() && player instanceof class_3222 serverPlayer) {
         if (serverPlayer.method_5715()) {
            LootrAPI.handleProviderSneak(ILootrInfoProvider.of(pos, level), serverPlayer);
         } else {
            LootrAPI.handleProviderOpen(ILootrInfoProvider.of(pos, level), serverPlayer);
         }

         return class_1269.field_5812;
      } else {
         return class_1269.field_21466;
      }
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return new LootrBarrelBlockEntity(pos, state);
   }

   public boolean method_9592(class_2680 state, class_1937 world, class_2338 pos, int id, int param) {
      super.method_9592(state, world, pos, id, param);
      class_2586 blockEntity = world.method_8321(pos);
      return blockEntity != null && blockEntity.method_11004(id, param);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 pLevel, class_2680 pState, class_2591<T> pBlockEntityType) {
      return ILootrBlockEntity::ticker;
   }

   public void method_9588(class_2680 pState, class_3218 pLevel, class_2338 pPos, class_5819 pRandom) {
      if (pLevel.method_8321(pPos) instanceof LootrBarrelBlockEntity barrel) {
         barrel.recheckOpen();
      }
   }

   public boolean method_9498(class_2680 pState) {
      return true;
   }

   public float method_9594(class_2680 pBlockState, class_1657 pPlayer, class_1922 pLevel, class_2338 pPos) {
      return LootrAPI.getDestroyProgress(pBlockState, pPlayer, pLevel, pPos, super.method_9594(pBlockState, pPlayer, pLevel, pPos));
   }

   public int method_9572(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos) {
      return LootrAPI.getAnalogOutputSignal(pBlockState, pLevel, pPos, 0);
   }

   public void method_9556(
      class_1937 level, class_1657 player, class_2338 blockPos, class_2680 blockState, @Nullable class_2586 blockEntity, class_1799 itemStack
   ) {
      super.method_9556(level, player, blockPos, blockState, blockEntity, itemStack);
      LootrAPI.playerDestroyed(level, player, blockPos, blockEntity);
   }
}
