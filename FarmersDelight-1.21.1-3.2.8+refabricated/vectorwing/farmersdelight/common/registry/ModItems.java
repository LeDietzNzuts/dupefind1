package vectorwing.farmersdelight.common.registry;

import com.google.common.collect.Sets;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.class_1747;
import net.minecraft.class_1765;
import net.minecraft.class_1792;
import net.minecraft.class_1798;
import net.minecraft.class_1802;
import net.minecraft.class_1822;
import net.minecraft.class_1832;
import net.minecraft.class_1834;
import net.minecraft.class_2248;
import net.minecraft.class_4174;
import net.minecraft.class_7707;
import net.minecraft.class_1792.class_1793;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.CookingPotItem;
import vectorwing.farmersdelight.common.item.DogFoodItem;
import vectorwing.farmersdelight.common.item.DrinkableItem;
import vectorwing.farmersdelight.common.item.FuelBlockItem;
import vectorwing.farmersdelight.common.item.FuelItem;
import vectorwing.farmersdelight.common.item.HorseFeedItem;
import vectorwing.farmersdelight.common.item.HotCocoaItem;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.item.MelonJuiceItem;
import vectorwing.farmersdelight.common.item.MilkBottleItem;
import vectorwing.farmersdelight.common.item.MushroomColonyItem;
import vectorwing.farmersdelight.common.item.PopsicleItem;
import vectorwing.farmersdelight.common.item.RiceItem;
import vectorwing.farmersdelight.common.item.RopeItem;
import vectorwing.farmersdelight.common.item.RottenTomatoItem;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModItems {
   public static LinkedHashSet<Supplier<class_1792>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();
   public static final Supplier<class_1792> STOVE = registerWithTab("stove", () -> new class_1747(ModBlocks.STOVE.get(), basicItem()));
   public static final Supplier<class_1792> COOKING_POT = registerWithTab(
      "cooking_pot", () -> new CookingPotItem(ModBlocks.COOKING_POT.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> SKILLET = registerWithTab(
      "skillet",
      () -> new SkilletItem(
         ModBlocks.SKILLET.get(), basicItem().method_7889(1).method_57348(SkilletItem.createAttributes(SkilletItem.SKILLET_TIER, 5.0F, -3.1F))
      )
   );
   public static final Supplier<class_1792> CUTTING_BOARD = registerWithTab(
      "cutting_board", () -> new FuelBlockItem(ModBlocks.CUTTING_BOARD.get(), basicItem(), 200)
   );
   public static final Supplier<class_1792> BASKET = registerWithTab("basket", () -> new FuelBlockItem(ModBlocks.BASKET.get(), basicItem(), 300));
   public static final Supplier<class_1792> CARROT_CRATE = registerWithTab("carrot_crate", () -> new class_1747(ModBlocks.CARROT_CRATE.get(), basicItem()));
   public static final Supplier<class_1792> POTATO_CRATE = registerWithTab("potato_crate", () -> new class_1747(ModBlocks.POTATO_CRATE.get(), basicItem()));
   public static final Supplier<class_1792> BEETROOT_CRATE = registerWithTab(
      "beetroot_crate", () -> new class_1747(ModBlocks.BEETROOT_CRATE.get(), basicItem())
   );
   public static final Supplier<class_1792> CABBAGE_CRATE = registerWithTab("cabbage_crate", () -> new class_1747(ModBlocks.CABBAGE_CRATE.get(), basicItem()));
   public static final Supplier<class_1792> TOMATO_CRATE = registerWithTab("tomato_crate", () -> new class_1747(ModBlocks.TOMATO_CRATE.get(), basicItem()));
   public static final Supplier<class_1792> ONION_CRATE = registerWithTab("onion_crate", () -> new class_1747(ModBlocks.ONION_CRATE.get(), basicItem()));
   public static final Supplier<class_1792> RICE_BALE = registerWithTab("rice_bale", () -> new class_1747(ModBlocks.RICE_BALE.get(), basicItem()));
   public static final Supplier<class_1792> RICE_BAG = registerWithTab("rice_bag", () -> new class_1747(ModBlocks.RICE_BAG.get(), basicItem()));
   public static final Supplier<class_1792> STRAW_BALE = registerWithTab("straw_bale", () -> new class_1747(ModBlocks.STRAW_BALE.get(), basicItem()));
   public static final Supplier<class_1792> SAFETY_NET = registerWithTab("safety_net", () -> new FuelBlockItem(ModBlocks.SAFETY_NET.get(), basicItem(), 200));
   public static final Supplier<class_1792> OAK_CABINET = registerWithTab("oak_cabinet", () -> new FuelBlockItem(ModBlocks.OAK_CABINET.get(), basicItem(), 300));
   public static final Supplier<class_1792> SPRUCE_CABINET = registerWithTab(
      "spruce_cabinet", () -> new FuelBlockItem(ModBlocks.SPRUCE_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> BIRCH_CABINET = registerWithTab(
      "birch_cabinet", () -> new FuelBlockItem(ModBlocks.BIRCH_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> JUNGLE_CABINET = registerWithTab(
      "jungle_cabinet", () -> new FuelBlockItem(ModBlocks.JUNGLE_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> ACACIA_CABINET = registerWithTab(
      "acacia_cabinet", () -> new FuelBlockItem(ModBlocks.ACACIA_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> DARK_OAK_CABINET = registerWithTab(
      "dark_oak_cabinet", () -> new FuelBlockItem(ModBlocks.DARK_OAK_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> MANGROVE_CABINET = registerWithTab(
      "mangrove_cabinet", () -> new FuelBlockItem(ModBlocks.MANGROVE_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> CHERRY_CABINET = registerWithTab(
      "cherry_cabinet", () -> new FuelBlockItem(ModBlocks.CHERRY_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> BAMBOO_CABINET = registerWithTab(
      "bamboo_cabinet", () -> new FuelBlockItem(ModBlocks.BAMBOO_CABINET.get(), basicItem(), 300)
   );
   public static final Supplier<class_1792> CRIMSON_CABINET = registerWithTab(
      "crimson_cabinet", () -> new class_1747(ModBlocks.CRIMSON_CABINET.get(), basicItem())
   );
   public static final Supplier<class_1792> WARPED_CABINET = registerWithTab(
      "warped_cabinet", () -> new class_1747(ModBlocks.WARPED_CABINET.get(), basicItem())
   );
   public static final Supplier<class_1792> TATAMI = registerWithTab("tatami", () -> new FuelBlockItem(ModBlocks.TATAMI.get(), basicItem(), 400));
   public static final Supplier<class_1792> FULL_TATAMI_MAT = registerWithTab(
      "full_tatami_mat", () -> new FuelBlockItem(ModBlocks.FULL_TATAMI_MAT.get(), basicItem(), 200)
   );
   public static final Supplier<class_1792> HALF_TATAMI_MAT = registerWithTab(
      "half_tatami_mat", () -> new FuelBlockItem(ModBlocks.HALF_TATAMI_MAT.get(), basicItem())
   );
   public static final Supplier<class_1792> CANVAS_RUG = registerWithTab("canvas_rug", () -> new FuelBlockItem(ModBlocks.CANVAS_RUG.get(), basicItem(), 200));
   public static final Supplier<class_1792> ORGANIC_COMPOST = registerWithTab(
      "organic_compost", () -> new class_1747(ModBlocks.ORGANIC_COMPOST.get(), basicItem())
   );
   public static final Supplier<class_1792> RICH_SOIL = registerWithTab("rich_soil", () -> new class_1747(ModBlocks.RICH_SOIL.get(), basicItem()));
   public static final Supplier<class_1792> RICH_SOIL_FARMLAND = registerWithTab(
      "rich_soil_farmland", () -> new class_1747(ModBlocks.RICH_SOIL_FARMLAND.get(), basicItem())
   );
   public static final Supplier<class_1792> ROPE = registerWithTab("rope", () -> new RopeItem(ModBlocks.ROPE.get(), basicItem()));
   public static final Supplier<class_1792> CANVAS_SIGN = registerWithTab(
      "canvas_sign", () -> new class_1822(basicItem(), ModBlocks.CANVAS_SIGN.get(), ModBlocks.CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> HANGING_CANVAS_SIGN = registerWithTab(
      "hanging_canvas_sign", () -> new class_7707(ModBlocks.HANGING_CANVAS_SIGN.get(), ModBlocks.HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> WHITE_CANVAS_SIGN = registerWithTab(
      "white_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.WHITE_CANVAS_SIGN.get(), ModBlocks.WHITE_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> WHITE_HANGING_CANVAS_SIGN = registerWithTab(
      "white_hanging_canvas_sign", () -> new class_7707(ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(), ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> LIGHT_GRAY_CANVAS_SIGN = registerWithTab(
      "light_gray_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> LIGHT_GRAY_HANGING_CANVAS_SIGN = registerWithTab(
      "light_gray_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> GRAY_CANVAS_SIGN = registerWithTab(
      "gray_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.GRAY_CANVAS_SIGN.get(), ModBlocks.GRAY_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> GRAY_HANGING_CANVAS_SIGN = registerWithTab(
      "gray_hanging_canvas_sign", () -> new class_7707(ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> BLACK_CANVAS_SIGN = registerWithTab(
      "black_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.BLACK_CANVAS_SIGN.get(), ModBlocks.BLACK_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> BLACK_HANGING_CANVAS_SIGN = registerWithTab(
      "black_hanging_canvas_sign", () -> new class_7707(ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(), ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> BROWN_CANVAS_SIGN = registerWithTab(
      "brown_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.BROWN_CANVAS_SIGN.get(), ModBlocks.BROWN_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> BROWN_HANGING_CANVAS_SIGN = registerWithTab(
      "brown_hanging_canvas_sign", () -> new class_7707(ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(), ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> RED_CANVAS_SIGN = registerWithTab(
      "red_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.RED_CANVAS_SIGN.get(), ModBlocks.RED_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> RED_HANGING_CANVAS_SIGN = registerWithTab(
      "red_hanging_canvas_sign", () -> new class_7707(ModBlocks.RED_HANGING_CANVAS_SIGN.get(), ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> ORANGE_CANVAS_SIGN = registerWithTab(
      "orange_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.ORANGE_CANVAS_SIGN.get(), ModBlocks.ORANGE_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> ORANGE_HANGING_CANVAS_SIGN = registerWithTab(
      "orange_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(), ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> YELLOW_CANVAS_SIGN = registerWithTab(
      "yellow_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.YELLOW_CANVAS_SIGN.get(), ModBlocks.YELLOW_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> YELLOW_HANGING_CANVAS_SIGN = registerWithTab(
      "yellow_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(), ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> LIME_CANVAS_SIGN = registerWithTab(
      "lime_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.LIME_CANVAS_SIGN.get(), ModBlocks.LIME_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> LIME_HANGING_CANVAS_SIGN = registerWithTab(
      "lime_hanging_canvas_sign", () -> new class_7707(ModBlocks.LIME_HANGING_CANVAS_SIGN.get(), ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> GREEN_CANVAS_SIGN = registerWithTab(
      "green_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.GREEN_CANVAS_SIGN.get(), ModBlocks.GREEN_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> GREEN_HANGING_CANVAS_SIGN = registerWithTab(
      "green_hanging_canvas_sign", () -> new class_7707(ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(), ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> CYAN_CANVAS_SIGN = registerWithTab(
      "cyan_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.CYAN_CANVAS_SIGN.get(), ModBlocks.CYAN_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> CYAN_HANGING_CANVAS_SIGN = registerWithTab(
      "cyan_hanging_canvas_sign", () -> new class_7707(ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(), ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> LIGHT_BLUE_CANVAS_SIGN = registerWithTab(
      "light_blue_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> LIGHT_BLUE_HANGING_CANVAS_SIGN = registerWithTab(
      "light_blue_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> BLUE_CANVAS_SIGN = registerWithTab(
      "blue_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.BLUE_CANVAS_SIGN.get(), ModBlocks.BLUE_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> BLUE_HANGING_CANVAS_SIGN = registerWithTab(
      "blue_hanging_canvas_sign", () -> new class_7707(ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> PURPLE_CANVAS_SIGN = registerWithTab(
      "purple_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.PURPLE_CANVAS_SIGN.get(), ModBlocks.PURPLE_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> PURPLE_HANGING_CANVAS_SIGN = registerWithTab(
      "purple_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(), ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> MAGENTA_CANVAS_SIGN = registerWithTab(
      "magenta_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.MAGENTA_CANVAS_SIGN.get(), ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> MAGENTA_HANGING_CANVAS_SIGN = registerWithTab(
      "magenta_hanging_canvas_sign",
      () -> new class_7707(ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(), ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> PINK_CANVAS_SIGN = registerWithTab(
      "pink_canvas_sign", () -> new class_1822(basicItem(), ModBlocks.PINK_CANVAS_SIGN.get(), ModBlocks.PINK_CANVAS_WALL_SIGN.get())
   );
   public static final Supplier<class_1792> PINK_HANGING_CANVAS_SIGN = registerWithTab(
      "pink_hanging_canvas_sign", () -> new class_7707(ModBlocks.PINK_HANGING_CANVAS_SIGN.get(), ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get(), basicItem())
   );
   public static final Supplier<class_1792> FLINT_KNIFE = registerWithTab("flint_knife", () -> new KnifeItem(ModMaterials.FLINT, knifeItem(ModMaterials.FLINT)));
   public static final Supplier<class_1792> IRON_KNIFE = registerWithTab(
      "iron_knife", () -> new KnifeItem(class_1834.field_8923, knifeItem(class_1834.field_8923))
   );
   public static final Supplier<class_1792> DIAMOND_KNIFE = registerWithTab(
      "diamond_knife", () -> new KnifeItem(class_1834.field_8930, knifeItem(class_1834.field_8930))
   );
   public static final Supplier<class_1792> NETHERITE_KNIFE = registerWithTab(
      "netherite_knife", () -> new KnifeItem(class_1834.field_22033, knifeItem(class_1834.field_22033).method_24359())
   );
   public static final Supplier<class_1792> GOLDEN_KNIFE = registerWithTab(
      "golden_knife", () -> new KnifeItem(class_1834.field_8929, knifeItem(class_1834.field_8929))
   );
   public static final Supplier<class_1792> STRAW = registerWithTab("straw", () -> new FuelItem(basicItem()));
   public static final Supplier<class_1792> CANVAS = registerWithTab("canvas", () -> new FuelItem(basicItem(), 400));
   public static final Supplier<class_1792> TREE_BARK = registerWithTab("tree_bark", () -> new FuelItem(basicItem(), 200));
   public static final Supplier<class_1792> SANDY_SHRUB = registerWithTab("sandy_shrub", () -> new class_1747(ModBlocks.SANDY_SHRUB.get(), basicItem()));
   public static final Supplier<class_1792> WILD_CABBAGES = registerWithTab("wild_cabbages", () -> new class_1747(ModBlocks.WILD_CABBAGES.get(), basicItem()));
   public static final Supplier<class_1792> WILD_ONIONS = registerWithTab("wild_onions", () -> new class_1747(ModBlocks.WILD_ONIONS.get(), basicItem()));
   public static final Supplier<class_1792> WILD_TOMATOES = registerWithTab("wild_tomatoes", () -> new class_1747(ModBlocks.WILD_TOMATOES.get(), basicItem()));
   public static final Supplier<class_1792> WILD_CARROTS = registerWithTab("wild_carrots", () -> new class_1747(ModBlocks.WILD_CARROTS.get(), basicItem()));
   public static final Supplier<class_1792> WILD_POTATOES = registerWithTab("wild_potatoes", () -> new class_1747(ModBlocks.WILD_POTATOES.get(), basicItem()));
   public static final Supplier<class_1792> WILD_BEETROOTS = registerWithTab(
      "wild_beetroots", () -> new class_1747(ModBlocks.WILD_BEETROOTS.get(), basicItem())
   );
   public static final Supplier<class_1792> WILD_RICE = registerWithTab("wild_rice", () -> new class_1765(ModBlocks.WILD_RICE.get(), basicItem()));
   public static final Supplier<class_1792> BROWN_MUSHROOM_COLONY = registerWithTab(
      "brown_mushroom_colony", () -> new MushroomColonyItem(ModBlocks.BROWN_MUSHROOM_COLONY.get(), basicItem())
   );
   public static final Supplier<class_1792> RED_MUSHROOM_COLONY = registerWithTab(
      "red_mushroom_colony", () -> new MushroomColonyItem(ModBlocks.RED_MUSHROOM_COLONY.get(), basicItem())
   );
   public static final Supplier<class_1792> CABBAGE = registerWithTab("cabbage", () -> new class_1792(foodItem(FoodValues.CABBAGE)));
   public static final Supplier<class_1792> TOMATO = registerWithTab("tomato", () -> new class_1792(foodItem(FoodValues.TOMATO)));
   public static final Supplier<class_1792> ONION = registerWithTab("onion", () -> new class_1798(ModBlocks.ONION_CROP.get(), foodItem(FoodValues.ONION)));
   public static final Supplier<class_1792> RICE_PANICLE = registerWithTab("rice_panicle", () -> new class_1792(basicItem()));
   public static final Supplier<class_1792> RICE = registerWithTab("rice", () -> new RiceItem(ModBlocks.RICE_CROP.get(), basicItem()));
   public static final Supplier<class_1792> CABBAGE_SEEDS = registerWithTab("cabbage_seeds", () -> new class_1798(ModBlocks.CABBAGE_CROP.get(), basicItem()));
   public static final Supplier<class_1792> TOMATO_SEEDS = registerWithTab(
      "tomato_seeds", () -> new class_1798(ModBlocks.BUDDING_TOMATO_CROP.get(), basicItem()) {
         public void method_7713(Map<class_2248, class_1792> blockToItemMap, class_1792 item) {
            super.method_7713(blockToItemMap, item);
            blockToItemMap.put(ModBlocks.TOMATO_CROP.get(), item);
         }
      }
   );
   public static final Supplier<class_1792> ROTTEN_TOMATO = registerWithTab("rotten_tomato", () -> new RottenTomatoItem(new class_1793().method_7889(16)));
   public static final Supplier<class_1792> FRIED_EGG = registerWithTab("fried_egg", () -> new class_1792(foodItem(FoodValues.FRIED_EGG)));
   public static final Supplier<class_1792> MILK_BOTTLE = registerWithTab("milk_bottle", () -> new MilkBottleItem(drinkItem()));
   public static final Supplier<class_1792> HOT_COCOA = registerWithTab("hot_cocoa", () -> new HotCocoaItem(drinkItem()));
   public static final Supplier<class_1792> APPLE_CIDER = registerWithTab(
      "apple_cider", () -> new DrinkableItem(drinkItem().method_19265(FoodValues.APPLE_CIDER), true, false)
   );
   public static final Supplier<class_1792> MELON_JUICE = registerWithTab("melon_juice", () -> new MelonJuiceItem(drinkItem()));
   public static final Supplier<class_1792> TOMATO_SAUCE = registerWithTab(
      "tomato_sauce", () -> new ConsumableItem(foodItem(FoodValues.TOMATO_SAUCE).method_7896(class_1802.field_8428))
   );
   public static final Supplier<class_1792> WHEAT_DOUGH = registerWithTab("wheat_dough", () -> new class_1792(foodItem(FoodValues.WHEAT_DOUGH)));
   public static final Supplier<class_1792> RAW_PASTA = registerWithTab("raw_pasta", () -> new class_1792(foodItem(FoodValues.RAW_PASTA)));
   public static final Supplier<class_1792> PUMPKIN_SLICE = registerWithTab("pumpkin_slice", () -> new class_1792(foodItem(FoodValues.PUMPKIN_SLICE)));
   public static final Supplier<class_1792> CABBAGE_LEAF = registerWithTab("cabbage_leaf", () -> new class_1792(foodItem(FoodValues.CABBAGE_LEAF)));
   public static final Supplier<class_1792> MINCED_BEEF = registerWithTab("minced_beef", () -> new class_1792(foodItem(FoodValues.MINCED_BEEF)));
   public static final Supplier<class_1792> BEEF_PATTY = registerWithTab("beef_patty", () -> new class_1792(foodItem(FoodValues.BEEF_PATTY)));
   public static final Supplier<class_1792> CHICKEN_CUTS = registerWithTab("chicken_cuts", () -> new class_1792(foodItem(FoodValues.CHICKEN_CUTS)));
   public static final Supplier<class_1792> COOKED_CHICKEN_CUTS = registerWithTab(
      "cooked_chicken_cuts", () -> new class_1792(foodItem(FoodValues.COOKED_CHICKEN_CUTS))
   );
   public static final Supplier<class_1792> BACON = registerWithTab("bacon", () -> new class_1792(foodItem(FoodValues.BACON)));
   public static final Supplier<class_1792> COOKED_BACON = registerWithTab("cooked_bacon", () -> new class_1792(foodItem(FoodValues.COOKED_BACON)));
   public static final Supplier<class_1792> COD_SLICE = registerWithTab("cod_slice", () -> new class_1792(foodItem(FoodValues.COD_SLICE)));
   public static final Supplier<class_1792> COOKED_COD_SLICE = registerWithTab("cooked_cod_slice", () -> new class_1792(foodItem(FoodValues.COOKED_COD_SLICE)));
   public static final Supplier<class_1792> SALMON_SLICE = registerWithTab("salmon_slice", () -> new class_1792(foodItem(FoodValues.SALMON_SLICE)));
   public static final Supplier<class_1792> COOKED_SALMON_SLICE = registerWithTab(
      "cooked_salmon_slice", () -> new class_1792(foodItem(FoodValues.COOKED_SALMON_SLICE))
   );
   public static final Supplier<class_1792> MUTTON_CHOPS = registerWithTab("mutton_chops", () -> new class_1792(foodItem(FoodValues.MUTTON_CHOPS)));
   public static final Supplier<class_1792> COOKED_MUTTON_CHOPS = registerWithTab(
      "cooked_mutton_chops", () -> new class_1792(foodItem(FoodValues.COOKED_MUTTON_CHOPS))
   );
   public static final Supplier<class_1792> HAM = registerWithTab("ham", () -> new class_1792(foodItem(FoodValues.HAM)));
   public static final Supplier<class_1792> SMOKED_HAM = registerWithTab("smoked_ham", () -> new class_1792(foodItem(FoodValues.SMOKED_HAM)));
   public static final Supplier<class_1792> PIE_CRUST = registerWithTab("pie_crust", () -> new class_1792(foodItem(FoodValues.PIE_CRUST)));
   public static final Supplier<class_1792> APPLE_PIE = registerWithTab("apple_pie", () -> new class_1747(ModBlocks.APPLE_PIE.get(), basicItem()));
   public static final Supplier<class_1792> SWEET_BERRY_CHEESECAKE = registerWithTab(
      "sweet_berry_cheesecake", () -> new class_1747(ModBlocks.SWEET_BERRY_CHEESECAKE.get(), basicItem())
   );
   public static final Supplier<class_1792> CHOCOLATE_PIE = registerWithTab("chocolate_pie", () -> new class_1747(ModBlocks.CHOCOLATE_PIE.get(), basicItem()));
   public static final Supplier<class_1792> CAKE_SLICE = registerWithTab("cake_slice", () -> new class_1792(foodItem(FoodValues.CAKE_SLICE)));
   public static final Supplier<class_1792> APPLE_PIE_SLICE = registerWithTab("apple_pie_slice", () -> new class_1792(foodItem(FoodValues.PIE_SLICE)));
   public static final Supplier<class_1792> SWEET_BERRY_CHEESECAKE_SLICE = registerWithTab(
      "sweet_berry_cheesecake_slice", () -> new class_1792(foodItem(FoodValues.PIE_SLICE))
   );
   public static final Supplier<class_1792> CHOCOLATE_PIE_SLICE = registerWithTab("chocolate_pie_slice", () -> new class_1792(foodItem(FoodValues.PIE_SLICE)));
   public static final Supplier<class_1792> SWEET_BERRY_COOKIE = registerWithTab("sweet_berry_cookie", () -> new class_1792(foodItem(FoodValues.COOKIES)));
   public static final Supplier<class_1792> HONEY_COOKIE = registerWithTab("honey_cookie", () -> new class_1792(foodItem(FoodValues.COOKIES)));
   public static final Supplier<class_1792> MELON_POPSICLE = registerWithTab("melon_popsicle", () -> new PopsicleItem(foodItem(FoodValues.POPSICLE)));
   public static final Supplier<class_1792> GLOW_BERRY_CUSTARD = registerWithTab(
      "glow_berry_custard", () -> new ConsumableItem(foodItem(FoodValues.GLOW_BERRY_CUSTARD).method_7896(class_1802.field_8469).method_7889(16))
   );
   public static final Supplier<class_1792> FRUIT_SALAD = registerWithTab("fruit_salad", () -> new ConsumableItem(bowlFoodItem(FoodValues.FRUIT_SALAD), true));
   public static final Supplier<class_1792> MIXED_SALAD = registerWithTab("mixed_salad", () -> new ConsumableItem(bowlFoodItem(FoodValues.MIXED_SALAD), true));
   public static final Supplier<class_1792> NETHER_SALAD = registerWithTab("nether_salad", () -> new ConsumableItem(bowlFoodItem(FoodValues.NETHER_SALAD)));
   public static final Supplier<class_1792> BARBECUE_STICK = registerWithTab("barbecue_stick", () -> new class_1792(foodItem(FoodValues.BARBECUE_STICK)));
   public static final Supplier<class_1792> EGG_SANDWICH = registerWithTab("egg_sandwich", () -> new class_1792(foodItem(FoodValues.EGG_SANDWICH)));
   public static final Supplier<class_1792> CHICKEN_SANDWICH = registerWithTab("chicken_sandwich", () -> new class_1792(foodItem(FoodValues.CHICKEN_SANDWICH)));
   public static final Supplier<class_1792> HAMBURGER = registerWithTab("hamburger", () -> new class_1792(foodItem(FoodValues.HAMBURGER)));
   public static final Supplier<class_1792> BACON_SANDWICH = registerWithTab("bacon_sandwich", () -> new class_1792(foodItem(FoodValues.BACON_SANDWICH)));
   public static final Supplier<class_1792> MUTTON_WRAP = registerWithTab("mutton_wrap", () -> new class_1792(foodItem(FoodValues.MUTTON_WRAP)));
   public static final Supplier<class_1792> DUMPLINGS = registerWithTab("dumplings", () -> new class_1792(foodItem(FoodValues.DUMPLINGS)));
   public static final Supplier<class_1792> STUFFED_POTATO = registerWithTab("stuffed_potato", () -> new class_1792(foodItem(FoodValues.STUFFED_POTATO)));
   public static final Supplier<class_1792> CABBAGE_ROLLS = registerWithTab("cabbage_rolls", () -> new class_1792(foodItem(FoodValues.CABBAGE_ROLLS)));
   public static final Supplier<class_1792> SALMON_ROLL = registerWithTab("salmon_roll", () -> new class_1792(foodItem(FoodValues.SALMON_ROLL)));
   public static final Supplier<class_1792> COD_ROLL = registerWithTab("cod_roll", () -> new class_1792(foodItem(FoodValues.COD_ROLL)));
   public static final Supplier<class_1792> KELP_ROLL = registerWithTab("kelp_roll", () -> new class_1792(foodItem(FoodValues.KELP_ROLL)));
   public static final Supplier<class_1792> KELP_ROLL_SLICE = registerWithTab("kelp_roll_slice", () -> new class_1792(foodItem(FoodValues.KELP_ROLL_SLICE)));
   public static final Supplier<class_1792> COOKED_RICE = registerWithTab("cooked_rice", () -> new ConsumableItem(bowlFoodItem(FoodValues.COOKED_RICE), true));
   public static final Supplier<class_1792> BONE_BROTH = registerWithTab("bone_broth", () -> new DrinkableItem(bowlFoodItem(FoodValues.BONE_BROTH), true));
   public static final Supplier<class_1792> BEEF_STEW = registerWithTab("beef_stew", () -> new ConsumableItem(bowlFoodItem(FoodValues.BEEF_STEW), true));
   public static final Supplier<class_1792> CHICKEN_SOUP = registerWithTab(
      "chicken_soup", () -> new ConsumableItem(bowlFoodItem(FoodValues.CHICKEN_SOUP), true)
   );
   public static final Supplier<class_1792> VEGETABLE_SOUP = registerWithTab(
      "vegetable_soup", () -> new ConsumableItem(bowlFoodItem(FoodValues.VEGETABLE_SOUP), true)
   );
   public static final Supplier<class_1792> FISH_STEW = registerWithTab("fish_stew", () -> new ConsumableItem(bowlFoodItem(FoodValues.FISH_STEW), true));
   public static final Supplier<class_1792> FRIED_RICE = registerWithTab("fried_rice", () -> new ConsumableItem(bowlFoodItem(FoodValues.FRIED_RICE), true));
   public static final Supplier<class_1792> PUMPKIN_SOUP = registerWithTab(
      "pumpkin_soup", () -> new ConsumableItem(bowlFoodItem(FoodValues.PUMPKIN_SOUP), true)
   );
   public static final Supplier<class_1792> BAKED_COD_STEW = registerWithTab(
      "baked_cod_stew", () -> new ConsumableItem(bowlFoodItem(FoodValues.BAKED_COD_STEW), true)
   );
   public static final Supplier<class_1792> NOODLE_SOUP = registerWithTab("noodle_soup", () -> new ConsumableItem(bowlFoodItem(FoodValues.NOODLE_SOUP), true));
   public static final Supplier<class_1792> BACON_AND_EGGS = registerWithTab(
      "bacon_and_eggs", () -> new ConsumableItem(bowlFoodItem(FoodValues.BACON_AND_EGGS), true)
   );
   public static final Supplier<class_1792> PASTA_WITH_MEATBALLS = registerWithTab(
      "pasta_with_meatballs", () -> new ConsumableItem(bowlFoodItem(FoodValues.PASTA_WITH_MEATBALLS), true)
   );
   public static final Supplier<class_1792> PASTA_WITH_MUTTON_CHOP = registerWithTab(
      "pasta_with_mutton_chop", () -> new ConsumableItem(bowlFoodItem(FoodValues.PASTA_WITH_MUTTON_CHOP), true)
   );
   public static final Supplier<class_1792> MUSHROOM_RICE = registerWithTab(
      "mushroom_rice", () -> new ConsumableItem(bowlFoodItem(FoodValues.MUSHROOM_RICE), true)
   );
   public static final Supplier<class_1792> ROASTED_MUTTON_CHOPS = registerWithTab(
      "roasted_mutton_chops", () -> new ConsumableItem(bowlFoodItem(FoodValues.ROASTED_MUTTON_CHOPS), true)
   );
   public static final Supplier<class_1792> VEGETABLE_NOODLES = registerWithTab(
      "vegetable_noodles", () -> new ConsumableItem(bowlFoodItem(FoodValues.VEGETABLE_NOODLES), true)
   );
   public static final Supplier<class_1792> STEAK_AND_POTATOES = registerWithTab(
      "steak_and_potatoes", () -> new ConsumableItem(bowlFoodItem(FoodValues.STEAK_AND_POTATOES), true)
   );
   public static final Supplier<class_1792> RATATOUILLE = registerWithTab("ratatouille", () -> new ConsumableItem(bowlFoodItem(FoodValues.RATATOUILLE), true));
   public static final Supplier<class_1792> SQUID_INK_PASTA = registerWithTab(
      "squid_ink_pasta", () -> new ConsumableItem(bowlFoodItem(FoodValues.SQUID_INK_PASTA), true)
   );
   public static final Supplier<class_1792> GRILLED_SALMON = registerWithTab(
      "grilled_salmon", () -> new ConsumableItem(bowlFoodItem(FoodValues.GRILLED_SALMON), true)
   );
   public static final Supplier<class_1792> ROAST_CHICKEN_BLOCK = registerWithTab(
      "roast_chicken_block", () -> new class_1747(ModBlocks.ROAST_CHICKEN_BLOCK.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> ROAST_CHICKEN = registerWithTab(
      "roast_chicken", () -> new ConsumableItem(bowlFoodItem(FoodValues.ROAST_CHICKEN), true)
   );
   public static final Supplier<class_1792> STUFFED_PUMPKIN_BLOCK = registerWithTab(
      "stuffed_pumpkin_block", () -> new class_1747(ModBlocks.STUFFED_PUMPKIN_BLOCK.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> STUFFED_PUMPKIN = registerWithTab(
      "stuffed_pumpkin", () -> new ConsumableItem(bowlFoodItem(FoodValues.STUFFED_PUMPKIN), true)
   );
   public static final Supplier<class_1792> HONEY_GLAZED_HAM_BLOCK = registerWithTab(
      "honey_glazed_ham_block", () -> new class_1747(ModBlocks.HONEY_GLAZED_HAM_BLOCK.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> HONEY_GLAZED_HAM = registerWithTab(
      "honey_glazed_ham", () -> new ConsumableItem(bowlFoodItem(FoodValues.HONEY_GLAZED_HAM), true)
   );
   public static final Supplier<class_1792> SHEPHERDS_PIE_BLOCK = registerWithTab(
      "shepherds_pie_block", () -> new class_1747(ModBlocks.SHEPHERDS_PIE_BLOCK.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> SHEPHERDS_PIE = registerWithTab(
      "shepherds_pie", () -> new ConsumableItem(bowlFoodItem(FoodValues.SHEPHERDS_PIE), true)
   );
   public static final Supplier<class_1792> RICE_ROLL_MEDLEY_BLOCK = registerWithTab(
      "rice_roll_medley_block", () -> new class_1747(ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get(), basicItem().method_7889(1))
   );
   public static final Supplier<class_1792> DOG_FOOD = registerWithTab("dog_food", () -> new DogFoodItem(bowlFoodItem(FoodValues.DOG_FOOD)));
   public static final Supplier<class_1792> HORSE_FEED = registerWithTab("horse_feed", () -> new HorseFeedItem(basicItem().method_7889(16)));

   public static Supplier<class_1792> registerWithTab(String name, Supplier<class_1792> supplier) {
      Supplier<class_1792> block = RegUtils.regItem(name, supplier);
      CREATIVE_TAB_ITEMS.add(block);
      return block;
   }

   public static class_1793 basicItem() {
      return new class_1793();
   }

   public static class_1793 knifeItem(class_1832 tier) {
      return new class_1793().method_57348(KnifeItem.method_57346(tier, 0.5F, -2.0F));
   }

   public static class_1793 foodItem(class_4174 food) {
      return new class_1793().method_19265(food);
   }

   public static class_1793 bowlFoodItem(class_4174 food) {
      return new class_1793().method_19265(food).method_7896(class_1802.field_8428).method_7889(16);
   }

   public static class_1793 drinkItem() {
      return new class_1793().method_7896(class_1802.field_8469).method_7889(16);
   }

   public static void touch() {
   }
}
