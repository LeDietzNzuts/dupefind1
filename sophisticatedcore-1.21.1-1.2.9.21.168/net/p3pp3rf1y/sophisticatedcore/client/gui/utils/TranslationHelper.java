package net.p3pp3rf1y.sophisticatedcore.client.gui.utils;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.class_1074;
import net.minecraft.class_124;
import net.minecraft.class_1792;
import net.minecraft.class_2561;
import net.minecraft.class_5250;
import net.p3pp3rf1y.sophisticatedcore.util.RegistryHelper;

public class TranslationHelper {
   public static final String TOOLTIP_SUFFIX = ".tooltip";
   private static final String BUTTONS_SUFFIX = "buttons.";
   private static final String MESSAGE_SUFFIX = "message.";
   private static final String CONTROLS_SUFFIX = "controls.";
   public static final TranslationHelper INSTANCE = new TranslationHelper("sophisticatedcore");
   private final String guiPrefix;
   private final String guiUpgradePrefix;
   private final String guiSettingsPrefix;
   private final String guiStatusPrefix;
   private final String buttonsPrefix;
   private final String itemUpgradePrefix;
   private final String blockPrefix;
   private final String upgradeGroupPrefix;
   private final String upgradeButtonsPrefix;
   private final String upgradeControlsPrefix;
   private final String keybindPrefix;

   public TranslationHelper(String modId) {
      this.guiPrefix = "gui." + modId + ".";
      this.keybindPrefix = "keybind." + modId + ".";
      this.itemUpgradePrefix = "item." + modId + ".";
      this.blockPrefix = "block." + modId + ".";
      this.upgradeGroupPrefix = "upgrade_group." + modId + ".";
      this.guiUpgradePrefix = this.guiPrefix + "upgrades.";
      this.guiSettingsPrefix = this.guiPrefix + "settings.";
      this.guiStatusPrefix = this.guiPrefix + "status.";
      this.buttonsPrefix = this.guiPrefix + "buttons.";
      this.upgradeButtonsPrefix = this.guiUpgradePrefix + "buttons.";
      this.upgradeControlsPrefix = this.guiUpgradePrefix + "controls.";
   }

   public class_5250 translStatusMessage(String statusMessage, Object... params) {
      return class_2561.method_43469(this.guiStatusPrefix + statusMessage, params);
   }

   public class_5250 translUpgrade(String upgradeName, Object... params) {
      return class_2561.method_43469(this.translUpgradeKey(upgradeName), params);
   }

   public class_2561 translUpgradeSlotTooltip(String tooltipName) {
      return class_2561.method_43471(this.guiUpgradePrefix + "slots." + tooltipName + ".tooltip");
   }

   public String translUpgradeKey(String upgradeName) {
      return this.guiUpgradePrefix + upgradeName;
   }

   public String translBlockTooltipKey(String blockName) {
      return this.blockPrefix + blockName + ".tooltip";
   }

   public String translSettings(String categoryName) {
      return this.guiSettingsPrefix + categoryName;
   }

   public String translSettingsButton(String buttonName) {
      return this.translSettings("buttons." + buttonName);
   }

   public String translSettingsMessage(String messageName) {
      return this.translSettings("message." + messageName);
   }

   public class_2561 translUpgradeTooltip(String upgradeName) {
      return class_2561.method_43471(this.translUpgradeKey(upgradeName) + ".tooltip");
   }

   public String translSettingsTooltip(String categoryName) {
      return this.translSettings(categoryName) + ".tooltip";
   }

   public class_2561 translColoredButton(String buttonName, class_124 color) {
      return class_2561.method_43471(this.translButton(buttonName)).method_27692(color);
   }

   public String translButton(String buttonName) {
      return this.buttonsPrefix + buttonName;
   }

   public class_2561 translError(String key, Object... params) {
      return class_2561.method_43469(this.guiPrefix + "error." + key, params);
   }

   public String translUpgradeGroup(String groupName) {
      return this.upgradeGroupPrefix + groupName;
   }

   public String translUpgradeButton(String buttonName) {
      return this.upgradeButtonsPrefix + buttonName;
   }

   public String translUpgradeControl(String controlName) {
      return this.upgradeControlsPrefix + controlName;
   }

   public String translItemTooltip(class_1792 item) {
      return this.translItemTooltip(RegistryHelper.getItemKey(item).method_12832());
   }

   public String translItemTooltip(String itemName) {
      return this.itemUpgradePrefix + itemName + ".tooltip";
   }

   public List<class_2561> getTranslatedLines(String translateKey, @Nullable Object parameters, class_124... textFormattings) {
      List<class_2561> ret = new ArrayList<>();

      for (class_2561 translatedLine : this.getTranslatedLines(translateKey, parameters)) {
         if (translatedLine instanceof class_5250 mutableComponent) {
            mutableComponent.method_27695(textFormattings);
            ret.add(translatedLine);
         }
      }

      return ret;
   }

   public List<class_2561> getTranslatedLines(String translateKey) {
      return this.getTranslatedLines(translateKey, null);
   }

   public List<class_2561> getTranslatedLines(String translateKey, @Nullable Object parameters) {
      String text = this.translate(translateKey, parameters);
      String[] lines = text.split("\n");
      List<class_2561> ret = new ArrayList<>();

      for (String line : lines) {
         ret.add(class_2561.method_43470(line));
      }

      return ret;
   }

   public String translate(String translateKey, Object... parameters) {
      return class_1074.method_4662(translateKey, parameters);
   }

   public String translKeybind(String keybindName) {
      return this.keybindPrefix + keybindName;
   }

   public String translGui(String guiTranslateKey) {
      return this.guiPrefix + guiTranslateKey;
   }

   public String translGuiTooltip(String guiTranslateKey) {
      return this.guiPrefix.substring(0, this.guiPrefix.length() - 1) + ".tooltip." + guiTranslateKey;
   }
}
