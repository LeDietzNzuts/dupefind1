package net.caffeinemc.mods.sodium.client.gui;

import com.google.common.collect.UnmodifiableIterator;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;
import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.caffeinemc.mods.sodium.client.console.Console;
import net.caffeinemc.mods.sodium.client.console.message.MessageLevel;
import net.caffeinemc.mods.sodium.client.data.fingerprint.HashedFingerprint;
import net.caffeinemc.mods.sodium.client.gui.options.Option;
import net.caffeinemc.mods.sodium.client.gui.options.OptionFlag;
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpact;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import net.caffeinemc.mods.sodium.client.gui.options.control.Control;
import net.caffeinemc.mods.sodium.client.gui.options.control.ControlElement;
import net.caffeinemc.mods.sodium.client.gui.options.storage.OptionStorage;
import net.caffeinemc.mods.sodium.client.gui.prompt.ScreenPrompt;
import net.caffeinemc.mods.sodium.client.gui.prompt.ScreenPromptable;
import net.caffeinemc.mods.sodium.client.gui.screen.ConfigCorruptedScreen;
import net.caffeinemc.mods.sodium.client.gui.widgets.AbstractWidget;
import net.caffeinemc.mods.sodium.client.gui.widgets.FlatButtonWidget;
import net.caffeinemc.mods.sodium.client.services.PlatformRuntimeInformation;
import net.caffeinemc.mods.sodium.client.util.Dim2i;
import net.minecraft.class_124;
import net.minecraft.class_156;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_446;
import net.minecraft.class_5250;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import org.jetbrains.annotations.Nullable;

public class SodiumOptionsGUI extends class_437 implements ScreenPromptable {
   private final List<OptionPage> pages = new ArrayList<>();
   private final List<ControlElement<?>> controls = new ArrayList<>();
   private final class_437 prevScreen;
   private OptionPage currentPage;
   private FlatButtonWidget applyButton;
   private FlatButtonWidget closeButton;
   private FlatButtonWidget undoButton;
   private FlatButtonWidget donateButton;
   private FlatButtonWidget hideDonateButton;
   private boolean hasPendingChanges;
   private ControlElement<?> hoveredElement;
   @Nullable
   private ScreenPrompt prompt;
   private static final List<class_5348> DONATION_PROMPT_MESSAGE = List.of(
      class_5348.method_29433(new class_5348[]{class_2561.method_43470("Hello!")}),
      class_5348.method_29433(
         new class_5348[]{
            class_2561.method_43470("It seems that you've been enjoying "),
            class_2561.method_43470("Sodium").method_54663(2616210),
            class_2561.method_43470(", the powerful and open rendering optimization mod for Minecraft.")
         }
      ),
      class_5348.method_29433(
         new class_5348[]{
            class_2561.method_43470("Mods like these are complex. They require "),
            class_2561.method_43470("thousands of hours").method_54663(16739840),
            class_2561.method_43470(" of development, debugging, and tuning to create the experience that players have come to expect.")
         }
      ),
      class_5348.method_29433(
         new class_5348[]{
            class_2561.method_43470("If you'd like to show your token of appreciation, and support the development of our mod in the process, then consider "),
            class_2561.method_43470("buying us a coffee").method_54663(15550926),
            class_2561.method_43470(".")
         }
      ),
      class_5348.method_29433(new class_5348[]{class_2561.method_43470("And thanks again for using our mod! We hope it helps you (and your computer.)")})
   );

   private SodiumOptionsGUI(class_437 prevScreen) {
      super(class_2561.method_43470("Sodium Renderer Settings"));
      this.prevScreen = prevScreen;
      this.pages.add(SodiumGameOptionPages.general());
      this.pages.add(SodiumGameOptionPages.quality());
      this.pages.add(SodiumGameOptionPages.performance());
      this.pages.add(SodiumGameOptionPages.advanced());
      this.checkPromptTimers();
   }

   private void checkPromptTimers() {
      if (!PlatformRuntimeInformation.getInstance().isDevelopmentEnvironment()) {
         SodiumGameOptions options = SodiumClientMod.options();
         if (!options.notifications.hasSeenDonationPrompt) {
            HashedFingerprint fingerprint = null;

            try {
               fingerprint = HashedFingerprint.loadFromDisk();
            } catch (Throwable var5) {
               SodiumClientMod.logger().error("Failed to read the fingerprint from disk", var5);
            }

            if (fingerprint != null) {
               Instant now = Instant.now();
               Instant threshold = Instant.ofEpochSecond(fingerprint.timestamp()).plus(3L, ChronoUnit.DAYS);
               if (now.isAfter(threshold)) {
                  this.openDonationPrompt(options);
               }
            }
         }
      }
   }

   private void openDonationPrompt(SodiumGameOptions options) {
      ScreenPrompt prompt = new ScreenPrompt(
         this, DONATION_PROMPT_MESSAGE, 320, 190, new ScreenPrompt.Action(class_2561.method_43470("Buy us a coffee"), this::openDonationPage)
      );
      prompt.method_25365(true);
      options.notifications.hasSeenDonationPrompt = true;

      try {
         SodiumGameOptions.writeToDisk(options);
      } catch (IOException var4) {
         SodiumClientMod.logger().error("Failed to update config file", var4);
      }
   }

