package net.p3pp3rf1y.sophisticatedcore.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_2371;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.p3pp3rf1y.sophisticatedcore.client.gui.SettingsScreen;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.extensions.client.gui.screens.inventory.SophisticatedAbstractContainerScreen;
import net.p3pp3rf1y.sophisticatedcore.util.MixinHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_465.class)
public abstract class AbstractContainerScreenMixin<T extends class_1703> extends class_437 implements SophisticatedAbstractContainerScreen {
   @Shadow
   @Nullable
   public class_1735 field_2787;
   @Shadow
   protected int field_2776;
   @Shadow
   protected int field_2800;
   @Shadow
   protected int field_2792;
   @Unique
   private boolean sophisticatedCore$isStorageScreen;
   @Unique
   private boolean sophisticatedCore$isSettingsScreen;

   @Shadow
   protected abstract void method_2389(class_332 var1, float var2, int var3, int var4);

   protected AbstractContainerScreenMixin(class_2561 title) {
      super(title);
   }

   @Inject(method = "render", at = @At("HEAD"))
   private void sophisticatedCore$renderHead(class_332 guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
      this.sophisticatedCore$isStorageScreen = this instanceof StorageScreenBase;
      this.sophisticatedCore$isSettingsScreen = this instanceof SettingsScreen;
   }

   @Inject(method = "render", at = @At("TAIL"))
   private void sophisticatedCore$renderTail(class_332 guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
      this.sophisticatedCore$isStorageScreen = false;
      this.sophisticatedCore$isSettingsScreen = false;
   }

   @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;size()I"))
   private int sophisticatedCore$MenuSlotSize(class_2371<class_1735> instance, Operation<Integer> original) {
      return this.sophisticatedCore$isStorageScreen ? 36 : (Integer)original.call(new Object[]{instance});
   }

   @WrapOperation(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/NonNullList;get(I)Ljava/lang/Object;"))
   private Object sophisticatedCore$MenuSlotGet(class_2371<class_1735> instance, int i, Operation<class_1735> original) {
      if (this.sophisticatedCore$isStorageScreen) {
         StorageContainerMenuBase<?> menu = (StorageContainerMenuBase<?>)MixinHelper.<StorageScreenBase>cast(this).method_17577();
         return menu.method_7611(menu.getInventorySlotsSize() - 36 + i);
      } else {
         return original.call(new Object[]{instance, i});
      }
   }

   @WrapOperation(
      method = "render",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/Screen;render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V")
   )
   private void sophisticatedCore$wrapRenderCall(
      class_465<?> instance, class_332 guiGraphics, int mouseX, int mouseY, float partialTick, Operation<Void> original
   ) {
      original.call(new Object[]{instance, guiGraphics, mouseX, mouseY, partialTick});
      if (this.sophisticatedCore$isStorageScreen || this.sophisticatedCore$isSettingsScreen) {
         this.method_2389(guiGraphics, partialTick, mouseX, mouseY);
      }
   }

   @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableDepthTest()V", shift = Shift.AFTER))
   private void sophisticatedCore$renderRenderables(class_332 guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
      if (this.sophisticatedCore$isStorageScreen || this.sophisticatedCore$isSettingsScreen) {
         this.field_2787 = null;

         for (class_4068 widget : this.field_33816) {
            widget.method_25394(guiGraphics, mouseX, mouseY, partialTick);
         }
      }
   }

   @WrapWithCondition(
      method = "render",
      at = @At(
         value = "FIELD",
         target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;hoveredSlot:Lnet/minecraft/world/inventory/Slot;",
         opcode = 181,
         ordinal = 0
      )
   )
   private boolean sophisticatedCore$bypassHoveredSlotReset(class_465<T> instance, class_1735 newSlot) {
      return !this.sophisticatedCore$isStorageScreen;
   }

   @Override
   public int sophisticatedCore_getXSize() {
      return this.field_2792;
   }

   @Override
   public int sophisticatedCore_getGuiLeft() {
      return this.field_2776;
   }

   @Override
   public int sophisticatedCore_getGuiTop() {
      return this.field_2800;
   }

   @Nullable
   @Override
   public class_1735 sophisticatedCore_getSlotUnderMouse() {
      return this.field_2787;
   }
}
