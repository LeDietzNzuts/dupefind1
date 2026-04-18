package net.caffeinemc.mods.lithium.mixin.entity.fast_retrieval;

import net.minecraft.class_238;
import net.minecraft.class_4076;
import net.minecraft.class_5568;
import net.minecraft.class_5572;
import net.minecraft.class_5573;
import net.minecraft.class_7927;
import net.minecraft.class_7927.class_7928;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_5573.class)
public abstract class EntitySectionStorageMixin<T extends class_5568> {
   @Shadow
   @Nullable
   public abstract class_5572<T> method_31785(long var1);

   @Inject(
      method = "method_31777(Lnet/minecraft/class_238;Lnet/minecraft/class_7927;)V",
      at = @At(value = "INVOKE_ASSIGN", shift = Shift.AFTER, target = "Lnet/minecraft/class_4076;method_32204(D)I", ordinal = 5),
      locals = LocalCapture.CAPTURE_FAILHARD,
      cancellable = true
   )
   public void forEachInBox(class_238 box, class_7927<class_5572<T>> action, CallbackInfo ci, int i, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
      if (maxX < minX + 4 && maxZ < minZ + 4) {
         ci.cancel();

         for (int x = minX; x <= maxX; x++) {
            for (int z = Math.max(minZ, 0); z <= maxZ; z++) {
               if (this.forEachInColumn(x, minY, maxY, z, action).method_47543()) {
                  return;
               }
            }

            int bound = Math.min(-1, maxZ);

            for (int zx = minZ; zx <= bound; zx++) {
               if (this.forEachInColumn(x, minY, maxY, zx, action).method_47543()) {
                  return;
               }
            }
         }
      }
   }

   private class_7928 forEachInColumn(int x, int minY, int maxY, int z, class_7927<class_5572<T>> action) {
      class_7928 ret = class_7928.field_41283;

      for (int y = Math.max(minY, 0); y <= maxY; y++) {
         if ((ret = this.consumeSection(class_4076.method_18685(x, y, z), action)).method_47543()) {
            return ret;
         }
      }

      int bound = Math.min(-1, maxY);

      for (int yx = minY; yx <= bound; yx++) {
         if ((ret = this.consumeSection(class_4076.method_18685(x, yx, z), action)).method_47543()) {
            return ret;
         }
      }

      return ret;
   }

   private class_7928 consumeSection(long pos, class_7927<class_5572<T>> action) {
      class_5572<T> section = this.method_31785(pos);
      return section != null && 0 != section.method_31769() && section.method_31768().method_31885() ? action.accept(section) : class_7928.field_41283;
   }
}
