package noobanidus.mods.lootr.common.mixin.ticker;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1923;
import net.minecraft.class_2487;
import net.minecraft.class_8174;
import noobanidus.mods.lootr.common.block.entity.BlockEntityTicker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_8174.class)
public class MixinBrushableBlockEntity {
   @WrapOperation(method = "method_49222", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2487;method_10573(Ljava/lang/String;I)Z"))
   public boolean lootr$tryLoadLootTable(class_2487 instance, String string, int i, Operation<Boolean> original) {
      if ((Boolean)original.call(new Object[]{instance, string, i})) {
         class_8174 bbe = (class_8174)this;
         if (bbe.method_10997() != null) {
            BlockEntityTicker.addEntity(bbe, bbe.method_10997(), new class_1923(bbe.method_11016()));
         }

         return true;
      } else {
         return false;
      }
   }
}
