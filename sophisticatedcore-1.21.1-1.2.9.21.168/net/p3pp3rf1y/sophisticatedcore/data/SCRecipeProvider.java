package net.p3pp3rf1y.sophisticatedcore.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.class_2456;
import net.minecraft.class_8790;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.crafting.UpgradeClearRecipe;

public class SCRecipeProvider extends FabricRecipeProvider {
   public SCRecipeProvider(FabricDataOutput output, CompletableFuture<class_7874> registriesFuture) {
      super(output, registriesFuture);
   }

   public void method_10419(class_8790 recipeOutput) {
      class_2456.method_10476(UpgradeClearRecipe::new).method_53820(recipeOutput, SophisticatedCore.getRegistryName("upgrade_clear"));
   }
}
