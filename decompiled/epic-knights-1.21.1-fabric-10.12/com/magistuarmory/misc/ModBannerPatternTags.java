package com.magistuarmory.misc;

import net.minecraft.class_2582;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public class ModBannerPatternTags {
   public static final class_6862<class_2582> APOSTOLIC_CROSS_PATTERN = create("apostolic_cross");
   public static final class_6862<class_2582> BOWL_PATTERN = create("bowl");
   public static final class_6862<class_2582> BULL_PATTERN = create("bull");
   public static final class_6862<class_2582> CHESS_PATTERN = create("chess");
   public static final class_6862<class_2582> CRUSADER_CROSS_PATTERN = create("crusader_cross");
   public static final class_6862<class_2582> DRAGON_PATTERN = create("dragon");
   public static final class_6862<class_2582> EAGLE_PATTERN = create("eagle");
   public static final class_6862<class_2582> HORSE_PATTERN = create("horse");
   public static final class_6862<class_2582> LILY_PATTERN = create("lily");
   public static final class_6862<class_2582> LION1_PATTERN = create("lion1");
   public static final class_6862<class_2582> LION2_PATTERN = create("lion2");
   public static final class_6862<class_2582> ORTHODOX_CROSS_PATTERN = create("orthodox_cross");
   public static final class_6862<class_2582> SNAKE_PATTERN = create("snake");
   public static final class_6862<class_2582> SUN_PATTERN = create("sun");
   public static final class_6862<class_2582> SWORDS_PATTERN = create("swords");
   public static final class_6862<class_2582> TOWER_PATTERN = create("tower");
   public static final class_6862<class_2582> TREE_PATTERN = create("tree");
   public static final class_6862<class_2582> TWOHEADED_EAGLE_PATTERN = create("two_headed_eagle");

   private static class_6862<class_2582> create(String name) {
      return class_6862.method_40092(class_7924.field_41252, class_2960.method_60655("magistuarmory", "pattern_item/" + name));
   }
}
