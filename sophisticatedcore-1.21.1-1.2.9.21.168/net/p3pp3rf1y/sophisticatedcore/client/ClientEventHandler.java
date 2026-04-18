package net.p3pp3rf1y.sophisticatedcore.client;

import java.util.Collections;
import java.util.Optional;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents.BeforeInit;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.class_124;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3611;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_465;
import net.minecraft.class_481;
import net.minecraft.class_5632;
import net.minecraft.class_634;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.api.IStashStorageItem;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.init.ModParticles;
import net.p3pp3rf1y.sophisticatedcore.event.client.ClientRecipesUpdated;
import net.p3pp3rf1y.sophisticatedcore.init.ModFluids;
import net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox.StorageSoundHandler;
import net.p3pp3rf1y.sophisticatedcore.util.RecipeHelper;
import org.jetbrains.annotations.NotNull;

public class ClientEventHandler implements ClientModInitializer {
   public void onInitializeClient() {
      ModParticles.registerFactories();
      registerFluidClientExtension();
      ServerWorldEvents.UNLOAD.register(StorageSoundHandler::onWorldUnload);
      ClientTickEvents.END_WORLD_TICK.register(StorageSoundHandler::tick);
      ClientPlayConnectionEvents.JOIN.register(ClientEventHandler::onPlayerJoinServer);
      ClientRecipesUpdated.EVENT.register(RecipeHelper::onRecipesUpdated);
      ScreenEvents.BEFORE_INIT.register((BeforeInit)(client, screen, windowWidth, windowHeight) -> {
         if (screen instanceof class_465 && !(screen instanceof class_481) && client.field_1724 != null) {
            ScreenEvents.afterRender(screen).register(ClientEventHandler::onDrawScreen);
         }
      });
   }

   // $VF: One or more variable merging failures!
   // $VF: Could not properly define all variable types!
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private static void onDrawScreen(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY, float tickDelta) {
      class_310 mc = class_310.method_1551();
      class_437 gui = mc.field_1755;
      if (gui instanceof class_465<?> containerGui && !(gui instanceof class_481) && mc.field_1724 != null) {
         class_1703 menu = containerGui.method_17577();
         class_1799 held = menu.method_34255();
         if (!held.method_7960()) {
            class_1735 under = containerGui.sophisticatedCore_getSlotUnderMouse();

            <unknown> var10000;
            for (class_1735 s : var10000_1) {
               class_1799 stack = s.method_7677();
               if (s.method_7674(mc.field_1724) && !stack.method_7960()) {
                  Optional<ClientEventHandler.StashResultAndTooltip> stashResultAndTooltip = getStashResultAndTooltip(stack, held);
                  if (!stashResultAndTooltip.isEmpty()) {
                     if (s == under) {
                        renderSpecialTooltip(mc, guiGraphics, mouseX, mouseY, stashResultAndTooltip.get());
                     } else {
                        renderStashSign(mc, containerGui, guiGraphics, s, stack, stashResultAndTooltip.get().stashResult());
                     }
                  }
               }
            }
         }
      }
   }

   private static void renderStashSign(
      class_310 mc, class_465<?> containerGui, class_332 guiGraphics, class_1735 s, class_1799 stack, IStashStorageItem.StashResult stashResult
   ) {
      int x = containerGui.sophisticatedCore_getGuiLeft() + s.field_7873;
      int y = containerGui.sophisticatedCore_getGuiTop() + s.field_7872;
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, 330.0F);
      int color = stashResult == IStashStorageItem.StashResult.MATCH_AND_SPACE ? class_124.field_1060.method_532() : 16776960;
      if (stack.method_7909() instanceof IStashStorageItem) {
         guiGraphics.method_25303(mc.field_1772, "+", x + 10, y + 8, color);
      } else {
         guiGraphics.method_25303(mc.field_1772, "-", x + 1, y, color);
      }

      poseStack.method_22909();
   }

   private static void renderSpecialTooltip(class_310 mc, class_332 guiGraphics, int x, int y, ClientEventHandler.StashResultAndTooltip stashResultAndTooltip) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, 100.0F);
      guiGraphics.method_51437(
         mc.field_1772,
         Collections.singletonList(class_2561.method_43471(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".right_click_to_add_to_storage")),
         stashResultAndTooltip.tooltip(),
         x,
         y
      );
      poseStack.method_22909();
   }

   private static Optional<ClientEventHandler.StashResultAndTooltip> getStashResultAndTooltip(class_1799 inInventory, class_1799 held) {
      if (inInventory.method_7947() == 1 && inInventory.method_7909() instanceof IStashStorageItem stashStorageItem) {
         return getStashResultAndTooltip(inInventory, held, stashStorageItem);
      } else {
         return held.method_7909() instanceof IStashStorageItem stashStorageItem
            ? getStashResultAndTooltip(held, inInventory, stashStorageItem)
            : Optional.empty();
      }
   }

   @NotNull
   private static Optional<ClientEventHandler.StashResultAndTooltip> getStashResultAndTooltip(
      class_1799 potentialStashStorage, class_1799 potentiallyStashable, IStashStorageItem stashStorageItem
   ) {
      IStashStorageItem.StashResult stashResult = stashStorageItem.getItemStashable(
         class_310.method_1551().field_1687.method_30349(), potentialStashStorage, potentiallyStashable
      );
      return stashResult == IStashStorageItem.StashResult.NO_SPACE
         ? Optional.empty()
         : Optional.of(new ClientEventHandler.StashResultAndTooltip(stashResult, stashStorageItem.getInventoryTooltip(potentialStashStorage)));
   }

   private static void onPlayerJoinServer(class_634 handler, PacketSender sender, class_310 client) {
      RecipeHelper.setLevel(class_310.method_1551().field_1687);
   }

   private static void registerFluidClientExtension() {
      FluidRenderHandlerRegistry.INSTANCE
         .register(
            (class_3611)ModFluids.XP_STILL.get(),
            (class_3611)ModFluids.XP_FLOWING.get(),
            new SimpleFluidRenderHandler(SophisticatedCore.getRL("block/xp_still"), SophisticatedCore.getRL("block/xp_flowing"))
         );
   }

   private record StashResultAndTooltip(IStashStorageItem.StashResult stashResult, Optional<class_5632> tooltip) {
   }
}
