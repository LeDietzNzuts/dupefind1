package net.raphimc.immediatelyfast.injection.mixins.core;

import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = class_1921.class, priority = 500)
public abstract class MixinRenderLayer {
   @ModifyArg(
      method = {
            "method_34834(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;",
            "method_34833(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;",
            "method_36437(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;",
            "method_36436(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;",
            "method_37348(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;",
            "method_37347(Lnet/minecraft/class_2960;)Lnet/minecraft/class_1921;"
      },
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1921;method_24049(Ljava/lang/String;Lnet/minecraft/class_293;Lnet/minecraft/class_293$class_5596;IZZLnet/minecraft/class_1921$class_4688;)Lnet/minecraft/class_1921$class_4687;"
      ),
      index = 5
   )
   private static boolean changeTranslucency(boolean value) {
      return false;
   }
}
