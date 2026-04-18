package dev.architectury.mixin.inject;

import dev.architectury.extensions.injected.InjectedItemPropertiesExtension;
import dev.architectury.impl.ItemPropertiesExtensionImpl;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.class_1761;
import net.minecraft.class_5321;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1793.class)
public class MixinItemProperties implements InjectedItemPropertiesExtension, ItemPropertiesExtensionImpl {
   @Unique
   private class_1761 tab;
   @Unique
   private DeferredSupplier<class_1761> tabSupplier;

   @Override
   public class_1793 arch$tab(class_1761 tab) {
      this.tab = tab;
      this.tabSupplier = null;
      return (class_1793)this;
   }

   @Override
   public class_1793 arch$tab(DeferredSupplier<class_1761> tab) {
      this.tab = null;
      this.tabSupplier = tab;
      return (class_1793)this;
   }

   @Override
   public class_1793 arch$tab(class_5321<class_1761> tab) {
      this.tab = null;
      this.tabSupplier = CreativeTabRegistry.defer(tab);
      return (class_1793)this;
   }

   @Nullable
   @Override
   public class_1761 arch$getTab() {
      return this.tab;
   }

   @Nullable
   @Override
   public DeferredSupplier<class_1761> arch$getTabSupplier() {
      return this.tabSupplier;
   }
}
