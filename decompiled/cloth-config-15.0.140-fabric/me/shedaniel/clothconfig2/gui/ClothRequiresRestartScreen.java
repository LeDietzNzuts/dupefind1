package me.shedaniel.clothconfig2.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_410;
import net.minecraft.class_437;

@Environment(EnvType.CLIENT)
public class ClothRequiresRestartScreen extends class_410 {
   public ClothRequiresRestartScreen(class_437 parent) {
      super(
         t -> {
            if (t) {
               class_310.method_1551().method_1592();
            } else {
               class_310.method_1551().method_1507(parent);
            }
         },
         class_2561.method_43471("text.cloth-config.restart_required"),
         class_2561.method_43471("text.cloth-config.restart_required_sub"),
         class_2561.method_43471("text.cloth-config.exit_minecraft"),
         class_2561.method_43471("text.cloth-config.ignore_restart")
      );
   }
}
