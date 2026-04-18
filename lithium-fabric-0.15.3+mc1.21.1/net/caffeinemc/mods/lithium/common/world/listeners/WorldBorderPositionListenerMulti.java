package net.caffeinemc.mods.lithium.common.world.listeners;

import java.util.WeakHashMap;
import net.minecraft.class_2780;
import net.minecraft.class_2784;

public class WorldBorderPositionListenerMulti implements class_2780 {
   private final WeakHashMap<WorldBorderListenerOnce, Object> delegate = new WeakHashMap<>();

   public void add(WorldBorderListenerOnce listener) {
      this.delegate.put(listener, null);
   }

   public void onAreaReplaced(class_2784 border) {
      for (WorldBorderListenerOnce listener : this.delegate.keySet()) {
         listener.onAreaReplaced(border);
      }

      this.delegate.clear();
   }

   public void method_11934(class_2784 border, double size) {
      for (WorldBorderListenerOnce listener : this.delegate.keySet()) {
         listener.method_11934(border, size);
      }

      this.delegate.clear();
   }

   public void method_11931(class_2784 border, double fromSize, double toSize, long time) {
      for (WorldBorderListenerOnce listener : this.delegate.keySet()) {
         listener.method_11931(border, fromSize, toSize, time);
      }

      this.delegate.clear();
   }

   public void method_11930(class_2784 border, double centerX, double centerZ) {
      for (WorldBorderListenerOnce listener : this.delegate.keySet()) {
         listener.method_11930(border, centerX, centerZ);
      }

      this.delegate.clear();
   }

   public void method_11932(class_2784 border, int warningTime) {
   }

   public void method_11933(class_2784 border, int warningBlockDistance) {
   }

   public void method_11929(class_2784 border, double damagePerBlock) {
   }

   public void method_11935(class_2784 border, double safeZoneRadius) {
   }
}
