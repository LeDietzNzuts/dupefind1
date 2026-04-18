package net.caffeinemc.mods.sodium.mixin;

import java.io.File;
import java.util.List;
import java.util.Set;
import net.caffeinemc.mods.sodium.client.data.config.MixinConfig;
import net.caffeinemc.mods.sodium.client.services.PlatformRuntimeInformation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class SodiumMixinPlugin implements IMixinConfigPlugin {
   private static final String MIXIN_PACKAGE_ROOT = "net.caffeinemc.mods.sodium.mixin.";
   private final Logger logger = LogManager.getLogger("Sodium");
   private MixinConfig config;
   private boolean dependencyResolutionFailed;

   public void onLoad(String mixinPackage) {
      try {
         this.config = MixinConfig.load(new File("./config/sodium-mixins.properties"));
      } catch (Exception var3) {
         throw new RuntimeException("Could not load configuration file for Sodium", var3);
      }

      this.dependencyResolutionFailed = PlatformRuntimeInformation.getInstance().isModInLoadingList("embeddium");
      if (this.dependencyResolutionFailed) {
         this.logger.error("Not applying any Sodium mixins; dependency resolution has failed.");
      }

      this.logger
         .info(
            "Loaded configuration file for Sodium: {} options available, {} override(s) found",
            this.config.getOptionCount(),
            this.config.getOptionOverrideCount()
         );
   }

   public String getRefMapperConfig() {
      return null;
   }

   public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
      if (this.dependencyResolutionFailed) {
         return false;
      } else if (!mixinClassName.startsWith("net.caffeinemc.mods.sodium.mixin.")) {
         this.logger
            .error(
               "Expected mixin '{}' to start with package root '{}', treating as foreign and disabling!", mixinClassName, "net.caffeinemc.mods.sodium.mixin."
            );
         return false;
      } else {
         String mixin = mixinClassName.substring("net.caffeinemc.mods.sodium.mixin.".length());
         MixinOption option = this.config.getEffectiveOptionForMixin(mixin);
         if (option == null) {
            this.logger.error("No rules matched mixin '{}', treating as foreign and disabling!", mixin);
            return false;
         } else {
            if (option.isOverridden()) {
               String source = "[unknown]";
               if (option.isUserDefined()) {
                  source = "user configuration";
               } else if (option.isModDefined()) {
                  source = "mods [" + String.join(", ", option.getDefiningMods()) + "]";
               }

               if (option.isEnabled()) {
                  this.logger.warn("Force-enabling mixin '{}' as rule '{}' (added by {}) enables it", mixin, option.getName(), source);
               } else {
                  this.logger.warn("Force-disabling mixin '{}' as rule '{}' (added by {}) disables it and children", mixin, option.getName(), source);
               }
            }

            return option.isEnabled();
         }
      }
   }

   public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
   }

   public List<String> getMixins() {
      return null;
   }

   public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
   }

   public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
   }
}
