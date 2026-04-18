package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1820;
import net.minecraft.class_1831;
import net.minecraft.class_1835;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2392;
import net.minecraft.class_2398;
import net.minecraft.class_2415;
import net.minecraft.class_243;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3737;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_9062;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.tag.ModTags;

public class CuttingBoardBlock extends class_2237 implements class_3737 {
   public static final MapCodec<CuttingBoardBlock> CODEC = method_54094(CuttingBoardBlock::new);
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2746 WATERLOGGED = class_2741.field_12508;
   protected static final class_265 SHAPE = class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);

   public CuttingBoardBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(FACING, class_2350.field_11043))
            .method_11657(WATERLOGGED, false)
      );
   }

   public static void init() {
      UseBlockCallback.EVENT.register(CuttingBoardBlock.ToolCarvingEvent::onSneakPlaceTool);
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return null;
   }

   public class_2464 method_9604(class_2680 pState) {
      return class_2464.field_11458;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      if (level.method_8321(pos) instanceof CuttingBoardBlockEntity cuttingBoardEntity) {
         class_1799 heldStack = player.method_5998(hand);
         class_1799 offhandStack = player.method_6079();
         if (cuttingBoardEntity.isEmpty()) {
            if (!offhandStack.method_7960()) {
               if (hand.equals(class_1268.field_5808)
                  && !offhandStack.method_31573(ModTags.OFFHAND_EQUIPMENT)
                  && !(heldStack.method_7909() instanceof class_1747)) {
                  return class_9062.field_47731;
               }

               if (hand.equals(class_1268.field_5810) && offhandStack.method_31573(ModTags.OFFHAND_EQUIPMENT)) {
                  return class_9062.field_47731;
               }
            }

            if (heldStack.method_7960()) {
               return class_9062.field_47731;
            }

            if (cuttingBoardEntity.addItem(player.method_31549().field_7477 ? heldStack.method_7972() : heldStack)) {
               class_243 centerPos = pos.method_46558();
               level.method_43128(
                  null,
                  centerPos.method_10216(),
                  centerPos.method_10214(),
                  centerPos.method_10215(),
                  class_3417.field_14718,
                  class_3419.field_15245,
                  1.0F,
                  0.8F
               );
               return class_9062.field_47728;
            }
         } else {
            if (!heldStack.method_7960()) {
               class_1799 boardStack = cuttingBoardEntity.getStoredItem().method_7972();
               if (cuttingBoardEntity.processStoredItemUsingTool(heldStack, player)) {
                  spawnCuttingParticles(level, pos, boardStack, 5);
                  return class_9062.field_47728;
               }

               return class_9062.field_47729;
            }

            if (hand.equals(class_1268.field_5808)) {
               if (!player.method_7337()) {
                  if (!player.method_31548().method_7394(cuttingBoardEntity.removeItem())) {
                     class_1264.method_5449(level, pos.method_10263(), pos.method_10264(), pos.method_10260(), cuttingBoardEntity.removeItem());
                  }
               } else {
                  cuttingBoardEntity.removeItem();
               }

               class_243 centerPos = pos.method_46558();
               level.method_43128(
                  null,
                  centerPos.method_10216(),
                  centerPos.method_10214(),
                  centerPos.method_10215(),
                  class_3417.field_14808,
                  class_3419.field_15245,
                  0.25F,
                  0.5F
               );
               return class_9062.field_47728;
            }
         }
      }

      return class_9062.field_47731;
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (state.method_26204() != newState.method_26204()) {
         if (level.method_8321(pos) instanceof CuttingBoardBlockEntity cuttingBoard) {
            class_1264.method_5449(level, pos.method_10263(), pos.method_10264(), pos.method_10260(), cuttingBoard.getStoredItem());
            level.method_8455(pos, this);
         }

         super.method_9536(state, level, pos, newState, isMoving);
      }
   }

   public boolean method_9538(class_2680 state) {
      return true;
   }

   public class_2680 method_9605(class_1750 context) {
      class_3610 fluid = context.method_8045().method_8316(context.method_8037());
      return (class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10153()))
         .method_11657(WATERLOGGED, fluid.method_15772() == class_3612.field_15910);
   }

   public class_2680 method_9559(class_2680 stateIn, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)stateIn.method_11654(WATERLOGGED)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return facing == class_2350.field_11033 && !stateIn.method_26184(level, currentPos)
         ? class_2246.field_10124.method_9564()
         : super.method_9559(stateIn, facing, facingState, level, currentPos, facingPos);
   }

   public boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_2338 floorPos = pos.method_10074();
      return class_2248.method_16361(level, floorPos) || class_2248.method_20044(level, floorPos, class_2350.field_11036);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FACING, WATERLOGGED});
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(WATERLOGGED) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 state, class_1937 level, class_2338 pos) {
      class_2586 blockEntity = level.method_8321(pos);
      if (blockEntity instanceof CuttingBoardBlockEntity) {
         return !((CuttingBoardBlockEntity)blockEntity).isEmpty() ? 15 : 0;
      } else {
         return 0;
      }
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.CUTTING_BOARD.get().method_11032(pos, state);
   }

   public class_2680 method_9598(class_2680 pState, class_2470 pRot) {
      return (class_2680)pState.method_11657(FACING, pRot.method_10503((class_2350)pState.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 pState, class_2415 pMirror) {
      return pState.method_26186(pMirror.method_10345((class_2350)pState.method_11654(FACING)));
   }

   public static void spawnCuttingParticles(class_1937 level, class_2338 pos, class_1799 stack, int count) {
      for (int i = 0; i < count; i++) {
         class_243 vec3d = new class_243(
            (level.field_9229.method_43057() - 0.5) * 0.1, Math.random() * 0.1 + 0.1, (level.field_9229.method_43057() - 0.5) * 0.1
         );
         if (level instanceof class_3218) {
            ((class_3218)level)
               .method_14199(
                  new class_2392(class_2398.field_11218, stack),
                  pos.method_10263() + 0.5F,
                  pos.method_10264() + 0.1F,
                  pos.method_10260() + 0.5F,
                  1,
                  vec3d.field_1352,
                  vec3d.field_1351 + 0.05,
                  vec3d.field_1350,
                  0.0
               );
         } else {
            level.method_8406(
               new class_2392(class_2398.field_11218, stack),
               pos.method_10263() + 0.5F,
               pos.method_10264() + 0.1F,
               pos.method_10260() + 0.5F,
               vec3d.field_1352,
               vec3d.field_1351 + 0.05,
               vec3d.field_1350
            );
         }
      }
   }

   public static class ToolCarvingEvent {
      public static class_1269 onSneakPlaceTool(class_1657 player, class_1937 level, class_1268 hand, class_3965 hit) {
         if (player.method_7325()) {
            return class_1269.field_5811;
         } else {
            class_2338 pos = hit.method_17777();
            class_1799 heldStack = player.method_6047();
            class_2586 tileEntity = level.method_8321(pos);
            if (player.method_21823()
               && !heldStack.method_7960()
               && tileEntity instanceof CuttingBoardBlockEntity
               && (
                  heldStack.method_7909() instanceof class_1831
                     || heldStack.method_7909() instanceof class_1835
                     || heldStack.method_7909() instanceof class_1820
               )) {
               boolean success = ((CuttingBoardBlockEntity)tileEntity).carveToolOnBoard(player.method_31549().field_7477 ? heldStack.method_7972() : heldStack);
               if (success) {
                  class_243 centerPos = pos.method_46558();
                  level.method_43128(
                     null,
                     centerPos.method_10216(),
                     centerPos.method_10214(),
                     centerPos.method_10215(),
                     class_3417.field_14718,
                     class_3419.field_15245,
                     1.0F,
                     0.8F
                  );
                  return class_1269.field_5812;
               }
            }

            return class_1269.field_5811;
         }
      }
   }
}
