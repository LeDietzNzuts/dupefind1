package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_1657;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;

public class UpgradeContainerRegistry {
   private static final Map<class_2960, UpgradeContainerType<? extends IUpgradeWrapper, ? extends UpgradeContainerBase<?, ?>>> UPGRADE_CONTAINERS = new HashMap<>();

   private UpgradeContainerRegistry() {
   }

   public static void register(class_2960 upgradeName, UpgradeContainerType<? extends IUpgradeWrapper, ? extends UpgradeContainerBase<?, ?>> containerFactory) {
      UPGRADE_CONTAINERS.put(upgradeName, containerFactory);
   }

   public static <W extends IUpgradeWrapper, C extends UpgradeContainerBase<W, C>> Optional<UpgradeContainerBase<W, C>> instantiateContainer(
      class_1657 player, int containerId, W wrapper
   ) {
      class_2960 upgradeName = class_7923.field_41178.method_10221(wrapper.getUpgradeStack().method_7909());
      return wrapper.getUpgradeStack().method_7909() instanceof IUpgradeItem && !wrapper.hideSettingsTab() && UPGRADE_CONTAINERS.containsKey(upgradeName)
         ? Optional.of(getContainerType(upgradeName).create(player, containerId, wrapper))
         : Optional.empty();
   }

   private static <W extends IUpgradeWrapper, C extends UpgradeContainerBase<W, C>> UpgradeContainerType<W, C> getContainerType(class_2960 upgradeName) {
      return (UpgradeContainerType<W, C>)UPGRADE_CONTAINERS.get(upgradeName);
   }
}
