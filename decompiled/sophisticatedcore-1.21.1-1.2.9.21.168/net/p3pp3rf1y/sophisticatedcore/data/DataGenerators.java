package net.p3pp3rf1y.sophisticatedcore.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack;

public class DataGenerators implements DataGeneratorEntrypoint {
   public void onInitializeDataGenerator(FabricDataGenerator generator) {
      Pack pack = generator.createPack();
      pack.addProvider(SCFluidTagsProvider::new);
      pack.addProvider(SCRecipeProvider::new);
   }
}
