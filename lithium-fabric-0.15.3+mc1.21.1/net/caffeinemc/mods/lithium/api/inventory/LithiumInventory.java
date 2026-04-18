package net.caffeinemc.mods.lithium.api.inventory;

import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2621;
import net.minecraft.class_7265;

public interface LithiumInventory extends class_1263 {
   class_2371<class_1799> getInventoryLithium();

   void setInventoryLithium(class_2371<class_1799> var1);

   default void generateLootLithium() {
      if (this instanceof class_2621) {
         ((class_2621)this).method_54873(null);
      }

      if (this instanceof class_7265) {
         ((class_7265)this).method_42291(null);
      }
   }
}
