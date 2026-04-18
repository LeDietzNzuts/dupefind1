package net.p3pp3rf1y.sophisticatedcore.upgrades;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.concurrent.Immutable;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.p3pp3rf1y.sophisticatedcore.util.CodecHelper;
import net.p3pp3rf1y.sophisticatedcore.util.StreamCodecHelper;
import org.jetbrains.annotations.Unmodifiable;

@Immutable
public record FilterAttributes(
   Set<class_6862<class_1792>> tagKeys,
   boolean isAllowList,
   boolean matchDurability,
   boolean matchComponents,
   PrimaryMatch primaryMatch,
   boolean matchAnyTag,
   @Unmodifiable List<class_1799> filterItems,
   boolean filterByStorage,
   boolean filterByInventory
) {
   public static final Codec<FilterAttributes> CODEC = RecordCodecBuilder.create(
      builder -> builder.group(
            CodecHelper.setOf(class_6862.method_40090(class_7924.field_41197))
               .optionalFieldOf("tag_keys", Collections.emptySet())
               .forGetter(FilterAttributes::tagKeys),
            Codec.BOOL.optionalFieldOf("is_allow_list", false).forGetter(FilterAttributes::isAllowList),
            Codec.BOOL.optionalFieldOf("match_durability", false).forGetter(FilterAttributes::matchDurability),
            Codec.BOOL.optionalFieldOf("match_components", false).forGetter(FilterAttributes::matchComponents),
            PrimaryMatch.CODEC.optionalFieldOf("primary_match", PrimaryMatch.ITEM).forGetter(FilterAttributes::primaryMatch),
            Codec.BOOL.optionalFieldOf("match_any_tag", false).forGetter(FilterAttributes::matchAnyTag),
            Codec.list(class_1799.field_49266).optionalFieldOf("filter_items", Collections.emptyList()).forGetter(FilterAttributes::filterItems),
            Codec.BOOL.optionalFieldOf("filter_by_storage", false).forGetter(FilterAttributes::filterByStorage),
            Codec.BOOL.optionalFieldOf("filter_by_inventory", false).forGetter(FilterAttributes::filterByInventory)
         )
         .apply(builder, FilterAttributes::new)
   );
   public static final class_9139<class_9129, FilterAttributes> STREAM_CODEC = StreamCodecHelper.composite(
      StreamCodecHelper.ofCollection(StreamCodecHelper.ofTagkey(class_7924.field_41197), HashSet::new),
      FilterAttributes::tagKeys,
      class_9135.field_48547,
      FilterAttributes::isAllowList,
      class_9135.field_48547,
      FilterAttributes::matchDurability,
      class_9135.field_48547,
      FilterAttributes::matchComponents,
      PrimaryMatch.STREAM_CODEC,
      FilterAttributes::primaryMatch,
      class_9135.field_48547,
      FilterAttributes::matchAnyTag,
      class_1799.field_49269,
      FilterAttributes::filterItems,
      class_9135.field_48547,
      FilterAttributes::filterByStorage,
      class_9135.field_48547,
      FilterAttributes::filterByInventory,
      FilterAttributes::new
   );

   public FilterAttributes setTagKeys(Set<class_6862<class_1792>> tagKeys) {
      return new FilterAttributes.CopyBuilder(this).setTagKeys(tagKeys).build();
   }

   public FilterAttributes setAllowList(boolean isAllowList) {
      return new FilterAttributes.CopyBuilder(this).setAllowList(isAllowList).build();
   }

   public FilterAttributes setMatchDurability(boolean matchDurability) {
      return new FilterAttributes.CopyBuilder(this).setMatchDurability(matchDurability).build();
   }

   public FilterAttributes setMatchComponents(boolean matchComponents) {
      return new FilterAttributes.CopyBuilder(this).setMatchComponents(matchComponents).build();
   }

   public FilterAttributes setPrimaryMatch(PrimaryMatch primaryMatch) {
      return new FilterAttributes.CopyBuilder(this).setPrimaryMatch(primaryMatch).build();
   }

   public FilterAttributes setMatchAnyTag(boolean matchAnyTag) {
      return new FilterAttributes.CopyBuilder(this).setMatchAnyTag(matchAnyTag).build();
   }

   public FilterAttributes setFilterItem(int slot, class_1799 filterItem) {
      return new FilterAttributes.CopyBuilder(this).setFilterItem(slot, filterItem).build();
   }

   public FilterAttributes setFilterByStorage(boolean filterByStorage) {
      return new FilterAttributes.CopyBuilder(this).setFilterByStorage(filterByStorage).build();
   }

   public FilterAttributes setFilterByInventory(boolean filterByInventory) {
      return new FilterAttributes.CopyBuilder(this).setFilterByInventory(filterByInventory).build();
   }

   public FilterAttributes expandFilterItems(int targetFilterCount) {
      return new FilterAttributes.CopyBuilder(this).expandFilterItems(targetFilterCount).build();
   }

   protected static class CopyBuilder {
      private Set<class_6862<class_1792>> tagKeys;
      private boolean isAllowList;
      private boolean matchDurability;
      private boolean matchComponents;
      private PrimaryMatch primaryMatch;
      private boolean matchAnyTag;
      private List<class_1799> filterItems;
      private boolean filterByStorage;
      private boolean filterByInventory;

      public CopyBuilder(FilterAttributes original) {
         this.tagKeys = original.tagKeys();
         this.isAllowList = original.isAllowList();
         this.matchDurability = original.matchDurability();
         this.matchComponents = original.matchComponents();
         this.primaryMatch = original.primaryMatch();
         this.matchAnyTag = original.matchAnyTag();
         this.filterItems = new ArrayList<>(original.filterItems());
         this.filterByStorage = original.filterByStorage();
         this.filterByInventory = original.filterByInventory();
      }

      public FilterAttributes.CopyBuilder setTagKeys(Set<class_6862<class_1792>> tagKeys) {
         this.tagKeys = tagKeys;
         return this;
      }

      public FilterAttributes build() {
         return new FilterAttributes(
            this.tagKeys,
            this.isAllowList,
            this.matchDurability,
            this.matchComponents,
            this.primaryMatch,
            this.matchAnyTag,
            Collections.unmodifiableList(this.filterItems),
            this.filterByStorage,
            this.filterByInventory
         );
      }

      public FilterAttributes.CopyBuilder setAllowList(boolean isAllowList) {
         this.isAllowList = isAllowList;
         return this;
      }

      public FilterAttributes.CopyBuilder setMatchDurability(boolean matchDurability) {
         this.matchDurability = matchDurability;
         return this;
      }

      public FilterAttributes.CopyBuilder setMatchComponents(boolean matchComponents) {
         this.matchComponents = matchComponents;
         return this;
      }

      public FilterAttributes.CopyBuilder setPrimaryMatch(PrimaryMatch primaryMatch) {
         this.primaryMatch = primaryMatch;
         return this;
      }

      public FilterAttributes.CopyBuilder setMatchAnyTag(boolean matchAnyTag) {
         this.matchAnyTag = matchAnyTag;
         return this;
      }

      public FilterAttributes.CopyBuilder setFilterItem(int slot, class_1799 filterItem) {
         this.filterItems.set(slot, filterItem.method_7972());
         return this;
      }

      public FilterAttributes.CopyBuilder setFilterByStorage(boolean filterByStorage) {
         this.filterByStorage = filterByStorage;
         return this;
      }

      public FilterAttributes.CopyBuilder setFilterByInventory(boolean filterByInventory) {
         this.filterByInventory = filterByInventory;
         return this;
      }

      public FilterAttributes.CopyBuilder expandFilterItems(int targetFilterCount) {
         class_2371<class_1799> targetFilterItems = class_2371.method_10213(targetFilterCount, class_1799.field_8037);

         for (int slot = 0; slot < this.filterItems.size() && slot < targetFilterCount; slot++) {
            targetFilterItems.set(slot, this.filterItems.get(slot));
         }

         this.filterItems = targetFilterItems;
         return this;
      }
   }
}
