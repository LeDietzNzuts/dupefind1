package net.caffeinemc.mods.sodium.client.render.chunk.terrain;

import net.minecraft.class_1921;

public class TerrainRenderPass {
   @Deprecated(forRemoval = true)
   private final class_1921 renderType;
   private final boolean isTranslucent;
   private final boolean fragmentDiscard;

   public TerrainRenderPass(class_1921 renderType, boolean isTranslucent, boolean allowFragmentDiscard) {
      this.renderType = renderType;
      this.isTranslucent = isTranslucent;
      this.fragmentDiscard = allowFragmentDiscard;
   }

   public boolean isTranslucent() {
      return this.isTranslucent;
   }

   @Deprecated
   public void startDrawing() {
      this.renderType.method_23516();
   }

   @Deprecated
   public void endDrawing() {
      this.renderType.method_23518();
   }

   public boolean supportsFragmentDiscard() {
      return this.fragmentDiscard;
   }
}
