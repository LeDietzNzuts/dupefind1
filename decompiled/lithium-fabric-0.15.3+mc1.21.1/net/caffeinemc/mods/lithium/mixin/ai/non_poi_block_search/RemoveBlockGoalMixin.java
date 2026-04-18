package net.caffeinemc.mods.lithium.mixin.ai.non_poi_block_search;

import java.util.function.BiPredicate;
import net.caffeinemc.mods.lithium.common.ai.non_poi_block_search.LithiumMoveToBlockGoal;
import net.minecraft.class_1314;
import net.minecraft.class_1367;
import net.minecraft.class_1382;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1382.class)
public abstract class RemoveBlockGoalMixin extends class_1367 implements LithiumMoveToBlockGoal {
   @Shadow
   @Final
   private class_2248 field_6587;
   @Unique
   private static final BiPredicate<class_2791, class_2339> IS_VALID_TARGET_ABOVE_BIPREDICATE = RemoveBlockGoalMixin::lithium$isValidTargetAbove;

   public RemoveBlockGoalMixin(class_1314 pathfinderMob, double d, int i) {
      super(pathfinderMob, d, i);
   }

   @Redirect(method = "method_6264()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1382;method_6292()Z"))
   protected boolean redirectFindNearestBlock(class_1382 removeBlockGoal) {
      return ((LithiumMoveToBlockGoal)removeBlockGoal).lithium$findNearestBlock(this::lithium$isValidTargetBlock, IS_VALID_TARGET_ABOVE_BIPREDICATE, false);
   }

   @Unique
   private boolean lithium$isValidTargetBlock(class_2680 blockState) {
      return blockState.method_27852(this.field_6587);
   }

   @Unique
   private static boolean lithium$isValidTargetAbove(class_2791 chunkAccess, class_2339 mutable) {
      return chunkAccess.method_8320(mutable.method_10100(0, 1, 0)).method_26215() && chunkAccess.method_8320(mutable.method_10100(0, 1, 0)).method_26215();
   }
}
