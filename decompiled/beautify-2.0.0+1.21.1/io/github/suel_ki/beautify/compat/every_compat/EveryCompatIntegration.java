package io.github.suel_ki.beautify.compat.every_compat;

import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;

public class EveryCompatIntegration {
   public static void register() {
      EveryCompatAPI.registerModule(new BeautifyModule("beautify"));
   }
}
