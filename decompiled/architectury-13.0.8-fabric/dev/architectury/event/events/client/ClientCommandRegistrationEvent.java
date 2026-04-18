package dev.architectury.event.events.client;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import java.util.function.Supplier;
import net.minecraft.class_2172;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_638;
import net.minecraft.class_7157;
import net.minecraft.class_746;

public interface ClientCommandRegistrationEvent {
   Event<ClientCommandRegistrationEvent> EVENT = EventFactory.createLoop();

   void register(CommandDispatcher<ClientCommandRegistrationEvent.ClientCommandSourceStack> var1, class_7157 var2);

   static LiteralArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack> literal(String name) {
      return LiteralArgumentBuilder.literal(name);
   }

   static <T> RequiredArgumentBuilder<ClientCommandRegistrationEvent.ClientCommandSourceStack, T> argument(String name, ArgumentType<T> type) {
      return RequiredArgumentBuilder.argument(name, type);
   }

   public interface ClientCommandSourceStack extends class_2172 {
      void arch$sendSuccess(Supplier<class_2561> var1, boolean var2);

      void arch$sendFailure(class_2561 var1);

      class_746 arch$getPlayer();

      class_243 arch$getPosition();

      class_241 arch$getRotation();

      class_638 arch$getLevel();
   }
}
