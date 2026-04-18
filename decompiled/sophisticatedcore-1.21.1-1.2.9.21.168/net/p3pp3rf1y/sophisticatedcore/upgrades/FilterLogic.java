package net.p3pp3rf1y.sophisticatedcore.upgrades;

import io.github.fabricators_of_create.porting_lib.util.DeferredHolder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.minecraft.class_9331;
import net.p3pp3rf1y.sophisticatedcore.util.FilterItemStackHandler;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;
import net.p3pp3rf1y.sophisticatedcore.util.ItemStackHelper;

public class FilterLogic {
   protected final class_1799 upgrade;
   protected final Consumer<class_1799> saveHandler;
   protected final DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> filterAttributesComponent;
   private final int defaultFilterSlotCount;
   private final Predicate<class_1799> isItemValid;
   @Nullable
   protected Set<class_6862<class_1792>> tagKeys = null;
   private FilterLogic.ObservableFilterItemStackHandler filterHandler = null;
   private boolean emptyAllowListMatchesEverything = false;
   private boolean allowListDefault = false;
   @Nullable
   private FilterAttributes emptyAttributes = null;

   public FilterLogic(
      class_1799 upgrade,
      Consumer<class_1799> saveHandler,
      int defaultFilterSlotCount,
      DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> filterAttributesComponent
   ) {
      this(upgrade, saveHandler, defaultFilterSlotCount, s -> true, filterAttributesComponent);
   }

   public FilterLogic(
      class_1799 upgrade,
      Consumer<class_1799> saveHandler,
      int defaultFilterSlotCount,
      Predicate<class_1799> isItemValid,
      DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> filterAttributesComponent
   ) {
      this.upgrade = upgrade;
      this.saveHandler = saveHandler;
      this.filterAttributesComponent = filterAttributesComponent;
      this.defaultFilterSlotCount = defaultFilterSlotCount;
      this.isItemValid = isItemValid;
   }

   public void setEmptyAllowListMatchesEverything() {
      this.emptyAllowListMatchesEverything = true;
   }

   public FilterLogic.ObservableFilterItemStackHandler getFilterHandler() {
      if (this.filterHandler == null) {
         int filterSlotCount = this.getAttributes().filterItems().size();
         this.filterHandler = new FilterLogic.ObservableFilterItemStackHandler(filterSlotCount);
         this.filterHandler.initFilters(this.getAttributes().filterItems());
         if (this.getAttributes().filterItems().size() < filterSlotCount) {
            this.setAttributes(contents -> contents.expandFilterItems(filterSlotCount));
         }
      }

      return this.filterHandler;
   }

   public boolean matchesFilter(class_1799 stack) {
      if (this.isAllowList()) {
         return this.getPrimaryMatch() == PrimaryMatch.TAGS
            ? this.isTagMatch(stack)
            : this.getFilterHandler().hasOnlyEmptyFilters() && this.emptyAllowListMatchesEverything
               || InventoryHelper.iterate(
                  this.getFilterHandler(), (slot, filter) -> this.stackMatchesFilter(stack, filter), () -> false, returnValue -> returnValue
               );
      } else {
         return this.getPrimaryMatch() == PrimaryMatch.TAGS
            ? !this.isTagMatch(stack)
            : this.getFilterHandler().hasOnlyEmptyFilters()
               || InventoryHelper.iterate(
                  this.getFilterHandler(), (slot, filter) -> !this.stackMatchesFilter(stack, filter), () -> true, returnValue -> !returnValue
               );
      }
   }

   private boolean isTagMatch(class_1799 stack) {
      return this.shouldMatchAnyTag() ? this.anyTagMatches(stack.method_40133()) : this.allTagsMatch(stack.method_40133());
   }

   private boolean allTagsMatch(Stream<class_6862<class_1792>> tagsStream) {
      if (this.tagKeys == null) {
         this.initTags();
      }

      Set<class_6862<class_1792>> tags = tagsStream.collect(Collectors.toSet());

      for (class_6862<class_1792> tagName : this.tagKeys) {
         if (!tags.contains(tagName)) {
            return false;
         }
      }

      return true;
   }

   private boolean anyTagMatches(Stream<class_6862<class_1792>> tags) {
      if (this.tagKeys == null) {
         this.initTags();
      }

      return tags.anyMatch(t -> this.tagKeys.contains(t));
   }

   protected FilterAttributes getAttributes() {
      return (FilterAttributes)this.upgrade.sophisticatedCore_getOrDefault(this.filterAttributesComponent, this.getEmptyAttributes());
   }

   private FilterAttributes getEmptyAttributes() {
      if (this.emptyAttributes == null) {
         this.emptyAttributes = new FilterAttributes(
            Collections.emptySet(),
            this.allowListDefault,
            false,
            false,
            PrimaryMatch.ITEM,
            true,
            class_2371.method_10213(this.defaultFilterSlotCount, class_1799.field_8037),
            false,
            false
         );
      }

      return this.emptyAttributes;
   }

