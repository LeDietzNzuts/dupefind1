package dev.architectury.registry.client.rendering.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.class_1921;
import net.minecraft.class_2248;
import net.minecraft.class_3611;

public class RenderTypeRegistryImpl {
   public static void register(class_1921 type, class_2248... blocks) {
      BlockRenderLayerMap.INSTANCE.putBlocks(type, blocks);
   }

   public static void register(class_1921 type, class_3611... fluids) {
      BlockRenderLayerMap.INSTANCE.putFluids(type, fluids);
   }
}
