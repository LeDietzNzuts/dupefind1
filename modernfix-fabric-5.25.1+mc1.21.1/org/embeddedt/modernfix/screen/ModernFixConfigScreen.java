package org.embeddedt.modernfix.screen;

import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import net.minecraft.class_5244;
import net.minecraft.class_4185.class_7840;

public class ModernFixConfigScreen extends class_437 {
   private OptionList optionList;
   private class_437 lastScreen;
   public boolean madeChanges = false;
   private class_4185 doneButton;
   private class_4185 wikiButton;
   private double lastScrollAmount = 0.0;

   public ModernFixConfigScreen(class_437 lastScreen) {
      super(class_2561.method_43471("modernfix.config"));
      this.lastScreen = lastScreen;
   }

   protected void method_25426() {
      this.optionList = new OptionList(this, this.field_22787);
      this.optionList.method_25307(this.lastScrollAmount);
      this.method_25429(this.optionList);
      this.wikiButton = new class_7840(
            class_2561.method_43471("modernfix.config.wiki"),
            arg -> class_156.method_668().method_670("https://github.com/embeddedt/ModernFix/wiki/Summary-of-Patches")
         )
         .method_46433(this.field_22789 / 2 - 155, this.field_22790 - 29)
         .method_46437(150, 20)
         .method_46431();
      this.doneButton = new class_7840(class_5244.field_24334, arg -> this.method_25419())
         .method_46433(this.field_22789 / 2 - 155 + 160, this.field_22790 - 29)
         .method_46437(150, 20)
         .method_46431();
      this.method_37063(this.wikiButton);
      this.method_37063(this.doneButton);
   }

   public void method_25419() {
      this.field_22787.method_1507(this.lastScreen);
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      this.optionList.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      guiGraphics.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 8, 16777215);
      this.doneButton.method_25355((class_2561)(this.madeChanges ? class_2561.method_43471("modernfix.config.done_restart") : class_5244.field_24334));
   }

   public void setLastScrollAmount(double d) {
      this.lastScrollAmount = d;
   }
}
