package me.shedaniel.clothconfig2.api;

import java.util.List;
import me.shedaniel.math.Point;
import net.minecraft.class_2561;
import net.minecraft.class_5348;
import net.minecraft.class_5481;

public interface Tooltip {
   static Tooltip of(Point location, class_2561... text) {
      return QueuedTooltip.create(location, text);
   }

   static Tooltip of(Point location, class_5348... text) {
      return QueuedTooltip.create(location, text);
   }

   static Tooltip of(Point location, class_5481... text) {
      return QueuedTooltip.create(location, text);
   }

   Point getPoint();

   default int getX() {
      return this.getPoint().getX();
   }

   default int getY() {
      return this.getPoint().getY();
   }

   List<class_5481> getText();
}
