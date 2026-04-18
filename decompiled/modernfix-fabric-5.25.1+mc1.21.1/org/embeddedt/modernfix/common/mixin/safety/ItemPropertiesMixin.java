package org.embeddedt.modernfix.common.mixin.safety;

import java.util.Collections;
import java.util.Map;
import net.minecraft.class_1792;
import net.minecraft.class_1800;
import net.minecraft.class_2960;
import net.minecraft.class_5272;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = class_5272.class, priority = 700)
@ClientOnlyMixin
public class ItemPropertiesMixin {
   @Shadow
   @Final
   @Mutable
   private static Map<class_2960, class_1800> field_24443;
   @Shadow
   @Final
   @Mutable
   private static Map<class_1792, Map<class_2960, class_1800>> field_24448;

   @Inject(method = "<clinit>", at = @At("RETURN"))
   private static void useConcurrentMaps(CallbackInfo ci) {
      field_24443 = Collections.synchronizedMap(field_24443);
      field_24448 = Collections.synchronizedMap(field_24448);
   }
}
