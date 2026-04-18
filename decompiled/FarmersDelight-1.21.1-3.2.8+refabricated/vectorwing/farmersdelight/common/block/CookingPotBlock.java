package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.minecraft.class_1264;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2464;
import net.minecraft.class_2586;
import net.minecraft.class_259;
import net.minecraft.class_2591;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2754;
import net.minecraft.class_2769;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
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
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.state.CookingPotSupport;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.refabricated.inventory.ItemStackHandler;

public class CookingPotBlock extends class_2248 implements class_3737, class_2343 {
   public static final MapCodec<CookingPotBlock> CODEC = method_54094(CookingPotBlock::new);
   public static final class_2753 FACING = class_2741.field_12481;
   public static final class_2754<CookingPotSupport> SUPPORT = class_2754.method_11850("support", CookingPotSupport.class);
   public static final class_2746 WATERLOGGED = class_2741.field_12508;
   protected static final class_265 SHAPE = class_2248.method_9541(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);
   protected static final class_265 SHAPE_WITH_TRAY = class_259.method_1084(SHAPE, class_2248.method_9541(0.0, -1.0, 0.0, 16.0, 0.0, 16.0));

   public CookingPotBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043))
               .method_11657(SUPPORT, CookingPotSupport.NONE))
            .method_11657(WATERLOGGED, false)
      );
   }

   protected MapCodec<? extends class_2248> method_53969() {
      return CODEC;
   }

   public class_9062 method_55765(
      class_1799 heldStack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 result
   ) {
      if (heldStack.method_7960() && player.method_5715()) {
         level.method_8501(
            pos,
            (class_2680)state.method_11657(
               SUPPORT,
               ((CookingPotSupport)state.method_11654(SUPPORT)).equals(CookingPotSupport.HANDLE) ? this.getTrayState(level, pos) : CookingPotSupport.HANDLE
            )
         );
         level.method_8396(null, pos, class_3417.field_17743, class_3419.field_15245, 0.7F, 1.0F);
      } else if (!level.field_9236) {
         if (level.method_8321(pos) instanceof CookingPotBlockEntity cookingPotEntity) {
            class_1799 servingStack = cookingPotEntity.useHeldItemOnMeal(heldStack);
            if (servingStack != class_1799.field_8037) {
               if (!player.method_31548().method_7394(servingStack)) {
                  player.method_7328(servingStack, false);
               }

               level.method_8396(null, pos, (class_3414)class_3417.field_14883.comp_349(), class_3419.field_15245, 1.0F, 1.0F);
            } else {
               player.method_17355(cookingPotEntity);
            }
         }

         return class_9062.field_47728;
      }

      return class_9062.field_47728;
   }

   public class_2464 method_9604(class_2680 pState) {
      return class_2464.field_11458;
   }

   public class_265 method_9530(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return SHAPE;
   }

   public class_265 method_9549(class_2680 state, class_1922 level, class_2338 pos, class_3726 context) {
      return ((CookingPotSupport)state.method_11654(SUPPORT)).equals(CookingPotSupport.TRAY) ? SHAPE_WITH_TRAY : SHAPE;
   }

   public class_2680 method_9605(class_1750 context) {
      class_2338 pos = context.method_8037();
      class_1937 level = context.method_8045();
      class_3610 fluid = level.method_8316(context.method_8037());
      class_2680 state = (class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10153()))
         .method_11657(WATERLOGGED, fluid.method_15772() == class_3612.field_15910);
      return context.method_8038().equals(class_2350.field_11033)
         ? (class_2680)state.method_11657(SUPPORT, CookingPotSupport.HANDLE)
         : (class_2680)state.method_11657(SUPPORT, this.getTrayState(level, pos));
   }

   public class_2680 method_9559(class_2680 state, class_2350 facing, class_2680 facingState, class_1936 level, class_2338 currentPos, class_2338 facingPos) {
      if ((Boolean)state.method_11654(WATERLOGGED)) {
         level.method_39281(currentPos, class_3612.field_15910, class_3612.field_15910.method_15789(level));
      }

      return facing.method_10166().equals(class_2351.field_11052) && !((CookingPotSupport)state.method_11654(SUPPORT)).equals(CookingPotSupport.HANDLE)
         ? (class_2680)state.method_11657(SUPPORT, this.getTrayState(level, currentPos))
         : state;
   }

   private CookingPotSupport getTrayState(class_1936 level, class_2338 pos) {
      return level.method_8320(pos.method_10074()).method_26164(ModTags.TRAY_HEAT_SOURCES) ? CookingPotSupport.TRAY : CookingPotSupport.NONE;
   }

   public class_1799 method_9574(class_4538 level, class_2338 pos, class_2680 state) {
      class_1799 stack = super.method_9574(level, pos, state);
      Optional<CookingPotBlockEntity> cookingPot = level.method_35230(pos, ModBlockEntityTypes.COOKING_POT.get());
      if (cookingPot.isPresent()) {
         stack = cookingPot.get().getAsItem();
      }

      return stack;
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (state.method_26204() != newState.method_26204()) {
         if (level.method_8321(pos) instanceof CookingPotBlockEntity cookingPotEntity) {
            class_1264.method_17349(level, pos, cookingPotEntity.getDroppableInventory());
            cookingPotEntity.getUsedRecipesAndPopExperience(level, class_243.method_24953(pos));
            level.method_8455(pos, this);
         }

         super.method_9536(state, level, pos, newState, isMoving);
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{FACING, SUPPORT, WATERLOGGED});
   }

   public void method_9496(class_2680 state, class_1937 level, class_2338 pos, class_5819 random) {
      if (level.method_8321(pos) instanceof CookingPotBlockEntity cookingPotEntity && cookingPotEntity.isHeated()) {
         class_3414 boilSound = !cookingPotEntity.getMeal().method_7960()
            ? ModSounds.BLOCK_COOKING_POT_BOIL_SOUP.get()
            : ModSounds.BLOCK_COOKING_POT_BOIL.get();
         double x = pos.method_10263() + 0.5;
         double y = pos.method_10264();
         double z = pos.method_10260() + 0.5;
         if (random.method_43048(10) == 0) {
            level.method_8486(x, y, z, boilSound, class_3419.field_15245, 0.5F, random.method_43057() * 0.2F + 0.9F, false);
         }
      }
   }

   public boolean method_9498(class_2680 state) {
      return true;
   }

   public int method_9572(class_2680 blockState, class_1937 level, class_2338 pos) {
      class_2586 tileEntity = level.method_8321(pos);
      if (tileEntity instanceof CookingPotBlockEntity) {
         ItemStackHandler inventory = ((CookingPotBlockEntity)tileEntity).getInventory();
         return MathUtils.calcRedstoneFromItemHandler(inventory);
      } else {
         return 0;
      }
   }

   public class_3610 method_9545(class_2680 state) {
      return state.method_11654(WATERLOGGED) ? class_3612.field_15910.method_15729(false) : super.method_9545(state);
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.COOKING_POT.get().method_11032(pos, state);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 level, class_2680 state, class_2591<T> blockEntity) {
      return level.field_9236
         ? class_2237.method_31618(blockEntity, ModBlockEntityTypes.COOKING_POT.get(), CookingPotBlockEntity::animationTick)
         : class_2237.method_31618(blockEntity, ModBlockEntityTypes.COOKING_POT.get(), CookingPotBlockEntity::cookingTick);
   }

   @Nullable
   protected static <E extends class_2586, A extends class_2586> class_5558<A> createTickerHelper(
      class_2591<A> serverType, class_2591<E> clientType, class_5558<? super E> ticker
   ) {
      return clientType == serverType ? ticker : null;
   }
}
