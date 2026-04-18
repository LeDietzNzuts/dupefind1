package vectorwing.farmersdelight.common.tag;

import net.minecraft.class_1291;
import net.minecraft.class_1299;
import net.minecraft.class_1792;
import net.minecraft.class_1959;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public class ModTags {
   public static final class_6862<class_2248> MINEABLE_WITH_KNIFE = modBlockTag("mineable/knife");
   public static final class_6862<class_2248> TERRAIN = modBlockTag("terrain");
   public static final class_6862<class_2248> STRAW_BLOCKS = modBlockTag("straw_blocks");
   public static final class_6862<class_2248> WILD_CROPS = modBlockTag("wild_crops");
   public static final class_6862<class_2248> ROPES = modBlockTag("ropes");
   public static final class_6862<class_2248> HEAT_SOURCES = modBlockTag("heat_sources");
   public static final class_6862<class_2248> HEAT_CONDUCTORS = modBlockTag("heat_conductors");
   public static final class_6862<class_2248> TRAY_HEAT_SOURCES = modBlockTag("tray_heat_sources");
   public static final class_6862<class_2248> COMPOST_ACTIVATORS = modBlockTag("compost_activators");
   public static final class_6862<class_2248> MUSHROOM_COLONY_GROWABLE_ON = modBlockTag("mushroom_colony_growable_on");
   public static final class_6862<class_2248> UNAFFECTED_BY_RICH_SOIL = modBlockTag("unaffected_by_rich_soil");
   public static final class_6862<class_2248> DROPS_CAKE_SLICE = modBlockTag("drops_cake_slice");
   public static final class_6862<class_2248> CAMPFIRE_SIGNAL_SMOKE = modBlockTag("campfire_signal_smoke");
   public static final class_6862<class_1792> KNIFE_ENCHANTABLE = modItemTag("enchantable/knife");
   public static final class_6862<class_1792> SNACKS = modItemTag("snacks");
   public static final class_6862<class_1792> MEALS = modItemTag("meals");
   public static final class_6862<class_1792> DRINKS = modItemTag("drinks");
   public static final class_6862<class_1792> SWEETS = modItemTag("sweets");
   public static final class_6862<class_1792> FEASTS = modItemTag("feasts");
   public static final class_6862<class_1792> WILD_CROPS_ITEM = modItemTag("wild_crops");
   public static final class_6862<class_1792> STRAW_HARVESTERS = modItemTag("straw_harvesters");
   public static final class_6862<class_1792> CABBAGE_ROLL_INGREDIENTS = modItemTag("cabbage_roll_ingredients");
   public static final class_6862<class_1792> OFFHAND_EQUIPMENT = modItemTag("offhand_equipment");
   public static final class_6862<class_1792> KNIVES = modItemTag("tools/knives");
   public static final class_6862<class_1792> CANVAS_SIGNS = modItemTag("canvas_signs");
   public static final class_6862<class_1792> HANGING_CANVAS_SIGNS = modItemTag("hanging_canvas_signs");
   public static final class_6862<class_1792> WOODEN_CABINETS = modItemTag("cabinets/wooden");
   public static final class_6862<class_1792> CABINETS = modItemTag("cabinets");
   public static final class_6862<class_1792> SERVING_CONTAINERS = modItemTag("serving_containers");
   public static final class_6862<class_1792> FLAT_ON_CUTTING_BOARD = modItemTag("flat_on_cutting_board");
   public static final class_6862<class_1299<?>> DOG_FOOD_USERS = modEntityTag("dog_food_users");
   public static final class_6862<class_1299<?>> HORSE_FEED_USERS = modEntityTag("horse_feed_users");
   public static final class_6862<class_1299<?>> HORSE_FEED_TEMPTED = modEntityTag("horse_feed_tempted");
   public static final class_6862<class_1959> HAS_BROWN_MUSHROOM_COLONY = modBiomeTag("has_brown_mushroom_colony");
   public static final class_6862<class_1959> HAS_RED_MUSHROOM_COLONY = modBiomeTag("has_red_mushroom_colony");
   public static final class_6862<class_1959> HAS_WILD_CABBAGE = modBiomeTag("has_wild_cabbage");
   public static final class_6862<class_1959> HAS_WILD_BEETROOTS = modBiomeTag("has_wild_beetroots");
   public static final class_6862<class_1959> WILD_CARROTS_WHITELIST = modBiomeTag("wild_carrots_whitelist");
   public static final class_6862<class_1959> WILD_CARROTS_BLACKLIST = modBiomeTag("wild_carrots_blacklist");
   public static final class_6862<class_1959> WILD_ONIONS_WHITELIST = modBiomeTag("wild_onions_whitelist");
   public static final class_6862<class_1959> WILD_ONIONS_BLACKLIST = modBiomeTag("wild_onions_blacklist");
   public static final class_6862<class_1959> WILD_POTATOES_WHITELIST = modBiomeTag("wild_potatoes_whitelist");
   public static final class_6862<class_1959> WILD_POTATOES_BLACKLIST = modBiomeTag("wild_potatoes_blacklist");
   public static final class_6862<class_1959> WILD_RICE_WHITELIST = modBiomeTag("wild_rice_whitelist");
   public static final class_6862<class_1959> WILD_RICE_BLACKLIST = modBiomeTag("wild_rice_blacklist");
   public static final class_6862<class_1959> WILD_TOMATOES_WHITELIST = modBiomeTag("wild_tomatoes_whitelist");
   public static final class_6862<class_1959> WILD_TOMATOES_BLACKLIST = modBiomeTag("wild_tomatoes_blacklist");
   public static final class_6862<class_2248> SURVIVES_RICH_SOIL = modBlockTag("survives/rich_soil");
   public static final class_6862<class_2248> DOES_NOT_SURVIVE_RICH_SOIL = modBlockTag("does_not_survive/rich_soil");
   public static final class_6862<class_2248> SURVIVES_RICH_SOIL_FARMLAND = modBlockTag("survives/rich_soil_farmland");
   public static final class_6862<class_2248> DOES_NOT_SURVIVE_RICH_SOIL_FARMLAND = modBlockTag("does_not_survive/rich_soil_farmland");
   public static final class_6862<class_1299<?>> DROPS_LEATHER = modEntityTag("drops_leather");
   public static final class_6862<class_1291> HOT_COCOA_IGNORED = modEffectTag("ignored/hot_cocoa");
   public static final class_6862<class_1291> MILK_BOTTLE_IGNORED = modEffectTag("ignored/milk_bottle");

   private static class_6862<class_1792> modItemTag(String path) {
      return class_6862.method_40092(class_7924.field_41197, class_2960.method_60655("farmersdelight", path));
   }

   private static class_6862<class_2248> modBlockTag(String path) {
      return class_6862.method_40092(class_7924.field_41254, class_2960.method_60655("farmersdelight", path));
   }

   private static class_6862<class_1299<?>> modEntityTag(String path) {
      return class_6862.method_40092(class_7924.field_41266, class_2960.method_60655("farmersdelight", path));
   }

   private static class_6862<class_1959> modBiomeTag(String path) {
      return class_6862.method_40092(class_7924.field_41236, class_2960.method_60655("farmersdelight", path));
   }

   private static class_6862<class_1291> modEffectTag(String path) {
      return class_6862.method_40092(class_7924.field_41208, class_2960.method_60655("farmersdelight", path));
   }
}
