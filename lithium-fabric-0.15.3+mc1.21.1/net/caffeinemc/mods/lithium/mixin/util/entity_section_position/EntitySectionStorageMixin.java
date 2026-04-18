package net.caffeinemc.mods.lithium.mixin.util.entity_section_position;

import net.caffeinemc.mods.lithium.common.entity.PositionedEntityTrackingSection;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5573;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5573.class)
public class EntitySectionStorageMixin<T extends class_5568> {
   @Inject(method = "method_31788(J)Lnet/minecraft/class_5572;", at = @At("RETURN"))
   private void rememberPos(long sectionPos, CallbackInfoReturnable<class_5572<T>> cir) {
      ((PositionedEntityTrackingSection)cir.getReturnValue()).lithium$setPos(sectionPos);
   }
}
