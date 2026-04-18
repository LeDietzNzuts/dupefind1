package net.caffeinemc.mods.lithium.mixin.world.game_events.dispatch;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.caffeinemc.mods.lithium.common.util.ChunkConstants;
import net.caffeinemc.mods.lithium.common.world.LithiumData;
import net.minecraft.class_1923;
import net.minecraft.class_243;
import net.minecraft.class_2791;
import net.minecraft.class_2818;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import net.minecraft.class_5712;
import net.minecraft.class_5713;
import net.minecraft.class_6880;
import net.minecraft.class_7719;
import net.minecraft.class_5712.class_7397;
import net.minecraft.class_5713.class_7721;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_7719.class)
public class GameEventDispatcherMixin {
   @Shadow
   @Final
   private class_3218 field_40352;

   @Redirect(
      method = "method_45490(Lnet/minecraft/class_6880;Lnet/minecraft/class_243;Lnet/minecraft/class_5712$class_7397;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3215;method_21730(II)Lnet/minecraft/class_2818;")
   )
   private class_2818 doNotGetChunk(class_3215 instance, int chunkX, int chunkZ) {
      return ChunkConstants.DUMMY_CHUNK;
   }

   @Redirect(
      method = "method_45490(Lnet/minecraft/class_6880;Lnet/minecraft/class_243;Lnet/minecraft/class_5712$class_7397;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2791;method_32914(I)Lnet/minecraft/class_5713;")
   )
   private class_5713 getNull(class_2791 chunk, int ySectionCoord) {
      return null;
   }

   @Redirect(
      method = "method_45490(Lnet/minecraft/class_6880;Lnet/minecraft/class_243;Lnet/minecraft/class_5712$class_7397;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5713;method_32943(Lnet/minecraft/class_6880;Lnet/minecraft/class_243;Lnet/minecraft/class_5712$class_7397;Lnet/minecraft/class_5713$class_7721;)Z"
      )
   )
   private boolean handleNullDispatcher(
      @Nullable class_5713 dispatcher,
      class_6880<class_5712> gameEventRegistryEntry,
      class_243 vec3d,
      class_7397 emitter,
      class_7721 dispatchCallback,
      @Local(ordinal = 7) int chunkX,
      @Local(ordinal = 8) int chunkZ,
      @Local(ordinal = 9) int ySectionCoord
   ) {
      if (dispatcher == null) {
         Int2ObjectMap<class_5713> yToDispatcherMap = ((LithiumData)this.field_40352)
            .lithium$getData()
            .gameEventDispatchers()
            .get(class_1923.method_8331(chunkX, chunkZ));
         dispatcher = yToDispatcherMap == null ? null : (class_5713)yToDispatcherMap.get(ySectionCoord);
      }

      return dispatcher != null && dispatcher.method_32943(gameEventRegistryEntry, vec3d, emitter, dispatchCallback);
   }
}
