package net.caffeinemc.mods.lithium.mixin.util.data_storage;

import java.util.function.Supplier;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.minecraft.class_1937;
import net.minecraft.class_5269;
import net.minecraft.class_5321;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1937.class)
public class LevelMixin implements LithiumData {
   @Unique
   private LithiumData.Data storage;

   @Inject(
      method = "<init>(Lnet/minecraft/class_5269;Lnet/minecraft/class_5321;Lnet/minecraft/class_5455;Lnet/minecraft/class_6880;Ljava/util/function/Supplier;ZZJI)V",
      at = @At("RETURN")
   )
   private void initLithiumData(
      class_5269 properties,
      class_5321<?> registryRef,
      class_5455 registryManager,
      class_6880<?> dimensionEntry,
      Supplier<?> profiler,
      boolean isClient,
      boolean debugWorld,
      long biomeAccess,
      int maxChainedNeighborUpdates,
      CallbackInfo ci
   ) {
      this.storage = new LithiumData.Data(registryManager);
   }

   @Override
   public LithiumData.Data lithium$getData() {
      return this.storage;
   }
}
