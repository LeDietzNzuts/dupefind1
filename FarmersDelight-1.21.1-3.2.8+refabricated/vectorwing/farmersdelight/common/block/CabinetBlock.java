package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1750;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3965;
import net.minecraft.class_5819;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class CabinetBlock extends class_2237 {
   public static final MapCodec<CabinetBlock> CODEC = method_54094(CabinetBlock::new);
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2746 OPEN = class_2741.field_12537;

   public CabinetBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043)).method_11657(OPEN, false)
      );
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return CODEC;
   }

   public class_1269 method_55766(class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit) {
      if (!level.field_9236) {
         class_2586 tile = level.method_8321(pos);
         if (tile instanceof CabinetBlockEntity) {
            player.method_17355((CabinetBlockEntity)tile);
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

   public void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      class_2586 tileEntity = level.method_8321(pos);
      if (tileEntity instanceof CabinetBlockEntity) {
         ((CabinetBlockEntity)tileEntity).recheckOpen();
      }
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10153());
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FACING, OPEN});
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      return class_1703.method_7608(level.method_8321(pos));
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.CABINET.get().method_11032(pos, state);
   }

   public class_2464 method_9604(class_2680 state) {
      return class_2464.field_11458;
   }

   public class_2680 method_9598(class_2680 state, class_2470 rot) {
      return (class_2680)state.method_11657(FACING, rot.method_10503((class_2350)state.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 state, class_2415 mirrorIn) {
      return state.method_26186(mirrorIn.method_10345((class_2350)state.method_11654(FACING)));
   }
}
