package noobanidus.mods.lootr.common.api;

import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1657;
import noobanidus.mods.lootr.common.api.annotation.ClientOnly;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface IClientOpeners extends IOpeners {
   @ClientOnly
   @Nullable
   Set<UUID> getClientOpeners();

   boolean isClientOpened();

   void setClientOpened(boolean var1);

   @Override
   default boolean clearOpeners() {
      boolean result = IOpeners.super.clearOpeners();
      Set<UUID> clientOpeners = this.getClientOpeners();
      if (clientOpeners != null && !clientOpeners.isEmpty()) {
         clientOpeners.clear();
         this.markChanged();
         return true;
      } else {
         return result;
      }
   }

   default boolean hasClientOpened(class_1657 player) {
      return this.hasClientOpened(player.method_5667());
   }

   default boolean hasClientOpened(UUID uuid) {
      if (this.isClientOpened()) {
         return true;
      } else {
         Set<UUID> clientOpeners = this.getClientOpeners();
         return clientOpeners != null && !clientOpeners.isEmpty() && clientOpeners.contains(uuid);
      }
   }
}
