package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import com.mojang.datafixers.util.Pair;
import net.minecraft.class_1735;
import net.minecraft.class_2960;
import net.p3pp3rf1y.sophisticatedcore.extensions.inventory.SophisticatedSlot;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_1735.class)
public class SlotMixin implements SophisticatedSlot {
   @Shadow
   @Final
   private int field_7875;
   @Unique
   private Pair<class_2960, class_2960> sophisticatedCore_background;

   @Inject(method = "getNoItemIcon", at = @At("HEAD"), cancellable = true)
   private void sophisticatedcore$background(CallbackInfoReturnable<Pair<class_2960, class_2960>> cir) {
      if (this.sophisticatedCore_background != null) {
         cir.setReturnValue(this.sophisticatedCore_background);
      }
   }

   @Override
   public class_1735 sophisticatedCore_setBackground(class_2960 atlas, class_2960 sprite) {
      this.sophisticatedCore_background = Pair.of(atlas, sprite);
      return MixinHelper.cast(this);
   }

   @Unique
   @Override
   public int sophisticatedCore_getSlotIndex() {
      return this.field_7875;
   }
}
