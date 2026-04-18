package fuzs.forgeconfigapiport.fabric.impl.network.configuration;

import fuzs.forgeconfigapiport.fabric.impl.network.ConfigSync;
import fuzs.forgeconfigapiport.impl.ForgeConfigAPIPort;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.networking.v1.ServerConfigurationNetworking;
import net.minecraft.class_2596;
import net.minecraft.class_8605;
import net.minecraft.class_8610;
import net.minecraft.class_8605.class_8606;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public record SyncConfig(class_8610 listener) implements class_8605 {
   public static final class_8606 TYPE = new class_8606(ForgeConfigAPIPort.id("sync_config").toString());

   public void method_52376(Consumer<class_2596<?>> task) {
      ConfigSync.syncConfigs().forEach(configFilePayload -> task.accept(ServerConfigurationNetworking.createS2CPacket(configFilePayload)));
      this.listener().method_52406(this.method_52375());
   }

   public class_8606 method_52375() {
      return TYPE;
   }
}
