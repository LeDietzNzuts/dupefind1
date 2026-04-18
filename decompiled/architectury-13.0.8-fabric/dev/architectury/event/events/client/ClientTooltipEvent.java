package dev.architectury.event.events.client;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import dev.architectury.impl.TooltipAdditionalContextsImpl;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1799;
import net.minecraft.class_1836;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_5684;
import net.minecraft.class_1792.class_9635;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@Environment(EnvType.CLIENT)
public interface ClientTooltipEvent {
   Event<ClientTooltipEvent.Item> ITEM = EventFactory.createLoop();
   Event<ClientTooltipEvent.Render> RENDER_PRE = EventFactory.createEventResult();
   Event<ClientTooltipEvent.RenderModifyPosition> RENDER_MODIFY_POSITION = EventFactory.createLoop();
   Event<ClientTooltipEvent.RenderModifyColor> RENDER_MODIFY_COLOR = EventFactory.createLoop();

   static ClientTooltipEvent.AdditionalContexts additionalContexts() {
      return TooltipAdditionalContextsImpl.get();
   }

   @NonExtendable
   public interface AdditionalContexts {
      @Nullable
      class_1799 getItem();

      void setItem(@Nullable class_1799 var1);
   }

   @Environment(EnvType.CLIENT)
   public interface ColorContext {
      int getBackgroundColor();

      void setBackgroundColor(int var1);

      int getOutlineGradientTopColor();

      void setOutlineGradientTopColor(int var1);

      int getOutlineGradientBottomColor();

      void setOutlineGradientBottomColor(int var1);
   }

   @Environment(EnvType.CLIENT)
   public interface Item {
      void append(class_1799 var1, List<class_2561> var2, class_9635 var3, class_1836 var4);
   }

   @Environment(EnvType.CLIENT)
   public interface PositionContext {
      int getTooltipX();

      void setTooltipX(int var1);

      int getTooltipY();

      void setTooltipY(int var1);
   }

   @Environment(EnvType.CLIENT)
   public interface Render {
      EventResult renderTooltip(class_332 var1, List<? extends class_5684> var2, int var3, int var4);
   }

   @Environment(EnvType.CLIENT)
   public interface RenderModifyColor {
      void renderTooltip(class_332 var1, int var2, int var3, ClientTooltipEvent.ColorContext var4);
   }

   @Environment(EnvType.CLIENT)
   public interface RenderModifyPosition {
      void renderTooltip(class_332 var1, ClientTooltipEvent.PositionContext var2);
   }
}
