package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.sleeping;

import net.minecraft.class_2338;
import net.minecraft.class_3218;
import net.minecraft.class_5562;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3218.class)
public class ServerLevelMixin {
   @Redirect(
      method = "method_21626(Ljava/io/Writer;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5562;method_31705()Lnet/minecraft/class_2338;")
   )
   private class_2338 getPosOrOrigin(class_5562 instance) {
      class_2338 pos = instance.method_31705();
      return pos == null ? class_2338.field_10980 : pos;
   }
}
