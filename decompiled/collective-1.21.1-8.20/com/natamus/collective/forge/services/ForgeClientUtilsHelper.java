package com.natamus.collective.forge.services;

import com.natamus.collective_common_forge.services.helpers.ClientUtilsHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class ForgeClientUtilsHelper implements ClientUtilsHelper {
   @Override
   public void blockSetRenderType(Block block, RenderType renderType) {
      ItemBlockRenderTypes.setRenderLayer(block, renderType);
   }

   @Override
   public void blockSetRenderCutout(Block block) {
      this.blockSetRenderType(block, RenderType.cutout());
   }
}
