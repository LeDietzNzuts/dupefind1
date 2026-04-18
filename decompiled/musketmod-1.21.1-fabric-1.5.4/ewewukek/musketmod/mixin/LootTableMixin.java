package ewewukek.musketmod.mixin;

import ewewukek.musketmod.ILootTableId;
import ewewukek.musketmod.VanillaHelper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_47;
import net.minecraft.class_52;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_52.class)
abstract class LootTableMixin implements ILootTableId {
   private class_2960 location;
   private class_47 context;

   @Override
   public void setLocation(class_2960 location) {
      this.location = location;
   }

   @Inject(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", at = @At("HEAD"))
   private void getRandomItemsHead(class_47 context, CallbackInfoReturnable<ObjectArrayList<class_1799>> ci) {
      this.context = context;
   }

   @Inject(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;", at = @At("RETURN"))
   private void getRandomItems(CallbackInfoReturnable<ObjectArrayList<class_1799>> ci) {
      if (this.location != null) {
         ObjectArrayList<class_1799> items = (ObjectArrayList<class_1799>)ci.getReturnValue();
         VanillaHelper.modifyLootTableItems(this.location, this.context, items::add);
      }
   }
}
