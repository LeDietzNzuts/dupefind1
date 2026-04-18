package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.class_2561;
import net.minecraft.class_8824;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record UpgradeSlotChangeResult(
   boolean successful, @Nullable class_2561 errorMessage, Set<Integer> errorUpgradeSlots, Set<Integer> errorInventorySlots, Set<Integer> errorInventoryParts
) {
   public static final class_9139<class_9129, UpgradeSlotChangeResult> STREAM_CODEC = class_9139.method_56906(
      class_9135.field_48547,
      UpgradeSlotChangeResult::successful,
      StreamCodecHelper.ofNullable(class_8824.field_48540),
      UpgradeSlotChangeResult::errorMessage,
      StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new),
      UpgradeSlotChangeResult::errorUpgradeSlots,
      StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new),
      UpgradeSlotChangeResult::errorInventorySlots,
      StreamCodecHelper.ofCollection(class_9135.field_49675, HashSet::new),
      UpgradeSlotChangeResult::errorInventoryParts,
      UpgradeSlotChangeResult::new
   );

   public Optional<class_2561> getErrorMessage() {
      return Optional.ofNullable(this.errorMessage);
   }

   public static UpgradeSlotChangeResult fail(
      class_2561 errorMessage, Set<Integer> errorUpgradeSlots, Set<Integer> errorInventorySlots, Set<Integer> errorInventoryParts
   ) {
      return new UpgradeSlotChangeResult(false, errorMessage, errorUpgradeSlots, errorInventorySlots, errorInventoryParts);
   }

   public static UpgradeSlotChangeResult success() {
      return new UpgradeSlotChangeResult(true, null, Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
   }
}
