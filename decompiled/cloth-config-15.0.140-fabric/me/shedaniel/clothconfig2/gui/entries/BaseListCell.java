package me.shedaniel.clothconfig2.gui.entries;

import java.util.Optional;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_362;
import net.minecraft.class_6379;

@Environment(EnvType.CLIENT)
public abstract class BaseListCell extends class_362 implements class_6379 {
   private Supplier<Optional<class_2561>> errorSupplier;

   public final int getPreferredTextColor() {
      return this.getConfigError().isPresent() ? 16733525 : 14737632;
   }

   public final Optional<class_2561> getConfigError() {
      return this.errorSupplier != null && this.errorSupplier.get().isPresent() ? this.errorSupplier.get() : this.getError();
   }

   public void setErrorSupplier(Supplier<Optional<class_2561>> errorSupplier) {
      this.errorSupplier = errorSupplier;
   }

   public abstract Optional<class_2561> getError();

   public abstract int getCellHeight();

   public abstract void render(class_332 var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9, float var10);

   public void updateSelected(boolean isSelected) {
   }

   public boolean isRequiresRestart() {
      return false;
   }

   public boolean isEdited() {
      return this.getConfigError().isPresent();
   }

   public void onAdd() {
   }

   public void onDelete() {
   }
}
