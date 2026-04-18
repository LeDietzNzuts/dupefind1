package net.p3pp3rf1y.sophisticatedbackpacks.api;

import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public interface IBlockToolSwapUpgrade {
   default boolean canProcessBlockInteract() {
      return true;
   }

   boolean onBlockInteract(class_1937 var1, class_2338 var2, class_2680 var3, class_1657 var4);
}
