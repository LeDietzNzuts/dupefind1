package vectorwing.farmersdelight.data;

import net.minecraft.class_1792;
import net.minecraft.class_1887;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7871;
import net.minecraft.class_7891;
import net.minecraft.class_7924;
import net.minecraft.class_9274;
import net.minecraft.class_9704;
import net.minecraft.class_9726;
import net.minecraft.class_1887.class_9700;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.common.tag.ModTags;

public class ModEnchantments {
   public static final class_5321<class_1887> BACKSTABBING = key("backstabbing");

   public static void bootstrap(class_7891<class_1887> context) {
      class_7871<class_1792> items = context.method_46799(class_7924.field_41197);
      register(
         context,
         BACKSTABBING,
         class_1887.method_60030(
               class_1887.method_58442(
                  items.method_46735(ModTags.KNIFE_ENCHANTABLE),
                  5,
                  3,
                  class_1887.method_58441(15, 9),
                  class_1887.method_58441(50, 8),
                  2,
                  new class_9274[]{class_9274.field_49217}
               )
            )
            .method_60066(ModDataComponents.BACKSTABBING.get(), new class_9726(class_9704.method_60187(1.4F, 0.2F)))
      );
   }

   private static void register(class_7891<class_1887> context, class_5321<class_1887> key, class_9700 builder) {
      context.method_46838(key, builder.method_60060(key.method_29177()));
   }

   private static class_5321<class_1887> key(String name) {
      return class_5321.method_29179(class_7924.field_41265, class_2960.method_60655("farmersdelight", name));
   }
}
