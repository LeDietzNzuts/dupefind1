package dev.architectury.impl;

import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.class_1761;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface ItemPropertiesExtensionImpl {
   @Nullable
   class_1761 arch$getTab();

   @Nullable
   DeferredSupplier<class_1761> arch$getTabSupplier();
}
