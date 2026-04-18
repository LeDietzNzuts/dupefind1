package net.p3pp3rf1y.sophisticatedbackpacks.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockPickResponseUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;

public record BlockPickPayload(class_1799 filter) implements class_8710 {
   public static final class_9154<BlockPickPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("block_pick"));
   public static final class_9139<class_9129, BlockPickPayload> STREAM_CODEC = class_9139.method_56434(
      class_1799.field_48349, BlockPickPayload::filter, BlockPickPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(BlockPickPayload payload, Context context) {
      class_1657 player = context.player();
      PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryHandlerName, identifier, slot) -> {
         IBackpackWrapper wrapper = BackpackWrapper.fromStack(backpack);

         for (IBlockPickResponseUpgrade upgrade : wrapper.getUpgradeHandler().getWrappersThatImplement(IBlockPickResponseUpgrade.class)) {
            if (upgrade.pickBlock(player, payload.filter)) {
               return true;
            }
         }

         return false;
      });
   }
}
