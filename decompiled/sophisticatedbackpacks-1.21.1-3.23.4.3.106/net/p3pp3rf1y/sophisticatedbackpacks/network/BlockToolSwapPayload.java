package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import java.util.concurrent.atomic.AtomicBoolean;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IBlockToolSwapUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;

public record BlockToolSwapPayload(class_2338 pos) implements class_8710 {
   public static final class_9154<BlockToolSwapPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("block_tool_swap"));
   public static final class_9139<ByteBuf, BlockToolSwapPayload> STREAM_CODEC = class_9139.method_56434(
      class_2338.field_48404, BlockToolSwapPayload::pos, BlockToolSwapPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(BlockToolSwapPayload payload, Context context) {
      class_1657 player = context.player();
      AtomicBoolean result = new AtomicBoolean(false);
      AtomicBoolean anyUpgradeCanInteract = new AtomicBoolean(false);
      PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryName, identifier, slot) -> {
         BackpackWrapper.fromStack(backpack).getUpgradeHandler().getWrappersThatImplement(IBlockToolSwapUpgrade.class).forEach(upgrade -> {
            if (upgrade.canProcessBlockInteract() && !result.get()) {
               anyUpgradeCanInteract.set(true);
               result.set(upgrade.onBlockInteract(player.method_37908(), payload.pos, player.method_37908().method_8320(payload.pos), player));
            }
         });
         return result.get();
      });
      if (!anyUpgradeCanInteract.get()) {
         player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.no_tool_swap_upgrade_present"), true);
      } else {
         if (!result.get()) {
            player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.no_tool_found_for_block"), true);
         }
      }
   }
}
