package org.embeddedt.modernfix.common.mixin.perf.fix_loop_spin_waiting;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BooleanSupplier;
import net.minecraft.class_1255;
import net.minecraft.class_156;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = MinecraftServer.class, priority = 500)
public abstract class MinecraftServerMixin extends class_1255<Runnable> {
   @Shadow
   private long field_47139;
   @Unique
   private boolean mfix$isWaitingForNextTick = false;

   protected MinecraftServerMixin(String name) {
      super(name);
   }

   @WrapOperation(
      method = "waitUntilNextTick",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;managedBlock(Ljava/util/function/BooleanSupplier;)V")
   )
   private void managedBlock(MinecraftServer instance, BooleanSupplier isDone, Operation<Void> original) {
      try {
         this.mfix$isWaitingForNextTick = true;
         original.call(new Object[]{instance, isDone});
      } finally {
         this.mfix$isWaitingForNextTick = false;
      }
   }

   @WrapOperation(method = "waitForTasks", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/thread/ReentrantBlockableEventLoop;waitForTasks()V"))
   private void waitLongerForTasks(MinecraftServer instance, Operation<Void> original) {
      if (this.mfix$isWaitingForNextTick) {
         LockSupport.parkNanos("waiting for tasks", this.field_47139 - class_156.method_648());
      } else {
         original.call(new Object[]{instance});
      }
   }
}
