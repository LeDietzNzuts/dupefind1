package net.p3pp3rf1y.sophisticatedcore.upgrades.filter;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum Direction implements class_3542 {
   BOTH("both"),
   INPUT("input"),
   OUTPUT("output");

   public static final Codec<Direction> CODEC = class_3542.method_28140(Direction::values);
   public static final class_9139<class_2540, Direction> STREAM_CODEC = StreamCodecHelper.enumCodec(Direction.class);
   private final String name;
   private static final Map<String, Direction> NAME_VALUES;
   private static final Direction[] VALUES;

   private Direction(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public Direction next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static Direction fromName(String name) {
      return NAME_VALUES.getOrDefault(name, BOTH);
   }

   static {
      Builder<String, Direction> builder = new Builder();

      for (Direction value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
