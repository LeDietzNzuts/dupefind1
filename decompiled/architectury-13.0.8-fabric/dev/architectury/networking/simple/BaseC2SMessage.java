package dev.architectury.networking.simple;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_310;

public abstract class BaseC2SMessage extends Message {
   @Environment(EnvType.CLIENT)
   public final void sendToServer() {
      if (class_310.method_1551().method_1562() != null) {
         class_310.method_1551().method_1562().method_52787(this.toPacket(class_310.method_1551().field_1687.method_30349()));
      } else {
         throw new IllegalStateException("Unable to send packet to the server while not in game!");
      }
   }
}
