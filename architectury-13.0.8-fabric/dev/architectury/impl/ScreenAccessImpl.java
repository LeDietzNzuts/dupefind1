package dev.architectury.impl;

import dev.architectury.hooks.client.screen.ScreenAccess;
import dev.architectury.hooks.client.screen.ScreenHooks;
import java.util.List;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;

public class ScreenAccessImpl implements ScreenAccess {
   private class_437 screen;

   public ScreenAccessImpl(class_437 screen) {
      this.screen = screen;
   }

   public void setScreen(class_437 screen) {
      this.screen = screen;
   }

   @Override
   public class_437 getScreen() {
      return this.screen;
   }

   @Override
   public List<class_6379> getNarratables() {
      return ScreenHooks.getNarratables(this.screen);
   }

   @Override
   public List<class_4068> getRenderables() {
      return ScreenHooks.getRenderables(this.screen);
   }

   @Override
   public <T extends class_339 & class_4068 & class_6379> T addRenderableWidget(T widget) {
      return ScreenHooks.addRenderableWidget(this.screen, widget);
   }

   @Override
   public <T extends class_4068> T addRenderableOnly(T listener) {
      return ScreenHooks.addRenderableOnly(this.screen, listener);
   }

   @Override
   public <T extends class_364 & class_6379> T addWidget(T listener) {
      return ScreenHooks.addWidget(this.screen, listener);
   }
}
