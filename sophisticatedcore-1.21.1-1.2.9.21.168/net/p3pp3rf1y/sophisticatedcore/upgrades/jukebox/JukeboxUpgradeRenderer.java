package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import java.util.function.UnaryOperator;
import net.minecraft.class_1937;
import net.minecraft.class_5819;
import net.p3pp3rf1y.sophisticatedcore.api.IUpgradeRenderer;
import net.p3pp3rf1y.sophisticatedcore.init.ModParticles;
import org.joml.Vector3f;

public class JukeboxUpgradeRenderer implements IUpgradeRenderer<JukeboxUpgradeRenderData> {
   public void render(class_1937 level, class_5819 rand, UnaryOperator<Vector3f> getPositionFromOffset, JukeboxUpgradeRenderData upgradeRenderData) {
      if (upgradeRenderData.isPlaying() && rand.method_43048(2) == 0) {
         float xOffset = level.field_9229.method_43057() * 0.6F - 0.3F;
         float yOffset = 0.5F + level.field_9229.method_43057() * 6.0F / 16.0F;
         float zOffset = level.field_9229.method_43057() * 0.6F - 0.1F;
         Vector3f randomAtTheBack = getPositionFromOffset.apply(new Vector3f(xOffset, yOffset, zOffset));
         level.method_8406(ModParticles.JUKEBOX_NOTE.get(), randomAtTheBack.x(), randomAtTheBack.y(), randomAtTheBack.z(), rand.method_43057(), 0.0, 0.0);
      }
   }
}
