package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.class_1086;
import net.minecraft.class_1087;
import net.minecraft.class_1088;
import net.minecraft.class_1091;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import net.minecraft.class_324;
import net.minecraft.class_3695;
import net.minecraft.class_7922;
import net.minecraft.class_9824;
import net.minecraft.class_1088.class_7776;
import net.minecraft.class_1088.class_9826;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.ModernFixClient;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.api.entrypoint.ModernFixClientIntegration;
import org.embeddedt.modernfix.duck.IBlockStateModelLoader;
import org.embeddedt.modernfix.duck.IExtendedModelBakery;
import org.embeddedt.modernfix.util.DynamicOverridableMap;
import org.embeddedt.modernfix.util.LRUMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1088.class)
@ClientOnlyMixin
public abstract class ModelBakeryMixin implements IExtendedModelBakery {
   @Unique
   private class_9824 dynamicLoader;
   @Unique
   private final ReentrantLock modelBakeryLock = new ReentrantLock();
   @Unique
   private class_9826 textureGetter;
   @Unique
   private class_1087 bakedMissingModel;
   @Shadow
   @Final
   private class_1100 field_52275;
   @Unique
   private static final boolean DEBUG_MODEL_LOADS = Boolean.getBoolean("modernfix.debugDynamicModelLoading");
   @Shadow
   @Mutable
   @Final
   private Map<class_1091, class_1087> field_5387;
   @Shadow
   @Mutable
   @Final
   public Map<class_1091, class_1100> field_5394;
   @Shadow
   @Mutable
   @Final
   private Map<class_2960, class_1100> field_5376;
   @Shadow
   @Mutable
   @Final
   public Map<class_7776, class_1087> field_5398;
   @Shadow
   @Final
   public static class_1091 field_52276;
   private final Map<class_1091, class_1087> mfix$emulatedBakedRegistry = new DynamicOverridableMap<>(class_1091.class, this::loadBakedModelDynamic);
   private boolean inInitialLoad = true;
   @Unique
   private int tickCount;
   @Unique
   private static final int MAXIMUM_CACHE_SIZE = 1000;

   @Shadow
   abstract class_1100 method_4726(class_2960 var1);

   @Shadow(aliases = "lambda$bakeModels$6")
   protected abstract void method_61072(class_9826 var1, class_1091 var2, class_1100 var3);

   @Shadow
   protected abstract void method_61075(class_2960 var1);

   @Shadow
   protected abstract void method_61074(class_1091 var1, class_1100 var2);

   @Override
   public class_1100 mfix$loadUnbakedModelDynamic(class_1091 location) {
      if (location.equals(field_52276)) {
         return this.field_52275;
      } else {
         this.modelBakeryLock.lock();

         class_1100 unbakedModel;
         try {
            class_1100 existing = this.field_5394.get(location);
            if (existing == null) {
               if (DEBUG_MODEL_LOADS) {
                  ModernFix.LOGGER.info("Loading model {}", location);
               }

               if (location.comp_2876().equals("inventory")) {
                  this.method_61075(location.comp_2875());
               } else if (!location.comp_2876().equals("fabric_resource") && !location.comp_2876().equals("standalone")) {
                  ((IBlockStateModelLoader)this.dynamicLoader).loadSpecificBlock(location);
               } else {
                  unbakedModel = this.method_4726(location.comp_2875());
                  this.method_61074(location, unbakedModel);
               }

               return this.field_5394.getOrDefault(location, this.field_52275);
            }

            unbakedModel = existing;
         } finally {
            this.modelBakeryLock.unlock();
         }

         return unbakedModel;
      }
   }

   @WrapMethod(method = "getModel")
   private class_1100 mfix$lockWhenGettingModel(class_2960 modelLocation, Operation<class_1100> original) {
      this.modelBakeryLock.lock();

      class_1100 var3;
      try {
         var3 = (class_1100)original.call(new Object[]{modelLocation});
      } finally {
         this.modelBakeryLock.unlock();
      }

      return var3;
   }

   @Override
   public class_1100 mfix$getMissingModel() {
      return this.field_52275;
   }

