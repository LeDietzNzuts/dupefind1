package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import net.minecraft.class_1092;
import net.minecraft.class_310;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IExtendedModelManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
@ClientOnlyMixin
public abstract class MinecraftMixin_ModelTicking {
   @Shadow
   public abstract class_1092 method_1554();

   @Inject(method = "tick", at = @At("RETURN"))
   private void tickModels(CallbackInfo ci) {
      ((IExtendedModelManager)this.method_1554()).mfix$tick();
   }
}
