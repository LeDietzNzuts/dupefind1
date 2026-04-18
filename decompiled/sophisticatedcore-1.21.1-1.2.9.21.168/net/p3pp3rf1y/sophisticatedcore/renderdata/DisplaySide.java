package net.p3pp3rf1y.sophisticatedcore.renderdata;

import com.google.common.collect.ImmutableMap.Builder;
import java.util.Map;
import net.minecraft.class_3542;

public enum DisplaySide implements class_3542 {
   FRONT("front"),
   LEFT("left"),
   RIGHT("right");

   private final String name;
   private static final Map<String, DisplaySide> NAME_VALUES;
   private static final DisplaySide[] VALUES;

   private DisplaySide(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public DisplaySide next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public DisplaySide previous() {
      return VALUES[(this.ordinal() + VALUES.length - 1) % VALUES.length];
   }

   public static DisplaySide fromName(String name) {
      return NAME_VALUES.getOrDefault(name, FRONT);
   }

   static {
      Builder<String, DisplaySide> builder = new Builder();

      for (DisplaySide value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
