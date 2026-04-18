package net.caffeinemc.mods.sodium.mixin.workarounds.context_creation;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import net.caffeinemc.mods.sodium.client.compatibility.checks.ModuleScanner;
import net.caffeinemc.mods.sodium.client.compatibility.checks.PostLaunchChecks;
import net.caffeinemc.mods.sodium.client.compatibility.environment.GlContextInfo;
import net.caffeinemc.mods.sodium.client.compatibility.workarounds.nvidia.NvidiaWorkarounds;
import net.caffeinemc.mods.sodium.client.platform.NativeWindowHandle;
import net.caffeinemc.mods.sodium.client.services.PlatformRuntimeInformation;
import net.minecraft.class_1041;
import net.minecraft.class_156;
import net.minecraft.class_323;
import net.minecraft.class_3678;
import net.minecraft.class_543;
import net.minecraft.class_156.class_158;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.WGL;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1041.class)
public class WindowMixin {
   @Shadow
   @Final
   private static Logger field_5178;
   @Unique
   private long wglPrevContext = 0L;

   @Redirect(
      method = "<init>(Lnet/minecraft/class_3678;Lnet/minecraft/class_323;Lnet/minecraft/class_543;Ljava/lang/String;Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"),
      expect = 0,
      require = 0
   )
   private long wrapGlfwCreateWindow(int width, int height, CharSequence title, long monitor, long share) {
      NvidiaWorkarounds.applyEnvironmentChanges();

      long var8;
      try {
         var8 = GLFW.glfwCreateWindow(width, height, title, monitor, share);
      } finally {
         NvidiaWorkarounds.undoEnvironmentChanges();
      }

      return var8;
   }

   @WrapOperation(
      method = "<init>(Lnet/minecraft/class_3678;Lnet/minecraft/class_323;Lnet/minecraft/class_543;Ljava/lang/String;Ljava/lang/String;)V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/neoforged/fml/loading/ImmediateWindowHandler;setupMinecraftWindow(Ljava/util/function/IntSupplier;Ljava/util/function/IntSupplier;Ljava/util/function/Supplier;Ljava/util/function/LongSupplier;)J"
      ),
      expect = 0,
      require = 0
   )
   private long wrapGlfwCreateWindowForge(IntSupplier width, IntSupplier height, Supplier<String> title, LongSupplier monitor, Operation<Long> op) {
      boolean applyWorkaroundsLate = !PlatformRuntimeInformation.getInstance().platformHasEarlyLoadingScreen();
      if (applyWorkaroundsLate) {
         NvidiaWorkarounds.applyEnvironmentChanges();
      }

      long var7;
      try {
         var7 = (Long)op.call(new Object[]{width, height, title, monitor});
      } finally {
         if (applyWorkaroundsLate) {
            NvidiaWorkarounds.undoEnvironmentChanges();
         }
      }

      return var7;
   }

   @Inject(
      method = "<init>(Lnet/minecraft/class_3678;Lnet/minecraft/class_323;Lnet/minecraft/class_543;Ljava/lang/String;Ljava/lang/String;)V",
      at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL;createCapabilities()Lorg/lwjgl/opengl/GLCapabilities;", shift = Shift.AFTER)
   )
   private void postContextReady(class_3678 eventHandler, class_323 monitorTracker, class_543 settings, String videoMode, String title, CallbackInfo ci) {
      GlContextInfo context = GlContextInfo.create();
      field_5178.info("OpenGL Vendor: {}", context.vendor());
      field_5178.info("OpenGL Renderer: {}", context.renderer());
      field_5178.info("OpenGL Version: {}", context.version());
      if (class_156.method_668() == class_158.field_1133) {
         this.wglPrevContext = WGL.wglGetCurrentContext();
      } else {
         this.wglPrevContext = 0L;
      }

      PostLaunchChecks.onContextInitialized((NativeWindowHandle)this, context);
      ModuleScanner.checkModules((NativeWindowHandle)this);
   }

   @Inject(method = "method_15998()V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;flipFrame(J)V", shift = Shift.AFTER))
   private void preSwapBuffers(CallbackInfo ci) {
      if (this.wglPrevContext != 0L) {
         long context = WGL.wglGetCurrentContext();
         if (this.wglPrevContext != context) {
            field_5178.warn("The OpenGL context appears to have been suddenly replaced! Something has likely just injected into the game process.");
            ModuleScanner.checkModules((NativeWindowHandle)this);
            this.wglPrevContext = context;
         }
      }
   }
}
