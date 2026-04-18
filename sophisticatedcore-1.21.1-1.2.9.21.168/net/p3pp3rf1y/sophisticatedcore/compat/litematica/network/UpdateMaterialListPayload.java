package net.p3pp3rf1y.sophisticatedcore.compat.litematica.network;

import io.netty.buffer.ByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.Context;
import net.minecraft.class_8710;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.compat.litematica.LitematicaHelper;

public record UpdateMaterialListPayload(int requestedContents) implements class_8710 {
   public static final class_9154<UpdateMaterialListPayload> TYPE = new class_9154(SophisticatedCore.getRL("litematica_update_material_list"));
   public static final class_9139<ByteBuf, UpdateMaterialListPayload> STREAM_CODEC = class_9139.method_56434(
      class_9135.field_49675, UpdateMaterialListPayload::requestedContents, UpdateMaterialListPayload::new
   );

   @Environment(EnvType.CLIENT)
   public static void handlePayload(UpdateMaterialListPayload payload, Context context) {
      LitematicaHelper.setRequested(payload.requestedContents);
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }
}
