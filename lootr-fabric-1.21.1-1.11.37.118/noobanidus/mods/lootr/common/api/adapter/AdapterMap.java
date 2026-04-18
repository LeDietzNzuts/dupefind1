package noobanidus.mods.lootr.common.api.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import org.jetbrains.annotations.Nullable;

public class AdapterMap<A extends ILootrAdapter<?>> {
   public static final ILootrDataAdapter<Object> NONE_DATA_ADAPTER = new ILootrDataAdapter<Object>() {
      @Nullable
      @Override
      public class_5321<class_52> getLootTable(Object entity) {
         return null;
      }

      @Override
      public long getLootSeed(Object entity) {
         return 0L;
      }

      @Override
      public void setLootTable(Object entity, class_5321<class_52> table, long seed) {
      }

      @Override
      public Class<Object> getAssignableClass() {
         return Object.class;
      }

      @Override
      public int priority() {
         return Integer.MIN_VALUE;
      }
   };
   public static final ILootrItemFrameAdapter<Object> NONE_ITEM_FRAME_ADAPTER = new ILootrItemFrameAdapter<Object>() {
      @Override
      public Class<Object> getAssignableClass() {
         return Object.class;
      }

      @Override
      public class_2350 getDirection(Object object) {
         return class_2350.field_11043;
      }

      @Override
      public class_1799 getItem(Object object) {
         return class_1799.field_8037;
      }

      @Override
      public int getRotation(Object object) {
         return 0;
      }

      @Override
      public class_2338 getPos(Object object) {
         return class_2338.field_10980;
      }

      @Override
      public boolean isFixed(Object object) {
         return false;
      }

      @Override
      public boolean isInvisible(Object object) {
         return false;
      }

      @Override
      public int priority() {
         return Integer.MIN_VALUE;
      }
   };
   private final A NONE;
   private final Map<Class<?>, A> byClass = new ConcurrentHashMap<>();
   private final List<A> allAdapters = new ArrayList<>();

   public AdapterMap(A none) {
      this.NONE = none;
   }

   public void register(A adapter) {
      this.allAdapters.add(adapter);
      this.byClass.clear();
   }

   @Nullable
   public A getAdapter(@Nullable Object type) {
      if (type == null) {
         return null;
      } else {
         Class<?> clazz = type.getClass();
         A potentialAdapter = this.byClass.computeIfAbsent(clazz, clazz2 -> {
            A best = null;
            int bestDistance = Integer.MAX_VALUE;
            int bestPriority = Integer.MIN_VALUE;

            for (A adapter : this.allAdapters) {
               if (adapter.getAssignableClass().isAssignableFrom((Class<?>)clazz2)) {
                  int d = distance((Class<?>)clazz2, adapter);
                  int p = adapter.priority();
                  if (d < bestDistance || d == bestDistance && p > bestPriority) {
                     best = adapter;
                     bestDistance = d;
                     bestPriority = p;
                  }
               }
            }

            return best == null ? this.NONE : best;
         });
         return potentialAdapter == this.NONE ? null : potentialAdapter;
      }
   }

   private static int distance(Class<?> runtime, ILootrAdapter<?> target) {
      Class<?> targetClass = target.getAssignableClass();
      int d = 0;

      for (Class<?> c = runtime; c != null; c = c.getSuperclass()) {
         if (c == targetClass) {
            return d;
         }

         d++;
      }

      return Integer.MAX_VALUE;
   }
}
