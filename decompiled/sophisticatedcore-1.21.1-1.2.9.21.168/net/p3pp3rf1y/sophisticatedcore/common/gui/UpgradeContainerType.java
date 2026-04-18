package net.p3pp3rf1y.sophisticatedcore.common.gui;

import net.minecraft.class_1657;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;

public class UpgradeContainerType<W extends IUpgradeWrapper, C extends UpgradeContainerBase<W, C>> {
   private final UpgradeContainerType.IFactory<W, C> factory;

   public UpgradeContainerType(UpgradeContainerType.IFactory<W, C> factory) {
      this.factory = factory;
   }

   public C create(class_1657 player, int containerId, W wrapper) {
      return this.factory.create(player, containerId, wrapper, this);
   }

   public interface IFactory<W extends IUpgradeWrapper, C extends UpgradeContainerBase<W, C>> {
      C create(class_1657 var1, int var2, W var3, UpgradeContainerType<W, C> var4);
   }
}
