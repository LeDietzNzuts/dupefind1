package vectorwing.farmersdelight.common;

import com.google.common.collect.ImmutableMap.Builder;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1792;
import net.minecraft.class_1802;
import net.minecraft.class_4174;
import net.minecraft.class_4174.class_4175;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FoodValues {
   public static final int BRIEF_DURATION = 600;
   public static final int SHORT_DURATION = 1200;
   public static final int MEDIUM_DURATION = 3600;
   public static final int LONG_DURATION = 6000;
   public static final class_4174 CABBAGE = new class_4175().method_19238(2).method_19237(0.4F).method_19242();
   public static final class_4174 TOMATO = new class_4175().method_19238(1).method_19237(0.3F).method_19242();
   public static final class_4174 ONION = new class_4175().method_19238(2).method_19237(0.4F).method_19242();
   public static final class_4174 APPLE_CIDER = new class_4175()
      .method_19240()
      .method_19239(new class_1293(class_1294.field_5898, 1200, 0), 1.0F)
      .method_19242();
   public static final class_4174 FRIED_EGG = new class_4175().method_19238(4).method_19237(0.4F).method_19242();
   public static final class_4174 TOMATO_SAUCE = new class_4175().method_19238(4).method_19237(0.4F).method_19242();
   public static final class_4174 WHEAT_DOUGH = new class_4175()
      .method_19238(2)
      .method_19237(0.3F)
      .method_19239(new class_1293(class_1294.field_5903, 600, 0), 0.3F)
      .method_19242();
   public static final class_4174 RAW_PASTA = new class_4175()
      .method_19238(2)
      .method_19237(0.3F)
      .method_19239(new class_1293(class_1294.field_5903, 600, 0), 0.3F)
      .method_19242();
   public static final class_4174 PIE_CRUST = new class_4175().method_19238(2).method_19237(0.2F).method_19242();
   public static final class_4174 PUMPKIN_SLICE = new class_4175().method_19238(3).method_19237(0.3F).method_19242();
   public static final class_4174 CABBAGE_LEAF = new class_4175().method_19238(1).method_19237(0.4F).method_19241().method_19242();
   public static final class_4174 MINCED_BEEF = new class_4175().method_19238(2).method_19237(0.3F).method_19241().method_19242();
   public static final class_4174 BEEF_PATTY = new class_4175().method_19238(4).method_19237(0.8F).method_19241().method_19242();
   public static final class_4174 CHICKEN_CUTS = new class_4175()
      .method_19238(1)
      .method_19237(0.3F)
      .method_19239(new class_1293(class_1294.field_5903, 600, 0), 0.3F)
      .method_19241()
      .method_19242();
   public static final class_4174 COOKED_CHICKEN_CUTS = new class_4175().method_19238(3).method_19237(0.6F).method_19241().method_19242();
   public static final class_4174 BACON = new class_4175().method_19238(2).method_19237(0.3F).method_19241().method_19242();
   public static final class_4174 COOKED_BACON = new class_4175().method_19238(4).method_19237(0.8F).method_19241().method_19242();
   public static final class_4174 COD_SLICE = new class_4175().method_19238(1).method_19237(0.1F).method_19241().method_19242();
   public static final class_4174 COOKED_COD_SLICE = new class_4175().method_19238(3).method_19237(0.5F).method_19241().method_19242();
   public static final class_4174 SALMON_SLICE = new class_4175().method_19238(1).method_19237(0.1F).method_19241().method_19242();
   public static final class_4174 COOKED_SALMON_SLICE = new class_4175().method_19238(3).method_19237(0.8F).method_19241().method_19242();
   public static final class_4174 MUTTON_CHOPS = new class_4175().method_19238(1).method_19237(0.3F).method_19241().method_19242();
   public static final class_4174 COOKED_MUTTON_CHOPS = new class_4175().method_19238(3).method_19237(0.8F).method_19241().method_19242();
   public static final class_4174 HAM = new class_4175().method_19238(5).method_19237(0.3F).method_19242();
   public static final class_4174 SMOKED_HAM = new class_4175().method_19238(10).method_19237(0.8F).method_19242();
   public static final class_4174 POPSICLE = new class_4175().method_19238(3).method_19237(0.2F).method_19241().method_19240().method_19242();
   public static final class_4174 COOKIES = new class_4175().method_19238(2).method_19237(0.1F).method_19241().method_19242();
   public static final class_4174 CAKE_SLICE = new class_4175()
      .method_19238(2)
      .method_19237(0.1F)
      .method_19241()
      .method_19239(new class_1293(class_1294.field_5904, 400, 0, false, false), 1.0F)
      .method_19242();
   public static final class_4174 PIE_SLICE = new class_4175()
      .method_19238(3)
      .method_19237(0.3F)
      .method_19241()
      .method_19239(new class_1293(class_1294.field_5904, 600, 0, false, false), 1.0F)
      .method_19242();
   public static final class_4174 FRUIT_SALAD = new class_4175()
      .method_19238(6)
      .method_19237(0.6F)
      .method_19239(new class_1293(class_1294.field_5924, 100, 0), 1.0F)
      .method_19242();
   public static final class_4174 GLOW_BERRY_CUSTARD = new class_4175()
      .method_19238(7)
      .method_19237(0.6F)
      .method_19240()
      .method_19239(new class_1293(class_1294.field_5912, 100, 0), 1.0F)
      .method_19242();
   public static final class_4174 MIXED_SALAD = new class_4175()
      .method_19238(6)
      .method_19237(0.6F)
      .method_19239(new class_1293(class_1294.field_5924, 100, 0), 1.0F)
      .method_19242();
   public static final class_4174 NETHER_SALAD = new class_4175()
      .method_19238(5)
      .method_19237(0.4F)
      .method_19239(new class_1293(class_1294.field_5916, 240, 0), 0.3F)
      .method_19242();
   public static final class_4174 BARBECUE_STICK = new class_4175().method_19238(8).method_19237(0.9F).method_19242();
   public static final class_4174 EGG_SANDWICH = new class_4175().method_19238(8).method_19237(0.8F).method_19242();
   public static final class_4174 CHICKEN_SANDWICH = new class_4175().method_19238(10).method_19237(0.8F).method_19242();
   public static final class_4174 HAMBURGER = new class_4175().method_19238(11).method_19237(0.8F).method_19242();
   public static final class_4174 BACON_SANDWICH = new class_4175().method_19238(10).method_19237(0.8F).method_19242();
   public static final class_4174 MUTTON_WRAP = new class_4175().method_19238(10).method_19237(0.8F).method_19242();
   public static final class_4174 DUMPLINGS = new class_4175().method_19238(8).method_19237(0.8F).method_19242();
   public static final class_4174 STUFFED_POTATO = new class_4175().method_19238(10).method_19237(0.7F).method_19242();
   public static final class_4174 CABBAGE_ROLLS = new class_4175().method_19238(5).method_19237(0.5F).method_19242();
   public static final class_4174 SALMON_ROLL = new class_4175().method_19238(7).method_19237(0.6F).method_19242();
   public static final class_4174 COD_ROLL = new class_4175().method_19238(7).method_19237(0.6F).method_19242();
   public static final class_4174 KELP_ROLL = new class_4174(12, 12.0F, false, 2.4F, Optional.empty(), List.of());
   public static final class_4174 KELP_ROLL_SLICE = new class_4175().method_19238(6).method_19237(0.5F).method_19241().method_19242();
   public static final class_4174 COOKED_RICE = new class_4175().method_19238(6).method_19237(0.4F).method_19239(comfort(600), 1.0F).method_19242();
   public static final class_4174 BONE_BROTH = new class_4175().method_19238(8).method_19237(0.7F).method_19239(comfort(1200), 1.0F).method_19242();
   public static final class_4174 BEEF_STEW = new class_4175().method_19238(12).method_19237(0.8F).method_19239(comfort(3600), 1.0F).method_19242();
   public static final class_4174 VEGETABLE_SOUP = new class_4175().method_19238(12).method_19237(0.8F).method_19239(comfort(3600), 1.0F).method_19242();
   public static final class_4174 FISH_STEW = new class_4175().method_19238(12).method_19237(0.8F).method_19239(comfort(3600), 1.0F).method_19242();
   public static final class_4174 CHICKEN_SOUP = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 FRIED_RICE = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 PUMPKIN_SOUP = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 BAKED_COD_STEW = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 NOODLE_SOUP = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 BACON_AND_EGGS = new class_4175().method_19238(10).method_19237(0.6F).method_19239(nourishment(1200), 1.0F).method_19242();
   public static final class_4174 RATATOUILLE = new class_4175().method_19238(10).method_19237(0.6F).method_19239(nourishment(1200), 1.0F).method_19242();
   public static final class_4174 STEAK_AND_POTATOES = new class_4175()
      .method_19238(12)
      .method_19237(0.8F)
      .method_19239(nourishment(3600), 1.0F)
      .method_19242();
   public static final class_4174 PASTA_WITH_MEATBALLS = new class_4175()
      .method_19238(12)
      .method_19237(0.8F)
      .method_19239(nourishment(3600), 1.0F)
      .method_19242();
   public static final class_4174 PASTA_WITH_MUTTON_CHOP = new class_4175()
      .method_19238(12)
      .method_19237(0.8F)
      .method_19239(nourishment(3600), 1.0F)
      .method_19242();
   public static final class_4174 MUSHROOM_RICE = new class_4175().method_19238(12).method_19237(0.8F).method_19239(nourishment(3600), 1.0F).method_19242();
   public static final class_4174 ROASTED_MUTTON_CHOPS = new class_4175()
      .method_19238(14)
      .method_19237(0.75F)
      .method_19239(nourishment(6000), 1.0F)
      .method_19242();
   public static final class_4174 VEGETABLE_NOODLES = new class_4175()
      .method_19238(14)
      .method_19237(0.75F)
      .method_19239(nourishment(6000), 1.0F)
      .method_19242();
   public static final class_4174 SQUID_INK_PASTA = new class_4175().method_19238(14).method_19237(0.75F).method_19239(nourishment(6000), 1.0F).method_19242();
   public static final class_4174 GRILLED_SALMON = new class_4175().method_19238(14).method_19237(0.75F).method_19239(nourishment(3600), 1.0F).method_19242();
   public static final class_4174 ROAST_CHICKEN = new class_4175().method_19238(14).method_19237(0.75F).method_19239(nourishment(6000), 1.0F).method_19242();
   public static final class_4174 STUFFED_PUMPKIN = new class_4175().method_19238(14).method_19237(0.75F).method_19239(comfort(6000), 1.0F).method_19242();
   public static final class_4174 HONEY_GLAZED_HAM = new class_4175().method_19238(14).method_19237(0.75F).method_19239(nourishment(6000), 1.0F).method_19242();
   public static final class_4174 SHEPHERDS_PIE = new class_4175().method_19238(14).method_19237(0.75F).method_19239(nourishment(6000), 1.0F).method_19242();
   public static final class_4174 DOG_FOOD = new class_4175().method_19238(4).method_19237(0.2F).method_19242();
   public static final Map<class_1792, class_4174> VANILLA_SOUP_EFFECTS = new Builder()
      .put(class_1802.field_8208, new class_4175().method_19239(comfort(3600), 1.0F).method_19242())
      .put(class_1802.field_8515, new class_4175().method_19239(comfort(3600), 1.0F).method_19242())
      .put(class_1802.field_8308, new class_4175().method_19239(comfort(6000), 1.0F).method_19242())
      .build();
   public static final class_4174 RABBIT_STEW_BUFF = new class_4175()
      .method_19238(14)
      .method_19237(0.75F)
      .method_19239(comfort(6000), 1.0F)
      .method_60500(class_1802.field_8428)
      .method_19242();

   public static class_1293 comfort(int duration) {
      return new class_1293(ModEffects.COMFORT, duration, 0, false, false);
   }

   public static class_1293 nourishment(int duration) {
      return new class_1293(ModEffects.NOURISHMENT, duration, 0, false, false);
   }
}
