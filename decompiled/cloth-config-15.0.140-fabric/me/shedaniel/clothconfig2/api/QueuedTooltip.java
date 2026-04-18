package me.shedaniel.clothconfig2.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import me.shedaniel.math.Point;
import net.minecraft.class_2477;
import net.minecraft.class_2561;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import org.jetbrains.annotations.ApiStatus.Internal;

public class QueuedTooltip implements Tooltip {
   private final Point location;
   private final List<class_5481> text;

   private QueuedTooltip(Point location, List<class_5481> text) {
      this.location = location;
      this.text = Collections.unmodifiableList(text);
   }

   public static QueuedTooltip create(Point location, List<class_2561> text) {
      return new QueuedTooltip(location, class_2477.method_10517().method_30933(text));
   }

   public static QueuedTooltip create(Point location, class_2561... text) {
      return create(location, Arrays.asList(text));
   }

   public static QueuedTooltip create(Point location, class_5481... text) {
      return new QueuedTooltip(location, Arrays.asList(text));
   }

   public static QueuedTooltip create(Point location, class_5348... text) {
      return new QueuedTooltip(location, class_2477.method_10517().method_30933(Arrays.asList(text)));
   }

   @Override
   public Point getPoint() {
      return this.location;
   }

   @Internal
   @Override
   public List<class_5481> getText() {
      return this.text;
   }
}