   protected void setAttributes(Function<FilterAttributes, FilterAttributes> setter) {
      this.upgrade.sophisticatedCore_set(this.filterAttributesComponent, setter.apply(this.getAttributes()));
   }

   public void setAllowByDefault(boolean allowListDefault) {
      this.allowListDefault = allowListDefault;
   }

   protected void save() {
      this.saveHandler.accept(this.upgrade);
   }

   public boolean stackMatchesFilter(class_1799 stack, class_1799 filter) {
      if (filter.method_7960()) {
         return false;
      } else {
         PrimaryMatch primaryMatch = this.getPrimaryMatch();
         if (primaryMatch == PrimaryMatch.MOD) {
            if (!class_7923.field_41178
               .method_10221(stack.method_7909())
               .method_12836()
               .equals(class_7923.field_41178.method_10221(filter.method_7909()).method_12836())) {
               return false;
            }
         } else if (primaryMatch == PrimaryMatch.ITEM && stack.method_7909() != filter.method_7909()) {
            return false;
         }

         return this.shouldMatchDurability() && stack.method_7919() != filter.method_7919()
            ? false
            : !this.shouldMatchComponents() || ItemStackHelper.areItemStackComponentsEqualIgnoreDurability(stack, filter);
      }
   }

   public Set<class_6862<class_1792>> getTagKeys() {
      if (this.tagKeys == null) {
         this.initTags();
      }

      return Collections.unmodifiableSet(this.tagKeys);
   }

   public void addTag(class_6862<class_1792> tagName) {
      if (this.tagKeys == null) {
         this.initTags();
      }

      this.tagKeys.add(tagName);
      this.serializeTags();
      this.save();
   }

   private void serializeTags() {
      if (this.tagKeys != null) {
         this.setAttributes(contents -> contents.setTagKeys(this.tagKeys));
      }
   }

   public void removeTagName(class_6862<class_1792> tagName) {
      if (this.tagKeys == null) {
         this.initTags();
      }

      this.tagKeys.remove(tagName);
      this.serializeTags();
      this.save();
   }

   protected void initTags() {
      this.tagKeys = new TreeSet<>(Comparator.comparing(class_6862::comp_327));
      this.tagKeys.addAll(this.getAttributes().tagKeys());
   }

   public void setAllowList(boolean isAllowList) {
      this.setAttributes(contents -> contents.setAllowList(isAllowList));
      this.save();
   }

   public boolean isAllowList() {
      return this.getAttributes().isAllowList();
   }

   public boolean shouldMatchDurability() {
      return this.getAttributes().matchDurability();
   }

   public void setMatchDurability(boolean matchDurability) {
      this.setAttributes(contents -> contents.setMatchDurability(matchDurability));
      this.save();
   }

   public void setMatchComponents(boolean matchComponents) {
      this.setAttributes(contents -> contents.setMatchComponents(matchComponents));
      this.save();
   }

   public boolean shouldMatchComponents() {
      return this.getAttributes().matchComponents();
   }

   public void setPrimaryMatch(PrimaryMatch primaryMatch) {
      this.setAttributes(contents -> contents.setPrimaryMatch(primaryMatch));
      this.save();
   }

   public PrimaryMatch getPrimaryMatch() {
      return this.getAttributes().primaryMatch();
   }

   public boolean shouldMatchAnyTag() {
      return this.getAttributes().matchAnyTag();
   }

   public void setMatchAnyTag(boolean matchAnyTag) {
      this.setAttributes(contents -> contents.setMatchAnyTag(matchAnyTag));
      this.save();
   }

   public DeferredHolder<class_9331<?>, class_9331<FilterAttributes>> getAttributesComponent() {
      return this.filterAttributesComponent;
   }

   public class ObservableFilterItemStackHandler extends FilterItemStackHandler {
      private IntConsumer onSlotChange = s -> {};

      public ObservableFilterItemStackHandler(int filterSlotCount) {
         super(filterSlotCount);
      }

      @Override
      protected void onContentsChanged(int slot) {
         super.onContentsChanged(slot);
         FilterLogic.this.setAttributes(contents -> contents.setFilterItem(slot, this.getStackInSlot(slot)));
         FilterLogic.this.save();
         this.onSlotChange.accept(slot);
      }

      public void setOnSlotChange(IntConsumer onSlotChange) {
         this.onSlotChange = onSlotChange;
      }

      @Override
      public boolean isItemValid(int slot, class_1799 stack) {
         return stack.method_7960() || this.doesNotContain(stack) && FilterLogic.this.isItemValid.test(stack);
      }

      private boolean doesNotContain(class_1799 stack) {
         return !InventoryHelper.hasItem(this, s -> class_1799.method_31577(s, stack));
      }

      public void initFilters(List<class_1799> filterItems) {
         for (int slot = 0; slot < filterItems.size(); slot++) {
            this.setStackInSlot(slot, filterItems.get(slot).method_7972());
         }

         this.onLoad();
      }
   }
}
