package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_3495;
import net.minecraft.class_6862;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class BlockTags extends BlockTagProvider {
   public BlockTags(FabricDataOutput output, CompletableFuture<class_7874> lookupProvider) {
      super(output, lookupProvider);
   }

   protected void method_10514(@NotNull class_7874 provider) {
      this.registerModTags();
      this.registerMinecraftTags();
      this.registerCommonTags();
      this.registerCompatibilityTags();
      this.registerBlockMineables();
   }

   protected void registerBlockMineables() {
      this.tagBuilder(class_3481.field_33713)
         .add(
            ModBlocks.BASKET.get(),
            ModBlocks.CUTTING_BOARD.get(),
            ModBlocks.CARROT_CRATE.get(),
            ModBlocks.POTATO_CRATE.get(),
            ModBlocks.BEETROOT_CRATE.get(),
            ModBlocks.CABBAGE_CRATE.get(),
            ModBlocks.TOMATO_CRATE.get(),
            ModBlocks.ONION_CRATE.get(),
            ModBlocks.OAK_CABINET.get(),
            ModBlocks.BIRCH_CABINET.get(),
            ModBlocks.SPRUCE_CABINET.get(),
            ModBlocks.JUNGLE_CABINET.get(),
            ModBlocks.ACACIA_CABINET.get(),
            ModBlocks.DARK_OAK_CABINET.get(),
            ModBlocks.MANGROVE_CABINET.get(),
            ModBlocks.CHERRY_CABINET.get(),
            ModBlocks.BAMBOO_CABINET.get(),
            ModBlocks.CRIMSON_CABINET.get(),
            ModBlocks.WARPED_CABINET.get(),
            ModBlocks.SANDY_SHRUB.get(),
            ModBlocks.STUFFED_PUMPKIN_BLOCK.get()
         );
      this.tagBuilder(class_3481.field_33714).add(ModBlocks.RICE_BALE.get(), ModBlocks.STRAW_BALE.get());
      this.tagBuilder(class_3481.field_33715).add(ModBlocks.STOVE.get(), ModBlocks.COOKING_POT.get(), ModBlocks.SKILLET.get());
      this.tagBuilder(class_3481.field_33716).add(ModBlocks.ORGANIC_COMPOST.get(), ModBlocks.RICH_SOIL.get(), ModBlocks.RICH_SOIL_FARMLAND.get());
      this.tagBuilder(ModTags.MINEABLE_WITH_KNIFE)
         .add(
            class_2246.field_10029,
            class_2246.field_46283,
            class_2246.field_46282,
            class_2246.field_10147,
            class_2246.field_10009,
            class_2246.field_10343,
            class_2246.field_10183,
            ModBlocks.APPLE_PIE.get(),
            ModBlocks.SWEET_BERRY_CHEESECAKE.get(),
            ModBlocks.CHOCOLATE_PIE.get(),
            ModBlocks.ROAST_CHICKEN_BLOCK.get(),
            ModBlocks.HONEY_GLAZED_HAM_BLOCK.get(),
            ModBlocks.SHEPHERDS_PIE_BLOCK.get(),
            ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get()
         )
         .addTag(class_3481.field_15479)
         .addTag(class_3481.field_15481)
         .addTag(class_3481.field_26984)
         .addTag(ModTags.STRAW_BLOCKS)
         .addTag(CommonTags.MINEABLE_WITH_KNIFE);
   }

   protected void registerMinecraftTags() {
      this.tagBuilder(class_3481.field_22414).add(ModBlocks.ROPE.get(), ModBlocks.TOMATO_CROP.get());
      this.tagBuilder(class_3481.field_44471).add(ModBlocks.SANDY_SHRUB.get());
      this.tagBuilder(class_3481.field_44470).add(ModBlocks.SANDY_SHRUB.get());
      this.tagBuilder(class_3481.field_15497).add(ModBlocks.RICH_SOIL.get());
      this.tagBuilder(class_3481.field_25739).add(ModBlocks.ORGANIC_COMPOST.get(), ModBlocks.RICH_SOIL.get());
      this.tagBuilder(class_3481.field_20341)
         .add(ModBlocks.CABBAGE_CROP.get(), ModBlocks.ONION_CROP.get(), ModBlocks.RICE_CROP_PANICLES.get(), ModBlocks.BUDDING_TOMATO_CROP.get());
      this.tagBuilder(class_3481.field_15472)
         .add(
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
            ModBlocks.BLACK_CANVAS_SIGN.get()
         );
      this.tagBuilder(class_3481.field_15492)
         .add(
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
         );
      this.tagBuilder(class_3481.field_40103)
         .add(
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
            ModBlocks.BLACK_HANGING_CANVAS_SIGN.get()
         );
      this.tagBuilder(class_3481.field_40104)
         .add(
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
         );
      this.tagBuilder(class_3481.field_15480)
         .add(
            ModBlocks.WILD_CARROTS.get(),
            ModBlocks.WILD_POTATOES.get(),
            ModBlocks.WILD_BEETROOTS.get(),
            ModBlocks.WILD_CABBAGES.get(),
            ModBlocks.WILD_TOMATOES.get(),
            ModBlocks.WILD_ONIONS.get()
         );
      this.tagBuilder(class_3481.field_20338).add(ModBlocks.WILD_RICE.get());
      this.tagBuilder(class_3481.field_29822).add(ModBlocks.RICH_SOIL.get());
      this.tagBuilder(class_3481.field_44589)
         .add(
            ModBlocks.CABBAGE_CROP.get(),
            ModBlocks.BUDDING_TOMATO_CROP.get(),
            ModBlocks.TOMATO_CROP.get(),
            ModBlocks.ONION_CROP.get(),
            ModBlocks.RICE_CROP.get()
         );
   }

   protected void registerCommonTags() {
      this.tagBuilder(CommonTags.MINEABLE_WITH_KNIFE);
      this.tagBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
         .addTag(CommonTags.STORAGE_BLOCKS_CARROT)
         .addTag(CommonTags.STORAGE_BLOCKS_POTATO)
         .addTag(CommonTags.STORAGE_BLOCKS_BEETROOT)
         .addTag(CommonTags.STORAGE_BLOCKS_CABBAGE)
         .addTag(CommonTags.STORAGE_BLOCKS_TOMATO)
         .addTag(CommonTags.STORAGE_BLOCKS_ONION)
         .addTag(CommonTags.STORAGE_BLOCKS_RICE)
         .addTag(CommonTags.STORAGE_BLOCKS_RICE_PANICLE)
         .addTag(CommonTags.STORAGE_BLOCKS_STRAW);
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_CARROT).add(ModBlocks.CARROT_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_POTATO).add(ModBlocks.POTATO_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_BEETROOT).add(ModBlocks.BEETROOT_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_CABBAGE).add(ModBlocks.CABBAGE_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_TOMATO).add(ModBlocks.TOMATO_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ONION).add(ModBlocks.ONION_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_RICE).add(ModBlocks.RICE_BAG.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_RICE_PANICLE).add(ModBlocks.RICE_BALE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_STRAW).add(ModBlocks.STRAW_BALE.get());
   }

   protected void registerModTags() {
      this.tagBuilder(ModTags.TERRAIN).addTag(class_3481.field_29822).addTag(class_3481.field_15466);
      this.tagBuilder(ModTags.STRAW_BLOCKS)
         .add(
            ModBlocks.RICE_BAG.get(),
            ModBlocks.ROPE.get(),
            ModBlocks.SAFETY_NET.get(),
            ModBlocks.CANVAS_RUG.get(),
            ModBlocks.TATAMI.get(),
            ModBlocks.FULL_TATAMI_MAT.get(),
            ModBlocks.HALF_TATAMI_MAT.get()
         );
      this.tagBuilder(ModTags.WILD_CROPS)
         .add(
            ModBlocks.WILD_CARROTS.get(),
            ModBlocks.WILD_POTATOES.get(),
            ModBlocks.WILD_BEETROOTS.get(),
            ModBlocks.WILD_CABBAGES.get(),
            ModBlocks.WILD_TOMATOES.get(),
            ModBlocks.WILD_ONIONS.get(),
            ModBlocks.WILD_RICE.get()
         );
      this.tagBuilder(ModTags.ROPES)
         .add(ModBlocks.ROPE.get())
         .addOptional(class_2960.method_60654("quark:rope"))
         .addOptional(class_2960.method_60654("supplementaries:rope"));
      this.tagBuilder(ModTags.TRAY_HEAT_SOURCES).add(class_2246.field_10164).addTag(class_3481.field_23799).addTag(class_3481.field_21952);
      this.tagBuilder(ModTags.HEAT_SOURCES).add(class_2246.field_10092, class_2246.field_27098, ModBlocks.STOVE.get()).addTag(ModTags.TRAY_HEAT_SOURCES);
      this.tagBuilder(ModTags.HEAT_CONDUCTORS).add(class_2246.field_10312).addOptional(class_2960.method_60654("create:chute"));
      this.tagBuilder(ModTags.COMPOST_ACTIVATORS)
         .add(
            class_2246.field_10251,
            class_2246.field_10559,
            class_2246.field_10520,
            class_2246.field_10402,
            ModBlocks.ORGANIC_COMPOST.get(),
            ModBlocks.RICH_SOIL.get(),
            ModBlocks.RICH_SOIL_FARMLAND.get(),
            ModBlocks.BROWN_MUSHROOM_COLONY.get(),
            ModBlocks.RED_MUSHROOM_COLONY.get()
         );
      this.tagBuilder(ModTags.UNAFFECTED_BY_RICH_SOIL)
         .add(
            class_2246.field_10219,
            class_2246.field_10479,
            class_2246.field_28681,
            class_2246.field_22120,
            class_2246.field_22113,
            class_2246.field_10112,
            class_2246.field_23078,
            class_2246.field_23079,
            class_2246.field_28682,
            class_2246.field_28683,
            class_2246.field_42750,
            ModBlocks.SANDY_SHRUB.get(),
            ModBlocks.BROWN_MUSHROOM_COLONY.get(),
            ModBlocks.RED_MUSHROOM_COLONY.get()
         )
         .addTag(ModTags.WILD_CROPS)
         .addTag(class_3481.field_20338);
      this.tagBuilder(ModTags.MUSHROOM_COLONY_GROWABLE_ON).add(ModBlocks.RICH_SOIL.get());
      this.tagBuilder(ModTags.DROPS_CAKE_SLICE)
         .add(
            class_2246.field_27142,
            class_2246.field_27143,
            class_2246.field_27144,
            class_2246.field_27145,
            class_2246.field_27146,
            class_2246.field_27147,
            class_2246.field_27148,
            class_2246.field_27149,
            class_2246.field_27150,
            class_2246.field_27151,
            class_2246.field_27152,
            class_2246.field_27153,
            class_2246.field_27154,
            class_2246.field_27155,
            class_2246.field_27156,
            class_2246.field_27157,
            class_2246.field_27158
         );
      this.tagBuilder(ModTags.CAMPFIRE_SIGNAL_SMOKE).add(ModBlocks.STRAW_BALE.get()).add(ModBlocks.RICE_BALE.get());
   }

   private void registerCompatibilityTags() {
      this.tagBuilder(CompatibilityTags.CREATE_PASSIVE_BOILER_HEATERS).add(ModBlocks.STOVE.get());
      this.tagBuilder(CompatibilityTags.CREATE_BRITTLE).add(ModBlocks.CUTTING_BOARD.get(), ModBlocks.FULL_TATAMI_MAT.get(), ModBlocks.HALF_TATAMI_MAT.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK)
         .add(ModBlocks.CABBAGE_CROP.get(), ModBlocks.ONION_CROP.get(), ModBlocks.RICE_CROP.get(), ModBlocks.RICE_CROP_PANICLES.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(ModBlocks.ONION_CROP.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK)
         .add(ModBlocks.BUDDING_TOMATO_CROP.get(), ModBlocks.TOMATO_CROP.get(), ModBlocks.RICE_CROP.get(), ModBlocks.RICE_CROP_PANICLES.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS_BLOCK).add(ModBlocks.CABBAGE_CROP.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS).add(ModBlocks.ONION_CROP.get());
   }

   private BlockTags.RefabricatedTagBuilder tagBuilder(class_6862<class_2248> tagKey) {
      return new BlockTags.RefabricatedTagBuilder(tagKey);
   }

   public class RefabricatedTagBuilder {
      private FabricTagProvider<class_2248>.FabricTagBuilder valueLookupBuilder;
      private class_3495 rawBuilder;

      public RefabricatedTagBuilder(class_6862<class_2248> tag) {
         this.valueLookupBuilder = BlockTags.this.getOrCreateTagBuilder(tag);
         this.rawBuilder = BlockTags.this.method_27169(tag);
      }

      public BlockTags.RefabricatedTagBuilder add(class_2248 item) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(item);
         return this;
      }

      public BlockTags.RefabricatedTagBuilder add(Supplier<class_2248> item) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(item.get());
         return this;
      }

      public BlockTags.RefabricatedTagBuilder addOptionalTag(class_6862<class_2248> itemTagKey) {
         this.valueLookupBuilder = this.valueLookupBuilder.addOptionalTag(itemTagKey);
         return this;
      }

      public BlockTags.RefabricatedTagBuilder add(class_2248... items) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(items);
         return this;
      }

      public BlockTags.RefabricatedTagBuilder addOptional(class_2960 item) {
         this.rawBuilder = this.rawBuilder.method_34891(item);
         return this;
      }

      @SafeVarargs
      public final void addTags(class_6862<class_2248>... tags) {
         for (class_6862<class_2248> tag : tags) {
            this.valueLookupBuilder.addTag(tag);
         }
      }

      public BlockTags.RefabricatedTagBuilder addTag(class_6862<class_2248> tagKey) {
         this.valueLookupBuilder.forceAddTag(tagKey);
         return this;
      }
   }
}
