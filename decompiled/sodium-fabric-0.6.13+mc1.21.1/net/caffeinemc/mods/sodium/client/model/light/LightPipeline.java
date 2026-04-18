package net.caffeinemc.mods.sodium.client.model.light;

import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.minecraft.class_2338;
import net.minecraft.class_2350;

public interface LightPipeline {
   void calculate(ModelQuadView var1, class_2338 var2, QuadLightData var3, class_2350 var4, class_2350 var5, boolean var6, boolean var7);
}
