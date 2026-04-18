package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_7923;
import net.minecraft.class_8170;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.block.LootrBarrelBlock;
import noobanidus.mods.lootr.common.block.LootrBrushableBlock;
import noobanidus.mods.lootr.common.block.LootrChestBlock;
import noobanidus.mods.lootr.common.block.LootrDecoratedPotBlock;
import noobanidus.mods.lootr.common.block.LootrInventoryBlock;
import noobanidus.mods.lootr.common.block.LootrShulkerBlock;
import noobanidus.mods.lootr.common.block.LootrTrappedChestBlock;
import noobanidus.mods.lootr.common.block.TrophyBlock;

public class ModBlocks {
   public static final LootrChestBlock CHEST = new LootrChestBlock(LootrConstants.CHEST_PROPERTIES);
   public static final LootrBarrelBlock BARREL = new LootrBarrelBlock(LootrConstants.BARREL_PROPERTIES);
   public static final LootrTrappedChestBlock TRAPPED_CHEST = new LootrTrappedChestBlock(LootrConstants.TRAPPED_CHEST_PROPERTIES);
   public static final LootrInventoryBlock INVENTORY = new LootrInventoryBlock(LootrConstants.INVENTORY_PROPERTIES);
   public static final class_2248 TROPHY = new TrophyBlock(LootrConstants.TROPHY_PROPERTIES);
   public static final LootrShulkerBlock SHULKER = new LootrShulkerBlock(LootrConstants.SHULKER_BOX_PROPERTIES);
   public static final LootrBrushableBlock SUSPICIOUS_SAND = new LootrBrushableBlock(
      class_2246.field_10102,
      ((class_8170)class_2246.field_42728).method_49811(),
      ((class_8170)class_2246.field_43227).method_49812(),
      LootrConstants.SUSPICIOUS_SAND_PROPERTIES
   );
   public static final LootrBrushableBlock SUSPICIOUS_GRAVEL = new LootrBrushableBlock(
      class_2246.field_10255,
      ((class_8170)class_2246.field_43227).method_49811(),
      ((class_8170)class_2246.field_43227).method_49812(),
      LootrConstants.SUSPICIOUS_GRAVEL_PROPERTIES
   );
   public static final LootrDecoratedPotBlock DECORATED_POT = new LootrDecoratedPotBlock(LootrConstants.DECORATED_POT_PROPERTIES);

   public static void registerBlocks() {
      class_2378.method_10230(class_7923.field_41175, LootrConstants.LOOTR_CHEST, CHEST);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.LOOTR_BARREL, BARREL);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.LOOTR_TRAPPED_CHEST, TRAPPED_CHEST);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.LOOTR_SHULKER, SHULKER);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.LOOTR_INVENTORY, INVENTORY);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.SUSPICIOUS_SAND, SUSPICIOUS_SAND);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.SUSPICIOUS_GRAVEL, SUSPICIOUS_GRAVEL);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.DECORATED_POT, DECORATED_POT);
      class_2378.method_10230(class_7923.field_41175, LootrConstants.TROPHY, TROPHY);
   }
}
