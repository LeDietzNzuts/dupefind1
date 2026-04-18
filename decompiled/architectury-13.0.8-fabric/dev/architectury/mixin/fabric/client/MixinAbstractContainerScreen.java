package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_465.class)
public abstract class MixinAbstractContainerScreen extends class_437 {
   protected MixinAbstractContainerScreen(class_2561 component) {
      super(component);
   }

   @Inject(
      method = "renderBackground",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V",
         ordinal = 0,
         shift = Shift.AFTER
      )
   )
   public void renderBackground(class_332 graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      ClientGuiEvent.RENDER_CONTAINER_BACKGROUND.invoker().render((class_465<?>)this, graphics, mouseX, mouseY, delta);
   }

   @Inject(
      method = "render",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderLabels(Lnet/minecraft/client/gui/GuiGraphics;II)V",
         ordinal = 0,
         shift = Shift.AFTER
      )
   )
   public void renderForeground(class_332 graphics, int mouseX, int mouseY, float delta, CallbackInfo ci) {
      ClientGuiEvent.RENDER_CONTAINER_FOREGROUND.invoker().render((class_465<?>)this, graphics, mouseX, mouseY, delta);
   }
}
