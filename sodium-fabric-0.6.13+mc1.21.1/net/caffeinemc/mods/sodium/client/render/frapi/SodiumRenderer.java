package net.caffeinemc.mods.sodium.client.render.frapi;

import java.util.HashMap;
import net.caffeinemc.mods.sodium.client.render.frapi.material.MaterialFinderImpl;
import net.caffeinemc.mods.sodium.client.render.frapi.material.RenderMaterialImpl;
import net.caffeinemc.mods.sodium.client.render.frapi.mesh.MeshBuilderImpl;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.fabricmc.fabric.api.renderer.v1.material.MaterialFinder;
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial;
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder;
import net.minecraft.class_2960;

public class SodiumRenderer implements Renderer {
   public static final SodiumRenderer INSTANCE = new SodiumRenderer();
   public static final RenderMaterial STANDARD_MATERIAL = INSTANCE.materialFinder().find();
   private final HashMap<class_2960, RenderMaterial> materialMap = new HashMap<>();

   private SodiumRenderer() {
   }

   public MeshBuilder meshBuilder() {
      return new MeshBuilderImpl();
   }

   public MaterialFinder materialFinder() {
      return new MaterialFinderImpl();
   }

   public RenderMaterial materialById(class_2960 id) {
      return this.materialMap.get(id);
   }

   public boolean registerMaterial(class_2960 id, RenderMaterial material) {
      if (this.materialMap.containsKey(id)) {
         return false;
      } else {
         this.materialMap.put(id, (RenderMaterialImpl)material);
         return true;
      }
   }

   static {
      INSTANCE.registerMaterial(RenderMaterial.MATERIAL_STANDARD, STANDARD_MATERIAL);
   }
}
