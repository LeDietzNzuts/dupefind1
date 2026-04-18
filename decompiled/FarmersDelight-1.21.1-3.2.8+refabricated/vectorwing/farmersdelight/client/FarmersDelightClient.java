package vectorwing.farmersdelight.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3929;
import net.minecraft.class_5272;
import vectorwing.farmersdelight.client.event.ClientSetupEvents;
import vectorwing.farmersdelight.client.event.TooltipEvents;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.client.gui.HUDOverlays;
import vectorwing.farmersdelight.client.renderer.SkilletItemRenderer;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModMenuTypes;

public class FarmersDelightClient implements ClientModInitializer {
   public void onInitializeClient() {
      ItemTooltipCallback.EVENT.register(TooltipEvents::addTooltipToVanillaSoups);
      TooltipComponentCallback.EVENT.register(ClientSetupEvents::registerCustomTooltipRenderers);
      ClientSetupEvents.onRegisterRenderers();
      ClientSetupEvents.registerParticles();
      class_3929.method_17542(ModMenuTypes.COOKING_POT.get(), CookingPotScreen::new);
      HUDOverlays.register();
      BuiltinItemRendererRegistry.INSTANCE.register((class_1935)ModItems.SKILLET.get(), new SkilletItemRenderer());
      class_5272.method_27879(
         ModItems.SKILLET.get(),
         class_2960.method_60656("cooking"),
         (stack, world, entity, s) -> ((ItemStackWrapper)stack.method_57825(ModDataComponents.SKILLET_INGREDIENT.get(), ItemStackWrapper.EMPTY))
               .getStack()
               .method_7960()
            ? 0.0F
            : 1.0F
      );
      ModNetworking.initClient();
      ClientPreAttackCallback.EVENT
         .register(
            (ClientPreAttackCallback)(client, player, clickCount) -> {
               if (player != null
                  && !player.method_7325()
                  && player.method_6115()
                  && player.method_6030().method_7909() instanceof SkilletItem
                  && clickCount != 0
                  && !player.method_6030().method_57826(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get())) {
                  ClientPlayNetworking.send(ModNetworking.FlipSkilletMessage.INSTANCE);
               }

               return false;
            }
         );
      BlockRenderLayerMap.INSTANCE
         .putBlocks(
            class_1921.method_23581(),
            new class_2248[]{
               ModBlocks.BROWN_MUSHROOM_COLONY.get(),
               ModBlocks.RED_MUSHROOM_COLONY.get(),
               ModBlocks.BUDDING_TOMATO_CROP.get(),
               ModBlocks.CABBAGE_CROP.get(),
               ModBlocks.CUTTING_BOARD.get(),
               ModBlocks.ONION_CROP.get(),
               ModBlocks.WILD_CABBAGES.get(),
               ModBlocks.WILD_BEETROOTS.get(),
               ModBlocks.WILD_CARROTS.get(),
               ModBlocks.WILD_ONIONS.get(),
               ModBlocks.WILD_POTATOES.get(),
               ModBlocks.WILD_RICE.get(),
               ModBlocks.WILD_TOMATOES.get(),
               ModBlocks.RICE_CROP.get(),
               ModBlocks.TOMATO_CROP.get(),
               ModBlocks.RICE_CROP_PANICLES.get(),
               ModBlocks.ROAST_CHICKEN_BLOCK.get(),
               ModBlocks.SANDY_SHRUB.get(),
               ModBlocks.ROPE.get(),
               ModBlocks.CANVAS_RUG.get(),
               ModBlocks.COOKING_POT.get(),
               ModBlocks.SAFETY_NET.get()
            }
         );
   }
}
