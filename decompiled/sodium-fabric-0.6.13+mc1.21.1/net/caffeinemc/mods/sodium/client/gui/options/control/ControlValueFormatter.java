package net.caffeinemc.mods.sodium.client.gui.options.control;

import net.caffeinemc.mods.sodium.client.compatibility.environment.OsUtils;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_313;

public interface ControlValueFormatter {
   static ControlValueFormatter guiScale() {
      return v -> v == 0 ? class_2561.method_43471("options.guiScale.auto") : class_2561.method_43470(v + "x");
   }

   static ControlValueFormatter resolution() {
      return v -> {
         class_313 monitor = class_310.method_1551().method_22683().method_20831();
         if (OsUtils.getOs() != OsUtils.OperatingSystem.WIN || monitor == null) {
            return class_2561.method_43471("options.fullscreen.unavailable");
         } else {
            return 0 == v
               ? class_2561.method_43471("options.fullscreen.current")
               : class_2561.method_43470(monitor.method_1620(v - 1).toString().replace(" (24bit)", ""));
         }
      };
   }

   static ControlValueFormatter fpsLimit() {
      return v -> v == 260 ? class_2561.method_43471("options.framerateLimit.max") : class_2561.method_43469("options.framerate", new Object[]{v});
   }

   static ControlValueFormatter brightness() {
      return v -> {
         if (v == 0) {
            return class_2561.method_43471("options.gamma.min");
         } else {
            return v == 100 ? class_2561.method_43471("options.gamma.max") : class_2561.method_43470(v + "%");
         }
      };
   }

   static ControlValueFormatter biomeBlend() {
      return v -> v == 0 ? class_2561.method_43471("gui.none") : class_2561.method_43469("sodium.options.biome_blend.value", new Object[]{v});
   }

   class_2561 format(int var1);

   static ControlValueFormatter translateVariable(String key) {
      return v -> class_2561.method_43469(key, new Object[]{v});
   }

   static ControlValueFormatter percentage() {
      return v -> class_2561.method_43470(v + "%");
   }

   static ControlValueFormatter multiplier() {
      return v -> class_2561.method_43470(v + "x");
   }

   static ControlValueFormatter quantityOrDisabled(String name, String disableText) {
      return v -> class_2561.method_43470(v == 0 ? disableText : v + " " + name);
   }

   static ControlValueFormatter number() {
      return v -> class_2561.method_43470(String.valueOf(v));
   }
}
