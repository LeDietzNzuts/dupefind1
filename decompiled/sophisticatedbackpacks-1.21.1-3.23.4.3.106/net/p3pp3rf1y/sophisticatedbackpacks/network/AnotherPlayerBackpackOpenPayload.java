package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_747;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.Config;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContext;
import net.p3pp3rf1y.sophisticatedbackpacks.settings.BackpackMainSettingsCategory;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsManager;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsCategory;

public record AnotherPlayerBackpackOpenPayload(int anotherPlayerId) implements class_8710 {
   public static final class_9154<AnotherPlayerBackpackOpenPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("another_player_backpack_open"));
   public static final class_9139<ByteBuf, AnotherPlayerBackpackOpenPayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, AnotherPlayerBackpackOpenPayload::anotherPlayerId, AnotherPlayerBackpackOpenPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(AnotherPlayerBackpackOpenPayload payload, Context context) {
      class_1657 player = context.player();
      if (!Boolean.FALSE.equals(Config.SERVER.allowOpeningOtherPlayerBackpacks.get())) {
         if (player.method_37908().method_8469(payload.anotherPlayerId) instanceof class_1657 anotherPlayer) {
            PlayerInventoryProvider.get()
               .runOnBackpacks(
                  anotherPlayer,
                  (backpack, inventoryName, identifier, slot) -> {
                     if (canAnotherPlayerOpenBackpack(anotherPlayer, backpack)) {
                        BackpackContext.AnotherPlayer backpackContext = new BackpackContext.AnotherPlayer(inventoryName, identifier, slot, anotherPlayer);
                        player.sophisticatedCore_openMenu(
                           new class_747((w, p, pl) -> new BackpackContainer(w, pl, backpackContext), backpack.method_7964()), backpackContext::toBuffer
                        );
                     } else {
                        player.method_7353(class_2561.method_43471("gui.sophisticatedbackpacks.status.backpack_cannot_be_open_by_another_player"), true);
                     }

                     return true;
                  },
                  true
               );
         }
      }
   }

   private static boolean canAnotherPlayerOpenBackpack(class_1657 anotherPlayer, class_1799 backpack) {
      MainSettingsCategory<?> category = BackpackWrapper.fromStack(backpack).getSettingsHandler().getGlobalSettingsCategory();
      return (Boolean)SettingsManager.getSettingValue(
         anotherPlayer, category.getPlayerSettingsTagName(), category, BackpackMainSettingsCategory.ANOTHER_PLAYER_CAN_OPEN
      );
   }
}
