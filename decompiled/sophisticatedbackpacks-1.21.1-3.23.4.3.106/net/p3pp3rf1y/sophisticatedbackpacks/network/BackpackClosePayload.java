package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.common.gui.BackpackContainer;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record BackpackClosePayload() implements class_8710 {
   public static final class_9154<BackpackClosePayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("backpack_close"));
   public static final class_9139<ByteBuf, BackpackClosePayload> STREAM_CODEC = StreamCodecHelper.singleton(BackpackClosePayload::new);

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(BackpackClosePayload payload, Context context) {
      class_1657 player = context.player();
      if (player.field_7512 instanceof BackpackContainer) {
         player.method_7346();
      }
   }
}
