package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.toolswapper;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum ToolSwapMode implements class_3542 {
   ANY("name"),
   ONLY_TOOLS("onlyTools"),
   NO_SWAP("noSwap");

   public static final Codec<ToolSwapMode> CODEC = class_3542.method_28140(ToolSwapMode::values);
   public static final class_9139<class_2540, ToolSwapMode> STREAM_CODEC = StreamCodecHelper.enumCodec(ToolSwapMode.class);
   private final String name;
   private static final ToolSwapMode[] VALUES;
   private static final Map<String, ToolSwapMode> NAME_VALUES;

   private ToolSwapMode(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public ToolSwapMode next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static ToolSwapMode fromName(String name) {
      return NAME_VALUES.getOrDefault(name, ANY);
   }

   static {
      Builder<String, ToolSwapMode> builder = new Builder();

      for (ToolSwapMode value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
