package net.p3pp3rf1y.sophisticatedcore.upgrades.jukebox;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum RepeatMode implements class_3542 {
   ALL("all"),
   ONE("one"),
   NO("no");

   public static final Codec<RepeatMode> CODEC = class_3542.method_28140(RepeatMode::values);
   public static final class_9139<class_2540, RepeatMode> STREAM_CODEC = StreamCodecHelper.enumCodec(RepeatMode.class);
   private final String name;
   private static final Map<String, RepeatMode> NAME_VALUES;
   private static final RepeatMode[] VALUES;

   private RepeatMode(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public RepeatMode next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static RepeatMode fromName(String name) {
      return NAME_VALUES.getOrDefault(name, NO);
   }

   static {
      Builder<String, RepeatMode> builder = new Builder();

      for (RepeatMode value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
