package noobanidus.mods.lootr.fabric.network.to_server;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_4208;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.network.ILootrPacket;

public record PacketRequestUpdate(class_4208 position) implements ILootrPacket {
   public static final class_9154<PacketRequestUpdate> TYPE = new class_9154(LootrAPI.rl("request_update"));
   public static final class_9139<ByteBuf, PacketRequestUpdate> STREAM_CODEC = class_9139.method_56434(
      class_4208.field_48451, PacketRequestUpdate::position, PacketRequestUpdate::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
