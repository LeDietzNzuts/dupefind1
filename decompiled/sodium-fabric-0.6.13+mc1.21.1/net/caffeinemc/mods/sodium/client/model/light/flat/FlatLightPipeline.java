package net.caffeinemc.mods.sodium.client.model.light.flat;

import java.util.Arrays;
import net.caffeinemc.mods.sodium.client.model.light.LightPipeline;
import net.caffeinemc.mods.sodium.client.model.light.data.LightDataAccess;
import net.caffeinemc.mods.sodium.client.model.light.data.QuadLightData;
import net.caffeinemc.mods.sodium.client.model.quad.ModelQuadView;
import net.caffeinemc.mods.sodium.client.services.PlatformBlockAccess;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_765;

public class FlatLightPipeline implements LightPipeline {
   private final LightDataAccess lightCache;

   public FlatLightPipeline(LightDataAccess lightCache) {
      this.lightCache = lightCache;
   }

   @Override
   public void calculate(ModelQuadView quad, class_2338 pos, QuadLightData out, class_2350 cullFace, class_2350 lightFace, boolean shade, boolean enhanced) {
      int lightmap;
      if (cullFace != null) {
         lightmap = this.getOffsetLightmap(pos, cullFace);
         Arrays.fill(out.br, this.lightCache.getLevel().method_24852(lightFace, shade));
      } else {
         int flags = quad.getFlags();
         if ((flags & 4) == 0 && ((flags & 2) == 0 || !LightDataAccess.unpackFC(this.lightCache.get(pos)))) {
            lightmap = LightDataAccess.getEmissiveLightmap(this.lightCache.get(pos));
            Arrays.fill(
               out.br,
               enhanced
                  ? PlatformBlockAccess.getInstance().getNormalVectorShade(quad, this.lightCache.getLevel(), shade)
                  : this.lightCache.getLevel().method_24852(lightFace, shade)
            );
         } else {
            lightmap = this.getOffsetLightmap(pos, lightFace);
            Arrays.fill(out.br, this.lightCache.getLevel().method_24852(lightFace, shade));
         }
      }

      Arrays.fill(out.lm, lightmap);
   }

   private int getOffsetLightmap(class_2338 pos, class_2350 face) {
      int word = this.lightCache.get(pos);
      if (LightDataAccess.unpackEM(word)) {
         return 15728880;
      } else {
         int adjWord = this.lightCache.get(pos, face);
         return class_765.method_23687(Math.max(LightDataAccess.unpackBL(adjWord), LightDataAccess.unpackLU(word)), LightDataAccess.unpackSL(adjWord));
      }
   }
}
