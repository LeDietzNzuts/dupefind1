package net.caffeinemc.mods.lithium.mixin.ai.task.run.long_jump_weighted_choice;

import com.llamalad7.mixinextras.sugar.Local;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import net.caffeinemc.mods.lithium.common.util.collections.LongJumpChoiceList;
import net.minecraft.class_1308;
import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_5819;
import net.minecraft.class_6011;
import net.minecraft.class_6030;
import net.minecraft.class_6030.class_6031;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_6030.class)
public class LongJumpToRandomPosMixin<E extends class_1308> {
   @Shadow
   protected List<class_6031> field_30142;
   @Shadow
   @Final
   protected int field_30140;
   @Shadow
   @Final
   protected int field_30139;

   @Inject(
      method = "method_35082(Lnet/minecraft/class_3218;Lnet/minecraft/class_1308;J)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2338;method_17962(IIIIII)Ljava/util/stream/Stream;"),
      cancellable = true
   )
   private void setTargets(class_3218 serverWorld, E mobEntity, long l, CallbackInfo ci, @Local class_2338 centerPos) {
      if (this.field_30140 < 128 && this.field_30139 < 128) {
         this.field_30142 = LongJumpChoiceList.forCenter(centerPos, (byte)this.field_30140, (byte)this.field_30139);
         ci.cancel();
      }
   }

   @Redirect(
      method = "method_41336(Lnet/minecraft/class_3218;)Ljava/util/Optional;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_6011;method_34986(Lnet/minecraft/class_5819;Ljava/util/List;)Ljava/util/Optional;")
   )
   private Optional<class_6031> getRandomFast(class_5819 random, List<class_6031> pool) {
      return pool instanceof LongJumpChoiceList longJumpChoiceList
         ? Optional.ofNullable(longJumpChoiceList.removeRandomWeightedByDistanceSq(random))
         : class_6011.method_34986(random, pool);
   }

   @Redirect(
      method = "method_41336(Lnet/minecraft/class_3218;)Ljava/util/Optional;",
      at = @At(value = "INVOKE", target = "Ljava/util/Optional;ifPresent(Ljava/util/function/Consumer;)V")
   )
   private void skipRemoveIfAlreadyRemoved(Optional<class_6031> result, Consumer<? super class_6031> removeAction) {
      if (!(this.field_30142 instanceof LongJumpChoiceList)) {
         result.ifPresent(removeAction);
      }
   }
}
