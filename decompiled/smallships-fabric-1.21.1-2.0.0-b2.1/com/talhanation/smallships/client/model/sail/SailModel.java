package com.talhanation.smallships.client.model.sail;

import com.talhanation.smallships.world.entity.ship.Ship;
import java.util.Arrays;
import net.minecraft.class_2960;
import net.minecraft.class_583;

public abstract class SailModel extends class_583<Ship> {
   public static SailModel.Color getSailColor(String stringColor) {
      return Arrays.stream(SailModel.Color.values()).filter(color -> color.toString().equals(stringColor)).findAny().orElse(SailModel.Color.WHITE);
   }

   public static enum Color {
      WHITE(class_2960.method_60655("smallships", "textures/entity/sail/white_sail.png")),
      ORANGE(class_2960.method_60655("smallships", "textures/entity/sail/orange_sail.png")),
      MAGENTA(class_2960.method_60655("smallships", "textures/entity/sail/magenta_sail.png")),
      LIGHT_BLUE(class_2960.method_60655("smallships", "textures/entity/sail/light_blue_sail.png")),
      YELLOW(class_2960.method_60655("smallships", "textures/entity/sail/yellow_sail.png")),
      LIME(class_2960.method_60655("smallships", "textures/entity/sail/lime_sail.png")),
      PINK(class_2960.method_60655("smallships", "textures/entity/sail/pink_sail.png")),
      GRAY(class_2960.method_60655("smallships", "textures/entity/sail/gray_sail.png")),
      LIGHT_GRAY(class_2960.method_60655("smallships", "textures/entity/sail/light_gray_sail.png")),
      CYAN(class_2960.method_60655("smallships", "textures/entity/sail/cyan_sail.png")),
      PURPLE(class_2960.method_60655("smallships", "textures/entity/sail/purple_sail.png")),
      BLUE(class_2960.method_60655("smallships", "textures/entity/sail/blue_sail.png")),
      BROWN(class_2960.method_60655("smallships", "textures/entity/sail/brown_sail.png")),
      GREEN(class_2960.method_60655("smallships", "textures/entity/sail/green_sail.png")),
      RED(class_2960.method_60655("smallships", "textures/entity/sail/red_sail.png")),
      BLACK(class_2960.method_60655("smallships", "textures/entity/sail/black_sail.png"));

      public final class_2960 location;

      private Color(class_2960 location) {
         this.location = location;
      }

      @Override
      public String toString() {
         return super.toString().toLowerCase();
      }
   }
}
