package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import net.minecraft.class_332;
import net.minecraft.class_437;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_437.class)
public class ScreenMixin {
   @Inject(method = "render", at = @At("HEAD"), order = 100000, cancellable = true)
   private void sophisticatedCore$renderHead(class_332 guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
      if (this instanceof StorageScreenBase || this instanceof SettingsScreen) {
         ci.cancel();
      }
   }
}
