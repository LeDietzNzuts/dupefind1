package net.caffeinemc.mods.sodium.mixin.core.world.chunk;

import java.util.Arrays;
import java.util.Objects;
import net.caffeinemc.mods.sodium.client.world.BitStorageExtension;
import net.minecraft.class_2837;
import net.minecraft.class_6502;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_6502.class)
public class ZeroBitStorageMixin implements BitStorageExtension {
   @Shadow
   @Final
   private int field_34402;

   @Override
   public <T> void sodium$unpack(T[] out, class_2837<T> palette) {
      if (this.field_34402 != out.length) {
         throw new IllegalArgumentException("Array has mismatched size");
      } else {
         T defaultValue = Objects.requireNonNull((T)palette.method_12288(0), "Palette must have default entry");
         Arrays.fill(out, defaultValue);
      }
   }
}
