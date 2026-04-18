package fuzs.forgeconfigapiport.fabric.api.forge.v4;

import fuzs.forgeconfigapiport.fabric.impl.core.ForgeConfigRegistryImpl;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

public interface ForgeConfigRegistry {
   ForgeConfigRegistry INSTANCE = new ForgeConfigRegistryImpl();

   @Deprecated(forRemoval = true)
   ModConfig register(String var1, ModConfig.Type var2, IConfigSpec<?> var3);

   void register(String var1, net.neoforged.fml.config.ModConfig.Type var2, IConfigSpec<?> var3);

   @Deprecated(forRemoval = true)
   ModConfig register(String var1, ModConfig.Type var2, IConfigSpec<?> var3, String var4);

   void register(String var1, net.neoforged.fml.config.ModConfig.Type var2, IConfigSpec<?> var3, String var4);
}
