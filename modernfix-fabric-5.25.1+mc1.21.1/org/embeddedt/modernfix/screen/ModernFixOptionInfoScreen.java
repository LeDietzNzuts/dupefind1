package org.embeddedt.modernfix.screen;

import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_5244;
import net.minecraft.class_5481;
import net.minecraft.class_4185.class_7840;

public class ModernFixOptionInfoScreen extends class_437 {
   private final class_437 lastScreen;
   private final class_2561 description;

   public ModernFixOptionInfoScreen(class_437 lastScreen, String optionName) {
      super(class_2561.method_43470(optionName));
      this.lastScreen = lastScreen;
      this.description = class_2561.method_43471("modernfix.option." + optionName);
   }

   protected void method_25426() {
      super.method_25426();
      this.method_37063(
         new class_7840(class_5244.field_24334, button -> this.method_25419())
            .method_46433(this.field_22789 / 2 - 100, this.field_22790 - 29)
            .method_46437(200, 20)
            .method_46431()
      );
   }

   public void method_25419() {
      this.field_22787.method_1507(this.lastScreen);
   }

   private void drawMultilineString(class_332 guiGraphics, class_327 fr, class_2561 str, int x, int y) {
      for (class_5481 s : fr.method_1728(str, this.field_22789 - 50)) {
         guiGraphics.method_51430(fr, s, x, y, 16777215, true);
         y += 9;
      }
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      guiGraphics.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 8, 16777215);
      this.drawMultilineString(guiGraphics, this.field_22787.field_1772, this.description, 10, 50);
   }
}
