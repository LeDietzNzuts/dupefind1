package fuzs.forgeconfigapiport.fabric.api.neoforge.v4;

import fuzs.forgeconfigapiport.fabric.impl.core.NeoForgeModConfigEventsHolder;
import java.util.Objects;
import net.fabricmc.fabric.api.event.Event;
import net.neoforged.fml.config.ModConfig;

public final class NeoForgeModConfigEvents {
   private NeoForgeModConfigEvents() {
   }

   public static Event<NeoForgeModConfigEvents.Loading> loading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return NeoForgeModConfigEventsHolder.forModId(modId).loading();
   }

   public static Event<NeoForgeModConfigEvents.Reloading> reloading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return NeoForgeModConfigEventsHolder.forModId(modId).reloading();
   }

   public static Event<NeoForgeModConfigEvents.Unloading> unloading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return NeoForgeModConfigEventsHolder.forModId(modId).unloading();
   }

   @FunctionalInterface
   public interface Loading {
      void onModConfigLoading(ModConfig var1);
   }

   @FunctionalInterface
   public interface Reloading {
      void onModConfigReloading(ModConfig var1);
   }

   @FunctionalInterface
   public interface Unloading {
      void onModConfigUnloading(ModConfig var1);
   }
}
