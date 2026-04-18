package noobanidus.mods.lootr.fabric.init;

import net.minecraft.class_1747;
import net.minecraft.class_1814;
import net.minecraft.class_2378;
import net.minecraft.class_7923;
import net.minecraft.class_1792.class_1793;
import noobanidus.mods.lootr.common.api.LootrConstants;

public class ModItems {
   public static final class_1747 CHEST = new class_1747(ModBlocks.CHEST, new class_1793());
   public static final class_1747 BARREL = new class_1747(ModBlocks.BARREL, new class_1793());
   public static final class_1747 TRAPPED_CHEST = new class_1747(ModBlocks.TRAPPED_CHEST, new class_1793());
   public static final class_1747 SHULKER = new class_1747(ModBlocks.SHULKER, new class_1793());
   public static final class_1747 INVENTORY = new class_1747(ModBlocks.INVENTORY, new class_1793());
   public static final class_1747 SUSPICIOUS_SAND = new class_1747(ModBlocks.SUSPICIOUS_SAND, new class_1793());
   public static final class_1747 SUSPICIOUS_GRAVEL = new class_1747(ModBlocks.SUSPICIOUS_GRAVEL, new class_1793());
   public static final class_1747 DECORATED_POT = new class_1747(ModBlocks.DECORATED_POT, new class_1793());
   public static final class_1747 TROPHY = new class_1747(ModBlocks.TROPHY, new class_1793().method_7894(class_1814.field_8904));

   public static void registerItems() {
      class_2378.method_10230(class_7923.field_41178, LootrConstants.LOOTR_CHEST, CHEST);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.LOOTR_BARREL, BARREL);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.LOOTR_TRAPPED_CHEST, TRAPPED_CHEST);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.LOOTR_SHULKER, SHULKER);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.LOOTR_INVENTORY, INVENTORY);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.SUSPICIOUS_SAND, SUSPICIOUS_SAND);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.SUSPICIOUS_GRAVEL, SUSPICIOUS_GRAVEL);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.DECORATED_POT, DECORATED_POT);
      class_2378.method_10230(class_7923.field_41178, LootrConstants.TROPHY, TROPHY);
   }
}
