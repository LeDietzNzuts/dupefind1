package net.p3pp3rf1y.sophisticatedbackpacks.client;

import javax.annotation.Nullable;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.class_5632;
import net.minecraft.class_5684;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.client.init.ModItemsClient;
import net.p3pp3rf1y.sophisticatedbackpacks.client.render.ClientBackpackContentsTooltip;

@Environment(EnvType.CLIENT)
public class SophisticatedBackpacksClient implements ClientModInitializer {
   public void onInitializeClient() {
      KeybindHandler.registerKeyMappings();
      KeybindHandler.register();
      ClientEventHandler.registerHandlers();
      ModItemsClient.registerScreens();
      TooltipComponentCallback.EVENT.register(SophisticatedBackpacksClient::registerTooltipComponent);
   }

   @Nullable
   private static class_5684 registerTooltipComponent(class_5632 data) {
      return data instanceof BackpackItem.BackpackContentsTooltip ? new ClientBackpackContentsTooltip((BackpackItem.BackpackContentsTooltip)data) : null;
   }
}