   public static class_437 createScreen(class_437 currentScreen) {
      return (class_437)(SodiumClientMod.options().isReadOnly()
         ? new ConfigCorruptedScreen(currentScreen, SodiumOptionsGUI::new)
         : new SodiumOptionsGUI(currentScreen));
   }

   public void setPage(OptionPage page) {
      this.currentPage = page;
      this.rebuildGUI();
   }

   protected void method_25426() {
      super.method_25426();
      this.rebuildGUI();
      if (this.prompt != null) {
         this.prompt.init();
      }
   }

   private void rebuildGUI() {
      this.controls.clear();
      this.method_37067();
      if (this.currentPage == null) {
         if (this.pages.isEmpty()) {
            throw new IllegalStateException("No pages are available?!");
         }

         this.currentPage = this.pages.get(0);
      }

      this.rebuildGUIPages();
      this.rebuildGUIOptions();
      this.undoButton = new FlatButtonWidget(
         new Dim2i(this.field_22789 - 211, this.field_22790 - 30, 65, 20), class_2561.method_43471("sodium.options.buttons.undo"), this::undoChanges
      );
      this.applyButton = new FlatButtonWidget(
         new Dim2i(this.field_22789 - 142, this.field_22790 - 30, 65, 20), class_2561.method_43471("sodium.options.buttons.apply"), this::applyChanges
      );
      this.closeButton = new FlatButtonWidget(
         new Dim2i(this.field_22789 - 73, this.field_22790 - 30, 65, 20), class_2561.method_43471("gui.done"), this::method_25419
      );
      this.donateButton = new FlatButtonWidget(
         new Dim2i(this.field_22789 - 128, 6, 100, 20), class_2561.method_43471("sodium.options.buttons.donate"), this::openDonationPage
      );
      this.hideDonateButton = new FlatButtonWidget(new Dim2i(this.field_22789 - 26, 6, 20, 20), class_2561.method_43470("x"), this::hideDonationButton);
      if (SodiumClientMod.options().notifications.hasClearedDonationButton) {
         this.setDonationButtonVisibility(false);
      }

      this.method_37063(this.undoButton);
      this.method_37063(this.applyButton);
      this.method_37063(this.closeButton);
      this.method_37063(this.donateButton);
      this.method_37063(this.hideDonateButton);
   }

   private void setDonationButtonVisibility(boolean value) {
      this.donateButton.setVisible(value);
      this.hideDonateButton.setVisible(value);
   }

   private void hideDonationButton() {
      SodiumGameOptions options = SodiumClientMod.options();
      options.notifications.hasClearedDonationButton = true;

      try {
         SodiumGameOptions.writeToDisk(options);
      } catch (IOException var3) {
         throw new RuntimeException("Failed to save configuration", var3);
      }

      this.setDonationButtonVisibility(false);
   }

   private void rebuildGUIPages() {
      int x = 6;
      int y = 6;

      for (OptionPage page : this.pages) {
         int width = 12 + this.field_22793.method_27525(page.getName());
         FlatButtonWidget button = new FlatButtonWidget(new Dim2i(x, y, width, 18), page.getName(), () -> this.setPage(page));
         button.setSelected(this.currentPage == page);
         x += width + 6;
         this.method_37063(button);
      }
   }

   private void rebuildGUIOptions() {
      int x = 6;
      int y = 28;

      for (UnmodifiableIterator var3 = this.currentPage.getGroups().iterator(); var3.hasNext(); y += 4) {
         OptionGroup group = (OptionGroup)var3.next();

         for (UnmodifiableIterator var5 = group.getOptions().iterator(); var5.hasNext(); y += 18) {
            Option<?> option = (Option<?>)var5.next();
            Control<?> control = option.getControl();
            ControlElement<?> element = control.createElement(new Dim2i(x, y, 240, 18));
            this.method_37063(element);
            this.controls.add(element);
         }
      }
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      this.updateControls();
      super.method_25394(graphics, this.prompt != null ? -1 : mouseX, this.prompt != null ? -1 : mouseY, delta);
      if (this.hoveredElement != null) {
         this.renderOptionTooltip(graphics, this.hoveredElement);
      }

      if (this.prompt != null) {
         this.prompt.method_25394(graphics, mouseX, mouseY, delta);
      }
   }

   private void updateControls() {
      ControlElement<?> hovered = this.getActiveControls()
         .filter(AbstractWidget::isHovered)
         .findFirst()
         .orElse(this.getActiveControls().filter(AbstractWidget::method_25370).findFirst().orElse(null));
      boolean hasChanges = this.getAllOptions().anyMatch(Option::hasChanged);

      for (OptionPage page : this.pages) {
         UnmodifiableIterator var5 = page.getOptions().iterator();

         while (var5.hasNext()) {
            Option<?> option = (Option<?>)var5.next();
            if (option.hasChanged()) {
               hasChanges = true;
            }
         }
      }

      this.applyButton.setEnabled(hasChanges);
      this.undoButton.setVisible(hasChanges);
      this.closeButton.setEnabled(!hasChanges);
      this.hasPendingChanges = hasChanges;
      this.hoveredElement = hovered;
   }

