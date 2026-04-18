package net.caffeinemc.mods.sodium.client.gui.screen;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.console.Console;
import net.caffeinemc.mods.sodium.client.console.message.MessageLevel;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;

public class ConfigCorruptedScreen extends class_437 {
   private static final String TEXT_BODY_RAW = "A problem occurred while trying to load the configuration file. This\ncan happen when the file has been corrupted on disk, or when trying\nto manually edit the file by hand.\n\nIf you continue, the configuration file will be reset back to known-good\ndefaults, and you will lose any changes that have since been made to your\nVideo Settings.\n\nMore information about the error can be found in the log file.\n";
   private static final List<class_2561> TEXT_BODY = Arrays.stream(
         "A problem occurred while trying to load the configuration file. This\ncan happen when the file has been corrupted on disk, or when trying\nto manually edit the file by hand.\n\nIf you continue, the configuration file will be reset back to known-good\ndefaults, and you will lose any changes that have since been made to your\nVideo Settings.\n\nMore information about the error can be found in the log file.\n"
            .split("\n")
      )
      .<class_2561>map(class_2561::method_43470)
      .collect(Collectors.toList());
   private static final int BUTTON_WIDTH = 140;
   private static final int BUTTON_HEIGHT = 20;
   private static final int SCREEN_PADDING = 32;
   @Nullable
   private final class_437 prevScreen;
   private final Function<class_437, class_437> nextScreen;

   public ConfigCorruptedScreen(@Nullable class_437 prevScreen, @Nullable Function<class_437, class_437> nextScreen) {
      super(class_2561.method_43470("Sodium failed to load the configuration file"));
      this.prevScreen = prevScreen;
      this.nextScreen = nextScreen;
   }

   protected void method_25426() {
      super.method_25426();
      int buttonY = this.field_22790 - 32 - 20;
      this.method_37063(class_4185.method_46430(class_2561.method_43470("Continue"), btn -> {
         Console.instance().logMessage(MessageLevel.INFO, "sodium.console.config_file_was_reset", true, 3.0);
         SodiumClientMod.restoreDefaultOptions();
         class_310.method_1551().method_1507(this.nextScreen.apply(this.prevScreen));
      }).method_46434(this.field_22789 - 32 - 140, buttonY, 140, 20).method_46431());
      this.method_37063(
         class_4185.method_46430(class_2561.method_43470("Go back"), btn -> class_310.method_1551().method_1507(this.prevScreen))
            .method_46434(32, buttonY, 140, 20)
            .method_46431()
      );
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      super.method_25394(graphics, mouseX, mouseY, delta);
      graphics.method_27535(this.field_22793, class_2561.method_43470("Sodium Renderer"), 32, 32, 16777215);
      graphics.method_27535(this.field_22793, class_2561.method_43470("Could not load the configuration file"), 32, 48, 16711680);

      for (int i = 0; i < TEXT_BODY.size(); i++) {
         if (!TEXT_BODY.get(i).getString().isEmpty()) {
            graphics.method_27535(this.field_22793, TEXT_BODY.get(i), 32, 68 + i * 12, 16777215);
         }
      }
   }
}
