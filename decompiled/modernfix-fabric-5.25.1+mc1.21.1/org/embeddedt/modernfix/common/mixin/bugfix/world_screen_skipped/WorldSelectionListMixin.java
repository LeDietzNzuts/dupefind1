package org.embeddedt.modernfix.common.mixin.bugfix.world_screen_skipped;

import net.minecraft.class_310;
import net.minecraft.class_525;
import net.minecraft.class_528.class_4272;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4272.class)
@ClientOnlyMixin
public class WorldSelectionListMixin {
   @Shadow
   @Final
   private class_310 field_19136;

   @Inject(
      method = "*",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/worldselection/WorldSelectionList$WorldListEntry;doDeleteWorld()V",
         ordinal = 0,
         shift = Shift.AFTER
      ),
      cancellable = true
   )
   private void preventClosingCreateScreenAfterDelete(CallbackInfo ci) {
      if (this.field_19136.field_1755 instanceof class_525) {
         ci.cancel();
      }
   }
}
