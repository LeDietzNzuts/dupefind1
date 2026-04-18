package net.p3pp3rf1y.sophisticatedbackpacks.init;

import io.github.fabricators_of_create.porting_lib.util.DeferredRegister;
import java.util.List;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.minecraft.class_2248;
import net.minecraft.class_2591;
import net.minecraft.class_7923;
import net.minecraft.class_2591.class_2592;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlock;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackBlockEntity;
import team.reborn.energy.api.EnergyStorage;

public class ModBlocks {
   private static final DeferredRegister<class_2248> BLOCKS = DeferredRegister.create(class_7923.field_41175, "sophisticatedbackpacks");
   private static final DeferredRegister<class_2591<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(class_7923.field_41181, "sophisticatedbackpacks");
   public static final Supplier<BackpackBlock> BACKPACK = BLOCKS.register("backpack", () -> new BackpackBlock());
   public static final Supplier<BackpackBlock> COPPER_BACKPACK = BLOCKS.register("copper_backpack", () -> new BackpackBlock());
   public static final Supplier<BackpackBlock> IRON_BACKPACK = BLOCKS.register("iron_backpack", () -> new BackpackBlock());
   public static final Supplier<BackpackBlock> GOLD_BACKPACK = BLOCKS.register("gold_backpack", () -> new BackpackBlock());
   public static final Supplier<BackpackBlock> DIAMOND_BACKPACK = BLOCKS.register("diamond_backpack", () -> new BackpackBlock());
   public static final Supplier<BackpackBlock> NETHERITE_BACKPACK = BLOCKS.register("netherite_backpack", () -> new BackpackBlock(1200.0F));
   public static final List<Supplier<BackpackBlock>> BACKPACKS = List.of(
      BACKPACK, COPPER_BACKPACK, IRON_BACKPACK, GOLD_BACKPACK, DIAMOND_BACKPACK, NETHERITE_BACKPACK
   );
   public static final Supplier<class_2591<BackpackBlockEntity>> BACKPACK_TILE_TYPE = BLOCK_ENTITY_TYPES.register(
      "backpack",
      () -> class_2592.method_20528(
            BackpackBlockEntity::new,
            new class_2248[]{BACKPACK.get(), COPPER_BACKPACK.get(), IRON_BACKPACK.get(), GOLD_BACKPACK.get(), DIAMOND_BACKPACK.get(), NETHERITE_BACKPACK.get()}
         )
         .method_11034(null)
   );

   private ModBlocks() {
   }

   public static void registerHandlers() {
      BLOCKS.register();
      BLOCK_ENTITY_TYPES.register();
      UseBlockCallback.EVENT.register(BackpackBlock::playerInteract);
      registerCapabilities();
   }

   private static void registerCapabilities() {
      ItemStorage.SIDED.registerForBlockEntity(BackpackBlockEntity::getExternalItemHandler, BACKPACK_TILE_TYPE.get());
      FluidStorage.SIDED.registerForBlockEntity(BackpackBlockEntity::getExternalFluidHandler, BACKPACK_TILE_TYPE.get());
      EnergyStorage.SIDED.registerForBlockEntity(BackpackBlockEntity::getExternalEnergyStorage, BACKPACK_TILE_TYPE.get());
   }
}
