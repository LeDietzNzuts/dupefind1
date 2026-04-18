package dev.architectury.mixin.fabric;

import com.google.common.base.Throwables;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ContextChain;
import dev.architectury.event.events.common.CommandPerformEvent;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2170.class)
public class MixinCommands {
   @ModifyVariable(
      method = "finishParsing",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/Commands;validateParseResults(Lcom/mojang/brigadier/ParseResults;)V"),
      argsOnly = true
   )
   private static ParseResults<class_2168> finishParsing(ParseResults<class_2168> results) {
      CommandPerformEvent event = new CommandPerformEvent(results, null);
      if (CommandPerformEvent.EVENT.invoker().act(event).isPresent()) {
         if (event.getThrowable() != null) {
            Throwables.throwIfUnchecked(event.getThrowable());
         }

         return null;
      } else {
         return event.getResults();
      }
   }

   @Inject(
      method = "finishParsing",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/commands/Commands;validateParseResults(Lcom/mojang/brigadier/ParseResults;)V"),
      cancellable = true
   )
   private static void finishParsing(ParseResults<class_2168> results, String command, class_2168 stack, CallbackInfoReturnable<ContextChain<class_2168>> cir) {
      if (results == null) {
         cir.setReturnValue(null);
      }
   }
}
