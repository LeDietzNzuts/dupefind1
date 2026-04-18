package vectorwing.farmersdelight.common.registry;

import java.util.function.Supplier;
import net.minecraft.class_2248;
import net.minecraft.class_2591;
import net.minecraft.class_2591.class_2592;
import vectorwing.farmersdelight.common.block.entity.BasketBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CabinetBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CanvasSignBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.block.entity.HangingCanvasSignBlockEntity;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;
import vectorwing.farmersdelight.common.block.entity.StoveBlockEntity;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModBlockEntityTypes {
   public static final Supplier<class_2591<StoveBlockEntity>> STOVE = RegUtils.regBlockEntity(
      "stove", () -> class_2592.method_20528(StoveBlockEntity::new, new class_2248[]{ModBlocks.STOVE.get()}).build()
   );
   public static final Supplier<class_2591<CookingPotBlockEntity>> COOKING_POT = RegUtils.regBlockEntity(
      "cooking_pot", () -> class_2592.method_20528(CookingPotBlockEntity::new, new class_2248[]{ModBlocks.COOKING_POT.get()}).build()
   );
   public static final Supplier<class_2591<BasketBlockEntity>> BASKET = RegUtils.regBlockEntity(
      "basket", () -> class_2592.method_20528(BasketBlockEntity::new, new class_2248[]{ModBlocks.BASKET.get()}).build()
   );
   public static final Supplier<class_2591<CuttingBoardBlockEntity>> CUTTING_BOARD = RegUtils.regBlockEntity(
      "cutting_board", () -> class_2592.method_20528(CuttingBoardBlockEntity::new, new class_2248[]{ModBlocks.CUTTING_BOARD.get()}).build()
   );
   public static final Supplier<class_2591<SkilletBlockEntity>> SKILLET = RegUtils.regBlockEntity(
      "skillet", () -> class_2592.method_20528(SkilletBlockEntity::new, new class_2248[]{ModBlocks.SKILLET.get()}).build()
   );
   public static final Supplier<class_2591<CabinetBlockEntity>> CABINET = RegUtils.regBlockEntity(
      "cabinet",
      () -> class_2592.method_20528(
            CabinetBlockEntity::new,
            new class_2248[]{
               ModBlocks.OAK_CABINET.get(),
               ModBlocks.BIRCH_CABINET.get(),
               ModBlocks.SPRUCE_CABINET.get(),
               ModBlocks.JUNGLE_CABINET.get(),
               ModBlocks.ACACIA_CABINET.get(),
               ModBlocks.DARK_OAK_CABINET.get(),
               ModBlocks.MANGROVE_CABINET.get(),
               ModBlocks.BAMBOO_CABINET.get(),
               ModBlocks.CHERRY_CABINET.get(),
               ModBlocks.CRIMSON_CABINET.get(),
               ModBlocks.WARPED_CABINET.get()
            }
         )
         .build()
   );
   public static final Supplier<class_2591<CanvasSignBlockEntity>> CANVAS_SIGN = RegUtils.regBlockEntity(
      "canvas_sign",
      () -> class_2592.method_20528(
            CanvasSignBlockEntity::new,
            new class_2248[]{
               ModBlocks.CANVAS_SIGN.get(),
               ModBlocks.WHITE_CANVAS_SIGN.get(),
               ModBlocks.ORANGE_CANVAS_SIGN.get(),
               ModBlocks.MAGENTA_CANVAS_SIGN.get(),
               ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(),
               ModBlocks.YELLOW_CANVAS_SIGN.get(),
               ModBlocks.LIME_CANVAS_SIGN.get(),
               ModBlocks.PINK_CANVAS_SIGN.get(),
               ModBlocks.GRAY_CANVAS_SIGN.get(),
               ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(),
               ModBlocks.CYAN_CANVAS_SIGN.get(),
               ModBlocks.PURPLE_CANVAS_SIGN.get(),
               ModBlocks.BLUE_CANVAS_SIGN.get(),
               ModBlocks.BROWN_CANVAS_SIGN.get(),
               ModBlocks.GREEN_CANVAS_SIGN.get(),
               ModBlocks.RED_CANVAS_SIGN.get(),
               ModBlocks.BLACK_CANVAS_SIGN.get(),
               ModBlocks.CANVAS_WALL_SIGN.get(),
               ModBlocks.WHITE_CANVAS_WALL_SIGN.get(),
               ModBlocks.ORANGE_CANVAS_WALL_SIGN.get(),
               ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get(),
               ModBlocks.YELLOW_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIME_CANVAS_WALL_SIGN.get(),
               ModBlocks.PINK_CANVAS_WALL_SIGN.get(),
               ModBlocks.GRAY_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get(),
               ModBlocks.CYAN_CANVAS_WALL_SIGN.get(),
               ModBlocks.PURPLE_CANVAS_WALL_SIGN.get(),
               ModBlocks.BLUE_CANVAS_WALL_SIGN.get(),
               ModBlocks.BROWN_CANVAS_WALL_SIGN.get(),
               ModBlocks.GREEN_CANVAS_WALL_SIGN.get(),
               ModBlocks.RED_CANVAS_WALL_SIGN.get(),
               ModBlocks.BLACK_CANVAS_WALL_SIGN.get()
            }
         )
         .build()
   );
   public static final Supplier<class_2591<HangingCanvasSignBlockEntity>> HANGING_CANVAS_SIGN = RegUtils.regBlockEntity(
      "hanging_canvas_sign",
      () -> class_2592.method_20528(
            HangingCanvasSignBlockEntity::new,
            new class_2248[]{
               ModBlocks.HANGING_CANVAS_SIGN.get(),
               ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(),
               ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(),
               ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(),
               ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(),
               ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(),
               ModBlocks.LIME_HANGING_CANVAS_SIGN.get(),
               ModBlocks.PINK_HANGING_CANVAS_SIGN.get(),
               ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(),
               ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(),
               ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(),
               ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(),
               ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(),
               ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(),
               ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(),
               ModBlocks.RED_HANGING_CANVAS_SIGN.get(),
               ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(),
               ModBlocks.HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get(),
               ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get()
            }
         )
         .build()
   );

   public static void touch() {
   }
}
