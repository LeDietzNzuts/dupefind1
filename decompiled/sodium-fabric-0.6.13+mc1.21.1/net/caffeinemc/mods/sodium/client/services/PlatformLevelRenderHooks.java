package net.caffeinemc.mods.sodium.client.services;

import java.util.List;
import java.util.function.Function;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_4604;
import net.minecraft.class_761;
import org.joml.Matrix4f;

public interface PlatformLevelRenderHooks {
   PlatformLevelRenderHooks INSTANCE = Services.load(PlatformLevelRenderHooks.class);

   static PlatformLevelRenderHooks getInstance() {
      return INSTANCE;
   }

   void runChunkLayerEvents(class_1921 var1, class_761 var2, Matrix4f var3, Matrix4f var4, int var5, class_4184 var6, class_4604 var7);

   List<?> retrieveChunkMeshAppenders(class_1937 var1, class_2338 var2);

   void runChunkMeshAppenders(List<?> var1, Function<class_1921, class_4588> var2, LevelSlice var3);
}
