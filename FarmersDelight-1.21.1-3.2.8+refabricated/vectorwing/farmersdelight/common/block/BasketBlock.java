package vectorwing.farmersdelight.common.block;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.minecraft.class_10;
import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1750;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_247;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3737;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.BasketBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class BasketBlock extends class_2237 implements class_3737 {
   public static final MapCodec<BasketBlock> CODEC = method_54094(BasketBlock::new);
   public static final class_2753 FACING = class_2741.field_12525;
   public static final class_2746 ENABLED = class_2741.field_12515;
   public static final class_2746 WATERLOGGED = class_2741.field_12508;
   public static final class_265 OUT_SHAPE = class_259.method_1077();
   public static final class_265 RENDER_SHAPE = class_2248.method_9541(1.0, 1.0, 1.0, 15.0, 15.0, 15.0);
   public static final ImmutableMap<class_2350, class_265> COLLISION_SHAPE_FACING = Maps.immutableEnumMap(
      ImmutableMap.builder()
         .put(class_2350.field_11033, makeHollowCubeShape(class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 14.0, 14.0)))
         .put(class_2350.field_11036, makeHollowCubeShape(class_2248.method_9541(2.0, 2.0, 2.0, 14.0, 16.0, 14.0)))
         .put(class_2350.field_11043, makeHollowCubeShape(class_2248.method_9541(2.0, 2.0, 0.0, 14.0, 14.0, 14.0)))
         .put(class_2350.field_11035, makeHollowCubeShape(class_2248.method_9541(2.0, 2.0, 2.0, 14.0, 14.0, 16.0)))
         .put(class_2350.field_11039, makeHollowCubeShape(class_2248.method_9541(0.0, 2.0, 2.0, 14.0, 14.0, 14.0)))
         .put(class_2350.field_11034, makeHollowCubeShape(class_2248.method_9541(2.0, 2.0, 2.0, 16.0, 14.0, 14.0)))
         .build()
   );

   private static class_265 makeHollowCubeShape(class_265 cutout) {
      return class_259.method_1082(OUT_SHAPE, cutout, class_247.field_16886).method_1097();
   }

   public BasketBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.method_9595().method_11664()).method_11657(FACING, class_2350.field_11036))
            .method_11657(WATERLOGGED, false)
      );
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return CODEC;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return (class_265)COLLISION_SHAPE_FACING.get(state.method_11654(FACING));
   }

   public class_265 method_9571(class_2680 state, class_1922 level, class_2338 pos) {
      return RENDER_SHAPE;
   }

   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, ENABLED, WATERLOGGED});
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit) {
      if (!level.field_9236) {
         class_2586 tileEntity = level.method_8321(pos);
         if (tileEntity instanceof BasketBlockEntity) {
            player.method_17355((BasketBlockEntity)tileEntity);
         }
      }

      return class_1269.field_5812;
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (state.method_26204() != newState.method_26204()) {
         class_2586 tileEntity = level.method_8321(pos);
         if (tileEntity instanceof class_1263) {
            class_1264.method_5451(level, pos, (class_1263)tileEntity);
            level.method_8455(pos, this);
         }

         super.method_9536(state, level, pos, newState, isMoving);
      }
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)state.method_11654(WATERLOGGED)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return super.method_9559(state, facing, facingState, level, currentPos, facingPos);
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(WATERLOGGED) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   public void method_9612(class_2680 state, class_1937 level, class_2338 pos, class_2248 block, class_2338 fromPos, boolean isMoving) {
      boolean isPowered = !level.method_49803(pos);
      if (isPowered != (Boolean)state.method_11654(ENABLED)) {
         level.method_8652(pos, (class_2680)state.method_11657(ENABLED, isPowered), 4);
      }
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 state, class_1937 level, class_2338 pos) {
      return class_1703.method_7608(level.method_8321(pos));
   }

   public class_2680 method_9605(class_1750 context) {
      class_3610 fluid = context.method_8045().method_8316(context.method_8037());
      return (class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_7715().method_10153()))
         .method_11657(WATERLOGGED, fluid.method_15772() == class_3612.field_15910);
   }

   public class_2680 method_9598(class_2680 state, class_2470 rotation) {
      return (class_2680)state.method_11657(FACING, rotation.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 state, class_2415 mirror) {
      return state.method_26186(mirror.method_10345((class_2350)state.method_11654(FACING)));
   }

   public boolean method_9526(class_2680 state) {
      return true;
   }

   public class_265 method_9584(class_2680 state, class_1922 level, class_2338 pos) {
      return OUT_SHAPE;
   }

   protected boolean method_9516(class_2680 state, class_10 type) {
      return false;
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return new BasketBlockEntity(pos, state);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 level, class_2680 state, class_2591<T> blockEntityType) {
      return level.field_9236 ? null : class_2237.method_31618(blockEntityType, ModBlockEntityTypes.BASKET.get(), BasketBlockEntity::pushItemsTick);
   }
}
