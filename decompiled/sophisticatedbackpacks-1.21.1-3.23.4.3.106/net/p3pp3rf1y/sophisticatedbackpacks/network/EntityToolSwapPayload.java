package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import java.util.concurrent.atomic.AtomicBoolean;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.api.IEntityToolSwapUpgrade;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;

public record EntityToolSwapPayload(int entityId) implements class_8710 {
   public static final class_9154<EntityToolSwapPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("entity_tool_swap"));
   public static final class_9139<ByteBuf, EntityToolSwapPayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, EntityToolSwapPayload::entityId, EntityToolSwapPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(EntityToolSwapPayload payload, Context context) {
      class_1657 player = context.player();
      class_1937 level = player.method_37908();
      class_1297 entity = level.method_8469(payload.entityId);
      if (entity != null) {
         AtomicBoolean result = new AtomicBoolean(false);
         AtomicBoolean anyUpgradeCanInteract = new AtomicBoolean(false);
         PlayerInventoryProvider.get().runOnBackpacks(player, (backpack, inventoryName, identifier, slot) -> {
            BackpackWrapper.fromStack(backpack).getUpgradeHandler().getWrappersThatImplement(IEntityToolSwapUpgrade.class).forEach(upgrade -> {
               if (upgrade.canProcessEntityInteract() && !result.get()) {
                  anyUpgradeCanInteract.set(true);
                  result.set(upgrade.onEntityInteract(level, entity, player));
               }
            });
            return result.get();
         });
         if (!anyUpgradeCanInteract.get()) {
            player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.no_tool_swap_upgrade_present"), true);
         } else {
            if (!result.get()) {
               player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.no_tool_found_for_entity"), true);
            }
         }
      }
   }
}
