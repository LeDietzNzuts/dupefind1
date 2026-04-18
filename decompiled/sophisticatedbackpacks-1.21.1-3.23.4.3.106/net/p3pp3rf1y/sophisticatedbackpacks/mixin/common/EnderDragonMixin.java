package net.p3pp3rf1y.sophisticatedbackpacks.mixin.common;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1510;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_6862;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BlockInterfaceHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1510.class)
public abstract class EnderDragonMixin extends class_1308 {
   protected EnderDragonMixin(class_1299<? extends class_1308> entityType, class_1937 level) {
      super(entityType, level);
   }

   @Redirect(method = "checkWalls", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"))
   public boolean sophisticatedBackpacks$checkWalls(class_2680 blockState, class_6862<class_2248> tagKey, @Local class_2338 blockPos) {
      return blockState.method_26204() instanceof BlockInterfaceHelper bih
         ? !bih.canEntityDestroy(blockState, this.method_37908(), blockPos, this)
         : blockState.method_26164(tagKey);
   }
}
