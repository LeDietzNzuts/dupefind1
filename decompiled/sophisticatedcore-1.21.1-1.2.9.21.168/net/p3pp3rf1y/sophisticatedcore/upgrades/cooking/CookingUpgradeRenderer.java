package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import java.util.function.UnaryOperator;
import net.minecraft.class_1937;
import net.minecraft.class_2398;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.p3pp3rf1y.sophisticatedcore.api.IUpgradeRenderer;
import org.joml.Vector3f;

public class CookingUpgradeRenderer implements IUpgradeRenderer<CookingUpgradeRenderData> {
   public void render(class_1937 level, class_5819 rand, UnaryOperator<Vector3f> getPositionFromOffset, CookingUpgradeRenderData upgradeRenderData) {
      if (upgradeRenderData.isBurning()) {
         if (level.field_9229.method_43058() < 0.1) {
            Vector3f renderCenter = getPositionFromOffset.apply(new Vector3f());
            level.method_8486(renderCenter.x(), renderCenter.y(), renderCenter.z(), class_3417.field_15006, class_3419.field_15245, 1.0F, 1.0F, false);
         }

         float xOffset = level.field_9229.method_43057() * 0.6F - 0.3F;
         float yOffset = level.field_9229.method_43057() * 6.0F / 16.0F;
         float zOffset = 0.02F;
         Vector3f randomAtTheBack = getPositionFromOffset.apply(new Vector3f(xOffset, yOffset, zOffset));
         level.method_8406(class_2398.field_11251, randomAtTheBack.x(), randomAtTheBack.y(), randomAtTheBack.z(), 0.0, 0.0, 0.0);
         level.method_8406(class_2398.field_11240, randomAtTheBack.x(), randomAtTheBack.y(), randomAtTheBack.z(), 0.0, 0.0, 0.0);
      }
   }
}
