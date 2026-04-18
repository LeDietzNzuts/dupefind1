package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_1950;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_3610;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1950.class)
public abstract class PathNavigationRegionMixin implements class_1922 {
   private static final class_2680 DEFAULT_BLOCK = class_2246.field_10124.method_9564();
   @Shadow
   @Final
   protected class_2791[][] field_9305;
   @Shadow
   @Final
   protected int field_9304;
   @Shadow
   @Final
   protected int field_9303;
   @Shadow
   @Final
   protected class_1937 field_9306;
   private class_2791[] chunksFlat;
   private int xLen;
   private int zLen;
   private int bottomY;
   private int topY;

   @Inject(method = "<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;Lnet/minecraft/class_2338;)V", at = @At("RETURN"))
   private void init(class_1937 world, class_2338 minPos, class_2338 maxPos, CallbackInfo ci) {
      this.xLen = 1 + Pos.ChunkCoord.fromBlockCoord(maxPos.method_10263()) - Pos.ChunkCoord.fromBlockCoord(minPos.method_10263());
      this.zLen = 1 + Pos.ChunkCoord.fromBlockCoord(maxPos.method_10260()) - Pos.ChunkCoord.fromBlockCoord(minPos.method_10260());
      this.chunksFlat = new class_2791[this.xLen * this.zLen];

      for (int x = 0; x < this.xLen; x++) {
         System.arraycopy(this.field_9305[x], 0, this.chunksFlat, x * this.zLen, this.zLen);
      }

      this.bottomY = this.method_31607();
      this.topY = this.method_31600();
   }

   @Overwrite
   public class_2680 method_8320(class_2338 pos) {
      int y = pos.method_10264();
      if (y >= this.bottomY && y < this.topY) {
         int x = pos.method_10263();
         int z = pos.method_10260();
         int chunkX = Pos.ChunkCoord.fromBlockCoord(x) - this.field_9304;
         int chunkZ = Pos.ChunkCoord.fromBlockCoord(z) - this.field_9303;
         if (chunkX >= 0 && chunkX < this.xLen && chunkZ >= 0 && chunkZ < this.zLen) {
            class_2791 chunk = this.chunksFlat[chunkX * this.zLen + chunkZ];
            if (chunk != null) {
               class_2826 section = chunk.method_12006()[Pos.SectionYIndex.fromBlockCoord(this, y)];
               if (section != null) {
                  return section.method_12254(x & 15, y & 15, z & 15);
               }
            }
         }
      }

      return DEFAULT_BLOCK;
   }

   @Overwrite
   public class_3610 method_8316(class_2338 pos) {
      return this.method_8320(pos).method_26227();
   }
}
