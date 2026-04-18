package net.caffeinemc.mods.sodium.client.render.texture;

import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder;
import net.minecraft.class_1059;
import net.minecraft.class_310;

public class SpriteFinderCache {
   private static SpriteFinder blockAtlasSpriteFinder;

   public static SpriteFinder forBlockAtlas() {
      if (blockAtlasSpriteFinder == null) {
         blockAtlasSpriteFinder = SpriteFinder.get(class_310.method_1551().method_1554().method_24153(class_1059.field_5275));
      }

      return blockAtlasSpriteFinder;
   }

   public static void resetSpriteFinder() {
      blockAtlasSpriteFinder = null;
   }
}
