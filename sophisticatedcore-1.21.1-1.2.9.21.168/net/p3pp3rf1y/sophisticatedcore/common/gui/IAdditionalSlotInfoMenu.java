package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.Map;
import java.util.Set;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_6880;

public interface IAdditionalSlotInfoMenu {
   void updateAdditionalSlotInfo(Set<Integer> var1, Map<Integer, Integer> var2, Set<Integer> var3, Map<Integer, class_6880<class_1792>> var4);

   void updateEmptySlotIcons(Map<class_2960, Set<Integer>> var1);
}
