package net.caffeinemc.mods.lithium.mixin.world.inline_block_access;

import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_4076;
import net.minecraft.class_5539;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_5539 {
   private static final class_2680 OUTSIDE_WORLD_BLOCK = class_2246.field_10243.method_9564();
   private static final class_2680 INSIDE_WORLD_DEFAULT_BLOCK = class_2246.field_10124.method_9564();

   @Shadow
   public abstract class_2818 method_8497(int var1, int var2);

   @Overwrite
   public class_2680 method_8320(class_2338 pos) {
      class_2818 worldChunk = this.method_8497(class_4076.method_18675(pos.method_10263()), class_4076.method_18675(pos.method_10260()));
      class_2826[] sections = worldChunk.method_12006();
      int x = pos.method_10263();
      int y = pos.method_10264();
      int z = pos.method_10260();
      int chunkY = this.method_31602(y);
      if (chunkY >= 0 && chunkY < sections.length && !worldChunk.method_12223()) {
         class_2826 section = sections[chunkY];
         return section != null && !section.method_38292() ? section.method_12254(x & 15, y & 15, z & 15) : INSIDE_WORLD_DEFAULT_BLOCK;
      } else {
         return OUTSIDE_WORLD_BLOCK;
      }
   }

   @Redirect(
      method = "method_8316(Lnet/minecraft/class_2338;)Lnet/minecraft/class_3610;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_31606(Lnet/minecraft/class_2338;)Z")
   )
   private boolean skipFluidHeightLimitTest(class_1937 world, class_2338 pos) {
      return world.method_31606(pos);
   }
}
