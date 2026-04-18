package vectorwing.farmersdelight.common.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.class_1922;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2380;
import net.minecraft.class_2680;
import net.minecraft.class_4970.class_2251;

public class StrawBaleBlock extends class_2380 {
   public StrawBaleBlock(class_2251 properties) {
      super(properties);
      FlammableBlockRegistry.getDefaultInstance().add(this, this.getFlammability(null, null, null, null), this.getFireSpreadSpeed(null, null, null, null));
   }

   public int getFireSpreadSpeed(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 60;
   }

   public int getFlammability(class_2680 state, class_1922 world, class_2338 pos, class_2350 face) {
      return 20;
   }
}
