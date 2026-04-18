package net.p3pp3rf1y.sophisticatedcore.common.gui;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum SortBy implements class_3542 {
   NAME("name"),
   MOD("mod"),
   COUNT("count"),
   TAGS("tags");

   public static final Codec<SortBy> CODEC = class_3542.method_28140(SortBy::values);
   public static final class_9139<class_2540, SortBy> STREAM_CODEC = StreamCodecHelper.enumCodec(SortBy.class);
   private final String name;
   private static final Map<String, SortBy> NAME_VALUES;
   private static final SortBy[] VALUES;

   private SortBy(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public SortBy next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static SortBy fromName(String name) {
      return NAME_VALUES.getOrDefault(name, NAME);
   }

   static {
      Builder<String, SortBy> builder = new Builder();

      for (SortBy value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
