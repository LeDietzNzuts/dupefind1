package org.embeddedt.modernfix.common.mixin.perf.chunk_meshing;

import net.minecraft.class_2338;
import net.minecraft.class_9810;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.annotation.RequiresMod;
import org.embeddedt.modernfix.util.blockpos.SectionBlockPosIterator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = class_9810.class, priority = 2000)
@ClientOnlyMixin
@RequiresMod("!fluidlogged")
public class RebuildTaskMixin {
   @Redirect(
      method = "compile",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/core/BlockPos;betweenClosed(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/lang/Iterable;"
      ),
      require = 0
   )
   private Iterable<class_2338> fastBetweenClosed(class_2338 firstPos, class_2338 secondPos) {
      return () -> new SectionBlockPosIterator(firstPos);
   }
}
