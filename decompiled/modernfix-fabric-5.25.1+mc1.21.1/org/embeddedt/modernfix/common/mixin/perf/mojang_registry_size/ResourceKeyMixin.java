package org.embeddedt.modernfix.common.mixin.perf.mojang_registry_size;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5321.class)
public class ResourceKeyMixin<T> {
   private static final Map<class_2960, Map<class_2960, class_5321<?>>> INTERNING_MAP = new Object2ObjectOpenHashMap();

   @Inject(
      method = "create(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/resources/ResourceKey;",
      at = @At("HEAD"),
      cancellable = true
   )
   private static <T> void createEfficient(class_2960 parent, class_2960 location, CallbackInfoReturnable<class_5321<T>> cir) {
      synchronized (class_5321.class) {
         Map<class_2960, class_5321<?>> keys = INTERNING_MAP.computeIfAbsent(parent, k -> new Object2ObjectOpenHashMap());
         class_5321<?> key = keys.get(location);
         if (key == null) {
            key = new class_5321(parent, location);
            keys.put(location, key);
         }

         cir.setReturnValue(key);
      }
   }
}
