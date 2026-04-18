package noobanidus.mods.lootr.fabric.impl;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1273;
import net.minecraft.class_1923;
import net.minecraft.class_2586;
import net.minecraft.class_2596;
import net.minecraft.class_2624;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import noobanidus.mods.lootr.common.api.DataToCopy;
import noobanidus.mods.lootr.common.api.IPlatformAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.impl.DefaultPlatformAPIImpl;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinBaseContainerBlockEntity;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketPerformBreakEffect;
import noobanidus.mods.lootr.fabric.network.to_client.PacketRefreshSection;

public class PlatformAPIImpl extends DefaultPlatformAPIImpl implements IPlatformAPI {
   @Override
   public void performEntityOpen(ILootrEntity entity, class_3222 player) {
      ServerPlayNetworking.send(player, new PacketOpenCart(entity.asEntity().method_5628()));
   }

   @Override
   public void performEntityOpen(ILootrEntity entity) {
      if (entity.getInfoLevel() instanceof class_3218 serverLevel) {
         class_2596<?> packet = ServerPlayNetworking.createS2CPacket(new PacketOpenCart(entity.asEntity().method_5628()));
         serverLevel.method_14178()
            .field_17254
            .method_17210(new class_1923(entity.asEntity().method_24515()), false)
            .forEach(player -> player.field_13987.method_14364(packet));
      }
   }

   @Override
   public void performEntityClose(ILootrEntity entity, class_3222 player) {
      ServerPlayNetworking.send(player, new PacketCloseCart(entity.asEntity().method_5628()));
   }

   @Override
   public void performEntityClose(ILootrEntity entity) {
      if (entity.getInfoLevel() instanceof class_3218 serverLevel) {
         class_2596<?> packet = ServerPlayNetworking.createS2CPacket(new PacketCloseCart(entity.asEntity().method_5628()));
         serverLevel.method_14178()
            .field_17254
            .method_17210(new class_1923(entity.asEntity().method_24515()), false)
            .forEach(player -> player.field_13987.method_14364(packet));
      }
   }

   @Override
   public void performBlockOpen(ILootrBlockEntity blockEntity, class_3222 player) {
      ServerPlayNetworking.send(player, new PacketOpenContainer(blockEntity.asBlockEntity().method_11016()));
   }

   @Override
   public void performBlockOpen(ILootrBlockEntity blockEntity) {
      if (blockEntity.getInfoLevel() instanceof class_3218 serverLevel) {
         class_2596<?> packet = ServerPlayNetworking.createS2CPacket(new PacketOpenContainer(blockEntity.asBlockEntity().method_11016()));
         serverLevel.method_14178()
            .field_17254
            .method_17210(new class_1923(blockEntity.asBlockEntity().method_11016()), false)
            .forEach(player -> player.field_13987.method_14364(packet));
      }
   }

   @Override
   public void performBlockClose(ILootrBlockEntity blockEntity, class_3222 player) {
      ServerPlayNetworking.send(player, new PacketCloseContainer(blockEntity.asBlockEntity().method_11016()));
   }

   @Override
   public void performBlockClose(ILootrBlockEntity blockEntity) {
      if (blockEntity.getInfoLevel() instanceof class_3218 serverLevel) {
         class_2596<?> packet = ServerPlayNetworking.createS2CPacket(new PacketCloseContainer(blockEntity.asBlockEntity().method_11016()));
         serverLevel.method_14178()
            .field_17254
            .method_17210(new class_1923(blockEntity.asBlockEntity().method_11016()), false)
            .forEach(player -> player.field_13987.method_14364(packet));
      }
   }

   @Override
   public DataToCopy copySpecificData(class_2586 oldBlockEntity) {
      class_1273 key = class_1273.field_5817;
      if (oldBlockEntity instanceof class_2624 baseContainer) {
         key = ((AccessorMixinBaseContainerBlockEntity)baseContainer).getLockKey();
      }

      return new DataToCopy(null, key);
   }

   @Override
   public void restoreSpecificData(DataToCopy data, class_2586 newBlockEntity) {
      if (newBlockEntity instanceof class_2624 baseContainer) {
         ((AccessorMixinBaseContainerBlockEntity)baseContainer).setLockKey(data.lockCode());
      }
   }

   @Override
   public void refreshPlayerSection(class_3222 player) {
      ServerPlayNetworking.send(player, PacketRefreshSection.INSTANCE);
   }

   @Override
   public void performPotBreak(ILootrBlockEntity blockEntity, class_3222 player) {
      if (blockEntity.getInfoLevel() instanceof class_3218 serverLevel) {
         class_2596<?> packet = ServerPlayNetworking.createS2CPacket(
            new PacketPerformBreakEffect(player.method_5628(), blockEntity.asBlockEntity().method_11016())
         );
         serverLevel.method_14178()
            .field_17254
            .method_17210(new class_1923(blockEntity.asBlockEntity().method_11016()), false)
            .forEach(splayer -> splayer.field_13987.method_14364(packet));
      }
   }
}
