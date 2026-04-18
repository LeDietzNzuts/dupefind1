package org.embeddedt.modernfix.common.mixin.perf.dynamic_entity_renderers;

import java.util.Map;
import net.minecraft.class_1299;
import net.minecraft.class_5617;
import net.minecraft.class_5619;
import net.minecraft.class_897;
import net.minecraft.class_5617.class_5618;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.entity.EntityRendererMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5619.class)
@ClientOnlyMixin
public class EntityRenderersMixin {
   @Shadow
   @Final
   private static Map<class_1299<?>, class_5617<?>> field_27768;

   @Inject(method = "createEntityRenderers", at = @At("HEAD"), cancellable = true)
   private static void createDynamicRendererLoader(class_5618 context, CallbackInfoReturnable<Map<class_1299<?>, class_897<?>>> cir) {
      cir.setReturnValue(new EntityRendererMap(field_27768, context));
      ModernFix.LOGGER.info("Dynamic entity renderer hook setup");
   }
}
