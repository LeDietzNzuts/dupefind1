package net.raphimc.immediatelyfast.injection.mixins.sign_text_buffering;

import java.util.Arrays;
import java.util.Objects;
import net.minecraft.class_1767;
import net.minecraft.class_2561;
import net.minecraft.class_5481;
import net.minecraft.class_8242;
import net.raphimc.immediatelyfast.injection.interfaces.ISignText;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_8242.class)
public abstract class MixinSignText implements ISignText {
   @Shadow
   @Final
   private class_2561[] field_43301;
   @Shadow
   @Final
   private class_2561[] field_43302;
   @Shadow
   @Final
   private class_1767 field_43303;
   @Shadow
   @Final
   private boolean field_43304;
   @Shadow
   @Nullable
   private class_5481[] field_43305;
   @Unique
   private boolean immediatelyFast$shouldCache;
   @Unique
   private boolean immediatelyFast$checkedShouldCache;
   @Unique
   private int immediatelyFast$cachedHashCode;
   @Unique
   private boolean immediatelyFast$calculatedHashCode;

   @Inject(method = "method_49868(ZLjava/util/function/Function;)[Lnet/minecraft/class_5481;", at = @At("RETURN"))
   private void checkShouldCache(CallbackInfoReturnable<class_5481[]> cir) {
      if (!this.immediatelyFast$checkedShouldCache) {
         this.immediatelyFast$checkedShouldCache = true;
         this.immediatelyFast$shouldCache = true;

         for (class_5481 orderedText : this.field_43305) {
            if (!this.immediatelyFast$shouldCache) {
               break;
            }

            orderedText.accept((index, style, codePoint) -> {
               if (style.method_10987()) {
                  this.immediatelyFast$shouldCache = false;
                  return false;
               } else {
                  return true;
               }
            });
         }
      }
   }

   @Inject(
      method = "method_49868(ZLjava/util/function/Function;)[Lnet/minecraft/class_5481;",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_8242;field_43305:[Lnet/minecraft/class_5481;", opcode = 181)
   )
   private void invalidateCache(CallbackInfoReturnable<class_5481[]> cir) {
      this.immediatelyFast$shouldCache = false;
      this.immediatelyFast$checkedShouldCache = false;
      this.immediatelyFast$cachedHashCode = 0;
      this.immediatelyFast$calculatedHashCode = false;
   }

   @Override
   public boolean immediatelyFast$shouldCache() {
      return this.immediatelyFast$shouldCache;
   }

   @Override
   public void immediatelyFast$setShouldCache(boolean shouldCache) {
      this.immediatelyFast$shouldCache = shouldCache;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         MixinSignText that = (MixinSignText)o;
         return this.field_43304 == that.field_43304
            && this.field_43303 == that.field_43303
            && Arrays.equals((Object[])this.field_43301, (Object[])that.field_43301)
            && Arrays.equals((Object[])this.field_43302, (Object[])that.field_43302);
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      if (!this.immediatelyFast$calculatedHashCode) {
         this.immediatelyFast$calculatedHashCode = true;
         int result = Objects.hash(this.field_43303, this.field_43304);
         result = 31 * result + Arrays.hashCode((Object[])this.field_43301);
         result = 31 * result + Arrays.hashCode((Object[])this.field_43302);
         this.immediatelyFast$cachedHashCode = result;
      }

      return this.immediatelyFast$cachedHashCode;
   }
}
