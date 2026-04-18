package net.p3pp3rf1y.sophisticatedcore.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Set;
import net.minecraft.class_1799;
import net.minecraft.class_9326;

public class CodecHelper {
   public static final Codec<class_1799> OVERSIZED_ITEM_STACK_CODEC = Codec.lazyInitialized(
      () -> RecordCodecBuilder.create(
         instance -> instance.group(
               class_1799.field_47312.fieldOf("id").forGetter(class_1799::method_41409),
               Codec.INT.fieldOf("count").orElse(1).forGetter(class_1799::method_7947),
               class_9326.field_49589.optionalFieldOf("components", class_9326.field_49588).forGetter(p_330103_ -> p_330103_.field_49270.method_57940())
            )
            .apply(instance, class_1799::new)
      )
   );
   public static final PrimitiveCodec<Integer> STRING_ENCODED_INT = new PrimitiveCodec<Integer>() {
      public <T> DataResult<Integer> read(DynamicOps<T> ops, T input) {
         return ops.getStringValue(input).map(s -> s.startsWith("i") ? Integer.parseInt(s.substring(1)) : Integer.parseInt(s));
      }

      public <T> T write(DynamicOps<T> ops, Integer value) {
         return (T)ops.createString("i" + value);
      }

      @Override
      public String toString() {
         return "Int";
      }
   };

   private CodecHelper() {
   }

   public static <T> Codec<Set<T>> setOf(Codec<T> elementCodec) {
      return new SetCodec<>(elementCodec);
   }
}
