package net.p3pp3rf1y.sophisticatedbackpacks.data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.class_173;
import net.minecraft.class_2438;
import net.minecraft.class_2438.class_7790;
import net.minecraft.class_7225.class_7874;

public class SBLootTableProvider extends class_2438 {
   public SBLootTableProvider(FabricDataOutput packOutput, CompletableFuture<class_7874> registries) {
      super(
         packOutput,
         SBInjectLootSubProvider.ALL_TABLES,
         List.of(
            new class_7790($ -> new SBPBlockLootSubProvider(packOutput, registries), class_173.field_1172),
            new class_7790(SBInjectLootSubProvider::new, class_173.field_1179)
         ),
         registries
      );
   }
}
