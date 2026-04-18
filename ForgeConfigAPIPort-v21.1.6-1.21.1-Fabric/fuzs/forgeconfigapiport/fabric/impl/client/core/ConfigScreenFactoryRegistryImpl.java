package fuzs.forgeconfigapiport.fabric.impl.client.core;

import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import net.minecraft.class_437;

public final class ConfigScreenFactoryRegistryImpl implements ConfigScreenFactoryRegistry {
   private final Map<String, UnaryOperator<class_437>> factories = new HashMap<>();

   @Override
   public void register(String modId, BiFunction<String, class_437, class_437> factory) {
      this.factories.put(modId, screen -> factory.apply(modId, screen));
   }

   public <T> Map<String, T> getConfigScreenFactories(Function<UnaryOperator<class_437>, T> converter) {
      return this.factories.entrySet().stream().collect(Collectors.toMap(Entry::getKey, entry -> converter.apply(entry.getValue())));
   }
}
