package vectorwing.farmersdelight.common.block;

import com.mojang.serialization.MapCodec;
import java.util.Optional;
import net.fabricmc.fabric.api.registry.LandPathNodeTypesRegistry;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1750;
import net.minecraft.class_1778;
import net.minecraft.class_1786;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2237;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2383;
import net.minecraft.class_2398;
import net.minecraft.class_2415;
import net.minecraft.class_2464;
import net.minecraft.class_2470;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2746;
import net.minecraft.class_2753;
import net.minecraft.class_2769;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3920;
import net.minecraft.class_3922;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_7;
import net.minecraft.class_8786;
import net.minecraft.class_9062;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.ItemUtils;
import vectorwing.farmersdelight.common.utility.MathUtils;
import vectorwing.farmersdelight.refabricated.ItemAbility;

public class StoveBlock extends class_2237 {
   public static final MapCodec<StoveBlock> CODEC = method_54094(StoveBlock::new);
   public static final class_2746 LIT = class_2741.field_12548;
   public static final class_2753 FACING = class_2741.field_12481;

   public StoveBlock(class_2251 properties) {
      super(properties);
      this.method_9590(
         (class_2680)((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(FACING, class_2350.field_11043)).method_11657(LIT, false)
      );
      LandPathNodeTypesRegistry.registerDynamic(this, (state, world, pos, neighbor) -> this.getBlockPathType(state, world, pos));
   }

   protected MapCodec<? extends class_2237> method_53969() {
      return CODEC;
   }

   protected class_9062 method_55765(
      class_1799 heldStack, class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_1268 hand, class_3965 hit
   ) {
      class_1792 heldItem = heldStack.method_7909();
      if ((Boolean)state.method_11654(LIT)) {
         if (ItemAbility.SHOVEL_DIG.canPerformAction(heldStack)) {
            this.extinguish(state, level, pos);
            heldStack.method_7970(1, player, class_1309.method_56079(hand));
            return class_9062.field_47728;
         }

         if (heldStack.method_31573(ConventionalItemTags.WATER_BUCKETS)) {
            if (!level.method_8608()) {
               level.method_8396(null, pos, class_3417.field_15222, class_3419.field_15245, 1.0F, 1.0F);
            }

            this.extinguish(state, level, pos);
            if (!player.method_7337()) {
               player.method_6122(hand, heldStack.getRecipeRemainder());
            }

            return class_9062.field_47728;
         }
      } else {
         if (heldItem instanceof class_1786) {
            level.method_8396(player, pos, class_3417.field_15145, class_3419.field_15245, 1.0F, MathUtils.RAND.nextFloat() * 0.4F + 0.8F);
            level.method_8652(pos, (class_2680)state.method_11657(class_2741.field_12548, Boolean.TRUE), 11);
            heldStack.method_7970(1, player, class_1309.method_56079(hand));
            return class_9062.field_47728;
         }

         if (heldItem instanceof class_1778) {
            level.method_8396(
               null, pos, class_3417.field_15013, class_3419.field_15245, 1.0F, (MathUtils.RAND.nextFloat() - MathUtils.RAND.nextFloat()) * 0.2F + 1.0F
            );
            level.method_8652(pos, (class_2680)state.method_11657(class_2741.field_12548, Boolean.TRUE), 11);
            if (!player.method_7337()) {
               heldStack.method_7934(1);
            }

            return class_9062.field_47728;
         }
      }

      if (level.method_8321(pos) instanceof StoveBlockEntity stoveEntity) {
         int stoveSlot = stoveEntity.getNextEmptySlot();
         if (stoveSlot < 0 || stoveEntity.isStoveBlockedAbove()) {
            return class_9062.field_47731;
         }

         Optional<class_8786<class_3920>> recipe = stoveEntity.getMatchingRecipe(heldStack);
         if (recipe.isPresent()) {
            if (!level.field_9236 && stoveEntity.addItem(player.method_31549().field_7477 ? heldStack.method_7972() : heldStack, recipe.get(), stoveSlot)) {
               return class_9062.field_47728;
            }

            return class_9062.field_47729;
         }
      }

      return class_9062.field_47731;
   }

   public class_2464 method_9604(class_2680 pState) {
      return class_2464.field_11458;
   }

   public void extinguish(class_2680 state, class_1937 level, class_2338 pos) {
      level.method_8652(pos, (class_2680)state.method_11657(LIT, false), 2);
      double x = pos.method_10263() + 0.5;
      double y = pos.method_10264();
      double z = pos.method_10260() + 0.5;
      level.method_8486(x, y, z, class_3417.field_15102, class_3419.field_15245, 0.5F, 2.6F, false);
   }

   public class_2680 method_9605(class_1750 context) {
      return (class_2680)((class_2680)this.method_9564().method_11657(FACING, context.method_8042().method_10153())).method_11657(LIT, true);
   }

   public void method_9591(class_1937 level, class_2338 pos, class_2680 state, class_1297 entity) {
      boolean isLit = (Boolean)level.method_8320(pos).method_11654(LIT);
      if (isLit && !entity.method_21749() && entity instanceof class_1309) {
         entity.method_5643(ModDamageTypes.getSimpleDamageSource(level, ModDamageTypes.STOVE_BURN), 1.0F);
      }

      super.method_9591(level, pos, state, entity);
   }

   public void method_9536(class_2680 state, class_1937 level, class_2338 pos, class_2680 newState, boolean isMoving) {
      if (state.method_26204() != newState.method_26204()) {
         class_2586 tileEntity = level.method_8321(pos);
         if (tileEntity instanceof StoveBlockEntity) {
            ItemUtils.dropItems(level, pos, ((StoveBlockEntity)tileEntity).getInventory());
         }

         super.method_9536(state, level, pos, newState, isMoving);
      }
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      super.method_9515(builder);
      builder.method_11667(new class_2769[]{LIT, FACING});
   }

   public void method_9496(class_2680 stateIn, class_1937 level, class_2338 pos, class_5819 rand) {
      if ((Boolean)stateIn.method_11654(class_3922.field_17352)) {
         double x = pos.method_10263() + 0.5;
         double y = pos.method_10264();
         double z = pos.method_10260() + 0.5;
         if (rand.method_43048(10) == 0) {
            level.method_8486(x, y, z, ModSounds.BLOCK_STOVE_CRACKLE.get(), class_3419.field_15245, 1.0F, 1.0F, false);
         }

         class_2350 direction = (class_2350)stateIn.method_11654(class_2383.field_11177);
         class_2351 direction$axis = direction.method_10166();
         double horizontalOffset = rand.method_43058() * 0.6 - 0.3;
         double xOffset = direction$axis == class_2351.field_11048 ? direction.method_10148() * 0.52 : horizontalOffset;
         double yOffset = rand.method_43058() * 6.0 / 16.0;
         double zOffset = direction$axis == class_2351.field_11051 ? direction.method_10165() * 0.52 : horizontalOffset;
         level.method_8406(class_2398.field_11251, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
         level.method_8406(class_2398.field_11240, x + xOffset, y + yOffset, z + zOffset, 0.0, 0.0, 0.0);
      }
   }

   @Nullable
   public class_2586 method_10123(class_2338 pos, class_2680 state) {
      return ModBlockEntityTypes.STOVE.get().method_11032(pos, state);
   }

   @Nullable
   public <T extends class_2586> class_5558<T> method_31645(class_1937 level, class_2680 state, class_2591<T> blockEntityType) {
      return state.method_11654(LIT)
         ? class_2237.method_31618(
            blockEntityType, ModBlockEntityTypes.STOVE.get(), level.field_9236 ? StoveBlockEntity::animationTick : StoveBlockEntity::cookingTick
         )
         : null;
   }

   @Deprecated
   @Nullable
   public class_7 getBlockPathType(class_2680 state, class_1922 world, class_2338 pos, @Nullable class_1308 entity) {
      return this.getBlockPathType(state, world, pos);
   }

   @Nullable
   public class_7 getBlockPathType(class_2680 state, class_1922 world, class_2338 pos) {
      return state.method_11654(LIT) ? class_7.field_3 : null;
   }

   public class_2680 method_9598(class_2680 pState, class_2470 pRot) {
      return (class_2680)pState.method_11657(FACING, pRot.method_10503((class_2350)pState.method_11654(FACING)));
   }

   public class_2680 method_9569(class_2680 pState, class_2415 pMirror) {
      return pState.method_26186(pMirror.method_10345((class_2350)pState.method_11654(FACING)));
   }
}
