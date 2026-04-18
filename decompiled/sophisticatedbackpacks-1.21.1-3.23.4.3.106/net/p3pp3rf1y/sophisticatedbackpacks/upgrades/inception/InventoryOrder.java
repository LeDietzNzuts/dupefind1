package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.inception;

import com.google.common.collect.ImmutableMap.Builder;
import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.class_2540;
import net.minecraft.class_3542;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public enum InventoryOrder implements class_3542 {
   MAIN_FIRST("main_first"),
   INCEPTED_FIRST("incepted_first");

   public static final Codec<InventoryOrder> CODEC = class_3542.method_28140(InventoryOrder::values);
   public static final class_9139<class_2540, InventoryOrder> STREAM_CODEC = StreamCodecHelper.enumCodec(InventoryOrder.class);
   private final String name;
   private static final Map<String, InventoryOrder> NAME_VALUES;
   private static final InventoryOrder[] VALUES;

   private InventoryOrder(String name) {
      this.name = name;
   }

   public String method_15434() {
      return this.name;
   }

   public InventoryOrder next() {
      return VALUES[(this.ordinal() + 1) % VALUES.length];
   }

   public static InventoryOrder fromName(String name) {
      return NAME_VALUES.getOrDefault(name, MAIN_FIRST);
   }

   static {
      Builder<String, InventoryOrder> builder = new Builder();

      for (InventoryOrder value : values()) {
         builder.put(value.method_15434(), value);
      }

      NAME_VALUES = builder.build();
      VALUES = values();
   }
}
