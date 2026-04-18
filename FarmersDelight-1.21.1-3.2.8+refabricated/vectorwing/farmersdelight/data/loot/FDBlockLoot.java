package vectorwing.farmersdelight.data.loot;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.class_1935;
import net.minecraft.class_2248;
import net.minecraft.class_44;
import net.minecraft.class_52;
import net.minecraft.class_55;
import net.minecraft.class_77;
import net.minecraft.class_9317;
import net.minecraft.class_9334;
import net.minecraft.class_55.class_56;
import net.minecraft.class_7225.class_7874;
import net.minecraft.class_9317.class_9319;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.registry.ModDataComponents;

public class FDBlockLoot extends FabricBlockLootTableProvider {
   public FDBlockLoot(FabricDataOutput dataOutput, CompletableFuture<class_7874> registryLookup) {
      super(dataOutput, registryLookup);
   }

   public void method_10379() {
      this.method_46025(ModBlocks.STOVE.get());
      this.dropNamedContainer(ModBlocks.BASKET.get());
      this.method_45994(
         ModBlocks.COOKING_POT.get(),
         block -> class_52.method_324()
            .method_336(
               (class_56)this.method_45978(
                  block,
                  class_55.method_347()
                     .method_352(class_44.method_32448(1.0F))
                     .method_351(
                        class_77.method_411(block)
                           .method_438(
                              class_9317.method_57637(class_9319.field_49436)
                                 .method_58730(class_9334.field_49631)
                                 .method_58730(ModDataComponents.MEAL.get())
                                 .method_58730(ModDataComponents.CONTAINER.get())
                           )
                     )
               )
            )
      );
      this.method_46025(ModBlocks.CUTTING_BOARD.get());
      this.method_46025(ModBlocks.CARROT_CRATE.get());
      this.method_46025(ModBlocks.POTATO_CRATE.get());
      this.method_46025(ModBlocks.BEETROOT_CRATE.get());
      this.method_46025(ModBlocks.CABBAGE_CRATE.get());
      this.method_46025(ModBlocks.TOMATO_CRATE.get());
      this.method_46025(ModBlocks.ONION_CRATE.get());
      this.method_46025(ModBlocks.RICE_BALE.get());
      this.method_46025(ModBlocks.RICE_BAG.get());
      this.method_46025(ModBlocks.STRAW_BALE.get());
      this.method_46025(ModBlocks.ROPE.get());
      this.method_46025(ModBlocks.SAFETY_NET.get());
      this.method_46025(ModBlocks.HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.WHITE_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.LIME_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.PINK_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.GRAY_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.CYAN_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.BLUE_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.BROWN_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.GREEN_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.RED_HANGING_CANVAS_SIGN.get());
      this.method_46025(ModBlocks.BLACK_HANGING_CANVAS_SIGN.get());
      this.dropNamedContainer(ModBlocks.OAK_CABINET.get());
      this.dropNamedContainer(ModBlocks.SPRUCE_CABINET.get());
      this.dropNamedContainer(ModBlocks.BIRCH_CABINET.get());
      this.dropNamedContainer(ModBlocks.JUNGLE_CABINET.get());
      this.dropNamedContainer(ModBlocks.ACACIA_CABINET.get());
      this.dropNamedContainer(ModBlocks.DARK_OAK_CABINET.get());
      this.dropNamedContainer(ModBlocks.MANGROVE_CABINET.get());
      this.dropNamedContainer(ModBlocks.BAMBOO_CABINET.get());
      this.dropNamedContainer(ModBlocks.CHERRY_CABINET.get());
      this.dropNamedContainer(ModBlocks.CRIMSON_CABINET.get());
      this.dropNamedContainer(ModBlocks.WARPED_CABINET.get());
      this.method_46025(ModBlocks.CANVAS_RUG.get());
      this.method_46025(ModBlocks.TATAMI.get());
      this.method_46025(ModBlocks.HALF_TATAMI_MAT.get());
      this.method_46025(ModBlocks.ORGANIC_COMPOST.get());
      this.method_46025(ModBlocks.RICH_SOIL.get());
      this.method_46006(ModBlocks.RICH_SOIL_FARMLAND.get(), (class_1935)ModBlocks.RICH_SOIL.get());
   }

   protected void dropNamedContainer(class_2248 block) {
      this.method_45994(block, this::method_45996);
   }
}
