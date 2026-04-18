package net.p3pp3rf1y.sophisticatedbackpacks.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1297;
import net.minecraft.class_1687;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BlockInterfaceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1687.class)
public class WitherSkullMixin {
   @ModifyExpressionValue(
      method = "getBlockExplosionResistance",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/WitherSkull;isDangerous()Z")
   )
   public boolean sophisticatedBackpacks$canDestroy(
      boolean original,
      @Local(argsOnly = true) class_1922 blockGetter,
      @Local(argsOnly = true) class_2338 blockPos,
      @Local(argsOnly = true) class_2680 blockState
   ) {
      return !(blockState.method_26204() instanceof BlockInterfaceHelper bih)
         ? original
         : original && bih.canEntityDestroy(blockState, blockGetter, blockPos, (class_1297)this);
   }
}
