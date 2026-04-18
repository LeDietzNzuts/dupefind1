package dev.architectury.networking.simple;

import dev.architectury.networking.NetworkManager;
import java.util.Objects;
import net.minecraft.class_2960;

public final class MessageType {
   private final SimpleNetworkManager manager;
   private final class_2960 id;
   private final NetworkManager.Side side;

   MessageType(SimpleNetworkManager manager, class_2960 id, NetworkManager.Side side) {
      this.manager = manager;
      this.id = id;
      this.side = side;
   }

   public SimpleNetworkManager getManager() {
      return this.manager;
   }

   public class_2960 getId() {
      return this.id;
   }

   public NetworkManager.Side getSide() {
      return this.side;
   }

   @Override
   public String toString() {
      return this.id.toString() + ":" + this.side.name().toLowerCase();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         MessageType messageType = (MessageType)o;
         return this.id.equals(messageType.id) && this.side == messageType.side;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.id, this.side);
   }
}
