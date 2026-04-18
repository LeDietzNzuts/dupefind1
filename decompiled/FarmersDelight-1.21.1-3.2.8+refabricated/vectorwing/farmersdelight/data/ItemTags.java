package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_3489;
import net.minecraft.class_3495;
import net.minecraft.class_6862;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ConventionalTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class ItemTags extends ItemTagProvider {
   public ItemTags(FabricDataOutput output, CompletableFuture<class_7874> provider, BlockTagProvider blockTagProvider) {
      super(output, provider, blockTagProvider);
   }

   protected void method_10514(@NotNull class_7874 provider) {
      this.copy(ModTags.WILD_CROPS, ModTags.WILD_CROPS_ITEM);
      this.copy(class_3481.field_15480, class_3489.field_15543);
      this.registerMinecraftTags();
      this.registerModTags();
      this.registerNeoForgeTags();
      this.registerCommonTags();
      this.registerCompatibilityTags();
   }

   private void registerMinecraftTags() {
      this.method_10512(class_3489.field_42617).method_26792(ModTags.KNIVES);
      this.tagBuilder(class_3489.field_20343).add(ModItems.WILD_RICE.get());
      this.tagBuilder(class_3489.field_24481).add(ModItems.GOLDEN_KNIFE.get());
      this.method_10512(class_3489.field_15533).method_26792(ModTags.CANVAS_SIGNS);
      this.method_10512(class_3489.field_40108).method_26792(ModTags.HANGING_CANVAS_SIGNS);
      this.tagBuilder(class_3489.field_44591).add(ModItems.CABBAGE_SEEDS.get()).add(ModItems.TOMATO_SEEDS.get()).add(ModItems.ONION.get());
      this.tagBuilder(class_3489.field_48310).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
      this.tagBuilder(class_3489.field_48305).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
      this.tagBuilder(class_3489.field_50108).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
      this.tagBuilder(class_3489.field_50107).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
      this.tagBuilder(class_3489.field_48304).addTag(ModTags.KNIVES).add(ModItems.SKILLET.get());
      this.method_10512(class_3489.field_48306).method_26792(ModTags.KNIVES);
      this.method_10512(class_3489.field_48307).method_26792(ModTags.KNIVES);
      this.tagBuilder(class_3489.field_49932)
         .add(ModItems.MINCED_BEEF.get())
         .add(ModItems.BEEF_PATTY.get())
         .add(ModItems.CHICKEN_CUTS.get())
         .add(ModItems.COOKED_CHICKEN_CUTS.get())
         .add(ModItems.BACON.get())
         .add(ModItems.COOKED_BACON.get())
         .add(ModItems.MUTTON_CHOPS.get())
         .add(ModItems.COOKED_MUTTON_CHOPS.get())
         .add(ModItems.HAM.get())
         .add(ModItems.SMOKED_HAM.get())
         .add(ModItems.DOG_FOOD.get());
      this.tagBuilder(class_3489.field_49943).add(ModItems.CABBAGE_SEEDS.get()).add(ModItems.TOMATO_SEEDS.get()).add(ModItems.RICE.get());
      this.tagBuilder(class_3489.field_49950).add(ModItems.CABBAGE.get()).add(ModItems.TOMATO.get());
      this.tagBuilder(class_3489.field_49951).add(ModItems.CABBAGE.get());
      this.tagBuilder(class_3489.field_49955).add(ModItems.CABBAGE_SEEDS.get()).add(ModItems.TOMATO_SEEDS.get()).add(ModItems.RICE.get());
      this.tagBuilder(class_3489.field_49939).add(ModItems.HORSE_FEED.get());
   }

   private void registerModTags() {
      this.tagBuilder(ModTags.SNACKS)
         .add(
            ModItems.BARBECUE_STICK.get(),
            ModItems.EGG_SANDWICH.get(),
            ModItems.CHICKEN_SANDWICH.get(),
            ModItems.HAMBURGER.get(),
            ModItems.BACON_SANDWICH.get(),
            ModItems.MUTTON_WRAP.get(),
            ModItems.DUMPLINGS.get(),
            ModItems.STUFFED_POTATO.get(),
            ModItems.CABBAGE_ROLLS.get(),
            ModItems.SALMON_ROLL.get(),
            ModItems.COD_ROLL.get(),
            ModItems.KELP_ROLL.get(),
            ModItems.KELP_ROLL_SLICE.get()
         );
      this.tagBuilder(ModTags.MEALS)
         .add(
            ModItems.MIXED_SALAD.get(),
            ModItems.COOKED_RICE.get(),
            ModItems.BONE_BROTH.get(),
            ModItems.BEEF_STEW.get(),
            ModItems.VEGETABLE_SOUP.get(),
            ModItems.FISH_STEW.get(),
            ModItems.CHICKEN_SOUP.get(),
            ModItems.FRIED_RICE.get(),
            ModItems.PUMPKIN_SOUP.get(),
            ModItems.BAKED_COD_STEW.get(),
            ModItems.NOODLE_SOUP.get(),
            ModItems.BACON_AND_EGGS.get(),
            ModItems.RATATOUILLE.get(),
            ModItems.STEAK_AND_POTATOES.get(),
            ModItems.PASTA_WITH_MEATBALLS.get(),
            ModItems.PASTA_WITH_MUTTON_CHOP.get(),
            ModItems.MUSHROOM_RICE.get(),
            ModItems.ROASTED_MUTTON_CHOPS.get(),
            ModItems.VEGETABLE_NOODLES.get(),
            ModItems.SQUID_INK_PASTA.get(),
            ModItems.GRILLED_SALMON.get(),
            ModItems.ROAST_CHICKEN.get(),
            ModItems.STUFFED_PUMPKIN.get(),
            ModItems.HONEY_GLAZED_HAM.get(),
            ModItems.SHEPHERDS_PIE.get()
         );
      this.tagBuilder(ModTags.DRINKS).add(ModItems.MILK_BOTTLE.get(), ModItems.APPLE_CIDER.get(), ModItems.MELON_JUICE.get(), ModItems.HOT_COCOA.get());
      this.tagBuilder(ModTags.SWEETS)
         .add(
            ModItems.CAKE_SLICE.get(),
            ModItems.APPLE_PIE_SLICE.get(),
            ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get(),
            ModItems.CHOCOLATE_PIE_SLICE.get(),
            ModItems.SWEET_BERRY_COOKIE.get(),
            ModItems.HONEY_COOKIE.get(),
            ModItems.MELON_POPSICLE.get(),
            ModItems.GLOW_BERRY_CUSTARD.get(),
            ModItems.FRUIT_SALAD.get()
         );
      this.tagBuilder(ModTags.FEASTS)
         .add(
            ModItems.ROAST_CHICKEN_BLOCK.get(),
            ModItems.STUFFED_PUMPKIN_BLOCK.get(),
            ModItems.SHEPHERDS_PIE_BLOCK.get(),
            ModItems.HONEY_GLAZED_HAM_BLOCK.get(),
            ModItems.RICE_ROLL_MEDLEY_BLOCK.get()
         );
      this.tagBuilder(ModTags.KNIVES)
         .add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
      this.tagBuilder(ModTags.KNIFE_ENCHANTABLE).addTag(ModTags.KNIVES);
      this.tagBuilder(ModTags.STRAW_HARVESTERS).addTag(ModTags.KNIVES);
      this.tagBuilder(ModTags.CABBAGE_ROLL_INGREDIENTS)
         .addTag(CommonTags.FOODS_RAW_PORK)
         .addTag(CommonTags.FOODS_SAFE_RAW_FISH)
         .addTag(CommonTags.FOODS_RAW_CHICKEN)
         .addTag(CommonTags.FOODS_RAW_BEEF)
         .addTag(CommonTags.FOODS_RAW_MUTTON)
         .addTag(ConventionalItemTags.EGGS)
         .addTag(ConventionalItemTags.MUSHROOMS)
         .add(class_1802.field_8179, class_1802.field_8567, class_1802.field_8186);
      this.tagBuilder(ModTags.CANVAS_SIGNS)
         .add(ModItems.CANVAS_SIGN.get())
         .add(ModItems.WHITE_CANVAS_SIGN.get())
         .add(ModItems.ORANGE_CANVAS_SIGN.get())
         .add(ModItems.MAGENTA_CANVAS_SIGN.get())
         .add(ModItems.LIGHT_BLUE_CANVAS_SIGN.get())
         .add(ModItems.YELLOW_CANVAS_SIGN.get())
         .add(ModItems.LIME_CANVAS_SIGN.get())
         .add(ModItems.PINK_CANVAS_SIGN.get())
         .add(ModItems.GRAY_CANVAS_SIGN.get())
         .add(ModItems.LIGHT_GRAY_CANVAS_SIGN.get())
         .add(ModItems.CYAN_CANVAS_SIGN.get())
         .add(ModItems.PURPLE_CANVAS_SIGN.get())
         .add(ModItems.BLUE_CANVAS_SIGN.get())
         .add(ModItems.BROWN_CANVAS_SIGN.get())
         .add(ModItems.GREEN_CANVAS_SIGN.get())
         .add(ModItems.RED_CANVAS_SIGN.get())
         .add(ModItems.BLACK_CANVAS_SIGN.get());
      this.tagBuilder(ModTags.HANGING_CANVAS_SIGNS)
         .add(ModItems.HANGING_CANVAS_SIGN.get())
         .add(ModItems.WHITE_HANGING_CANVAS_SIGN.get())
         .add(ModItems.ORANGE_HANGING_CANVAS_SIGN.get())
         .add(ModItems.MAGENTA_HANGING_CANVAS_SIGN.get())
         .add(ModItems.LIGHT_BLUE_HANGING_CANVAS_SIGN.get())
         .add(ModItems.YELLOW_HANGING_CANVAS_SIGN.get())
         .add(ModItems.LIME_HANGING_CANVAS_SIGN.get())
         .add(ModItems.PINK_HANGING_CANVAS_SIGN.get())
         .add(ModItems.GRAY_HANGING_CANVAS_SIGN.get())
         .add(ModItems.LIGHT_GRAY_HANGING_CANVAS_SIGN.get())
         .add(ModItems.CYAN_HANGING_CANVAS_SIGN.get())
         .add(ModItems.PURPLE_HANGING_CANVAS_SIGN.get())
         .add(ModItems.BLUE_HANGING_CANVAS_SIGN.get())
         .add(ModItems.BROWN_HANGING_CANVAS_SIGN.get())
         .add(ModItems.GREEN_HANGING_CANVAS_SIGN.get())
         .add(ModItems.RED_HANGING_CANVAS_SIGN.get())
         .add(ModItems.BLACK_HANGING_CANVAS_SIGN.get());
      this.tagBuilder(ModTags.WOODEN_CABINETS)
         .add(ModItems.OAK_CABINET.get())
         .add(ModItems.SPRUCE_CABINET.get())
         .add(ModItems.BIRCH_CABINET.get())
         .add(ModItems.JUNGLE_CABINET.get())
         .add(ModItems.ACACIA_CABINET.get())
         .add(ModItems.DARK_OAK_CABINET.get())
         .add(ModItems.MANGROVE_CABINET.get())
         .add(ModItems.CHERRY_CABINET.get())
         .add(ModItems.BAMBOO_CABINET.get())
         .add(ModItems.CRIMSON_CABINET.get())
         .add(ModItems.WARPED_CABINET.get());
      this.tagBuilder(ModTags.CABINETS).addTag(ModTags.WOODEN_CABINETS);
      this.tagBuilder(ModTags.OFFHAND_EQUIPMENT).addTag(ConventionalItemTags.SHIELD_TOOLS).addOptional(class_2960.method_60654("create:extendo_grip"));
      this.tagBuilder(ModTags.SERVING_CONTAINERS).add(class_1802.field_8428, class_1802.field_8469, class_1802.field_8550);
      this.tagBuilder(ModTags.FLAT_ON_CUTTING_BOARD)
         .add(class_1802.field_8547, class_1802.field_27070)
         .addOptional(class_2960.method_60654("supplementaries:quiver"))
         .addOptional(class_2960.method_60654("autumnity:turkey"))
         .addOptional(class_2960.method_60654("autumnity:cooked_turkey"));
   }

   private void registerNeoForgeTags() {
      this.method_10512(ConventionalItemTags.CROPS).method_26792(CommonTags.CROPS_GRAIN);
      this.method_10512(ConventionalItemTags.DRINKS).method_26792(ModTags.DRINKS);
      this.tagBuilder(ConventionalItemTags.FOODS)
         .add(ModItems.TOMATO_SAUCE.get())
         .add(ModItems.PIE_CRUST.get())
         .add(ModItems.PUMPKIN_SLICE.get())
         .add(ModItems.HAM.get())
         .add(ModItems.SMOKED_HAM.get())
         .add(ModItems.DOG_FOOD.get())
         .addTag(ModTags.SNACKS)
         .addTag(ModTags.MEALS)
         .addTag(ModTags.SWEETS)
         .addTag(CommonTags.FOODS_LEAFY_GREEN)
         .addTag(CommonTags.FOODS_DOUGH)
         .addTag(CommonTags.FOODS_PASTA)
         .addTag(CommonTags.FOODS_COOKED_EGG);
      this.tagBuilder(ConventionalTags.DRINKS_MILK).add(ModItems.MILK_BOTTLE.get()).addTag(CommonTags.FOODS_MILK);
      this.tagBuilder(ConventionalItemTags.VEGETABLE_FOODS).add(ModItems.ONION.get(), ModItems.TOMATO.get());
      this.tagBuilder(ConventionalItemTags.COOKIE_FOODS).add(ModItems.HONEY_COOKIE.get(), ModItems.SWEET_BERRY_COOKIE.get());
      this.method_10512(ConventionalItemTags.DOUGH_FOODS).method_26792(CommonTags.FOODS_DOUGH_WHEAT);
      this.tagBuilder(ConventionalItemTags.RAW_MEAT_FOODS)
         .addTags(CommonTags.FOODS_RAW_CHICKEN, CommonTags.FOODS_RAW_PORK, CommonTags.FOODS_RAW_BEEF, CommonTags.FOODS_RAW_MUTTON);
      this.tagBuilder(ConventionalItemTags.RAW_FISH_FOODS).addTags(CommonTags.FOODS_RAW_COD, CommonTags.FOODS_RAW_SALMON);
      this.tagBuilder(ConventionalItemTags.COOKED_MEAT_FOODS)
         .addTags(CommonTags.FOODS_COOKED_CHICKEN, CommonTags.FOODS_COOKED_PORK, CommonTags.FOODS_COOKED_BEEF, CommonTags.FOODS_COOKED_MUTTON);
      this.tagBuilder(ConventionalItemTags.COOKED_FISH_FOODS).addTags(CommonTags.FOODS_COOKED_COD, CommonTags.FOODS_COOKED_SALMON);
      this.tagBuilder(ConventionalItemTags.FOOD_POISONING_FOODS)
         .add(ModItems.WHEAT_DOUGH.get(), ModItems.RAW_PASTA.get(), ModItems.CHICKEN_CUTS.get(), ModItems.NETHER_SALAD.get());
      this.tagBuilder(ConventionalItemTags.EDIBLE_WHEN_PLACED_FOODS)
         .add(ModItems.APPLE_PIE.get())
         .add(ModItems.SWEET_BERRY_CHEESECAKE.get())
         .add(ModItems.CHOCOLATE_PIE.get())
         .add(ModItems.ROAST_CHICKEN_BLOCK.get())
         .add(ModItems.HONEY_GLAZED_HAM_BLOCK.get())
         .add(ModItems.SHEPHERDS_PIE_BLOCK.get())
         .add(ModItems.STUFFED_PUMPKIN_BLOCK.get())
         .add(ModItems.RICE_ROLL_MEDLEY_BLOCK.get());
      this.tagBuilder(ConventionalItemTags.SOUP_FOODS)
         .add(ModItems.BONE_BROTH.get())
         .add(ModItems.BEEF_STEW.get())
         .add(ModItems.VEGETABLE_SOUP.get())
         .add(ModItems.CHICKEN_SOUP.get())
         .add(ModItems.FISH_STEW.get())
         .add(ModItems.PUMPKIN_SOUP.get())
         .add(ModItems.BAKED_COD_STEW.get())
         .add(ModItems.NOODLE_SOUP.get());
      this.tagBuilder(ConventionalItemTags.PIE_FOODS)
         .add(ModItems.APPLE_PIE_SLICE.get())
         .add(ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get())
         .add(ModItems.CHOCOLATE_PIE_SLICE.get());
      this.method_10512(ConventionalItemTags.TOOLS).method_26792(CommonTags.TOOLS_KNIFE);
      this.tagBuilder(ConventionalItemTags.SEEDS).add(ModItems.CABBAGE_SEEDS.get(), ModItems.RICE.get(), ModItems.TOMATO_SEEDS.get());
      this.tagBuilder(ConventionalItemTags.CROPS).addTags(CommonTags.CROPS_CABBAGE, CommonTags.CROPS_ONION, CommonTags.CROPS_RICE, CommonTags.CROPS_TOMATO);
      this.tagBuilder(ConventionalItemTags.STORAGE_BLOCKS)
         .addTags(
            CommonTags.STORAGE_BLOCKS_ITEM_CARROT,
            CommonTags.STORAGE_BLOCKS_ITEM_POTATO,
            CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT,
            CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE,
            CommonTags.STORAGE_BLOCKS_ITEM_TOMATO,
            CommonTags.STORAGE_BLOCKS_ITEM_ONION,
            CommonTags.STORAGE_BLOCKS_ITEM_RICE,
            CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE,
            CommonTags.STORAGE_BLOCKS_ITEM_STRAW
         );
   }

   public void registerCommonTags() {
      this.tagBuilder(CommonTags.FOODS_MILK).add(class_1802.field_8103, ModItems.MILK_BOTTLE.get());
      this.tagBuilder(CommonTags.CROPS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
      this.tagBuilder(CommonTags.CROPS_ONION).add(ModItems.ONION.get());
      this.tagBuilder(CommonTags.CROPS_TOMATO).add(ModItems.TOMATO.get());
      this.tagBuilder(CommonTags.CROPS_RICE).add(ModItems.RICE.get());
      this.tagBuilder(CommonTags.FOODS_CABBAGE).add(ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get());
      this.tagBuilder(CommonTags.FOODS_TOMATO).add(ModItems.TOMATO.get());
      this.tagBuilder(CommonTags.FOODS_ONION).add(ModItems.ONION.get());
      this.tagBuilder(CommonTags.FOODS_DOUGH_WHEAT).add(ModItems.WHEAT_DOUGH.get());
      this.tagBuilder(CommonTags.CROPS_GRAIN).add(class_1802.field_8861, ModItems.RICE.get());
      this.tagBuilder(CommonTags.FOODS_PASTA).add(ModItems.RAW_PASTA.get());
      this.method_10512(CommonTags.FOODS_LEAFY_GREEN).method_26792(CommonTags.FOODS_CABBAGE);
      this.tagBuilder(CommonTags.FOODS_RAW_BACON).add(ModItems.BACON.get());
      this.tagBuilder(CommonTags.FOODS_RAW_BEEF).add(class_1802.field_8046, ModItems.MINCED_BEEF.get());
      this.tagBuilder(CommonTags.FOODS_RAW_CHICKEN).add(class_1802.field_8726, ModItems.CHICKEN_CUTS.get());
      this.tagBuilder(CommonTags.FOODS_RAW_PORK).add(class_1802.field_8389).addTag(CommonTags.FOODS_RAW_BACON);
      this.tagBuilder(CommonTags.FOODS_RAW_MUTTON).add(class_1802.field_8748, ModItems.MUTTON_CHOPS.get());
      this.tagBuilder(CommonTags.FOODS_RAW_COD).add(class_1802.field_8429, ModItems.COD_SLICE.get());
      this.tagBuilder(CommonTags.FOODS_RAW_SALMON).add(class_1802.field_8209, ModItems.SALMON_SLICE.get());
      this.tagBuilder(CommonTags.FOODS_SAFE_RAW_FISH).addTag(CommonTags.FOODS_RAW_COD).addTag(CommonTags.FOODS_RAW_SALMON).add(class_1802.field_8846);
      this.tagBuilder(CommonTags.FOODS_COOKED_BACON).add(ModItems.COOKED_BACON.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_BEEF).add(class_1802.field_8176, ModItems.BEEF_PATTY.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_CHICKEN).add(class_1802.field_8544, ModItems.COOKED_CHICKEN_CUTS.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_PORK).add(class_1802.field_8261).addTag(CommonTags.FOODS_COOKED_BACON);
      this.tagBuilder(CommonTags.FOODS_COOKED_MUTTON).add(class_1802.field_8347, ModItems.COOKED_MUTTON_CHOPS.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_COD).add(class_1802.field_8373, ModItems.COOKED_COD_SLICE.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_SALMON).add(class_1802.field_8509, ModItems.COOKED_SALMON_SLICE.get());
      this.tagBuilder(CommonTags.FOODS_COOKED_EGG).add(ModItems.FRIED_EGG.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CARROT).add(ModItems.CARROT_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_POTATO).add(ModItems.POTATO_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_BEETROOT).add(ModItems.BEETROOT_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_CABBAGE).add(ModItems.CABBAGE_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_TOMATO).add(ModItems.TOMATO_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_ONION).add(ModItems.ONION_CRATE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE).add(ModItems.RICE_BAG.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_RICE_PANICLE).add(ModItems.RICE_BALE.get());
      this.tagBuilder(CommonTags.STORAGE_BLOCKS_ITEM_STRAW).add(ModItems.STRAW_BALE.get());
      this.tagBuilder(CommonTags.TOOLS_KNIFE)
         .add(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.GOLDEN_KNIFE.get(), ModItems.NETHERITE_KNIFE.get());
   }

   public void registerCompatibilityTags() {
      this.tagBuilder(CompatibilityTags.CREATE_UPRIGHT_ON_BELT)
         .addTag(ModTags.MEALS)
         .addTag(ModTags.DRINKS)
         .addTag(ModTags.FEASTS)
         .add(ModItems.TOMATO_SAUCE.get())
         .add(ModItems.DOG_FOOD.get())
         .add(ModItems.FRUIT_SALAD.get())
         .add(ModItems.NETHER_SALAD.get())
         .add(ModItems.PIE_CRUST.get())
         .add(ModItems.APPLE_PIE.get())
         .add(ModItems.SWEET_BERRY_CHEESECAKE.get())
         .add(ModItems.CHOCOLATE_PIE.get());
      this.tagBuilder(CompatibilityTags.CREATE_CA_PLANT_FOODS)
         .add(ModItems.PUMPKIN_SLICE.get())
         .add(ModItems.ROTTEN_TOMATO.get())
         .add(ModItems.RICE_PANICLE.get());
      this.tagBuilder(CompatibilityTags.CREATE_CA_PLANTS)
         .add(ModItems.SANDY_SHRUB.get())
         .add(ModItems.BROWN_MUSHROOM_COLONY.get())
         .add(ModItems.RED_MUSHROOM_COLONY.get());
      this.tagBuilder(CompatibilityTags.ORIGINS_MEAT)
         .add(ModItems.FRIED_EGG.get())
         .add(ModItems.COD_SLICE.get())
         .add(ModItems.COOKED_COD_SLICE.get())
         .add(ModItems.SALMON_SLICE.get())
         .add(ModItems.COOKED_SALMON_SLICE.get())
         .add(ModItems.BACON_AND_EGGS.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS).add(ModItems.CABBAGE_SEEDS.get()).add(ModItems.ONION.get()).add(ModItems.RICE.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS).add(ModItems.ONION.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS).add(ModItems.TOMATO_SEEDS.get()).add(ModItems.RICE.get());
      this.tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS).add(ModItems.CABBAGE_SEEDS.get());
      this.tagBuilder(CompatibilityTags.TINKERS_CONSTRUCT_SEEDS).add(ModItems.ONION.get());
   }

   private ItemTags.RefabricatedTagBuilder tagBuilder(class_6862<class_1792> tagKey) {
      return new ItemTags.RefabricatedTagBuilder(tagKey);
   }

   public class RefabricatedTagBuilder {
      private FabricTagProvider<class_1792>.FabricTagBuilder valueLookupBuilder;
      private class_3495 rawBuilder;

      public RefabricatedTagBuilder(class_6862<class_1792> tag) {
         this.valueLookupBuilder = ItemTags.this.getOrCreateTagBuilder(tag);
         this.rawBuilder = ItemTags.this.method_27169(tag);
      }

      public ItemTags.RefabricatedTagBuilder add(class_1792 item) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(item);
         return this;
      }

      public ItemTags.RefabricatedTagBuilder add(Supplier<class_1792> item) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(item.get());
         return this;
      }

      public ItemTags.RefabricatedTagBuilder addOptionalTag(class_6862<class_1792> itemTagKey) {
         this.valueLookupBuilder = this.valueLookupBuilder.addOptionalTag(itemTagKey);
         return this;
      }

      public ItemTags.RefabricatedTagBuilder add(class_1792... items) {
         this.valueLookupBuilder = this.valueLookupBuilder.add(items);
         return this;
      }

      public ItemTags.RefabricatedTagBuilder addOptional(class_2960 item) {
         this.rawBuilder = this.rawBuilder.method_34891(item);
         return this;
      }

      @SafeVarargs
      public final void addTags(class_6862<class_1792>... tags) {
         for (class_6862<class_1792> tag : tags) {
            this.valueLookupBuilder.forceAddTag(tag);
         }
      }

      public ItemTags.RefabricatedTagBuilder addTag(class_6862<class_1792> tagKey) {
         this.valueLookupBuilder.forceAddTag(tagKey);
         return this;
      }
   }
}
