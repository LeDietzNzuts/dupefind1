package com.magistuarmory.block;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.class_2248;
import net.minecraft.class_2591;
import net.minecraft.class_7924;
import net.minecraft.class_2591.class_2592;

public class ModBlockEntityTypes {
   public static DeferredRegister<class_2591<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create("magistuarmory", class_7924.field_41255);
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> WOOD_PAVISE = BLOCK_ENTITY_TYPES.register(
      "wood_pavise", ModBlockEntityTypes.WOOD_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> GOLD_PAVISE = BLOCK_ENTITY_TYPES.register(
      "gold_pavise", ModBlockEntityTypes.GOLD_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> STONE_PAVISE = BLOCK_ENTITY_TYPES.register(
      "stone_pavise", ModBlockEntityTypes.STONE_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> IRON_PAVISE = BLOCK_ENTITY_TYPES.register(
      "iron_pavise", ModBlockEntityTypes.IRON_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> DIAMOND_PAVISE = BLOCK_ENTITY_TYPES.register(
      "diamond_pavise", ModBlockEntityTypes.DIAMOND_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> NETHERITE_PAVISE = BLOCK_ENTITY_TYPES.register(
      "netherite_pavise", ModBlockEntityTypes.NETHERITE_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> TIN_PAVISE = BLOCK_ENTITY_TYPES.register("tin_pavise", ModBlockEntityTypes.TIN_PAVISE_BUILDER);
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> COPPER_PAVISE = BLOCK_ENTITY_TYPES.register(
      "copper_pavise", ModBlockEntityTypes.COPPER_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> SILVER_PAVISE = BLOCK_ENTITY_TYPES.register(
      "silver_pavise", ModBlockEntityTypes.SILVER_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> BRONZE_PAVISE = BLOCK_ENTITY_TYPES.register(
      "bronze_pavise", ModBlockEntityTypes.BRONZE_PAVISE_BUILDER
   );
   public static RegistrySupplier<class_2591<PaviseBlockEntity>> STEEL_PAVISE = BLOCK_ENTITY_TYPES.register(
      "steel_pavise", ModBlockEntityTypes.STEEL_PAVISE_BUILDER
   );
   private static final Supplier<class_2591<PaviseBlockEntity>> WOOD_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(WOOD_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.WOOD_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> GOLD_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(GOLD_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.GOLD_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> STONE_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(STONE_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.STONE_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> IRON_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(IRON_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.IRON_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> DIAMOND_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(DIAMOND_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.DIAMOND_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> NETHERITE_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(NETHERITE_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.NETHERITE_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> TIN_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(TIN_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.TIN_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> COPPER_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(COPPER_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.COPPER_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> SILVER_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(SILVER_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.SILVER_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> BRONZE_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(BRONZE_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.BRONZE_PAVISE.get()}
      )
      .method_11034(null);
   private static final Supplier<class_2591<PaviseBlockEntity>> STEEL_PAVISE_BUILDER = () -> class_2592.method_20528(
         (a, b) -> new PaviseBlockEntity(STEEL_PAVISE, a, b), new class_2248[]{(class_2248)ModBlocks.STEEL_PAVISE.get()}
      )
      .method_11034(null);

   public static void init() {
      BLOCK_ENTITY_TYPES.register();
   }
}
