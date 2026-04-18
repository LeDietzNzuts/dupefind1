package dev.architectury.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.fabric.ReloadListenerRegistryImpl;
import java.util.Collection;
import java.util.List;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3302;
import org.jetbrains.annotations.Nullable;

public final class ReloadListenerRegistry {
   private ReloadListenerRegistry() {
   }

   public static void register(class_3264 type, class_3302 listener) {
      register(type, listener, null);
   }

   public static void register(class_3264 type, class_3302 listener, @Nullable class_2960 listenerId) {
      register(type, listener, listenerId, List.of());
   }

   @ExpectPlatform
   @Transformed
   public static void register(class_3264 type, class_3302 listener, @Nullable class_2960 listenerId, Collection<class_2960> dependencies) {
      ReloadListenerRegistryImpl.register(type, listener, listenerId, dependencies);
   }
}
