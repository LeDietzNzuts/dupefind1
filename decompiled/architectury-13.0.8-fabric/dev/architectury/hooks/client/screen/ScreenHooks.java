package dev.architectury.hooks.client.screen;

import dev.architectury.hooks.client.screen.fabric.ScreenHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;

@Environment(EnvType.CLIENT)
public final class ScreenHooks {
   private ScreenHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static List<class_6379> getNarratables(class_437 screen) {
      return ScreenHooksImpl.getNarratables(screen);
   }

   @ExpectPlatform
   @Transformed
   public static List<class_4068> getRenderables(class_437 screen) {
      return ScreenHooksImpl.getRenderables(screen);
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_339 & class_4068 & class_6379> T addRenderableWidget(class_437 screen, T widget) {
      return ScreenHooksImpl.addRenderableWidget(screen, widget);
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_4068> T addRenderableOnly(class_437 screen, T listener) {
      return ScreenHooksImpl.addRenderableOnly(screen, listener);
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_364 & class_6379> T addWidget(class_437 screen, T listener) {
      return ScreenHooksImpl.addWidget(screen, listener);
   }
}
