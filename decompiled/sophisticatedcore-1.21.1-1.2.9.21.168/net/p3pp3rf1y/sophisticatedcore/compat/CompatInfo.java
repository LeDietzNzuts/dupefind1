package net.p3pp3rf1y.sophisticatedcore.compat;

import javax.annotation.Nullable;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.version.VersionPredicate;

public record CompatInfo(String modId, @Nullable VersionPredicate supportedVersionRange) {
   public boolean isLoaded() {
      return FabricLoader.getInstance()
         .getModContainer(this.modId())
         .map(container -> this.supportedVersionRange() == null || this.supportedVersionRange().test(container.getMetadata().getVersion()))
         .orElse(false);
   }
}
