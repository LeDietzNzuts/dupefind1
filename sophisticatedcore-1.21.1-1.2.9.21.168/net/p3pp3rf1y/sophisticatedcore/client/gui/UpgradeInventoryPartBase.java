package net.p3pp3rf1y.sophisticatedcore.client.gui;

import net.minecraft.class_332;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;

public abstract class UpgradeInventoryPartBase<C extends UpgradeContainerBase<?, ?>> {
   protected final C container;
   protected final int upgradeSlot;

   protected UpgradeInventoryPartBase(int upgradeSlot, C container) {
      this.container = container;
      this.upgradeSlot = upgradeSlot;
   }

   public abstract void render(class_332 var1, int var2, int var3);

   public abstract boolean handleMouseReleased(double var1, double var3, int var5);

   public abstract void renderErrorOverlay(class_332 var1);

   public abstract void renderTooltip(StorageScreenBase<?> var1, class_332 var2, int var3, int var4);
}
