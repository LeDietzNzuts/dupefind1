package me.shedaniel.clothconfig2.gui;

import java.util.Optional;
import java.util.function.Supplier;
import me.shedaniel.clothconfig2.api.Tooltip;
import me.shedaniel.math.Point;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_4264;
import net.minecraft.class_5348;
import net.minecraft.class_6382;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ClothConfigTabButton extends class_4264 {
   private final int index;
   private final ClothConfigScreen screen;
   @Nullable
   private final Supplier<Optional<class_5348[]>> descriptionSupplier;

   public ClothConfigTabButton(
      ClothConfigScreen screen,
      int index,
      int int_1,
      int int_2,
      int int_3,
      int int_4,
      class_2561 string_1,
      Supplier<Optional<class_5348[]>> descriptionSupplier
   ) {
      super(int_1, int_2, int_3, int_4, string_1);
      this.index = index;
      this.screen = screen;
      this.descriptionSupplier = descriptionSupplier;
   }

   public ClothConfigTabButton(ClothConfigScreen screen, int index, int int_1, int int_2, int int_3, int int_4, class_2561 string_1) {
      this(screen, index, int_1, int_2, int_3, int_4, string_1, null);
   }

   public void method_25306() {
      if (this.index != -1) {
         this.screen.selectedCategoryIndex = this.index;
      }

      this.screen.method_25423(class_310.method_1551(), this.screen.field_22789, this.screen.field_22790);
   }

   public void method_48579(class_332 graphics, int mouseX, int mouseY, float delta) {
      this.field_22763 = this.index != this.screen.selectedCategoryIndex;
      super.method_48579(graphics, mouseX, mouseY, delta);
      if (this.method_25405(mouseX, mouseY)) {
         Optional<class_5348[]> tooltip = this.getDescription();
         if (tooltip.isPresent() && tooltip.get().length > 0) {
            this.screen.addTooltip(Tooltip.of(new Point(mouseX, mouseY), tooltip.get()));
         }
      }
   }

   protected boolean method_25361(double double_1, double double_2) {
      return this.field_22764 && this.field_22763 && this.method_25405(double_1, double_2);
   }

   public boolean method_25405(double double_1, double double_2) {
      return this.field_22764
         && double_1 >= this.method_46426()
         && double_2 >= this.method_46427()
         && double_1 < this.method_46426() + this.field_22758
         && double_2 < this.method_46427() + this.field_22759
         && double_1 >= 20.0
         && double_1 < this.screen.field_22789 - 20;
   }

   public Optional<class_5348[]> getDescription() {
      return this.descriptionSupplier != null ? this.descriptionSupplier.get() : Optional.empty();
   }

   public void method_47399(class_6382 narrationElementOutput) {
   }
}
