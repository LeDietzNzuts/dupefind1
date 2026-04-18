package org.embeddedt.modernfix.common.mixin.safety;

import java.util.Collections;
import java.util.List;
import net.minecraft.class_3887;
import net.minecraft.class_922;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_922.class)
@ClientOnlyMixin
public class LivingEntityRendererMixin {
   @Shadow
   @Final
   @Mutable
   protected List<class_3887<?, ?>> field_4738;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void synchronizeLayerList(CallbackInfo ci) {
      this.field_4738 = Collections.synchronizedList(this.field_4738);
   }
}
