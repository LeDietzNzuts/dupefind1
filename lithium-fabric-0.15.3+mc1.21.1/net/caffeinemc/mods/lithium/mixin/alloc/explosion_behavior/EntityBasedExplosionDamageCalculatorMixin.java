package net.caffeinemc.mods.lithium.mixin.alloc.explosion_behavior;

import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1922;
import net.minecraft.class_1927;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_5361;
import net.minecraft.class_5362;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_5361.class)
public class EntityBasedExplosionDamageCalculatorMixin extends class_5362 {
   @Shadow
   @Final
   private class_1297 field_25399;

   @Overwrite
   public Optional<Float> method_29555(class_1927 explosion, class_1922 world, class_2338 pos, class_2680 blockState, class_3610 fluidState) {
      Optional<Float> optionalBlastResistance = super.method_29555(explosion, world, pos, blockState, fluidState);
      if (optionalBlastResistance.isPresent()) {
         float blastResistance = optionalBlastResistance.get();
         float effectiveExplosionResistance = this.field_25399.method_5774(explosion, world, pos, blockState, fluidState, blastResistance);
         if (effectiveExplosionResistance != blastResistance) {
            return Optional.of(effectiveExplosionResistance);
         }
      }

      return optionalBlastResistance;
   }
}
