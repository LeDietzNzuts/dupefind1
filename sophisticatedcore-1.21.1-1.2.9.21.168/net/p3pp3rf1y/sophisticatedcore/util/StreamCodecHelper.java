package net.p3pp3rf1y.sophisticatedcore.util;

import com.mojang.datafixers.util.Function9;
import io.netty.buffer.ByteBuf;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2540;
import net.minecraft.class_2680;
import net.minecraft.class_5321;
import net.minecraft.class_6862;
import net.minecraft.class_8703;
import net.minecraft.class_9139;
import org.jetbrains.annotations.Nullable;

public class StreamCodecHelper {
   public static final class_9139<ByteBuf, class_2680> BLOCKSTATE = new class_9139<ByteBuf, class_2680>() {
      public void encode(ByteBuf buffer, class_2680 state) {
         class_8703.method_53017(buffer, class_2248.method_9507(state));
      }

      public class_2680 decode(ByteBuf pBuffer) {
         return class_2248.method_9531(class_8703.method_53016(pBuffer));
      }
   };

   private StreamCodecHelper() {
   }

   public static <B extends class_2540, T> class_9139<B, class_6862<T>> ofTagkey(class_5321<? extends class_2378<T>> registry) {
      return new class_9139<B, class_6862<T>>() {
         public void encode(B buffer, class_6862<T> value) {
            buffer.method_10812(value.comp_327());
         }

         public class_6862<T> decode(B buffer) {
            return class_6862.method_40092(registry, buffer.method_10810());
         }
      };
   }

   public static <B extends ByteBuf, V> class_9139<B, V> ofNullable(class_9139<B, V> streamCodec) {
      return new class_9139<B, V>() {
         @Nullable
         public V decode(B buf) {
            return (V)(buf.readBoolean() ? streamCodec.decode(buf) : null);
         }

         public void encode(B buf, @Nullable V value) {
            buf.writeBoolean(value != null);
            if (value != null) {
               streamCodec.encode(buf, value);
            }
         }
      };
   }

   public static <B extends ByteBuf, V> class_9139<B, V> singleton(Supplier<V> instantiator) {
      return new class_9139<B, V>() {
         public V decode(B p_320376_) {
            return instantiator.get();
         }

         public void encode(B p_320158_, V p_320396_) {
         }
      };
   }

   public static <B extends ByteBuf, E, V extends Collection<E>> class_9139<B, V> ofCollection(class_9139<B, E> elementStreamCodec, Supplier<V> instantiator) {
      return new class_9139<B, V>() {
         public V decode(B buf) {
            int size = buf.readInt();
            V collection = instantiator.get();

            for (int i = 0; i < size; i++) {
               collection.add((E)elementStreamCodec.decode(buf));
            }

            return collection;
         }

         public void encode(B buf, V collection) {
            buf.writeInt(collection.size());

            for (E element : collection) {
               elementStreamCodec.encode(buf, element);
            }
         }
      };
   }

   public static <B extends ByteBuf, K, V, M extends Map<K, V>> class_9139<B, M> ofMap(
      class_9139<? super B, K> keyStreamCodec, class_9139<? super B, V> valueStreamCodec, Supplier<M> instantiator
   ) {
      return new class_9139<B, M>() {
         public M decode(B buf) {
            int size = buf.readInt();
            M map = instantiator.get();

            for (int i = 0; i < size; i++) {
               map.put((K)keyStreamCodec.decode(buf), (V)valueStreamCodec.decode(buf));
            }

            return map;
         }

         public void encode(B buf, M map) {
            buf.writeInt(map.size());
            map.forEach((k, v) -> {
               keyStreamCodec.encode(buf, k);
               valueStreamCodec.encode(buf, v);
            });
         }
      };
   }

   public static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> class_9139<B, C> composite(
      class_9139<? super B, T1> pCodec1,
      Function<C, T1> pGetter1,
      class_9139<? super B, T2> pCodec2,
      Function<C, T2> pGetter2,
      class_9139<? super B, T3> pCodec3,
      Function<C, T3> pGetter3,
      class_9139<? super B, T4> pCodec4,
      Function<C, T4> pGetter4,
      class_9139<? super B, T5> pCodec5,
      Function<C, T5> pGetter5,
      class_9139<? super B, T6> pCodec6,
      Function<C, T6> pGetter6,
      class_9139<? super B, T7> pCodec7,
      Function<C, T7> pGetter7,
      class_9139<? super B, T8> pCodec8,
      Function<C, T8> pGetter8,
      class_9139<? super B, T9> pCodec9,
      Function<C, T9> pGetter9,
      Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> pFactory
   ) {
      return new class_9139<B, C>() {
         public C decode(B buffer) {
            T1 t1 = (T1)pCodec1.decode(buffer);
            T2 t2 = (T2)pCodec2.decode(buffer);
            T3 t3 = (T3)pCodec3.decode(buffer);
            T4 t4 = (T4)pCodec4.decode(buffer);
            T5 t5 = (T5)pCodec5.decode(buffer);
            T6 t6 = (T6)pCodec6.decode(buffer);
            T7 t7 = (T7)pCodec7.decode(buffer);
            T8 t8 = (T8)pCodec8.decode(buffer);
            T9 t9 = (T9)pCodec9.decode(buffer);
            return (C)pFactory.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
         }

         public void encode(B buffer, C value) {
            pCodec1.encode(buffer, pGetter1.apply(value));
            pCodec2.encode(buffer, pGetter2.apply(value));
            pCodec3.encode(buffer, pGetter3.apply(value));
            pCodec4.encode(buffer, pGetter4.apply(value));
            pCodec5.encode(buffer, pGetter5.apply(value));
            pCodec6.encode(buffer, pGetter6.apply(value));
            pCodec7.encode(buffer, pGetter7.apply(value));
            pCodec8.encode(buffer, pGetter8.apply(value));
            pCodec9.encode(buffer, pGetter9.apply(value));
         }
      };
   }

   public static <T extends Enum<T>> EnumStreamCodec<T> enumCodec(Class<T> enumClass) {
      return new EnumStreamCodec<>(enumClass);
   }
}
