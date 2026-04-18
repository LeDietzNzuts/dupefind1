package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.world.block_entity_ticking.support_cache;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_2338;
import net.minecraft.class_2343;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin {
   @Shadow
   public abstract class_2680 method_8320(class_2338 var1);

   @Redirect(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2343;method_10123(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;)Lnet/minecraft/class_2586;"
      )
   )
   private class_2586 createBlockEntityWithCachedStateFix(class_2343 blockEntityProvider, class_2338 pos, class_2680 state) {
      class_2680 blockState = this.method_8320(pos);
      if (state == blockState) {
         return blockEntityProvider.method_10123(pos, state);
      } else {
         return blockState.method_31709() ? ((class_2343)blockState.method_26204()).method_10123(pos, blockState) : null;
      }
   }

   @Inject(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2586;method_31664(Lnet/minecraft/class_2680;)V", shift = Shift.AFTER)
   )
   private void fixCachedState(class_2338 pos, class_2680 state, boolean moved, CallbackInfoReturnable<class_2680> cir, @Local class_2586 blockEntity) {
      class_2680 updatedBlockState = this.method_8320(pos);
      if (updatedBlockState != state) {
         blockEntity.method_31664(updatedBlockState);
      }
   }
}
