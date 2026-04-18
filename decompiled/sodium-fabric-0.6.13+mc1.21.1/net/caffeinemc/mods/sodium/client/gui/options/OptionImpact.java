package net.caffeinemc.mods.sodium.client.gui.options;

import net.minecraft.class_124;
import net.minecraft.class_2561;

public enum OptionImpact implements TextProvider {
   LOW(class_124.field_1060, "sodium.option_impact.low"),
   MEDIUM(class_124.field_1054, "sodium.option_impact.medium"),
   HIGH(class_124.field_1065, "sodium.option_impact.high"),
   VARIES(class_124.field_1068, "sodium.option_impact.varies");

   private final class_2561 text;

   private OptionImpact(class_124 formatting, String text) {
      this.text = class_2561.method_43471(text).method_27692(formatting);
   }

   @Override
   public class_2561 getLocalizedName() {
      return this.text;
   }
}
