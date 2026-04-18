package com.magistuarmory.item.armor;

import com.magistuarmory.api.client.render.model.ModModelsProvider;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ArmorDecorationItem;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import org.jetbrains.annotations.NotNull;

public class DyeableWearableArmorDecorationItem extends DyeableMedievalArmorItem implements ArmorDecoration {
   public DyeableWearableArmorDecorationItem(ArmorType material, class_8051 type, class_1793 properties, int defaultcolor) {
      super(material, type, properties, defaultcolor);
   }

   @Override
   public class_2960 getResourceLocation() {
      return this.getArmorType().getLocation();
   }

   @Override
   public class_2487 getCompoundTag(class_1799 stack) {
      class_2487 compoundnbt = new class_2487();
      compoundnbt.method_10582("name", this.getResourceLocation().toString());
      compoundnbt.method_10556("dyeable", true);
      compoundnbt.method_10569("color", this.getColor(stack));
      return compoundnbt;
   }

   @NotNull
   @Override
   public class_8051 method_48398() {
      return this.field_41933;
   }

   @Override
   public boolean isApplicableForDecoration(class_1799 stack) {
      return stack.method_7909() != this
         && ArmorDecorationItem.getDecorationTags(stack).size() < 8
         && stack.method_7909() instanceof class_1738 armor
         && this.method_48398() == armor.method_48398();
   }

   @Override
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
      return ModModelsProvider.createDecorationLocation(this.getResourceLocation());
   }
}
