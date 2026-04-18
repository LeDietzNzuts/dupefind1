package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import net.minecraft.class_1792;
import net.minecraft.class_5614.class_5615;

public interface IBackpackModelProvider {
   void initModels();

   void initModels(class_5615 var1);

   IBackpackModel getBackpackModel(class_1792 var1);
}
