package noobanidus.mods.lootr.common.api;

import net.minecraft.class_2246;
import net.minecraft.class_2498;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_3619;
import net.minecraft.class_4970.class_2251;
import net.minecraft.class_4970.class_4973;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;

public class LootrConstants {
   private static final class_4973 posPredicate = (state, level, pos) -> {
      class_2586 blockentity = level.method_8321(pos);
      ILootrBlockEntity patt0$temp = LootrAPI.resolveBlockEntity(blockentity);
      return patt0$temp instanceof ILootrBlockEntity ? !patt0$temp.isPhysicallyOpen() : false;
   };
   public static final class_2960 SHERDSAPI_POT_DECORATIONS = class_2960.method_60655("sherdsapi", "stack_pot_decorations");
   public static final class_2960 SHERDSAPI_SHERD_PATTERN = class_2960.method_60655("sherdsapi", "sherd_pattern");
   public static final class_2960 LOOTR_CHEST = LootrAPI.rl("lootr_chest");
   public static final class_2960 LOOTR_TRAPPED_CHEST = LootrAPI.rl("lootr_trapped_chest");
   public static final class_2960 LOOTR_SHULKER = LootrAPI.rl("lootr_shulker");
   public static final class_2960 LOOTR_BARREL = LootrAPI.rl("lootr_barrel");
   public static final class_2960 LOOTR_INVENTORY = LootrAPI.rl("lootr_inventory");
   public static final class_2960 LOOTR_CART = LootrAPI.rl("lootr_cart");
   public static final class_2960 CHEST = LootrAPI.rl("chest");
   public static final class_2960 TRAPPED_CHEST = LootrAPI.rl("trapped_chest");
   public static final class_2960 SHULKER = LootrAPI.rl("shulker");
   public static final class_2960 SHULKER_BOX = LootrAPI.rl("shulker_box");
   public static final class_2960 BARREL = LootrAPI.rl("barrel");
   public static final class_2960 INVENTORY = LootrAPI.rl("inventory");
   public static final class_2960 MINECART = LootrAPI.rl("chest_minecart");
   public static final class_2960 TROPHY = LootrAPI.rl("trophy");
   public static final class_2960 DECORATED_POT = LootrAPI.rl("decorated_pot");
   public static final class_2960 BRUSHABLE_BLOCK = LootrAPI.rl("brushable_block");
   public static final class_2960 SUSPICIOUS_SAND = LootrAPI.rl("suspicious_sand");
   public static final class_2960 SUSPICIOUS_GRAVEL = LootrAPI.rl("suspicious_gravel");
   public static final class_2960 ITEM_FRAME = LootrAPI.rl("item_frame");
   public static final class_2960 UNOPENED_PARTICLE = LootrAPI.rl("unopened_particle");
   public static final class_2960 SIMPLE = LootrAPI.rl("simple");
   public static final class_2960 CAN_CONVERT = LootrAPI.rl("lootr_can_convert_item_frame");
   public static final String CAN_CONVERT_TAG = CAN_CONVERT.toString();
   public static final String LOOTR_SPECIAL_CHEST = "lootr:special_loot_chest";
   public static final String LOOTR_SPECIAL_BARREL = "lootr:special_loot_barrel";
   public static final String LOOTR_SPECIAL_TRAPPED_CHEST = "lootr:special_trapped_loot_chest";
   public static final String LOOTR_SPECIAL_SHULKER = "lootr:special_loot_shulker";
   public static final String LOOTR_SPECIAL_INVENTORY = "lootr:special_loot_inventory";
   public static final String LOOTR_DATA_DIRECTORY = "lootr";
   public static final String REGION_DIRECTORY = "region";
   public static final String MCA_FILE_EXTENSION = ".mca";
   public static final class_2251 CHEST_PROPERTIES = class_2251.method_9630(class_2246.field_10034).method_9632(2.5F);
   public static final class_2251 TRAPPED_CHEST_PROPERTIES = class_2251.method_9630(class_2246.field_10380).method_9632(2.5F);
   public static final class_2251 BARREL_PROPERTIES = class_2251.method_9630(class_2246.field_16328).method_9632(2.5F);
   public static final class_2251 INVENTORY_PROPERTIES = class_2251.method_9637().method_9632(2.5F).method_9626(class_2498.field_11547);
   public static final class_2251 TROPHY_PROPERTIES = class_2251.method_9637()
      .method_9632(15.0F)
      .method_9626(class_2498.field_11533)
      .method_22488()
      .method_9631(o -> 15);
   public static final class_2251 SHULKER_BOX_PROPERTIES = class_2251.method_9637()
      .method_9632(2.5F)
      .method_9624()
      .method_22488()
      .method_51369()
      .method_50012(class_3619.field_15971)
      .method_26243(posPredicate)
      .method_26245(posPredicate);
   public static final class_2251 SUSPICIOUS_SAND_PROPERTIES = class_2251.method_9630(class_2246.field_42728).method_9632(2.5F);
   public static final class_2251 SUSPICIOUS_GRAVEL_PROPERTIES = class_2251.method_9630(class_2246.field_43227).method_9632(2.5F);
   public static final class_2251 DECORATED_POT_PROPERTIES = class_2251.method_9630(class_2246.field_42752)
      .method_9632(1.5F)
      .method_9626(class_2498.field_42771);
}
