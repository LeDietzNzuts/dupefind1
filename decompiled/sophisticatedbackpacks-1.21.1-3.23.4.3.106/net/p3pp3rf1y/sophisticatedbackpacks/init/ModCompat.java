package net.p3pp3rf1y.sophisticatedbackpacks.init;

import net.p3pp3rf1y.sophisticatedbackpacks.compat.chipped.ChippedCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.litematica.LitematicaCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.trinkets.TrinketsCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatInfo;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatRegistry;

public class ModCompat {
   private ModCompat() {
   }

   public static void register() {
      CompatRegistry registry = CompatRegistry.getRegistry("sophisticatedbackpacks");
      registry.registerCompat(new CompatInfo("trinkets", null), () -> new TrinketsCompat());
      registry.registerCompat(new CompatInfo("chipped", null), () -> new ChippedCompat());
      registry.registerCompat(new CompatInfo("litematica", null), () -> new LitematicaCompat());
   }
}
