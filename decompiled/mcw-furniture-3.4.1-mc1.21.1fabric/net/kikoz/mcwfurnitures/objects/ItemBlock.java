package net.kikoz.mcwfurnitures.objects;

import net.minecraft.class_1747;
import net.minecraft.class_1799;
import net.minecraft.class_2248;
import net.minecraft.class_3956;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.Nullable;

public class ItemBlock extends class_1747 {
   public ItemBlock(class_2248 block, class_1793 prop) {
      super(block, prop);
   }

   public int getFuelTime(class_1799 itemStack, @Nullable class_3956<?> recipeType) {
      return 300;
   }
}
