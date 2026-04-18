package com.talhanation.smallships.mixin.container;

import com.talhanation.smallships.client.gui.screens.inventory.ShipContainerScreen;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import net.minecraft.class_312;
import net.minecraft.class_3675;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_310.class)
public abstract class MinecraftMixin {
   @Shadow
   @Final
   public class_312 field_1729;
   @Shadow
   @Nullable
   public class_437 field_1755;
   @Unique
   private class_437 smallships$oldScreen;
   @Unique
   private int smallships$seenNull;
   @Unique
   private double smallships$oldMousePosX;
   @Unique
   private double smallships$oldMousePosY;

   @Shadow
   public abstract class_1041 method_22683();

   @Inject(
      method = "setScreen(Lnet/minecraft/client/gui/screens/Screen;)V",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screens/Screen;",
         opcode = 181,
         shift = Shift.BEFORE
      )
   )
   private void setScreenSaveMousePosition(class_437 screen, CallbackInfo ci) {
      if (this.field_1755 == null) {
         this.smallships$seenNull++;
         if (this.smallships$seenNull > 2) {
            this.smallships$oldScreen = null;
         }
      } else {
         this.smallships$seenNull = 0;
         this.smallships$oldScreen = this.field_1755;
      }

      if (this.field_1755 instanceof ShipContainerScreen) {
         this.smallships$oldMousePosX = this.field_1729.method_1603();
         this.smallships$oldMousePosY = this.field_1729.method_1604();
      }
   }

   @Inject(
      method = "setScreen(Lnet/minecraft/client/gui/screens/Screen;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;init(Lnet/minecraft/client/Minecraft;II)V", shift = Shift.AFTER)
   )
   private void setScreenApplyMousePosition(class_437 screen, CallbackInfo ci) {
      if (screen instanceof ShipContainerScreen && this.smallships$oldScreen instanceof ShipContainerScreen) {
         ((MouseHandlerAccessor)this.field_1729).setXpos(this.smallships$oldMousePosX);
         ((MouseHandlerAccessor)this.field_1729).setYpos(this.smallships$oldMousePosY);
         ((MouseHandlerAccessor)this.field_1729).setMouseGrabbed(false);
         class_3675.method_15984(this.method_22683().method_4490(), 212993, this.smallships$oldMousePosX, this.smallships$oldMousePosY);
      }
   }
}
