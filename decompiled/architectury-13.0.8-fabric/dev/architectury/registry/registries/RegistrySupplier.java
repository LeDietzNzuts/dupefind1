package dev.architectury.registry.registries;

import java.util.function.Consumer;
import net.minecraft.class_6880;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@NonExtendable
public interface RegistrySupplier<T> extends DeferredSupplier<T>, class_6880<T> {
   RegistrarManager getRegistrarManager();

   Registrar<T> getRegistrar();

   default void listen(Consumer<T> callback) {
      this.getRegistrar().listen(this, callback);
   }
}
