package me.shedaniel.clothconfig2.api;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.gui.AbstractConfigScreen;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_5250;
import net.minecraft.class_5481;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public abstract class AbstractConfigEntry<T>
   extends DynamicElementListWidget.ElementEntry<AbstractConfigEntry<T>>
   implements ReferenceProvider<T>,
   ValueHolder<T> {
   private AbstractConfigScreen screen;
   private Supplier<Optional<class_2561>> errorSupplier;
   @Nullable
   private List<ReferenceProvider<?>> referencableEntries = null;
   @Nullable
   protected Consumer<T> saveCallback;
   private int cacheFieldNameHash = -1;
   private List<String> cachedTags = null;
   private Iterable<String> additionalSearchTags = null;

   public final void setReferenceProviderEntries(@Nullable List<ReferenceProvider<?>> referencableEntries) {
      this.referencableEntries = referencableEntries;
   }

   public void requestReferenceRebuilding() {
      AbstractConfigScreen configScreen = this.getConfigScreen();
      if (configScreen instanceof ReferenceBuildingConfigScreen) {
         ((ReferenceBuildingConfigScreen)configScreen).requestReferenceRebuilding();
      }
   }

   @NotNull
   @Override
   public AbstractConfigEntry<T> provideReferenceEntry() {
      return this;
   }

   @Nullable
   @Internal
   public final List<ReferenceProvider<?>> getReferenceProviderEntries() {
      return this.referencableEntries;
   }

   public abstract boolean isRequiresRestart();

   public abstract void setRequiresRestart(boolean var1);

   public abstract class_2561 getFieldName();

   public class_2561 getDisplayedFieldName() {
      class_5250 text = this.getFieldName().method_27661();
      boolean hasError = this.getConfigError().isPresent();
      boolean isEdited = this.isEdited();
      if (hasError) {
         text = text.method_27692(class_124.field_1061);
      }

      if (isEdited) {
         text = text.method_27692(class_124.field_1056);
      }

      if (!hasError && !isEdited) {
         text = text.method_27692(class_124.field_1080);
      }

      if (!this.isEnabled()) {
         text = text.method_27692(class_124.field_1063);
      }

      return text;
   }

   public Iterator<String> getSearchTags() {
      String s = this.getFieldName().getString();
      if (s.isEmpty()) {
         this.cacheFieldNameHash = -1;
         this.cachedTags = null;
         return ((Iterable)MoreObjects.firstNonNull(this.additionalSearchTags, Collections.emptyList())).iterator();
      } else {
         if (s.hashCode() != this.cacheFieldNameHash) {
            this.cacheFieldNameHash = s.hashCode();
            this.cachedTags = Lists.newArrayList(s.split(" "));
         }

         return Iterators.concat(
            this.cachedTags.iterator(), ((Iterable)MoreObjects.firstNonNull(this.additionalSearchTags, Collections.emptyList())).iterator()
         );
      }
   }

   public void appendSearchTags(Iterable<String> tags) {
      if (this.additionalSearchTags == null) {
         this.additionalSearchTags = tags;
      } else {
         this.additionalSearchTags = Iterables.concat(this.additionalSearchTags, tags);
      }
   }

   public final Optional<class_2561> getConfigError() {
      return this.errorSupplier != null && this.errorSupplier.get().isPresent() ? this.errorSupplier.get() : this.getError();
   }

   public void lateRender(class_332 graphics, int mouseX, int mouseY, float delta) {
   }

   public void setErrorSupplier(Supplier<Optional<class_2561>> errorSupplier) {
      this.errorSupplier = errorSupplier;
   }

   public Optional<class_2561> getError() {
      return Optional.empty();
   }

   public abstract Optional<T> getDefaultValue();

   @Nullable
   public final AbstractConfigScreen getConfigScreen() {
      return this.screen;
   }

   public final void addTooltip(@NotNull Tooltip tooltip) {
      this.screen.addTooltip(tooltip);
   }

   protected class_5481[] wrapLinesToScreen(class_2561[] lines) {
      return this.wrapLines(lines, this.screen.field_22789);
   }

   protected class_5481[] wrapLines(class_2561[] lines, int width) {
      class_327 font = class_310.method_1551().field_1772;
      return Arrays.stream(lines).map(line -> font.method_1728(line, width)).flatMap(Collection::stream).toArray(class_5481[]::new);
   }

   public void updateSelected(boolean isSelected) {
   }

   @Internal
   public final void setScreen(AbstractConfigScreen screen) {
      this.screen = screen;
   }

   public void save() {
      if (this.saveCallback != null) {
         this.saveCallback.accept(this.getValue());
      }
   }

   public boolean isEdited() {
      return this.getConfigError().isPresent();
   }

   @Override
   public int getItemHeight() {
      return 24;
   }

   public int getInitialReferenceOffset() {
      return 0;
   }
}
