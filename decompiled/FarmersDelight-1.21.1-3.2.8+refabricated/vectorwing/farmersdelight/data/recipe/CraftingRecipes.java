package vectorwing.farmersdelight.data.recipe;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_1856;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2447;
import net.minecraft.class_2450;
import net.minecraft.class_2456;
import net.minecraft.class_2960;
import net.minecraft.class_3489;
import net.minecraft.class_6862;
import net.minecraft.class_7800;
import net.minecraft.class_8074;
import net.minecraft.class_8790;
import net.minecraft.class_2066.class_2068;
import vectorwing.farmersdelight.common.crafting.DoughRecipe;
import vectorwing.farmersdelight.common.crafting.FoodServingRecipe;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.ConventionalTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class CraftingRecipes {
   public static void register(class_8790 output) {
      recipesVanillaAlternatives(output);
      recipesBlocks(output);
      recipesCanvasSigns(output);
      recipesTools(output);
      recipesMaterials(output);
      recipesFoodstuffs(output);
      recipesFoodBlocks(output);
      recipesCraftedMeals(output);
      class_2456.method_10476(FoodServingRecipe::new).method_53820(output, "farmersdelight:food_serving");
      class_2456.method_10476(DoughRecipe::new).method_53820(output, "farmersdelight:wheat_dough_from_water");
   }

   public static void canvasSignDyeing(class_8790 output, class_1935 canvasSign, class_1935 hangingCanvasSign, class_6862<class_1792> dyeTag) {
      class_2450.method_10448(class_7800.field_40635, canvasSign, 1)
         .method_10446(ModTags.CANVAS_SIGNS)
         .method_10446(dyeTag)
         .method_10442("has_canvas_sign", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS_SIGN.get()}))
         .method_10452("fd_canvas_sign")
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, hangingCanvasSign, 1)
         .method_10446(ModTags.HANGING_CANVAS_SIGNS)
         .method_10446(dyeTag)
         .method_10442("has_hanging_canvas_sign", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.HANGING_CANVAS_SIGN.get()}))
         .method_10452("fd_hanging_canvas_sign")
         .method_10431(output);
   }

   private static void recipesVanillaAlternatives(class_8790 output) {
      class_2450.method_10447(class_7800.field_40642, class_1802.field_46249)
         .method_10454((class_1935)ModItems.PUMPKIN_SLICE.get())
         .method_10442("has_pumpkin_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.PUMPKIN_SLICE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "pumpkin_seeds_from_slice"));
      class_2447.method_10436(class_7800.field_40635, class_1802.field_16482, 6)
         .method_10439("b#b")
         .method_10439("b b")
         .method_10439("b b")
         .method_10434('b', class_1802.field_8648)
         .method_10434('#', (class_1935)ModItems.CANVAS.get())
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "scaffolding_from_canvas"));
      class_2447.method_10437(class_7800.field_40638, class_1802.field_8719)
         .method_10439("ss ")
         .method_10439("ss ")
         .method_10439("  s")
         .method_10434('s', (class_1935)ModItems.STRAW.get())
         .method_10429("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "lead_from_straw"));
      class_2447.method_10437(class_7800.field_40635, class_1802.field_8892)
         .method_10439("sss")
         .method_10439("scs")
         .method_10439("sss")
         .method_10434('s', class_1802.field_8600)
         .method_10434('c', (class_1935)ModItems.CANVAS.get())
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "painting_from_canvas"));
      class_2447.method_10437(class_7800.field_40634, class_1802.field_17518)
         .method_10439("##")
         .method_10439("##")
         .method_10434('#', (class_1935)ModItems.PUMPKIN_SLICE.get())
         .method_10429("has_pumpkin_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.PUMPKIN_SLICE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "pumpkin_from_slices"));
      class_2447.method_10437(class_7800.field_40640, class_1802.field_17534)
         .method_10439("mmm")
         .method_10439("ses")
         .method_10439("www")
         .method_10433('m', ConventionalTags.DRINKS_MILK)
         .method_10434('s', class_1802.field_8479)
         .method_10433('e', ConventionalItemTags.EGGS)
         .method_10434('w', class_1802.field_8861)
         .method_10429("has_milk_bottle", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.MILK_BOTTLE.get()}))
         .method_10435("cake")
         .method_17972(output, class_2960.method_60655("farmersdelight", "cake_from_milk_bottle"));
      class_2450.method_10447(class_7800.field_40640, class_1802.field_17534)
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10454((class_1935)ModItems.CAKE_SLICE.get())
         .method_10442("has_cake_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CAKE_SLICE.get()}))
         .method_10452("cake")
         .method_17972(output, class_2960.method_60655("farmersdelight", "cake_from_slices"));
      class_2450.method_10447(class_7800.field_40642, class_1802.field_8529)
         .method_10454(class_1802.field_8407)
         .method_10454(class_1802.field_8407)
         .method_10454(class_1802.field_8407)
         .method_10454((class_1935)ModItems.CANVAS.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "book_from_canvas"));
      class_2450.method_10447(class_7800.field_40642, class_1802.field_8103)
         .method_10454(class_1802.field_8550)
         .method_10454((class_1935)ModItems.MILK_BOTTLE.get())
         .method_10454((class_1935)ModItems.MILK_BOTTLE.get())
         .method_10454((class_1935)ModItems.MILK_BOTTLE.get())
         .method_10454((class_1935)ModItems.MILK_BOTTLE.get())
         .method_10442("has_milk_bottle", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.MILK_BOTTLE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "milk_bucket_from_bottles"));
      class_2450.method_10447(class_7800.field_40642, class_1802.field_8407)
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10442("has_tree_bark", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TREE_BARK.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "paper_from_tree_bark"));
      class_2450.method_10448(class_7800.field_40634, class_1802.field_37518, 2)
         .method_10454((class_1935)ModItems.STRAW.get())
         .method_10454(class_1802.field_37537)
         .method_10454(class_1802.field_37537)
         .method_10442("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "packed_mud_from_straw"));
   }

   private static void recipesCanvasSigns(class_8790 output) {
      class_2447.method_10436(class_7800.field_40635, (class_1935)ModItems.CANVAS_SIGN.get(), 3)
         .method_10439("w#w")
         .method_10439("w#w")
         .method_10439(" / ")
         .method_10433('w', class_3489.field_15537)
         .method_10434('#', (class_1935)ModItems.CANVAS.get())
         .method_10434('/', class_1802.field_8600)
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40635, (class_1935)ModItems.HANGING_CANVAS_SIGN.get(), 6)
         .method_10439("X X")
         .method_10439("w#w")
         .method_10439("w#w")
         .method_10434('X', class_1802.field_23983)
         .method_10433('w', class_3489.field_15539)
         .method_10434('#', (class_1935)ModItems.CANVAS.get())
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10431(output);
      canvasSignDyeing(
         output, (class_1935)ModItems.WHITE_CANVAS_SIGN.get(), (class_1935)ModItems.WHITE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.WHITE_DYES
      );
      canvasSignDyeing(
         output, (class_1935)ModItems.ORANGE_CANVAS_SIGN.get(), (class_1935)ModItems.ORANGE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.ORANGE_DYES
      );
      canvasSignDyeing(
         output, (class_1935)ModItems.MAGENTA_CANVAS_SIGN.get(), (class_1935)ModItems.MAGENTA_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.MAGENTA_DYES
      );
      canvasSignDyeing(
         output,
         (class_1935)ModItems.LIGHT_BLUE_CANVAS_SIGN.get(),
         (class_1935)ModItems.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(),
         ConventionalItemTags.LIGHT_BLUE_DYES
      );
      canvasSignDyeing(
         output, (class_1935)ModItems.YELLOW_CANVAS_SIGN.get(), (class_1935)ModItems.YELLOW_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.YELLOW_DYES
      );
      canvasSignDyeing(output, (class_1935)ModItems.LIME_CANVAS_SIGN.get(), (class_1935)ModItems.LIME_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.LIME_DYES);
      canvasSignDyeing(output, (class_1935)ModItems.PINK_CANVAS_SIGN.get(), (class_1935)ModItems.PINK_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.PINK_DYES);
      canvasSignDyeing(output, (class_1935)ModItems.GRAY_CANVAS_SIGN.get(), (class_1935)ModItems.GRAY_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.GRAY_DYES);
      canvasSignDyeing(
         output,
         (class_1935)ModItems.LIGHT_GRAY_CANVAS_SIGN.get(),
         (class_1935)ModItems.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(),
         ConventionalItemTags.LIGHT_GRAY_DYES
      );
      canvasSignDyeing(output, (class_1935)ModItems.CYAN_CANVAS_SIGN.get(), (class_1935)ModItems.CYAN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.CYAN_DYES);
      canvasSignDyeing(
         output, (class_1935)ModItems.PURPLE_CANVAS_SIGN.get(), (class_1935)ModItems.PURPLE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.PURPLE_DYES
      );
      canvasSignDyeing(output, (class_1935)ModItems.BLUE_CANVAS_SIGN.get(), (class_1935)ModItems.BLUE_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BLUE_DYES);
      canvasSignDyeing(
         output, (class_1935)ModItems.BROWN_CANVAS_SIGN.get(), (class_1935)ModItems.BROWN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BROWN_DYES
      );
      canvasSignDyeing(
         output, (class_1935)ModItems.GREEN_CANVAS_SIGN.get(), (class_1935)ModItems.GREEN_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.GREEN_DYES
      );
      canvasSignDyeing(output, (class_1935)ModItems.RED_CANVAS_SIGN.get(), (class_1935)ModItems.RED_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.RED_DYES);
      canvasSignDyeing(
         output, (class_1935)ModItems.BLACK_CANVAS_SIGN.get(), (class_1935)ModItems.BLACK_HANGING_CANVAS_SIGN.get(), ConventionalItemTags.BLACK_DYES
      );
   }

   private static void recipesBlocks(class_8790 output) {
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.STOVE.get())
         .method_10439("iii")
         .method_10439("B B")
         .method_10439("BCB")
         .method_10433('i', ConventionalItemTags.IRON_INGOTS)
         .method_10434('B', class_2246.field_10104)
         .method_10434('C', class_2246.field_17350)
         .method_10429("has_campfire", class_2068.method_8959(new class_1935[]{class_2246.field_17350}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.COOKING_POT.get())
         .method_10439("bSb")
         .method_10439("iWi")
         .method_10439("iii")
         .method_10434('b', class_1802.field_8621)
         .method_10433('i', ConventionalItemTags.IRON_INGOTS)
         .method_10434('S', class_1802.field_8876)
         .method_10433('W', ConventionalItemTags.WATER_BUCKETS)
         .method_10429("has_iron_ingot", class_2068.method_8959(new class_1935[]{class_1802.field_8620}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.BASKET.get())
         .method_10439("b b")
         .method_10439("# #")
         .method_10439("b#b")
         .method_10434('b', class_1802.field_8648)
         .method_10434('#', (class_1935)ModItems.CANVAS.get())
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.CUTTING_BOARD.get())
         .method_10439("/##")
         .method_10439("/##")
         .method_10434('/', class_1802.field_8600)
         .method_10433('#', class_3489.field_15537)
         .method_10429("has_stick", class_2068.method_8959(new class_1935[]{class_1802.field_8600}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.SKILLET.get())
         .method_10439(" ##")
         .method_10439(" ##")
         .method_10439("/  ")
         .method_10434('/', class_1802.field_8621)
         .method_10433('#', ConventionalItemTags.IRON_INGOTS)
         .method_10429("has_brick", class_2068.method_8959(new class_1935[]{class_1802.field_8621}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.OAK_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8320)
         .method_10434('D', class_1802.field_8376)
         .method_10429("has_oak_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8376}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.BIRCH_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8843)
         .method_10434('D', class_1802.field_8774)
         .method_10429("has_birch_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8774}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.SPRUCE_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8189)
         .method_10434('D', class_1802.field_8495)
         .method_10429("has_spruce_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8495}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.JUNGLE_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8224)
         .method_10434('D', class_1802.field_8321)
         .method_10429("has_jungle_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8321}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.ACACIA_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8400)
         .method_10434('D', class_1802.field_8190)
         .method_10429("has_acacia_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8190}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.DARK_OAK_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_8540)
         .method_10434('D', class_1802.field_8844)
         .method_10429("has_dark_oak_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_8844}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.MANGROVE_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_37516)
         .method_10434('D', class_1802.field_37529)
         .method_10429("has_mangrove_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_37529}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.CHERRY_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_42697)
         .method_10434('D', class_1802.field_42702)
         .method_10429("has_cherry_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_42702}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.BAMBOO_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_40216)
         .method_10434('D', class_1802.field_40226)
         .method_10429("has_bamboo_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_40226}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.CRIMSON_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_21985)
         .method_10434('D', class_1802.field_22002)
         .method_10429("has_crimson_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_22002}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40635, (class_1935)ModBlocks.WARPED_CABINET.get())
         .method_10439("___")
         .method_10439("D D")
         .method_10439("___")
         .method_10434('_', class_1802.field_21986)
         .method_10434('D', class_1802.field_22003)
         .method_10429("has_warped_trapdoor", class_2068.method_8959(new class_1935[]{class_1802.field_22003}))
         .method_10435("fd_cabinet")
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40635, (class_1935)ModItems.ROPE.get(), 4)
         .method_10439("s")
         .method_10439("s")
         .method_10434('s', (class_1935)ModItems.STRAW.get())
         .method_10429("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_10435("fd_rope")
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40635, (class_1935)ModItems.SAFETY_NET.get(), 1)
         .method_10439("rr")
         .method_10439("rr")
         .method_10434('r', (class_1935)ModItems.ROPE.get())
         .method_10429("has_rope", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.ROPE.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, (class_1935)ModItems.ROPE.get(), 4)
         .method_10454((class_1935)ModItems.SAFETY_NET.get())
         .method_10442("has_safety_net", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.SAFETY_NET.get()}))
         .method_10452("fd_rope")
         .method_17972(output, class_2960.method_60655("farmersdelight", "rope_from_safety_net"));
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.CABBAGE_CRATE.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.CABBAGE.get())
         .method_10429("has_cabbage", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CABBAGE.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.TOMATO_CRATE.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.TOMATO.get())
         .method_10429("has_tomato", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TOMATO.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.ONION_CRATE.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.ONION.get())
         .method_10429("has_onion", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.ONION.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.RICE_BALE.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.RICE_PANICLE.get())
         .method_10429("has_rice_panicle", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE_PANICLE.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.RICE_BAG.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.RICE.get())
         .method_10429("has_rice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.STRAW_BALE.get(), 1)
         .method_10439("###")
         .method_10439("###")
         .method_10439("###")
         .method_10434('#', (class_1935)ModItems.STRAW.get())
         .method_10429("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, (class_1935)ModItems.CANVAS_RUG.get(), 2)
         .method_10454((class_1935)ModItems.CANVAS.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40642, (class_1935)ModItems.CANVAS.get(), 1)
         .method_10454((class_1935)ModItems.CANVAS_RUG.get())
         .method_10454((class_1935)ModItems.CANVAS_RUG.get())
         .method_10442("has_canvas_rug", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS_RUG.get()}))
         .method_10452("fd_canvas")
         .method_17972(output, class_2960.method_60655("farmersdelight", "canvas_from_canvas_rug"));
      class_2450.method_10448(class_7800.field_40634, (class_1935)ModItems.ORGANIC_COMPOST.get(), 1)
         .method_10454(class_1802.field_8831)
         .method_10454(class_1802.field_8511)
         .method_10454(class_1802.field_8511)
         .method_10454((class_1935)ModItems.STRAW.get())
         .method_10454((class_1935)ModItems.STRAW.get())
         .method_10454(class_1802.field_8324)
         .method_10454(class_1802.field_8324)
         .method_10454(class_1802.field_8324)
         .method_10454(class_1802.field_8324)
         .method_10442("has_rotten_flesh", class_2068.method_8959(new class_1935[]{class_1802.field_8511}))
         .method_10442("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_10452("fd_organic_compost")
         .method_17972(output, class_2960.method_60655("farmersdelight", "organic_compost_from_rotten_flesh"));
      class_2450.method_10448(class_7800.field_40634, (class_1935)ModItems.ORGANIC_COMPOST.get(), 1)
         .method_10454(class_1802.field_8831)
         .method_10454((class_1935)ModItems.STRAW.get())
         .method_10454((class_1935)ModItems.STRAW.get())
         .method_10454(class_1802.field_8324)
         .method_10454(class_1802.field_8324)
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10454((class_1935)ModItems.TREE_BARK.get())
         .method_10442("has_tree_bark", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TREE_BARK.get()}))
         .method_10442("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_10452("fd_organic_compost")
         .method_17972(output, class_2960.method_60655("farmersdelight", "organic_compost_from_tree_bark"));
      class_2447.method_10436(class_7800.field_40634, (class_1935)ModItems.TATAMI.get(), 2)
         .method_10439("cs")
         .method_10439("sc")
         .method_10434('c', (class_1935)ModItems.CANVAS.get())
         .method_10434('s', (class_1935)ModItems.STRAW.get())
         .method_10429("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10435("fd_tatami")
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, (class_1935)ModItems.FULL_TATAMI_MAT.get(), 2)
         .method_10454((class_1935)ModItems.TATAMI.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10452("fd_full_tatami_mat")
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, (class_1935)ModItems.HALF_TATAMI_MAT.get(), 2)
         .method_10454((class_1935)ModItems.FULL_TATAMI_MAT.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40635, (class_1935)ModItems.FULL_TATAMI_MAT.get(), 1)
         .method_10454((class_1935)ModItems.HALF_TATAMI_MAT.get())
         .method_10454((class_1935)ModItems.HALF_TATAMI_MAT.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10452("fd_full_tatami_mat")
         .method_17972(output, class_2960.method_60655("farmersdelight", "full_tatami_mat_from_halves"));
      class_2450.method_10448(class_7800.field_40634, (class_1935)ModItems.TATAMI.get(), 1)
         .method_10454((class_1935)ModItems.FULL_TATAMI_MAT.get())
         .method_10454((class_1935)ModItems.FULL_TATAMI_MAT.get())
         .method_10442("has_canvas", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CANVAS.get()}))
         .method_10452("fd_tatami")
         .method_17972(output, class_2960.method_60655("farmersdelight", "tatami_block_from_full"));
   }

   private static void recipesTools(class_8790 output) {
      class_2447.method_10437(class_7800.field_40639, (class_1935)ModItems.FLINT_KNIFE.get())
         .method_10439("m")
         .method_10439("s")
         .method_10434('m', class_1802.field_8145)
         .method_10434('s', class_1802.field_8600)
         .method_10429("has_stick", class_2068.method_8959(new class_1935[]{class_1802.field_8600}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40639, (class_1935)ModItems.IRON_KNIFE.get())
         .method_10439("m")
         .method_10439("s")
         .method_10433('m', ConventionalItemTags.IRON_INGOTS)
         .method_10434('s', class_1802.field_8600)
         .method_10429("has_iron_ingot", class_2068.method_8959(new class_1935[]{class_1802.field_8620}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40639, (class_1935)ModItems.DIAMOND_KNIFE.get())
         .method_10439("m")
         .method_10439("s")
         .method_10434('m', class_1802.field_8477)
         .method_10434('s', class_1802.field_8600)
         .method_10429("has_diamond", class_2068.method_8959(new class_1935[]{class_1802.field_8477}))
         .method_10431(output);
      class_2447.method_10437(class_7800.field_40639, (class_1935)ModItems.GOLDEN_KNIFE.get())
         .method_10439("m")
         .method_10439("s")
         .method_10434('m', class_1802.field_8695)
         .method_10434('s', class_1802.field_8600)
         .method_10429("has_gold_ingot", class_2068.method_8959(new class_1935[]{class_1802.field_8695}))
         .method_10431(output);
      class_8074.method_48535(
            class_1856.method_8091(new class_1935[]{class_1802.field_41946}),
            class_1856.method_8091(new class_1935[]{(class_1935)ModItems.DIAMOND_KNIFE.get()}),
            class_1856.method_8091(new class_1935[]{class_1802.field_22020}),
            class_7800.field_40639,
            ModItems.NETHERITE_KNIFE.get()
         )
         .method_48536("has_netherite_ingot", class_2068.method_8959(new class_1935[]{class_1802.field_22020}))
         .method_48538(output, "farmersdelight:netherite_knife_smithing");
   }

   private static void recipesMaterials(class_8790 output) {
      class_2447.method_10437(class_7800.field_40642, (class_1935)ModItems.CANVAS.get())
         .method_10439("##")
         .method_10439("##")
         .method_10434('#', (class_1935)ModItems.STRAW.get())
         .method_10429("has_straw", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW.get()}))
         .method_10435("fd_canvas")
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, class_1802.field_8179, 9)
         .method_10454((class_1935)ModItems.CARROT_CRATE.get())
         .method_10442("has_carrot_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CARROT_CRATE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "carrot_from_crate"));
      class_2450.method_10448(class_7800.field_40640, class_1802.field_8567, 9)
         .method_10454((class_1935)ModItems.POTATO_CRATE.get())
         .method_10442("has_potato_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.POTATO_CRATE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "potato_from_crate"));
      class_2450.method_10448(class_7800.field_40640, class_1802.field_8186, 9)
         .method_10454((class_1935)ModItems.BEETROOT_CRATE.get())
         .method_10442("has_beetroot_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.BEETROOT_CRATE.get()}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "beetroot_from_crate"));
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.CABBAGE.get(), 9)
         .method_10454((class_1935)ModItems.CABBAGE_CRATE.get())
         .method_10442("has_cabbage_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CABBAGE_CRATE.get()}))
         .method_10452("fd_cabbage")
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.TOMATO.get(), 9)
         .method_10454((class_1935)ModItems.TOMATO_CRATE.get())
         .method_10442("has_tomato_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TOMATO_CRATE.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.ONION.get(), 9)
         .method_10454((class_1935)ModItems.ONION_CRATE.get())
         .method_10442("has_onion_crate", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.ONION_CRATE.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.RICE_PANICLE.get(), 9)
         .method_10454((class_1935)ModItems.RICE_BALE.get())
         .method_10442("has_rice_bale", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE_BALE.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.RICE.get(), 9)
         .method_10454((class_1935)ModItems.RICE_BAG.get())
         .method_10442("has_rice_bag", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE_BAG.get()}))
         .method_10452("fd_rice")
         .method_36443(output, "farmersdelight:rice_from_bag");
      class_2450.method_10448(class_7800.field_40642, (class_1935)ModItems.STRAW.get(), 9)
         .method_10454((class_1935)ModItems.STRAW_BALE.get())
         .method_10442("has_straw_bale", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.STRAW_BALE.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.RICE.get())
         .method_10454((class_1935)ModItems.RICE_PANICLE.get())
         .method_10442("has_rice_panicle", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.RICE_PANICLE.get()}))
         .method_10452("fd_rice")
         .method_10431(output);
   }

   private static void recipesFoodstuffs(class_8790 output) {
      class_2450.method_10447(class_7800.field_40642, (class_1935)ModItems.TOMATO_SEEDS.get())
         .method_10451(class_1856.method_8091(new class_1935[]{(class_1935)ModItems.TOMATO.get(), (class_1935)ModItems.ROTTEN_TOMATO.get()}))
         .method_10442("has_tomato", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TOMATO.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.MILK_BOTTLE.get(), 4)
         .method_10454(class_1802.field_8103)
         .method_10454(class_1802.field_8469)
         .method_10454(class_1802.field_8469)
         .method_10454(class_1802.field_8469)
         .method_10454(class_1802.field_8469)
         .method_10442("has_milk_bucket", class_2068.method_8959(new class_1935[]{class_1802.field_8103}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.MELON_JUICE.get(), 1)
         .method_10454(class_1802.field_8497)
         .method_10454(class_1802.field_8497)
         .method_10454(class_1802.field_8479)
         .method_10454(class_1802.field_8497)
         .method_10454(class_1802.field_8497)
         .method_10454(class_1802.field_8469)
         .method_10442("has_melon_slice", class_2068.method_8959(new class_1935[]{class_1802.field_8497}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.WHEAT_DOUGH.get(), 3)
         .method_10454(class_1802.field_8861)
         .method_10454(class_1802.field_8861)
         .method_10454(class_1802.field_8861)
         .method_10446(ConventionalItemTags.EGGS)
         .method_10452("fd_dough")
         .method_10442("has_wheat", class_2068.method_8959(new class_1935[]{class_1802.field_8861}))
         .method_17972(output, class_2960.method_60655("farmersdelight", "wheat_dough_from_egg"));
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.PIE_CRUST.get(), 1)
         .method_10439("wMw")
         .method_10439(" w ")
         .method_10434('w', class_1802.field_8861)
         .method_10433('M', ConventionalTags.DRINKS_MILK)
         .method_10429("has_wheat", class_2068.method_8959(new class_1935[]{class_1802.field_8861}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.SWEET_BERRY_COOKIE.get(), 8)
         .method_10454(class_1802.field_16998)
         .method_10454(class_1802.field_8861)
         .method_10454(class_1802.field_8861)
         .method_10442("has_sweet_berries", class_2068.method_8959(new class_1935[]{class_1802.field_16998}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.HONEY_COOKIE.get(), 8)
         .method_10454(class_1802.field_20417)
         .method_10454(class_1802.field_8861)
         .method_10454(class_1802.field_8861)
         .method_10442("has_honey_bottle", class_2068.method_8959(new class_1935[]{class_1802.field_20417}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.CABBAGE.get())
         .method_10454((class_1935)ModItems.CABBAGE_LEAF.get())
         .method_10454((class_1935)ModItems.CABBAGE_LEAF.get())
         .method_10442("has_cabbage_leaf", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CABBAGE_LEAF.get()}))
         .method_10452("fd_cabbage")
         .method_17972(output, class_2960.method_60655("farmersdelight", "cabbage_from_leaves"));
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.HORSE_FEED.get(), 1)
         .method_10451(class_1856.method_8091(new class_1935[]{class_1802.field_17528, (class_1935)ModItems.RICE_BALE.get()}))
         .method_10454(class_1802.field_8279)
         .method_10454(class_1802.field_8279)
         .method_10454(class_1802.field_8071)
         .method_10442("has_golden_carrot", class_2068.method_8959(new class_1935[]{class_1802.field_8071}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.MELON_POPSICLE.get(), 1)
         .method_10439(" mm")
         .method_10439("imm")
         .method_10439("-i ")
         .method_10434('m', class_1802.field_8497)
         .method_10434('i', class_1802.field_8426)
         .method_10434('-', class_1802.field_8600)
         .method_10429("has_melon", class_2068.method_8959(new class_1935[]{class_1802.field_8497}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.FRUIT_SALAD.get(), 1)
         .method_10454(class_1802.field_8279)
         .method_10454(class_1802.field_8497)
         .method_10454(class_1802.field_8497)
         .method_10446(ConventionalItemTags.BERRY_FOODS)
         .method_10446(ConventionalItemTags.BERRY_FOODS)
         .method_10454((class_1935)ModItems.PUMPKIN_SLICE.get())
         .method_10454(class_1802.field_8428)
         .method_10442(
            "has_fruits",
            class_2068.method_8959(
               new class_1935[]{class_1802.field_8497, class_1802.field_16998, class_1802.field_8279, (class_1935)ModItems.PUMPKIN_SLICE.get()}
            )
         )
         .method_10431(output);
   }

   private static void recipesFoodBlocks(class_8790 output) {
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.APPLE_PIE.get(), 1)
         .method_10439("###")
         .method_10439("aaa")
         .method_10439("xOx")
         .method_10434('#', class_1802.field_8861)
         .method_10434('a', class_1802.field_8279)
         .method_10434('x', class_1802.field_8479)
         .method_10434('O', (class_1935)ModItems.PIE_CRUST.get())
         .method_10429("has_pie_crust", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.PIE_CRUST.get()}))
         .method_10435("fd_apple_pie")
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.APPLE_PIE.get(), 1)
         .method_10439("##")
         .method_10439("##")
         .method_10434('#', (class_1935)ModItems.APPLE_PIE_SLICE.get())
         .method_10429("has_apple_pie_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.APPLE_PIE_SLICE.get()}))
         .method_10435("fd_apple_pie")
         .method_17972(output, class_2960.method_60655("farmersdelight", "apple_pie_from_slices"));
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.SWEET_BERRY_CHEESECAKE.get(), 1)
         .method_10439("sss")
         .method_10439("sss")
         .method_10439("mOm")
         .method_10434('s', class_1802.field_16998)
         .method_10433('m', ConventionalTags.DRINKS_MILK)
         .method_10434('O', (class_1935)ModItems.PIE_CRUST.get())
         .method_10429("has_pie_crust", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.PIE_CRUST.get()}))
         .method_10435("fd_sweet_berry_cheesecake")
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.SWEET_BERRY_CHEESECAKE.get(), 1)
         .method_10439("##")
         .method_10439("##")
         .method_10434('#', (class_1935)ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
         .method_10429("has_sweet_berry_cheesecake_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get()}))
         .method_10435("fd_sweet_berry_cheesecake")
         .method_17972(output, class_2960.method_60655("farmersdelight", "sweet_berry_cheesecake_from_slices"));
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.CHOCOLATE_PIE.get(), 1)
         .method_10439("ccc")
         .method_10439("mmm")
         .method_10439("xOx")
         .method_10434('c', class_1802.field_8116)
         .method_10433('m', ConventionalTags.DRINKS_MILK)
         .method_10434('x', class_1802.field_8479)
         .method_10434('O', (class_1935)ModItems.PIE_CRUST.get())
         .method_10429("has_pie_crust", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.PIE_CRUST.get()}))
         .method_10435("fd_chocolate_pie")
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.CHOCOLATE_PIE.get(), 1)
         .method_10439("##")
         .method_10439("##")
         .method_10434('#', (class_1935)ModItems.CHOCOLATE_PIE_SLICE.get())
         .method_10429("has_chocolate_pie_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.CHOCOLATE_PIE_SLICE.get()}))
         .method_10435("fd_chocolate_pie")
         .method_17972(output, class_2960.method_60655("farmersdelight", "chocolate_pie_from_slices"));
   }

   private static void recipesCraftedMeals(class_8790 output) {
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.MIXED_SALAD.get())
         .method_10446(CommonTags.FOODS_LEAFY_GREEN)
         .method_10446(CommonTags.CROPS_TOMATO)
         .method_10454(class_1802.field_8186)
         .method_10454(class_1802.field_8428)
         .method_10442("has_bowl", class_2068.method_8959(new class_1935[]{class_1802.field_8428}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.NETHER_SALAD.get())
         .method_10454(class_1802.field_21987)
         .method_10454(class_1802.field_21988)
         .method_10454(class_1802.field_8428)
         .method_10442("has_bowl", class_2068.method_8959(new class_1935[]{class_1802.field_8428}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.BARBECUE_STICK.get())
         .method_10446(CommonTags.CROPS_TOMATO)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10446(ConventionalItemTags.COOKED_MEAT_FOODS)
         .method_10454(class_1802.field_8600)
         .method_10442("has_tomato", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.TOMATO.get()}))
         .method_10442("has_onion", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.ONION.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.EGG_SANDWICH.get())
         .method_10446(ConventionalItemTags.BREAD_FOODS)
         .method_10446(CommonTags.FOODS_COOKED_EGG)
         .method_10446(CommonTags.FOODS_COOKED_EGG)
         .method_10442("has_fried_egg", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.FRIED_EGG.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.CHICKEN_SANDWICH.get())
         .method_10446(ConventionalItemTags.BREAD_FOODS)
         .method_10446(CommonTags.FOODS_COOKED_CHICKEN)
         .method_10446(CommonTags.FOODS_LEAFY_GREEN)
         .method_10454(class_1802.field_8179)
         .method_10442("has_cooked_chicken", class_2068.method_8959(new class_1935[]{class_1802.field_8544}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.HAMBURGER.get())
         .method_10446(ConventionalItemTags.BREAD_FOODS)
         .method_10454((class_1935)ModItems.BEEF_PATTY.get())
         .method_10446(CommonTags.FOODS_LEAFY_GREEN)
         .method_10446(CommonTags.CROPS_TOMATO)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10442("has_beef_patty", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.BEEF_PATTY.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.BACON_SANDWICH.get())
         .method_10446(ConventionalItemTags.BREAD_FOODS)
         .method_10446(CommonTags.FOODS_COOKED_BACON)
         .method_10446(CommonTags.FOODS_LEAFY_GREEN)
         .method_10446(CommonTags.CROPS_TOMATO)
         .method_10442("has_bacon", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.COOKED_BACON.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.MUTTON_WRAP.get())
         .method_10446(ConventionalItemTags.BREAD_FOODS)
         .method_10446(CommonTags.FOODS_COOKED_MUTTON)
         .method_10446(CommonTags.FOODS_LEAFY_GREEN)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10442("has_mutton", class_2068.method_8959(new class_1935[]{class_1802.field_8347}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.STUFFED_POTATO.get())
         .method_10454(class_1802.field_8512)
         .method_10446(CommonTags.FOODS_COOKED_BEEF)
         .method_10446(ConventionalTags.DRINKS_MILK)
         .method_10442("has_baked_potato", class_2068.method_8959(new class_1935[]{class_1802.field_8512}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.SALMON_ROLL.get(), 2)
         .method_10454((class_1935)ModItems.SALMON_SLICE.get())
         .method_10454((class_1935)ModItems.SALMON_SLICE.get())
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10442("has_salmon_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.SALMON_SLICE.get()}))
         .method_10431(output);
      class_2450.method_10448(class_7800.field_40640, (class_1935)ModItems.COD_ROLL.get(), 2)
         .method_10454((class_1935)ModItems.COD_SLICE.get())
         .method_10454((class_1935)ModItems.COD_SLICE.get())
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10442("has_cod_slice", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.COD_SLICE.get()}))
         .method_10431(output);
      class_2447.method_10436(class_7800.field_40640, (class_1935)ModItems.KELP_ROLL.get(), 1)
         .method_10439("RXR")
         .method_10439("###")
         .method_10434('#', class_1802.field_8551)
         .method_10434('R', (class_1935)ModItems.COOKED_RICE.get())
         .method_10434('X', class_1802.field_8179)
         .method_10429("has_dried_kelp", class_2068.method_8959(new class_1935[]{class_1802.field_8551}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.GRILLED_SALMON.get())
         .method_10446(CommonTags.FOODS_COOKED_SALMON)
         .method_10454(class_1802.field_16998)
         .method_10454(class_1802.field_8428)
         .method_10446(CommonTags.CROPS_CABBAGE)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10442("has_salmon", class_2068.method_8959(new class_1935[]{class_1802.field_8209}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.STEAK_AND_POTATOES.get())
         .method_10454(class_1802.field_8512)
         .method_10454(class_1802.field_8176)
         .method_10454(class_1802.field_8428)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10442("has_baked_potato", class_2068.method_8959(new class_1935[]{class_1802.field_8512}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.ROASTED_MUTTON_CHOPS.get())
         .method_10454((class_1935)ModItems.COOKED_MUTTON_CHOPS.get())
         .method_10454(class_1802.field_8186)
         .method_10454(class_1802.field_8428)
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10446(CommonTags.CROPS_TOMATO)
         .method_10442("has_mutton", class_2068.method_8959(new class_1935[]{class_1802.field_8347}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.BACON_AND_EGGS.get())
         .method_10446(CommonTags.FOODS_COOKED_BACON)
         .method_10446(CommonTags.FOODS_COOKED_BACON)
         .method_10454(class_1802.field_8428)
         .method_10446(CommonTags.FOODS_COOKED_EGG)
         .method_10446(CommonTags.FOODS_COOKED_EGG)
         .method_10442("has_bacon", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.COOKED_BACON.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.ROAST_CHICKEN_BLOCK.get())
         .method_10446(CommonTags.CROPS_ONION)
         .method_10446(ConventionalItemTags.EGGS)
         .method_10454(class_1802.field_8229)
         .method_10454(class_1802.field_8179)
         .method_10454(class_1802.field_8544)
         .method_10454(class_1802.field_8512)
         .method_10454(class_1802.field_8179)
         .method_10454(class_1802.field_8428)
         .method_10454(class_1802.field_8512)
         .method_10442("has_cooked_chicken", class_2068.method_8959(new class_1935[]{class_1802.field_8544}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.SHEPHERDS_PIE_BLOCK.get())
         .method_10454(class_1802.field_8512)
         .method_10446(ConventionalTags.DRINKS_MILK)
         .method_10454(class_1802.field_8512)
         .method_10446(CommonTags.FOODS_COOKED_MUTTON)
         .method_10446(CommonTags.FOODS_COOKED_MUTTON)
         .method_10446(CommonTags.FOODS_COOKED_MUTTON)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10454(class_1802.field_8428)
         .method_10446(CommonTags.CROPS_ONION)
         .method_10442("has_cooked_mutton", class_2068.method_8959(new class_1935[]{class_1802.field_8347}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.HONEY_GLAZED_HAM_BLOCK.get())
         .method_10454(class_1802.field_16998)
         .method_10454(class_1802.field_20417)
         .method_10454(class_1802.field_16998)
         .method_10454(class_1802.field_16998)
         .method_10454((class_1935)ModItems.SMOKED_HAM.get())
         .method_10454(class_1802.field_16998)
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10454(class_1802.field_8428)
         .method_10454((class_1935)ModItems.COOKED_RICE.get())
         .method_10442("has_smoked_ham", class_2068.method_8959(new class_1935[]{(class_1935)ModItems.SMOKED_HAM.get()}))
         .method_10431(output);
      class_2450.method_10447(class_7800.field_40640, (class_1935)ModItems.RICE_ROLL_MEDLEY_BLOCK.get())
         .method_10454((class_1935)ModItems.KELP_ROLL_SLICE.get())
         .method_10454((class_1935)ModItems.KELP_ROLL_SLICE.get())
         .method_10454((class_1935)ModItems.KELP_ROLL_SLICE.get())
         .method_10454((class_1935)ModItems.SALMON_ROLL.get())
         .method_10454((class_1935)ModItems.SALMON_ROLL.get())
         .method_10454((class_1935)ModItems.SALMON_ROLL.get())
         .method_10454((class_1935)ModItems.COD_ROLL.get())
         .method_10454(class_1802.field_8428)
         .method_10454((class_1935)ModItems.COD_ROLL.get())
         .method_10442(
            "has_rice_roll",
            class_2068.method_8959(
               new class_1935[]{(class_1935)ModItems.SALMON_ROLL.get(), (class_1935)ModItems.COD_ROLL.get(), (class_1935)ModItems.KELP_ROLL_SLICE.get()}
            )
         )
         .method_10431(output);
   }
}
