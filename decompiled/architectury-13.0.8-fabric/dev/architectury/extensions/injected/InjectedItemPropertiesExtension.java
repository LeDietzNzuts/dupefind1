package dev.architectury.extensions.injected;

import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.class_1761;
import net.minecraft.class_5321;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.ApiStatus.Experimental;

public interface InjectedItemPropertiesExtension {
   @Experimental
   default class_1793 arch$tab(class_1761 tab) {
      throw new UnsupportedOperationException();
   }

   @Experimental
   default class_1793 arch$tab(DeferredSupplier<class_1761> tab) {
      throw new UnsupportedOperationException();
   }

   @Experimental
   default class_1793 arch$tab(class_5321<class_1761> tab) {
      throw new UnsupportedOperationException();
   }
}
