package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.client.ClientGuiEvent;
import java.util.List;
import net.minecraft.class_340;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_340.class)
public class MixinDebugScreenOverlay {
   @Inject(method = "getGameInformation", at = @At("RETURN"))
   private void getLeftTexts(CallbackInfoReturnable<List<String>> cir) {
      ClientGuiEvent.DEBUG_TEXT_LEFT.invoker().gatherText((List<String>)cir.getReturnValue());
   }

   @Inject(method = "getSystemInformation", at = @At("RETURN"))
   private void getRightTexts(CallbackInfoReturnable<List<String>> cir) {
      ClientGuiEvent.DEBUG_TEXT_RIGHT.invoker().gatherText((List<String>)cir.getReturnValue());
   }
}
