package net.caffeinemc.mods.sodium.mixin.features.options.render_layers;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.class_4696;
import net.minecraft.class_5365;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4696.class)
public class ItemBlockRenderTypesMixin {
   @Unique
   private static boolean leavesFancy;

   @Redirect(
      method = {
            "method_23679(Lnet/minecraft/class_2680;)Lnet/minecraft/class_1921;",
            "method_29359(Lnet/minecraft/class_2680;)Lnet/minecraft/class_1921;",
            "getRenderLayers"
      },
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_4696;field_21472:Z"),
      require = 2
   )
   private static boolean redirectLeavesShouldBeFancy() {
      return leavesFancy;
   }

   @Inject(method = "method_23682(Z)V", at = @At("RETURN"))
   private static void onSetFancyGraphicsOrBetter(boolean fancyGraphicsOrBetter, CallbackInfo ci) {
      leavesFancy = SodiumClientMod.options().quality.leavesQuality.isFancy(fancyGraphicsOrBetter ? class_5365.field_25428 : class_5365.field_25427);
   }
}
