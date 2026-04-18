package dev.architectury.registry.fabric;

import com.google.common.primitives.Longs;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.class_2960;
import net.minecraft.class_3264;
import net.minecraft.class_3300;
import net.minecraft.class_3302;
import net.minecraft.class_3695;
import net.minecraft.class_3302.class_4045;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

public class ReloadListenerRegistryImpl {
   private static final SecureRandom RANDOM = new SecureRandom();

   public static void register(class_3264 type, class_3302 listener, @Nullable class_2960 listenerId, Collection<class_2960> dependencies) {
      byte[] bytes = new byte[8];
      RANDOM.nextBytes(bytes);
      final class_2960 id = listenerId != null
         ? listenerId
         : class_2960.method_60654("architectury:reload_" + StringUtils.leftPad(Math.abs(Longs.fromByteArray(bytes)) + "", 19, '0'));
      ResourceManagerHelper.get(type)
         .registerReloadListener(
            new IdentifiableResourceReloadListener() {
               public class_2960 getFabricId() {
                  return id;
               }

               public String method_22322() {
                  return listener.method_22322();
               }

               public Collection<class_2960> getFabricDependencies() {
                  return dependencies;
               }

               public CompletableFuture<Void> method_25931(
                  class_4045 preparationBarrier,
                  class_3300 resourceManager,
                  class_3695 profilerFiller,
                  class_3695 profilerFiller2,
                  Executor executor,
                  Executor executor2
               ) {
                  return listener.method_25931(preparationBarrier, resourceManager, profilerFiller, profilerFiller2, executor, executor2);
               }
            }
         );
   }
}
