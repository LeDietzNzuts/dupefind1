package net.kikoz.mcwfurnitures.util;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.kikoz.mcwfurnitures.MacawsFurniture;
import net.kikoz.mcwfurnitures.init.BlockInit;
import net.kikoz.mcwfurnitures.init.ScreenHandlerInit;
import net.kikoz.mcwfurnitures.sittable.ChairRenderer;
import net.kikoz.mcwfurnitures.sittable.CouchRenderer;
import net.kikoz.mcwfurnitures.storage.FurnitureScreen;
import net.minecraft.class_1921;
import net.minecraft.class_3929;

@Environment(EnvType.CLIENT)
public class ClientEventBusSubscriber implements ClientModInitializer {
   public void onInitializeClient() {
      class_3929.method_17542(ScreenHandlerInit.FURNITURE_SCREEN_HANDLER, FurnitureScreen::new);
      EntityRendererRegistry.register(MacawsFurniture.CHAIR_ENTITY_TYPE, ChairRenderer::new);
      EntityRendererRegistry.register(MacawsFurniture.COUCH_ENTITY_TYPE, CouchRenderer::new);
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ACACIA_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BIRCH_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CRIMSON_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.OAK_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.JUNGLE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DARK_OAK_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPRUCE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.WARPED_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MANGROVE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CHERRY_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_ACACIA_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_BIRCH_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_CRIMSON_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_OAK_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_JUNGLE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_DARK_OAK_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_SPRUCE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_WARPED_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_MANGROVE_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_CHERRY_GLASS_TABLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.OAK_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_OAK_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BIRCH_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_BIRCH_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPRUCE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_SPRUCE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.JUNGLE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_JUNGLE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ACACIA_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_ACACIA_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DARK_OAK_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_DARK_OAK_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.WARPED_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_WARPED_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CRIMSON_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_CRIMSON_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MANGROVE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_MANGROVE_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CHERRY_GLASS_KITCHEN_CABINET, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STRIPPED_CHERRY_GLASS_KITCHEN_CABINET, class_1921.method_23581());
   }
}
