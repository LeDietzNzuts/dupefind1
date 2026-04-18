package vectorwing.farmersdelight.common.block.state;

import net.minecraft.class_3542;
import org.jetbrains.annotations.NotNull;

public enum CookingPotSupport implements class_3542 {
   NONE("none"),
   TRAY("tray"),
   HANDLE("handle");

   private final String supportName;

   private CookingPotSupport(String name) {
      this.supportName = name;
   }

   @Override
   public String toString() {
      return this.method_15434();
   }

   @NotNull
   public String method_15434() {
      return this.supportName;
   }
}
