package me.shedaniel.clothconfig2.gui.entries;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.Tooltip;
import me.shedaniel.math.Point;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
public abstract class TooltipListEntry<T> extends AbstractConfigListEntry<T> {
   @Nullable
   private Supplier<Optional<class_2561[]>> tooltipSupplier;

   @Deprecated
   @Internal
   public TooltipListEntry(class_2561 fieldName, @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this(fieldName, tooltipSupplier, false);
   }

   @Deprecated
   @Internal
   public TooltipListEntry(class_2561 fieldName, @Nullable Supplier<Optional<class_2561[]>> tooltipSupplier, boolean requiresRestart) {
      super(fieldName, requiresRestart);
      this.tooltipSupplier = tooltipSupplier;
   }

   @Override
   public void render(class_332 graphics, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isHovered, float delta) {
      super.render(graphics, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isHovered, delta);
      if (this.isMouseInside(mouseX, mouseY, x, y, entryWidth, entryHeight)) {
         this.getTooltip(mouseX, mouseY).map(lines -> Tooltip.of(new Point(mouseX, mouseY), this.wrapLinesToScreen(lines))).ifPresent(this::addTooltip);
      }
   }

   public Optional<class_2561[]> getTooltip() {
      Stream<class_2561> tooltipStream = Stream.ofNullable(this.tooltipSupplier).map(Supplier::get).flatMap(Optional::stream).flatMap(Arrays::stream);
      class_2561 disabled = this.isEnabled() ? null : class_2561.method_43471("text.cloth-config.disabled_tooltip");
      class_2561[] lines = Stream.concat(tooltipStream, Stream.ofNullable(disabled)).toArray(class_2561[]::new);
      return lines.length < 1 ? Optional.empty() : Optional.of(lines);
   }

   public Optional<class_2561[]> getTooltip(int mouseX, int mouseY) {
      return this.getTooltip();
   }

   @Nullable
   public Supplier<Optional<class_2561[]>> getTooltipSupplier() {
      return this.tooltipSupplier;
   }

   public void setTooltipSupplier(@Nullable Supplier<Optional<class_2561[]>> tooltipSupplier) {
      this.tooltipSupplier = tooltipSupplier;
   }
}
