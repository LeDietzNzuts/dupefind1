package net.p3pp3rf1y.sophisticatedcore.upgrades.tank;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1703;
import net.minecraft.class_2653;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;

public record TankClickPayload(int upgradeSlot) implements class_8710 {
   public static final class_9154<TankClickPayload> TYPE = new class_9154(SophisticatedCore.getRL("tank_click"));
   public static final class_9139<ByteBuf, TankClickPayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, TankClickPayload::upgradeSlot, TankClickPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(TankClickPayload payload, Context context) {
      class_3222 serverPlayer = context.player();
      if (serverPlayer.field_7512 instanceof StorageContainerMenuBase<?> storageContainerMenu) {
         class_1703 var11 = serverPlayer.field_7512;
         UpgradeContainerBase<?, ?> upgradeContainer = storageContainerMenu.getUpgradeContainers().get(payload.upgradeSlot);
         if (upgradeContainer instanceof TankUpgradeContainer tankContainer) {
            ContainerItemContext cic = ContainerItemContext.ofPlayerCursor(serverPlayer, var11);
            Storage<FluidVariant> storage = (Storage<FluidVariant>)cic.find(FluidStorage.ITEM);
            if (storage != null) {
               TankUpgradeWrapper tankWrapper = tankContainer.getUpgradeWrapper();
               FluidStack tankContents = tankWrapper.getContents();
               if (tankContents.isEmpty()) {
                  drainHandler(serverPlayer, var11, cic, storage, tankWrapper);
               } else if (!tankWrapper.fillHandler(cic, storage, itemStackIn -> {
                  var11.method_34254(itemStackIn);
                  serverPlayer.field_13987.method_14364(new class_2653(-1, var11.method_37422(), -1, var11.method_34255()));
               })) {
                  drainHandler(serverPlayer, var11, cic, storage, tankWrapper);
               }
            }
         }
      }
   }

   private static void drainHandler(
      class_3222 player, class_1703 containerMenu, ContainerItemContext cic, Storage<FluidVariant> fluidHandler, TankUpgradeWrapper tankWrapper
   ) {
      tankWrapper.drainHandler(cic, fluidHandler, itemStackIn -> {
         containerMenu.method_34254(itemStackIn);
         player.field_13987.method_14364(new class_2653(-1, containerMenu.method_37422(), -1, containerMenu.method_34255()));
      });
   }
}
