package noobanidus.mods.lootr.common.mixin.ticker;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1657;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2586;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8934;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.BlockEntityTicker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_8934.class)
public interface MixinRandomizableContainer {
   @Shadow
   class_5321<class_52> method_54869();

   @Shadow
   class_1937 method_10997();

   @WrapOperation(method = "method_54871", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_8934;method_54866(J)V"))
   default void lootr$tryLoadLootTable(class_8934 instance, long l, Operation<Void> original) {
      original.call(new Object[]{instance, l});
      if (instance.method_10997() != null
         && !instance.method_10997().method_8608()
         && instance instanceof class_2586 blockEntity
         && !(instance instanceof ILootrBlockEntity)
         && !(LootrAPI.resolveBlockEntity(blockEntity) instanceof ILootrBlockEntity)) {
         BlockEntityTicker.addEntity(blockEntity, blockEntity.method_10997(), new class_1923(blockEntity.method_11016()));
      }
   }

   @WrapOperation(
      method = "method_54867(Lnet/minecraft/class_5321;J)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_8934;method_11285(Lnet/minecraft/class_5321;)V")
   )
   default void lootr$setLootTable(class_8934 instance, class_5321<class_52> table, Operation<Void> original) {
      original.call(new Object[]{instance, table});
      if (table != null
         && instance.method_10997() != null
         && !instance.method_10997().method_8608()
         && instance instanceof class_2586 blockEntity
         && !(instance instanceof ILootrBlockEntity)
         && !(LootrAPI.resolveBlockEntity(blockEntity) instanceof ILootrBlockEntity)) {
         BlockEntityTicker.addEntity(blockEntity, blockEntity.method_10997(), new class_1923(blockEntity.method_11016()));
      }
   }

   @Inject(
      method = "method_54873",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_9383$class_9385;method_58295(Lnet/minecraft/class_5321;)Lnet/minecraft/class_52;"),
      cancellable = true
   )
   default void lootr$unpackLootTable(class_1657 player, CallbackInfo ci) {
      if (this instanceof class_2586 blockEntity && BlockEntityTicker.isValidEntityFull(blockEntity)) {
         BlockEntityTicker.addEntity(blockEntity, blockEntity.method_10997(), new class_1923(blockEntity.method_11016()));
         ci.cancel();
      }
   }
}
