package net.neoforged.neoforge.client.gui;

import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig.Entry;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import fuzs.forgeconfigapiport.fabric.impl.config.ForgeConfigApiPortConfig;
import fuzs.forgeconfigapiport.fabric.impl.config.ModConfigValues;
import fuzs.forgeconfigapiport.impl.services.CommonAbstractions;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.class_1074;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_315;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_342;
import net.minecraft.class_3532;
import net.minecraft.class_364;
import net.minecraft.class_410;
import net.minecraft.class_4185;
import net.minecraft.class_424;
import net.minecraft.class_4325;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_4667;
import net.minecraft.class_500;
import net.minecraft.class_5244;
import net.minecraft.class_5250;
import net.minecraft.class_5676;
import net.minecraft.class_6382;
import net.minecraft.class_642;
import net.minecraft.class_7172;
import net.minecraft.class_7842;
import net.minecraft.class_7919;
import net.minecraft.class_8667;
import net.minecraft.class_9017;
import net.minecraft.class_4926.class_6291;
import net.minecraft.class_5676.class_5680;
import net.minecraft.class_7172.class_7174;
import net.minecraft.class_7172.class_7178;
import net.minecraft.class_7172.class_7277;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.config.ModConfigs;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.TranslatableEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class ConfigurationScreen extends class_4667 {
   private static final String LANG_PREFIX = "neoforge.configuration.uitext.";
   private static final String SECTION = "neoforge.configuration.uitext.section";
   private static final String SECTION_TEXT = "neoforge.configuration.uitext.sectiontext";
   private static final String CRUMB = "neoforge.configuration.uitext.breadcrumb";
   private static final String LIST_ELEMENT = "neoforge.configuration.uitext.listelement";
   private static final String RANGE_TOOLTIP = "neoforge.configuration.uitext.rangetooltip";
   private static final String FILENAME_TOOLTIP = "neoforge.configuration.uitext.filenametooltip";
   public static final class_2561 TOOLTIP_CANNOT_EDIT_THIS_WHILE_ONLINE = class_2561.method_43471("neoforge.configuration.uitext.notonline");
   public static final class_2561 TOOLTIP_CANNOT_EDIT_THIS_WHILE_OPEN_TO_LAN = class_2561.method_43471("neoforge.configuration.uitext.notlan");
   public static final class_2561 TOOLTIP_CANNOT_EDIT_NOT_LOADED = class_2561.method_43471("neoforge.configuration.uitext.notloaded");
   public static final class_2561 NEW_LIST_ELEMENT = class_2561.method_43471("neoforge.configuration.uitext.newlistelement");
   public static final class_2561 MOVE_LIST_ELEMENT_UP = class_2561.method_43471("neoforge.configuration.uitext.listelementup");
   public static final class_2561 MOVE_LIST_ELEMENT_DOWN = class_2561.method_43471("neoforge.configuration.uitext.listelementdown");
   public static final class_2561 REMOVE_LIST_ELEMENT = class_2561.method_43471("neoforge.configuration.uitext.listelementremove");
   public static final class_2561 UNSUPPORTED_ELEMENT = class_2561.method_43471("neoforge.configuration.uitext.unsupportedelement");
   public static final class_2561 LONG_STRING = class_2561.method_43471("neoforge.configuration.uitext.longstring");
   public static final class_2561 GAME_RESTART_TITLE = class_2561.method_43471("neoforge.configuration.uitext.restart.game.title");
   public static final class_2561 GAME_RESTART_MESSAGE = class_2561.method_43471("neoforge.configuration.uitext.restart.game.text");
   public static final class_2561 GAME_RESTART_YES = class_2561.method_43471("menu.quit");
   public static final class_2561 SERVER_RESTART_TITLE = class_2561.method_43471("neoforge.configuration.uitext.restart.server.title");
   public static final class_2561 SERVER_RESTART_MESSAGE = class_2561.method_43471("neoforge.configuration.uitext.restart.server.text");
   public static final class_2561 RETURN_TO_MENU = class_2561.method_43471("menu.returnToMenu");
   public static final class_2561 SAVING_LEVEL = class_2561.method_43471("menu.savingLevel");
   public static final class_2561 RESTART_NO = class_2561.method_43471("neoforge.configuration.uitext.restart.return");
   public static final class_2561 RESTART_NO_TOOLTIP = class_2561.method_43471("neoforge.configuration.uitext.restart.return.tooltip");
   public static final class_2561 UNDO = class_2561.method_43471("neoforge.configuration.uitext.undo");
   public static final class_2561 UNDO_TOOLTIP = class_2561.method_43471("neoforge.configuration.uitext.undo.tooltip");
   public static final class_2561 RESET = class_2561.method_43471("neoforge.configuration.uitext.reset");
   public static final class_2561 RESET_TOOLTIP = class_2561.method_43471("neoforge.configuration.uitext.reset.tooltip");
   public static final int BIG_BUTTON_WIDTH = 310;
   protected static final ConfigurationScreen.TranslationChecker translationChecker = new ConfigurationScreen.TranslationChecker();
   protected final String modId;
   private final class_6291<ConfigurationScreen, ModConfig.Type, ModConfig, class_2561, class_437> sectionScreen;
   public ModConfigSpec.RestartType needsRestart = ModConfigSpec.RestartType.NONE;
   private boolean autoClose = false;

   public ConfigurationScreen(String modId, class_437 parent) {
      this(modId, parent, ConfigurationScreen.ConfigurationSectionScreen::new);
   }

   public ConfigurationScreen(String modId, class_437 parent, ConfigurationScreen.ConfigurationSectionScreen.Filter filter) {
      this(modId, parent, (a, b, c, d) -> new ConfigurationScreen.ConfigurationSectionScreen(a, b, c, d, filter));
   }

   public ConfigurationScreen(String modId, class_437 parent, class_6291<ConfigurationScreen, ModConfig.Type, ModConfig, class_2561, class_437> sectionScreen) {
      super(
         parent,
         class_310.method_1551().field_1690,
         class_2561.method_43469(
            translationChecker.check(modId + ".configuration.title", "neoforge.configuration.uitext.title"),
            new Object[]{FabricLoader.getInstance().getModContainer(modId).map(ModContainer::getMetadata).<String>map(ModMetadata::getName).orElse(modId)}
         )
      );
      this.modId = modId;
      this.sectionScreen = sectionScreen;
   }

   protected void method_60325() {
      class_4185 btn = null;
      int count = 0;

      for (ModConfig.Type type : ModConfig.Type.values()) {
         boolean headerAdded = false;

         for (ModConfig modConfig : ModConfigs.getConfigSet(type)) {
            if (modConfig.getModId().equals(this.modId)) {
               if (!headerAdded) {
                  this.field_51824
                     .method_20407(
                        new class_7842(
                              310,
                              20,
                              class_2561.method_43471("neoforge.configuration.uitext." + type.name().toLowerCase(Locale.ENGLISH))
                                 .method_27692(class_124.field_1073),
                              this.field_22793
                           )
                           .method_48596(),
                        null
                     );
                  headerAdded = true;
               }

               btn = class_4185.method_46430(
                     class_2561.method_43469(
                        "neoforge.configuration.uitext.section",
                        new Object[]{
                           this.translatableConfig(modConfig, "", "neoforge.configuration.uitext.type." + modConfig.getType().name().toLowerCase(Locale.ROOT))
                        }
                     ),
                     button -> this.field_22787
                        .method_1507(
                           (class_437)this.sectionScreen
                              .method_35906(
                                 this,
                                 type,
                                 modConfig,
                                 this.translatableConfig(modConfig, ".title", "neoforge.configuration.uitext.title." + type.name().toLowerCase(Locale.ROOT))
                              )
                        )
                  )
                  .method_46432(310)
                  .method_46431();
               class_5250 tooltip = class_2561.method_43473();
               if (!((ModConfigSpec)modConfig.getSpec()).isLoaded()) {
                  tooltip.method_10852(TOOLTIP_CANNOT_EDIT_NOT_LOADED).method_10852(class_2561.method_43470("\n\n"));
                  btn.field_22763 = false;
                  count = 99;
               } else if (type == ModConfig.Type.SERVER && this.field_22787.method_1558() != null && !this.field_22787.method_47392()) {
                  tooltip.method_10852(TOOLTIP_CANNOT_EDIT_THIS_WHILE_ONLINE).method_10852(class_2561.method_43470("\n\n"));
                  btn.field_22763 = false;
                  count = 99;
               } else if (type == ModConfig.Type.SERVER && this.field_22787.method_1496() && this.field_22787.method_1576().method_3860()) {
                  tooltip.method_10852(TOOLTIP_CANNOT_EDIT_THIS_WHILE_OPEN_TO_LAN).method_10852(class_2561.method_43470("\n\n"));
                  btn.field_22763 = false;
                  count = 99;
               }

               tooltip.method_10852(class_2561.method_43469("neoforge.configuration.uitext.filenametooltip", new Object[]{modConfig.getFileName()}));
               btn.method_47400(class_7919.method_47407(tooltip));
               this.field_51824.method_20407(btn, null);
               count++;
            }
         }
      }

      if (count == 1) {
         this.autoClose = true;
         btn.method_25306();
      }
   }

   public class_2561 translatableConfig(ModConfig modConfig, String suffix, String fallback) {
      return class_2561.method_43469(
         translationChecker.check(
            this.modId
               + ".configuration.section."
               + modConfig.getFileName().replaceAll("[^a-zA-Z0-9]+", ".").replaceFirst("^\\.", "").replaceFirst("\\.$", "").toLowerCase(Locale.ENGLISH)
               + suffix,
            fallback
         ),
         new Object[]{
            FabricLoader.getInstance().getModContainer(this.modId).map(ModContainer::getMetadata).<String>map(ModMetadata::getName).orElse(this.modId)
         }
      );
   }

   public void method_49589() {
      super.method_49589();
      if (this.autoClose) {
         this.autoClose = false;
         this.method_25419();
      }
   }

   public void method_25419() {
      translationChecker.finish();
      switch (this.needsRestart) {
         case GAME:
            this.field_22787.method_1507(new ConfigurationScreen.TooltipConfirmScreen(b -> {
               if (b) {
                  this.field_22787.method_1592();
               } else {
                  super.method_25419();
               }
            }, GAME_RESTART_TITLE, GAME_RESTART_MESSAGE, GAME_RESTART_YES, RESTART_NO));
            return;
         case WORLD:
            if (this.field_22787.field_1687 != null) {
               this.field_22787.method_1507(new ConfigurationScreen.TooltipConfirmScreen(b -> {
                  if (b) {
                     this.onDisconnect();
                  } else {
                     super.method_25419();
                  }
               }, SERVER_RESTART_TITLE, SERVER_RESTART_MESSAGE, this.field_22787.method_1542() ? RETURN_TO_MENU : class_5244.field_45692, RESTART_NO));
               return;
            }
         default:
            super.method_25419();
      }
   }

   private void onDisconnect() {
      boolean flag = this.field_22787.method_1542();
      class_642 serverdata = this.field_22787.method_1558();
      this.field_22787.field_1687.method_8525();
      if (flag) {
         this.field_22787.method_56134(new class_424(SAVING_LEVEL));
      } else {
         this.field_22787.method_18099();
      }

      class_442 titlescreen = new class_442();
      if (flag) {
         this.field_22787.method_1507(titlescreen);
      } else if (serverdata != null && serverdata.method_52811()) {
         this.field_22787.method_1507(new class_4325(titlescreen));
      } else {
         this.field_22787.method_1507(new class_500(titlescreen));
      }
   }

   public static class ConfigurationListScreen<T> extends ConfigurationScreen.ConfigurationSectionScreen {
      protected final String key;
      protected final ModConfigSpec.ListValueSpec spec;
      protected final ModConfigSpec.ConfigValue<List<T>> valueList;
      protected List<T> cfgList;

      public ConfigurationListScreen(
         ConfigurationScreen.ConfigurationSectionScreen.Context context,
         String key,
         class_2561 title,
         ModConfigSpec.ListValueSpec spec,
         ModConfigSpec.ConfigValue<List<T>> valueList
      ) {
         super(context, title);
         this.key = key;
         this.spec = spec;
         this.valueList = valueList;
         this.cfgList = new ArrayList<>(valueList.getRaw());
      }

      @Override
      protected ConfigurationScreen.ConfigurationSectionScreen rebuild() {
         if (this.field_51824 != null) {
            this.field_51824.method_25396().clear();

            for (int idx = 0; idx < this.cfgList.size(); idx++) {
               T entry = this.cfgList.get(idx);

               ConfigurationScreen.ConfigurationSectionScreen.Element element = switch (entry) {
                  case null -> null;
                  case Boolean value -> this.createBooleanListValue(idx, value);
                  case Integer valuex -> this.createIntegerListValue(idx, valuex);
                  case Long valuexx -> this.createLongListValue(idx, valuexx);
                  case Double valuexxx -> this.createDoubleListValue(idx, valuexxx);
                  case String valuexxxx -> this.createStringListValue(idx, valuexxxx);
                  default -> this.createOtherValue(idx, entry);
               };
               if (element != null) {
                  class_339 widget = element.getWidget(this.field_21336);
                  if (widget instanceof class_342 box) {
                     box.method_1852(box.method_1882());
                  }

                  this.field_51824.method_20407(this.createListLabel(idx), widget);
               }
            }

            this.createAddElementButton();
            if (this.undoButton == null) {
               this.createUndoButton();
               this.createResetButton();
            }
         }

         return this;
      }

      @Override
      protected boolean isAnyNondefault() {
         return !this.cfgList.equals(this.valueList.getDefault());
      }

      protected void createAddElementButton() {
         Supplier<?> newElement = this.spec.getNewElementSupplier();
         ModConfigSpec.Range<Integer> sizeRange = this.spec.getSizeRange();
         if (newElement != null && sizeRange.test(this.cfgList.size() + 1)) {
            this.field_51824
               .method_20407(
                  new class_7842(150, 20, class_2561.method_43473(), this.field_22793),
                  class_4185.method_46430(ConfigurationScreen.NEW_LIST_ELEMENT, button -> {
                     List<T> newValue = new ArrayList<>(this.cfgList);
                     newValue.add((T)newElement.get());
                     this.undoManager.add(v -> {
                        this.cfgList = v;
                        this.onChanged(this.key);
                     }, newValue, v -> {
                        this.cfgList = v;
                        this.onChanged(this.key);
                     }, this.cfgList);
                     this.rebuild();
                  }).method_46431()
               );
         }
      }

      protected class_339 createListLabel(int idx) {
         return new ConfigurationScreen.ConfigurationListScreen.ListLabelWidget(
            0, 0, 150, 20, class_2561.method_43469("neoforge.configuration.uitext.listelement", new Object[]{idx}), idx
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createOtherValue(int idx, T entry) {
         class_7842 label = new class_7842(150, 20, class_2561.method_43470(Objects.toString(entry)), this.field_22793).method_48596();
         label.method_47400(class_7919.method_47407(ConfigurationScreen.UNSUPPORTED_ELEMENT));
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(
            this.getTranslationComponent(this.key), this.getTooltipComponent(this.key, null), label, false
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createStringListValue(int idx, String value) {
         return this.createStringValue(this.key, v -> this.spec.test(List.of(v)), () -> value, newValue -> this.cfgList.set(idx, (T)newValue));
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createDoubleListValue(int idx, Double value) {
         return this.createNumberBox(
            this.key, this.spec, () -> value, newValue -> this.cfgList.set(idx, (T)newValue), v -> this.spec.test(List.of(v)), Double::parseDouble, 0.0
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createLongListValue(int idx, Long value) {
         return this.createNumberBox(
            this.key, this.spec, () -> value, newValue -> this.cfgList.set(idx, (T)newValue), v -> this.spec.test(List.of(v)), Long::decode, 0L
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createIntegerListValue(int idx, Integer value) {
         return this.createNumberBox(
            this.key, this.spec, () -> value, newValue -> this.cfgList.set(idx, (T)newValue), v -> this.spec.test(List.of(v)), Integer::decode, 0
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createBooleanListValue(int idx, Boolean value) {
         return this.createBooleanValue(this.key, this.spec, () -> value, newValue -> this.cfgList.set(idx, (T)newValue));
      }

      protected boolean swap(int idx, boolean simulate) {
         List<T> values = new ArrayList<>(this.cfgList);
         values.add(idx, values.remove(idx + 1));
         return this.addUndoListener(simulate, values);
      }

      protected boolean del(int idx, boolean simulate) {
         List<T> values = new ArrayList<>(this.cfgList);
         values.remove(idx);
         return this.addUndoListener(simulate, values);
      }

      private boolean addUndoListener(boolean simulate, List<T> values) {
         boolean valid = this.spec.test(values);
         if (!simulate && valid) {
            this.undoManager.add(v -> {
               this.cfgList = v;
               this.onChanged(this.key);
            }, values, v -> {
               this.cfgList = v;
               this.onChanged(this.key);
            }, this.cfgList);
            this.rebuild();
         }

         return valid;
      }

      @Override
      public void method_25419() {
         if (this.changed && this.spec.test(this.cfgList)) {
            this.valueList.set(this.cfgList);
            if (this.context.parent instanceof ConfigurationScreen.ConfigurationSectionScreen parent) {
               parent.onChanged(this.key);
            }
         }

         super.method_25419();
      }

      @Override
      public void method_25394(class_332 graphics, int p_281550_, int p_282878_, float p_282465_) {
         this.doneButton.field_22763 = this.spec.test(this.cfgList);
         super.method_25394(graphics, p_281550_, p_282878_, p_282465_);
      }

      @Override
      protected void onChanged(String key) {
         this.changed = true;
      }

      @Override
      protected void createResetButton() {
         this.resetButton = class_4185.method_46430(ConfigurationScreen.RESET, button -> {
            this.undoManager.add(v -> {
               this.cfgList = v;
               this.onChanged(this.key);
            }, new ArrayList((List)this.getValueSpec(this.key).correct(null)), v -> {
               this.cfgList = v;
               this.onChanged(this.key);
            }, this.cfgList);
            this.rebuild();
         }).method_46436(class_7919.method_47407(ConfigurationScreen.RESET_TOOLTIP)).method_46432(120).method_46431();
      }

      public class ListLabelWidget extends class_9017 {
         protected final class_4185 upButton = class_4185.method_46430(ConfigurationScreen.MOVE_LIST_ELEMENT_UP, this::up).method_46431();
         protected final class_4185 downButton = class_4185.method_46430(ConfigurationScreen.MOVE_LIST_ELEMENT_DOWN, this::down).method_46431();
         protected final class_4185 delButton = class_4185.method_46430(ConfigurationScreen.REMOVE_LIST_ELEMENT, this::rem).method_46431();
         protected final class_7842 label = new class_7842(0, 0, 0, 0, class_2561.method_43473(), ConfigurationListScreen.this.field_22793).method_48596();
         protected final int idx;
         protected final boolean isFirst;
         protected final boolean isLast;

         public ListLabelWidget(final int x, final int y, final int width, final int height, final class_2561 labelText, final int idx) {
            super(x, y, width, height, labelText);
            this.idx = idx;
            this.isFirst = idx == 0;
            this.isLast = idx + 1 == ConfigurationListScreen.this.cfgList.size();
            this.label.method_25355(labelText);
            this.checkButtons();
            this.updateLayout();
         }

         public void method_46421(int pX) {
            super.method_46421(pX);
            this.updateLayout();
         }

         public void method_46419(int pY) {
            super.method_46419(pY);
            this.updateLayout();
         }

         public void method_53533(int pHeight) {
            super.method_53533(pHeight);
            this.updateLayout();
         }

         public void method_25358(int pWidth) {
            super.method_25358(pWidth);
            this.updateLayout();
         }

         public void method_55445(int pWidth, int pHeight) {
            super.method_55445(pWidth, pHeight);
            this.updateLayout();
         }

         protected void updateLayout() {
            this.upButton.method_46421(this.method_46426());
            this.downButton.method_46421(this.method_46426() + this.method_25364() + 2);
            this.delButton.method_46421(this.method_46426() + this.method_25368() - this.method_25364());
            this.label.method_46421(this.method_46426() + 44);
            this.upButton.method_46419(this.method_46427());
            this.downButton.method_46419(this.method_46427());
            this.delButton.method_46419(this.method_46427());
            this.label.method_46419(this.method_46427());
            this.upButton.method_53533(this.method_25364());
            this.downButton.method_53533(this.method_25364());
            this.delButton.method_53533(this.method_25364());
            this.label.method_53533(this.method_25364());
            this.upButton.method_25358(this.method_25364());
            this.downButton.method_25358(this.method_25364());
            this.delButton.method_25358(this.method_25364());
            this.label.method_25358(this.method_25368() - 3 * (this.method_25364() + 2));
         }

         void up(class_4185 button) {
            ConfigurationListScreen.this.swap(this.idx - 1, false);
         }

         void down(class_4185 button) {
            ConfigurationListScreen.this.swap(this.idx, false);
         }

         void rem(class_4185 button) {
            ConfigurationListScreen.this.del(this.idx, false);
         }

         public List<? extends class_364> method_25396() {
            return List.of(this.upButton, this.label, this.downButton, this.delButton);
         }

         protected void method_48579(class_332 pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
            this.checkButtons();
            this.label.method_25394(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            if (!this.isFirst) {
               this.upButton.method_25394(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            }

            if (!this.isLast) {
               this.downButton.method_25394(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
            }

            this.delButton.method_25394(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
         }

         protected void checkButtons() {
            this.upButton.field_22764 = !this.isFirst;
            this.upButton.field_22763 = !this.isFirst && ConfigurationListScreen.this.swap(this.idx - 1, true);
            this.downButton.field_22764 = !this.isLast;
            this.downButton.field_22763 = !this.isLast && ConfigurationListScreen.this.swap(this.idx, true);
            ModConfigSpec.Range<Integer> sizeRange = ConfigurationListScreen.this.spec.getSizeRange();
            this.delButton.field_22763 = !ConfigurationListScreen.this.cfgList.isEmpty()
               && (sizeRange == null || sizeRange.test(ConfigurationListScreen.this.cfgList.size() - 1))
               && ConfigurationListScreen.this.del(this.idx, true);
         }

         protected void method_47399(class_6382 pNarrationElementOutput) {
         }
      }
   }

   public static class ConfigurationSectionScreen extends class_4667 {
      protected static final long MAX_SLIDER_SIZE = 256L;
      protected final ConfigurationScreen.ConfigurationSectionScreen.Context context;
      protected boolean changed = false;
      protected ModConfigSpec.RestartType needsRestart = ModConfigSpec.RestartType.NONE;
      protected final Map<String, ConfigurationScreen.ConfigurationSectionScreen> sectionCache = new HashMap<>();
      @Nullable
      protected class_4185 undoButton;
      @Nullable
      protected class_4185 resetButton;
      protected final class_4185 doneButton = class_4185.method_46430(class_5244.field_24334, button -> this.method_25419()).method_46432(120).method_46431();
      protected final ConfigurationScreen.UndoManager undoManager = new ConfigurationScreen.UndoManager();

      public ConfigurationSectionScreen(class_437 parent, ModConfig.Type type, ModConfig modConfig, class_2561 title) {
         this(parent, type, modConfig, title, (c, k, e) -> e);
      }

      public ConfigurationSectionScreen(
         class_437 parent, ModConfig.Type type, ModConfig modConfig, class_2561 title, ConfigurationScreen.ConfigurationSectionScreen.Filter filter
      ) {
         this(ConfigurationScreen.ConfigurationSectionScreen.Context.top(modConfig.getModId(), parent, modConfig, filter), title);
         this.needsRestart = type == ModConfig.Type.STARTUP ? ModConfigSpec.RestartType.GAME : ModConfigSpec.RestartType.NONE;
      }

      public ConfigurationSectionScreen(
         ConfigurationScreen.ConfigurationSectionScreen.Context parentContext,
         class_437 parent,
         Map<String, Object> valueSpecs,
         String key,
         Set<? extends Entry> entrySet,
         class_2561 title
      ) {
         this(
            ConfigurationScreen.ConfigurationSectionScreen.Context.section(parentContext, parent, entrySet, valueSpecs, key),
            class_2561.method_43469("neoforge.configuration.uitext.breadcrumb", new Object[]{parent.method_25440(), title})
         );
      }

      protected ConfigurationSectionScreen(ConfigurationScreen.ConfigurationSectionScreen.Context context, class_2561 title) {
         super(context.parent, class_310.method_1551().field_1690, title);
         this.context = context;
      }

      @Nullable
      protected ModConfigSpec.ValueSpec getValueSpec(String key) {
         return this.context.valueSpecs.get(key) instanceof ModConfigSpec.ValueSpec vs ? vs : null;
      }

      protected String getTranslationKey(String key) {
         ModConfigSpec.ValueSpec valueSpec = this.getValueSpec(key);
         String result = valueSpec != null ? valueSpec.getTranslationKey() : this.context.modSpec.getLevelTranslationKey(this.context.makeKeyList(key));
         return result != null ? result : this.context.modId + ".configuration." + key;
      }

      protected class_5250 getTranslationComponent(String key) {
         return class_2561.method_43471(ConfigurationScreen.translationChecker.check(this.getTranslationKey(key)));
      }

      protected String getComment(String key) {
         ModConfigSpec.ValueSpec valueSpec = this.getValueSpec(key);
         return valueSpec != null ? this.getValueSpec(key).getComment() : this.context.modSpec.getLevelComment(this.context.makeKeyList(key));
      }

      protected <T> class_7277<T> getTooltip(String key, @Nullable ModConfigSpec.Range<?> range) {
         return class_7172.method_42717(this.getTooltipComponent(key, range));
      }

      protected class_2561 getTooltipComponent(String key, @Nullable ModConfigSpec.Range<?> range) {
         String tooltipKey = this.getTranslationKey(key) + ".tooltip";
         String comment = this.getComment(key);
         boolean hasTranslatedTooltip = ConfigurationScreen.translationChecker.existsWithFallback(tooltipKey);
         class_5250 component = class_2561.method_43473().method_10852(this.getTranslationComponent(key).method_27692(class_124.field_1067));
         if (hasTranslatedTooltip || !Strings.isBlank(comment)) {
            component = component.method_10852(class_2561.method_43470("\n\n")).method_10852(class_2561.method_48321(tooltipKey, comment));
         }

         if (hasTranslatedTooltip && range != null) {
            component = component.method_10852(class_2561.method_43469("neoforge.configuration.uitext.rangetooltip", new Object[]{range.toString()}));
         }

         return component;
      }

      protected void onChanged(String key) {
         this.changed = true;
         ModConfigSpec.ValueSpec valueSpec = this.getValueSpec(key);
         if (valueSpec != null) {
            this.needsRestart = this.needsRestart.with(valueSpec.restartType());
         }
      }

      protected void method_60325() {
         this.rebuild();
      }

      protected ConfigurationScreen.ConfigurationSectionScreen rebuild() {
         if (this.field_51824 != null) {
            this.field_51824.method_25396().clear();
            boolean hasUndoableElements = false;
            List<ConfigurationScreen.ConfigurationSectionScreen.Element> elements = new ArrayList<>();

            label114:
            for (Entry entry : this.context.entries) {
               String key = entry.getKey();
               Object rawValue = entry.getRawValue();
               Object var10000 = entry.getRawValue();
               Objects.requireNonNull(var10000);
               Object var7 = var10000;
               switch (var7) {
                  case ModConfigSpec.ConfigValue cv:
                     ModConfigSpec.ValueSpec valueSpec = this.getValueSpec(key);
                     ModConfigSpec.ValueSpec var32 = valueSpec;
                     byte var13 = 0;

                     while (true) {
                        switch (SwitchBootstraps.typeSwitch<"typeSwitch",ModConfigSpec.ListValueSpec,ModConfigSpec.ValueSpec,ModConfigSpec.ValueSpec,ModConfigSpec.ValueSpec,ModConfigSpec.ValueSpec,ModConfigSpec.ValueSpec>(
                           var32, var13
                        )) {
                           case -1:
                              var10000 = null;
                              break;
                           case 0:
                              ModConfigSpec.ListValueSpec listValueSpec = (ModConfigSpec.ListValueSpec)var32;
                              var10000 = this.createList(key, listValueSpec, cv);
                              break;
                           case 1:
                              if (cv.getClass() != ModConfigSpec.ConfigValue.class || !(var32.getDefault() instanceof String)) {
                                 var13 = 2;
                                 continue;
                              }

                              var10000 = this.createStringValue(key, valueSpec::test, () -> (String)cv.getRaw(), cv::set);
                              break;
                           case 2:
                              if (cv.getClass() != ModConfigSpec.ConfigValue.class || !(var32.getDefault() instanceof Integer)) {
                                 var13 = 3;
                                 continue;
                              }

                              var10000 = this.createIntegerValue(key, valueSpec, () -> (Integer)cv.getRaw(), cv::set);
                              break;
                           case 3:
                              if (cv.getClass() != ModConfigSpec.ConfigValue.class || !(var32.getDefault() instanceof Long)) {
                                 var13 = 4;
                                 continue;
                              }

                              var10000 = this.createLongValue(key, valueSpec, () -> (Long)cv.getRaw(), cv::set);
                              break;
                           case 4:
                              if (cv.getClass() != ModConfigSpec.ConfigValue.class || !(var32.getDefault() instanceof Double)) {
                                 var13 = 5;
                                 continue;
                              }

                              var10000 = this.createDoubleValue(key, valueSpec, () -> (Double)cv.getRaw(), cv::set);
                              break;
                           case 5:
                              if (cv.getClass() != ModConfigSpec.ConfigValue.class || !(var32.getDefault() instanceof Enum)) {
                                 var13 = 6;
                                 continue;
                              }

                              var10000 = this.createEnumValue(key, valueSpec, cv::getRaw, cv::set);
                              break;
                           default:
                              switch (cv) {
                                 case ModConfigSpec.BooleanValue value:
                                    var10000 = this.createBooleanValue(key, valueSpec, value::getRaw, value::set);
                                    break;
                                 case ModConfigSpec.IntValue valuex:
                                    var10000 = this.createIntegerValue(key, valueSpec, valuex::getRaw, valuex::set);
                                    break;
                                 case ModConfigSpec.LongValue valuexx:
                                    var10000 = this.createLongValue(key, valueSpec, valuexx::getRaw, valuexx::set);
                                    break;
                                 case ModConfigSpec.DoubleValue valuexxx:
                                    var10000 = this.createDoubleValue(key, valueSpec, valuexxx::getRaw, valuexxx::set);
                                    break;
                                 case ModConfigSpec.EnumValue valuexxxx:
                                    var10000 = this.createEnumValue(key, valueSpec, valuexxxx::getRaw, valuexxxx::set);
                                    break;
                                 default:
                                    var10000 = this.createOtherValue(key, cv);
                              }
                        }

                        ConfigurationScreen.ConfigurationSectionScreen.Element element = (ConfigurationScreen.ConfigurationSectionScreen.Element)var10000;
                        elements.add(this.context.filter.filterEntry(this.context, key, element));
                        continue label114;
                     }
                  case UnmodifiableConfig subsection when this.context.valueSpecs.get(key) instanceof UnmodifiableConfig subconfig:
                     elements.add(this.createSection(key, subconfig, subsection));
                     break;
                  default:
                     elements.add(this.context.filter.filterEntry(this.context, key, this.createOtherSection(key, rawValue)));
               }
            }

            elements.addAll(this.createSyntheticValues());

            for (ConfigurationScreen.ConfigurationSectionScreen.Element element : elements) {
               if (element != null) {
                  if (element.name() == null) {
                     this.field_51824.method_20407(new class_7842(150, 20, class_2561.method_43473(), this.field_22793), element.getWidget(this.field_21336));
                  } else {
                     class_7842 label = new class_7842(150, 20, element.name, this.field_22793).method_48596();
                     label.method_47400(class_7919.method_47407(element.tooltip));
                     this.field_51824.method_20407(label, element.getWidget(this.field_21336));
                  }

                  hasUndoableElements |= element.undoable;
               }
            }

            if (hasUndoableElements && this.undoButton == null) {
               this.createUndoButton();
               this.createResetButton();
            }
         }

         return this;
      }

      protected Collection<? extends ConfigurationScreen.ConfigurationSectionScreen.Element> createSyntheticValues() {
         return Collections.emptyList();
      }

      protected boolean isNonDefault(ModConfigSpec.ConfigValue<?> cv) {
         return !Objects.equals(cv.getRaw(), cv.getDefault());
      }

      protected boolean isAnyNondefault() {
         for (Entry entry : this.context.entries) {
            if (entry.getRawValue() instanceof ModConfigSpec.ConfigValue<?> cv
               && !(this.getValueSpec(entry.getKey()) instanceof ModConfigSpec.ListValueSpec)
               && this.isNonDefault(cv)) {
               return true;
            }
         }

         return false;
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createStringValue(
         String key, Predicate<String> tester, Supplier<String> source, Consumer<String> target
      ) {
         if (source.get().length() > 192) {
            class_7842 label = new class_7842(150, 20, class_2561.method_43470(source.get().substring(0, 128)), this.field_22793).method_48596();
            label.method_47400(class_7919.method_47407(ConfigurationScreen.LONG_STRING));
            return new ConfigurationScreen.ConfigurationSectionScreen.Element(
               this.getTranslationComponent(key), this.getTooltipComponent(key, null), label, false
            );
         } else {
            class_342 box = new class_342(this.field_22793, 150, 20, this.getTranslationComponent(key));
            box.method_1888(true);
            box.method_47400(class_7919.method_47407(this.getTooltipComponent(key, null)));
            box.method_1880(class_3532.method_15340(source.get().length() + 5, 128, 192));
            box.method_1852(source.get());
            box.method_1863(newValue -> {
               if (newValue != null && tester.test(newValue)) {
                  if (!newValue.equals(source.get())) {
                     this.undoManager.add(v -> {
                        target.accept(v);
                        this.onChanged(key);
                     }, newValue, v -> {
                        target.accept(v);
                        this.onChanged(key);
                     }, source.get());
                  }

                  box.method_1868(14737632);
               } else {
                  box.method_1868(-65536);
               }
            });
            return new ConfigurationScreen.ConfigurationSectionScreen.Element(this.getTranslationComponent(key), this.getTooltipComponent(key, null), box);
         }
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createOtherSection(String key, Object value) {
         return null;
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createOtherValue(String key, ModConfigSpec.ConfigValue<?> value) {
         class_7842 label = new class_7842(150, 20, class_2561.method_43470(Objects.toString(value.getRaw())), this.field_22793).method_48596();
         label.method_47400(class_7919.method_47407(ConfigurationScreen.UNSUPPORTED_ELEMENT));
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(this.getTranslationComponent(key), this.getTooltipComponent(key, null), label, false);
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createBooleanValue(
         String key, ModConfigSpec.ValueSpec spec, Supplier<Boolean> source, Consumer<Boolean> target
      ) {
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(
            this.getTranslationComponent(key),
            this.getTooltipComponent(key, null),
            new class_7172(
               this.getTranslationKey(key),
               this.getTooltip(key, null),
               class_7172.field_41333,
               ConfigurationScreen.ConfigurationSectionScreen.Custom.BOOLEAN_VALUES_NO_PREFIX,
               source.get(),
               newValue -> this.undoManager.add(v -> {
                  target.accept(v);
                  this.onChanged(key);
               }, newValue, v -> {
                  target.accept(v);
                  this.onChanged(key);
               }, source.get())
            )
         );
      }

      @Nullable
      protected <T extends Enum<T>> ConfigurationScreen.ConfigurationSectionScreen.Element createEnumValue(
         String key, ModConfigSpec.ValueSpec spec, Supplier<T> source, Consumer<T> target
      ) {
         Class<T> clazz = (Class<T>)spec.getClazz();

         assert clazz != null;

         List<T> list = Arrays.<T>stream(clazz.getEnumConstants()).filter(spec::test).toList();
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(
            this.getTranslationComponent(key),
            this.getTooltipComponent(key, null),
            new class_7172(
               this.getTranslationKey(key),
               this.getTooltip(key, null),
               (caption, displayvalue) -> (class_2561)(displayvalue instanceof TranslatableEnum tenum
                  ? tenum.getTranslatedName()
                  : class_2561.method_43470(displayvalue.name())),
               new ConfigurationScreen.ConfigurationSectionScreen.Custom<>(list),
               source.get(),
               newValue -> this.undoManager.add(v -> {
                  target.accept(v);
                  this.onChanged(key);
               }, (T)newValue, v -> {
                  target.accept(v);
                  this.onChanged(key);
               }, source.get())
            )
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createIntegerValue(
         String key, ModConfigSpec.ValueSpec spec, Supplier<Integer> source, Consumer<Integer> target
      ) {
         ModConfigSpec.Range<Integer> range = spec.getRange();
         int min = range != null ? range.getMin() : 0;
         int max = range != null ? range.getMax() : Integer.MAX_VALUE;
         return (long)max - min < 256L
            ? this.createSlider(key, source, target, range)
            : this.createNumberBox(key, spec, source, target, null, Integer::decode, 0);
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createSlider(
         String key, Supplier<Integer> source, Consumer<Integer> target, @Nullable ModConfigSpec.Range<Integer> range
      ) {
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(
            this.getTranslationComponent(key),
            this.getTooltipComponent(key, null),
            new class_7172(
               this.getTranslationKey(key),
               this.getTooltip(key, range),
               (caption, displayvalue) -> class_2561.method_43470(displayvalue + ""),
               new class_7174(range != null ? range.getMin() : 0, range != null ? range.getMax() : Integer.MAX_VALUE),
               null,
               source.get(),
               newValue -> {
                  if (!newValue.equals(source.get())) {
                     this.undoManager.add(v -> {
                        target.accept(v);
                        this.onChanged(key);
                     }, newValue, v -> {
                        target.accept(v);
                        this.onChanged(key);
                     }, source.get());
                  }
               }
            )
         );
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createLongValue(
         String key, ModConfigSpec.ValueSpec spec, Supplier<Long> source, Consumer<Long> target
      ) {
         return this.createNumberBox(key, spec, source, target, null, Long::decode, 0L);
      }

      @Nullable
      protected <T extends Number & Comparable<? super T>> ConfigurationScreen.ConfigurationSectionScreen.Element createNumberBox(
         String key, ModConfigSpec.ValueSpec spec, Supplier<T> source, Consumer<T> target, @Nullable Predicate<T> tester, Function<String, T> parser, T zero
      ) {
         ModConfigSpec.Range<T> range = spec.getRange();
         class_342 box = new class_342(this.field_22793, 150, 20, this.getTranslationComponent(key));
         box.method_1888(true);
         box.method_1890(newValueString -> {
            try {
               parser.apply(newValueString);
               return true;
            } catch (NumberFormatException var6) {
               return this.isPartialNumber(newValueString, range == null || range.getMin().compareTo(zero) < 0);
            }
         });
         box.method_47400(class_7919.method_47407(this.getTooltipComponent(key, range)));
         box.method_1852(source.get() + "");
         box.method_1863(newValueString -> {
            try {
               T newValue = parser.apply(newValueString);
               if (tester != null ? tester.test(newValue) : newValue != null && (range == null || range.test(newValue)) && spec.test(newValue)) {
                  if (!newValue.equals(source.get())) {
                     this.undoManager.add(v -> {
                        target.accept((T)v);
                        this.onChanged(key);
                     }, newValue, v -> {
                        target.accept((T)v);
                        this.onChanged(key);
                     }, source.get());
                  }

                  box.method_1868(14737632);
                  return;
               }
            } catch (NumberFormatException var11) {
            }

            box.method_1868(-65536);
         });
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(this.getTranslationComponent(key), this.getTooltipComponent(key, null), box);
      }

      protected boolean isPartialNumber(String value, boolean allowNegative) {
         return switch (value) {
            case "" -> true;
            case "0" -> true;
            case "0x" -> true;
            case "0X" -> true;
            case "#" -> true;
            case "-" -> allowNegative;
            case "-0" -> allowNegative;
            case "-0x" -> allowNegative;
            case "-0X" -> allowNegative;
            default -> false;
         };
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createDoubleValue(
         String key, ModConfigSpec.ValueSpec spec, Supplier<Double> source, Consumer<Double> target
      ) {
         return this.createNumberBox(key, spec, source, target, null, Double::parseDouble, 0.0);
      }

      @Nullable
      protected ConfigurationScreen.ConfigurationSectionScreen.Element createSection(String key, UnmodifiableConfig subconfig, UnmodifiableConfig subsection) {
         return subconfig.isEmpty()
            ? null
            : new ConfigurationScreen.ConfigurationSectionScreen.Element(
               class_2561.method_43469("neoforge.configuration.uitext.section", new Object[]{this.getTranslationComponent(key)}),
               this.getTooltipComponent(key, null),
               class_4185.method_46430(
                     class_2561.method_43469(
                        "neoforge.configuration.uitext.section",
                        new Object[]{
                           class_2561.method_43471(ConfigurationScreen.translationChecker.check(key + ".button", "neoforge.configuration.uitext.sectiontext"))
                        }
                     ),
                     button -> this.field_22787
                        .method_1507(
                           (class_437)this.sectionCache
                              .computeIfAbsent(
                                 key,
                                 k -> new ConfigurationScreen.ConfigurationSectionScreen(
                                       this.context,
                                       this,
                                       subconfig.valueMap(),
                                       key,
                                       subsection.entrySet(),
                                       class_2561.method_43471(this.getTranslationKey(key))
                                    )
                                    .rebuild()
                              )
                        )
                  )
                  .method_46436(class_7919.method_47407(this.getTooltipComponent(key, null)))
                  .method_46432(150)
                  .method_46431(),
               false
            );
      }

      @Nullable
      protected <T> ConfigurationScreen.ConfigurationSectionScreen.Element createList(
         String key, ModConfigSpec.ListValueSpec spec, ModConfigSpec.ConfigValue<List<T>> list
      ) {
         return new ConfigurationScreen.ConfigurationSectionScreen.Element(
            class_2561.method_43469("neoforge.configuration.uitext.section", new Object[]{this.getTranslationComponent(key)}),
            this.getTooltipComponent(key, null),
            class_4185.method_46430(
                  class_2561.method_43469(
                     "neoforge.configuration.uitext.section",
                     new Object[]{
                        class_2561.method_43471(ConfigurationScreen.translationChecker.check(key + ".button", "neoforge.configuration.uitext.sectiontext"))
                     }
                  ),
                  button -> this.field_22787
                     .method_1507(
                        this.sectionCache
                           .computeIfAbsent(
                              key,
                              k -> new ConfigurationScreen.ConfigurationListScreen<>(
                                 ConfigurationScreen.ConfigurationSectionScreen.Context.list(this.context, this),
                                 key,
                                 class_2561.method_43469(
                                    "neoforge.configuration.uitext.breadcrumb", new Object[]{this.method_25440(), this.getTranslationComponent(key)}
                                 ),
                                 spec,
                                 list
                              )
                           )
                           .rebuild()
                     )
               )
               .method_46436(class_7919.method_47407(this.getTooltipComponent(key, null)))
               .method_46431(),
            false
         );
      }

      public void method_25394(class_332 graphics, int p_281550_, int p_282878_, float p_282465_) {
         this.setUndoButtonstate(this.undoManager.canUndo());
         this.setResetButtonstate(this.isAnyNondefault());
         super.method_25394(graphics, p_281550_, p_282878_, p_282465_);
      }

      protected void method_31387() {
         if (this.undoButton == null && this.resetButton == null) {
            super.method_31387();
         } else {
            class_8667 linearlayout = (class_8667)this.field_49503.method_48996(class_8667.method_52742().method_52735(8));
            if (this.undoButton != null) {
               linearlayout.method_52736(this.undoButton);
            }

            if (this.resetButton != null) {
               linearlayout.method_52736(this.resetButton);
            }

            linearlayout.method_52736(this.doneButton);
         }
      }

      protected void createUndoButton() {
         this.undoButton = class_4185.method_46430(ConfigurationScreen.UNDO, button -> {
            this.undoManager.undo();
            this.rebuild();
         }).method_46436(class_7919.method_47407(ConfigurationScreen.UNDO_TOOLTIP)).method_46432(120).method_46431();
         this.undoButton.field_22763 = false;
      }

      protected void setUndoButtonstate(boolean state) {
         if (this.undoButton != null) {
            this.undoButton.field_22763 = state;
         }
      }

      protected void createResetButton() {
         this.resetButton = class_4185.method_46430(
               ConfigurationScreen.RESET,
               button -> {
                  List<ConfigurationScreen.UndoManager.Step<?>> list = new ArrayList<>();

                  for (Entry entry : this.context.entries) {
                     if (entry.getRawValue() instanceof ModConfigSpec.ConfigValue cv
                        && !(this.getValueSpec(entry.getKey()) instanceof ModConfigSpec.ListValueSpec)
                        && this.isNonDefault(cv)) {
                        String key = entry.getKey();
                        list.add(this.undoManager.step(v -> {
                           cv.set(v);
                           this.onChanged(key);
                        }, this.getValueSpec(key).correct(null), v -> {
                           cv.set(v);
                           this.onChanged(key);
                        }, cv.getRaw()));
                     }
                  }

                  this.undoManager.add(list);
                  this.rebuild();
               }
            )
            .method_46436(class_7919.method_47407(ConfigurationScreen.RESET_TOOLTIP))
            .method_46432(120)
            .method_46431();
      }

      protected void setResetButtonstate(boolean state) {
         if (this.resetButton != null) {
            this.resetButton.field_22763 = state;
         }
      }

      public void method_25419() {
         if (this.changed) {
            if (this.field_21335 instanceof ConfigurationScreen.ConfigurationSectionScreen parent) {
               parent.changed = true;
            } else {
               this.context.modSpec.save();
            }

            if (this.field_21335 instanceof ConfigurationScreen.ConfigurationSectionScreen parent) {
               parent.needsRestart = parent.needsRestart.with(this.needsRestart);
            } else if (this.field_21335 instanceof ConfigurationScreen parent) {
               parent.needsRestart = parent.needsRestart.with(this.needsRestart);
            }
         }

         super.method_25419();
      }

      public record Context(
         String modId,
         class_437 parent,
         ModConfig modConfig,
         ModConfigSpec modSpec,
         Set<? extends Entry> entries,
         Map<String, Object> valueSpecs,
         List<String> keylist,
         ConfigurationScreen.ConfigurationSectionScreen.Filter filter
      ) {
         @Internal
         public Context(
            String modId,
            class_437 parent,
            ModConfig modConfig,
            ModConfigSpec modSpec,
            Set<? extends Entry> entries,
            Map<String, Object> valueSpecs,
            List<String> keylist,
            ConfigurationScreen.ConfigurationSectionScreen.Filter filter
         ) {
            this.modId = modId;
            this.parent = parent;
            this.modConfig = modConfig;
            this.modSpec = modSpec;
            this.entries = entries;
            this.valueSpecs = valueSpecs;
            this.keylist = keylist;
            this.filter = filter;
         }

         public static ConfigurationScreen.ConfigurationSectionScreen.Context top(
            String modId, class_437 parent, ModConfig modConfig, ConfigurationScreen.ConfigurationSectionScreen.Filter filter
         ) {
            return new ConfigurationScreen.ConfigurationSectionScreen.Context(
               modId,
               parent,
               modConfig,
               (ModConfigSpec)modConfig.getSpec(),
               ((ModConfigSpec)modConfig.getSpec()).getValues().entrySet(),
               ((ModConfigSpec)modConfig.getSpec()).getSpec().valueMap(),
               List.of(),
               filter
            );
         }

         public static ConfigurationScreen.ConfigurationSectionScreen.Context section(
            ConfigurationScreen.ConfigurationSectionScreen.Context parentContext,
            class_437 parent,
            Set<? extends Entry> entries,
            Map<String, Object> valueSpecs,
            String key
         ) {
            return new ConfigurationScreen.ConfigurationSectionScreen.Context(
               parentContext.modId,
               parent,
               parentContext.modConfig,
               parentContext.modSpec,
               entries,
               valueSpecs,
               parentContext.makeKeyList(key),
               parentContext.filter
            );
         }

         public static ConfigurationScreen.ConfigurationSectionScreen.Context list(
            ConfigurationScreen.ConfigurationSectionScreen.Context parentContext, class_437 parent
         ) {
            return new ConfigurationScreen.ConfigurationSectionScreen.Context(
               parentContext.modId,
               parent,
               parentContext.modConfig,
               parentContext.modSpec,
               parentContext.entries,
               parentContext.valueSpecs,
               parentContext.keylist,
               null
            );
         }

         private ArrayList<String> makeKeyList(String key) {
            ArrayList<String> result = new ArrayList<>(this.keylist);
            result.add(key);
            return result;
         }
      }

      public record Custom<T>(List<T> values) implements class_7178<T> {
         public static final ConfigurationScreen.ConfigurationSectionScreen.Custom<Boolean> BOOLEAN_VALUES_NO_PREFIX = new ConfigurationScreen.ConfigurationSectionScreen.Custom<>(
            ImmutableList.of(Boolean.TRUE, Boolean.FALSE)
         );

         public Function<class_7172<T>, class_339> method_41756(class_7277<T> tooltip, class_315 options, int x, int y, int width, Consumer<T> target) {
            return optionsInstance -> class_5676.method_32606(optionsInstance.field_37864)
               .method_42729(class_5680.method_32627(this.values))
               .method_32618(tooltip)
               .method_32616()
               .method_32619(optionsInstance.method_41753())
               .method_32617(x, y, width, 20, optionsInstance.field_38280, (source, newValue) -> {
                  optionsInstance.method_41748(newValue);
                  options.method_1640();
                  target.accept((T)newValue);
               });
         }

         public Optional<T> method_41758(T value) {
            return this.values.contains(value) ? Optional.of(value) : Optional.empty();
         }

         public Codec<T> comp_675() {
            return null;
         }
      }

      public record Element(
         @Nullable class_2561 name, @Nullable class_2561 tooltip, @Nullable class_339 widget, @Nullable class_7172<?> option, boolean undoable
      ) {
         @Internal
         public Element(@Nullable class_2561 name, @Nullable class_2561 tooltip, @Nullable class_339 widget, @Nullable class_7172<?> option, boolean undoable) {
            this.name = name;
            this.tooltip = tooltip;
            this.widget = widget;
            this.option = option;
            this.undoable = undoable;
         }

         public Element(@Nullable class_2561 name, @Nullable class_2561 tooltip, class_339 widget) {
            this(name, tooltip, widget, null, true);
         }

         public Element(@Nullable class_2561 name, @Nullable class_2561 tooltip, class_339 widget, boolean undoable) {
            this(name, tooltip, widget, null, undoable);
         }

         public Element(class_2561 name, class_2561 tooltip, class_7172<?> option) {
            this(name, tooltip, null, option, true);
         }

         public Element(class_2561 name, class_2561 tooltip, class_7172<?> option, boolean undoable) {
            this(name, tooltip, null, option, undoable);
         }

         public class_339 getWidget(class_315 options) {
            return this.widget != null ? this.widget : this.option.method_57701(options);
         }

         @Nullable
         public Object any() {
            return this.widget != null ? this.widget : this.option;
         }
      }

      public interface Filter {
         @Nullable
         ConfigurationScreen.ConfigurationSectionScreen.Element filterEntry(
            ConfigurationScreen.ConfigurationSectionScreen.Context var1, String var2, ConfigurationScreen.ConfigurationSectionScreen.Element var3
         );
      }
   }

   private static final class TooltipConfirmScreen extends class_410 {
      boolean seenYes = false;

      private TooltipConfirmScreen(BooleanConsumer callback, class_2561 title, class_2561 message, class_2561 yesButton, class_2561 noButton) {
         super(callback, title, message, yesButton, noButton);
      }

      protected void method_25426() {
         this.seenYes = false;
         super.method_25426();
      }

      protected void method_37052(class_4185 button) {
         if (this.seenYes) {
            button.method_47400(class_7919.method_47407(ConfigurationScreen.RESTART_NO_TOOLTIP));
         } else {
            this.seenYes = true;
         }

         super.method_37052(button);
      }
   }

   public static class TranslationChecker {
      private static final Logger LOGGER = LogManager.getLogger();
      private final Set<String> untranslatables = new HashSet<>();
      private final Set<String> untranslatablesWithFallback = new HashSet<>();

      public String check(String translationKey) {
         if (!class_1074.method_4663(translationKey)) {
            this.untranslatables.add(translationKey);
         }

         return translationKey;
      }

      public String check(String translationKey, String fallback) {
         if (!class_1074.method_4663(translationKey)) {
            this.untranslatablesWithFallback.add(translationKey);
            return this.check(fallback);
         } else {
            return translationKey;
         }
      }

      public boolean existsWithFallback(String translationKey) {
         if (!class_1074.method_4663(translationKey)) {
            this.untranslatablesWithFallback.add(translationKey);
            return false;
         } else {
            return true;
         }
      }

      public void finish() {
         if (ForgeConfigApiPortConfig.getBoolConfigValue(ModConfigValues.LOG_UNTRANSLATED_CONFIGURATION_WARNINGS)
            && CommonAbstractions.INSTANCE.isDevelopmentEnvironment()
            && (!this.untranslatables.isEmpty() || !this.untranslatablesWithFallback.isEmpty())) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(
               "\n\tDev warning - Untranslated configuration keys encountered. Please translate your configuration keys so users can properly configure your mod.\n"
            );
            if (!this.untranslatables.isEmpty()) {
               stringBuilder.append("\nUntranslated keys:");

               for (String key : this.untranslatables) {
                  stringBuilder.append("\n  \"").append(key).append("\": \"\",");
               }
            }

            if (!this.untranslatablesWithFallback.isEmpty()) {
               stringBuilder.append("\nThe following keys have fallbacks. Please check if those are suitable, and translate them if they're not.");

               for (String key : this.untranslatablesWithFallback) {
                  stringBuilder.append("\n  \"").append(key).append("\": \"\",");
               }
            }

            LOGGER.warn(stringBuilder);
         }

         this.untranslatables.clear();
      }
   }

   public static final class UndoManager {
      private final List<ConfigurationScreen.UndoManager.Step<?>> undos = new ArrayList<>();
      private final List<ConfigurationScreen.UndoManager.Step<?>> redos = new ArrayList<>();

      public void undo() {
         if (this.canUndo()) {
            ConfigurationScreen.UndoManager.Step<?> step = (ConfigurationScreen.UndoManager.Step<?>)this.undos.removeLast();
            step.runUndo();
            this.redos.add(step);
         }
      }

      public void redo() {
         if (this.canRedo()) {
            ConfigurationScreen.UndoManager.Step<?> step = (ConfigurationScreen.UndoManager.Step<?>)this.redos.removeLast();
            step.runRedo();
            this.undos.add(step);
         }
      }

      private void add(ConfigurationScreen.UndoManager.Step<?> step, boolean execute) {
         this.undos.add(step);
         this.redos.clear();
         if (execute) {
            step.runRedo();
         }
      }

      public <T> ConfigurationScreen.UndoManager.Step<T> step(Consumer<T> run, T newValue, Consumer<T> undo, T oldValue) {
         return new ConfigurationScreen.UndoManager.Step<>(run, newValue, undo, oldValue);
      }

      public <T> void add(Consumer<T> run, T newValue, Consumer<T> undo, T oldValue) {
         this.add(this.step(run, newValue, undo, oldValue), true);
      }

      public <T> void addNoExecute(Consumer<T> run, T newValue, Consumer<T> undo, T oldValue) {
         this.add(this.step(run, newValue, undo, oldValue), false);
      }

      public void add(ConfigurationScreen.UndoManager.Step<?>... steps) {
         this.add(ImmutableList.copyOf(steps));
      }

      public void add(List<ConfigurationScreen.UndoManager.Step<?>> steps) {
         this.add(
            new ConfigurationScreen.UndoManager.Step<>(
               n -> steps.forEach(ConfigurationScreen.UndoManager.Step::runRedo), null, n -> steps.forEach(ConfigurationScreen.UndoManager.Step::runUndo), null
            ),
            true
         );
      }

      public boolean canUndo() {
         return !this.undos.isEmpty();
      }

      public boolean canRedo() {
         return !this.redos.isEmpty();
      }

      public record Step<T>(Consumer<T> run, T newValue, Consumer<T> undo, T oldValue) {
         private void runUndo() {
            this.undo.accept(this.oldValue);
         }

         private void runRedo() {
            this.run.accept(this.newValue);
         }
      }
   }
}
