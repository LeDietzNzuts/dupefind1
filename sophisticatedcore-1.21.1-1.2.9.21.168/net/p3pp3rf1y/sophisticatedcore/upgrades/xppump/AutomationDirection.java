package net.p3pp3rf1y.sophisticatedcore.upgrades.xppump;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum AutomationDirection implements class_3542 {
   INPUT("input"),
   OUTPUT("output"),
   KEEP("keep"),
   OFF("off");

   public static final Codec<AutomationDirection> CODEC = class_3542.method_28140(AutomationDirection::values);
   public static final class_9139<class_2540, AutomationDirection> STREAM_CODEC = StreamCodecHelper.enumCodec(AutomationDirection.class);
   private final String name;
   private static final Map<String, AutomationDirection> NAME_VALUES;
   private static final AutomationDirection[] VALUES;

   private AutomationDirection(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public AutomationDirection next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static AutomationDirection fromName(String name) {
      return NAME_VALUES.getOrDefault(name, INPUT);
   }

   static {
      Builder<String, AutomationDirection> builder = new Builder();

      for (AutomationDirection value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
