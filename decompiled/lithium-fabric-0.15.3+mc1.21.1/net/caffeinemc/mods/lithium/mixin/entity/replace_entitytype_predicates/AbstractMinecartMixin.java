package net.caffeinemc.mods.lithium.mixin.entity.replace_entitytype_predicates;

import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1688;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1688.class)
public class AbstractMinecartMixin {
   @Redirect(
      method = "method_5773()V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_1937;method_8335(Lnet/minecraft/class_1297;Lnet/minecraft/class_238;)Ljava/util/List;")
   )
   private List<class_1688> getOtherAbstractMinecarts(class_1937 world, class_1297 except, class_238 box) {
      return world.method_8390(class_1688.class, box, entity -> entity != except);
   }
}
