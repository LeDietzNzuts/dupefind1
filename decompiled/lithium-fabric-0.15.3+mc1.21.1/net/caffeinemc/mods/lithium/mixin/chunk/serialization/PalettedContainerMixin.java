package net.caffeinemc.mods.lithium.mixin.chunk.serialization;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;
import net.caffeinemc.mods.lithium.common.world.chunk.CompactingPackedIntegerArray;
import net.caffeinemc.mods.lithium.common.world.chunk.LithiumHashPalette;
import net.minecraft.class_2359;
import net.minecraft.class_2835;
import net.minecraft.class_2837;
import net.minecraft.class_2841;
import net.minecraft.class_3508;
import net.minecraft.class_6490;
import net.minecraft.class_6502;
import net.minecraft.class_2841.class_4464;
import net.minecraft.class_2841.class_6561;
import net.minecraft.class_2841.class_6563;
import net.minecraft.class_7522.class_6562;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2841.class)
public abstract class PalettedContainerMixin<T> {
   private static final ThreadLocal<short[]> CACHED_ARRAY_4096 = ThreadLocal.withInitial(() -> new short[4096]);
   private static final ThreadLocal<short[]> CACHED_ARRAY_64 = ThreadLocal.withInitial(() -> new short[64]);
   @Shadow
   private volatile class_6561<T> field_34560;
   @Shadow
   @Final
   private class_2835<T> field_34558;

   @Shadow
   public abstract void method_12334();

   @Shadow
   protected abstract T method_12331(int var1);

   @Shadow
   public abstract void method_12335();

   @Overwrite
   public class_6562<T> method_44345(class_2359<T> idList, class_6563 provider) {
      this.method_12334();
      LithiumHashPalette<T> hashPalette = null;
      Optional<LongStream> data = Optional.empty();
      List<T> elements = null;
      class_2837<T> palette = this.field_34560.comp_119();
      class_6490 storage = this.field_34560.comp_118();
      if (storage instanceof class_6502 || palette.method_12197() == 1) {
         elements = List.of((T)palette.method_12288(0));
      } else if (palette instanceof LithiumHashPalette<T> lithiumHashPalette) {
         hashPalette = lithiumHashPalette;
      }

      if (elements == null) {
         LithiumHashPalette<T> compactedPalette = new LithiumHashPalette<>(idList, storage.method_34896(), this.field_34558);
         short[] array = this.getOrCreate(provider.method_38312());
         ((CompactingPackedIntegerArray)storage).lithium$compact(this.field_34560.comp_119(), compactedPalette, array);
         if (hashPalette != null
            && hashPalette.method_12197() == compactedPalette.method_12197()
            && storage.method_34896() == provider.method_38315(idList, hashPalette.method_12197())) {
            data = this.asOptional((long[])storage.method_15212().clone());
            elements = hashPalette.getElements();
         } else {
            int bits = provider.method_38315(idList, compactedPalette.method_12197());
            if (bits != 0) {
               class_3508 copy = new class_3508(bits, array.length);

               for (int i = 0; i < array.length; i++) {
                  copy.method_15210(i, array[i]);
               }

               data = this.asOptional(copy.method_15212());
            }

            elements = compactedPalette.getElements();
         }
      }

      this.method_12335();
      return new class_6562(elements, data);
   }

   private Optional<LongStream> asOptional(long[] data) {
      return Optional.of(Arrays.stream(data));
   }

   private short[] getOrCreate(int size) {
      return switch (size) {
         case 64 -> (short[])CACHED_ARRAY_64.get();
         case 4096 -> (short[])CACHED_ARRAY_4096.get();
         default -> new short[size];
      };
   }

   @Inject(method = "method_21732(Lnet/minecraft/class_2841$class_4464;)V", at = @At("HEAD"), cancellable = true)
   public void count(class_4464<T> consumer, CallbackInfo ci) {
      int len = this.field_34560.comp_119().method_12197();
      if (len <= 4096) {
         short[] counts = new short[len];
         this.field_34560.comp_118().method_21739(ix -> counts[ix]++);

         for (int i = 0; i < counts.length; i++) {
            T obj = (T)this.field_34560.comp_119().method_12288(i);
            if (obj != null) {
               consumer.accept(obj, counts[i]);
            }
         }

         ci.cancel();
      }
   }
}
