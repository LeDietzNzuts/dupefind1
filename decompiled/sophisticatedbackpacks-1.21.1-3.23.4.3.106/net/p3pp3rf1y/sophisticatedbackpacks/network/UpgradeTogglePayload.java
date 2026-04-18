package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import java.util.Map;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_2561;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;

public record UpgradeTogglePayload(int upgradeSlot) implements class_8710 {
   public static final class_9154<UpgradeTogglePayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("upgrade_toggle"));
   public static final class_9139<ByteBuf, UpgradeTogglePayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, UpgradeTogglePayload::upgradeSlot, UpgradeTogglePayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(UpgradeTogglePayload payload, Context context) {
      class_1657 player = context.player();
      PlayerInventoryProvider.get()
         .runOnBackpacks(
            player,
            (backpack, inventoryName, identifier, slot) -> {
               Map<Integer, IUpgradeWrapper> slotWrappers = BackpackWrapper.fromStack(backpack).getUpgradeHandler().getSlotWrappers();
               if (slotWrappers.containsKey(payload.upgradeSlot)) {
                  IUpgradeWrapper upgradeWrapper = slotWrappers.get(payload.upgradeSlot);
                  if (upgradeWrapper.canBeDisabled()) {
                     upgradeWrapper.setEnabled(!upgradeWrapper.isEnabled());
                     String translKey = upgradeWrapper.isEnabled()
                        ? "gui.sophisticatedbackpacks.status.upgrade_switched_on"
                        : "gui.sophisticatedbackpacks.status.upgrade_switched_off";
                     player.method_7353(class_2561.method_43469(translKey, new Object[]{upgradeWrapper.getUpgradeStack().method_7964()}), true);
                  }
               }

               return true;
            }
         );
   }
}
