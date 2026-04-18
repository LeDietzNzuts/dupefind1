package net.p3pp3rf1y.sophisticatedbackpacks.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1528;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BlockInterfaceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1528.class)
public abstract class WitherBossMixin extends class_1297 {
   public WitherBossMixin(class_1299<?> entityType, class_1937 level) {
      super(entityType, level);
   }

   @ModifyExpressionValue(
      method = "customServerAiStep",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/boss/wither/WitherBoss;canDestroy(Lnet/minecraft/world/level/block/state/BlockState;)Z")
   )
   public boolean sophisticatedBackpacks$checkWalls(boolean original, @Local class_2338 blockPos, @Local class_2680 blockState) {
      return blockState.method_26204() instanceof BlockInterfaceHelper bih ? !bih.canEntityDestroy(blockState, this.method_37908(), blockPos, this) : original;
   }
}
