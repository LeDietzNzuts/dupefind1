package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import me.shedaniel.clothconfig2.ClothConfigInitializer;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.ConfigScreen;
import me.shedaniel.clothconfig2.api.Modifier;
import me.shedaniel.clothconfig2.api.ModifierKeyCode;
import me.shedaniel.clothconfig2.api.TickableWidget;
import me.shedaniel.clothconfig2.api.Tooltip;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.math.Rectangle;
import net.minecraft.class_156;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_3675;
import net.minecraft.class_407;
import net.minecraft.class_410;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_293.class_5596;
import net.minecraft.class_3675.class_307;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.joml.Matrix4f;

public abstract class AbstractConfigScreen extends class_437 implements ConfigScreen {
   protected static final class_2960 CONFIG_TEX = class_2960.method_60655("cloth-config2", "textures/gui/cloth_config.png");
   private final class_2960 backgroundLocation;
   protected boolean confirmSave;
   protected final class_437 parent;
   private boolean alwaysShowTabs = false;
   private boolean transparentBackground = false;
   @Nullable
   private class_2561 defaultFallbackCategory = null;
   public int selectedCategoryIndex = 0;
   private boolean editable = true;
   private KeyCodeEntry focusedBinding;
   private ModifierKeyCode startedKeyCode = null;
   private final List<Tooltip> tooltips = Lists.newArrayList();
   @Nullable
   private Runnable savingRunnable = null;
   @Nullable
   protected Consumer<class_437> afterInitConsumer = null;

   protected AbstractConfigScreen(class_437 parent, class_2561 title, class_2960 backgroundLocation) {
      super(title);
      this.parent = parent;
      this.backgroundLocation = backgroundLocation;
   }

   public List<class_364> childrenL() {
      return super.method_25396();
   }

   @Override
   public void setSavingRunnable(@Nullable Runnable savingRunnable) {
      this.savingRunnable = savingRunnable;
   }

   @Override
   public void setAfterInitConsumer(@Nullable Consumer<class_437> afterInitConsumer) {
      this.afterInitConsumer = afterInitConsumer;
   }

   @Override
   public class_2960 getBackgroundLocation() {
      return this.backgroundLocation;
   }

   @Override
   public boolean isRequiresRestart() {
      for (List<AbstractConfigEntry<?>> entries : this.getCategorizedEntries().values()) {
         for (AbstractConfigEntry<?> entry : entries) {
            if (entry.getConfigError().isEmpty() && entry.isEdited() && entry.isRequiresRestart()) {
               return true;
            }
         }
      }

      return false;
   }

   public abstract Map<class_2561, List<AbstractConfigEntry<?>>> getCategorizedEntries();

