package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.caffeinemc.mods.lithium.common.ai.pathing.PathNodeCache;
import net.minecraft.class_6;
import net.minecraft.class_7;
import net.minecraft.class_9316;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_6.class)
public class FlyNodeEvaluatorMixin {
   @Redirect(
      method = "method_17(Lnet/minecraft/class_9316;III)Lnet/minecraft/class_7;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_6;method_59(Lnet/minecraft/class_9316;IIILnet/minecraft/class_7;)Lnet/minecraft/class_7;")
   )
   private class_7 getNodeTypeFromNeighbors(class_9316 pathContext, int x, int y, int z, class_7 pathNodeType) {
      return PathNodeCache.getNodeTypeFromNeighbors(pathContext, x, y, z, pathNodeType);
   }
}
