package noobanidus.mods.lootr.common.api.data.inventory;

import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_3908;
import net.minecraft.class_7225.class_7874;
import noobanidus.mods.lootr.common.api.MenuBuilder;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.ILootrSavedData;

public interface ILootrInventory extends class_1263, class_3908 {
   ILootrInfo getInfo();

   void setInfo(ILootrSavedData var1);

   default class_2561 method_5476() {
      class_2561 name = this.getInfo().getInfoDisplayName();
      return (class_2561)(name == null ? class_2561.method_43473() : name);
   }

   class_2371<class_1799> getInventoryContents();

   void setMenuBuilder(MenuBuilder var1);

   class_2487 saveToTag(class_7874 var1);
}
