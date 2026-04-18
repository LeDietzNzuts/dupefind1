package com.natamus.collective.fabric.services;

import com.natamus.collective_common_fabric.services.helpers.ClientUtilsHelper;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.class_1921;
import net.minecraft.class_2248;

public class FabricClientUtilsHelper implements ClientUtilsHelper {
   @Override
   public void blockSetRenderType(class_2248 block, class_1921 renderType) {
      BlockRenderLayerMap.INSTANCE.putBlock(block, renderType);
   }

   @Override
   public void blockSetRenderCutout(class_2248 block) {
      this.blockSetRenderType(block, class_1921.method_23581());
   }
}
