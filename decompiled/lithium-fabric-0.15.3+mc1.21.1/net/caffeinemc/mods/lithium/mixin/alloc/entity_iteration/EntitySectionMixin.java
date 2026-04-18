package net.caffeinemc.mods.lithium.mixin.alloc.entity_iteration;

import java.util.Iterator;
import net.minecraft.class_3509;
import net.minecraft.class_5572;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_5572.class)
public class EntitySectionMixin {
   @Redirect(
      method = "method_31765(Lnet/minecraft/class_238;Lnet/minecraft/class_7927;)Lnet/minecraft/class_7927$class_7928;",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3509;iterator()Ljava/util/Iterator;")
   )
   private Iterator<?> directIterator(class_3509<?> instance) {
      return ((ClassInstanceMultiMapAccessor)instance).getAllInstances().iterator();
   }
}
