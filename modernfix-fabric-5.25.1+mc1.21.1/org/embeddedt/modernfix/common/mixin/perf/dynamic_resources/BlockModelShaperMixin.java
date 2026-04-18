package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import net.minecraft.class_1087;
import net.minecraft.class_1091;
import net.minecraft.class_1092;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_773;
import net.minecraft.class_7923;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IModelHoldingBlockState;
import org.embeddedt.modernfix.dynamicresources.ModelLocationCache;
import org.embeddedt.modernfix.util.DynamicOverridableMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_773.class)
@ClientOnlyMixin
public class BlockModelShaperMixin {
   @Shadow
   @Final
   private class_1092 field_4163;
   @Shadow
   private Map<class_2680, class_1087> field_4162;

   @Inject(method = {"<init>", "replaceCache"}, at = @At("RETURN"))
   private void replaceModelMap(CallbackInfo ci) {
      this.field_4162 = new DynamicOverridableMap<>(class_2680.class, statex -> this.field_4163.method_4742(ModelLocationCache.get(statex)));

      for (class_2248 block : class_7923.field_41175) {
         UnmodifiableIterator var4 = block.method_9595().method_11662().iterator();

         while (var4.hasNext()) {
            class_2680 state = (class_2680)var4.next();
            if (state instanceof IModelHoldingBlockState modelHolder) {
               modelHolder.mfix$setModel(null);
            }
         }
      }
   }

   private class_1087 cacheBlockModel(class_2680 state) {
      class_1091 mrl = ModelLocationCache.get(state);
      class_1087 model = mrl == null ? null : this.field_4163.method_4742(mrl);
      if (model == null) {
         model = this.field_4163.method_4744();
      }

      return model;
   }

   @Overwrite
   public class_1087 method_3335(class_2680 state) {
      if (state instanceof IModelHoldingBlockState modelHolder) {
         class_1087 model = modelHolder.mfix$getModel();
         if (model != null) {
            return model;
         } else {
            model = this.cacheBlockModel(state);
            modelHolder.mfix$setModel(model);
            return model;
         }
      } else {
         return this.cacheBlockModel(state);
      }
   }
}
