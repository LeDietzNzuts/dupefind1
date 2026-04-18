package dev.architectury.mixin.fabric;

import dev.architectury.hooks.level.entity.fabric.EntityHooksImpl;
import net.minecraft.class_1297;
import net.minecraft.class_5569;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(class_1297.class)
public class MixinEntity {
   @ModifyVariable(method = "setLevelCallback", argsOnly = true, ordinal = 0, at = @At("HEAD"))
   public class_5569 modifyLevelCallback_setLevelCallback(class_5569 callback) {
      return EntityHooksImpl.wrapEntityInLevelCallback((class_1297)this, callback);
   }
}
