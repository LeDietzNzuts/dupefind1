package vectorwing.farmersdelight.common.tag;

import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7924;

public class CompatibilityTags {
   public static final String CREATE = "create";
   public static final class_6862<class_2248> CREATE_PASSIVE_BOILER_HEATERS = externalBlockTag("create", "passive_boiler_heaters");
   public static final class_6862<class_2248> CREATE_BRITTLE = externalBlockTag("create", "brittle");
   public static final class_6862<class_1792> CREATE_UPRIGHT_ON_BELT = externalItemTag("create", "upright_on_belt");
   public static final String CREATE_CA = "createaddition";
   public static final class_6862<class_1792> CREATE_CA_PLANT_FOODS = externalItemTag("createaddition", "plant_foods");
   public static final class_6862<class_1792> CREATE_CA_PLANTS = externalItemTag("createaddition", "plants");
   public static final String ORIGINS = "origins";
   public static final class_6862<class_1792> ORIGINS_MEAT = externalItemTag("origins", "meat");
   public static final String SERENE_SEASONS = "sereneseasons";
   public static final class_6862<class_2248> SERENE_SEASONS_AUTUMN_CROPS_BLOCK = externalBlockTag("sereneseasons", "autumn_crops");
   public static final class_6862<class_2248> SERENE_SEASONS_SPRING_CROPS_BLOCK = externalBlockTag("sereneseasons", "spring_crops");
   public static final class_6862<class_2248> SERENE_SEASONS_SUMMER_CROPS_BLOCK = externalBlockTag("sereneseasons", "summer_crops");
   public static final class_6862<class_2248> SERENE_SEASONS_WINTER_CROPS_BLOCK = externalBlockTag("sereneseasons", "winter_crops");
   public static final class_6862<class_2248> SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS = externalBlockTag("sereneseasons", "unbreakable_infertile_crops");
   public static final class_6862<class_1792> SERENE_SEASONS_AUTUMN_CROPS = externalItemTag("sereneseasons", "autumn_crops");
   public static final class_6862<class_1792> SERENE_SEASONS_SPRING_CROPS = externalItemTag("sereneseasons", "spring_crops");
   public static final class_6862<class_1792> SERENE_SEASONS_SUMMER_CROPS = externalItemTag("sereneseasons", "summer_crops");
   public static final class_6862<class_1792> SERENE_SEASONS_WINTER_CROPS = externalItemTag("sereneseasons", "winter_crops");
   public static final String TINKERS_CONSTRUCT = "tconstruct";
   public static final class_6862<class_1792> TINKERS_CONSTRUCT_SEEDS = externalItemTag("tconstruct", "seeds");

   private static class_6862<class_1792> externalItemTag(String modId, String path) {
      return class_6862.method_40092(class_7924.field_41197, class_2960.method_60655(modId, path));
   }

   private static class_6862<class_2248> externalBlockTag(String modId, String path) {
      return class_6862.method_40092(class_7924.field_41254, class_2960.method_60655(modId, path));
   }
}
