package net.p3pp3rf1y.sophisticatedcore.api;

import java.util.Optional;
import net.minecraft.class_1799;
import net.minecraft.class_5632;
import net.minecraft.class_7225.class_7874;

public interface IStashStorageItem {
   Optional<class_5632> getInventoryTooltip(class_1799 var1);

   IStashStorageItem.StashResult getItemStashable(class_7874 var1, class_1799 var2, class_1799 var3);

   public static enum StashResult {
      MATCH_AND_SPACE,
      SPACE,
      NO_SPACE;
   }
}
