package me.toastymop.combatlog.mixin;

import me.toastymop.combatlog.util.IEntityDataSaver;
import net.minecraft.class_1297;
import net.minecraft.class_2487;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1297.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
   private class_2487 persistentData;

   @Override
   public class_2487 getPersistentData() {
      if (this.persistentData == null) {
         this.persistentData = new class_2487();
      }

      return this.persistentData;
   }

   @Inject(method = "method_5647(Lnet/minecraft/class_2487;)Lnet/minecraft/class_2487;", at = @At("HEAD"))
   protected void injectWriteMethod(class_2487 nbt, CallbackInfoReturnable info) {
      if (this.persistentData != null) {
         nbt.method_10566("combatLog", this.persistentData);
      }
   }

   @Inject(method = "method_5651(Lnet/minecraft/class_2487;)V", at = @At("HEAD"))
   protected void injectReadMethod(class_2487 nbt, CallbackInfo info) {
      if (nbt.method_10573("combatLog", 10)) {
         this.persistentData = nbt.method_10562("combatLog");
      }
   }
}
