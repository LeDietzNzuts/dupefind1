package net.caffeinemc.mods.sodium.mixin.core;

import it.unimi.dsi.fastutil.longs.LongArrayFIFOQueue;
import java.util.concurrent.CompletableFuture;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.checks.ResourcePackScanner;
import net.minecraft.class_310;
import net.minecraft.class_3304;
import net.minecraft.class_3695;
import org.lwjgl.opengl.GL32C;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_310.class)
public class MinecraftMixin {
   @Shadow
   @Final
   private class_3304 field_1745;
   @Unique
   private final LongArrayFIFOQueue fences = new LongArrayFIFOQueue();

   @Inject(method = "method_1523(Z)V", at = @At("HEAD"))
   private void preRender(boolean tick, CallbackInfo ci) {
      class_3695 profiler = class_310.method_1551().method_16011();
      profiler.method_15396("wait_for_gpu");

      while (this.fences.size() > SodiumClientMod.options().advanced.cpuRenderAheadLimit) {
         long fence = this.fences.dequeueLong();
         GL32C.glClientWaitSync(fence, 1, Long.MAX_VALUE);
         GL32C.glDeleteSync(fence);
      }

      profiler.method_15407();
   }

   @Inject(method = "method_1523(Z)V", at = @At("RETURN"))
   private void postRender(boolean tick, CallbackInfo ci) {
      long fence = GL32C.glFenceSync(37143, 0);
      if (fence == 0L) {
         throw new RuntimeException("Failed to create fence object");
      } else {
         this.fences.enqueue(fence);
      }
   }

   @Inject(method = "method_53527(Lnet/minecraft/class_310$class_8764;)Ljava/lang/Runnable;", at = @At("TAIL"))
   private void postInit(CallbackInfoReturnable<Runnable> cir) {
      ResourcePackScanner.checkIfCoreShaderLoaded(this.field_1745);
   }

   @Inject(method = "method_1521()Ljava/util/concurrent/CompletableFuture;", at = @At("TAIL"))
   private void postResourceReload(CallbackInfoReturnable<CompletableFuture<Void>> cir) {
      ResourcePackScanner.checkIfCoreShaderLoaded(this.field_1745);
   }
}
