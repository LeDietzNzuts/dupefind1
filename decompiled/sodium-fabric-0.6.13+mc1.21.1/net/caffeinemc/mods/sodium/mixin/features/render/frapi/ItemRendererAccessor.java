package net.caffeinemc.mods.sodium.mixin.features.render.frapi;

import net.minecraft.class_1799;
import net.minecraft.class_918;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_918.class)
public interface ItemRendererAccessor {
   @Invoker("method_51795")
   static boolean sodium$hasAnimatedTexture(class_1799 stack) {
      throw new AssertionError();
   }
}
