package me.shedaniel.math.impl;

import me.shedaniel.math.FloatingPoint;
import me.shedaniel.math.Point;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;

@Environment(EnvType.CLIENT)
public class PointHelper {
   public static Point ofMouse() {
      class_310 client = class_310.method_1551();
      double mx = client.field_1729.method_1603() * client.method_22683().method_4486() / client.method_22683().method_4480();
      double my = client.field_1729.method_1604() * client.method_22683().method_4502() / client.method_22683().method_4507();
      return new Point(mx, my);
   }

   public static FloatingPoint ofFloatingMouse() {
      class_310 client = class_310.method_1551();
      double mx = client.field_1729.method_1603() * client.method_22683().method_4486() / client.method_22683().method_4480();
      double my = client.field_1729.method_1604() * client.method_22683().method_4502() / client.method_22683().method_4507();
      return new FloatingPoint(mx, my);
   }

   public static int getMouseX() {
      return ofMouse().x;
   }

   public static int getMouseY() {
      return ofMouse().y;
   }

   public static double getMouseFloatingX() {
      return ofFloatingMouse().x;
   }

   public static double getMouseFloatingY() {
      return ofFloatingMouse().y;
   }
}
