package net.p3pp3rf1y.sophisticatedcore.upgrades.voiding;

import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import net.neoforged.neoforge.common.ModConfigSpec.Builder;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilteredUpgradeConfigBase;

public class VoidUpgradeConfig extends FilteredUpgradeConfigBase {
   public final BooleanValue voidAnythingEnabled;

   public VoidUpgradeConfig(Builder builder, String name, String path, int defaultFilterSlots, int defaultSlotsInRow) {
      super(builder, name, path, defaultFilterSlots, defaultSlotsInRow);
      this.voidAnythingEnabled = builder.comment("Determines whether void upgrade allows voiding anything or it only has overflow option")
         .define("voidAnythingEnabled", true);
      builder.pop();
   }
}
