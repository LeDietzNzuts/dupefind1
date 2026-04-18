package net.caffeinemc.mods.sodium.fabric.level;

import java.util.List;
import java.util.function.Function;
import net.caffeinemc.mods.sodium.client.services.PlatformLevelRenderHooks;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_4184;
import net.minecraft.class_4588;
import net.minecraft.class_4604;
import net.minecraft.class_761;
import org.joml.Matrix4f;

public class FabricLevelRenderHooks implements PlatformLevelRenderHooks {
   @Override
   public void runChunkLayerEvents(
      class_1921 renderLayer,
      class_761 levelRenderer,
      Matrix4f modelMatrix,
      Matrix4f projectionMatrix,
      int ticks,
      class_4184 mainCamera,
      class_4604 cullingFrustum
   ) {
   }

   @Override
   public List<?> retrieveChunkMeshAppenders(class_1937 level, class_2338 origin) {
      return List.of();
   }

   @Override
   public void runChunkMeshAppenders(List<?> renderers, Function<class_1921, class_4588> typeToConsumer, LevelSlice slice) {
   }
}
