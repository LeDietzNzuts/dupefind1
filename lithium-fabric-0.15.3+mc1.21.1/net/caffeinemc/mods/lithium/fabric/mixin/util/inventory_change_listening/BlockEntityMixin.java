package net.caffeinemc.mods.lithium.fabric.mixin.util.inventory_change_listening;

import net.caffeinemc.mods.lithium.common.block.entity.SetBlockStateHandlingBlockEntity;
import net.minecraft.class_2586;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2586.class)
public class BlockEntityMixin implements SetBlockStateHandlingBlockEntity {
   @Inject(method = "method_31664(Lnet/minecraft/class_2680;)V", at = @At("RETURN"))
   private void emitRemovedOnSetCachedState(CallbackInfo ci) {
      this.lithium$handleSetBlockState();
   }
}
