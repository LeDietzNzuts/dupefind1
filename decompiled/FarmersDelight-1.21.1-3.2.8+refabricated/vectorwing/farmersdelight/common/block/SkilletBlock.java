package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_3726;
import net.minecraft.class_3737;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_9062;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;

public class SkilletBlock extends class_2237 implements class_3737 {
   public static final MapCodec<SkilletBlock> CODEC = method_54094(SkilletBlock::new);
   public static final int MINIMUM_COOKING_TIME = 60;
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2746 SUPPORT = class_2746.method_11825("support");
   public static final class_2746 WATERLOGGED = class_2741.field_12508;
   protected static final class_265 SHAPE = class_2248.method_9541(1.0, 0.0, 1.0, 15.0, 4.0, 15.0);
   protected static final class_265 SHAPE_WITH_TRAY = class_259.method_1084(SHAPE, class_2248.method_9541(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));

   public SkilletBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043))
               .method_11657(SUPPORT, false))
            .method_11657(WATERLOGGED, false)
      );
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return CODEC;
   }

   public class_9062 method_55765(class_1799 stack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit) {
      if (level.method_8321(pos) instanceof SkilletBlockEntity skilletEntity) {
         if (!level.field_9236) {
            class_1799 heldStack = player.method_5998(hand);
            class_1304 heldSlot = hand.equals(class_1268.field_5808) ? class_1304.field_6173 : class_1304.field_6171;
            if (heldStack.method_7960()) {
               class_1799 extractedStack = skilletEntity.removeItem();
               if (!player.method_7337()) {
                  player.method_5673(heldSlot, extractedStack);
               }

               return class_9062.field_47728;
            }

            class_1799 remainderStack = skilletEntity.addItemToCook(heldStack, player);
            if (remainderStack.method_7947() != heldStack.method_7947()) {
               if (!player.method_7337()) {
                  player.method_5673(heldSlot, remainderStack);
               }

               level.method_8396(null, pos, class_3417.field_17743, class_3419.field_15245, 0.7F, 1.0F);
               return class_9062.field_47728;
            }
         }

         return class_9062.field_47729;
      } else {
         return class_9062.field_47731;
      }
   }

   public class_2464 method_9604(class_2680 pState) {
      return class_2464.field_11458;
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (state.method_26204() != newState.method_26204()) {
         class_2586 tileEntity = level.method_8321(pos);
         if (tileEntity instanceof SkilletBlockEntity) {
            class_1264.method_5449(
               level, pos.method_10263(), pos.method_10264(), pos.method_10260(), ((SkilletBlockEntity)tileEntity).getInventory().getStackInSlot(0)
            );
         }

         super.method_9536(state, level, pos, newState, isMoving);
      }
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_265 method_9549(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return ((Boolean)state.method_11654(SUPPORT)).equals(true) ? SHAPE_WITH_TRAY : SHAPE;
   }

   public class_2680 method_9605(class_1750 context) {
      class_1937 level = context.method_8045();
      class_3610 fluid = level.method_8316(context.method_8037());
      return (class_2680)((class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_8042()))
            .method_11657(WATERLOGGED, fluid.method_15772() == class_3612.field_15910))
         .method_11657(SUPPORT, this.getTrayState(context.method_8045(), context.method_8037()));
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)state.method_11654(WATERLOGGED)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return facing.method_10166().equals(class_2351.field_11052) ? (class_2680)state.method_11657(SUPPORT, this.getTrayState(level, currentPos)) : state;
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      return level.method_8321(pos) instanceof SkilletBlockEntity skillet ? skillet.getSkilletAsItem() : super.method_9574(level, pos, state);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{FACING, SUPPORT, WATERLOGGED});
   }

   public void method_9496(class_2680 stateIn, class_1937 level, class_2338 pos, class_5819 rand) {
      if (level.method_8321(pos) instanceof SkilletBlockEntity skilletEntity && skilletEntity.isCooking()) {
         double x = pos.method_10263() + 0.5;
         double y = pos.method_10264();
         double z = pos.method_10260() + 0.5;
         if (rand.method_43048(10) == 0) {
            level.method_8486(x, y, z, ModSounds.BLOCK_SKILLET_SIZZLE.get(), class_3419.field_15245, 0.4F, rand.method_43057() * 0.2F + 0.9F, false);
         }
      }
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(WATERLOGGED) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.SKILLET.get().method_11032(pos, state);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 level, class_2680 state, class_2591<T> blockEntity) {
      return level.field_9236
         ? class_2237.method_31618(blockEntity, ModBlockEntityTypes.SKILLET.get(), SkilletBlockEntity::animationTick)
         : class_2237.method_31618(blockEntity, ModBlockEntityTypes.SKILLET.get(), SkilletBlockEntity::cookingTick);
   }

   private boolean getTrayState(class_1936 world, class_2338 pos) {
      return world.method_8320(pos.method_10074()).method_26164(ModTags.TRAY_HEAT_SOURCES);
   }

   public static int getSkilletCookingTime(int originalCookingTime, int fireAspectLevel) {
      int cookingTime = originalCookingTime > 0 ? originalCookingTime : 600;
      int cookingSeconds = cookingTime / 20;
      float cookingTimeReduction = 0.2F;
      if (fireAspectLevel > 0) {
         cookingTimeReduction = (float)(cookingTimeReduction - fireAspectLevel * 0.05);
      }

      int result = (int)(cookingSeconds * cookingTimeReduction) * 20;
      return class_3532.method_15340(result, 60, originalCookingTime);
   }
}
