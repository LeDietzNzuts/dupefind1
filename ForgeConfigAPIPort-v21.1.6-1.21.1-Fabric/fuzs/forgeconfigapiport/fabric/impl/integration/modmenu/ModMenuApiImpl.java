package fuzs.forgeconfigapiport.fabric.impl.integration.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import fuzs.forgeconfigapiport.fabric.impl.client.core.ConfigScreenFactoryRegistryImpl;
import java.util.Map;

public final class ModMenuApiImpl implements ModMenuApi {
   public ConfigScreenFactory<?> getModConfigScreenFactory() {
      return null;
   }

   public Map<String, ConfigScreenFactory<?>> getProvidedConfigScreenFactories() {
      return ((ConfigScreenFactoryRegistryImpl)ConfigScreenFactoryRegistry.INSTANCE).getConfigScreenFactories(operator -> operator::apply);
   }
}
