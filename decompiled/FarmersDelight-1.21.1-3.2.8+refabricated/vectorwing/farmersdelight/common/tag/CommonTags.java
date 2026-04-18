package vectorwing.farmersdelight.common.tag;

import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public class CommonTags {
   @Deprecated
   public static final class_6862<class_1792> FOODS_MILK = commonItemTag("foods/milk");
   public static final class_6862<class_2248> MINEABLE_WITH_KNIFE = commonBlockTag("mineable/knife");
   public static final class_6862<class_2248> STORAGE_BLOCKS_CARROT = commonBlockTag("storage_blocks/carrot");
   public static final class_6862<class_2248> STORAGE_BLOCKS_POTATO = commonBlockTag("storage_blocks/potato");
   public static final class_6862<class_2248> STORAGE_BLOCKS_BEETROOT = commonBlockTag("storage_blocks/beetroot");
   public static final class_6862<class_2248> STORAGE_BLOCKS_CABBAGE = commonBlockTag("storage_blocks/cabbage");
   public static final class_6862<class_2248> STORAGE_BLOCKS_TOMATO = commonBlockTag("storage_blocks/tomato");
   public static final class_6862<class_2248> STORAGE_BLOCKS_ONION = commonBlockTag("storage_blocks/onion");
   public static final class_6862<class_2248> STORAGE_BLOCKS_RICE = commonBlockTag("storage_blocks/rice");
   public static final class_6862<class_2248> STORAGE_BLOCKS_RICE_PANICLE = commonBlockTag("storage_blocks/rice_panicle");
   public static final class_6862<class_2248> STORAGE_BLOCKS_STRAW = commonBlockTag("storage_blocks/straw");
   public static final class_6862<class_1792> CROPS_CABBAGE = commonItemTag("crops/cabbage");
   public static final class_6862<class_1792> CROPS_TOMATO = commonItemTag("crops/tomato");
   public static final class_6862<class_1792> CROPS_ONION = commonItemTag("crops/onion");
   public static final class_6862<class_1792> CROPS_RICE = commonItemTag("crops/rice");
   public static final class_6862<class_1792> CROPS_GRAIN = commonItemTag("crops/grain");
   public static final class_6862<class_1792> FOODS_CABBAGE = commonItemTag("foods/cabbage");
   public static final class_6862<class_1792> FOODS_TOMATO = commonItemTag("foods/tomato");
   public static final class_6862<class_1792> FOODS_ONION = commonItemTag("foods/onion");
   public static final class_6862<class_1792> FOODS_LEAFY_GREEN = commonItemTag("foods/leafy_green");
   public static final class_6862<class_1792> FOODS_DOUGH = commonItemTag("foods/dough");
   public static final class_6862<class_1792> FOODS_DOUGH_WHEAT = commonItemTag("foods/dough/wheat");
   public static final class_6862<class_1792> FOODS_PASTA = commonItemTag("foods/pasta");
   public static final class_6862<class_1792> FOODS_RAW_BACON = commonItemTag("foods/raw_bacon");
   public static final class_6862<class_1792> FOODS_RAW_BEEF = commonItemTag("foods/raw_beef");
   public static final class_6862<class_1792> FOODS_RAW_CHICKEN = commonItemTag("foods/raw_chicken");
   public static final class_6862<class_1792> FOODS_RAW_PORK = commonItemTag("foods/raw_pork");
   public static final class_6862<class_1792> FOODS_RAW_MUTTON = commonItemTag("foods/raw_mutton");
   public static final class_6862<class_1792> FOODS_SAFE_RAW_FISH = commonItemTag("foods/safe_raw_fish");
   public static final class_6862<class_1792> FOODS_RAW_COD = commonItemTag("foods/raw_cod");
   public static final class_6862<class_1792> FOODS_RAW_SALMON = commonItemTag("foods/raw_salmon");
   public static final class_6862<class_1792> FOODS_COOKED_BACON = commonItemTag("foods/cooked_bacon");
   public static final class_6862<class_1792> FOODS_COOKED_BEEF = commonItemTag("foods/cooked_beef");
   public static final class_6862<class_1792> FOODS_COOKED_CHICKEN = commonItemTag("foods/cooked_chicken");
   public static final class_6862<class_1792> FOODS_COOKED_PORK = commonItemTag("foods/cooked_pork");
   public static final class_6862<class_1792> FOODS_COOKED_MUTTON = commonItemTag("foods/cooked_mutton");
   public static final class_6862<class_1792> FOODS_COOKED_EGG = commonItemTag("foods/cooked_egg");
   public static final class_6862<class_1792> FOODS_COOKED_COD = commonItemTag("foods/cooked_cod");
   public static final class_6862<class_1792> FOODS_COOKED_SALMON = commonItemTag("foods/cooked_salmon");
   public static final class_6862<class_1792> TOOLS_KNIFE = commonItemTag("tools/knife");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_CARROT = commonItemTag("storage_blocks/carrot");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_POTATO = commonItemTag("storage_blocks/potato");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_BEETROOT = commonItemTag("storage_blocks/beetroot");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_CABBAGE = commonItemTag("storage_blocks/cabbage");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_TOMATO = commonItemTag("storage_blocks/tomato");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_ONION = commonItemTag("storage_blocks/onion");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_RICE = commonItemTag("storage_blocks/rice");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_RICE_PANICLE = commonItemTag("storage_blocks/rice_panicle");
   public static final class_6862<class_1792> STORAGE_BLOCKS_ITEM_STRAW = commonItemTag("storage_blocks/straw");

   private static class_6862<class_2248> commonBlockTag(String path) {
      return class_6862.method_40092(class_7924.field_41254, class_2960.method_60655("c", path));
   }

   private static class_6862<class_1792> commonItemTag(String path) {
      return class_6862.method_40092(class_7924.field_41197, class_2960.method_60655("c", path));
   }
}
