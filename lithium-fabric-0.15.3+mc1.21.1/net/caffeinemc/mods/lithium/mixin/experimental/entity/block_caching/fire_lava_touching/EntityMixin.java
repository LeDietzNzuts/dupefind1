package net.caffeinemc.mods.lithium.mixin.experimental.entity.block_caching.fire_lava_touching;

import java.util.function.Predicate;
import java.util.stream.Stream;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCache;
import net.caffeinemc.mods.lithium.common.tracking.block.BlockCacheProvider;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3481;
import net.minecraft.class_3532;
import net.minecraft.class_2338.class_2339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1297.class)
public abstract class EntityMixin implements BlockCacheProvider {
   private static final Stream<class_2680> EMPTY_BLOCKSTATE_STREAM = Stream.empty();
   @Shadow
   private int field_5956;
   @Shadow
   public boolean field_28629;
   @Shadow
   public boolean field_27857;

   @Shadow
   protected abstract int method_5676();

   @Shadow
   public abstract boolean method_5637();

   @Redirect(
      method = "method_5784(Lnet/minecraft/class_1313;Lnet/minecraft/class_243;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_29556(Lnet/minecraft/class_238;)Ljava/util/stream/Stream;")
   )
   private Stream<class_2680> skipFireTestIfResultDoesNotMatterOrIsCached(class_1937 world, class_238 box) {
      if (this.field_5956 <= 0 && this.field_5956 != -this.method_5676() || this.field_28629 && (this.field_27857 || this.method_5637())) {
         BlockCache bc = this.getUpdatedBlockCache((class_1297)this);
         byte cachedTouchingFireLava = bc.getIsTouchingFireLava();
         if (cachedTouchingFireLava == 0) {
            return null;
         } else if (cachedTouchingFireLava == 1) {
            return EMPTY_BLOCKSTATE_STREAM;
         } else {
            int minX = class_3532.method_15357(box.field_1323);
            int maxX = class_3532.method_15357(box.field_1320);
            int minY = class_3532.method_15357(box.field_1322);
            int maxY = class_3532.method_15357(box.field_1325);
            int minZ = class_3532.method_15357(box.field_1321);
            int maxZ = class_3532.method_15357(box.field_1324);
            if (maxY >= world.method_31607() && minY < world.method_31600() && world.method_33597(minX, minZ, maxX, maxZ)) {
               class_2339 blockPos = new class_2339();

               for (int y = minY; y <= maxY; y++) {
                  for (int z = minZ; z <= maxZ; z++) {
                     for (int x = minX; x <= maxX; x++) {
                        blockPos.method_10103(x, y, z);
                        class_2680 state = world.method_8320(blockPos);
                        if (state.method_26164(class_3481.field_21952) || state.method_27852(class_2246.field_10164)) {
                           bc.setCachedTouchingFireLava(true);
                           return EMPTY_BLOCKSTATE_STREAM;
                        }
                     }
                  }
               }
            }

            bc.setCachedTouchingFireLava(false);
            return null;
         }
      } else {
         return null;
      }
   }

   @Redirect(
      method = "method_5784(Lnet/minecraft/class_1313;Lnet/minecraft/class_243;)V",
      at = @At(value = "INVOKE", target = "Ljava/util/stream/Stream;noneMatch(Ljava/util/function/Predicate;)Z")
   )
   private boolean skipNullStream(Stream<class_2680> stream, Predicate<class_2680> predicate) {
      return stream == null;
   }
}
