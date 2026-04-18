package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.List;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_7923;
import net.minecraft.class_1792.class_1793;
import net.minecraft.class_1792.class_9635;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ItemBase;

public abstract class UpgradeItemBase<T extends IUpgradeWrapper> extends ItemBase implements IUpgradeItem<T> {
   private final IUpgradeCountLimitConfig upgradeTypeLimitConfig;

   protected UpgradeItemBase(IUpgradeCountLimitConfig upgradeTypeLimitConfig) {
      super(new class_1793());
      this.upgradeTypeLimitConfig = upgradeTypeLimitConfig;
   }

   public void method_7851(class_1799 stack, class_9635 context, List<class_2561> tooltip, class_1836 flagIn) {
      tooltip.addAll(TranslationHelper.INSTANCE.getTranslatedLines(stack.method_7909().method_7876() + ".tooltip", null, class_124.field_1063));
   }

   @Override
   public int getUpgradesPerStorage(String storageType) {
      return this.upgradeTypeLimitConfig.getMaxUpgradesPerStorage(storageType, class_7923.field_41178.method_10221(this));
   }

   @Override
   public int getUpgradesInGroupPerStorage(String storageType) {
      return this.getUpgradeGroup().isSolo()
         ? Integer.MAX_VALUE
         : this.upgradeTypeLimitConfig.getMaxUpgradesInGroupPerStorage(storageType, this.getUpgradeGroup());
   }

   @Override
   public class_2561 getName() {
      return class_2561.method_43471(this.method_7876());
   }
}
