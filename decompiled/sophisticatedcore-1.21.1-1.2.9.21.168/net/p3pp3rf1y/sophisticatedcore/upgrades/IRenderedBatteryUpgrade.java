package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.function.Consumer;
import net.minecraft.class_2487;

public interface IRenderedBatteryUpgrade {
   void setBatteryRenderInfoUpdateCallback(Consumer<IRenderedBatteryUpgrade.BatteryRenderInfo> var1);

   void forceUpdateBatteryRenderInfo();

   public static class BatteryRenderInfo {
      private static final String CHARGE_RATIO_TAG = "chargeRatio";
      private float chargeRatio;

      public BatteryRenderInfo(float chargeRatio) {
         this.chargeRatio = chargeRatio;
      }

      public class_2487 serialize() {
         class_2487 ret = new class_2487();
         ret.method_10548("chargeRatio", this.chargeRatio);
         return ret;
      }

      public static IRenderedBatteryUpgrade.BatteryRenderInfo deserialize(class_2487 tag) {
         return new IRenderedBatteryUpgrade.BatteryRenderInfo(tag.method_10583("chargeRatio"));
      }

      public void setChargeRatio(float chargeRatio) {
         this.chargeRatio = chargeRatio;
      }

      public float getChargeRatio() {
         return this.chargeRatio;
      }
   }
}
