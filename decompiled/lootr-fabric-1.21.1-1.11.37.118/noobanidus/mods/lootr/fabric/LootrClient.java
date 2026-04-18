package noobanidus.mods.lootr.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_1921;
import net.minecraft.class_5602;
import net.minecraft.class_5616;
import noobanidus.mods.lootr.common.client.block.LootrBrushableBlockRenderer;
import noobanidus.mods.lootr.common.client.block.LootrDecoratedPotRenderer;
import noobanidus.mods.lootr.common.client.entity.LootrItemFrameRenderer;
import noobanidus.mods.lootr.common.client.particle.UnopenedParticle;
import noobanidus.mods.lootr.fabric.client.block.BarrelModelLoader;
import noobanidus.mods.lootr.fabric.client.block.CustomModelLoader;
import noobanidus.mods.lootr.fabric.client.block.LootrChestBlockRenderer;
import noobanidus.mods.lootr.fabric.client.block.LootrShulkerBlockRenderer;
import noobanidus.mods.lootr.fabric.client.entity.LootrChestCartRenderer;
import noobanidus.mods.lootr.fabric.client.item.LootrChestItemRenderer;
import noobanidus.mods.lootr.fabric.client.item.LootrDecoratedPotItemRenderer;
import noobanidus.mods.lootr.fabric.client.item.LootrShulkerItemRenderer;
import noobanidus.mods.lootr.fabric.client.item.LootrTrappedChestItemRenderer;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import noobanidus.mods.lootr.fabric.init.ModEntities;
import noobanidus.mods.lootr.fabric.init.ModParticles;
import noobanidus.mods.lootr.fabric.network.LootrClientNetworkingInit;

@Environment(EnvType.CLIENT)
public class LootrClient implements ClientModInitializer {
   public void onInitializeClient() {
      LootrClientNetworkingInit.register();
      BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BARREL, class_1921.method_23579());
      BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TROPHY, class_1921.method_23579());
      class_5616.method_32144(ModBlockEntities.LOOTR_CHEST, LootrChestBlockRenderer::new);
      BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.CHEST, LootrChestItemRenderer.getInstance());
      class_5616.method_32144(ModBlockEntities.LOOTR_TRAPPED_CHEST, LootrChestBlockRenderer::new);
      BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.TRAPPED_CHEST, LootrTrappedChestItemRenderer.getInstance());
      class_5616.method_32144(ModBlockEntities.LOOTR_INVENTORY, LootrChestBlockRenderer::new);
      BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.INVENTORY, LootrChestItemRenderer.getInstance());
      class_5616.method_32144(ModBlockEntities.LOOTR_SHULKER, LootrShulkerBlockRenderer::new);
      class_5616.method_32144(ModBlockEntities.LOOTR_BRUSHABLE_BLOCK, LootrBrushableBlockRenderer::new);
      BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.SHULKER, LootrShulkerItemRenderer.getInstance());
      class_5616.method_32144(ModBlockEntities.LOOTR_DECORATED_POT, LootrDecoratedPotRenderer::new);
      BuiltinItemRendererRegistry.INSTANCE.register(ModBlocks.DECORATED_POT, LootrDecoratedPotItemRenderer.getInstance());
      ModelLoadingPlugin.register(BarrelModelLoader.INSTANCE);
      ModelLoadingPlugin.register(CustomModelLoader.INSTANCE);
      EntityRendererRegistry.register(ModEntities.LOOTR_MINECART_ENTITY, context -> new LootrChestCartRenderer(context, class_5602.field_27690));
      EntityRendererRegistry.register(ModEntities.ITEM_FRAME, LootrItemFrameRenderer::new);
      EntityModelLayerRegistry.registerModelLayer(LootrDecoratedPotRenderer.OPEN_POT_LAYER, LootrDecoratedPotRenderer::createBodyLayer);
      ParticleFactoryRegistry.getInstance().register(ModParticles.UNOPENED_PARTCLE, UnopenedParticle.Provider::new);
   }
}
