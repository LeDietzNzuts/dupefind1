package net.caffeinemc.mods.lithium.mixin.world.raycast;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.caffeinemc.mods.lithium.common.util.Pos;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2826;
import net.minecraft.class_3218;
import net.minecraft.class_3610;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4538;
import net.minecraft.class_3959.class_242;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1922.class)
public interface BlockGetterMixin {
   @Shadow
   class_2680 method_8320(class_2338 var1);

   @Shadow
   @Nullable
   class_3965 method_17745(class_243 var1, class_243 var2, class_2338 var3, class_265 var4, class_2680 var5);

   @Shadow
   static <T, C> T method_17744(class_243 start, class_243 end, C context, BiFunction<C, class_2338, T> blockHitFactory, Function<C, T> missFactory) {
      throw new AssertionError();
   }

   @Shadow(aliases = "lambda$clip$2")
   class_3965 method_17743(class_3959 var1, class_2338 var2);

   @Shadow(aliases = "lambda$clip$3")
   static class_3965 method_17746(class_3959 par1) {
      throw new AssertionError();
   }

   @Overwrite
   default class_3965 method_17742(class_3959 context) {
      return method_17744(
         context.method_17750(),
         context.method_17747(),
         context,
         !(this instanceof class_3218) && !(this instanceof class_1937 l && l.method_8608()) ? this::method_17743 : this.lithium$blockHitFactory(context),
         BlockGetterMixin::method_17746
      );
   }

   @Unique
   private BiFunction<class_3959, class_2338, class_3965> lithium$blockHitFactory(class_3959 context) {
      return new BiFunction<class_3959, class_2338, class_3965>() {
         int chunkX = Integer.MIN_VALUE;
         int chunkZ = Integer.MIN_VALUE;
         class_2791 chunk = null;
         final boolean handleFluids = ((ClipContextAccessor)context).getFluidHandling() != class_242.field_1348;

         public class_3965 apply(class_3959 innerContext, class_2338 pos) {
            class_2680 blockState = this.getBlock((class_4538)BlockGetterMixin.this, pos);
            class_243 start = innerContext.method_17750();
            class_243 end = innerContext.method_17747();
            class_265 blockShape = innerContext.method_17748(blockState, (class_1922)BlockGetterMixin.this, pos);
            class_3965 blockHitResult = BlockGetterMixin.this.method_17745(start, end, pos, blockShape, blockState);
            double d = blockHitResult == null ? Double.MAX_VALUE : innerContext.method_17750().method_1025(blockHitResult.method_17784());
            double e = Double.MAX_VALUE;
            class_3965 fluidHitResult = null;
            if (this.handleFluids) {
               class_3610 fluidState = blockState.method_26227();
               class_265 fluidShape = innerContext.method_17749(fluidState, (class_1922)BlockGetterMixin.this, pos);
               fluidHitResult = fluidShape.method_1092(start, end, pos);
               e = fluidHitResult == null ? Double.MAX_VALUE : innerContext.method_17750().method_1025(fluidHitResult.method_17784());
            }

            return d <= e ? blockHitResult : fluidHitResult;
         }

         private class_2680 getBlock(class_4538 world, class_2338 blockPos) {
            if (world.method_31601(blockPos.method_10264())) {
               return class_2246.field_10243.method_9564();
            } else {
               int chunkX = Pos.ChunkCoord.fromBlockCoord(blockPos.method_10263());
               int chunkZ = Pos.ChunkCoord.fromBlockCoord(blockPos.method_10260());
               if (this.chunkX != chunkX || this.chunkZ != chunkZ) {
                  this.chunk = world.method_8392(chunkX, chunkZ);
                  this.chunkX = chunkX;
                  this.chunkZ = chunkZ;
               }

               class_2791 chunk = this.chunk;
               if (chunk != null) {
                  class_2826 section = chunk.method_12006()[Pos.SectionYIndex.fromBlockCoord(chunk, blockPos.method_10264())];
                  if (section != null && !section.method_38292()) {
                     return section.method_12254(blockPos.method_10263() & 15, blockPos.method_10264() & 15, blockPos.method_10260() & 15);
                  }
               }

               return class_2246.field_10124.method_9564();
            }
         }
      };
   }
}
