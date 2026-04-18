package net.p3pp3rf1y.sophisticatedcore.extensions.entity;

import io.netty.buffer.Unpooled;
import java.util.OptionalInt;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import net.minecraft.class_9129;
import org.jetbrains.annotations.Nullable;

public interface SophisticatedPlayer {
   private class_1657 self() {
      return (class_1657)this;
   }

   default OptionalInt sophisticatedCore_openMenu(class_3908 menuProvider, class_2338 pos) {
      return this.sophisticatedCore_openMenu(menuProvider, (Consumer<class_9129>)(buf -> buf.method_10807(pos)));
   }

   default OptionalInt sophisticatedCore_openMenu(class_3908 menu, Consumer<class_9129> context) {
      var screenHandlerFactory = new ExtendedScreenHandlerFactory<Object>() {
         @Nullable
         public class_1703 createMenu(int i, class_1661 inventory, class_1657 player) {
            return menu.createMenu(i, inventory, player);
         }

         public boolean shouldCloseCurrentScreen() {
            return menu.shouldCloseCurrentScreen();
         }

         public class_2561 method_5476() {
            return menu.method_5476();
         }

         public byte[] getScreenOpeningData(class_3222 player) {
            class_9129 buf = new class_9129(Unpooled.buffer(), player.method_56673());
            context.accept(buf);
            return buf.array();
         }
      };
      return this.self().method_17355(screenHandlerFactory);
   }
}
