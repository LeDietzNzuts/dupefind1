package me.toastymop.combatlog.mixin;

import com.mojang.brigadier.ParseResults;
import me.toastymop.combatlog.CombatConfig;
import me.toastymop.combatlog.util.IEntityDataSaver;
import me.toastymop.combatlog.util.TagData;
import net.minecraft.class_124;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2170.class)
public class CommandsMixin {
   @Inject(method = "method_9249(Lcom/mojang/brigadier/ParseResults;Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
   private void onExecuteCommand(ParseResults<class_2168> parseResults, String command, CallbackInfo ci) {
      class_3222 player = ((class_2168)parseResults.getContext().getSource()).method_44023();
      if (player != null) {
         String[] words = command.split("\\s+");
         if (CombatConfig.Config.blockedCommands.contains(words[0]) && TagData.getCombat((IEntityDataSaver)player)) {
            if (CombatConfig.Config.combatNotice) {
               player.method_43496(
                  class_2561.method_30163(CombatConfig.Config.blockedCommandMessage)
                     .method_27661()
                     .method_27696(class_2583.field_24360.method_10977(class_124.field_1061))
               );
            }

            ci.cancel();
         }
      }
   }
}
