package com.magistuarmory.item;

import com.google.common.collect.Lists;
import com.magistuarmory.client.render.model.ModModels;
import com.magistuarmory.component.ModDataComponents;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1738;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_9279;
import net.minecraft.class_9331;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import org.jetbrains.annotations.Nullable;

public class ArmorDecorationItem extends class_1792 implements ArmorDecoration {
   class_2960 location;
   class_8051 armorType;

   public ArmorDecorationItem(class_2960 location, class_1793 properties, class_8051 armorType) {
      super(properties.method_7889(1));
      this.location = location;
      this.armorType = armorType;
   }

   @Override
   public class_2960 getResourceLocation() {
      return this.location;
   }

   @Override
   public class_8051 method_48398() {
      return this.armorType;
   }

   public static List<ArmorDecorationItem.DecorationInfo> createDecorations(@Nullable class_2499 listtag) {
      List<ArmorDecorationItem.DecorationInfo> list = Lists.newArrayList();
      if (listtag != null) {
         for (int i = 0; i < listtag.size(); i++) {
            class_2487 tag = listtag.method_10602(i);
            String name = tag.method_10558("name");
            boolean dyeable = tag.method_10577("dyeable");
            int j = tag.method_10550("color");
            list.add(new ArmorDecorationItem.DecorationInfo(name, dyeable, j));
         }
      }

      return list;
   }

   @Override
   public class_2487 getCompoundTag(class_1799 stack) {
      class_2487 compoundnbt = new class_2487();
      compoundnbt.method_10582("name", this.location.toString());
      compoundnbt.method_10556("dyeable", false);
      compoundnbt.method_10569("color", 1);
      return compoundnbt;
   }

   @Override
   public boolean isApplicableForDecoration(class_1799 stack) {
      return getDecorationTags(stack).size() < 8 && stack.method_7909() instanceof class_1738 armor && this.method_48398() == armor.method_48398();
   }

   public static class_2499 getDecorationTags(class_1799 stack) {
      class_9279 data = (class_9279)stack.method_57824((class_9331)ModDataComponents.ARMOR_DECORATION.get());
      return data == null ? new class_2499() : data.method_57461().method_10554("Items", 10);
   }

   public void method_7851(class_1799 stack, class_9635 tooltipContext, List<class_2561> tooltip, class_1836 flag) {
      super.method_7851(stack, tooltipContext, tooltip, flag);
      tooltip.add(
         class_2561.method_43471("magistuarmory.armor_decoration." + this.method_48398().method_48400() + ".description")
            .method_27696(class_2583.field_24360.method_10977(class_124.field_1078).method_10978(true))
      );
   }

   @Environment(EnvType.CLIENT)
   @Override
   public class_5601 createModelLocation() {
      return ModModels.createDecorationLocation(this.location);
   }

   public record DecorationInfo(String name, boolean dyeable, int color) {
      public class_2960 location() {
         class_2960 loc = class_2960.method_60654(this.name);
         return class_2960.method_60655(!loc.method_12836().equals("minecraft") ? loc.method_12836() : "magistuarmory", loc.method_12832());
      }
   }
}
