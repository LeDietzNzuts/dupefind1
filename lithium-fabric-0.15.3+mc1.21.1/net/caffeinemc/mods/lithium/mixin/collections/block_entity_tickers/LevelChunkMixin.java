package net.caffeinemc.mods.lithium.mixin.collections.block_entity_tickers;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_6749;
import net.minecraft.class_6755;
import net.minecraft.class_2818.class_6829;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2818.class)
public class LevelChunkMixin {
   @Mutable
   @Shadow
   @Final
   private Map<class_2338, ?> field_27222;

   @Inject(
      method = "<init>(Lnet/minecraft/class_1937;Lnet/minecraft/class_1923;Lnet/minecraft/class_2843;Lnet/minecraft/class_6755;Lnet/minecraft/class_6755;J[Lnet/minecraft/class_2826;Lnet/minecraft/class_2818$class_6829;Lnet/minecraft/class_6749;)V",
      at = @At(value = "INVOKE_ASSIGN", target = "Lcom/google/common/collect/Maps;newHashMap()Ljava/util/HashMap;", shift = Shift.AFTER, remap = false)
   )
   @Coerce
   private void createFastUtilMap(
      class_1937 world,
      class_1923 pos,
      class_2843 upgradeData,
      class_6755<?> blockTickScheduler,
      class_6755<?> fluidTickScheduler,
      long inhabitedTime,
      class_2826[] sectionArrayInitializer,
      class_6829 entityLoader,
      class_6749 blendingData,
      CallbackInfo ci
   ) {
      this.field_27222 = new Object2ObjectOpenHashMap();
   }
}
