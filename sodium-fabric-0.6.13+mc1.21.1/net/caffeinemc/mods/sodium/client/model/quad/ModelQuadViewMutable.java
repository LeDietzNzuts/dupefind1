package net.caffeinemc.mods.sodium.client.model.quad;

import net.minecraft.class_1058;
import net.minecraft.class_2350;

public interface ModelQuadViewMutable extends ModelQuadView {
   void setX(int var1, float var2);

   void setY(int var1, float var2);

   void setZ(int var1, float var2);

   void setColor(int var1, int var2);

   void setTexU(int var1, float var2);

   void setTexV(int var1, float var2);

   void setLight(int var1, int var2);

   void setNormal(int var1, int var2);

   void setFaceNormal(int var1);

   void setFlags(int var1);

   void setSprite(class_1058 var1);

   void setLightFace(class_2350 var1);

   void setColorIndex(int var1);
}
