package net.caffeinemc.mods.sodium.mixin.core.world.chunk;

import java.util.Objects;
import net.caffeinemc.mods.sodium.client.world.BitStorageExtension;
import net.caffeinemc.mods.sodium.client.world.PalettedContainerROExtension;
import net.minecraft.class_2837;
import net.minecraft.class_2841;
import net.minecraft.class_6490;
import net.minecraft.class_7522;
import net.minecraft.class_2841.class_6561;
import net.minecraft.class_2841.class_6563;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_2841.class)
public abstract class PalettedContainerMixin<T> implements PalettedContainerROExtension<T> {
   @Shadow
   private volatile class_6561<T> field_34560;
   @Shadow
   @Final
   private class_6563 field_34561;

   @Shadow
   public abstract class_2841<T> method_39957();

   @Override
   public void sodium$unpack(T[] values) {
      class_6563 strategy = Objects.requireNonNull(this.field_34561);
      if (values.length != strategy.method_38312()) {
         throw new IllegalArgumentException("Array is wrong size");
      } else {
         class_6561<T> data = Objects.requireNonNull(this.field_34560, "PalettedContainer must have data");
         BitStorageExtension storage = (BitStorageExtension)data.comp_118();
         storage.sodium$unpack(values, data.comp_119());
      }
   }

   @Override
   public void sodium$unpack(T[] values, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
      class_6563 strategy = Objects.requireNonNull(this.field_34561);
      if (values.length != strategy.method_38312()) {
         throw new IllegalArgumentException("Array is wrong size");
      } else {
         class_6561<T> data = Objects.requireNonNull(this.field_34560, "PalettedContainer must have data");
         class_6490 storage = data.comp_118();
         class_2837<T> palette = data.comp_119();

         for (int y = minY; y <= maxY; y++) {
            for (int z = minZ; z <= maxZ; z++) {
               for (int x = minX; x <= maxX; x++) {
                  int localBlockIndex = strategy.method_38313(x, y, z);
                  int paletteIndex = storage.method_15211(localBlockIndex);
                  T paletteValue = (T)palette.method_12288(paletteIndex);
                  values[localBlockIndex] = paletteValue;
               }
            }
         }
      }
   }

   @Override
   public class_7522<T> sodium$copy() {
      return this.method_39957();
   }
}
