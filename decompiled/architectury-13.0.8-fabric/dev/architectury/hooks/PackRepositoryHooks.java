package dev.architectury.hooks;

import dev.architectury.hooks.fabric.PackRepositoryHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_3283;
import net.minecraft.class_3285;

public class PackRepositoryHooks {
   private PackRepositoryHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static void addSource(class_3283 repository, class_3285 source) {
      PackRepositoryHooksImpl.addSource(repository, source);
   }
}
