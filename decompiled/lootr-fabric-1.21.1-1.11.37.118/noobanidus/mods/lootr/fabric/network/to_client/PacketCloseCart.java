package noobanidus.mods.lootr.fabric.network.to_client;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.network.ILootrPacket;

public record PacketCloseCart(int entityId) implements ILootrPacket {
   public static final class_9154<PacketCloseCart> TYPE = new class_9154(LootrAPI.rl("close_cart"));
   public static final class_9139<ByteBuf, PacketCloseCart> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_48550, PacketCloseCart::entityId, PacketCloseCart::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
