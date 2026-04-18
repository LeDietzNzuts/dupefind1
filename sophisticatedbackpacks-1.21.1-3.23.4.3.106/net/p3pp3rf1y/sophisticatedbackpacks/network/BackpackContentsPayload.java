package net.p3pp3rf1y.sophisticatedbackpacks.network;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import javax.annotation.Nullable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_2487;
import net.minecraft.class_4844;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedbackpacks.SophisticatedBackpacks;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;
import net.p3pp3rf1y.sophisticatedcore.client.render.ClientStorageContentsTooltipBase;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;

public record BackpackContentsPayload(UUID backpackUuid, @Nullable class_2487 backpackContents) implements class_8710 {
   public static final class_9154<BackpackContentsPayload> TYPE = new class_9154(SophisticatedBackpacks.getRL("backpack_contents"));
   public static final class_9139<ByteBuf, BackpackContentsPayload> STREAM_CODEC = class_9139.method_56435(
      class_4844.field_48453,
      BackpackContentsPayload::backpackUuid,
      StreamCodecHelper.ofNullable(class_9135.field_48556),
      BackpackContentsPayload::backpackContents,
      BackpackContentsPayload::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   @Environment(EnvType.CLIENT)
   public static void handlePayload(BackpackContentsPayload payload, Context context) {
      if (payload.backpackContents != null) {
         BackpackStorage.get().setBackpackContents(payload.backpackUuid, payload.backpackContents);
         ClientStorageContentsTooltipBase.refreshContents();
      }
   }
}
