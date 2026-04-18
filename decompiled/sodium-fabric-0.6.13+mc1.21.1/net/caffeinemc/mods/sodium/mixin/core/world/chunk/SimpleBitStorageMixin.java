package net.caffeinemc.mods.sodium.mixin.core.world.chunk;

import java.util.Objects;
import net.caffeinemc.mods.sodium.client.world.BitStorageExtension;
import net.minecraft.class_2837;
import net.minecraft.class_3508;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_3508.class)
public class SimpleBitStorageMixin implements BitStorageExtension {
   @Shadow
   @Final
   private long[] field_15631;
   @Shadow
   @Final
   private int field_24079;
   @Shadow
   @Final
   private long field_15634;
   @Shadow
   @Final
   private int field_15633;
   @Shadow
   @Final
   private int field_15632;

   @Override
   public <T> void sodium$unpack(T[] out, class_2837<T> palette) {
      int idx = 0;

      for (long word : this.field_15631) {
         long l = word;

         for (int j = 0; j < this.field_24079; j++) {
            out[idx] = Objects.requireNonNull((T)palette.method_12288((int)(l & this.field_15634)), "Palette does not contain entry for value in storage");
            l >>= this.field_15633;
            if (++idx >= this.field_15632) {
               return;
            }
         }
      }
   }
}
