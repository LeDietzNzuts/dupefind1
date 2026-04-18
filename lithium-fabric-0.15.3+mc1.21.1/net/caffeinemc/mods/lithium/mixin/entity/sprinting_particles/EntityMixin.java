package net.caffeinemc.mods.lithium.mixin.entity.sprinting_particles;

import net.minecraft.class_1297;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1297.class)
public abstract class EntityMixin {
   @Redirect(method = "method_5670()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1297;method_27298()Z"))
   private boolean skipParticlesOnServerSide(class_1297 instance) {
      return instance.method_37908().method_8608() ? instance.method_27298() : false;
   }
}
