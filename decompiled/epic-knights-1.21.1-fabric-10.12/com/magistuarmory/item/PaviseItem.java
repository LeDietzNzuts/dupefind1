package com.magistuarmory.item;

import com.magistuarmory.block.ModBlocks;
import com.magistuarmory.block.PaviseBlock;
import com.magistuarmory.block.PaviseBlockEntity;
import com.magistuarmory.block.PaviseUpperCollisionBlock;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_124;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_174;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_1838;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2498;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_3726;
import net.minecraft.class_4538;
import net.minecraft.class_5712;
import net.minecraft.class_7699;
import net.minecraft.class_9275;
import net.minecraft.class_9334;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.minecraft.class_5712.class_7397;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PaviseItem extends MedievalShieldItem {
   private final class_2350 attachmentDirection = class_2350.field_11033;
   private final Supplier<PaviseBlock> block;

   public PaviseItem(
      String id,
      class_2960 location,
      class_1793 properties,
      ModItemTier material,
      boolean paintable,
      boolean is3d,
      ShieldType type,
      Supplier<PaviseBlock> block
   ) {
      super(id, location, properties, material, paintable, is3d, type);
      this.block = block;
   }

   @NotNull
   public class_1269 method_7884(class_1838 context) {
      class_1269 interactionResult = this.place(new class_1750(context));
      if (!interactionResult.method_23665() && context.method_8041().method_57826(class_9334.field_50075)) {
         class_1269 interactionResult2 = super.method_7836(context.method_8045(), context.method_8036(), context.method_20287()).method_5467();
         return interactionResult2 == class_1269.field_21466 ? class_1269.field_33562 : interactionResult2;
      } else {
         return interactionResult;
      }
   }

   public class_1269 place(class_1750 context) {
      if (!this.getBlock().method_45382(context.method_8045().method_45162())) {
         return class_1269.field_5814;
      } else if (!context.method_7716()) {
         return class_1269.field_5814;
      } else {
         class_1750 context2 = this.updatePlacementContext(context);
         if (context2 == null) {
            return class_1269.field_5814;
         } else {
            class_2680 blockstate = this.getPlacementState(context2);
            if (blockstate == null) {
               return class_1269.field_5814;
            } else if (!this.placeBlock(context2, blockstate)) {
               return class_1269.field_5814;
            } else {
               class_2338 blockpos = context2.method_8037();
               class_1937 level = context2.method_8045();
               class_1657 player = context2.method_8036();
               class_1799 stack = context2.method_8041();
               class_2680 blockstate2 = level.method_8320(blockpos);
               if (blockstate2.method_27852(blockstate.method_26204())) {
                  blockstate2 = this.updateBlockStateFromTag(blockpos, level, stack, blockstate2);
                  if (level.method_8321(blockpos) instanceof PaviseBlockEntity paviseblockentity) {
                     paviseblockentity.fromItem(stack);
                  }

                  blockstate2.method_26204().method_9567(level, blockpos, blockstate2, player, stack);
                  if (player instanceof class_3222) {
                     class_174.field_1191.method_23889((class_3222)player, blockpos, stack);
                  }
               }

               class_2498 sound = blockstate2.method_26231();
               level.method_8396(
                  player, blockpos, this.getPlaceSound(blockstate2), class_3419.field_15245, (sound.method_10597() + 1.0F) / 2.0F, sound.method_10599() * 0.8F
               );
               level.method_43276(class_5712.field_28164, blockpos, class_7397.method_43286(player, blockstate2));
               if (player == null || !player.method_31549().field_7477) {
                  stack.method_7934(1);
               }

               return class_1269.method_29236(level.field_9236);
            }
         }
      }
   }

   protected class_3414 getPlaceSound(class_2680 blockstate) {
      return blockstate.method_26231().method_10598();
   }

   @Nullable
   public class_1750 updatePlacementContext(class_1750 context) {
      return context;
   }

   private class_2680 updateBlockStateFromTag(class_2338 blockpos, class_1937 level, class_1799 stack, class_2680 blockstate) {
      class_9275 blockItemStateProperties = (class_9275)stack.method_57825(class_9334.field_49623, class_9275.field_49284);
      if (blockItemStateProperties.method_57414()) {
         return blockstate;
      } else {
         class_2680 blockstate2 = blockItemStateProperties.method_57415(blockstate);
         if (blockstate2 != blockstate) {
            level.method_8652(blockpos, blockstate2, 2);
         }

         return blockstate2;
      }
   }

   protected boolean placeBlock(class_1750 context, class_2680 blockstate) {
      class_1937 level = context.method_8045();
      class_2338 aboveblockpos = context.method_8037().method_10084();
      if (level.method_16358(aboveblockpos, state -> state.method_26204() != class_2246.field_10124) && !level.method_22351(aboveblockpos)) {
         return false;
      } else if (!context.method_8045().method_8652(context.method_8037(), blockstate, 11)) {
         return false;
      } else {
         level.method_8652(aboveblockpos, ((PaviseUpperCollisionBlock)ModBlocks.PAVISE_UPPER_COLLISION.get()).method_9564(), 27);
         return true;
      }
   }

   public String method_7876() {
      return this.getBlock().method_9539();
   }

   @Override
   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> list, class_1836 tooltipflag) {
      super.method_7851(stack, tooltipContext, list, tooltipflag);
      list.add(class_2561.method_43471("canbeplacedonground").method_27692(class_124.field_1078));
   }

   public PaviseBlock getBlock() {
      return this.block.get();
   }

   @NotNull
   public class_7699 method_45322() {
      return this.getBlock().method_45322();
   }

   protected boolean canPlace(class_4538 levelreader, class_2680 blockstate, class_2338 blockpos) {
      return blockstate.method_26184(levelreader, blockpos);
   }

   @Nullable
   protected class_2680 getPlacementState(class_1750 context) {
      class_2680 blockstate2 = null;
      class_4538 levelreader = context.method_8045();
      class_2338 blockpos = context.method_8037();
      class_2350[] var6 = context.method_7718();

      for (class_2350 direction : var6) {
         if (direction != this.attachmentDirection.method_10153()) {
            class_2680 blockstate3 = this.getBlock().method_9605(context);
            if (blockstate3 != null && this.canPlace(levelreader, blockstate3, blockpos)) {
               blockstate2 = blockstate3;
               break;
            }
         }
      }

      return blockstate2 != null && levelreader.method_8628(blockstate2, blockpos, class_3726.method_16194()) ? blockstate2 : null;
   }
}
