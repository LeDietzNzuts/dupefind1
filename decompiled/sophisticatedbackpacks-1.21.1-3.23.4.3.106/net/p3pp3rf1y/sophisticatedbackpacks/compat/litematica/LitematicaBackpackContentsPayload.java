package net.p3pp3rf1y.sophisticatedbackpacks.compat.litematica;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaHelper;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record LitematicaBackpackContentsPayload(UUID backpackUuid, @Nullable class_2487 backpackContents) implements class_8710 {
   public static final class_9154<LitematicaBackpackContentsPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("litematica_backpack_contents"));
   public static final class_9139<ByteBuf, LitematicaBackpackContentsPayload> STREAM_CODEC = class_9139.method_56435(
      class_4844.field_48453,
      LitematicaBackpackContentsPayload::backpackUuid,
      StreamCodecHelper.ofNullable(class_9135.field_48556),
      LitematicaBackpackContentsPayload::backpackContents,
      LitematicaBackpackContentsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(LitematicaBackpackContentsPayload payload, Context context) {
      if (payload.backpackContents != null) {
         BackpackStorage.get().setBackpackContents(payload.backpackUuid, payload.backpackContents);
         LitematicaHelper.incrementReceived(1);
      }
   }
}
