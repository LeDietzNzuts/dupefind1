package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2591;
import net.minecraft.class_7923;
import net.minecraft.class_2591.class_2592;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.block.entity.LootrBarrelBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrBrushableBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrChestBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrDecoratedPotBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrInventoryBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrShulkerBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrTrappedChestBlockEntity;

public class ModBlockEntities {
   public static final class_2591<LootrChestBlockEntity> LOOTR_CHEST = class_2592.method_20528(LootrChestBlockEntity::new, new class_2248[]{ModBlocks.CHEST})
      .method_11034(null);
   public static final class_2591<LootrBarrelBlockEntity> LOOTR_BARREL = class_2592.method_20528(
         LootrBarrelBlockEntity::new, new class_2248[]{ModBlocks.BARREL}
      )
      .method_11034(null);
   public static final class_2591<LootrTrappedChestBlockEntity> LOOTR_TRAPPED_CHEST = class_2592.method_20528(
         LootrTrappedChestBlockEntity::new, new class_2248[]{ModBlocks.TRAPPED_CHEST}
      )
      .method_11034(null);
   public static final class_2591<LootrShulkerBlockEntity> LOOTR_SHULKER = class_2592.method_20528(
         LootrShulkerBlockEntity::new, new class_2248[]{ModBlocks.SHULKER}
      )
      .method_11034(null);
   public static final class_2591<LootrInventoryBlockEntity> LOOTR_INVENTORY = class_2592.method_20528(
         LootrInventoryBlockEntity::new, new class_2248[]{ModBlocks.INVENTORY}
      )
      .method_11034(null);
   public static final class_2591<LootrBrushableBlockEntity> LOOTR_BRUSHABLE_BLOCK = class_2592.method_20528(
         LootrBrushableBlockEntity::new, new class_2248[]{ModBlocks.SUSPICIOUS_GRAVEL, ModBlocks.SUSPICIOUS_SAND}
      )
      .method_11034(null);
   public static final class_2591<LootrDecoratedPotBlockEntity> LOOTR_DECORATED_POT = class_2592.method_20528(
         LootrDecoratedPotBlockEntity::new, new class_2248[]{ModBlocks.DECORATED_POT}
      )
      .method_11034(null);

   public static void registerBlockEntities() {
      class_2378.method_10230(class_7923.field_41181, LootrConstants.LOOTR_CHEST, LOOTR_CHEST);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.LOOTR_TRAPPED_CHEST, LOOTR_TRAPPED_CHEST);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.LOOTR_SHULKER, LOOTR_SHULKER);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.LOOTR_BARREL, LOOTR_BARREL);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.LOOTR_INVENTORY, LOOTR_INVENTORY);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.BRUSHABLE_BLOCK, LOOTR_BRUSHABLE_BLOCK);
      class_2378.method_10230(class_7923.field_41181, LootrConstants.DECORATED_POT, LOOTR_DECORATED_POT);
   }
}
