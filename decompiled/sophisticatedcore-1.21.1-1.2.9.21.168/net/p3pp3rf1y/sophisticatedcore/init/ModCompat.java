package net.p3pp3rf1y.sophisticatedcore.init;

import net.p3pp3rf1y.sophisticatedcore.compat.CompatInfo;
import net.p3pp3rf1y.sophisticatedcore.compat.CompatRegistry;
import net.p3pp3rf1y.sophisticatedcore.compat.audioplayer.AudioPlayerCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.craftingtweaks.CraftingTweaksCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.emi.EmiCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.JeiCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaCompat;
import net.p3pp3rf1y.sophisticatedcore.compat.rei.REICompat;
import net.p3pp3rf1y.sophisticatedcore.compat.trinkets.TrinketsCompat;

public class ModCompat {
   private ModCompat() {
   }

   public static void register() {
      CompatRegistry registry = CompatRegistry.getRegistry("sophisticatedcore");
      registry.registerCompat(new CompatInfo("jei", null), () -> new JeiCompat());
      registry.registerCompat(new CompatInfo("craftingtweaks", null), () -> new CraftingTweaksCompat());
      registry.registerCompat(new CompatInfo("emi", null), () -> new EmiCompat());
      registry.registerCompat(new CompatInfo("roughlyenoughitems", null), () -> new REICompat());
      registry.registerCompat(new CompatInfo("litematica", null), () -> new LitematicaCompat());
      registry.registerCompat(new CompatInfo("audioplayer", null), () -> new AudioPlayerCompat());
      registry.registerCompat(new CompatInfo("trinkets", null), () -> new TrinketsCompat());
   }
}
