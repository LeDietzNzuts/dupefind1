package io.github.suel_ki.beautify.compat.jade.providers;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.LampJar;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IDisplayHelper;
import snownee.jade.api.ui.IElementHelper;

public enum LampJarProvider implements IBlockComponentProvider {
   INSTANCE;

   public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
      if (accessor.getBlock() instanceof LampJar) {
         class_2680 state = accessor.getBlockState();
         int currentLevel = (Integer)state.method_11654(LampJar.FILL_LEVEL);
         if (currentLevel != 0) {
            class_1799 icon = class_1802.field_8601.method_7854();
            if (icon.method_7960()) {
               return;
            }

            int count = currentLevel / 5;
            tooltip.add(IElementHelper.get().smallItem(icon));
            tooltip.append(IDisplayHelper.get().stripColor(class_2561.method_43470(count + "× ")));
            tooltip.append(IDisplayHelper.get().stripColor(icon.method_7964()));
         }
      }
   }

   public class_2960 getUid() {
      return Beautify.id("lamp_jar.glowstone_dust");
   }
}
