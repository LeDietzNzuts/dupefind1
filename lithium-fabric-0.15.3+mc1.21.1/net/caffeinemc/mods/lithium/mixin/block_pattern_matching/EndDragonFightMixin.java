package net.caffeinemc.mods.lithium.mixin.block_pattern_matching;

import net.caffeinemc.mods.lithium.common.world.block_pattern_matching.BlockPatternExtended;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2700;
import net.minecraft.class_2881;
import net.minecraft.class_3218;
import net.minecraft.class_2881.class_8576;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2881.class)
public class EndDragonFightMixin {
   @Shadow
   @Final
   private class_2700 field_13110;

   @Inject(method = "<init>(Lnet/minecraft/class_3218;JLnet/minecraft/class_2881$class_8576;Lnet/minecraft/class_2338;)V", at = @At("RETURN"))
   private void setPatternToDragonPattern(class_3218 serverLevel, long l, class_8576 data, class_2338 blockPos, CallbackInfo ci) {
      ((BlockPatternExtended)this.field_13110).lithium$setRequiredBlock(class_2246.field_9987, 41);
   }
}
