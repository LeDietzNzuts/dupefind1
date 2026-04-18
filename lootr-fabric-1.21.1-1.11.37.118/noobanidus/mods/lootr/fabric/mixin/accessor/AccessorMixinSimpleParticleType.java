package noobanidus.mods.lootr.fabric.mixin.accessor;

import net.minecraft.class_2400;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_2400.class)
public interface AccessorMixinSimpleParticleType {
   @Invoker("<init>")
   static class_2400 lootr$invokeConstructor(boolean p_123456_) {
      throw new UnsupportedOperationException();
   }
}
