package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_1874;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeGroup;

public interface ICookingUpgrade<T extends class_1874> {
   UpgradeGroup UPGRADE_GROUP = new UpgradeGroup("furnace_upgrades", TranslationHelper.INSTANCE.translUpgradeGroup("cooking_upgrades"));

   CookingLogic<T> getCookingLogic();
}
