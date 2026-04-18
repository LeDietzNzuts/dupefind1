package net.caffeinemc.mods.lithium.mixin.profiler;

import java.util.function.Supplier;
import net.minecraft.class_1937;
import net.minecraft.class_2874;
import net.minecraft.class_3218;
import net.minecraft.class_3695;
import net.minecraft.class_5269;
import net.minecraft.class_5321;
import net.minecraft.class_5455;
import net.minecraft.class_6880;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_3218.class)
public abstract class ServerLevelMixin extends class_1937 {
   protected ServerLevelMixin(
      class_5269 properties,
      class_5321<class_1937> registryRef,
      class_5455 registryManager,
      class_6880<class_2874> dimensionEntry,
      Supplier<class_3695> profiler,
      boolean isClient,
      boolean debugWorld,
      long biomeAccess,
      int maxChainedNeighborUpdates
   ) {
      super(properties, registryRef, registryManager, dimensionEntry, profiler, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
   }

   @Shadow
   @NotNull
   public abstract MinecraftServer method_8503();

   public class_3695 method_16107() {
      return this.method_8503().method_16044();
   }
}
