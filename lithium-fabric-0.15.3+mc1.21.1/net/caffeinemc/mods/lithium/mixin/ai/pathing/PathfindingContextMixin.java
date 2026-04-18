package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1308;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_9316;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_9316.class)
public class PathfindingContextMixin {
   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_1941;Lnet/minecraft/class_1308;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1308;method_37908()Lnet/minecraft/class_1937;")
   )
   private class_1937 getWorldIfNonNull(class_1308 instance, Operation<class_1937> original) {
      return instance == null ? null : (class_1937)original.call(new Object[]{instance});
   }

   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_1941;Lnet/minecraft/class_1308;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1308;method_24515()Lnet/minecraft/class_2338;")
   )
   private class_2338 getBlockPosIfNonNull(class_1308 instance, Operation<class_2338> original) {
      return instance == null ? null : (class_2338)original.call(new Object[]{instance});
   }
}
