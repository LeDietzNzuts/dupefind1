package com.magistuarmory.item.armor;

import com.magistuarmory.client.render.ModRender;
import java.util.Optional;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1738;
import net.minecraft.class_5601;
import net.minecraft.class_572;
import net.minecraft.class_1738.class_8051;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_5617.class_5618;

public class MedievalArmorItem extends class_1738 implements ISurcoat {
   @Nullable
   protected class_572<class_1309> model = null;
   private final ArmorType armortype;

   public MedievalArmorItem(ArmorType armortype, class_8051 type, class_1793 properties) {
      super(armortype.getMaterial(), type, properties.method_7895(armortype.getDurabilityForType(type)).method_7889(1));
      this.armortype = armortype;
   }

   public ArmorType getArmorType() {
      return this.armortype;
   }

   @Deprecated(forRemoval = true)
   @Environment(EnvType.CLIENT)
   public void loadModel(class_5618 context) {
      Optional<class_5601> location = this.armortype.getModelLocation();
      this.model = location.<class_572<class_1309>>map(l -> new class_572(context.method_32167(l)))
         .orElseGet(() -> this.method_48398() == class_8051.field_41936 ? ModRender.INNER_ARMOR : ModRender.OUTER_ARMOR);
   }

   @Environment(EnvType.CLIENT)
   public void setModel(class_572<class_1309> model) {
      this.model = model;
   }

   @Environment(EnvType.CLIENT)
   public class_572<? extends class_1309> getArmorModel(class_1304 slot, class_572<? extends class_1309> _default) {
      return slot == this.field_41933.method_48399() && this.model != null ? this.model : _default;
   }
}
