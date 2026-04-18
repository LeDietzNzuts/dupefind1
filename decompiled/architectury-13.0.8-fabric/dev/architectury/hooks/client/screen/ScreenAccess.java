package dev.architectury.hooks.client.screen;

import java.util.List;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4068;
import net.minecraft.class_437;
import net.minecraft.class_6379;

public interface ScreenAccess {
   class_437 getScreen();

   List<class_6379> getNarratables();

   List<class_4068> getRenderables();

   <T extends class_339 & class_4068 & class_6379> T addRenderableWidget(T var1);

   <T extends class_4068> T addRenderableOnly(T var1);

   <T extends class_364 & class_6379> T addWidget(T var1);
}
