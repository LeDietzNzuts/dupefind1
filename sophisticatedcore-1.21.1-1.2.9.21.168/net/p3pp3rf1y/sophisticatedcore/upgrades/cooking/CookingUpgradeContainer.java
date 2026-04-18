package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_1657;
import net.minecraft.class_1874;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;

public class CookingUpgradeContainer<R extends class_1874, W extends CookingUpgradeWrapper<W, ?, R>>
   extends UpgradeContainerBase<W, CookingUpgradeContainer<R, W>> {
   private final CookingLogicContainer<R> cookingLogicContainer = new CookingLogicContainer<>(() -> this.upgradeWrapper.getCookingLogic(), this.slots::add);

   public CookingUpgradeContainer(class_1657 player, int containerId, W wrapper, UpgradeContainerType<W, CookingUpgradeContainer<R, W>> type) {
      super(player, containerId, wrapper, type);
   }

   @Override
   public void handlePacket(class_2487 data) {
   }

   public CookingLogicContainer<R> getSmeltingLogicContainer() {
      return this.cookingLogicContainer;
   }
}
