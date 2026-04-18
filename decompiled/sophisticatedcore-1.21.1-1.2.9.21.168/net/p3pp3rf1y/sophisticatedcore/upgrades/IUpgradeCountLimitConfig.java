package net.p3pp3rf1y.sophisticatedcore.upgrades;

import javax.annotation.Nullable;
import net.minecraft.class_2960;

public interface IUpgradeCountLimitConfig {
   int getMaxUpgradesPerStorage(String var1, @Nullable class_2960 var2);

   int getMaxUpgradesInGroupPerStorage(String var1, UpgradeGroup var2);
}
