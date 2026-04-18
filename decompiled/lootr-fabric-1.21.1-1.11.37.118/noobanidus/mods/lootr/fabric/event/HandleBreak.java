package noobanidus.mods.lootr.fabric.event;

import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_5251;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.fabric.config.ConfigManager;
import org.jetbrains.annotations.Nullable;

public class HandleBreak {
   public static boolean beforeBlockBreak(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, @Nullable class_2586 blockEntity) {
      if (!world.method_8608() && state.method_26164(LootrTags.Blocks.CONTAINERS)) {
         ILootrBlockEntity var6 = LootrAPI.resolveBlockEntity(blockEntity);
         if (var6 instanceof ILootrBlockEntity && !var6.hasLootTable() && !var6.isInfoReferenceInventory()) {
            return true;
         } else if ((!LootrAPI.isFakePlayer(player) || !LootrAPI.isFakePlayerBreakEnabled()) && !LootrAPI.isBreakEnabled()) {
            if ((!(player instanceof FakePlayer) || !LootrAPI.isFakePlayerBreakEnabled()) && !LootrAPI.isBreakEnabled()) {
               if (LootrAPI.isBreakDisabled()) {
                  if (!player.method_31549().field_7477) {
                     player.method_7353(class_2561.method_43471("lootr.message.cannot_break").method_10862(getChatStyle()), false);
                     return false;
                  }

                  if (!player.method_5715()) {
                     player.method_7353(class_2561.method_43471("lootr.message.cannot_break_sneak").method_10862(getChatStyle()), false);
                     return false;
                  }
               } else if (!player.method_5715()) {
                  player.method_7353(class_2561.method_43471("lootr.message.should_sneak").method_10862(getChatStyle()), false);
                  player.method_7353(class_2561.method_43471("lootr.message.should_sneak2").method_10862(getChatStyle()), false);
                  return false;
               }

               return true;
            } else {
               return true;
            }
         } else {
            return true;
         }
      } else {
         return true;
      }
   }

   public static void afterBlockBreak(class_1937 world, class_1657 player, class_2338 pos, class_2680 state, @Nullable class_2586 blockEntity) {
      if (state.method_26164(LootrTags.Blocks.CONTAINERS)) {
         blockEntity.method_5431();
         ILootrBlockEntity var6 = LootrAPI.resolveBlockEntity(blockEntity);
         if (var6 instanceof ILootrBlockEntity) {
            var6.updatePacketViaForce(blockEntity);
         }
      }
   }

   public static class_2583 getChatStyle() {
      return ConfigManager.get().notifications.disable_message_styles
         ? class_2583.field_24360
         : class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1075));
   }
}
