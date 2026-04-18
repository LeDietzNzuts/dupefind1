package dev.architectury.mixin.fabric;

import dev.architectury.extensions.ItemExtension;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1661.class)
public class MixinInventory {
   @Shadow
   @Final
   public class_2371<class_1799> field_7548;
   @Shadow
   @Final
   public class_1657 field_7546;

   @Inject(method = "tick", at = @At("RETURN"))
   private void updateItems(CallbackInfo ci) {
      for (class_1799 stack : this.field_7548) {
         if (stack.method_7909() instanceof ItemExtension extension) {
            extension.tickArmor(stack, this.field_7546);
         }
      }
   }
}
