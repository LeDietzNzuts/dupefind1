package net.caffeinemc.mods.lithium.mixin.world.combined_heightmap_update;

import java.util.Map;
import net.caffeinemc.mods.lithium.common.world.chunk.heightmap.CombinedHeightmapUpdate;
import net.minecraft.class_1923;
import net.minecraft.class_1959;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_2680;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_2826;
import net.minecraft.class_2843;
import net.minecraft.class_2902;
import net.minecraft.class_5539;
import net.minecraft.class_6749;
import net.minecraft.class_2902.class_2903;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_2818.class)
public abstract class LevelChunkMixin extends class_2791 {
   public LevelChunkMixin(
      class_1923 pos,
      class_2843 upgradeData,
      class_5539 heightLimitView,
      class_2378<class_1959> biome,
      long inhabitedTime,
      @Nullable class_2826[] sectionArrayInitializer,
      @Nullable class_6749 blendingData
   ) {
      super(pos, upgradeData, heightLimitView, biome, inhabitedTime, sectionArrayInitializer, blendingData);
   }

   @Redirect(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;")
   )
   private <K, V> V skipGetHeightmap(Map<K, V> heightmaps, K heightmapType) {
      return heightmapType != class_2903.field_13197
            && heightmapType != class_2903.field_13203
            && heightmapType != class_2903.field_13200
            && heightmapType != class_2903.field_13202
         ? heightmaps.get(heightmapType)
         : null;
   }

   @Redirect(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2902;method_12597(IIILnet/minecraft/class_2680;)Z")
   )
   private boolean skipHeightmapUpdate(class_2902 instance, int x, int y, int z, class_2680 state) {
      return instance == null ? false : instance.method_12597(x, y, z, state);
   }

   @Inject(
      method = "method_12010(Lnet/minecraft/class_2338;Lnet/minecraft/class_2680;Z)Lnet/minecraft/class_2680;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2902;method_12597(IIILnet/minecraft/class_2680;)Z", shift = Shift.BEFORE, ordinal = 0),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void updateHeightmapsCombined(
      class_2338 pos,
      class_2680 state,
      boolean moved,
      CallbackInfoReturnable<class_2680> cir,
      int y,
      class_2826 chunkSection,
      boolean bl,
      int x,
      int yMod16,
      int z,
      class_2680 blockState,
      class_2248 block
   ) {
      class_2902 heightmap0 = (class_2902)this.field_34541.get(class_2903.field_13197);
      class_2902 heightmap1 = (class_2902)this.field_34541.get(class_2903.field_13203);
      class_2902 heightmap2 = (class_2902)this.field_34541.get(class_2903.field_13200);
      class_2902 heightmap3 = (class_2902)this.field_34541.get(class_2903.field_13202);
      CombinedHeightmapUpdate.updateHeightmaps(heightmap0, heightmap1, heightmap2, heightmap3, (class_2818)this, x, y, z, state);
   }
}
