package dev.architectury.core.fluid.fabric;

import dev.architectury.core.fluid.ArchitecturyFluidAttributes;
import dev.architectury.utils.Env;
import dev.architectury.utils.EnvExecutor;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.class_3609;

public class ArchitecturyFlowingFluidImpl {
   public static void addFabricFluidAttributes(class_3609 fluid, ArchitecturyFluidAttributes attributes) {
      FluidVariantAttributes.register(fluid, new ArchitecturyFluidAttributesFabric(attributes));
      EnvExecutor.runInEnv(Env.CLIENT, () -> () -> ArchitecturyFlowingFluidImpl.Client.run(fluid, attributes));
   }

   private static class Client {
      private static void run(class_3609 fluid, ArchitecturyFluidAttributes attributes) {
         FluidVariantRendering.register(fluid, new ArchitecturyFluidRenderingFabric(attributes));
         FluidRenderHandlerRegistry.INSTANCE.register(fluid, new ArchitecturyFluidRenderingFabric(attributes));
      }
   }
}
