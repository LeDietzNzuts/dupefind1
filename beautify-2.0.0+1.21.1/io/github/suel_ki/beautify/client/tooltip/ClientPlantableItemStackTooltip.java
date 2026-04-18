package io.github.suel_ki.beautify.client.tooltip;

import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import org.jetbrains.annotations.Nullable;

public class ClientPlantableItemStackTooltip implements class_5684 {
   private static final class_2960 TEXTURE_LOCATION = class_2960.method_60656("container/bundle/slot");
   private static final class_2561 TEXT = class_2561.method_43471("tooltip.beautify.plantable").method_27692(class_124.field_1060);
   private static final int SLOT_SIZE = 18;
   private final int columns;
   private final List<class_1799> plants;

   public ClientPlantableItemStackTooltip(PlantableItemStackTooltip tooltip) {
      this.columns = tooltip.getColumns();
      this.plants = tooltip.plants();
   }

   @Nullable
   public static class_5684 get(class_5632 component) {
      return component instanceof PlantableItemStackTooltip tooltip ? new ClientPlantableItemStackTooltip(tooltip) : null;
   }

   public void method_32666(class_327 font, int tooltipX, int tooltipY, class_332 graphics) {
      int slotSize = 18;
      int x = tooltipX;
      int y = tooltipY + 9 + 3;

      for (class_1799 plant : this.plants) {
         graphics.method_52707(TEXTURE_LOCATION, x - 1, y - 1, 0, 18, 20);
         graphics.method_51427(plant, x, y);
         graphics.method_51431(font, plant, x, y);
         x += slotSize;
         if (x >= tooltipX + slotSize * this.columns) {
            x = tooltipX;
            y += slotSize;
         }
      }

      graphics.method_27535(font, TEXT, tooltipX, tooltipY, 0);
   }

   public int method_32661() {
      int columns = this.columns;
      int rows = (this.plants.size() + columns - 1) / columns;
      return rows * 18 + 15;
   }

   public int method_32664(class_327 font) {
      int width = this.columns * 18;
      int textWidth = font.method_27525(TEXT);
      return Math.max(width, textWidth);
   }
}
