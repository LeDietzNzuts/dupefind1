package net.p3pp3rf1y.sophisticatedcore.upgrades;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum PrimaryMatch implements class_3542 {
   ITEM("item"),
   MOD("mod"),
   TAGS("tags");

   private final String name;
   public static final Codec<PrimaryMatch> CODEC = class_3542.method_28140(PrimaryMatch::values);
   public static final class_9139<class_2540, PrimaryMatch> STREAM_CODEC = StreamCodecHelper.enumCodec(PrimaryMatch.class);
   private static final Map<String, PrimaryMatch> NAME_VALUES;
   private static final PrimaryMatch[] VALUES;

   private PrimaryMatch(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public PrimaryMatch next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static PrimaryMatch fromName(String name) {
      return NAME_VALUES.getOrDefault(name, ITEM);
   }

   static {
      Builder<String, PrimaryMatch> builder = new Builder();

      for (PrimaryMatch value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
