package net.p3pp3rf1y.sophisticatedbackpacks.mixin.common;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_4838;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_4838.class)
public class PiglinAiMixin {
   @Inject(method = "isWearingGold", at = @At("HEAD"), cancellable = true)
   private static void sophisticatedBackpacks$isWearingGold(class_1309 entity, CallbackInfoReturnable<Boolean> cir) {
      for (class_1799 itemStack : sophisticatedBackpacks_getArmorSlots(entity)) {
         if (itemStack.makesPiglinsNeutral(entity)) {
            cir.setReturnValue(true);
            return;
         }
      }
   }

   @Unique
   private static Iterable<class_1799> sophisticatedBackpacks_getArmorSlots(class_1309 entity) {
      if (entity instanceof class_1657 player) {
         List<class_1799> trinkets = Lists.newArrayList();
         PlayerInventoryProvider.get().runOnBackpacks(player, "trinkets", (backpack, inventoryHandlerName, identifier, slot) -> trinkets.add(backpack));
         return Iterables.concat(entity.method_5661(), trinkets);
      } else {
         return entity.method_5661();
      }
   }
}
