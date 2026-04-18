package noobanidus.mods.lootr.fabric.mixin.data_fixer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import noobanidus.mods.lootr.common.api.LootrConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_2586.class)
public class MixinBlockEntity {
   @WrapOperation(
      method = "method_11005",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2960;method_12829(Ljava/lang/String;)Lnet/minecraft/class_2960;")
   )
   private static class_2960 LootrLoadStatic(String string, Operation<class_2960> original) {
      return switch (string) {
         case "lootr:special_loot_chest" -> LootrConstants.LOOTR_CHEST;
         case "lootr:special_loot_barrel" -> LootrConstants.LOOTR_BARREL;
         case "lootr:special_trapped_loot_chest" -> LootrConstants.LOOTR_TRAPPED_CHEST;
         case "lootr:special_loot_shulker" -> LootrConstants.LOOTR_SHULKER;
         case "lootr:special_loot_inventory" -> LootrConstants.LOOTR_INVENTORY;
         default -> (class_2960)original.call(new Object[]{string});
      };
   }
}
