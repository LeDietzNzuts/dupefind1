package dev.architectury.event.events.client;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import dev.architectury.hooks.client.screen.ScreenAccess;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_465;
import net.minecraft.class_9779;

@Environment(EnvType.CLIENT)
public interface ClientGuiEvent {
   Event<ClientGuiEvent.RenderHud> RENDER_HUD = EventFactory.createLoop();
   Event<ClientGuiEvent.DebugText> DEBUG_TEXT_LEFT = EventFactory.createLoop();
   Event<ClientGuiEvent.DebugText> DEBUG_TEXT_RIGHT = EventFactory.createLoop();
   Event<ClientGuiEvent.ScreenInitPre> INIT_PRE = EventFactory.createEventResult();
   Event<ClientGuiEvent.ScreenInitPost> INIT_POST = EventFactory.createLoop();
   Event<ClientGuiEvent.ScreenRenderPre> RENDER_PRE = EventFactory.createEventResult();
   Event<ClientGuiEvent.ScreenRenderPost> RENDER_POST = EventFactory.createLoop();
   Event<ClientGuiEvent.ContainerScreenRenderBackground> RENDER_CONTAINER_BACKGROUND = EventFactory.createLoop();
   Event<ClientGuiEvent.ContainerScreenRenderForeground> RENDER_CONTAINER_FOREGROUND = EventFactory.createLoop();
   Event<ClientGuiEvent.SetScreen> SET_SCREEN = EventFactory.createCompoundEventResult();

   @Environment(EnvType.CLIENT)
   public interface ContainerScreenRenderBackground {
      void render(class_465<?> var1, class_332 var2, int var3, int var4, float var5);
   }

   @Environment(EnvType.CLIENT)
   public interface ContainerScreenRenderForeground {
      void render(class_465<?> var1, class_332 var2, int var3, int var4, float var5);
   }

   @Environment(EnvType.CLIENT)
   public interface DebugText {
      void gatherText(List<String> var1);
   }

   @Environment(EnvType.CLIENT)
   public interface RenderHud {
      void renderHud(class_332 var1, class_9779 var2);
   }

   @Environment(EnvType.CLIENT)
   public interface ScreenInitPost {
      void init(class_437 var1, ScreenAccess var2);
   }

   @Environment(EnvType.CLIENT)
   public interface ScreenInitPre {
      EventResult init(class_437 var1, ScreenAccess var2);
   }

   @Environment(EnvType.CLIENT)
   public interface ScreenRenderPost {
      void render(class_437 var1, class_332 var2, int var3, int var4, class_9779 var5);
   }

   @Environment(EnvType.CLIENT)
   public interface ScreenRenderPre {
      EventResult render(class_437 var1, class_332 var2, int var3, int var4, class_9779 var5);
   }

   @Environment(EnvType.CLIENT)
   public interface SetScreen {
      CompoundEventResult<class_437> modifyScreen(class_437 var1);
   }
}
