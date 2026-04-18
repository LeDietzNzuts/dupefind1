package net.p3pp3rf1y.sophisticatedcore.util;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType.ExtendedFactory;
import net.minecraft.class_1703;
import net.minecraft.class_2540;
import net.minecraft.class_3917;
import net.minecraft.class_9135;

public interface IMenuTypeExtension<T> {
   static <T extends class_1703> class_3917<T> create(ExtendedFactory<T, class_2540> factory) {
      return new ExtendedScreenHandlerType((windowId, inventory, data) -> {
         class_2540 buf = new class_2540(Unpooled.wrappedBuffer(data));
         T menu = (T)factory.create(windowId, inventory, buf);
         buf.release();
         return menu;
      }, class_9135.field_48987);
   }
}
