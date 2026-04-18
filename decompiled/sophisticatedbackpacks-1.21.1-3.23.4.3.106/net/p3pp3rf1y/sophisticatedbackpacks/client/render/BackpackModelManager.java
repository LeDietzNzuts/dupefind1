package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import net.minecraft.class_1792;
import net.minecraft.class_310;
import net.minecraft.class_5599;
import net.minecraft.class_5614.class_5615;
import net.p3pp3rf1y.sophisticatedbackpacks.client.ClientEventHandler;

public class BackpackModelManager {
   private static IBackpackModelProvider backpackModelProvider = new IBackpackModelProvider() {
      private static IBackpackModel model;

      @Override
      public void initModels() {
         if (model == null) {
            class_5599 entityModels = class_310.method_1551().method_31974();
            model = new BackpackModel(entityModels.method_32072(ClientEventHandler.BACKPACK_LAYER));
         }
      }

      @Override
      public void initModels(class_5615 context) {
         if (model == null) {
            model = new BackpackModel(context.method_32140(ClientEventHandler.BACKPACK_LAYER));
         }
      }

      @Override
      public IBackpackModel getBackpackModel(class_1792 backpackItem) {
         return model;
      }
   };

   public static void registerBackpackModelProvider(IBackpackModelProvider provider) {
      backpackModelProvider = provider;
   }

   public static void initModels() {
      backpackModelProvider.initModels();
   }

   public static IBackpackModel getBackpackModel(class_1792 backpackItem) {
      return backpackModelProvider.getBackpackModel(backpackItem);
   }
}
