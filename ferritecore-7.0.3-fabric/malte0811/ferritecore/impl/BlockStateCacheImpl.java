package malte0811.ferritecore.impl;

import com.google.common.base.Suppliers;
import it.unimi.dsi.fastutil.booleans.BooleanArrays;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import malte0811.ferritecore.ducks.BlockStateCacheAccess;
import malte0811.ferritecore.hash.ArrayVoxelShapeHash;
import malte0811.ferritecore.hash.VoxelShapeHash;
import malte0811.ferritecore.mixin.accessors.ArrayVSAccess;
import malte0811.ferritecore.mixin.accessors.SliceShapeAccess;
import malte0811.ferritecore.util.Constants;
import net.minecraft.class_245;
import net.minecraft.class_265;
import net.minecraft.class_4970.class_4971;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class BlockStateCacheImpl {
   public static final Map<ArrayVSAccess, ArrayVSAccess> CACHE_COLLIDE = new Object2ObjectOpenCustomHashMap(ArrayVoxelShapeHash.INSTANCE);
   public static final Map<class_265, Pair<class_265, class_265[]>> CACHE_PROJECT = new Object2ObjectOpenCustomHashMap(VoxelShapeHash.INSTANCE);
   public static final Map<boolean[], boolean[]> CACHE_FACE_STURDY = new Object2ObjectOpenCustomHashMap(BooleanArrays.HASH_STRATEGY);
   private static final Supplier<Function<class_4971, BlockStateCacheAccess>> GET_CACHE = Suppliers.memoize(() -> {
      try {
         String cacheName = Constants.PLATFORM_HOOKS.computeBlockstateCacheFieldName();
         Field cacheField = class_4971.class.getDeclaredField(cacheName);
         cacheField.setAccessible(true);
         MethodHandle getter = MethodHandles.lookup().unreflectGetter(cacheField);
         return state -> {
            try {
               return (BlockStateCacheAccess)getter.invoke((class_4971)state);
            } catch (Throwable var3x) {
               throw new RuntimeException(var3x);
            }
         };
      } catch (IllegalAccessException | NoSuchFieldException var3) {
         throw new RuntimeException(var3);
      }
   });
   private static final ThreadLocal<BlockStateCacheAccess> LAST_CACHE = new ThreadLocal<>();

   public static void deduplicateCachePre(class_4971 state) {
      LAST_CACHE.set(GET_CACHE.get().apply(state));
   }

   public static void deduplicateCachePost(class_4971 state) {
      BlockStateCacheAccess newCache = GET_CACHE.get().apply(state);
      if (newCache != null) {
         BlockStateCacheAccess oldCache = LAST_CACHE.get();
         deduplicateCollisionShape(newCache, oldCache);
         deduplicateRenderShapes(newCache, oldCache);
         deduplicateFaceSturdyArray(newCache, oldCache);
         LAST_CACHE.set(null);
      }
   }

   private static void deduplicateCollisionShape(BlockStateCacheAccess newCache, @Nullable BlockStateCacheAccess oldCache) {
      class_265 dedupedCollisionShape;
      if (oldCache != null && VoxelShapeHash.INSTANCE.equals(oldCache.getCollisionShape(), newCache.getCollisionShape())) {
         dedupedCollisionShape = oldCache.getCollisionShape();
      } else {
         dedupedCollisionShape = newCache.getCollisionShape();
         if (dedupedCollisionShape instanceof ArrayVSAccess access) {
            dedupedCollisionShape = (class_265)CACHE_COLLIDE.computeIfAbsent(access, Function.identity());
         }
      }

      replaceInternals(dedupedCollisionShape, newCache.getCollisionShape());
      newCache.setCollisionShape(dedupedCollisionShape);
   }

   private static void deduplicateRenderShapes(BlockStateCacheAccess newCache, @Nullable BlockStateCacheAccess oldCache) {
      class_265 newRenderShape = getRenderShape(newCache.getOcclusionShapes());
      if (newRenderShape != null) {
         Pair<class_265, class_265[]> dedupedRenderShapes = null;
         if (oldCache != null) {
            class_265 oldRenderShape = getRenderShape(oldCache.getOcclusionShapes());
            if (VoxelShapeHash.INSTANCE.equals(newRenderShape, oldRenderShape)) {
               dedupedRenderShapes = Pair.of(oldRenderShape, oldCache.getOcclusionShapes());
            }
         }

         if (dedupedRenderShapes == null) {
            Pair<class_265, class_265[]> newPair = Pair.of(newRenderShape, newCache.getOcclusionShapes());
            dedupedRenderShapes = CACHE_PROJECT.putIfAbsent(newRenderShape, newPair);
            if (dedupedRenderShapes == null) {
               dedupedRenderShapes = newPair;
            }
         }

         replaceInternals((class_265)dedupedRenderShapes.getLeft(), newRenderShape);
         newCache.setOcclusionShapes((class_265[])dedupedRenderShapes.getRight());
      }
   }

   private static void deduplicateFaceSturdyArray(BlockStateCacheAccess newCache, @Nullable BlockStateCacheAccess oldCache) {
      boolean[] dedupedFaceSturdy;
      if (oldCache != null && Arrays.equals(oldCache.getFaceSturdy(), newCache.getFaceSturdy())) {
         dedupedFaceSturdy = oldCache.getFaceSturdy();
      } else {
         dedupedFaceSturdy = CACHE_FACE_STURDY.computeIfAbsent(newCache.getFaceSturdy(), Function.identity());
      }

      newCache.setFaceSturdy(dedupedFaceSturdy);
   }

   private static void replaceInternals(class_265 toKeep, class_265 toReplace) {
      if (toKeep instanceof class_245 keepArray && toReplace instanceof class_245 replaceArray) {
         replaceInternals(keepArray, replaceArray);
      }
   }

   public static void replaceInternals(class_245 toKeep, class_245 toReplace) {
      if (toKeep != toReplace) {
         ArrayVSAccess toReplaceAccess = (ArrayVSAccess)toReplace;
         ArrayVSAccess toKeepAccess = (ArrayVSAccess)toKeep;
         toReplaceAccess.setXPoints(toKeepAccess.getXPoints());
         toReplaceAccess.setYPoints(toKeepAccess.getYPoints());
         toReplaceAccess.setZPoints(toKeepAccess.getZPoints());
         toReplaceAccess.setFaces(toKeepAccess.getFaces());
         toReplaceAccess.setShape(toKeepAccess.getShape());
      }
   }

   @Nullable
   private static class_265 getRenderShape(@Nullable class_265[] projected) {
      if (projected != null) {
         for (class_265 side : projected) {
            if (side instanceof SliceShapeAccess slice) {
               return slice.getDelegate();
            }
         }
      }

      return null;
   }
}
