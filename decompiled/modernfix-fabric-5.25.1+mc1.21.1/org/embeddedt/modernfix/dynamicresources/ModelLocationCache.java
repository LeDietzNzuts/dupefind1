package org.embeddedt.modernfix.dynamicresources;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import net.minecraft.class_1091;
import net.minecraft.class_1792;
import net.minecraft.class_2680;
import net.minecraft.class_773;
import net.minecraft.class_7923;

public class ModelLocationCache {
   private static final LoadingCache<class_2680, class_1091> blockLocationCache = CacheBuilder.newBuilder()
      .maximumSize(10000L)
      .build(new CacheLoader<class_2680, class_1091>() {
         public class_1091 load(class_2680 key) throws Exception {
            return class_773.method_3340(key);
         }
      });
   private static final LoadingCache<class_1792, class_1091> itemLocationCache = CacheBuilder.newBuilder()
      .maximumSize(10000L)
      .build(new CacheLoader<class_1792, class_1091>() {
         public class_1091 load(class_1792 key) throws Exception {
            return new class_1091(class_7923.field_41178.method_10221(key), "inventory");
         }
      });

   public static class_1091 get(class_2680 state) {
      if (state == null) {
         return null;
      } else {
         try {
            return (class_1091)blockLocationCache.get(state);
         } catch (ExecutionException var2) {
            throw new RuntimeException(var2.getCause());
         }
      }
   }

   public static class_1091 get(class_1792 item) {
      if (item == null) {
         return null;
      } else {
         try {
            return (class_1091)itemLocationCache.get(item);
         } catch (ExecutionException var2) {
            throw new RuntimeException(var2.getCause());
         }
      }
   }
}
