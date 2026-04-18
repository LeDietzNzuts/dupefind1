package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_3956;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;

public class FuelItem extends class_1792 {
   public final int burnTime;

   public FuelItem(class_1793 properties) {
      this(properties, 100);
   }

   public FuelItem(class_1793 properties, int burnTime) {
      super(properties);
      this.burnTime = burnTime;
      FuelRegistry.INSTANCE.add(this, this.burnTime);
   }

   public int getBurnTime(class_1799 stack, @Nullable class_3956<?> recipeType) {
      return this.burnTime;
   }
}
