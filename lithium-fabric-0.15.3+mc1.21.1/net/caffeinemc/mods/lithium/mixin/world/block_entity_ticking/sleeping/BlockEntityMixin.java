package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping;

import net.caffeinemc.mods.lithium.common.block.entity.SetChangedHandlingBlockEntity;
import net.minecraft.class_2586;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2586.class)
public class BlockEntityMixin implements SetChangedHandlingBlockEntity {
   @Inject(method = "method_5431()V", at = @At("RETURN"))
   private void handleSetChanged(CallbackInfo ci) {
      this.lithium$handleSetChanged();
   }
}
