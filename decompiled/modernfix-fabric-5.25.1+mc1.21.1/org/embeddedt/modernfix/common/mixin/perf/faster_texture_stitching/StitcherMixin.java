package org.embeddedt.modernfix.common.mixin.perf.faster_texture_stitching;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import net.minecraft.class_1054;
import net.minecraft.class_1055;
import net.minecraft.class_1055.class_1056;
import net.minecraft.class_1055.class_4726;
import net.minecraft.class_1055.class_7769;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;
import org.embeddedt.modernfix.textures.StbStitcher;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1055.class)
@ClientOnlyMixin
public class StitcherMixin<T extends class_7769> {
   @Shadow
   @Final
   private List<class_1056<T>> field_5237;
   @Shadow
   private int field_5242;
   @Shadow
   private int field_5241;
   @Shadow
   @Final
   private int field_5240;
   @Shadow
   @Final
   private int field_5238;
   @Shadow
   @Final
   private static Comparator<class_1056<?>> field_18030;
   private List<StbStitcher.LoadableSpriteInfo<T>> loadableSpriteInfos;

   @Inject(method = "stitch", at = @At("HEAD"), cancellable = true)
   private void stitchFast(CallbackInfo ci) {
      this.loadableSpriteInfos = null;
      if (!ModernFixPlatformHooks.INSTANCE.isLoadingNormally()) {
         ModernFix.LOGGER.error("Using vanilla stitcher implementation due to invalid loading state");
      } else if (this.field_5237.size() >= 100) {
         ci.cancel();
         ObjectArrayList<class_1056<T>> holderList = new ObjectArrayList(this.field_5237);
         holderList.sort(field_18030);
         class_1056<T>[] aholder = (class_1056<T>[])holderList.toArray(new class_1056[0]);
         Pair<Pair<Integer, Integer>, List<StbStitcher.LoadableSpriteInfo<T>>> packingInfo = StbStitcher.packRects(aholder);
         this.field_5242 = (Integer)((Pair)packingInfo.getFirst()).getFirst();
         this.field_5241 = (Integer)((Pair)packingInfo.getFirst()).getSecond();
         if (this.field_5242 <= this.field_5240 && this.field_5241 <= this.field_5238) {
            this.loadableSpriteInfos = (List<StbStitcher.LoadableSpriteInfo<T>>)packingInfo.getSecond();
         } else {
            ModernFix.LOGGER.error("Requested atlas size {}x{} exceeds maximum of {}x{}", this.field_5242, this.field_5241, this.field_5240, this.field_5238);
            throw new class_1054(aholder[0].comp_1046(), Stream.of(aholder).map(arg -> arg.comp_1046()).collect(ImmutableList.toImmutableList()));
         }
      }
   }

   @Inject(method = "gatherSprites", at = @At("HEAD"), cancellable = true)
   private void gatherSpritesFast(class_4726<T> spriteLoader, CallbackInfo ci) {
      if (this.loadableSpriteInfos != null) {
         ci.cancel();

         for (StbStitcher.LoadableSpriteInfo<T> info : this.loadableSpriteInfos) {
            spriteLoader.load(info.info, info.x, info.y);
         }
      }
   }
}
