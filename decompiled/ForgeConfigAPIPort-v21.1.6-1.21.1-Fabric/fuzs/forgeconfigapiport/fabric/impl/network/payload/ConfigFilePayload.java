package fuzs.forgeconfigapiport.fabric.impl.network.payload;

import fuzs.forgeconfigapiport.impl.ForgeConfigAPIPort;
import net.minecraft.class_2540;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public record ConfigFilePayload(String fileName, byte[] contents) implements class_8710 {
   public static final class_9139<class_2540, byte[]> UNBOUNDED_BYTE_ARRAY = new class_9139<class_2540, byte[]>() {
      public byte[] decode(class_2540 buf) {
         return buf.method_10795();
      }

      public void encode(class_2540 buf, byte[] data) {
         buf.method_10813(data);
      }
   };
   public static final class_9154<ConfigFilePayload> TYPE = new class_9154(ForgeConfigAPIPort.id("config_file"));
   public static final class_9139<class_2540, ConfigFilePayload> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_48554, ConfigFilePayload::fileName, UNBOUNDED_BYTE_ARRAY, ConfigFilePayload::contents, ConfigFilePayload::new
   );

   public class_9154<ConfigFilePayload> method_56479() {
      return TYPE;
   }
}
