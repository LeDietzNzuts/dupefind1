package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping.campfire.lit;

import net.caffeinemc.mods.lithium.common.block.entity.SleepingBlockEntity;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_3924;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3924.class)
public abstract class CampfireBlockEntityMixin extends class_2586 implements SleepingBlockEntity {
   public CampfireBlockEntityMixin(class_2591<?> type, class_2338 pos, class_2680 state) {
      super(type, pos, state);
   }

   @Inject(
      method = "method_31666(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_3924;)V",
      at = @At("RETURN"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private static void trySleepLit(class_1937 world, class_2338 pos, class_2680 state, class_3924 campfire, CallbackInfo ci, boolean hadProgress) {
      if (!hadProgress) {
         CampfireBlockEntityMixin self = (CampfireBlockEntityMixin)campfire;
         self.lithium$startSleeping();
      }
   }
}
