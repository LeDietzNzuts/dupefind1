package net.caffeinemc.mods.lithium.mixin.ai.poi;

import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMaps;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.world.interests.PointOfInterestSetExtended;
import net.caffeinemc.mods.lithium.common.world.interests.iterator.SinglePointOfInterestTypeFilter;
import net.minecraft.class_4156;
import net.minecraft.class_4157;
import net.minecraft.class_4158;
import net.minecraft.class_6880;
import net.minecraft.class_4153.class_4155;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4157.class)
public class PoiSectionMixin implements PointOfInterestSetExtended {
   @Mutable
   @Shadow
   @Final
   private Map<class_6880<class_4158>, Set<class_4156>> field_18498;

   private static <K, V> Iterable<? extends Entry<K, V>> getPointsByTypeIterator(Map<K, V> map) {
      return (Iterable<? extends Entry<K, V>>)(map instanceof Reference2ReferenceMap
         ? Reference2ReferenceMaps.fastIterable((Reference2ReferenceMap)map)
         : map.entrySet());
   }

   @Inject(method = "<init>(Ljava/lang/Runnable;ZLjava/util/List;)V", at = @At("RETURN"))
   private void reinit(Runnable updateListener, boolean bl, List<class_4156> list, CallbackInfo ci) {
      this.field_18498 = new Reference2ReferenceOpenHashMap(this.field_18498);
   }

   @Override
   public void lithium$collectMatchingPoints(Predicate<class_6880<class_4158>> type, class_4155 status, Consumer<class_4156> consumer) {
      if (type instanceof SinglePointOfInterestTypeFilter) {
         this.getWithSingleTypeFilter(((SinglePointOfInterestTypeFilter)type).getType(), status, consumer);
      } else {
         this.getWithDynamicTypeFilter(type, status, consumer);
      }
   }

   private void getWithDynamicTypeFilter(Predicate<class_6880<class_4158>> type, class_4155 status, Consumer<class_4156> consumer) {
      for (Entry<class_6880<class_4158>, Set<class_4156>> entry : getPointsByTypeIterator(this.field_18498)) {
         if (type.test(entry.getKey()) && !entry.getValue().isEmpty()) {
            for (class_4156 poi : entry.getValue()) {
               if (status.method_19135().test(poi)) {
                  consumer.accept(poi);
               }
            }
         }
      }
   }

   private void getWithSingleTypeFilter(class_6880<class_4158> type, class_4155 status, Consumer<class_4156> consumer) {
      Set<class_4156> entries = this.field_18498.get(type);
      if (entries != null && !entries.isEmpty()) {
         for (class_4156 poi : entries) {
            if (status.method_19135().test(poi)) {
               consumer.accept(poi);
            }
         }
      }
   }

   @Redirect(
      method = "method_20350(Lnet/minecraft/class_4156;)Z",
      at = @At(value = "INVOKE", target = "Ljava/util/Map;computeIfAbsent(Ljava/lang/Object;Ljava/util/function/Function;)Ljava/lang/Object;")
   )
   private <K, V> K computeIfAbsent(Map<K, V> map, K key, Function<? super K, ? extends V> mappingFunction) {
      return (K)map.computeIfAbsent(key, o -> (V)(new ObjectOpenHashSet()));
   }
}
