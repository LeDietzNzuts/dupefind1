package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.Optional;
import net.minecraft.class_310;
import net.minecraft.class_5455;
import net.minecraft.class_638;

public class ClientRegistryHelper {
   public static Optional<class_5455> getRegistryAccess() {
      class_638 level = class_310.method_1551().field_1687;
      return level == null ? Optional.empty() : Optional.of(level.method_30349());
   }
}
