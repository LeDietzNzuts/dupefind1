package net.p3pp3rf1y.sophisticatedcore.client.render;

import net.minecraft.class_1058;
import net.minecraft.class_1087;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.p3pp3rf1y.sophisticatedcore.util.model.ModelData;

public interface CustomParticleIcon {
   default ModelData getModelData(class_1920 world, class_2338 pos, class_2680 state, ModelData tileData) {
      return ModelData.EMPTY;
   }

   default class_1058 getParticleIcon(ModelData data) {
      return ((class_1087)this).method_4711();
   }
}
