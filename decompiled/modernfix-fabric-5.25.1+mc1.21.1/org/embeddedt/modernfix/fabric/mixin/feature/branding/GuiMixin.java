package org.embeddedt.modernfix.fabric.mixin.feature.branding;

import java.util.List;
import net.minecraft.class_340;
import org.embeddedt.modernfix.ModernFixClientFabric;
import org.embeddedt.modernfix.annotation.ClientOnlyMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(class_340.class)
@ClientOnlyMixin
public class GuiMixin {
   @ModifyVariable(method = "getSystemInformation", at = @At("STORE"), ordinal = 0, require = 0)
   private List<String> addModernFix(List<String> list) {
      list.add("");
      list.add(ModernFixClientFabric.commonMod.brandingString);
      return list;
   }
}
