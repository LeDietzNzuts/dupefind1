package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1087;
import net.minecraft.class_1091;
import net.minecraft.class_1092;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_763;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.dynamicresources.DynamicModelCache;
import org.embeddedt.modernfix.dynamicresources.ModelLocationCache;
import org.embeddedt.modernfix.util.DynamicInt2ObjectMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_763.class)
@ClientOnlyMixin
public abstract class ItemModelShaperMixin {
   @Shadow
   @Final
   @Mutable
   private Int2ObjectMap<class_1087> field_4130;
   private Map<class_1792, class_1091> overrideLocationsVanilla;
   private static final class_1091 SENTINEL_VANILLA = new class_1091(class_2960.method_60655("modernfix", "sentinel"), "sentinel");
   private final DynamicModelCache<class_1792> mfix$itemModelCache = new DynamicModelCache<>(k -> this.mfix$getModelForItem((class_1792)k), true);

   @Shadow
   public abstract class_1092 method_3303();

   @Inject(method = "<init>", at = @At("RETURN"))
   private void replaceLocationMap(CallbackInfo ci) {
      this.overrideLocationsVanilla = new HashMap<>();
      this.field_4130 = new DynamicInt2ObjectMap<>(index -> this.method_3303().method_4742(ModelLocationCache.get(class_1792.method_7875(index))));
   }

   @Unique
   private class_1091 mfix$getLocation(class_1792 item) {
      class_1091 map = this.overrideLocationsVanilla.getOrDefault(item, SENTINEL_VANILLA);
      if (map == SENTINEL_VANILLA) {
         map = ModelLocationCache.get(item);
      }

      return map;
   }

   private class_1087 mfix$getModelForItem(class_1792 item) {
      class_1091 map = this.mfix$getLocation(item);
      return map == null ? null : this.method_3303().method_4742(map);
   }

   @Overwrite
   public class_1087 method_3304(class_1792 item) {
      return this.mfix$itemModelCache.get(item);
   }

   @Overwrite
   public void method_3309(class_1792 item, class_1091 location) {
      this.overrideLocationsVanilla.put(item, location);
   }

   @Overwrite
   public void method_3310() {
      this.mfix$itemModelCache.clear();
   }
}
