package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.world.block_entity_ticking.support_cache;

import net.caffeinemc.mods.lithium.common.world.blockentity.SupportCache;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(targets = "net/minecraft/class_2818$class_5563")
public class DirectBlockEntityTickInvokerMixin<T extends class_2586> {
   @Shadow
   @Final
   private T field_27224;

   @Redirect(
      method = "method_31703()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2818;method_8320(Lnet/minecraft/class_2338;)Lnet/minecraft/class_2680;"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/class_3695;method_15400(Ljava/util/function/Supplier;)V"),
         to = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/class_5558;tick(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2586;)V"
         )
      )
   )
   private class_2680 getCachedState(class_2818 chunk, class_2338 pos) {
      return this.field_27224.method_11010();
   }

   @Redirect(
      method = "method_31703()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2591;method_20526(Lnet/minecraft/class_2680;)Z"),
      slice = @Slice(
         from = @At(value = "INVOKE", target = "Lnet/minecraft/class_3695;method_15400(Ljava/util/function/Supplier;)V"),
         to = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/class_5558;tick(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Lnet/minecraft/class_2586;)V"
         )
      )
   )
   private boolean cachedIsSupported(class_2591<?> blockEntityType, class_2680 block) {
      return ((SupportCache)this.field_27224).lithium$isSupported();
   }
}
