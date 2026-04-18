package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_3675;
import net.minecraft.class_3675.class_306;

@Environment(EnvType.CLIENT)
public class ModifierKeyCodeImpl implements ModifierKeyCode {
   private class_306 keyCode;
   private Modifier modifier;

   @Override
   public class_306 getKeyCode() {
      return this.keyCode;
   }

   @Override
   public Modifier getModifier() {
      return this.modifier;
   }

   @Override
   public ModifierKeyCode setKeyCode(class_306 keyCode) {
      this.keyCode = keyCode.method_1442().method_1447(keyCode.method_1444());
      if (keyCode.equals(class_3675.field_16237)) {
         this.setModifier(Modifier.none());
      }

      return this;
   }

   @Override
   public ModifierKeyCode setModifier(Modifier modifier) {
      this.modifier = Modifier.of(modifier.getValue());
      return this;
   }

   @Override
   public String toString() {
      return this.getLocalizedName().getString();
   }

   @Override
   public class_2561 getLocalizedName() {
      class_2561 base = this.keyCode.method_27445();
      if (this.modifier.hasShift()) {
         base = class_2561.method_43469("modifier.cloth-config.shift", new Object[]{base});
      }

      if (this.modifier.hasControl()) {
         base = class_2561.method_43469("modifier.cloth-config.ctrl", new Object[]{base});
      }

      if (this.modifier.hasAlt()) {
         base = class_2561.method_43469("modifier.cloth-config.alt", new Object[]{base});
      }

      return base;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else {
         return !(o instanceof ModifierKeyCode that) ? false : this.keyCode.equals(that.getKeyCode()) && this.modifier.equals(that.getModifier());
      }
   }

   @Override
   public int hashCode() {
      int result = this.keyCode != null ? this.keyCode.hashCode() : 0;
      return 31 * result + (this.modifier != null ? this.modifier.hashCode() : 0);
   }
}
