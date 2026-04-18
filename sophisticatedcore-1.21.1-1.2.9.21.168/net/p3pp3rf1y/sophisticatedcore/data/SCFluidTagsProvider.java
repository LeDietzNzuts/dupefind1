package net.p3pp3rf1y.sophisticatedcore.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.FluidTagProvider;
import net.minecraft.class_3611;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;

public class SCFluidTagsProvider extends FluidTagProvider {
   public SCFluidTagsProvider(FabricDataOutput output, CompletableFuture<class_7874> completableFuture) {
      super(output, completableFuture);
   }

   protected void method_10514(class_7874 arg) {
      this.getOrCreateTagBuilder(ModFluids.EXPERIENCE_TAG).add((class_3611)ModFluids.XP_STILL.get());
   }
}