   @Unique
   private class_1087 loadBakedModelDynamic(class_1091 location) {
      if (location.equals(field_52276)) {
         return this.bakedMissingModel;
      } else {
         this.modelBakeryLock.lock();

         class_1087 model;
         try {
            model = this.field_5387.get(location);
            if (model == null) {
               class_1100 prototype = this.mfix$loadUnbakedModelDynamic(location);
               if (prototype == this.field_52275) {
                  model = this.bakedMissingModel;
               } else {
                  prototype.method_45785(this::method_4726);
                  if (DEBUG_MODEL_LOADS) {
                     ModernFix.LOGGER.info("Baking model {}", location);
                  }

                  this.method_61072(this.textureGetter, location, prototype);
                  model = this.field_5387.remove(location);
                  if (model == null) {
                     ModernFix.LOGGER.error("Failed to load model " + location);
                     model = this.bakedMissingModel;
                  }

                  for (ModernFixClientIntegration integration : ModernFixClient.CLIENT_INTEGRATIONS) {
                     try {
                        model = integration.onBakedModelLoad(location, prototype, model, class_1086.field_5350, (class_1088)this, this.textureGetter);
                     } catch (RuntimeException var10) {
                        ModernFix.LOGGER.error("Exception encountered running dynamic resources integration", var10);
                     }
                  }
               }
            }
         } finally {
            this.modelBakeryLock.unlock();
         }

         return model;
      }
   }

   @ModifyExpressionValue(method = "<init>", at = @At(value = "CONSTANT", args = "stringValue=missing_model"))
   private String replaceBackingMaps(String original) {
      this.field_5376 = new LRUMap<class_2960, class_1100>(this.field_5376);
      this.field_5398 = new LRUMap<class_7776, class_1087>(this.field_5398);
      this.field_5394 = new LRUMap<class_1091, class_1100>(this.field_5394);
      this.field_5387 = new LRUMap<class_1091, class_1087>(this.field_5387);
      return original;
   }

   @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/resources/model/BlockStateModelLoader;loadAllBlockStates()V"))
   private void noInitialBlockStateLoad(class_9824 instance, Operation<Void> original) {
      this.dynamicLoader = instance;
      original.call(new Object[]{instance});
   }

   @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/DefaultedRegistry;keySet()Ljava/util/Set;"))
   private Set<?> skipLoadingItems(class_7922 instance) {
      return Collections.emptySet();
   }

   @Inject(method = "bakeModels", at = @At("HEAD"))
   private void storeTextureGetterAndBakeMissing(class_9826 textureGetter, CallbackInfo ci) {
      this.textureGetter = textureGetter;
      this.method_61072(textureGetter, field_52276, Objects.requireNonNull(this.field_5394.get(field_52276)));
      this.bakedMissingModel = this.field_5387.get(field_52276);
   }

   @Inject(method = "bakeModels", at = @At("RETURN"))
   private void onInitialBakeFinish(class_9826 textureGetter, CallbackInfo ci) {
      ObjectOpenHashSet<class_1091> permanentMRLs = new ObjectOpenHashSet(this.field_5387.keySet());
      ((LRUMap)this.field_5387).setPermanentEntries(permanentMRLs);
      ModernFix.LOGGER.info("Dynamic model bakery initial baking finished, with {} permanent top level baked models", this.field_5387.size());
   }

   @Inject(method = "<init>", at = @At("RETURN"))
   private void onInitialLoadFinish(class_324 blockColors, class_3695 profilerFiller, Map map, Map map2, CallbackInfo ci) {
      ObjectOpenHashSet<class_1091> permanentMRLs = new ObjectOpenHashSet(this.field_5394.keySet());
      ((LRUMap)this.field_5394).setPermanentEntries(permanentMRLs);
      ModernFix.LOGGER.info("Dynamic model bakery loading finished, with {} permanent top level models", this.field_5394.size());
   }

   private void runCleanup() {
      try {
         ((LRUMap)this.field_5376).dropEntriesToMeetSize(1000);
      } catch (RuntimeException var5) {
         throw new IllegalStateException("Exception dropping entries in unbaked cache", var5);
      }

      try {
         ((LRUMap)this.field_5398).dropEntriesToMeetSize(1000);
      } catch (RuntimeException var4) {
         throw new IllegalStateException("Exception dropping entries in baked cache", var4);
      }

      try {
         ((LRUMap)this.field_5394).dropEntriesToMeetSize(1000);
      } catch (RuntimeException var3) {
         throw new IllegalStateException("Exception dropping entries in top level models", var3);
      }

      try {
         ((LRUMap)this.field_5387).dropEntriesToMeetSize(1000);
      } catch (RuntimeException var2) {
         throw new IllegalStateException("Exception dropping entries in baked top level models", var2);
      }
   }

   @Override
   public void mfix$finishLoading() {
      this.inInitialLoad = false;
   }

   @Override
   public void mfix$tick() {
      if (!this.inInitialLoad) {
         this.tickCount++;
         if (this.tickCount % 200 == 0 && this.modelBakeryLock.tryLock()) {
            try {
               this.runCleanup();
            } finally {
               this.modelBakeryLock.unlock();
            }
         }
      }
   }

   @Overwrite
   public Map<class_1091, class_1087> method_4734() {
      return this.mfix$emulatedBakedRegistry;
   }

   @Override
   public ReentrantLock mfix$getLock() {
      return this.modelBakeryLock;
   }
}
