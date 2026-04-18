package dev.architectury.hooks.client.screen.fabric;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;

@Environment(EnvType.CLIENT)
public class ScreenHooksImpl {
   public static List<class_6379> getNarratables(class_437 screen) {
      return screen.field_33815;
   }

   public static List<class_4068> getRenderables(class_437 screen) {
      return screen.field_33816;
   }

   public static <T extends class_339 & class_4068 & class_6379> T addRenderableWidget(class_437 screen, T widget) {
      return (T)screen.method_37063(widget);
   }

   public static <T extends class_4068> T addRenderableOnly(class_437 screen, T listener) {
      return (T)screen.method_37060(listener);
   }

   public static <T extends class_364 & class_6379> T addWidget(class_437 screen, T listener) {
      return (T)screen.method_25429(listener);
   }
}
