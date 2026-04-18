package fuzs.forgeconfigapiport.fabric.api.neoforge.v4;

import fuzs.forgeconfigapiport.fabric.impl.core.NeoForgeConfigRegistryImpl;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;

public interface NeoForgeConfigRegistry {
   NeoForgeConfigRegistry INSTANCE = new NeoForgeConfigRegistryImpl();

   ModConfig register(String var1, ModConfig.Type var2, IConfigSpec var3);

   ModConfig register(String var1, ModConfig.Type var2, IConfigSpec var3, String var4);
}
