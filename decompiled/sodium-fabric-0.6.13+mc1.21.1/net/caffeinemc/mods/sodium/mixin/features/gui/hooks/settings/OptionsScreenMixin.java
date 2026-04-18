package net.caffeinemc.mods.sodium.mixin.features.gui.hooks.settings;

import net.caffeinemc.mods.sodium.client.gui.SodiumOptionsGUI;
import net.minecraft.class_2561;
import net.minecraft.class_429;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_429.class)
public class OptionsScreenMixin extends class_437 {
   protected OptionsScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(method = {"method_19828()Lnet/minecraft/class_437;", "lambda$init$2"}, require = 1, at = @At("HEAD"), cancellable = true)
   @Dynamic
   private void open(CallbackInfoReturnable<class_437> ci) {
      ci.setReturnValue(SodiumOptionsGUI.createScreen(this));
   }
}
