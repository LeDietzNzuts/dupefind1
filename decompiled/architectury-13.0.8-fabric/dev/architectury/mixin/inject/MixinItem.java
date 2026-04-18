package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedItemExtension;
import dev.architectury.impl.ItemPropertiesExtensionImpl;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.class_1761;
import net.minecraft.class_1792;
import net.minecraft.class_1792.class_1793;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1792.class)
public class MixinItem implements InjectedItemExtension {
   @Inject(method = "<init>", at = @At("RETURN"))
   private void init(class_1793 properties, CallbackInfo ci) {
      class_1761 tab = ((ItemPropertiesExtensionImpl)properties).arch$getTab();
      if (tab != null) {
         CreativeTabRegistry.appendBuiltin(tab, (class_1792)this);
      } else {
         DeferredSupplier<class_1761> tabSupplier = ((ItemPropertiesExtensionImpl)properties).arch$getTabSupplier();
         if (tabSupplier != null) {
            CreativeTabRegistry.append(tabSupplier, (class_1792)this);
         }
      }
   }
}
