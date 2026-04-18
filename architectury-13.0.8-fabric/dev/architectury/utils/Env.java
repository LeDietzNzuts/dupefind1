package dev.architectury.utils;

import net.fabricmc.api.EnvType;

public enum Env {
   CLIENT,
   SERVER;

   public static Env fromPlatform(Object type) {
      return type == EnvType.CLIENT ? CLIENT : (type == EnvType.SERVER ? SERVER : null);
   }

   public EnvType toPlatform() {
      return this == CLIENT ? EnvType.CLIENT : EnvType.SERVER;
   }
}
