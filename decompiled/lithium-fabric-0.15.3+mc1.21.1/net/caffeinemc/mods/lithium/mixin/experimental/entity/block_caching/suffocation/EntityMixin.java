package net.caffeinemc.mods.lithium.mixin.experimental.entity.block_caching.suffocation;

import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_247;
import net.minecraft.class_259;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3532;
import net.minecraft.class_4048;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_1297.class)
public abstract class EntityMixin implements BlockCacheProvider {
   @Shadow
   public class_1937 field_6002;
   @Shadow
   private class_4048 field_18065;

   protected EntityMixin(class_4048 dimensions) {
      this.field_18065 = dimensions;
   }

   @Inject(
      method = "method_5757()Z",
      cancellable = true,
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2338;method_29715(Lnet/minecraft/class_238;)Ljava/util/stream/Stream;", shift = Shift.BEFORE),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   public void isInsideWall(CallbackInfoReturnable<Boolean> cir, float f, class_238 box) {
      int minX = class_3532.method_15357(box.field_1323);
      int minY = class_3532.method_15357(box.field_1322);
      int minZ = class_3532.method_15357(box.field_1321);
      int maxX = class_3532.method_15357(box.field_1320);
      int maxY = class_3532.method_15357(box.field_1325);
      int maxZ = class_3532.method_15357(box.field_1324);
      BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
      byte cachedSuffocation = bc.getIsSuffocating();
      if (cachedSuffocation == 0) {
         cir.setReturnValue(false);
      } else if (cachedSuffocation == 1) {
         cir.setReturnValue(true);
      } else {
         class_1937 world = this.field_6002;
         if (world.method_31607() <= maxY && world.method_31600() >= minY) {
            class_2339 blockPos = new class_2339();
            class_265 suffocationShape = null;
            boolean shouldCache = true;

            for (int y = minY; y <= maxY; y++) {
               for (int z = minZ; z <= maxZ; z++) {
                  for (int x = minX; x <= maxX; x++) {
                     blockPos.method_10103(x, y, z);
                     class_2680 blockState = world.method_8320(blockPos);
                     if (!blockState.method_26215() && blockState.method_26228(this.field_6002, blockPos)) {
                        if (shouldCache && blockState.method_26164(class_3481.field_21490)) {
                           shouldCache = false;
                        }

                        if (suffocationShape == null) {
                           suffocationShape = class_259.method_1078(
                              new class_238(box.field_1323, box.field_1322, box.field_1321, box.field_1320, box.field_1325, box.field_1324)
                           );
                        }

                        if (class_259.method_1074(
                           blockState.method_26220(this.field_6002, blockPos)
                              .method_1096(blockPos.method_10263(), blockPos.method_10264(), blockPos.method_10260()),
                           suffocationShape,
                           class_247.field_16896
                        )) {
                           if (shouldCache) {
                              bc.setCachedIsSuffocating(true);
                           }

                           cir.setReturnValue(true);
                           return;
                        }
                     }
                  }
               }
            }

            if (shouldCache) {
               bc.setCachedIsSuffocating(false);
            }

            cir.setReturnValue(false);
         } else {
            bc.setCachedIsSuffocating(false);
            cir.setReturnValue(false);
         }
      }
   }
}
