package net.caffeinemc.mods.sodium.client.services;

import java.util.List;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_4076;
import net.minecraft.class_5819;
import net.minecraft.class_777;
import org.jetbrains.annotations.ApiStatus.Internal;

public interface PlatformModelAccess {
   PlatformModelAccess INSTANCE = Services.load(PlatformModelAccess.class);

   static PlatformModelAccess getInstance() {
      return INSTANCE;
   }

   Iterable<class_1921> getModelRenderTypes(class_1920 var1, class_1087 var2, class_2680 var3, class_2338 var4, class_5819 var5, SodiumModelData var6);

   List<class_777> getQuads(
      class_1920 var1, class_2338 var2, class_1087 var3, class_2680 var4, class_2350 var5, class_5819 var6, class_1921 var7, SodiumModelData var8
   );

   SodiumModelDataContainer getModelDataContainer(class_1937 var1, class_4076 var2);

   SodiumModelData getModelData(LevelSlice var1, class_1087 var2, class_2680 var3, class_2338 var4, SodiumModelData var5);

   @Internal
   SodiumModelData getEmptyModelData();
}
