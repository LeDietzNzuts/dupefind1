package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import net.minecraft.class_1863;
import net.minecraft.class_2788;
import net.minecraft.class_634;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientRecipesUpdated;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_634.class)
public class ClientPacketListenerMixin {
   @Shadow
   @Final
   private class_1863 field_3688;

   @Inject(method = "handleUpdateRecipes", at = @At("RETURN"))
   private void sophisticatedCore$handleUpdateRecipes(class_2788 packet, CallbackInfo ci) {
      ((ClientRecipesUpdated)ClientRecipesUpdated.EVENT.invoker()).onRecipesUpdated(this.field_3688);
   }
}
