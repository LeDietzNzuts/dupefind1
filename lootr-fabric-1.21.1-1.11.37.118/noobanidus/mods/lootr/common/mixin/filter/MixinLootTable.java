package noobanidus.mods.lootr.common.mixin.filter;

import com.llamalad7.mixinextras.sugar.Local;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1263;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_52;
import net.minecraft.class_5819;
import net.minecraft.class_8567;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.DefaultLootFiller;
import noobanidus.mods.lootr.common.api.filter.ILootrFilter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_52.class)
public class MixinLootTable {
   @Inject(
      method = "method_329",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_52;method_333(Lit/unimi/dsi/fastutil/objects/ObjectArrayList;ILnet/minecraft/class_5819;)V")
   )
   private void LootrFill(
      class_1263 container,
      class_8567 lootParams,
      long l,
      CallbackInfo ci,
      @Local class_47 lootContext,
      @Local ObjectArrayList<class_1799> items,
      @Local class_5819 random
   ) {
      for (ILootrFilter filter : LootrAPI.getFilters()) {
         if (filter.mutate(items, DefaultLootFiller.getFillerState(), lootContext, random)) {
            break;
         }
      }
   }
}
