package dev.architectury.mixin.fabric.client;

import com.mojang.datafixers.util.Pair;
import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientReloadShadersEvent;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.class_1041;
import net.minecraft.class_281;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_5912;
import net.minecraft.class_5944;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = class_757.class, priority = 1100)
public abstract class MixinGameRenderer {
   @Shadow
   @Final
   private class_310 field_4015;

   @Shadow
   public abstract void method_3182();

   @Inject(
      method = "render(Lnet/minecraft/client/DeltaTracker;Z)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/Screen;renderWithTooltip(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILEXCEPTION,
      cancellable = true
   )
   public void renderScreenPre(
      class_9779 tickDelta,
      boolean tick,
      CallbackInfo ci,
      boolean isGameLoadFinished,
      int mouseX,
      int mouseY,
      class_1041 window,
      Matrix4f matrix,
      Matrix4fStack matrices,
      class_332 graphics
   ) {
      if (ClientGuiEvent.RENDER_PRE.invoker().render(this.field_4015.field_1755, graphics, mouseX, mouseY, tickDelta).isFalse()) {
         ci.cancel();
      }
   }

   @Inject(
      method = "render(Lnet/minecraft/client/DeltaTracker;Z)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/Screen;renderWithTooltip(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
         shift = Shift.AFTER,
         ordinal = 0
      ),
      locals = LocalCapture.CAPTURE_FAILEXCEPTION
   )
   public void renderScreenPost(
      class_9779 tickDelta,
      boolean tick,
      CallbackInfo ci,
      boolean isGameLoadFinished,
      int mouseX,
      int mouseY,
      class_1041 window,
      Matrix4f matrix,
      Matrix4fStack matrices,
      class_332 graphics
   ) {
      ClientGuiEvent.RENDER_POST.invoker().render(this.field_4015.field_1755, graphics, mouseX, mouseY, tickDelta);
   }

   @Inject(
      method = "reloadShaders",
      at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0),
      locals = LocalCapture.CAPTURE_FAILEXCEPTION
   )
   public void reloadShaders(class_5912 provider, CallbackInfo ci, List<class_281> programs, List<Pair<class_5944, Consumer<class_5944>>> shaders) {
      ClientReloadShadersEvent.EVENT.invoker().reload(provider, (shader, callback) -> shaders.add(Pair.of(shader, callback)));
   }
}
