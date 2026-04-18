package vectorwing.farmersdelight.common.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_1799;
import net.minecraft.class_2540;
import net.minecraft.class_2960;
import net.minecraft.class_299;
import net.minecraft.class_310;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.minecraft.server.MinecraftServer;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.registry.ModDataComponents;
import vectorwing.farmersdelight.refabricated.FDRecipeBookTypes;

public class ModNetworking {
   public static void init() {
      PayloadTypeRegistry.playS2C().register(ModNetworking.SendRecipeBookValuesMessage.TYPE, ModNetworking.SendRecipeBookValuesMessage.STREAM_CODEC);
      PayloadTypeRegistry.playC2S().register(ModNetworking.FlipSkilletMessage.TYPE, ModNetworking.FlipSkilletMessage.STREAM_CODEC);
      ServerPlayNetworking.registerGlobalReceiver(
         ModNetworking.FlipSkilletMessage.TYPE, (payload, context) -> payload.handle(context.server(), context.player())
      );
   }

   public static void initClient() {
      ClientPlayNetworking.registerGlobalReceiver(ModNetworking.SendRecipeBookValuesMessage.TYPE, (payload, context) -> payload.handle());
   }

   public static class FlipSkilletMessage implements class_8710 {
      public static final class_2960 ID = FarmersDelight.res("flip_skillet");
      public static final ModNetworking.FlipSkilletMessage INSTANCE = new ModNetworking.FlipSkilletMessage();
      public static final class_9154<ModNetworking.FlipSkilletMessage> TYPE = new class_9154(ID);
      public static final class_9139<class_9129, ModNetworking.FlipSkilletMessage> STREAM_CODEC = class_9139.method_56431(INSTANCE);

      public class_9154<? extends class_8710> method_56479() {
         return TYPE;
      }

      public void handle(MinecraftServer server, class_3222 player) {
         class_1799 stack = player.method_6030();
         if (stack.method_7909() instanceof SkilletItem) {
            stack.method_57379(ModDataComponents.SKILLET_FLIP_TIMESTAMP.get(), player.method_37908().method_8510());
         }
      }
   }

   public record SendRecipeBookValuesMessage(boolean open, boolean filtering) implements class_8710 {
      public static final class_2960 ID = FarmersDelight.res("send_recipe_book_values");
      public static final class_9154<ModNetworking.SendRecipeBookValuesMessage> TYPE = new class_9154(ID);
      public static final class_9139<class_9129, ModNetworking.SendRecipeBookValuesMessage> STREAM_CODEC = class_9139.method_56437(
         ModNetworking.SendRecipeBookValuesMessage::write, ModNetworking.SendRecipeBookValuesMessage::new
      );

      public SendRecipeBookValuesMessage(class_2540 buf) {
         this(buf.readBoolean(), buf.readBoolean());
      }

      public static void write(class_9129 buf, ModNetworking.SendRecipeBookValuesMessage message) {
         buf.method_52964(message.open);
         buf.method_52964(message.filtering);
      }

      public class_9154<? extends class_8710> method_56479() {
         return TYPE;
      }

      public void handle() {
         class_310.method_1551().execute(() -> {
            class_299 recipeBook = class_310.method_1551().field_1724.method_3130();
            recipeBook.method_14884(FDRecipeBookTypes.COOKING, this.open);
            recipeBook.method_30177(FDRecipeBookTypes.COOKING, this.filtering);
         });
      }
   }
}
