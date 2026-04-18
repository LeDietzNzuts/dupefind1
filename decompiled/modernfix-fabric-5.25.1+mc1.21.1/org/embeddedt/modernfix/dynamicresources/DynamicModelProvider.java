package org.embeddedt.modernfix.dynamicresources;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1100;
import net.minecraft.class_2960;

public class DynamicModelProvider {
   private final Map<class_2960, class_1100> internalModels;
   private final Cache<class_2960, Optional<class_1100>> loadedModels = CacheBuilder.newBuilder()
      .expireAfterAccess(3L, TimeUnit.MINUTES)
      .maximumSize(1000L)
      .concurrencyLevel(8)
      .softValues()
      .build();

   public DynamicModelProvider(Map<class_2960, class_1100> initialModels) {
      this.internalModels = initialModels;
   }

   public class_1100 getModel(class_2960 location) {
      try {
         return (class_1100)((Optional)this.loadedModels.get(location, () -> Optional.ofNullable(this.loadModel(location)))).orElse(null);
      } catch (ExecutionException var3) {
         throw new RuntimeException(var3.getCause());
      }
   }

   private class_1100 loadModel(class_2960 location) {
      return null;
   }
}
