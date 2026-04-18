package org.embeddedt.modernfix.screen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.class_1074;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_2568;
import net.minecraft.class_2583;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4265;
import net.minecraft.class_5250;
import net.minecraft.class_6379;
import net.minecraft.class_7919;
import net.minecraft.class_2568.class_5247;
import net.minecraft.class_4185.class_7840;
import net.minecraft.class_4265.class_4266;
import org.embeddedt.modernfix.ModernFix;
import org.embeddedt.modernfix.core.ModernFixMixinPlugin;
import org.embeddedt.modernfix.core.config.Option;
import org.embeddedt.modernfix.core.config.OptionCategories;
import org.embeddedt.modernfix.platform.ModernFixPlatformHooks;

public class OptionList extends class_4265<OptionList.Entry> {
   private int maxNameWidth = 0;
   private static final int DEPTH_OFFSET = 20;
   private static final class_2561 OPTION_ON = class_2561.method_43471("modernfix.option.on").method_27694(style -> style.method_10977(class_124.field_1060));
   private static final class_2561 OPTION_OFF = class_2561.method_43471("modernfix.option.off").method_27694(style -> style.method_10977(class_124.field_1061));
   private static final Set<String> OPTIONS_MISSING_HELP = new HashSet<>();
   private ModernFixConfigScreen mainScreen;
   private final Set<Option> addedOptions = new HashSet<>();

   private static class_5250 getOptionComponent(Option option) {
      String friendlyKey = "modernfix.option.name." + option.getName();
      class_5250 baseComponent = class_2561.method_43470(option.getSelfName());
      return class_1074.method_4663(friendlyKey)
         ? class_2561.method_43471(friendlyKey).method_27694(style -> style.method_10949(new class_2568(class_5247.field_24342, baseComponent)))
         : baseComponent;
   }

   public void updateOptionEntryStatuses() {
      for (OptionList.Entry e : this.method_25396()) {
         if (e instanceof OptionList.OptionEntry) {
            ((OptionList.OptionEntry)e).updateStatus();
         }
      }
   }

   private void addOption(Option option) {
      if (this.addedOptions.add(option)) {
         int w = this.field_22740.field_1772.method_27525(getOptionComponent(option)) + 20 * option.getDepth();
         this.maxNameWidth = Math.max(w, this.maxNameWidth);
         this.method_25321(new OptionList.OptionEntry(option.getName(), option));
         ModernFixMixinPlugin.instance
            .config
            .getOptionMap()
            .values()
            .stream()
            .filter(subOption -> subOption.getParent() == option)
            .sorted(Comparator.comparing(Option::getName))
            .forEach(this::addOption);
      }
   }

   public OptionList(ModernFixConfigScreen arg, class_310 arg2) {
      super(arg2, arg.field_22789 + 45, arg.field_22790 - 52, 20, 20);
      this.mainScreen = arg;
      Multimap<String, Option> optionsByCategory = ModernFixMixinPlugin.instance.config.getOptionCategoryMap();

      for (String category : OptionCategories.getCategoriesInOrder()) {
         String categoryTranslationKey = "modernfix.option.category." + category;
         this.method_25321(
            new OptionList.CategoryEntry(
               class_2561.method_43471(categoryTranslationKey)
                  .method_27696(
                     class_2583.field_24360
                        .method_10949(new class_2568(class_5247.field_24342, class_2561.method_43471(categoryTranslationKey + ".description")))
                  )
            )
         );
         optionsByCategory.get(category).stream().filter(key -> {
            int dotCount = 0;

            for (char c : key.getName().toCharArray()) {
               if (c == '.') {
                  dotCount++;
               }
            }

            return dotCount >= 2;
         }).sorted(Comparator.comparing(Option::getName)).forEach(this::addOption);
      }
   }

   protected int method_25329() {
      return super.method_25329() + 15 + 20;
   }

   public int method_25322() {
      return super.method_25322() + 32;
   }

   class CategoryEntry extends OptionList.Entry {
      private final class_2561 name;
      private final int width;

      public CategoryEntry(class_2561 component) {
         this.name = component;
         this.width = OptionList.this.field_22740.field_1772.method_27525(this.name);
      }

      public void method_25343(
         class_332 guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean isMouseOver, float partialTicks
      ) {
         class_327 var10000 = OptionList.this.field_22740.field_1772;
         float x = OptionList.this.field_22740.field_1755.field_22789 / 2 - this.width / 2;
         int y = top + height - 10;
         guiGraphics.method_27535(var10000, this.name, (int)x, y, 16777215);
      }

