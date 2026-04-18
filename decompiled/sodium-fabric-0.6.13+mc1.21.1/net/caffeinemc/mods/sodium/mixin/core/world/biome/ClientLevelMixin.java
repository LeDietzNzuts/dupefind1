package net.caffeinemc.mods.sodium.mixin.core.world.biome;

import java.util.function.Supplier;
import net.caffeinemc.mods.sodium.client.world.BiomeSeedProvider;
import net.minecraft.class_1937;
import net.minecraft.class_2874;
import net.minecraft.class_3695;
import net.minecraft.class_5321;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import net.minecraft.class_761;
import net.minecraft.class_638.class_5271;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_638.class)
public class ClientLevelMixin implements BiomeSeedProvider {
   @Unique
   private long biomeZoomSeed;

   @Inject(
      method = "<init>(Lnet/minecraft/class_634;Lnet/minecraft/class_638$class_5271;Lnet/minecraft/class_5321;Lnet/minecraft/class_6880;IILjava/util/function/Supplier;Lnet/minecraft/class_761;ZJ)V",
      at = @At("RETURN")
   )
   private void captureSeed(
      class_634 packetListener,
      class_5271 levelData,
      class_5321<class_1937> dimension,
      class_6880<class_2874> dimensionType,
      int loadDistance,
      int simulationDistance,
      Supplier<class_3695> profiler,
      class_761 renderer,
      boolean isDebug,
      long biomeZoomSeed,
      CallbackInfo ci
   ) {
      this.biomeZoomSeed = biomeZoomSeed;
   }

   @Override
   public long sodium$getBiomeZoomSeed() {
      return this.biomeZoomSeed;
   }
}
