package net.p3pp3rf1y.sophisticatedcore.util;

import net.minecraft.class_2540;
import net.minecraft.class_9139;

public class EnumStreamCodec<T extends Enum<T>> implements class_9139<class_2540, T> {
   private final Class<T> enumClass;

   public EnumStreamCodec(Class<T> enumClass) {
      this.enumClass = enumClass;
   }

   public T decode(class_2540 buf) {
      return (T)buf.method_10818(this.enumClass);
   }

   public void encode(class_2540 buf, T value) {
      buf.method_10817(value);
   }
}
