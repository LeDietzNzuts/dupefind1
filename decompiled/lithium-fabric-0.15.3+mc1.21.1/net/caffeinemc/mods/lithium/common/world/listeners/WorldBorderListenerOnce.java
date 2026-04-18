package net.caffeinemc.mods.lithium.common.world.listeners;

import net.minecraft.class_2780;
import net.minecraft.class_2784;

public interface WorldBorderListenerOnce extends class_2780 {
   void lithium$onWorldBorderShapeChange(class_2784 var1);

   default void onAreaReplaced(class_2784 border) {
      this.lithium$onWorldBorderShapeChange(border);
   }

   default void method_11934(class_2784 border, double size) {
      this.lithium$onWorldBorderShapeChange(border);
   }

   default void method_11931(class_2784 border, double fromSize, double toSize, long time) {
      this.lithium$onWorldBorderShapeChange(border);
   }

   default void method_11930(class_2784 border, double centerX, double centerZ) {
      this.lithium$onWorldBorderShapeChange(border);
   }

   default void method_11932(class_2784 border, int warningTime) {
   }

   default void method_11933(class_2784 border, int warningBlockDistance) {
   }

   default void method_11929(class_2784 border, double damagePerBlock) {
   }

   default void method_11935(class_2784 border, double safeZoneRadius) {
   }
}
