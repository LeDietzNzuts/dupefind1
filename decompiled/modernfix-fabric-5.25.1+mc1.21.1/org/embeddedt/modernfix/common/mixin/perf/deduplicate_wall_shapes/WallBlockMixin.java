package org.embeddedt.modernfix.common.mixin.perf.deduplicate_wall_shapes;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_2248;
import net.minecraft.class_2544;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_2769;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2544.class)
public abstract class WallBlockMixin extends class_2248 {
   private static Map<ImmutableList<Float>, Pair<Map<Map<class_2769<?>, Comparable<?>>, class_265>, class_2689<class_2248, class_2680>>> CACHE_BY_SHAPE_VALS = new HashMap<>();

   public WallBlockMixin(class_2251 properties) {
      super(properties);
   }

   @Inject(method = "makeShapes", at = @At("HEAD"), cancellable = true)
   private synchronized void useCachedShapeMap(
      float f1, float f2, float f3, float f4, float f5, float f6, CallbackInfoReturnable<Map<class_2680, class_265>> cir
   ) {
      ImmutableList<Float> key = ImmutableList.of(f1, f2, f3, f4, f5, f6);
      Pair<Map<Map<class_2769<?>, Comparable<?>>, class_265>, class_2689<class_2248, class_2680>> cache = CACHE_BY_SHAPE_VALS.get(key);
      if (cache != null && ((class_2689)cache.getSecond()).method_11659().equals(this.field_10647.method_11659())) {
         Builder<class_2680, class_265> builder = ImmutableMap.builder();
         UnmodifiableIterator var11 = this.field_10647.method_11662().iterator();

         while (var11.hasNext()) {
            class_2680 state = (class_2680)var11.next();
            class_265 shape = (class_265)((Map)cache.getFirst()).get(state.method_11656());
            if (shape == null) {
               return;
            }

            builder.put(state, shape);
         }

         cir.setReturnValue(builder.build());
      }
   }

   @Inject(method = "makeShapes", at = @At("RETURN"))
   private synchronized void storeCachedShapesByProperty(
      float f1, float f2, float f3, float f4, float f5, float f6, CallbackInfoReturnable<Map<class_2680, class_265>> cir
   ) {
      if (this.getClass() == class_2544.class) {
         ImmutableList<Float> key = ImmutableList.of(f1, f2, f3, f4, f5, f6);
         if (!CACHE_BY_SHAPE_VALS.containsKey(key)) {
            Map<Map<class_2769<?>, Comparable<?>>, class_265> cacheByProperties = new HashMap<>();
            Map<class_2680, class_265> shapeMap = (Map<class_2680, class_265>)cir.getReturnValue();

            for (Entry<class_2680, class_265> entry : shapeMap.entrySet()) {
               cacheByProperties.put(entry.getKey().method_11656(), entry.getValue());
            }

            CACHE_BY_SHAPE_VALS.put(key, Pair.of(cacheByProperties, this.field_10647));
         }
      }
   }
}
