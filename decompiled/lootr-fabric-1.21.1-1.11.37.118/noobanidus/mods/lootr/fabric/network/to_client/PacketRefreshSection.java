package noobanidus.mods.lootr.fabric.network.to_client;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.network.ILootrPacket;

public record PacketRefreshSection() implements ILootrPacket {
   public static final PacketRefreshSection INSTANCE = new PacketRefreshSection();
   public static final class_9154<PacketRefreshSection> TYPE = new class_9154(LootrAPI.rl("refresh_section"));
   public static final class_9139<ByteBuf, PacketRefreshSection> STREAM_CODEC = class_9139.method_56431(INSTANCE);

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
