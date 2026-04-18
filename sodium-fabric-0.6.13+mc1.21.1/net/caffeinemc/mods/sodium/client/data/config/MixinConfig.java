package net.caffeinemc.mods.sodium.client.data.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import net.caffeinemc.mods.sodium.client.services.PlatformMixinOverrides;
import net.caffeinemc.mods.sodium.mixin.MixinOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MixinConfig {
   protected static final Logger LOGGER = LogManager.getLogger("SodiumConfig");
   protected static final String JSON_KEY_SODIUM_OPTIONS = "sodium:options";
   private final Map<String, MixinOption> options = new HashMap<>();

   protected MixinConfig() {
      this.addMixinRule("core", true);
      this.addMixinRule("debug.checks", false);
      this.addMixinRule("features", true);
      this.addMixinRule("features.gui", true);
      this.addMixinRule("features.gui.hooks", true);
      this.addMixinRule("features.gui.hooks.console", true);
      this.addMixinRule("features.gui.hooks.debug", true);
      this.addMixinRule("features.gui.hooks.settings", true);
      this.addMixinRule("features.gui.screen", true);
      this.addMixinRule("features.model", true);
      this.addMixinRule("features.options", true);
      this.addMixinRule("features.options.overlays", true);
      this.addMixinRule("features.options.render_layers", true);
      this.addMixinRule("features.options.weather", true);
      this.addMixinRule("features.render", true);
      this.addMixinRule("features.render.compositing", true);
      this.addMixinRule("features.render.entity", true);
      this.addMixinRule("features.render.entity.cull", true);
      this.addMixinRule("features.render.entity.shadow", true);
      this.addMixinRule("features.render.frapi", true);
      this.addMixinRule("features.render.gui", true);
      this.addMixinRule("features.render.gui.font", true);
      this.addMixinRule("features.render.gui.outlines", true);
      this.addMixinRule("features.render.immediate", true);
      this.addMixinRule("features.render.immediate.buffer_builder", true);
      this.addMixinRule("features.render.immediate.matrix_stack", true);
      this.addMixinRule("features.render.model", true);
      this.addMixinRule("features.render.model.block", true);
      this.addMixinRule("features.render.model.item", true);
      this.addMixinRule("features.render.particle", true);
      this.addMixinRule("features.render.world", true);
      this.addMixinRule("features.render.world.clouds", true);
      this.addMixinRule("features.render.world.sky", true);
      this.addMixinRule("features.shader", true);
      this.addMixinRule("features.shader.uniform", true);
      this.addMixinRule("features.textures", true);
      this.addMixinRule("features.textures.animations", true);
      this.addMixinRule("features.textures.mipmaps", true);
      this.addMixinRule("features.world", true);
      this.addMixinRule("features.world.biome", true);
      this.addMixinRule("workarounds", true);
      this.addMixinRule("workarounds.context_creation", true);
      this.addMixinRule("workarounds.event_loop", true);
   }

   private void addMixinRule(String mixin, boolean enabled) {
      String name = getMixinRuleName(mixin);
      if (this.options.putIfAbsent(name, new MixinOption(name, enabled, false)) != null) {
         throw new IllegalStateException("Mixin rule already defined: " + mixin);
      }
   }

   private void readProperties(Properties props) {
      for (Entry<Object, Object> entry : props.entrySet()) {
         String key = (String)entry.getKey();
         String value = (String)entry.getValue();
         MixinOption option = this.options.get(key);
         if (option == null) {
            LOGGER.warn("No configuration key exists with name '{}', ignoring", key);
         } else {
            boolean enabled;
            if (value.equalsIgnoreCase("true")) {
               enabled = true;
            } else {
               if (!value.equalsIgnoreCase("false")) {
                  LOGGER.warn("Invalid value '{}' encountered for configuration key '{}', ignoring", value, key);
                  continue;
               }

               enabled = false;
            }

            option.setEnabled(enabled, true);
         }
      }
   }

   protected void applyModOverride(PlatformMixinOverrides.MixinOverride override) {
      MixinOption option = this.options.get(override.option());
      if (option == null) {
         LOGGER.warn("Mod '{}' attempted to override option '{}', which doesn't exist, ignoring", override.modId(), override.option());
      } else {
         if (!override.enabled() && option.isEnabled()) {
            option.clearModsDefiningValue();
         }

         if (!override.enabled() || option.isEnabled() || option.getDefiningMods().isEmpty()) {
            option.addModOverride(override.enabled(), override.modId());
         }
      }
   }

   public MixinOption getEffectiveOptionForMixin(String mixinClassName) {
      int lastSplit = 0;
      MixinOption rule = null;

      int nextSplit;
      while ((nextSplit = mixinClassName.indexOf(46, lastSplit)) != -1) {
         String key = getMixinRuleName(mixinClassName.substring(0, nextSplit));
         MixinOption candidate = this.options.get(key);
         if (candidate != null) {
            rule = candidate;
            if (!candidate.isEnabled()) {
               return candidate;
            }
         }

         lastSplit = nextSplit + 1;
      }

      return rule;
   }

   public static MixinConfig load(File file) {
      if (!file.exists()) {
         try {
            writeDefaultConfig(file);
         } catch (IOException var6) {
            LOGGER.warn("Could not write default configuration file", var6);
         }

         MixinConfig config = new MixinConfig();
         PlatformMixinOverrides.getInstance().applyModOverrides().forEach(config::applyModOverride);
         return config;
      } else {
         Properties props = new Properties();

         try (FileInputStream fin = new FileInputStream(file)) {
            props.load(fin);
         } catch (IOException var8) {
            throw new RuntimeException("Could not load config file", var8);
         }

         MixinConfig config = new MixinConfig();
         config.readProperties(props);
         PlatformMixinOverrides.getInstance().applyModOverrides().forEach(config::applyModOverride);
         return config;
      }
   }

   private static void writeDefaultConfig(File file) throws IOException {
      File dir = file.getParentFile();
      if (!dir.exists()) {
         if (!dir.mkdirs()) {
            throw new IOException("Could not create parent directories");
         }
      } else if (!dir.isDirectory()) {
         throw new IOException("The parent file is not a directory");
      }

      try (Writer writer = new FileWriter(file)) {
         writer.write("# This is the configuration file for Sodium.\n");
         writer.write("#\n");
         writer.write("# You can find information on editing this file and all the available options here:\n");
         writer.write("# https://github.com/CaffeineMC/sodium/wiki/Configuration-File\n");
         writer.write("#\n");
         writer.write("# By default, this file will be empty except for this notice.\n");
      }
   }

   private static String getMixinRuleName(String name) {
      return "mixin." + name;
   }

   public int getOptionCount() {
      return this.options.size();
   }

   public int getOptionOverrideCount() {
      return (int)this.options.values().stream().filter(MixinOption::isOverridden).count();
   }
}
