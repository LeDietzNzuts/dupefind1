package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_1657;
import net.minecraft.class_1874;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerType;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogic;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;

public class AutoCookingUpgradeContainer<R extends class_1874, W extends AutoCookingUpgradeWrapper<W, ?, R>>
   extends UpgradeContainerBase<W, AutoCookingUpgradeContainer<R, W>> {
   private final FilterLogicContainer<FilterLogic> inputFilterLogicContainer = new FilterLogicContainer<>(
      () -> this.upgradeWrapper.getInputFilterLogic(), this, this.slots::add
   );
   private final FilterLogicContainer<FilterLogic> fuelFilterLogicContainer = new FilterLogicContainer<>(
      () -> this.upgradeWrapper.getFuelFilterLogic(), this, this.slots::add
   );
   private final CookingLogicContainer<R> cookingLogicContainer = new CookingLogicContainer<>(() -> this.upgradeWrapper.getCookingLogic(), this.slots::add);

   public AutoCookingUpgradeContainer(class_1657 player, int containerId, W wrapper, UpgradeContainerType<W, AutoCookingUpgradeContainer<R, W>> type) {
      super(player, containerId, wrapper, type);
   }

   @Override
   public void handlePacket(class_2487 data) {
      this.inputFilterLogicContainer.handlePacket(data);
   }

   public CookingLogicContainer<R> getCookingLogicContainer() {
      return this.cookingLogicContainer;
   }

   public FilterLogicContainer<FilterLogic> getInputFilterLogicContainer() {
      return this.inputFilterLogicContainer;
   }

   public FilterLogicContainer<FilterLogic> getFuelFilterLogicContainer() {
      return this.fuelFilterLogicContainer;
   }
}
