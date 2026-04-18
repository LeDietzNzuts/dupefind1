package com.magistuarmory.fabric.client.render.tileentity;

import com.magistuarmory.client.render.tileentity.HeraldryItemStackRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_811;

@Environment(EnvType.CLIENT)
public class HeraldryItemStackRendererFabric extends HeraldryItemStackRenderer implements DynamicItemRenderer {
   public HeraldryItemStackRendererFabric(String id, class_2960 location) {
      super(id, location);
   }

   public void render(class_1799 stack, class_811 mode, class_4587 matrices, class_4597 vertexConsumers, int p, int overlay) {
      super.method_3166(stack, mode, matrices, vertexConsumers, p, overlay);
   }
}
