package com.magistuarmory.item;

import com.magistuarmory.component.ModDataComponents;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_9279;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_1738.class_8051;
import org.jetbrains.annotations.NotNull;

public interface ArmorDecoration extends class_1935 {
   class_2960 getResourceLocation();

   class_2487 getCompoundTag(class_1799 var1);

   class_8051 method_48398();

   boolean isApplicableForDecoration(class_1799 var1);

   default void decorate(class_1799 armorstack, class_1799 decorationstack) {
      class_9279 data = (class_9279)armorstack.method_57824((class_9331)ModDataComponents.ARMOR_DECORATION.get());
      class_2499 listtag = ArmorDecorationItem.getDecorationTags(armorstack);
      if (data == null || listtag.isEmpty()) {
         armorstack.method_57379(
            class_9334.field_49631,
            class_2561.method_43469("magistuarmory.decorated", new Object[]{class_2561.method_43471(armorstack.method_7964().getString())})
         );
      }

      class_2487 compoundtag1 = data != null ? data.method_57461() : new class_2487();
      class_2487 decorationdata = this.getCompoundTag(decorationstack);
      String name = decorationdata.method_10558("name");
      boolean set = false;

      for (int i = 0; i < listtag.size(); i++) {
         if (listtag.method_10602(i).method_10558("name").equals(name)) {
            listtag.method_10606(i, decorationdata);
            set = true;
            break;
         }
      }

      if (!set) {
         listtag.add(decorationdata);
      }

      compoundtag1.method_10566("Items", listtag);
      armorstack.method_57379((class_9331)ModDataComponents.ARMOR_DECORATION.get(), class_9279.method_57456(compoundtag1));
   }

   class_5601 createModelLocation();

   @NotNull
   default class_1792 method_8389() {
      return (class_1792)this;
   }
}
