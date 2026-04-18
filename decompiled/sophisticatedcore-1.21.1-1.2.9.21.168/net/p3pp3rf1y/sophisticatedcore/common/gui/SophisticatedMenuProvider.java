package net.p3pp3rf1y.sophisticatedcore.common.gui;

import net.minecraft.class_1270;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2561;
import net.minecraft.class_3908;

public class SophisticatedMenuProvider implements class_3908 {
   private final class_2561 title;
   private final class_1270 menuConstructor;
   private final boolean triggerClientSideContainerClosingOnOpen;

   public SophisticatedMenuProvider(class_1270 menuConstructor, class_2561 title) {
      this(menuConstructor, title, true);
   }

   public SophisticatedMenuProvider(class_1270 menuConstructor, class_2561 title, boolean triggerClientSideContainerClosingOnOpen) {
      this.menuConstructor = menuConstructor;
      this.title = title;
      this.triggerClientSideContainerClosingOnOpen = triggerClientSideContainerClosingOnOpen;
   }

   public class_2561 method_5476() {
      return this.title;
   }

   public class_1703 createMenu(int containerId, class_1661 playerInventory, class_1657 player) {
      return this.menuConstructor.createMenu(containerId, playerInventory, player);
   }

   public boolean shouldCloseCurrentScreen() {
      return this.triggerClientSideContainerClosingOnOpen;
   }
}
