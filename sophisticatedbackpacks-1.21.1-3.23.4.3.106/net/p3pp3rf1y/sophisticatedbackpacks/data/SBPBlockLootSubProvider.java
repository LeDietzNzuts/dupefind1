package net.p3pp3rf1y.sophisticatedbackpacks.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.class_44;
import net.minecraft.class_52;
import net.minecraft.class_55;
import net.minecraft.class_77;
import net.minecraft.class_52.class_53;
import net.minecraft.class_55.class_56;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_79.class_80;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModBlocks;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;

public class SBPBlockLootSubProvider extends FabricBlockLootTableProvider {
   protected SBPBlockLootSubProvider(FabricDataOutput output, CompletableFuture<class_7874> registryLookup) {
      super(output, registryLookup);
   }

   public void method_10379() {
      this.method_45988(ModBlocks.BACKPACK.get(), dropBackpackWithContents(ModItems.BACKPACK.get()));
      this.method_45988(ModBlocks.COPPER_BACKPACK.get(), dropBackpackWithContents(ModItems.COPPER_BACKPACK.get()));
      this.method_45988(ModBlocks.IRON_BACKPACK.get(), dropBackpackWithContents(ModItems.IRON_BACKPACK.get()));
      this.method_45988(ModBlocks.GOLD_BACKPACK.get(), dropBackpackWithContents(ModItems.GOLD_BACKPACK.get()));
      this.method_45988(ModBlocks.DIAMOND_BACKPACK.get(), dropBackpackWithContents(ModItems.DIAMOND_BACKPACK.get()));
      this.method_45988(ModBlocks.NETHERITE_BACKPACK.get(), dropBackpackWithContents(ModItems.NETHERITE_BACKPACK.get()));
   }

   private static class_53 dropBackpackWithContents(BackpackItem item) {
      class_80<?> entry = class_77.method_411(item);
      class_56 pool = class_55.method_347()
         .name("main")
         .method_352(class_44.method_32448(1.0F))
         .method_351(entry)
         .method_353(CopyBackpackDataFunction.builder());
      return class_52.method_324().method_336(pool);
   }
}
