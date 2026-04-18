package net.caffeinemc.mods.lithium.mixin.chunk.serialization;

import net.caffeinemc.mods.lithium.common.world.chunk.CompactingPackedIntegerArray;
import net.minecraft.class_2837;
import net.minecraft.class_3508;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_3508.class)
public abstract class SimpleBitStorageMixin implements CompactingPackedIntegerArray {
   @Shadow
   @Final
   private int field_15632;
   @Shadow
   @Final
   private int field_15633;
   @Shadow
   @Final
   private long field_15634;
   @Shadow
   @Final
   private int field_24079;
   @Shadow
   @Final
   private long[] field_15631;

   @Override
   public <T> void lithium$compact(class_2837<T> srcPalette, class_2837<T> dstPalette, short[] out) {
      if (this.field_15632 >= 32767) {
         throw new IllegalStateException("Array too large");
      } else if (this.field_15632 != out.length) {
         throw new IllegalStateException("Array size mismatch");
      } else {
         short[] mappings = new short[(int)(this.field_15634 + 1L)];
         int idx = 0;

         for (long word : this.field_15631) {
            long bits = word;

            for (int elementIdx = 0; elementIdx < this.field_24079; elementIdx++) {
               int value = (int)(bits & this.field_15634);
               int remappedId = mappings[value];
               if (remappedId == 0) {
                  remappedId = dstPalette.method_12291(srcPalette.method_12288(value)) + 1;
                  mappings[value] = (short)remappedId;
               }

               out[idx] = (short)(remappedId - 1);
               bits >>= this.field_15633;
               if (++idx >= this.field_15632) {
                  return;
               }
            }
         }
      }
   }
}
