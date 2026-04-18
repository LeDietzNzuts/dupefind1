package org.embeddedt.modernfix.common.mixin.perf.deduplicate_location;

import net.minecraft.class_2960;
import org.embeddedt.modernfix.dedup.IdentifierCaches;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2960.class)
public class MixinResourceLocation {
   @Mutable
   @Shadow
   @Final
   private String field_13353;
   @Mutable
   @Shadow
   @Final
   private String field_13355;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void reinit(String string, String string2, CallbackInfo ci) {
      this.field_13353 = IdentifierCaches.NAMESPACES.deduplicate(this.field_13353);
      this.field_13355 = IdentifierCaches.PATH.deduplicate(this.field_13355);
   }
}
