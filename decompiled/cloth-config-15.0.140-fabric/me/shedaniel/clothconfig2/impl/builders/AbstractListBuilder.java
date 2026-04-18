package me.shedaniel.clothconfig2.impl.builders;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.class_2561;

public abstract class AbstractListBuilder<T, A extends AbstractConfigListEntry, SELF extends AbstractListBuilder<T, A, SELF>>
   extends AbstractFieldBuilder<List<T>, A, SELF> {
   protected Function<T, Optional<class_2561>> cellErrorSupplier;
   private boolean expanded = false;
   private class_2561 addTooltip = class_2561.method_43471("text.cloth-config.list.add");
   private class_2561 removeTooltip = class_2561.method_43471("text.cloth-config.list.remove");
   private boolean insertButtonEnabled = true;
   private boolean deleteButtonEnabled = true;
   private boolean insertInFront = false;

   protected AbstractListBuilder(class_2561 resetButtonKey, class_2561 fieldNameKey) {
      super(resetButtonKey, fieldNameKey);
   }

   public Function<T, Optional<class_2561>> getCellErrorSupplier() {
      return this.cellErrorSupplier;
   }

   public SELF setCellErrorSupplier(Function<T, Optional<class_2561>> cellErrorSupplier) {
      this.cellErrorSupplier = cellErrorSupplier;
      return (SELF)this;
   }

   public SELF setDeleteButtonEnabled(boolean deleteButtonEnabled) {
      this.deleteButtonEnabled = deleteButtonEnabled;
      return (SELF)this;
   }

   public SELF setInsertButtonEnabled(boolean insertButtonEnabled) {
      this.insertButtonEnabled = insertButtonEnabled;
      return (SELF)this;
   }

   public SELF setInsertInFront(boolean insertInFront) {
      this.insertInFront = insertInFront;
      return (SELF)this;
   }

   public SELF setAddButtonTooltip(class_2561 addTooltip) {
      this.addTooltip = addTooltip;
      return (SELF)this;
   }

   public SELF setRemoveButtonTooltip(class_2561 removeTooltip) {
      this.removeTooltip = removeTooltip;
      return (SELF)this;
   }

   public SELF setExpanded(boolean expanded) {
      this.expanded = expanded;
      return (SELF)this;
   }

   public boolean isExpanded() {
      return this.expanded;
   }

   public class_2561 getAddTooltip() {
      return this.addTooltip;
   }

   public class_2561 getRemoveTooltip() {
      return this.removeTooltip;
   }

   public boolean isInsertButtonEnabled() {
      return this.insertButtonEnabled;
   }

   public boolean isDeleteButtonEnabled() {
      return this.deleteButtonEnabled;
   }

   public boolean isInsertInFront() {
      return this.insertInFront;
   }
}
