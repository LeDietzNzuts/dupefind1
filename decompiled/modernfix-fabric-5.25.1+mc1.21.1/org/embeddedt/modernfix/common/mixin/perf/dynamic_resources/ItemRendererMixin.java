package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import net.minecraft.class_1091;
import net.minecraft.class_1792;
import net.minecraft.class_763;
import net.minecraft.class_918;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_918.class)
@ClientOnlyMixin
public class ItemRendererMixin {
   @Redirect(
      method = "<init>",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/ItemModelShaper;register(Lnet/minecraft/world/item/Item;Lnet/minecraft/client/resources/model/ModelResourceLocation;)V"
      )
   )
   private void skipDefaultRegistration(class_763 shaper, class_1792 item, class_1091 mrl) {
   }
}
