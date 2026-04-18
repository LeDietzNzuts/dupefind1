package org.embeddedt.modernfix.common.mixin.perf.lazy_search_tree_registry;

import com.google.common.base.Stopwatch;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.class_1124;
import net.minecraft.class_1129;
import net.minecraft.class_516;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(class_1124.class)
@ClientOnlyMixin
public class SessionSearchTreesMixin {
   @Shadow
   private CompletableFuture<class_1129<class_516>> field_51828;
   private Supplier<class_1129<class_516>> mfix$deferredSearchTreeSupplier;

   @ModifyArg(
      method = {"method_60367", "lambda$updateRecipes$8"},
      at = @At(
         value = "INVOKE",
         target = "Ljava/util/concurrent/CompletableFuture;supplyAsync(Ljava/util/function/Supplier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"
      )
   )
   private Supplier<class_1129<class_516>> mfix$deferProcessing(Supplier<class_1129<class_516>> supplier) {
      this.mfix$deferredSearchTreeSupplier = supplier;
      return class_1129::empty;
   }

   @WrapMethod(method = "recipes")
   private class_1129<class_516> mfix$processDeferredBuild(Operation<class_1129<class_516>> original) {
      synchronized (this) {
         if (this.mfix$deferredSearchTreeSupplier != null) {
            Stopwatch watch = Stopwatch.createStarted();
            this.field_51828 = CompletableFuture.completedFuture(this.mfix$deferredSearchTreeSupplier.get());
            watch.stop();
            ModernFix.LOGGER.info("Building recipe book search tree took {}", watch);
            this.mfix$deferredSearchTreeSupplier = null;
         }

         return (class_1129<class_516>)original.call(new Object[0]);
      }
   }
}
