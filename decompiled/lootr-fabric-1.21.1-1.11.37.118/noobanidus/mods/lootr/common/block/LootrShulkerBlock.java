package noobanidus.mods.lootr.common.block;

import java.util.List;
import net.minecraft.class_1269;
import net.minecraft.class_1606;
import net.minecraft.class_1657;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2480;
import net.minecraft.class_2561;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_3468;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_4838;
import net.minecraft.class_5558;
import net.minecraft.class_5712;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_2627.class_2628;
import net.minecraft.class_4970.class_2251;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.ILootrInfoProvider;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrShulkerBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LootrShulkerBlock extends class_2480 {
   public LootrShulkerBlock(class_2251 pProperties) {
      super(class_1767.field_7947, pProperties);
      this.method_9590((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(field_11496, class_2350.field_11036));
   }

   private static boolean canOpen(class_2680 pState, class_1937 pLevel, class_2338 pPos, LootrShulkerBlockEntity pBlockEntity) {
      if (pBlockEntity.getAnimationStatus() != class_2628.field_12065) {
         return true;
      } else {
         class_238 aabb = class_1606.method_33347(1.0F, (class_2350)pState.method_11654(field_11496), 0.0F, 0.5F).method_996(pPos).method_1011(1.0E-6);
         return pLevel.method_18026(aabb);
      }
   }

   public float method_9520() {
      return LootrAPI.getExplosionResistance(this, super.method_9520());
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 trace) {
      if (!level.method_8608() && !player.method_7325() && player instanceof class_3222 serverPlayer) {
         if (level.method_8321(pos) instanceof LootrShulkerBlockEntity shulkerboxblockentity) {
            if (!canOpen(state, level, pos, shulkerboxblockentity)) {
               return class_1269.field_5811;
            } else {
               if (serverPlayer.method_5715()) {
                  LootrAPI.handleProviderSneak(ILootrInfoProvider.of(pos, level), serverPlayer);
               } else {
                  LootrAPI.handleProviderOpen(ILootrInfoProvider.of(pos, level), serverPlayer);
                  player.method_7281(class_3468.field_15418);
               }

               return class_1269.field_5812;
            }
         } else {
            return class_1269.field_5811;
         }
      } else {
         return class_1269.field_21466;
      }
   }

   public class_2680 method_9576(class_1937 pLevel, class_2338 pPos, class_2680 pState, class_1657 pPlayer) {
      this.method_33614(pLevel, pPlayer, pPos, pState);
      if (pState.method_26164(class_3481.field_23800)) {
         class_4838.method_24733(pPlayer, false);
      }

      pLevel.method_33596(pPlayer, class_5712.field_28165, pPos);
      return pState;
   }

   public void method_9536(class_2680 pState, class_1937 pLevel, class_2338 pPos, class_2680 pNewState, boolean pIsMoving) {
      if (!pState.method_27852(pNewState.method_26204())) {
         class_2586 blockentity = pLevel.method_8321(pPos);
         if (blockentity instanceof LootrShulkerBlockEntity) {
            pLevel.method_8455(pPos, pState.method_26204());
         }

         if (pState.method_31709() && (!pState.method_27852(pNewState.method_26204()) || !pNewState.method_31709())) {
            pLevel.method_8544(pPos);
         }
      }
   }

   public void method_9568(class_1799 p_56193_, class_9635 p_339693_, List<class_2561> p_56195_, class_1836 p_56196_) {
   }

   public class_265 method_9530(class_2680 pState, class_1922 pLevel, class_2338 pPos, class_3726 pContext) {
      class_2586 blockentity = pLevel.method_8321(pPos);
      return blockentity instanceof LootrShulkerBlockEntity
         ? class_259.method_1078(((LootrShulkerBlockEntity)blockentity).getBoundingBox(pState))
         : class_259.method_1077();
   }

   public boolean method_9498(class_2680 pState) {
      return true;
   }

   @Nullable
   public class_1767 method_10528() {
      return class_1767.field_7947;
   }

   public float method_9594(class_2680 p_60466_, class_1657 p_60467_, class_1922 p_60468_, class_2338 p_60469_) {
      return LootrAPI.getDestroyProgress(p_60466_, p_60467_, p_60468_, p_60469_, super.method_9594(p_60466_, p_60467_, p_60468_, p_60469_));
   }

   public int method_9572(class_2680 pBlockState, class_1937 pLevel, class_2338 pPos) {
      return LootrAPI.getAnalogOutputSignal(pBlockState, pLevel, pPos, 0);
   }

   public class_2586 method_10123(class_2338 pPos, class_2680 pState) {
      return new LootrShulkerBlockEntity(pPos, pState);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 pLevel, class_2680 pState, class_2591<T> pBlockEntityType) {
      return ILootrBlockEntity::ticker;
   }

   public void method_9556(
      class_1937 level, class_1657 player, class_2338 blockPos, class_2680 blockState, @Nullable class_2586 blockEntity, class_1799 itemStack
   ) {
      super.method_9556(level, player, blockPos, blockState, blockEntity, itemStack);
      LootrAPI.playerDestroyed(level, player, blockPos, blockEntity);
   }
}
