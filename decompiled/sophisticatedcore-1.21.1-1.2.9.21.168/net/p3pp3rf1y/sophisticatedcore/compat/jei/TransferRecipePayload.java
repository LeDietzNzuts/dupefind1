package net.p3pp3rf1y.sophisticatedcore.compat.jei;

import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_2960;
import net.minecraft.class_3956;
import net.minecraft.class_7923;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record TransferRecipePayload(
   class_2960 recipeId,
   class_2960 recipeTypeId,
   Map<Integer, Integer> matchingItems,
   List<Integer> craftingSlotIndexes,
   List<Integer> inventorySlotIndexes,
   boolean maxTransfer
) implements class_8710 {
   public static final class_9154<TransferRecipePayload> TYPE = new class_9154(SophisticatedCore.getRL("transfer_recipe"));
   public static final class_9139<ByteBuf, TransferRecipePayload> STREAM_CODEC = class_9139.method_58025(
      class_2960.field_48267,
      TransferRecipePayload::recipeId,
      class_2960.field_48267,
      TransferRecipePayload::recipeTypeId,
      StreamCodecHelper.ofMap(class_9135.field_49675, class_9135.field_49675, HashMap::new),
      TransferRecipePayload::matchingItems,
      class_9135.field_49675.method_56433(class_9135.method_56363()),
      TransferRecipePayload::craftingSlotIndexes,
      class_9135.field_49675.method_56433(class_9135.method_56363()),
      TransferRecipePayload::inventorySlotIndexes,
      class_9135.field_48547,
      TransferRecipePayload::maxTransfer,
      TransferRecipePayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(TransferRecipePayload payload, Context context) {
      class_3956<?> recipeType = (class_3956<?>)class_7923.field_41188.method_10223(payload.recipeTypeId);
      if (recipeType != null) {
         CraftingContainerRecipeTransferHandlerServer.setItems(
            context.player(),
            payload.recipeId,
            recipeType,
            payload.matchingItems,
            payload.craftingSlotIndexes,
            payload.inventorySlotIndexes,
            payload.maxTransfer
         );
      }
   }
}
