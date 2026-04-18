package org.embeddedt.modernfix.common.mixin.perf.dynamic_resources;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.class_1087;
import net.minecraft.class_1088;
import net.minecraft.class_1100;
import net.minecraft.class_2960;
import net.minecraft.class_3665;
import net.minecraft.class_1088.class_7778;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.duck.IExtendedModelBakery;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_7778.class)
@ClientOnlyMixin
public abstract class ModelBakerImplMixin {
   @Shadow(aliases = "this$0")
   @Final
   private class_1088 field_40571;
   @Unique
   private int mfix$getDepth = 0;

   @Shadow
   public abstract class_1100 method_45872(class_2960 var1);

   @ModifyReturnValue(method = "getModel", at = @At("RETURN"))
   private class_1100 resolveParents(class_1100 model) {
      this.mfix$getDepth++;
      if (this.mfix$getDepth == 1) {
         try {
            model.method_45785(this::method_45872);
         } catch (Exception var3) {
            ModernFix.LOGGER.warn("Exception encountered resolving parents", var3);
         }
      }

      this.mfix$getDepth--;
      return model;
   }

   @WrapMethod(
      method = "bake(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/resources/model/ModelState;)Lnet/minecraft/client/resources/model/BakedModel;"
   )
   private class_1087 mfix$lockWhenBaking(class_2960 location, class_3665 transform, Operation<class_1087> original) {
      ReentrantLock lock = ((IExtendedModelBakery)this.field_40571).mfix$getLock();
      lock.lock();

      class_1087 var5;
      try {
         var5 = (class_1087)original.call(new Object[]{location, transform});
      } finally {
         lock.unlock();
      }

      return var5;
   }
}
