package fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client;

import fuzs.forgeconfigapiport.fabric.impl.client.core.ConfigScreenFactoryRegistryImpl;
import java.util.function.BiFunction;
import net.minecraft.class_437;

public interface ConfigScreenFactoryRegistry {
   ConfigScreenFactoryRegistry INSTANCE = new ConfigScreenFactoryRegistryImpl();

   void register(String var1, BiFunction<String, class_437, class_437> var2);
}
