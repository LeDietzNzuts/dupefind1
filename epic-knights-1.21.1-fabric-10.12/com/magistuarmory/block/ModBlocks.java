package com.magistuarmory.block;

import com.magistuarmory.item.ModItemTier;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.function.Supplier;
import net.minecraft.class_1767;
import net.minecraft.class_2248;
import net.minecraft.class_2498;
import net.minecraft.class_7924;
import net.minecraft.class_4970.class_2251;

public class ModBlocks {
   public static DeferredRegister<class_2248> BLOCKS = DeferredRegister.create("magistuarmory", class_7924.field_41254);
   public static RegistrySupplier<PaviseBlock> WOOD_PAVISE = BLOCKS.register(
      "wood_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "wood_pavise",
         ModBlockEntityTypes.WOOD_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> GOLD_PAVISE = BLOCKS.register(
      "gold_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "gold_pavise",
         ModBlockEntityTypes.GOLD_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> STONE_PAVISE = BLOCKS.register(
      "stone_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "stone_pavise",
         ModBlockEntityTypes.STONE_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> IRON_PAVISE = BLOCKS.register(
      "iron_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "iron_pavise",
         ModBlockEntityTypes.IRON_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> DIAMOND_PAVISE = BLOCKS.register(
      "diamond_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "diamond_pavise",
         ModBlockEntityTypes.DIAMOND_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> NETHERITE_PAVISE = BLOCKS.register(
      "netherite_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "netherite_pavise",
         ModBlockEntityTypes.NETHERITE_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> TIN_PAVISE = BLOCKS.register(
      "tin_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "tin_pavise",
         ModBlockEntityTypes.TIN_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> COPPER_PAVISE = BLOCKS.register(
      "copper_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "copper_pavise",
         ModBlockEntityTypes.COPPER_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> SILVER_PAVISE = BLOCKS.register(
      "silver_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "silver_pavise",
         ModBlockEntityTypes.SILVER_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> BRONZE_PAVISE = BLOCKS.register(
      "bronze_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "bronze_pavise",
         ModBlockEntityTypes.BRONZE_PAVISE
      )
   );
   public static RegistrySupplier<PaviseBlock> STEEL_PAVISE = BLOCKS.register(
      "steel_pavise",
      () -> new PaviseBlock(
         class_1767.field_7952,
         class_2251.method_9637().method_9624().method_45477().method_9626(class_2498.field_11547).method_50013(),
         "steel_pavise",
         ModBlockEntityTypes.STEEL_PAVISE
      )
   );
   public static RegistrySupplier<PaviseUpperCollisionBlock> PAVISE_UPPER_COLLISION = BLOCKS.register("pavise_upper_collision", PaviseUpperCollisionBlock::new);

   public static void init() {
      BLOCKS.register();
   }

   public static Supplier<PaviseBlock> getPaviseByMaterialName(ModItemTier material) {
      String var1 = material.getMaterialName();
      switch (var1) {
         case "wood":
            return WOOD_PAVISE;
         case "gold":
            return GOLD_PAVISE;
         case "stone":
            return STONE_PAVISE;
         case "iron":
            return IRON_PAVISE;
         case "diamond":
            return DIAMOND_PAVISE;
         case "netherite":
            return NETHERITE_PAVISE;
         case "tin":
            return TIN_PAVISE;
         case "copper":
            return COPPER_PAVISE;
         case "silver":
            return SILVER_PAVISE;
         case "bronze":
            return BRONZE_PAVISE;
         case "steel":
            return STEEL_PAVISE;
         default:
            throw new IllegalArgumentException("unknown material " + material.getMaterialName());
      }
   }
}
