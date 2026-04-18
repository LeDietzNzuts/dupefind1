package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.renderdata.IUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.renderdata.UpgradeRenderDataType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class CookingUpgradeRenderData implements IUpgradeRenderData {
   public static final UpgradeRenderDataType<CookingUpgradeRenderData> TYPE = new UpgradeRenderDataType<>(
      "smelting", CookingUpgradeRenderData.class, CookingUpgradeRenderData::deserializeNBT
   );
   private final boolean burning;

   public CookingUpgradeRenderData(boolean burning) {
      this.burning = burning;
   }

   public boolean isBurning() {
      return this.burning;
   }

   @Override
   public class_2487 serializeNBT() {
      return NBTHelper.putBoolean(new class_2487(), "burning", this.burning);
   }

   public static CookingUpgradeRenderData deserializeNBT(class_2487 nbt) {
      return new CookingUpgradeRenderData(nbt.method_10577("burning"));
   }
}