   @Override
   public boolean isEdited() {
      for (List<AbstractConfigEntry<?>> entries : this.getCategorizedEntries().values()) {
         for (AbstractConfigEntry<?> entry : entries) {
            if (entry.isEdited()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean isShowingTabs() {
      return this.isAlwaysShowTabs() || this.getCategorizedEntries().size() > 1;
   }

   public boolean isAlwaysShowTabs() {
      return this.alwaysShowTabs;
   }

   @Internal
   public void setAlwaysShowTabs(boolean alwaysShowTabs) {
      this.alwaysShowTabs = alwaysShowTabs;
   }

   public boolean isTransparentBackground() {
      return this.transparentBackground;
   }

   @Internal
   public void setTransparentBackground(boolean transparentBackground) {
      this.transparentBackground = transparentBackground;
   }

   public class_2561 getFallbackCategory() {
      return this.defaultFallbackCategory != null ? this.defaultFallbackCategory : this.getCategorizedEntries().keySet().iterator().next();
   }

   @Internal
   public void setFallbackCategory(@Nullable class_2561 defaultFallbackCategory) {
      this.defaultFallbackCategory = defaultFallbackCategory;
      List<class_2561> categories = Lists.newArrayList(this.getCategorizedEntries().keySet());

      for (int i = 0; i < categories.size(); i++) {
         class_2561 category = categories.get(i);
         if (category.equals(this.getFallbackCategory())) {
            this.selectedCategoryIndex = i;
            break;
         }
      }
   }

   @Override
   public void saveAll(boolean openOtherScreens) {
      for (List<AbstractConfigEntry<?>> entries : Lists.newArrayList(this.getCategorizedEntries().values())) {
         for (AbstractConfigEntry<?> entry : entries) {
            entry.save();
         }
      }

      this.save();
      if (openOtherScreens) {
         if (this.isRequiresRestart()) {
            this.field_22787.method_1507(new ClothRequiresRestartScreen(this.parent));
         } else {
            this.field_22787.method_1507(this.parent);
         }
      }
   }

   public void save() {
      Optional.ofNullable(this.savingRunnable).ifPresent(Runnable::run);
   }

   public boolean isEditable() {
      return this.editable;
   }

   @Internal
   public void setEditable(boolean editable) {
      this.editable = editable;
   }

   @Internal
   public void setConfirmSave(boolean confirmSave) {
      this.confirmSave = confirmSave;
   }

   public KeyCodeEntry getFocusedBinding() {
      return this.focusedBinding;
   }

   @Internal
   public void setFocusedBinding(KeyCodeEntry focusedBinding) {
      this.focusedBinding = focusedBinding;
      if (focusedBinding != null) {
         this.startedKeyCode = this.focusedBinding.getValue();
         this.startedKeyCode.setKeyCodeAndModifier(class_3675.field_16237, Modifier.none());
      } else {
         this.startedKeyCode = null;
      }
   }

   public boolean method_25406(double double_1, double double_2, int int_1) {
      if (this.focusedBinding != null && this.startedKeyCode != null && !this.startedKeyCode.isUnknown() && this.focusedBinding.isAllowMouse()) {
         this.focusedBinding.setValue(this.startedKeyCode);
         this.setFocusedBinding(null);
         return true;
      } else {
         return super.method_25406(double_1, double_2, int_1);
      }
   }

   public boolean method_16803(int int_1, int int_2, int int_3) {
      if (this.focusedBinding != null && this.startedKeyCode != null && this.focusedBinding.isAllowKey()) {
         this.focusedBinding.setValue(this.startedKeyCode);
         this.setFocusedBinding(null);
         return true;
      } else {
         return super.method_16803(int_1, int_2, int_3);
      }
   }

   public boolean method_25402(double double_1, double double_2, int int_1) {
      if (this.focusedBinding != null && this.startedKeyCode != null && this.focusedBinding.isAllowMouse()) {
         if (this.startedKeyCode.isUnknown()) {
            this.startedKeyCode.setKeyCode(class_307.field_1672.method_1447(int_1));
         } else if (this.focusedBinding.isAllowModifiers() && this.startedKeyCode.getType() == class_307.field_1668) {
            int code = this.startedKeyCode.getKeyCode().method_1444();
            if (class_310.field_1703 ? code == 343 || code == 347 : code == 341 || code == 345) {
               Modifier modifier = this.startedKeyCode.getModifier();
               this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
               this.startedKeyCode.setKeyCode(class_307.field_1672.method_1447(int_1));
               return true;
            }

            if (code == 344 || code == 340) {
               Modifier modifier = this.startedKeyCode.getModifier();
               this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
               this.startedKeyCode.setKeyCode(class_307.field_1672.method_1447(int_1));
               return true;
            }

            if (code == 342 || code == 346) {
               Modifier modifier = this.startedKeyCode.getModifier();
               this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
               this.startedKeyCode.setKeyCode(class_307.field_1672.method_1447(int_1));
               return true;
            }
         }

         return true;
      } else {
         return this.focusedBinding != null ? true : super.method_25402(double_1, double_2, int_1);
      }
   }

   public boolean method_25404(int int_1, int int_2, int int_3) {
      if (this.focusedBinding != null && (this.focusedBinding.isAllowKey() || int_1 == 256)) {
         if (int_1 != 256) {
            if (this.startedKeyCode.isUnknown()) {
               this.startedKeyCode.setKeyCode(class_3675.method_15985(int_1, int_2));
            } else if (this.focusedBinding.isAllowModifiers()) {
               if (this.startedKeyCode.getType() == class_307.field_1668) {
                  int code = this.startedKeyCode.getKeyCode().method_1444();
                  if (class_310.field_1703 ? code == 343 || code == 347 : code == 341 || code == 345) {
                     Modifier modifier = this.startedKeyCode.getModifier();
                     this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                     this.startedKeyCode.setKeyCode(class_3675.method_15985(int_1, int_2));
                     return true;
                  }

                  if (code == 344 || code == 340) {
                     Modifier modifier = this.startedKeyCode.getModifier();
                     this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                     this.startedKeyCode.setKeyCode(class_3675.method_15985(int_1, int_2));
                     return true;
                  }

                  if (code == 342 || code == 346) {
                     Modifier modifier = this.startedKeyCode.getModifier();
                     this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                     this.startedKeyCode.setKeyCode(class_3675.method_15985(int_1, int_2));
                     return true;
                  }
               }

               if (class_310.field_1703 ? int_1 == 343 || int_1 == 347 : int_1 == 341 || int_1 == 345) {
                  Modifier modifier = this.startedKeyCode.getModifier();
                  this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                  return true;
               }

               if (int_1 == 344 || int_1 == 340) {
                  Modifier modifier = this.startedKeyCode.getModifier();
                  this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                  return true;
               }

               if (int_1 == 342 || int_1 == 346) {
                  Modifier modifier = this.startedKeyCode.getModifier();
                  this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                  return true;
               }
            }
         } else {
            this.focusedBinding.setValue(ModifierKeyCode.unknown());
            this.setFocusedBinding(null);
         }

         return true;
      } else if (this.focusedBinding != null && int_1 != 256) {
         return true;
      } else {
         return int_1 == 256 && this.method_25422() ? this.quit() : super.method_25404(int_1, int_2, int_3);
      }
   }

   protected final boolean quit() {
      if (this.confirmSave && this.isEdited()) {
         this.field_22787
            .method_1507(
               new class_410(
                  new AbstractConfigScreen.QuitSaveConsumer(),
                  class_2561.method_43471("text.cloth-config.quit_config"),
                  class_2561.method_43471("text.cloth-config.quit_config_sure"),
                  class_2561.method_43471("text.cloth-config.quit_discard"),
                  class_2561.method_43471("gui.cancel")
               )
            );
      } else {
         this.field_22787.method_1507(this.parent);
      }

      return true;
   }

   public void method_25393() {
      super.method_25393();
      boolean edited = this.isEdited();
      Optional.ofNullable(this.getQuitButton())
         .ifPresent(button -> button.method_25355(edited ? class_2561.method_43471("text.cloth-config.cancel_discard") : class_2561.method_43471("gui.cancel")));

      for (class_364 child : this.method_25396()) {
         if (child instanceof TickableWidget widget) {
            widget.tick();
         }
      }
   }

   @Nullable
   protected class_339 getQuitButton() {
      return null;
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float delta) {
      super.method_25394(graphics, mouseX, mouseY, delta);

      for (Tooltip tooltip : this.tooltips) {
         graphics.method_51447(class_310.method_1551().field_1772, tooltip.getText(), tooltip.getX(), tooltip.getY());
      }

      this.tooltips.clear();
   }

   @Override
   public void addTooltip(Tooltip tooltip) {
      this.tooltips.add(tooltip);
   }

   protected void overlayBackground(class_332 graphics, Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
      this.overlayBackground(graphics.method_51448(), rect, red, green, blue, startAlpha, endAlpha);
   }

   protected void overlayBackground(class_4587 matrices, Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
      this.overlayBackground(matrices.method_23760().method_23761(), rect, red, green, blue, startAlpha, endAlpha);
   }

   protected void overlayBackground(Matrix4f matrix, Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
      if (!this.isTransparentBackground()) {
         class_289 tesselator = class_289.method_1348();
         class_287 buffer = tesselator.method_60827(class_5596.field_27382, class_290.field_1575);
         RenderSystem.setShader(class_757::method_34543);
         RenderSystem.setShaderTexture(0, this.getBackgroundLocation());
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         buffer.method_22918(matrix, rect.getMinX(), rect.getMaxY(), 0.0F)
            .method_22913(rect.getMinX() / 32.0F, rect.getMaxY() / 32.0F)
            .method_1336(red, green, blue, endAlpha);
         buffer.method_22918(matrix, rect.getMaxX(), rect.getMaxY(), 0.0F)
            .method_22913(rect.getMaxX() / 32.0F, rect.getMaxY() / 32.0F)
            .method_1336(red, green, blue, endAlpha);
         buffer.method_22918(matrix, rect.getMaxX(), rect.getMinY(), 0.0F)
            .method_22913(rect.getMaxX() / 32.0F, rect.getMinY() / 32.0F)
            .method_1336(red, green, blue, startAlpha);
         buffer.method_22918(matrix, rect.getMinX(), rect.getMinY(), 0.0F)
            .method_22913(rect.getMinX() / 32.0F, rect.getMinY() / 32.0F)
            .method_1336(red, green, blue, startAlpha);
      }
   }

   public boolean method_25430(@Nullable class_2583 style) {
      if (style == null) {
         return false;
      } else {
         class_2558 clickEvent = style.method_10970();
         if (clickEvent != null && clickEvent.method_10845() == class_2559.field_11749) {
            try {
               URI uri = new URI(clickEvent.method_10844());
               String string = uri.getScheme();
               if (string == null) {
                  throw new URISyntaxException(clickEvent.method_10844(), "Missing protocol");
               }

               if (!string.equalsIgnoreCase("http") && !string.equalsIgnoreCase("https")) {
                  throw new URISyntaxException(clickEvent.method_10844(), "Unsupported protocol: " + string.toLowerCase(Locale.ROOT));
               }

               class_310.method_1551().method_1507(new class_407(openInBrowser -> {
                  if (openInBrowser) {
                     class_156.method_668().method_673(uri);
                  }

                  class_310.method_1551().method_1507(this);
               }, clickEvent.method_10844(), true));
            } catch (URISyntaxException var5) {
               ClothConfigInitializer.LOGGER.error("Can't open url for {}", clickEvent, var5);
            }

            return true;
         } else {
            return super.method_25430(style);
         }
      }
   }

   private class QuitSaveConsumer implements BooleanConsumer {
      public void accept(boolean t) {
         if (!t) {
            AbstractConfigScreen.this.field_22787.method_1507(AbstractConfigScreen.this);
         } else {
            AbstractConfigScreen.this.field_22787.method_1507(AbstractConfigScreen.this.parent);
         }
      }
   }
}
