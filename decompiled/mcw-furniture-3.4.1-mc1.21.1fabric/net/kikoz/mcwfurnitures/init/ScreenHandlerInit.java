package net.kikoz.mcwfurnitures.init;

import net.kikoz.mcwfurnitures.storage.FurnitureScreenHandler;
import net.minecraft.class_2378;
import net.minecraft.class_2960;
import net.minecraft.class_3917;
import net.minecraft.class_7701;
import net.minecraft.class_7923;

public class ScreenHandlerInit {
   public static final class_3917<FurnitureScreenHandler> FURNITURE_SCREEN_HANDLER = new class_3917(FurnitureScreenHandler::new, class_7701.field_40182);

   public static void init() {
      class_2378.method_10230(class_7923.field_41187, class_2960.method_60655("mcwfurnitures", "furnitures"), FURNITURE_SCREEN_HANDLER);
   }
}
