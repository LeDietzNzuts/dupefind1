package fuzs.forgeconfigapiport.impl;

import net.minecraft.class_2960;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForgeConfigAPIPort {
   public static final String MOD_ID = "forgeconfigapiport";
   public static final String MOD_NAME = "Forge Config API Port";
   public static final Logger LOGGER = LoggerFactory.getLogger("Forge Config API Port");

   public static class_2960 id(String key) {
      return class_2960.method_60655("forgeconfigapiport", key);
   }
}
