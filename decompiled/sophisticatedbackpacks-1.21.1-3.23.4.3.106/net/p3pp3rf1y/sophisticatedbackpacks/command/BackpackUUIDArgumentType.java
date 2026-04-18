package net.p3pp3rf1y.sophisticatedbackpacks.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2168;
import net.minecraft.class_2172;
import net.minecraft.class_5242;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackAccessLogger;

public class BackpackUUIDArgumentType extends class_5242 {
   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      if (context.getSource() instanceof class_2168) {
         return class_2172.method_9265(BackpackAccessLogger.getBackpackUuids().stream().map(UUID::toString).toList(), builder);
      } else {
         return context.getSource() instanceof class_2172 sharedSuggestionProvider ? sharedSuggestionProvider.method_9261(context) : Suggestions.empty();
      }
   }

   public static BackpackUUIDArgumentType backpackUuid() {
      return new BackpackUUIDArgumentType();
   }
}