   private Stream<Option<?>> getAllOptions() {
      return this.pages.stream().flatMap(s -> s.getOptions().stream());
   }

   private Stream<ControlElement<?>> getActiveControls() {
      return this.controls.stream();
   }

   private void renderOptionTooltip(class_332 graphics, ControlElement<?> element) {
      Dim2i dim = element.getDimensions();
      int textPadding = 3;
      int boxPadding = 3;
      int boxY = dim.y();
      int boxX = dim.getLimitX() + boxPadding;
      int boxWidth = Math.min(200, this.field_22789 - boxX - boxPadding);
      Option<?> option = element.getOption();
      int splitWidth = boxWidth - textPadding * 2;
      List<class_5481> tooltip = new ArrayList<>(this.field_22793.method_1728(option.getTooltip(), splitWidth));
      OptionImpact impact = option.getImpact();
      if (impact != null) {
         class_5250 impactText = class_2561.method_43469("sodium.options.performance_impact_string", new Object[]{impact.getLocalizedName()});
         tooltip.addAll(this.field_22793.method_1728(impactText.method_27692(class_124.field_1080), splitWidth));
      }

      int boxHeight = tooltip.size() * 12 + boxPadding;
      int boxYLimit = boxY + boxHeight;
      int boxYCutoff = this.field_22790 - 40;
      if (boxYLimit > boxYCutoff) {
         boxY -= boxYLimit - boxYCutoff;
      }

      graphics.method_25296(boxX, boxY, boxX + boxWidth, boxY + boxHeight, -536870912, -536870912);

      for (int i = 0; i < tooltip.size(); i++) {
         graphics.method_35720(this.field_22793, tooltip.get(i), boxX + textPadding, boxY + textPadding + i * 12, -1);
      }
   }

   private void applyChanges() {
      HashSet<OptionStorage<?>> dirtyStorages = new HashSet<>();
      EnumSet<OptionFlag> flags = EnumSet.noneOf(OptionFlag.class);
      this.getAllOptions().forEach(option -> {
         if (option.hasChanged()) {
            option.applyChanges();
            flags.addAll(option.getFlags());
            dirtyStorages.add(option.getStorage());
         }
      });
      class_310 client = class_310.method_1551();
      if (client.field_1687 != null) {
         if (flags.contains(OptionFlag.REQUIRES_RENDERER_RELOAD)) {
            client.field_1769.method_3279();
         } else if (flags.contains(OptionFlag.REQUIRES_RENDERER_UPDATE)) {
            client.field_1769.method_3292();
         }
      }

      if (flags.contains(OptionFlag.REQUIRES_ASSET_RELOAD)) {
         client.method_24041((Integer)client.field_1690.method_42563().method_41753());
         client.method_1513();
      }

      if (flags.contains(OptionFlag.REQUIRES_VIDEOMODE_RELOAD)) {
         client.method_22683().method_4475();
      }

      if (flags.contains(OptionFlag.REQUIRES_GAME_RESTART)) {
         Console.instance().logMessage(MessageLevel.WARN, "sodium.console.game_restart", true, 10.0);
      }

      for (OptionStorage<?> storage : dirtyStorages) {
         storage.save();
      }
   }

   private void undoChanges() {
      this.getAllOptions().forEach(Option::reset);
   }

   private void openDonationPage() {
      class_156.method_668().method_670("https://caffeinemc.net/donate");
   }

   public boolean method_25404(int keyCode, int scanCode, int modifiers) {
      if (this.prompt != null && this.prompt.method_25404(keyCode, scanCode, modifiers)) {
         return true;
      } else if (this.prompt == null && keyCode == 80 && (modifiers & 1) != 0) {
         class_310.method_1551().method_1507(new class_446(this.prevScreen, class_310.method_1551(), class_310.method_1551().field_1690));
         return true;
      } else {
         return super.method_25404(keyCode, scanCode, modifiers);
      }
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (this.prompt != null) {
         return this.prompt.method_25402(mouseX, mouseY, button);
      } else {
         boolean clicked = super.method_25402(mouseX, mouseY, button);
         if (!clicked) {
            this.method_25395(null);
            return true;
         } else {
            return clicked;
         }
      }
   }

   public boolean method_25422() {
      return !this.hasPendingChanges;
   }

   public void method_25419() {
      this.field_22787.method_1507(this.prevScreen);
   }

   public List<? extends class_364> method_25396() {
      return this.prompt == null ? super.method_25396() : this.prompt.getWidgets();
   }

   @Override
   public void setPrompt(@Nullable ScreenPrompt prompt) {
      this.prompt = prompt;
   }

   @Nullable
   @Override
   public ScreenPrompt getPrompt() {
      return this.prompt;
   }

   @Override
   public Dim2i getDimensions() {
      return new Dim2i(0, 0, this.field_22789, this.field_22790);
   }
}
