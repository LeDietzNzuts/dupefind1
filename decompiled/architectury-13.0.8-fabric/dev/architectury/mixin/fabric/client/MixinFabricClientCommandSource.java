package dev.architectury.mixin.fabric.client;

import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import java.util.function.Supplier;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_638;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FabricClientCommandSource.class)
public interface MixinFabricClientCommandSource extends ClientCommandRegistrationEvent.ClientCommandSourceStack {
   @Override
   default void arch$sendSuccess(Supplier<class_2561> message, boolean broadcastToAdmins) {
      ((FabricClientCommandSource)this).sendFeedback(message.get());
   }

   @Override
   default void arch$sendFailure(class_2561 message) {
      ((FabricClientCommandSource)this).sendError(message);
   }

   @Override
   default class_746 arch$getPlayer() {
      return ((FabricClientCommandSource)this).getPlayer();
   }

   @Override
   default class_243 arch$getPosition() {
      return ((FabricClientCommandSource)this).getPosition();
   }

   @Override
   default class_241 arch$getRotation() {
      return ((FabricClientCommandSource)this).getRotation();
   }

   @Override
   default class_638 arch$getLevel() {
      return ((FabricClientCommandSource)this).getWorld();
   }
}
