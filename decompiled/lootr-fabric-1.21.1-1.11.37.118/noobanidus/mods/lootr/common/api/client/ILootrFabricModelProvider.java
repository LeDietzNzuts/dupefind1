package noobanidus.mods.lootr.common.api.client;

import net.minecraft.class_2960;
import org.jetbrains.annotations.Nullable;

public interface ILootrFabricModelProvider {
   void provideModels(ILootrFabricModelProvider.Acceptor var1);

   public interface Acceptor {
      void acceptCustomModel(class_2960 var1, class_2960 var2, class_2960 var3, @Nullable class_2960 var4);

      @Deprecated
      default void acceptBarrelModel(
         class_2960 modelName, class_2960 modelOpenedLocation, class_2960 modelUnopenedLocation, @Nullable class_2960 modelVanillaLocation
      ) {
         this.acceptCustomModel(modelName, modelOpenedLocation, modelUnopenedLocation, modelVanillaLocation);
      }

      void acceptBrushableModel(class_2960 var1, class_2960 var2, class_2960 var3, class_2960 var4, class_2960 var5, class_2960 var6);
   }
}
