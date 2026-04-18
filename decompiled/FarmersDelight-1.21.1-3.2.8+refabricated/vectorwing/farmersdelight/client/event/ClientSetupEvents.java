package vectorwing.farmersdelight.client.event;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_2396;
import net.minecraft.class_2960;
import net.minecraft.class_5272;
import net.minecraft.class_5616;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import net.minecraft.class_953;
import vectorwing.farmersdelight.client.gui.CookingPotTooltip;
import vectorwing.farmersdelight.client.particle.StarParticle;
import vectorwing.farmersdelight.client.particle.SteamParticle;
import vectorwing.farmersdelight.client.renderer.CanvasSignRenderer;
import vectorwing.farmersdelight.client.renderer.CuttingBoardRenderer;
import vectorwing.farmersdelight.client.renderer.HangingCanvasSignRenderer;
import vectorwing.farmersdelight.client.renderer.SkilletRenderer;
import vectorwing.farmersdelight.client.renderer.StoveRenderer;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModEntityTypes;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;

public class ClientSetupEvents {
   public static void init() {
      class_5272.method_27879(
         ModItems.SKILLET.get(),
         class_2960.method_60656("cooking"),
         (stack, world, entity, s) -> ((ItemStackWrapper)stack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY))
               .getStack()
               .method_7960()
            ? 0.0F
            : 1.0F
      );
   }

   public static class_5684 registerCustomTooltipRenderers(class_5632 data) {
      return CookingPotTooltip.CookingPotTooltipComponent.class.isAssignableFrom(data.getClass())
         ? new CookingPotTooltip((CookingPotTooltip.CookingPotTooltipComponent)data)
         : null;
   }

   public static void onRegisterRenderers() {
      EntityRendererRegistry.register(ModEntityTypes.ROTTEN_TOMATO.get(), class_953::new);
      class_5616.method_32144(ModBlockEntityTypes.STOVE.get(), StoveRenderer::new);
      class_5616.method_32144(ModBlockEntityTypes.CUTTING_BOARD.get(), CuttingBoardRenderer::new);
      class_5616.method_32144(ModBlockEntityTypes.CANVAS_SIGN.get(), CanvasSignRenderer::new);
      class_5616.method_32144(ModBlockEntityTypes.HANGING_CANVAS_SIGN.get(), HangingCanvasSignRenderer::new);
      class_5616.method_32144(ModBlockEntityTypes.SKILLET.get(), SkilletRenderer::new);
   }

   public static void registerParticles() {
      ParticleFactoryRegistry.getInstance().register((class_2396)ModParticleTypes.STAR.get(), StarParticle.Factory::new);
      ParticleFactoryRegistry.getInstance().register((class_2396)ModParticleTypes.STEAM.get(), SteamParticle.Factory::new);
   }
}
