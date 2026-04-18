package com.magistuarmory.item.armor;

import com.magistuarmory.api.client.render.model.ModModelsProvider;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.ArmorDecorationItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import org.jetbrains.annotations.NotNull;

public class WearableArmorDecorationItem extends MedievalArmorItem implements ArmorDecoration {
   public WearableArmorDecorationItem(ArmorType material, class_8051 type, class_1793 properties) {
      super(material, type, properties);
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
      compoundnbt.method_10569("color", 1);
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

   @Environment(EnvType.CLIENT)
   @Override
   public class_5601 createModelLocation() {
      return ModModelsProvider.createDecorationLocation(this.getResourceLocation());
   }
}
