package org.embeddedt.modernfix.common.mixin.perf.mojang_registry_size;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import net.minecraft.class_2688;
import net.minecraft.class_2769;
import org.embeddedt.modernfix.annotation.RequiresMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2688.class)
@RequiresMod("!ferritecore")
public class StateHolderMixin {
   @Shadow
   private Table<class_2769<?>, Comparable<?>, ?> field_24741;

   @Inject(method = "populateNeighbours", at = @At("RETURN"), require = 0)
   private void replaceEmptyTable(CallbackInfo ci) {
      if ((this.field_24741 instanceof ArrayTable || this.field_24741 instanceof HashBasedTable) && this.field_24741.isEmpty()) {
         this.field_24741 = ImmutableTable.of();
      }
   }
}
