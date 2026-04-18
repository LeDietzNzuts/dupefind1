package net.caffeinemc.mods.lithium.mixin.block.flatten_states;

import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3610.class)
public abstract class FluidStateMixin {
   private boolean isEmptyCache;

   @Shadow
   public abstract class_3611 method_15772();

   @Inject(
      method = "<init>(Lnet/minecraft/class_3611;Lit/unimi/dsi/fastutil/objects/Reference2ObjectArrayMap;Lcom/mojang/serialization/MapCodec;)V",
      at = @At("RETURN")
   )
   private void initFluidCache(class_3611 fluid, Reference2ObjectArrayMap<?, ?> propertyMap, MapCodec<?> codec, CallbackInfo ci) {
      this.isEmptyCache = this.method_15772().method_15794();
   }

   @Overwrite
   public boolean method_15769() {
      return this.isEmptyCache;
   }
}
