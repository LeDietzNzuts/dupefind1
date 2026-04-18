package net.p3pp3rf1y.sophisticatedbackpacks.client.render;

import java.util.UUID;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_638;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.network.RequestBackpackInventoryContentsPayload;
import net.p3pp3rf1y.sophisticatedcore.client.render.ClientStorageContentsTooltipBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public class ClientBackpackContentsTooltip extends ClientStorageContentsTooltipBase {
   private final class_1799 backpack;

   public static void onWorldLoad(class_310 mc, class_638 level) {
      refreshContents();
      lastRequestTime = 0L;
   }

   public void method_32666(class_327 font, int leftX, int topY, class_332 guiGraphics) {
      this.renderTooltip(BackpackWrapper.fromStack(this.backpack), font, leftX, topY, guiGraphics);
   }

   public ClientBackpackContentsTooltip(BackpackItem.BackpackContentsTooltip tooltip) {
      this.backpack = tooltip.getBackpack();
   }

   protected void sendInventorySyncRequest(UUID uuid) {
      PacketDistributor.sendToServer(new RequestBackpackInventoryContentsPayload(uuid));
   }
}
