package net.p3pp3rf1y.sophisticatedbackpacks.compat.chipped;

import earth.terrarium.chipped.common.compat.jei.WorkbenchCategory;
import earth.terrarium.chipped.common.registry.ModBlocks;
import earth.terrarium.chipped.common.registry.ModRecipeTypes;
import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import mezz.jei.api.recipe.RecipeType;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.emi.EmiCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.jei.SBPPlugin;
import net.p3pp3rf1y.sophisticatedbackpacks.compat.rei.REIClientCompat;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerRegistry;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeContainer;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.compat.chipped.BlockTransformationUpgradeWrapper;

public class ChippedCompat implements ICompat {
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> BOTANIST_WORKBENCH_UPGRADE = ModItems.ITEMS
      .register("chipped/botanist_workbench_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> GLASSBLOWER_UPGRADE = ModItems.ITEMS
      .register("chipped/glassblower_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> CARPENTERS_TABLE_UPGRADE = ModItems.ITEMS
      .register("chipped/carpenters_table_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> LOOM_TABLE_UPGRADE = ModItems.ITEMS
      .register("chipped/loom_table_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> MASON_TABLE_UPGRADE = ModItems.ITEMS
      .register("chipped/mason_table_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> ALCHEMY_BENCH_UPGRADE = ModItems.ITEMS
      .register("chipped/alchemy_bench_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));
   public static final DeferredHolder<class_1792, BlockTransformationUpgradeItem> TINKERING_TABLE_UPGRADE = ModItems.ITEMS
      .register("chipped/tinkering_table_upgrade", () -> new BlockTransformationUpgradeItem(ModRecipeTypes.WORKBENCH, Config.SERVER.maxUpgradesPerStorage));

   public void init() {
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         if (FabricLoader.getInstance().isModLoaded("jei")) {
            SBPPlugin.setAdditionalCatalystRegistrar(registration -> {
               registration.addRecipeCatalyst(new class_1799((class_1935)BOTANIST_WORKBENCH_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)GLASSBLOWER_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)CARPENTERS_TABLE_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)LOOM_TABLE_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)MASON_TABLE_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)ALCHEMY_BENCH_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
               registration.addRecipeCatalyst(new class_1799((class_1935)TINKERING_TABLE_UPGRADE.get()), new RecipeType[]{WorkbenchCategory.RECIPE});
            });
         }

         if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            REIClientCompat.setAdditionalCategories(
               registration -> {
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.BOTANIST_WORKBENCH.getId()),
                     new EntryStack[]{EntryStacks.of((class_1935)BOTANIST_WORKBENCH_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.GLASSBLOWER.getId()), new EntryStack[]{EntryStacks.of((class_1935)GLASSBLOWER_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.CARPENTERS_TABLE.getId()), new EntryStack[]{EntryStacks.of((class_1935)CARPENTERS_TABLE_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.LOOM_TABLE.getId()), new EntryStack[]{EntryStacks.of((class_1935)LOOM_TABLE_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.MASON_TABLE.getId()), new EntryStack[]{EntryStacks.of((class_1935)MASON_TABLE_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.ALCHEMY_BENCH.getId()), new EntryStack[]{EntryStacks.of((class_1935)ALCHEMY_BENCH_UPGRADE.get())}
                  );
                  registration.addWorkstations(
                     CategoryIdentifier.of(ModBlocks.TINKERING_TABLE.getId()), new EntryStack[]{EntryStacks.of((class_1935)TINKERING_TABLE_UPGRADE.get())}
                  );
               }
            );
         }

         if (FabricLoader.getInstance().isModLoaded("emi")) {
            EmiCompat.WORKSTATIONS
               .register(
                  (EmiCompat.WorkstationCallback)consumer -> {
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("botanist_workbench"),
                           (class_2248)ModBlocks.BOTANIST_WORKBENCH.get(),
                           (class_1792)BOTANIST_WORKBENCH_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("glassblower"), (class_2248)ModBlocks.GLASSBLOWER.get(), (class_1792)GLASSBLOWER_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("carpenters_table"),
                           (class_2248)ModBlocks.CARPENTERS_TABLE.get(),
                           (class_1792)CARPENTERS_TABLE_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("loom_table"), (class_2248)ModBlocks.LOOM_TABLE.get(), (class_1792)LOOM_TABLE_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("mason_table"), (class_2248)ModBlocks.MASON_TABLE.get(), (class_1792)MASON_TABLE_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("alchemy_bench"), (class_2248)ModBlocks.ALCHEMY_BENCH.get(), (class_1792)ALCHEMY_BENCH_UPGRADE.get()
                        )
                     );
                     consumer.accept(
                        new EmiCompat.WorkstationEntry(
                           SophisticatedBackpacks.getRL("tinkering_table"),
                           (class_2248)ModBlocks.TINKERING_TABLE.get(),
                           (class_1792)TINKERING_TABLE_UPGRADE.get()
                        )
                     );
                  }
               );
         }
      }
   }

   public void registerContainers() {
      this.registerUpgradeContainer(BOTANIST_WORKBENCH_UPGRADE);
      this.registerUpgradeContainer(GLASSBLOWER_UPGRADE);
      this.registerUpgradeContainer(CARPENTERS_TABLE_UPGRADE);
      this.registerUpgradeContainer(LOOM_TABLE_UPGRADE);
      this.registerUpgradeContainer(MASON_TABLE_UPGRADE);
      this.registerUpgradeContainer(ALCHEMY_BENCH_UPGRADE);
      this.registerUpgradeContainer(TINKERING_TABLE_UPGRADE);
   }

   private void registerUpgradeContainer(DeferredHolder<class_1792, BlockTransformationUpgradeItem> item) {
      UpgradeContainerType<BlockTransformationUpgradeWrapper, BlockTransformationUpgradeContainer> containerType = new UpgradeContainerType(
         BlockTransformationUpgradeContainer::new
      );
      UpgradeContainerRegistry.register(item.getId(), containerType);
      if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
         ChippedCompatClient.registerUpgradeTab(item.getId(), containerType);
      }
   }

   public void setup() {
      this.registerContainers();
   }
}
