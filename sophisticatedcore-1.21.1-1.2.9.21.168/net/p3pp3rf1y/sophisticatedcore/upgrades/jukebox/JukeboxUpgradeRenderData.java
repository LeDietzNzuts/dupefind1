package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.renderdata.IUpgradeRenderData;
import net.p3pp3rf1y.sophisticatedcore.renderdata.UpgradeRenderDataType;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class JukeboxUpgradeRenderData implements IUpgradeRenderData {
   public static final UpgradeRenderDataType<JukeboxUpgradeRenderData> TYPE = new UpgradeRenderDataType<>(
      "jukebox", JukeboxUpgradeRenderData.class, JukeboxUpgradeRenderData::deserializeNBT
   );
   private final boolean playing;

   public JukeboxUpgradeRenderData(boolean playing) {
      this.playing = playing;
   }

   public boolean isPlaying() {
      return this.playing;
   }

   @Override
   public class_2487 serializeNBT() {
      return NBTHelper.putBoolean(new class_2487(), "playing", this.playing);
   }

   public static JukeboxUpgradeRenderData deserializeNBT(class_2487 nbt) {
      return new JukeboxUpgradeRenderData(nbt.method_10577("playing"));
   }
}
