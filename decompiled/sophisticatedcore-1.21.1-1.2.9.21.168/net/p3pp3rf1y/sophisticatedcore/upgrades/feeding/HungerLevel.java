package net.p3pp3rf1y.sophisticatedcore.upgrades.feeding;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum HungerLevel implements class_3542 {
   ANY("any"),
   HALF("half"),
   FULL("full");

   public static final Codec<HungerLevel> CODEC = class_3542.method_28140(HungerLevel::values);
   public static final class_9139<class_2540, HungerLevel> STREAM_CODEC = StreamCodecHelper.enumCodec(HungerLevel.class);
   private final String name;
   private static final Map<String, HungerLevel> NAME_VALUES;
   private static final HungerLevel[] VALUES;

   private HungerLevel(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public HungerLevel next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static HungerLevel fromName(String name) {
      return NAME_VALUES.getOrDefault(name, HALF);
   }

   static {
      Builder<String, HungerLevel> builder = new Builder();

      for (HungerLevel value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
