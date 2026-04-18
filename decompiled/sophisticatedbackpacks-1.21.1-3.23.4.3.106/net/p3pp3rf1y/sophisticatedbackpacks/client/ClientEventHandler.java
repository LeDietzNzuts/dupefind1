package net.p3pp3rf1y.sophisticatedbackpacks.client;

import io.github.fabricators_of_create.porting_lib.models.geometry.IGeometryLoader;
import io.github.fabricators_of_create.porting_lib.models.geometry.RegisterGeometryLoadersCallback;
import java.util.Map;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.event.client.player.ClientPickBlockApplyCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_5601;
import net.minecraft.class_5616;
import net.minecraft.class_634;
import net.minecraft.class_916;
import net.minecraft.class_239.class_240;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedbackpacks.client.init.ModBlockColors;
import net.p3pp3rf1y.sophisticatedbackpacks.client.init.ModItemColors;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackBlockEntityRenderer;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackDynamicModel;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackItemStackRenderer;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackLayerRenderer;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.BackpackModel;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.ClientBackpackContentsTooltip;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.network.BlockPickPayload;
import net.p3pp3rf1y.sophisticatedbackpacks.network.RequestPlayerSettingsPayload;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientLifecycleEvents;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class ClientEventHandler {
   private static final String BACKPACK_REG_NAME = "backpack";
   public static final class_5601 BACKPACK_LAYER = new class_5601(class_2960.method_60655("sophisticatedbackpacks", "backpack"), "main");

   private ClientEventHandler() {
   }

   public static void registerHandlers() {
      RegisterGeometryLoadersCallback.EVENT.register(ClientEventHandler::onModelRegistry);
      registerLayer();
      registerEntityRenderers();
      registerReloadListener();
      ModItemColors.registerItemColorHandlers();
      ModBlockColors.registerBlockColorHandlers();
      registerBackpackClientExtension();
      ClientLifecycleEvents.CLIENT_LEVEL_LOAD.register(ClientBackpackContentsTooltip::onWorldLoad);
      ClientPickBlockApplyCallback.EVENT.register(ClientEventHandler::handleBlockPick);
      ClientPlayConnectionEvents.JOIN.register(ClientEventHandler::onPlayerLoggingIn);
      ClientLifecycleEvents.CLIENT_LEVEL_LOAD.register(BackpackStorage::onClientWorldLoad);
   }

   private static void onPlayerLoggingIn(class_634 clientPacketListener, PacketSender packetSender, class_310 minecraft) {
      PacketDistributor.sendToServer(new RequestPlayerSettingsPayload());
   }

   private static void onModelRegistry(Map<class_2960, IGeometryLoader<?>> loaders) {
      loaders.put(class_2960.method_60655("sophisticatedbackpacks", "backpack"), BackpackDynamicModel.Loader.INSTANCE);
   }

   public static void registerReloadListener() {
      registerBackpackLayer();
   }

   private static void registerEntityRenderers() {
      EntityRendererRegistry.register(ModItems.EVERLASTING_BACKPACK_ITEM_ENTITY.get(), class_916::new);
      class_5616.method_32144(ModBlocks.BACKPACK_TILE_TYPE.get(), context -> new BackpackBlockEntityRenderer());
      BlockRenderLayerMap.INSTANCE.putBlocks(class_1921.method_23581(), ModBlocks.BACKPACKS.stream().map(Supplier::get).toArray(BackpackBlock[]::new));
   }

   public static void registerLayer() {
      EntityModelLayerRegistry.registerModelLayer(BACKPACK_LAYER, BackpackModel::createBodyLayer);
   }

   private static void registerBackpackLayer() {
      LivingEntityFeatureRendererRegistrationCallback.EVENT
         .register(
            (LivingEntityFeatureRendererRegistrationCallback)(entityType, livingEntityRenderer, registrationHelper, context) -> registrationHelper.register(
               new BackpackLayerRenderer(livingEntityRenderer)
            )
         );
   }

   public static class_1799 handleBlockPick(class_1657 player, class_239 target, class_1799 stack) {
      if (!player.method_7337() && target.method_17783() == class_240.field_1332) {
         class_1937 level = player.method_37908();
         class_2338 pos = ((class_3965)target).method_17777();
         class_2680 state = level.method_8320(pos);
         if (state.method_26215()) {
            return stack;
         } else {
            class_1799 result = state.method_26204().method_9574(level, pos, state);
            if (!result.method_7960() && player.method_31548().method_7395(result) <= -1) {
               PacketDistributor.sendToServer(new BlockPickPayload(result));
               return stack;
            } else {
               return stack;
            }
         }
      } else {
         return stack;
      }
   }

   private static void registerBackpackClientExtension() {
      ModItems.BACKPACKS.forEach(backpack -> BuiltinItemRendererRegistry.INSTANCE.register((class_1935)backpack.get(), new BackpackItemStackRenderer()));
   }
}
