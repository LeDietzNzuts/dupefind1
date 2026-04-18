package vectorwing.farmersdelight.common.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_3956;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;

public class FuelBlockItem extends class_1747 {
   public final int burnTime;

   public FuelBlockItem(class_2248 block, class_1793 properties) {
      this(block, properties, 100);
   }

   public FuelBlockItem(class_2248 block, class_1793 properties, int burnTime) {
      super(block, properties);
      this.burnTime = burnTime;
      FuelRegistry.INSTANCE.add(this, burnTime);
   }

   public int getBurnTime(class_1799 stack, @Nullable class_3956<?> recipeType) {
      return this.burnTime;
   }
}
