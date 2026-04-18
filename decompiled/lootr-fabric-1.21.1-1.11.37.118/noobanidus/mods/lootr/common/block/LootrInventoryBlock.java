package noobanidus.mods.lootr.common.block;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3908;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_4970.class_2251;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.block.entity.LootrChestBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrInventoryBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LootrInventoryBlock extends class_2281 {
   public LootrInventoryBlock(class_2251 properties) {
      super(properties, LootrRegistry::getInventoryBlockEntity);
   }

   public float method_9520() {
      return LootrAPI.getExplosionResistance(this, super.method_9520());
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 trace) {
      if (!level.method_8608() && !player.method_7325() && player instanceof class_3222 serverPlayer) {
         if (serverPlayer.method_5715()) {
            LootrAPI.handleProviderSneak(ILootrInfoProvider.of(pos, level), serverPlayer);
         } else if (!method_9756(level, pos)) {
            LootrAPI.handleProviderOpen(ILootrInfoProvider.of(pos, level), serverPlayer);
         }

         return class_1269.field_5812;
      } else {
         return class_1269.field_21466;
      }
   }

   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return new LootrInventoryBlockEntity(pos, state);
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 worldIn, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)stateIn.method_11654(field_10772)) {
         worldIn.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(worldIn));
      }

      return stateIn;
   }

   public class_265 method_9530(class_2680 state, class_1922 worldIn, class_2338 pos, class_3726 context) {
      return field_10774;
   }

   public class_2680 method_9605(class_1750 context) {
      class_2350 direction = context.method_8042().method_10153();
      class_3610 fluidstate = context.method_8045().method_8316(context.method_8037());
      return (class_2680)((class_2680)((class_2680)this.method_9564().method_11657(field_10768, direction)).method_11657(field_10770, class_2745.field_12569))
         .method_11657(field_10772, fluidstate.method_15772() == class_3612.field_15910);
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(field_10772) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   @Nullable
   public class_3908 method_17454(class_2680 state, class_1937 worldIn, class_2338 pos) {
      return null;
   }

   public boolean method_9498(class_2680 pState) {
      return true;
   }

   public float method_9594(class_2680 p_60466_, class_1657 p_60467_, class_1922 p_60468_, class_2338 p_60469_) {
      return LootrAPI.getDestroyProgress(p_60466_, p_60467_, p_60468_, p_60469_, super.method_9594(p_60466_, p_60467_, p_60468_, p_60469_));
   }

   public int method_9572(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos) {
      return LootrAPI.getAnalogOutputSignal(pBlockState, pLevel, pPos, 0);
   }

   public boolean method_9506(class_2680 pState) {
      return LootrAPI.isCustomTrapped();
   }

   public int method_9524(class_2680 pBlockState, class_1922 pBlockAccess, class_2338 pPos, class_2350 pSide) {
      return LootrAPI.isCustomTrapped() ? class_3532.method_15340(LootrChestBlockEntity.getOpenCount(pBlockAccess, pPos), 0, 15) : 0;
   }

   public int method_9603(class_2680 pBlockState, class_1922 pBlockAccess, class_2338 pPos, class_2350 pSide) {
      if (LootrAPI.isCustomTrapped()) {
         return pSide == class_2350.field_11036 ? pBlockState.method_26195(pBlockAccess, pPos, pSide) : 0;
      } else {
         return 0;
      }
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 pLevel, class_2680 pState, class_2591<T> pBlockEntityType) {
      return ILootrBlockEntity::ticker;
   }

   public void method_9588(class_2680 pState, class_3218 pLevel, class_2338 pPos, class_5819 pRandom) {
      if (pLevel.method_8321(pPos) instanceof LootrInventoryBlockEntity inventory) {
         inventory.method_31671();
      }
   }

   public void method_9556(
      class_1937 level, class_1657 player, class_2338 blockPos, class_2680 blockState, @Nullable class_2586 blockEntity, class_1799 itemStack
   ) {
      super.method_9556(level, player, blockPos, blockState, blockEntity, itemStack);
      LootrAPI.playerDestroyed(level, player, blockPos, blockEntity);
   }
}
