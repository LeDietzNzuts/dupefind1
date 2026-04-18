package ewewukek.musketmod.mixin;

import ewewukek.musketmod.GunItem;
import ewewukek.musketmod.Items;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1547;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
abstract class EntityMixin {
   @Inject(method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("HEAD"), cancellable = true)
   private void spawnAtLocation(class_1799 stack, CallbackInfoReturnable<class_1542> ci) {
      if (stack.method_7909() == class_1802.field_8107 && this instanceof class_1547 entity && GunItem.isHoldingGun(entity)) {
         class_1799 cartridges = new class_1799(Items.CARTRIDGE, stack.method_7947());
         ci.setReturnValue(entity.method_5699(cartridges, 0.0F));
         ci.cancel();
      }
   }
}
