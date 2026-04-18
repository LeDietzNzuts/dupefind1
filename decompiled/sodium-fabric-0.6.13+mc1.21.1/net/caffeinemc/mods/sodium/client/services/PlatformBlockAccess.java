package net.caffeinemc.mods.sodium.client.services;

import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.render.frapi.render.AmbientOcclusionMode;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_746;

public interface PlatformBlockAccess {
   PlatformBlockAccess INSTANCE = Services.load(PlatformBlockAccess.class);

   static PlatformBlockAccess getInstance() {
      return INSTANCE;
   }

   int getLightEmission(class_2680 var1, class_1920 var2, class_2338 var3);

   boolean shouldSkipRender(class_1922 var1, class_2680 var2, class_2680 var3, class_2338 var4, class_2338 var5, class_2350 var6);

   boolean shouldShowFluidOverlay(class_2680 var1, class_1920 var2, class_2338 var3, class_3610 var4);

   boolean platformHasBlockData();

   float getNormalVectorShade(ModelQuadView var1, class_1920 var2, boolean var3);

   AmbientOcclusionMode usesAmbientOcclusion(class_1087 var1, class_2680 var2, SodiumModelData var3, class_1921 var4, class_1920 var5, class_2338 var6);

   boolean shouldBlockEntityGlow(class_2586 var1, class_746 var2);

   boolean shouldOccludeFluid(class_2350 var1, class_2680 var2, class_3610 var3);
}
