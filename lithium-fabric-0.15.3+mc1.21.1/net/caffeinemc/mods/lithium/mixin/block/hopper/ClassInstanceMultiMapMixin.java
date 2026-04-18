package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.minecraft.class_3509;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = class_3509.class, priority = 1010)
public class ClassInstanceMultiMapMixin<T> {
   @Redirect(
      method = "method_15216(Ljava/lang/Class;)Ljava/util/Collection;",
      at = @At(value = "INVOKE", target = "Ljava/lang/Class;isAssignableFrom(Ljava/lang/Class;)Z", remap = false),
      require = 0,
      expect = 0
   )
   private boolean isAlwaysAssignable(Class<?> aClass, Class<?> cls) {
      return true;
   }
}
