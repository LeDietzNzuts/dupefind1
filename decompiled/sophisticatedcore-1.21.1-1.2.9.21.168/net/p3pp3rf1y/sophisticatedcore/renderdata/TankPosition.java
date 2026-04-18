package net.p3pp3rf1y.sophisticatedcore.renderdata;

import java.util.Locale;
import net.minecraft.class_3542;

public enum TankPosition implements class_3542 {
   LEFT,
   RIGHT;

   public String method_15434() {
      return this.name().toLowerCase(Locale.ENGLISH);
   }
}