      public boolean changeFocus(boolean focus) {
         return false;
      }

      public List<? extends class_364> method_25396() {
         return Collections.emptyList();
      }

      public List<? extends class_6379> method_37025() {
         return Collections.emptyList();
      }
   }

   public abstract static class Entry extends class_4266<OptionList.Entry> {
   }

   class OptionEntry extends OptionList.Entry {
      private final String name;
      private final class_4185 toggleButton;
      private final class_4185 helpButton;
      private final Option option;

      public OptionEntry(String optionName, Option option) {
         this.name = optionName;
         this.option = option;
         class_7919 toggleTooltip = null;
         if (this.option.isModDefined()) {
            String disablingMods = String.join(", ", this.option.getDefiningMods());
            toggleTooltip = class_7919.method_47407(
               class_2561.method_43471("modernfix.option." + (this.option.isEnabled() ? "enabled" : "disabled"))
                  .method_10852(class_2561.method_43469("modernfix.option.mod_override", new Object[]{disablingMods}))
            );
         }

         this.toggleButton = new class_7840(class_2561.method_43470(""), arg -> {
            this.option.setEnabled(!this.option.isEnabled(), !this.option.isUserDefined());

            try {
               ModernFixMixinPlugin.instance.config.save();
               if (!OptionList.this.mainScreen.madeChanges) {
                  OptionList.this.mainScreen.madeChanges = true;
               }
            } catch (IOException var3) {
               this.option.setEnabled(!this.option.isEnabled(), !this.option.isUserDefined());
               ModernFix.LOGGER.error("Unable to save config", var3);
            }

            OptionList.this.updateOptionEntryStatuses();
         }).method_46436(toggleTooltip).method_46433(0, 0).method_46437(55, 20).method_46431();
         this.updateStatus();
         this.helpButton = new class_7840(class_2561.method_43470("?"), arg -> {
            OptionList.this.mainScreen.setLastScrollAmount(OptionList.this.method_25341());
            class_310.method_1551().method_1507(new ModernFixOptionInfoScreen(OptionList.this.mainScreen, optionName));
         }).method_46433(75, 0).method_46437(20, 20).method_46431();
         if (!class_1074.method_4663("modernfix.option." + optionName)) {
            this.helpButton.field_22763 = false;
            if (ModernFixPlatformHooks.INSTANCE.isDevEnv() && OptionList.OPTIONS_MISSING_HELP.add(optionName)) {
               ModernFix.LOGGER.warn("Missing help for {}", optionName);
            }
         }
      }

      void updateStatus() {
         this.toggleButton.field_22763 = !this.option.isModDefined() && !this.option.isEffectivelyDisabledByParent();
      }

      public void method_25343(
         class_332 guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean isMouseOver, float partialTicks
      ) {
         class_5250 nameComponent = OptionList.getOptionComponent(this.option);
         if (this.option.isUserDefined()) {
            nameComponent = nameComponent.method_27694(style -> style.method_10978(true)).method_10852(class_2561.method_43471("modernfix.config.not_default"));
         }

         float textX = left + 20 * this.option.getDepth() + 160 - OptionList.this.maxNameWidth;
         float textY = top + height / 2 - 4;
         guiGraphics.method_27535(OptionList.this.field_22740.field_1772, nameComponent, (int)textX, (int)textY, 16777215);
         this.toggleButton.method_48229(left + 175, top);
         this.toggleButton.method_25355(this.getOptionMessage(this.option));
         this.toggleButton.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
         this.helpButton.method_48229(left + 175 + 55, top);
         this.helpButton.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      }

      private class_2561 getOptionMessage(Option option) {
         return option.isEnabled() ? OptionList.OPTION_ON : OptionList.OPTION_OFF;
      }

      public List<? extends class_364> method_25396() {
         return ImmutableList.of(this.toggleButton, this.helpButton);
      }

      public boolean method_25402(double mouseX, double mouseY, int button) {
         for (class_364 listener : this.method_25396()) {
            if (listener.method_25402(mouseX, mouseY, button)) {
               return true;
            }
         }

         return false;
      }

      public boolean method_25406(double mouseX, double mouseY, int button) {
         for (class_364 listener : this.method_25396()) {
            if (listener.method_25406(mouseX, mouseY, button)) {
               return true;
            }
         }

         return false;
      }

      public List<? extends class_6379> method_37025() {
         return Collections.emptyList();
      }
   }
}
