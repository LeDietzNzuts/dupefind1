package noobanidus.mods.lootr.common.block;

import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1676;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3481;
import net.minecraft.class_3726;
import net.minecraft.class_3727;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_4838;
import net.minecraft.class_5558;
import net.minecraft.class_5712;
import net.minecraft.class_8168;
import net.minecraft.class_9062;
import net.minecraft.class_4970.class_2251;
import net.minecraft.class_5712.class_7397;
import net.minecraft.class_8172.class_8837;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrDecoratedPotBlockEntity;
import org.jetbrains.annotations.Nullable;

public class LootrDecoratedPotBlock extends class_8168 {
   private static final class_265 BOUNDING_BOX = class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 8.0, 15.0);

   public LootrDecoratedPotBlock(class_2251 properties) {
      super(properties);
   }

   protected void method_9606(class_2680 blockState, class_1937 level, class_2338 blockPos, class_1657 player) {
      if (level.method_8321(blockPos) instanceof LootrDecoratedPotBlockEntity decoratedPotBlockEntity) {
         decoratedPotBlockEntity.wobble(class_8837.field_46665);
         level.method_33596(player, class_5712.field_28733, blockPos);
         if (!level.method_8608()) {
            decoratedPotBlockEntity.dropContent((class_3222)player);
         }
      }
   }

   public class_2680 method_9576(class_1937 level, class_2338 blockPos, class_2680 blockState, class_1657 player) {
      this.method_33614(level, player, blockPos, blockState);
      if (blockState.method_26164(class_3481.field_23800)) {
         class_4838.method_24733(player, false);
      }

      level.method_43276(class_5712.field_28165, blockPos, class_7397.method_43286(player, blockState));
      return blockState;
   }

   protected class_9062 method_55765(
      class_1799 itemStack,
      class_2680 blockState,
      class_1937 level,
      class_2338 blockPos,
      class_1657 player,
      class_1268 interactionHand,
      class_3965 blockHitResult
   ) {
      return class_9062.field_47731;
   }

   protected class_1269 method_55766(class_2680 blockState, class_1937 level, class_2338 blockPos, class_1657 player, class_3965 blockHitResult) {
      if (level.method_8321(blockPos) instanceof LootrDecoratedPotBlockEntity decoratedPotBlockEntity) {
         level.method_8396(null, blockPos, class_3417.field_46650, class_3419.field_15245, 1.0F, 1.0F);
         decoratedPotBlockEntity.wobble(class_8837.field_46665);
         level.method_33596(player, class_5712.field_28733, blockPos);
         if (!level.method_8608()) {
            decoratedPotBlockEntity.dropContent((class_3222)player);
         }

         return class_1269.field_5812;
      } else {
         return class_1269.field_5811;
      }
   }

   protected void method_19286(class_1937 level, class_2680 blockState, class_3965 blockHitResult, class_1676 projectile) {
   }

   public class_1799 method_9574(class_4538 levelReader, class_2338 blockPos, class_2680 blockState) {
      return levelReader.method_8321(blockPos) instanceof LootrDecoratedPotBlockEntity decoratedPotBlockEntity
         ? decoratedPotBlockEntity.getPotAsItem()
         : super.method_9574(levelReader, blockPos, blockState);
   }

   protected class_265 method_9530(class_2680 blockState, class_1922 blockGetter, class_2338 blockPos, class_3726 collisionContext) {
      switch (this.getCollisionState(blockGetter, blockPos, collisionContext)) {
         case PLAYER_OPEN:
         case ITEM_ENTITY:
            return BOUNDING_BOX;
         case PLAYER_CLOSED:
         case OTHER:
            return super.method_9530(blockState, blockGetter, blockPos, collisionContext);
         default:
            return super.method_9530(blockState, blockGetter, blockPos, collisionContext);
      }
   }

   protected class_265 method_9549(class_2680 blockState, class_1922 blockGetter, class_2338 blockPos, class_3726 collisionContext) {
      switch (this.getCollisionState(blockGetter, blockPos, collisionContext)) {
         case PLAYER_OPEN:
         case ITEM_ENTITY:
            return BOUNDING_BOX;
         case PLAYER_CLOSED:
         case OTHER:
            return super.method_9530(blockState, blockGetter, blockPos, collisionContext);
         default:
            return super.method_9530(blockState, blockGetter, blockPos, collisionContext);
      }
   }

   private LootrDecoratedPotBlock.CollisionState getCollisionState(class_1922 getter, class_2338 pos, class_3726 context) {
      if (getter.method_8321(pos) instanceof LootrDecoratedPotBlockEntity potBlockEntity) {
         if (context instanceof class_3727 entityContext) {
            class_1297 entity = entityContext.method_32480();
            if (entity == null) {
               return LootrDecoratedPotBlock.CollisionState.OTHER;
            } else if (entity instanceof class_1542) {
               return LootrDecoratedPotBlock.CollisionState.ITEM_ENTITY;
            } else if (entity instanceof class_1657 player) {
               if (player.method_37908().method_8608()) {
                  return potBlockEntity.hasClientOpened(player)
                     ? LootrDecoratedPotBlock.CollisionState.PLAYER_OPEN
                     : LootrDecoratedPotBlock.CollisionState.PLAYER_CLOSED;
               } else {
                  return potBlockEntity.hasVisualOpened(player)
                     ? LootrDecoratedPotBlock.CollisionState.PLAYER_OPEN
                     : LootrDecoratedPotBlock.CollisionState.PLAYER_CLOSED;
               }
            } else {
               return LootrDecoratedPotBlock.CollisionState.OTHER;
            }
         } else {
            return LootrDecoratedPotBlock.CollisionState.OTHER;
         }
      } else {
         return LootrDecoratedPotBlock.CollisionState.OTHER;
      }
   }

   protected boolean method_9498(class_2680 blockState) {
      return true;
   }

   protected int method_9572(class_2680 blockState, class_1937 level, class_2338 blockPos) {
      return LootrAPI.getAnalogOutputSignal(blockState, level, blockPos, 0);
   }

   @Nullable
   public class_2586 method_10123(class_2338 blockPos, class_2680 blockState) {
      return new LootrDecoratedPotBlockEntity(blockPos, blockState);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 pLevel, class_2680 pState, class_2591<T> pBlockEntityType) {
      return ILootrBlockEntity::ticker;
   }

   static enum CollisionState {
      PLAYER_OPEN,
      PLAYER_CLOSED,
      ITEM_ENTITY,
      OTHER;
   }
}
