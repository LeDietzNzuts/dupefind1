package net.p3pp3rf1y.sophisticatedbackpacks.mixin.common;

import java.util.Optional;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_5362;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BlockInterfaceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5362.class)
public class ExplosionDamageCalculatorMixin {
   @Inject(method = "getBlockExplosionResistance", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"), cancellable = true)
   public void sophisticatedBackpacks$getBlockExplosionResistance(
      class_1927 explosion, class_1922 reader, class_2338 pos, class_2680 state, class_3610 fluid, CallbackInfoReturnable<Optional<Float>> cir
   ) {
      if (state.method_26204() instanceof BlockInterfaceHelper) {
         cir.setReturnValue(
            Optional.of(Math.max(((BlockInterfaceHelper)state.method_26204()).getExplosionResistance(state, reader, pos, explosion), fluid.method_15760()))
         );
      }
   }
}
