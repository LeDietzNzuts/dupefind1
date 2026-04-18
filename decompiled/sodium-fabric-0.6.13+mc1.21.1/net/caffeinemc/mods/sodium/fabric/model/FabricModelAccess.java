package net.caffeinemc.mods.sodium.fabric.model;

import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import java.util.Collections;
import java.util.List;
import net.caffeinemc.mods.sodium.client.services.PlatformModelAccess;
import net.caffeinemc.mods.sodium.client.services.SodiumModelData;
import net.caffeinemc.mods.sodium.client.services.SodiumModelDataContainer;
import net.caffeinemc.mods.sodium.client.world.LevelSlice;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_4076;
import net.minecraft.class_4696;
import net.minecraft.class_5819;
import net.minecraft.class_777;

public class FabricModelAccess implements PlatformModelAccess {
   private static final SodiumModelDataContainer EMPTY_CONTAINER = new SodiumModelDataContainer(Long2ObjectMaps.emptyMap());

   @Override
   public Iterable<class_1921> getModelRenderTypes(
      class_1920 level, class_1087 model, class_2680 state, class_2338 pos, class_5819 random, SodiumModelData modelData
   ) {
      return Collections.singleton(class_4696.method_23679(state));
   }

   @Override
   public List<class_777> getQuads(
      class_1920 level,
      class_2338 pos,
      class_1087 model,
      class_2680 state,
      class_2350 face,
      class_5819 random,
      class_1921 renderType,
      SodiumModelData modelData
   ) {
      return model.method_4707(state, face, random);
   }

   @Override
   public SodiumModelDataContainer getModelDataContainer(class_1937 level, class_4076 sectionPos) {
      return EMPTY_CONTAINER;
   }

   @Override
   public SodiumModelData getModelData(LevelSlice slice, class_1087 model, class_2680 state, class_2338 pos, SodiumModelData originalData) {
      return null;
   }

   @Override
   public SodiumModelData getEmptyModelData() {
      return null;
   }
}
