package com.natamus.collective_common_forge.services.helpers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public interface ClientUtilsHelper {
   void blockSetRenderType(Block var1, RenderType var2);

   void blockSetRenderCutout(Block var1);
}
