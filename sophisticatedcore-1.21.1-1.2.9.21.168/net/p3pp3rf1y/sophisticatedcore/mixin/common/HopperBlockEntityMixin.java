package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import java.util.Collections;
import java.util.List;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.minecraft.class_1297;
import net.minecraft.class_1301;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2614;
import net.minecraft.class_2615;
import net.p3pp3rf1y.sophisticatedcore.util.Capabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2614.class)
public class HopperBlockEntityMixin {
   @Shadow
   private class_2350 field_49101;

   @Inject(method = "ejectItems", at = @At("HEAD"), cancellable = true)
   private static void sophisticatedCore_ejectItems(class_1937 world, class_2338 pos, class_2614 blockEntity, CallbackInfoReturnable<Boolean> cir) {
      class_2350 direction = ((HopperBlockEntityMixin)blockEntity).field_49101;
      class_243 searchPos = new class_243(
         pos.method_10263() + direction.method_10148() + 0.5,
         pos.method_10264() + direction.method_10164() + 0.5,
         pos.method_10260() + direction.method_10165() + 0.5
      );
      List<class_1297> list = world.method_8333(
         (class_1297)null,
         new class_238(
            searchPos.method_10216() - 0.5,
            searchPos.method_10214() - 0.5,
            searchPos.method_10215() - 0.5,
            searchPos.method_10216() + 0.5,
            searchPos.method_10214() + 0.5,
            searchPos.method_10215() + 0.5
         ),
         class_1301.field_6154
      );
      if (!list.isEmpty()) {
         Collections.shuffle(list);

         for (class_1297 entity : list) {
            Storage<ItemVariant> target = (Storage<ItemVariant>)Capabilities.ItemHandler.ENTITY_AUTOMATION.find(entity, direction.method_10153());
            if (target != null) {
               long moved = StorageUtil.move(InventoryStorage.of(blockEntity, direction), target, iv -> true, 1L, null);
               cir.setReturnValue(moved == 1L);
               return;
            }
         }
      }
   }

   @Inject(method = "suckInItems", at = @At("HEAD"), cancellable = true)
   private static void sophisticatedCore_suckInItems(class_1937 world, class_2615 hopper, CallbackInfoReturnable<Boolean> cir) {
      class_243 searchPos = new class_243(hopper.method_11266(), hopper.method_11264() + 1.0, hopper.method_11265());
      List<class_1297> list = world.method_8333(
         (class_1297)null,
         new class_238(
            searchPos.method_10216() - 0.5,
            searchPos.method_10214() - 0.5,
            searchPos.method_10215() - 0.5,
            searchPos.method_10216() + 0.5,
            searchPos.method_10214() + 0.5,
            searchPos.method_10215() + 0.5
         ),
         class_1301.field_6154
      );
      if (!list.isEmpty()) {
         Collections.shuffle(list);

         for (class_1297 entity : list) {
            Storage<ItemVariant> source = (Storage<ItemVariant>)Capabilities.ItemHandler.ENTITY_AUTOMATION.find(entity, class_2350.field_11033);
            if (source != null) {
               long moved = StorageUtil.move(source, InventoryStorage.of(hopper, class_2350.field_11036), iv -> true, 1L, null);
               cir.setReturnValue(moved == 1L);
               return;
            }
         }
      }
   }
}
