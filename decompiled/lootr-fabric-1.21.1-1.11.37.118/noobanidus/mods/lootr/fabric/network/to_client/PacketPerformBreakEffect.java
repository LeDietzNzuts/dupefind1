package noobanidus.mods.lootr.fabric.network.to_client;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_2338;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.network.ILootrPacket;

public record PacketPerformBreakEffect(int entityId, class_2338 pos) implements ILootrPacket {
   public static final class_9154<PacketPerformBreakEffect> TYPE = new class_9154(LootrAPI.rl("perform_break_effect"));
   public static final class_9139<ByteBuf, PacketPerformBreakEffect> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_48550, PacketPerformBreakEffect::entityId, class_2338.field_48404, PacketPerformBreakEffect::pos, PacketPerformBreakEffect::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
