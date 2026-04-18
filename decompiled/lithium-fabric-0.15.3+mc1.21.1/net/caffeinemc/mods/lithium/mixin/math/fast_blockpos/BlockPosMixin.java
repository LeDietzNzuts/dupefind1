package net.caffeinemc.mods.lithium.mixin.math.fast_blockpos;

import net.minecraft.class_2338;
import net.minecraft.class_2382;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(class_2338.class)
public abstract class BlockPosMixin extends class_2382 {
   public BlockPosMixin(int x, int y, int z) {
      super(x, y, z);
   }

   @Overwrite
   public class_2338 method_10084() {
      return new class_2338(this.method_10263(), this.method_10264() + 1, this.method_10260());
   }

   @Overwrite
   public class_2338 method_10086(int distance) {
      return new class_2338(this.method_10263(), this.method_10264() + distance, this.method_10260());
   }

   @Overwrite
   public class_2338 method_10074() {
      return new class_2338(this.method_10263(), this.method_10264() - 1, this.method_10260());
   }

   @Overwrite
   public class_2338 method_10087(int distance) {
      return new class_2338(this.method_10263(), this.method_10264() - distance, this.method_10260());
   }

   @Overwrite
   public class_2338 method_10095() {
      return new class_2338(this.method_10263(), this.method_10264(), this.method_10260() - 1);
   }

   @Overwrite
   public class_2338 method_10076(int distance) {
      return new class_2338(this.method_10263(), this.method_10264(), this.method_10260() - distance);
   }

   @Overwrite
   public class_2338 method_10072() {
      return new class_2338(this.method_10263(), this.method_10264(), this.method_10260() + 1);
   }

   @Overwrite
   public class_2338 method_10077(int distance) {
      return new class_2338(this.method_10263(), this.method_10264(), this.method_10260() + distance);
   }

   @Overwrite
   public class_2338 method_10067() {
      return new class_2338(this.method_10263() - 1, this.method_10264(), this.method_10260());
   }

   @Overwrite
   public class_2338 method_10088(int distance) {
      return new class_2338(this.method_10263() - distance, this.method_10264(), this.method_10260());
   }

   @Overwrite
   public class_2338 method_10078() {
      return new class_2338(this.method_10263() + 1, this.method_10264(), this.method_10260());
   }

   @Overwrite
   public class_2338 method_10089(int distance) {
      return new class_2338(this.method_10263() + distance, this.method_10264(), this.method_10260());
   }
}
