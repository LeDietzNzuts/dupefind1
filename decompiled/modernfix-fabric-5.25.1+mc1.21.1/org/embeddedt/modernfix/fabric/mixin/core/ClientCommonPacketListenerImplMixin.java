package org.embeddedt.modernfix.fabric.mixin.core;

import net.minecraft.class_8674;
import org.embeddedt.modernfix.ModernFixClientFabric;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_8674.class)
@ClientOnlyMixin
public class ClientCommonPacketListenerImplMixin {
   @Inject(method = "handleUpdateTags", at = @At("RETURN"))
   private void signalTags(CallbackInfo ci) {
      ModernFixClientFabric.commonMod.onTagsUpdated();
   }
}
