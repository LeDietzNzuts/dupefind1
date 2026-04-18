package net.kikoz.mcwfurnitures.init;

import net.kikoz.mcwfurnitures.objects.CraftingItem;
import net.minecraft.class_1792;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_1792.class_1793;

public class ItemInit {
   public static final class_1792 CABINET_DOOR = registerItem("cabinet_door", new CraftingItem(new class_1793()));
   public static final class_1792 CABINET_DRAWER = registerItem("cabinet_drawer", new CraftingItem(new class_1793()));

   private static class_1792 registerItem(String name, class_1792 item) {
      return (class_1792)class_2378.method_10230(class_7923.field_41178, class_2960.method_60655("mcwfurnitures", name), item);
   }

   public static void registerModItems() {
   }
}
