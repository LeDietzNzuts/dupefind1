package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.List;
import net.minecraft.class_1263;
import net.minecraft.class_1735;
import net.minecraft.class_2960;
import net.minecraft.class_3956;

public interface ICraftingContainer {
   List<class_1735> getRecipeSlots();

   class_1263 getCraftMatrix();

   void setRecipeUsed(class_2960 var1);

   class_3956<?> getRecipeType();
}
