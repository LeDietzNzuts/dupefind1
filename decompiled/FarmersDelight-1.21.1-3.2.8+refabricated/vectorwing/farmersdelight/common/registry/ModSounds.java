package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModSounds {
   public static final Supplier<class_3414> BLOCK_STOVE_CRACKLE = RegUtils.regSound(
      "block.stove.crackle", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.stove.crackle"))
   );
   public static final Supplier<class_3414> BLOCK_COOKING_POT_BOIL = RegUtils.regSound(
      "block.cooking_pot.boil", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.cooking_pot.boil"))
   );
   public static final Supplier<class_3414> BLOCK_COOKING_POT_BOIL_SOUP = RegUtils.regSound(
      "block.cooking_pot.boil_soup", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.cooking_pot.boil_soup"))
   );
   public static final Supplier<class_3414> BLOCK_CUTTING_BOARD_KNIFE = RegUtils.regSound(
      "block.cutting_board.knife", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.cutting_board.knife"))
   );
   public static final Supplier<class_3414> BLOCK_CABINET_OPEN = RegUtils.regSound(
      "block.cabinet.open", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.cabinet.open"))
   );
   public static final Supplier<class_3414> BLOCK_CABINET_CLOSE = RegUtils.regSound(
      "block.cabinet.close", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.cabinet.close"))
   );
   public static final Supplier<class_3414> BLOCK_SKILLET_SIZZLE = RegUtils.regSound(
      "block.skillet.sizzle", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.skillet.sizzle"))
   );
   public static final Supplier<class_3414> BLOCK_SKILLET_ADD_FOOD = RegUtils.regSound(
      "block.skillet.add_food", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.skillet.add_food"))
   );
   public static final Supplier<class_3414> ITEM_SKILLET_ATTACK_STRONG = RegUtils.regSound(
      "item.skillet.attack.strong", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "item.skillet.attack.strong"))
   );
   public static final Supplier<class_3414> ITEM_SKILLET_ATTACK_WEAK = RegUtils.regSound(
      "item.skillet.attack.weak", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "item.skillet.attack.weak"))
   );
   public static final Supplier<class_3414> ITEM_TOMATO_PICK_FROM_BUSH = RegUtils.regSound(
      "block.tomato_bush.pick_tomatoes", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "block.tomato_bush.pick_tomatoes"))
   );
   public static final Supplier<class_3414> ENTITY_ROTTEN_TOMATO_THROW = RegUtils.regSound(
      "entity.rotten_tomato.throw", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "entity.rotten_tomato.throw"))
   );
   public static final Supplier<class_3414> ENTITY_ROTTEN_TOMATO_HIT = RegUtils.regSound(
      "entity.rotten_tomato.hit", () -> class_3414.method_47908(class_2960.method_60655("farmersdelight", "entity.rotten_tomato.hit"))
   );

   public static void touch() {
   }
}
