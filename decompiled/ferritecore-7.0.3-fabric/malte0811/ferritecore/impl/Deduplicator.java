package malte0811.ferritecore.impl;

import com.mojang.datafixers.util.Unit;
import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.objects.ObjectOpenCustomHashSet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import malte0811.ferritecore.hash.LambdaBasedHash;
import malte0811.ferritecore.mixin.accessors.BakedQuadAccess;
import malte0811.ferritecore.util.PredicateHelper;
import net.minecraft.class_1087;
import net.minecraft.class_1095;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import net.minecraft.class_3304;
import net.minecraft.class_3695;
import net.minecraft.class_4080;
import net.minecraft.class_777;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;

public class Deduplicator {
   private static final Map<String, String> VARIANT_IDENTITIES = new ConcurrentHashMap<>();
   private static final Map<List<Pair<Predicate<class_2680>, class_1087>>, class_1095> KNOWN_MULTIPART_MODELS = new ConcurrentHashMap<>();
   private static final Map<List<Predicate<class_2680>>, Predicate<class_2680>> OR_PREDICATE_CACHE = new ConcurrentHashMap<>();
   private static final Map<List<Predicate<class_2680>>, Predicate<class_2680>> AND_PREDICATE_CACHE = new ConcurrentHashMap<>();
   private static final ObjectOpenCustomHashSet<int[]> BAKED_QUAD_CACHE = new ObjectOpenCustomHashSet(
      new LambdaBasedHash<>(Deduplicator::betterIntArrayHash, Arrays::equals)
   );

   public static String deduplicateVariant(String variant) {
      return VARIANT_IDENTITIES.computeIfAbsent(variant, Function.identity());
   }

   public static class_1095 makeMultipartModel(List<Pair<Predicate<class_2680>, class_1087>> selectors) {
      return KNOWN_MULTIPART_MODELS.computeIfAbsent(selectors, class_1095::new);
   }

   public static Predicate<class_2680> or(List<Predicate<class_2680>> list) {
      return OR_PREDICATE_CACHE.computeIfAbsent(list, PredicateHelper::or);
   }

   public static Predicate<class_2680> and(List<Predicate<class_2680>> list) {
      return AND_PREDICATE_CACHE.computeIfAbsent(list, PredicateHelper::and);
   }

   public static void deduplicate(class_777 bq) {
      synchronized (BAKED_QUAD_CACHE) {
         int[] deduped = (int[])BAKED_QUAD_CACHE.addOrGet(bq.method_3357());
         ((BakedQuadAccess)bq).setVertices(deduped);
      }
   }

   public static void registerReloadListener() {
      ((class_3304)class_310.method_1551().method_1478()).method_14477(new class_4080<Unit>() {
         protected Unit prepare(@NotNull class_3300 resourceManager, @NotNull class_3695 profiler) {
            return Unit.INSTANCE;
         }

         protected void apply(@NotNull Unit object, @NotNull class_3300 resourceManager, @NotNull class_3695 profiler) {
            Deduplicator.VARIANT_IDENTITIES.clear();
            Deduplicator.KNOWN_MULTIPART_MODELS.clear();
            Deduplicator.OR_PREDICATE_CACHE.clear();
            Deduplicator.AND_PREDICATE_CACHE.clear();
            Deduplicator.BAKED_QUAD_CACHE.clear();
            Deduplicator.BAKED_QUAD_CACHE.trim();
         }
      });
   }

   private static int betterIntArrayHash(int[] in) {
      int result = 0;

      for (int i : in) {
         result = 31 * result + HashCommon.murmurHash3(i);
      }

      return result;
   }
}
