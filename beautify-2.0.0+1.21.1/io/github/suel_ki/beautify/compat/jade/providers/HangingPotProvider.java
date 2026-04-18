package io.github.suel_ki.beautify.compat.jade.providers;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.HangingPot;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IDisplayHelper;
import snownee.jade.api.ui.IElementHelper;

public enum HangingPotProvider implements IBlockComponentProvider {
   INSTANCE;

   public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
      if (accessor.getBlock() instanceof HangingPot hangingPot) {
         class_2680 state = accessor.getBlockState();
         if ((Integer)state.method_11654(HangingPot.POTFLOWER) != 0) {
            class_1799 iconFlower = new class_1799((class_1935)hangingPot.getValidFlowers().get((Integer)state.method_11654(HangingPot.POTFLOWER)));
            if (iconFlower.method_7960()) {
               return;
            }

            tooltip.add(IElementHelper.get().smallItem(iconFlower));
            tooltip.append(IDisplayHelper.get().stripColor(iconFlower.method_7964()));
         }

         if ((Boolean)state.method_11654(HangingPot.GROWN)) {
            tooltip.add(class_2561.method_43471("tooltip.jade.crop_mature").method_27692(class_124.field_1060));
         }
      }
   }

   public class_2960 getUid() {
      return Beautify.id("hanging_pot.pot_flower");
   }
}
