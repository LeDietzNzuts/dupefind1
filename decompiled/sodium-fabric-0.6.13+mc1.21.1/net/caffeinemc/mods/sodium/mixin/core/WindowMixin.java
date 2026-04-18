package net.caffeinemc.mods.sodium.mixin.core;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.compatibility.workarounds.Workarounds;
import net.caffeinemc.mods.sodium.client.platform.NativeWindowHandle;
import net.caffeinemc.mods.sodium.client.services.PlatformRuntimeInformation;
import net.minecraft.class_1041;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_1041.class)
public class WindowMixin implements NativeWindowHandle {
   @Shadow
   @Final
   private long field_5187;

   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_3678;Lnet/minecraft/class_323;Lnet/minecraft/class_543;Ljava/lang/String;Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"),
      require = 0
   )
   public long setAdditionalWindowHints(int titleEncoded, int width, CharSequence height, long title, long monitor, Operation<Long> original) {
      if (!PlatformRuntimeInformation.getInstance().platformHasEarlyLoadingScreen()
         && SodiumClientMod.options().performance.useNoErrorGLContext
         && !Workarounds.isWorkaroundEnabled(Workarounds.Reference.NO_ERROR_CONTEXT_UNSUPPORTED)) {
         GLFW.glfwWindowHint(139274, 1);
      }

      return (Long)original.call(new Object[]{titleEncoded, width, height, title, monitor});
   }

   @Override
   public long getWin32Handle() {
      return GLFWNativeWin32.glfwGetWin32Window(this.field_5187);
   }
}
