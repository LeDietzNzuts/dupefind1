package ewewukek.musketmod;

import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_1297;
import net.minecraft.class_173;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_181;
import net.minecraft.class_1887;
import net.minecraft.class_1890;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_47;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_8567;
import net.minecraft.class_9304;
import net.minecraft.class_6880.class_6882;
import net.minecraft.class_8567.class_8568;

public class VanillaHelper {
   public static void modifyLootTableItems(class_2960 location, class_47 context, Consumer<class_1799> adder) {
      if (location.method_12836().equals("minecraft")) {
         class_5321<class_52> key = class_5321.method_29179(class_7924.field_50079, MusketMod.resource(location.method_12832()));
         context.method_51183()
            .method_58561(class_7924.field_50079, key)
            .ifPresent(modTable -> ((class_52)modTable.comp_349()).method_328(context, class_52.method_332(context.method_299(), adder)));
      }
   }

   public static boolean canEnchant(class_6880<class_1887> enchantment, class_1799 stack) {
      if (stack.method_7909() instanceof GunItem && enchantment.method_40231() == class_6882.field_36446) {
         class_5321<class_1887> key = (class_5321<class_1887>)enchantment.method_40230().get();
         if (key.method_29177().method_12836().equals("minecraft")) {
            String tagPath = "enchantable/" + key.method_29177().method_12832();
            class_6862<class_1792> tag = class_6862.method_40092(class_7924.field_41197, MusketMod.resource(tagPath));
            return stack.method_31573(tag);
         }
      }

      return false;
   }

   public static int getEnchantmentLevel(class_1799 stack, class_5321<class_1887> enchantment) {
      class_9304 enchantments = class_1890.method_57532(stack);

      for (Entry<class_6880<class_1887>> entry : enchantments.method_57539()) {
         if (((class_6880)entry.getKey()).method_40225(enchantment)) {
            return entry.getIntValue();
         }
      }

      return 0;
   }

   public static class_1799 getRandomWeapon(class_1297 entity, class_5321<class_52> key) {
      class_52 table = entity.method_37908().method_8503().method_58576().method_58295(key);
      class_8567 params = new class_8568((class_3218)entity.method_37908())
         .method_51874(class_181.field_1226, entity)
         .method_51874(class_181.field_24424, entity.method_19538())
         .method_51875(class_173.field_20762);
      List<class_1799> items = table.method_51878(params);
      return items.size() > 0 ? items.get(0) : class_1799.field_8037;
   }
}
