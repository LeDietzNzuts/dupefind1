package dev.architectury.registry.menu.fabric;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import dev.architectury.registry.menu.MenuRegistry;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import java.util.function.Function;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3917;
import net.minecraft.class_3929;
import net.minecraft.class_3936;
import net.minecraft.class_437;
import net.minecraft.class_7701;
import net.minecraft.class_9135;
import org.jetbrains.annotations.Nullable;

public class MenuRegistryImpl {
   public static void openExtendedMenu(class_3222 player, ExtendedMenuProvider provider) {
      player.method_17355(new ExtendedScreenHandlerFactory<byte[]>() {
         public byte[] getScreenOpeningData(class_3222 player) {
            class_2540 buf = PacketByteBufs.create();
            provider.saveExtraData(buf);
            byte[] bytes = ByteBufUtil.getBytes(buf);
            buf.release();
            return bytes;
         }

         public class_2561 method_5476() {
            return provider.method_5476();
         }

         @Nullable
         public class_1703 createMenu(int i, class_1661 inventory, class_1657 playerx) {
            return provider.createMenu(i, inventory, playerx);
         }
      });
   }

   public static <T extends class_1703> class_3917<T> of(MenuRegistry.SimpleMenuTypeFactory<T> factory) {
      return new class_3917(factory::create, class_7701.field_40182);
   }

   public static <T extends class_1703> class_3917<T> ofExtended(MenuRegistry.ExtendedMenuTypeFactory<T> factory) {
      return new ExtendedScreenHandlerType((syncId, inventory, data) -> {
         class_2540 buf = new class_2540(Unpooled.wrappedBuffer(data));
         T menu = factory.create(syncId, inventory, buf);
         buf.release();
         return menu;
      }, class_9135.field_48987.method_56439(Function.identity()));
   }

   @Environment(EnvType.CLIENT)
   public static <H extends class_1703, S extends class_437 & class_3936<H>> void registerScreenFactory(
      class_3917<? extends H> type, MenuRegistry.ScreenFactory<H, S> factory
   ) {
      class_3929.method_17542(type, factory::create);
   }
}
