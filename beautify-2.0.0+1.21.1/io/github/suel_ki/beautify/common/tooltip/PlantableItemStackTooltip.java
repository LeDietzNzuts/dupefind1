package io.github.suel_ki.beautify.common.tooltip;

import java.util.List;
import net.minecraft.class_1799;
import net.minecraft.class_5632;

public record PlantableItemStackTooltip(List<class_1799> plants) implements class_5632 {
   public int getColumns() {
      int maxColumns = 9;
      int columns = this.plants.size() / 3;
      if (this.plants.size() % 3 != 0) {
         columns++;
      }

      return Math.min(columns, maxColumns);
   }
}
