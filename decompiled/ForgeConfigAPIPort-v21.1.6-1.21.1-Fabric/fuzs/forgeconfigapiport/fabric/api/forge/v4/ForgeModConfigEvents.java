package fuzs.forgeconfigapiport.fabric.api.forge.v4;

import fuzs.forgeconfigapiport.fabric.impl.core.ForgeModConfigEventsHolder;
import java.util.Objects;
import net.fabricmc.fabric.api.event.Event;
import net.minecraftforge.fml.config.ModConfig;

@Deprecated(forRemoval = true)
public final class ForgeModConfigEvents {
   private ForgeModConfigEvents() {
   }

   public static Event<ForgeModConfigEvents.Loading> loading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return ForgeModConfigEventsHolder.forModId(modId).loading();
   }

   public static Event<ForgeModConfigEvents.Reloading> reloading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return ForgeModConfigEventsHolder.forModId(modId).reloading();
   }

   public static Event<ForgeModConfigEvents.Unloading> unloading(String modId) {
      Objects.requireNonNull(modId, "mod id is null");
      return ForgeModConfigEventsHolder.forModId(modId).unloading();
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
