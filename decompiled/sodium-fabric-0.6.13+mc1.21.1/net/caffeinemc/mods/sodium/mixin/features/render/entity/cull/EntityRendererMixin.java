package net.caffeinemc.mods.sodium.mixin.features.render.entity.cull;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.render.SodiumWorldRenderer;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_4604;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_897.class)
public abstract class EntityRendererMixin<T extends class_1297> {
   @WrapOperation(
      method = "method_3933(Lnet/minecraft/class_1297;Lnet/minecraft/class_4604;DDD)Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4604;method_23093(Lnet/minecraft/class_238;)Z", ordinal = 0)
   )
   private boolean preShouldRender(class_4604 instance, class_238 aABB, Operation<Boolean> original, T entity) {
      SodiumWorldRenderer renderer = SodiumWorldRenderer.instanceNullable();
      return renderer == null
         ? (Boolean)original.call(new Object[]{instance, aABB})
         : renderer.isEntityVisible(entity) && (Boolean)original.call(new Object[]{instance, aABB});
   }
}
