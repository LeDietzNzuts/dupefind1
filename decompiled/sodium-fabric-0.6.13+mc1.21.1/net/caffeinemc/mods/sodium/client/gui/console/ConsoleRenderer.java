package net.caffeinemc.mods.sodium.client.gui.console;

import java.util.ArrayList;
import java.util.Deque;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import net.caffeinemc.mods.sodium.api.util.ColorARGB;
import net.caffeinemc.mods.sodium.api.util.ColorU8;
import net.caffeinemc.mods.sodium.client.console.Console;
import net.caffeinemc.mods.sodium.client.console.message.Message;
import net.caffeinemc.mods.sodium.client.console.message.MessageLevel;
import net.minecraft.class_2477;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5225;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import org.lwjgl.glfw.GLFW;

public class ConsoleRenderer {
   static final ConsoleRenderer INSTANCE = new ConsoleRenderer();
   private final LinkedList<ConsoleRenderer.ActiveMessage> activeMessages = new LinkedList<>();
   private static final EnumMap<MessageLevel, ConsoleRenderer.ColorPalette> COLORS = new EnumMap<>(MessageLevel.class);

   public void update(Console console, double currentTime) {
      this.purgeMessages(currentTime);
      this.pollMessages(console, currentTime);
   }

   private void purgeMessages(double currentTime) {
      this.activeMessages.removeIf(message -> currentTime > message.timestamp() + message.duration());
   }

   private void pollMessages(Console console, double currentTime) {
      Deque<Message> log = console.getMessageDrain();

      while (!log.isEmpty()) {
         this.activeMessages.add(ConsoleRenderer.ActiveMessage.create(log.poll(), currentTime));
      }
   }

   public void draw(class_332 context) {
      double currentTime = GLFW.glfwGetTime();
      class_310 minecraft = class_310.method_1551();
      class_4587 matrices = context.method_51448();
      matrices.method_22903();
      matrices.method_46416(0.0F, 0.0F, 1000.0F);
      int paddingWidth = 3;
      int paddingHeight = 1;
      ArrayList<ConsoleRenderer.MessageRender> renders = new ArrayList<>();
      int x = 4;
      int y = 4;

      for (ConsoleRenderer.ActiveMessage message : this.activeMessages) {
         double opacity = getMessageOpacity(message, currentTime);
         if (!(opacity < 0.025)) {
            List<class_5481> lines = new ArrayList<>();
            int messageWidth = 270;
            class_5225 splitter = minecraft.field_1772.method_27527();
            splitter.method_29971(
               message.text(), messageWidth - 20, class_2583.field_24360, (text, lastLineWrapped) -> lines.add(class_2477.method_10517().method_30934(text))
            );
            int messageHeight = 9 * lines.size() + paddingHeight * 2;
            renders.add(new ConsoleRenderer.MessageRender(x, y, messageWidth, messageHeight, message.level(), lines, opacity));
            y += messageHeight;
         }
      }

      double mouseX = minecraft.field_1729.method_1603() / minecraft.method_22683().method_4495();
      double mouseY = minecraft.field_1729.method_1604() / minecraft.method_22683().method_4495();
      boolean hovered = false;

      for (ConsoleRenderer.MessageRender render : renders) {
         if (mouseX >= render.x && mouseX < render.x + render.width && mouseY >= render.y && mouseY < render.y + render.height) {
            hovered = true;
            break;
         }
      }

      for (ConsoleRenderer.MessageRender renderx : renders) {
         int xx = renderx.x();
         int yx = renderx.y();
         int width = renderx.width();
         int height = renderx.height();
         ConsoleRenderer.ColorPalette colors = COLORS.get(renderx.level());
         double opacity = renderx.opacity();
         if (hovered) {
            opacity *= 0.4;
         }

         context.method_25294(xx, yx, xx + width, yx + height, ColorARGB.withAlpha(colors.background(), weightAlpha(opacity)));
         context.method_25294(xx, yx, xx + 1, yx + height, ColorARGB.withAlpha(colors.foreground(), weightAlpha(opacity)));

         for (class_5481 line : renderx.lines()) {
            context.method_51430(
               minecraft.field_1772, line, xx + paddingWidth + 3, yx + paddingHeight, ColorARGB.withAlpha(colors.text(), weightAlpha(opacity)), false
            );
            yx += 9;
         }
      }

      matrices.method_22909();
   }

   private static double getMessageOpacity(ConsoleRenderer.ActiveMessage message, double time) {
      double midpoint = message.timestamp() + message.duration() / 2.0;
      if (time > midpoint) {
         return getFadeOutOpacity(message, time);
      } else {
         return time < midpoint ? getFadeInOpacity(message, time) : 1.0;
      }
   }

   private static double getFadeInOpacity(ConsoleRenderer.ActiveMessage message, double time) {
      double animationDuration = 0.25;
      double animationStart = message.timestamp();
      double animationEnd = message.timestamp() + animationDuration;
      return getAnimationProgress(time, animationStart, animationEnd);
   }

   private static double getFadeOutOpacity(ConsoleRenderer.ActiveMessage message, double time) {
      double animationDuration = Math.min(0.5, message.duration() * 0.2);
      double animationStart = message.timestamp() + message.duration() - animationDuration;
      double animationEnd = message.timestamp() + message.duration();
      return 1.0 - getAnimationProgress(time, animationStart, animationEnd);
   }

   private static double getAnimationProgress(double currentTime, double startTime, double endTime) {
      return class_3532.method_15350(class_3532.method_15370(currentTime, startTime, endTime), 0.0, 1.0);
   }

   private static int weightAlpha(double scale) {
      return ColorU8.normalizedFloatToByte((float)scale);
   }

   static {
      COLORS.put(MessageLevel.INFO, new ConsoleRenderer.ColorPalette(ColorARGB.pack(255, 255, 255), ColorARGB.pack(15, 15, 15), ColorARGB.pack(15, 15, 15)));
      COLORS.put(MessageLevel.WARN, new ConsoleRenderer.ColorPalette(ColorARGB.pack(224, 187, 0), ColorARGB.pack(25, 21, 0), ColorARGB.pack(180, 150, 0)));
      COLORS.put(MessageLevel.SEVERE, new ConsoleRenderer.ColorPalette(ColorARGB.pack(220, 0, 0), ColorARGB.pack(25, 0, 0), ColorARGB.pack(160, 0, 0)));
   }

   private record ActiveMessage(MessageLevel level, class_2561 text, double duration, double timestamp) {
      public static ConsoleRenderer.ActiveMessage create(Message message, double timestamp) {
         class_5250 text = (message.translated() ? class_2561.method_43471(message.text()) : class_2561.method_43470(message.text()))
            .method_27661()
            .method_27694(style -> style.method_27704(class_310.field_24211));
         return new ConsoleRenderer.ActiveMessage(message.level(), text, message.duration(), timestamp);
      }
   }

   private record ColorPalette(int text, int background, int foreground) {
   }

   private record MessageRender(int x, int y, int width, int height, MessageLevel level, List<class_5481> lines, double opacity) {
   }
}
