package net.kikoz.mcwfurnitures.objects;

import java.util.List;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_3956;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import org.jetbrains.annotations.Nullable;

public class CraftingItem extends class_1792 {
   public CraftingItem(class_1793 settings) {
      super(settings);
   }

   public void method_7851(class_1799 stack, class_9635 context, List<class_2561> tooltip, class_1836 type) {
      tooltip.add(class_2561.method_43471("mcwfurnitures.furnitureitem.desc"));
   }

   public int getFuelTime(class_1799 itemStack, @Nullable class_3956<?> recipeType) {
      return 300;
   }
}
