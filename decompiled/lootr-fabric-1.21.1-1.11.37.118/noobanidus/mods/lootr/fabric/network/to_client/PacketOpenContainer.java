package noobanidus.mods.lootr.fabric.network.to_client;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_2338;
import net.minecraft.class_8710;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.network.ILootrPacket;

public record PacketOpenContainer(class_2338 blockPos) implements ILootrPacket {
   public static final class_9154<PacketOpenContainer> TYPE = new class_9154(LootrAPI.rl("open_container"));
   public static final class_9139<ByteBuf, PacketOpenContainer> STREAM_CODEC = class_9139.method_56434(
      class_2338.field_48404, PacketOpenContainer::blockPos, PacketOpenContainer::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
