package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.function.Consumer;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;

public abstract class UpgradeWrapperBase<W extends IUpgradeWrapper, T extends UpgradeItemBase<W>> implements IUpgradeWrapper {
   protected final IStorageWrapper storageWrapper;
   protected final Consumer<class_1799> upgradeSaveHandler;
   protected final class_1799 upgrade;
   protected final T upgradeItem;
   private long cooldown = 0L;

   protected UpgradeWrapperBase(IStorageWrapper storageWrapper, class_1799 upgrade, Consumer<class_1799> upgradeSaveHandler) {
      this.storageWrapper = storageWrapper;
      this.upgrade = upgrade;
      this.upgradeItem = (T)upgrade.method_7909();
      this.upgradeSaveHandler = upgradeSaveHandler;
   }

   @Override
   public class_1799 getUpgradeStack() {
      return this.upgrade;
   }

   protected void save() {
      this.upgradeSaveHandler.accept(this.upgrade);
   }

   protected void setCooldown(class_1937 level, int time) {
      this.cooldown = level.method_8510() + time;
   }

   public long getCooldownTime() {
      return this.cooldown;
   }

   public boolean isInCooldown(class_1937 level) {
      return this.getCooldownTime() > level.method_8510();
   }

   @Override
   public boolean isEnabled() {
      return (Boolean)this.upgrade.sophisticatedCore_getOrDefault(ModCoreDataComponents.ENABLED, true);
   }

   @Override
   public void setEnabled(boolean enabled) {
      this.upgrade.sophisticatedCore_set(ModCoreDataComponents.ENABLED, enabled);
      this.save();
      this.storageWrapper.getUpgradeHandler().refreshWrappersThatImplementAndTypeWrappers();
   }
}
