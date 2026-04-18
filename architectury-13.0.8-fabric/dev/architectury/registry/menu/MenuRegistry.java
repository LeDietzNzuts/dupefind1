package dev.architectury.registry.menu;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.menu.fabric.MenuRegistryImpl;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1703;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3908;
import net.minecraft.class_3917;
import net.minecraft.class_3936;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;

public final class MenuRegistry {
   private MenuRegistry() {
   }

   public static void openExtendedMenu(class_3222 player, class_3908 provider, Consumer<class_2540> bufWriter) {
      openExtendedMenu(player, new ExtendedMenuProvider() {
         @Override
         public void saveExtraData(class_2540 buf) {
            bufWriter.accept(buf);
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

   @ExpectPlatform
   @Transformed
   public static void openExtendedMenu(class_3222 player, ExtendedMenuProvider provider) {
      MenuRegistryImpl.openExtendedMenu(player, provider);
   }

   public static void openMenu(class_3222 player, class_3908 provider) {
      player.method_17355(provider);
   }

   @Deprecated(forRemoval = true)
   @ExpectPlatform
   @Transformed
   public static <T extends class_1703> class_3917<T> of(MenuRegistry.SimpleMenuTypeFactory<T> factory) {
      return MenuRegistryImpl.of(factory);
   }

   @ExpectPlatform
   @Transformed
   public static <T extends class_1703> class_3917<T> ofExtended(MenuRegistry.ExtendedMenuTypeFactory<T> factory) {
      return MenuRegistryImpl.ofExtended(factory);
   }

   @Environment(EnvType.CLIENT)
   @ExpectPlatform
   @Transformed
   public static <H extends class_1703, S extends class_437 & class_3936<H>> void registerScreenFactory(
      class_3917<? extends H> type, MenuRegistry.ScreenFactory<H, S> factory
   ) {
      MenuRegistryImpl.registerScreenFactory(type, factory);
   }

   @FunctionalInterface
   public interface ExtendedMenuTypeFactory<T extends class_1703> {
      T create(int var1, class_1661 var2, class_2540 var3);
   }

   @FunctionalInterface
   @Environment(EnvType.CLIENT)
   public interface ScreenFactory<H extends class_1703, S extends class_437 & class_3936<H>> {
      S create(H var1, class_1661 var2, class_2561 var3);
   }

   @Deprecated(forRemoval = true)
   @FunctionalInterface
   public interface SimpleMenuTypeFactory<T extends class_1703> {
      T create(int var1, class_1661 var2);
   }
}
