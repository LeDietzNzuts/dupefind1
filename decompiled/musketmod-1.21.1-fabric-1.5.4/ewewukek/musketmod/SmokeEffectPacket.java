package ewewukek.musketmod;

import io.netty.buffer.ByteBuf;
import net.minecraft.class_243;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.joml.Vector3f;

public record SmokeEffectPacket(Vector3f origin, Vector3f direction) implements class_8710 {
   public static final class_9154<SmokeEffectPacket> TYPE = new class_9154(MusketMod.resource("smoke"));
   public static final class_9139<ByteBuf, SmokeEffectPacket> CODEC = class_9139.method_56435(
      class_9135.field_48558, SmokeEffectPacket::origin, class_9135.field_48558, SmokeEffectPacket::direction, SmokeEffectPacket::new
   );

   public static SmokeEffectPacket fromVec3(class_243 origin, class_243 direction) {
      return new SmokeEffectPacket(
         new Vector3f((float)origin.field_1352, (float)origin.field_1351, (float)origin.field_1350),
         new Vector3f((float)direction.field_1352, (float)direction.field_1351, (float)direction.field_1350)
      );
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
