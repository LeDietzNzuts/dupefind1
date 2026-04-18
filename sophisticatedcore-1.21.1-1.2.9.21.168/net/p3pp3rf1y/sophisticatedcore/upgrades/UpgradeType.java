package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;

public class UpgradeType<T extends IUpgradeWrapper> {
   private final UpgradeType.IFactory<T> factory;

   public UpgradeType(UpgradeType.IFactory<T> factory) {
      this.factory = factory;
   }

   public T create(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      return this.factory.create(storageWrapper, upgrade, upgradeSaveHandler);
   }

   public interface IFactory<T extends IUpgradeWrapper> {
      T create(IStorageWrapper var1, class_1799 var2, Consumer<class_1799> var3);
   }
}
