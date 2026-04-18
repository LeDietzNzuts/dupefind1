package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.impl.ModifierKeyCodeImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_3675;
import net.minecraft.class_3675.class_306;
import net.minecraft.class_3675.class_307;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public interface ModifierKeyCode {
   static ModifierKeyCode of(class_306 keyCode, Modifier modifier) {
      return new ModifierKeyCodeImpl().setKeyCodeAndModifier(keyCode, modifier);
   }

   static ModifierKeyCode copyOf(ModifierKeyCode code) {
      return of(code.getKeyCode(), code.getModifier());
   }

   static ModifierKeyCode unknown() {
      return of(class_3675.field_16237, Modifier.none());
   }

   class_306 getKeyCode();

   ModifierKeyCode setKeyCode(class_306 var1);

   default class_307 getType() {
      return this.getKeyCode().method_1442();
   }

   Modifier getModifier();

   ModifierKeyCode setModifier(Modifier var1);

   default ModifierKeyCode copy() {
      return copyOf(this);
   }

   default boolean matchesMouse(int button) {
      return !this.isUnknown() && this.getType() == class_307.field_1672 && this.getKeyCode().method_1444() == button && this.getModifier().matchesCurrent();
   }

   default boolean matchesKey(int keyCode, int scanCode) {
      if (this.isUnknown()) {
         return false;
      } else {
         return keyCode == class_3675.field_16237.method_1444()
            ? this.getType() == class_307.field_1671 && this.getKeyCode().method_1444() == scanCode && this.getModifier().matchesCurrent()
            : this.getType() == class_307.field_1668 && this.getKeyCode().method_1444() == keyCode && this.getModifier().matchesCurrent();
      }
   }

   default boolean matchesCurrentMouse() {
      return !this.isUnknown() && this.getType() == class_307.field_1672 && this.getModifier().matchesCurrent()
         ? GLFW.glfwGetMouseButton(class_310.method_1551().method_22683().method_4490(), this.getKeyCode().method_1444()) == 1
         : false;
   }

   default boolean matchesCurrentKey() {
      return !this.isUnknown()
         && this.getType() == class_307.field_1668
         && this.getModifier().matchesCurrent()
         && class_3675.method_15987(class_310.method_1551().method_22683().method_4490(), this.getKeyCode().method_1444());
   }

   default ModifierKeyCode setKeyCodeAndModifier(class_306 keyCode, Modifier modifier) {
      this.setKeyCode(keyCode);
      this.setModifier(modifier);
      return this;
   }

   default ModifierKeyCode clearModifier() {
      return this.setModifier(Modifier.none());
   }

   @Override
   String toString();

   class_2561 getLocalizedName();

   default boolean isUnknown() {
      return this.getKeyCode().equals(class_3675.field_16237);
   }
}
