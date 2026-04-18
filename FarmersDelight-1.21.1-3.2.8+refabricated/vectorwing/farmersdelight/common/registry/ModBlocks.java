package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import net.minecraft.class_1294;
import net.minecraft.class_1767;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2498;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_3619;
import net.minecraft.class_3620;
import net.minecraft.class_4970.class_2251;
import vectorwing.farmersdelight.common.block.BasketBlock;
import vectorwing.farmersdelight.common.block.BuddingTomatoBlock;
import vectorwing.farmersdelight.common.block.CabbageBlock;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.CanvasRugBlock;
import vectorwing.farmersdelight.common.block.CeilingHangingCanvasSignBlock;
import vectorwing.farmersdelight.common.block.CookingPotBlock;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.HoneyGlazedHamBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.block.OnionBlock;
import vectorwing.farmersdelight.common.block.OrganicCompostBlock;
import vectorwing.farmersdelight.common.block.PieBlock;
import vectorwing.farmersdelight.common.block.RiceBaleBlock;
import vectorwing.farmersdelight.common.block.RiceBlock;
import vectorwing.farmersdelight.common.block.RicePaniclesBlock;
import vectorwing.farmersdelight.common.block.RiceRollMedleyBlock;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.RichSoilFarmlandBlock;
import vectorwing.farmersdelight.common.block.RoastChickenBlock;
import vectorwing.farmersdelight.common.block.RopeBlock;
import vectorwing.farmersdelight.common.block.SafetyNetBlock;
import vectorwing.farmersdelight.common.block.SandyShrubBlock;
import vectorwing.farmersdelight.common.block.ShepherdsPieBlock;
import vectorwing.farmersdelight.common.block.SkilletBlock;
import vectorwing.farmersdelight.common.block.StandingCanvasSignBlock;
import vectorwing.farmersdelight.common.block.StoveBlock;
import vectorwing.farmersdelight.common.block.StrawBaleBlock;
import vectorwing.farmersdelight.common.block.TatamiBlock;
import vectorwing.farmersdelight.common.block.TatamiHalfMatBlock;
import vectorwing.farmersdelight.common.block.TatamiMatBlock;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.block.WallCanvasSignBlock;
import vectorwing.farmersdelight.common.block.WallHangingCanvasSignBlock;
import vectorwing.farmersdelight.common.block.WildCropBlock;
import vectorwing.farmersdelight.common.block.WildRiceBlock;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModBlocks {
   public static final Supplier<class_2248> STOVE = RegUtils.regBlock(
      "stove", () -> new StoveBlock(class_2251.method_9630(class_2246.field_10104).method_9631(litBlockEmission(13)))
   );
   public static final Supplier<class_2248> COOKING_POT = RegUtils.regBlock(
      "cooking_pot",
      () -> new CookingPotBlock(class_2251.method_9637().method_31710(class_3620.field_16005).method_9629(0.5F, 6.0F).method_9626(class_2498.field_17734))
   );
   public static final Supplier<class_2248> SKILLET = RegUtils.regBlock(
      "skillet",
      () -> new SkilletBlock(class_2251.method_9637().method_31710(class_3620.field_16005).method_9629(0.5F, 6.0F).method_9626(class_2498.field_17734))
   );
   public static final Supplier<class_2248> BASKET = RegUtils.regBlock(
      "basket", () -> new BasketBlock(class_2251.method_9637().method_9632(1.5F).method_9626(class_2498.field_40314))
   );
   public static final Supplier<class_2248> CUTTING_BOARD = RegUtils.regBlock(
      "cutting_board", () -> new CuttingBoardBlock(class_2251.method_9630(class_2246.field_10161).method_9632(2.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> CARROT_CRATE = RegUtils.regBlock(
      "carrot_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> POTATO_CRATE = RegUtils.regBlock(
      "potato_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> BEETROOT_CRATE = RegUtils.regBlock(
      "beetroot_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> CABBAGE_CRATE = RegUtils.regBlock(
      "cabbage_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> TOMATO_CRATE = RegUtils.regBlock(
      "tomato_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> ONION_CRATE = RegUtils.regBlock(
      "onion_crate", () -> new class_2248(class_2251.method_9630(class_2246.field_10161).method_9629(2.0F, 3.0F).method_9626(class_2498.field_11547))
   );
   public static final Supplier<class_2248> RICE_BALE = RegUtils.regBlock("rice_bale", () -> new RiceBaleBlock(class_2251.method_9630(class_2246.field_10359)));
   public static final Supplier<class_2248> RICE_BAG = RegUtils.regBlock("rice_bag", () -> new class_2248(class_2251.method_9630(class_2246.field_10446)));
   public static final Supplier<class_2248> STRAW_BALE = RegUtils.regBlock(
      "straw_bale", () -> new StrawBaleBlock(class_2251.method_9630(class_2246.field_10359))
   );
   public static final Supplier<class_2248> ROPE = RegUtils.regBlock(
      "rope",
      () -> new RopeBlock(class_2251.method_9630(class_2246.field_10473).method_9634().method_22488().method_9632(0.2F).method_9626(class_2498.field_11543))
   );
   public static final Supplier<class_2248> SAFETY_NET = RegUtils.regBlock(
      "safety_net", () -> new SafetyNetBlock(class_2251.method_9630(class_2246.field_10473).method_9632(0.2F).method_9626(class_2498.field_11543))
   );
   public static final Supplier<class_2248> OAK_CABINET = RegUtils.regBlock(
      "oak_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> SPRUCE_CABINET = RegUtils.regBlock(
      "spruce_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> BIRCH_CABINET = RegUtils.regBlock(
      "birch_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> JUNGLE_CABINET = RegUtils.regBlock(
      "jungle_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> ACACIA_CABINET = RegUtils.regBlock(
      "acacia_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> DARK_OAK_CABINET = RegUtils.regBlock(
      "dark_oak_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> MANGROVE_CABINET = RegUtils.regBlock(
      "mangrove_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328))
   );
   public static final Supplier<class_2248> CHERRY_CABINET = RegUtils.regBlock(
      "cherry_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328).method_9626(class_2498.field_42766))
   );
   public static final Supplier<class_2248> BAMBOO_CABINET = RegUtils.regBlock(
      "bamboo_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328).method_9626(class_2498.field_40314))
   );
   public static final Supplier<class_2248> CRIMSON_CABINET = RegUtils.regBlock(
      "crimson_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328).method_9626(class_2498.field_40315))
   );
   public static final Supplier<class_2248> WARPED_CABINET = RegUtils.regBlock(
      "warped_cabinet", () -> new CabinetBlock(class_2251.method_9630(class_2246.field_16328).method_9626(class_2498.field_40315))
   );
   public static final Supplier<class_2248> CANVAS_RUG = RegUtils.regBlock(
      "canvas_rug", () -> new CanvasRugBlock(class_2251.method_9630(class_2246.field_10466).method_9626(class_2498.field_11535).method_9632(0.2F))
   );
   public static final Supplier<class_2248> TATAMI = RegUtils.regBlock("tatami", () -> new TatamiBlock(class_2251.method_9630(class_2246.field_10446)));
   public static final Supplier<class_2248> FULL_TATAMI_MAT = RegUtils.regBlock(
      "full_tatami_mat", () -> new TatamiMatBlock(class_2251.method_9630(class_2246.field_10446).method_9632(0.3F))
   );
   public static final Supplier<class_2248> HALF_TATAMI_MAT = RegUtils.regBlock(
      "half_tatami_mat", () -> new TatamiHalfMatBlock(class_2251.method_9630(class_2246.field_10446).method_9632(0.3F).method_50012(class_3619.field_15971))
   );
   public static final Supplier<class_2248> CANVAS_SIGN = RegUtils.regBlock("canvas_sign", () -> new StandingCanvasSignBlock(null));
   public static final Supplier<class_2248> WHITE_CANVAS_SIGN = RegUtils.regBlock("white_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7952));
   public static final Supplier<class_2248> ORANGE_CANVAS_SIGN = RegUtils.regBlock(
      "orange_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7946)
   );
   public static final Supplier<class_2248> MAGENTA_CANVAS_SIGN = RegUtils.regBlock(
      "magenta_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7958)
   );
   public static final Supplier<class_2248> LIGHT_BLUE_CANVAS_SIGN = RegUtils.regBlock(
      "light_blue_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7951)
   );
   public static final Supplier<class_2248> YELLOW_CANVAS_SIGN = RegUtils.regBlock(
      "yellow_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7947)
   );
   public static final Supplier<class_2248> LIME_CANVAS_SIGN = RegUtils.regBlock("lime_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7961));
   public static final Supplier<class_2248> PINK_CANVAS_SIGN = RegUtils.regBlock("pink_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7954));
   public static final Supplier<class_2248> GRAY_CANVAS_SIGN = RegUtils.regBlock("gray_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7944));
   public static final Supplier<class_2248> LIGHT_GRAY_CANVAS_SIGN = RegUtils.regBlock(
      "light_gray_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7967)
   );
   public static final Supplier<class_2248> CYAN_CANVAS_SIGN = RegUtils.regBlock("cyan_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7955));
   public static final Supplier<class_2248> PURPLE_CANVAS_SIGN = RegUtils.regBlock(
      "purple_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7945)
   );
   public static final Supplier<class_2248> BLUE_CANVAS_SIGN = RegUtils.regBlock("blue_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7966));
   public static final Supplier<class_2248> BROWN_CANVAS_SIGN = RegUtils.regBlock("brown_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7957));
   public static final Supplier<class_2248> GREEN_CANVAS_SIGN = RegUtils.regBlock("green_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7942));
   public static final Supplier<class_2248> RED_CANVAS_SIGN = RegUtils.regBlock("red_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7964));
   public static final Supplier<class_2248> BLACK_CANVAS_SIGN = RegUtils.regBlock("black_canvas_sign", () -> new StandingCanvasSignBlock(class_1767.field_7963));
   public static final Supplier<class_2248> CANVAS_WALL_SIGN = RegUtils.regBlock(
      "canvas_wall_sign", () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(CANVAS_SIGN.get()), null)
   );
   public static final Supplier<class_2248> WHITE_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "white_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(WHITE_CANVAS_SIGN.get()), class_1767.field_7952)
   );
   public static final Supplier<class_2248> ORANGE_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "orange_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(ORANGE_CANVAS_SIGN.get()), class_1767.field_7946)
   );
   public static final Supplier<class_2248> MAGENTA_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "magenta_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(MAGENTA_CANVAS_SIGN.get()), class_1767.field_7958)
   );
   public static final Supplier<class_2248> LIGHT_BLUE_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "light_blue_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(LIGHT_BLUE_CANVAS_SIGN.get()), class_1767.field_7951)
   );
   public static final Supplier<class_2248> YELLOW_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "yellow_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(YELLOW_CANVAS_SIGN.get()), class_1767.field_7947)
   );
   public static final Supplier<class_2248> LIME_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "lime_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(LIME_CANVAS_SIGN.get()), class_1767.field_7961)
   );
   public static final Supplier<class_2248> PINK_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "pink_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(PINK_CANVAS_SIGN.get()), class_1767.field_7954)
   );
   public static final Supplier<class_2248> GRAY_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "gray_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(GRAY_CANVAS_SIGN.get()), class_1767.field_7944)
   );
   public static final Supplier<class_2248> LIGHT_GRAY_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "light_gray_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(LIGHT_GRAY_CANVAS_SIGN.get()), class_1767.field_7967)
   );
   public static final Supplier<class_2248> CYAN_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "cyan_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(CYAN_CANVAS_SIGN.get()), class_1767.field_7955)
   );
   public static final Supplier<class_2248> PURPLE_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "purple_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(PURPLE_CANVAS_SIGN.get()), class_1767.field_7945)
   );
   public static final Supplier<class_2248> BLUE_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "blue_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(BLUE_CANVAS_SIGN.get()), class_1767.field_7966)
   );
   public static final Supplier<class_2248> BROWN_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "brown_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(BROWN_CANVAS_SIGN.get()), class_1767.field_7957)
   );
   public static final Supplier<class_2248> GREEN_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "green_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(GREEN_CANVAS_SIGN.get()), class_1767.field_7942)
   );
   public static final Supplier<class_2248> RED_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "red_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(RED_CANVAS_SIGN.get()), class_1767.field_7964)
   );
   public static final Supplier<class_2248> BLACK_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "black_canvas_wall_sign",
      () -> new WallCanvasSignBlock(class_2251.method_9630(class_2246.field_10411).method_16228(BLACK_CANVAS_SIGN.get()), class_1767.field_7963)
   );
   public static final Supplier<class_2248> HANGING_CANVAS_SIGN = RegUtils.regBlock("hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(null));
   public static final Supplier<class_2248> WHITE_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "white_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7952)
   );
   public static final Supplier<class_2248> ORANGE_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "orange_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7946)
   );
   public static final Supplier<class_2248> MAGENTA_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "magenta_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7958)
   );
   public static final Supplier<class_2248> LIGHT_BLUE_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "light_blue_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7951)
   );
   public static final Supplier<class_2248> YELLOW_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "yellow_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7947)
   );
   public static final Supplier<class_2248> LIME_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "lime_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7961)
   );
   public static final Supplier<class_2248> PINK_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "pink_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7954)
   );
   public static final Supplier<class_2248> GRAY_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "gray_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7944)
   );
   public static final Supplier<class_2248> LIGHT_GRAY_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "light_gray_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7967)
   );
   public static final Supplier<class_2248> CYAN_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "cyan_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7955)
   );
   public static final Supplier<class_2248> PURPLE_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "purple_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7945)
   );
   public static final Supplier<class_2248> BLUE_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "blue_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7966)
   );
   public static final Supplier<class_2248> BROWN_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "brown_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7957)
   );
   public static final Supplier<class_2248> GREEN_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "green_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7942)
   );
   public static final Supplier<class_2248> RED_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "red_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7964)
   );
   public static final Supplier<class_2248> BLACK_HANGING_CANVAS_SIGN = RegUtils.regBlock(
      "black_hanging_canvas_sign", () -> new CeilingHangingCanvasSignBlock(class_1767.field_7963)
   );
   public static final Supplier<class_2248> HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(HANGING_CANVAS_SIGN.get()), null)
   );
   public static final Supplier<class_2248> WHITE_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "white_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(WHITE_HANGING_CANVAS_SIGN.get()), class_1767.field_7952)
   );
   public static final Supplier<class_2248> ORANGE_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "orange_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(ORANGE_HANGING_CANVAS_SIGN.get()), class_1767.field_7946)
   );
   public static final Supplier<class_2248> MAGENTA_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "magenta_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(
         class_2251.method_9630(class_2246.field_40273).method_16228(MAGENTA_HANGING_CANVAS_SIGN.get()), class_1767.field_7958
      )
   );
   public static final Supplier<class_2248> LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "light_blue_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(
         class_2251.method_9630(class_2246.field_40273).method_16228(LIGHT_BLUE_HANGING_CANVAS_SIGN.get()), class_1767.field_7951
      )
   );
   public static final Supplier<class_2248> YELLOW_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "yellow_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(YELLOW_HANGING_CANVAS_SIGN.get()), class_1767.field_7947)
   );
   public static final Supplier<class_2248> LIME_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "lime_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(LIME_HANGING_CANVAS_SIGN.get()), class_1767.field_7961)
   );
   public static final Supplier<class_2248> PINK_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "pink_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(PINK_HANGING_CANVAS_SIGN.get()), class_1767.field_7954)
   );
   public static final Supplier<class_2248> GRAY_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "gray_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(GRAY_HANGING_CANVAS_SIGN.get()), class_1767.field_7944)
   );
   public static final Supplier<class_2248> LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "light_gray_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(
         class_2251.method_9630(class_2246.field_40273).method_16228(LIGHT_GRAY_HANGING_CANVAS_SIGN.get()), class_1767.field_7967
      )
   );
   public static final Supplier<class_2248> CYAN_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "cyan_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(CYAN_HANGING_CANVAS_SIGN.get()), class_1767.field_7955)
   );
   public static final Supplier<class_2248> PURPLE_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "purple_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(PURPLE_HANGING_CANVAS_SIGN.get()), class_1767.field_7945)
   );
   public static final Supplier<class_2248> BLUE_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "blue_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(BLUE_HANGING_CANVAS_SIGN.get()), class_1767.field_7966)
   );
   public static final Supplier<class_2248> BROWN_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "brown_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(BROWN_HANGING_CANVAS_SIGN.get()), class_1767.field_7957)
   );
   public static final Supplier<class_2248> GREEN_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "green_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(GREEN_HANGING_CANVAS_SIGN.get()), class_1767.field_7942)
   );
   public static final Supplier<class_2248> RED_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "red_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(RED_HANGING_CANVAS_SIGN.get()), class_1767.field_7964)
   );
   public static final Supplier<class_2248> BLACK_HANGING_CANVAS_WALL_SIGN = RegUtils.regBlock(
      "black_wall_hanging_canvas_sign",
      () -> new WallHangingCanvasSignBlock(class_2251.method_9630(class_2246.field_40273).method_16228(BLACK_HANGING_CANVAS_SIGN.get()), class_1767.field_7963)
   );
   public static final Supplier<class_2248> BROWN_MUSHROOM_COLONY = RegUtils.regBlock(
      "brown_mushroom_colony", () -> new MushroomColonyBlock(class_1802.field_17516.method_40131(), class_2251.method_9630(class_2246.field_10251))
   );
   public static final Supplier<class_2248> RED_MUSHROOM_COLONY = RegUtils.regBlock(
      "red_mushroom_colony", () -> new MushroomColonyBlock(class_1802.field_17517.method_40131(), class_2251.method_9630(class_2246.field_10559))
   );
   public static final Supplier<class_2248> ORGANIC_COMPOST = RegUtils.regBlock(
      "organic_compost", () -> new OrganicCompostBlock(class_2251.method_9630(class_2246.field_10566).method_9632(1.2F).method_9626(class_2498.field_17580))
   );
   public static final Supplier<class_2248> RICH_SOIL = RegUtils.regBlock(
      "rich_soil", () -> new RichSoilBlock(class_2251.method_9630(class_2246.field_10566).method_9640())
   );
   public static final Supplier<class_2248> RICH_SOIL_FARMLAND = RegUtils.regBlock(
      "rich_soil_farmland", () -> new RichSoilFarmlandBlock(class_2251.method_9630(class_2246.field_10362))
   );
   public static final Supplier<class_2248> APPLE_PIE = RegUtils.regBlock(
      "apple_pie", () -> new PieBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.APPLE_PIE_SLICE.get())
   );
   public static final Supplier<class_2248> SWEET_BERRY_CHEESECAKE = RegUtils.regBlock(
      "sweet_berry_cheesecake", () -> new PieBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
   );
   public static final Supplier<class_2248> CHOCOLATE_PIE = RegUtils.regBlock(
      "chocolate_pie", () -> new PieBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.CHOCOLATE_PIE_SLICE.get())
   );
   public static final Supplier<class_2248> SANDY_SHRUB = RegUtils.regBlock(
      "sandy_shrub", () -> new SandyShrubBlock(class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_CABBAGES = RegUtils.regBlock(
      "wild_cabbages", () -> new WildCropBlock(class_1294.field_5910, 6, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_ONIONS = RegUtils.regBlock(
      "wild_onions", () -> new WildCropBlock(class_1294.field_5918, 6, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_TOMATOES = RegUtils.regBlock(
      "wild_tomatoes", () -> new WildCropBlock(class_1294.field_5899, 10, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_CARROTS = RegUtils.regBlock(
      "wild_carrots", () -> new WildCropBlock(class_1294.field_5901, 6, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_POTATOES = RegUtils.regBlock(
      "wild_potatoes", () -> new WildCropBlock(class_1294.field_5916, 8, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_BEETROOTS = RegUtils.regBlock(
      "wild_beetroots", () -> new WildCropBlock(class_1294.field_5923, 8, class_2251.method_9630(class_2246.field_10214))
   );
   public static final Supplier<class_2248> WILD_RICE = RegUtils.regBlock("wild_rice", () -> new WildRiceBlock(class_2251.method_9630(class_2246.field_10214)));
   public static final Supplier<class_2248> CABBAGE_CROP = RegUtils.regBlock("cabbages", () -> new CabbageBlock(class_2251.method_9630(class_2246.field_10293)));
   public static final Supplier<class_2248> ONION_CROP = RegUtils.regBlock("onions", () -> new OnionBlock(class_2251.method_9630(class_2246.field_10293)));
   public static final Supplier<class_2248> BUDDING_TOMATO_CROP = RegUtils.regBlock(
      "budding_tomatoes", () -> new BuddingTomatoBlock(class_2251.method_9630(class_2246.field_10293))
   );
   public static final Supplier<class_2248> TOMATO_CROP = RegUtils.regBlock(
      "tomatoes", () -> new TomatoVineBlock(class_2251.method_9630(class_2246.field_10293))
   );
   public static final Supplier<class_2248> RICE_CROP = RegUtils.regBlock(
      "rice", () -> new RiceBlock(class_2251.method_9630(class_2246.field_10293).method_9632(0.2F))
   );
   public static final Supplier<class_2248> RICE_CROP_PANICLES = RegUtils.regBlock(
      "rice_panicles", () -> new RicePaniclesBlock(class_2251.method_9630(class_2246.field_10293))
   );
   public static final Supplier<class_2248> ROAST_CHICKEN_BLOCK = RegUtils.regBlock(
      "roast_chicken_block", () -> new RoastChickenBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.ROAST_CHICKEN.get(), true)
   );
   public static final Supplier<class_2248> STUFFED_PUMPKIN_BLOCK = RegUtils.regBlock(
      "stuffed_pumpkin_block", () -> new FeastBlock(class_2251.method_9630(class_2246.field_46282), () -> ModItems.STUFFED_PUMPKIN.get(), false)
   );
   public static final Supplier<class_2248> HONEY_GLAZED_HAM_BLOCK = RegUtils.regBlock(
      "honey_glazed_ham_block", () -> new HoneyGlazedHamBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.HONEY_GLAZED_HAM.get(), true)
   );
   public static final Supplier<class_2248> SHEPHERDS_PIE_BLOCK = RegUtils.regBlock(
      "shepherds_pie_block", () -> new ShepherdsPieBlock(class_2251.method_9630(class_2246.field_10183), () -> ModItems.SHEPHERDS_PIE.get(), true)
   );
   public static final Supplier<class_2248> RICE_ROLL_MEDLEY_BLOCK = RegUtils.regBlock(
      "rice_roll_medley_block", () -> new RiceRollMedleyBlock(class_2251.method_9630(class_2246.field_10183))
   );

   private static ToIntFunction<class_2680> litBlockEmission(int lightValue) {
      return state -> state.method_11654(class_2741.field_12548) ? lightValue : 0;
   }

   public static void touch() {
   }
}
