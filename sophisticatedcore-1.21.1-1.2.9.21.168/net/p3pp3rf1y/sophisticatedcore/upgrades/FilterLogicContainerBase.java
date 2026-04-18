package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1735;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7924;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IFilterSlot;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class FilterLogicContainerBase<T extends FilterLogic, S extends class_1735> {
   private static final String DATA_IS_ALLOW_LIST = "isAllowList";
   private static final String DATA_MATCH_DURABILITY = "matchDurability";
   private static final String DATA_MATCH_NBT = "matchNbt";
   private static final String DATA_PRIMARY_MATCH = "primaryMatch";
   private static final String DATA_ADD_TAG_NAME = "addTagName";
   private static final String DATA_REMOVE_TAG_NAME = "removeTagName";
   private static final String DATA_MATCH_ANY_TAG = "matchAnyTag";
   private static final String DATA_COMPONENT_KEY = "parentTagKey";
   protected final List<S> filterSlots = new ArrayList<>();
   protected final IServerUpdater serverUpdater;
   protected final Supplier<T> filterLogic;
   private final FilterLogicContainerBase<T, S>.TagSelectionSlot tagSelectionSlot;
   private int selectedTagToAdd = 0;
   private int selectedTagToRemove = 0;
   private final Set<class_6862<class_1792>> tagsToAdd = new TreeSet<>(Comparator.comparing(class_6862::comp_327));

   public FilterLogicContainerBase(IServerUpdater serverUpdater, Supplier<T> filterLogic, Consumer<class_1735> addSlot) {
      this.serverUpdater = serverUpdater;
      this.filterLogic = filterLogic;
      this.tagSelectionSlot = new FilterLogicContainerBase.TagSelectionSlot();
      addSlot.accept(this.tagSelectionSlot);
   }

   public int getSelectedTagToAdd() {
      return this.selectedTagToAdd;
   }

   public int getSelectedTagToRemove() {
      return this.selectedTagToRemove;
   }

   public FilterLogicContainerBase<T, S>.TagSelectionSlot getTagSelectionSlot() {
      return this.tagSelectionSlot;
   }

   public List<S> getFilterSlots() {
      return this.filterSlots;
   }

   public Set<class_6862<class_1792>> getTagNames() {
      return this.filterLogic.get().getTagKeys();
   }

   public Set<class_6862<class_1792>> getTagsToAdd() {
      return this.tagsToAdd;
   }

   public void addSelectedTag() {
      this.getTagAtIndex(this.tagsToAdd, this.selectedTagToAdd).ifPresent(tagName -> {
         this.addTagName((class_6862<class_1792>)tagName);
         this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "addTagName", tagName.comp_327().toString()));
         this.selectedTagToRemove = 0;
         this.tagsToAdd.remove(tagName);
         this.selectedTagToAdd = Math.max(0, this.selectedTagToAdd - 1);
      });
   }

   private void addTagName(class_6862<class_1792> tagName) {
      this.filterLogic.get().addTag(tagName);
   }

   public void removeSelectedTag() {
      this.getTagAtIndex(this.getTagNames(), this.selectedTagToRemove).ifPresent(tagName -> {
         this.removeSelectedTag((class_6862<class_1792>)tagName);
         this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "removeTagName", tagName.comp_327().toString()));
         if (this.tagSelectionSlot.method_7677().method_31573(tagName)) {
            this.tagsToAdd.add((class_6862<class_1792>)tagName);
         }

         this.selectedTagToRemove = Math.max(0, this.selectedTagToRemove - 1);
      });
   }

   private void removeSelectedTag(class_6862<class_1792> tagName) {
      this.filterLogic.get().removeTagName(tagName);
   }

   public void selectNextTagToRemove() {
      this.selectedTagToRemove = this.getNextIndex(this.getTagNames().size(), this.selectedTagToRemove);
   }

   private int getNextIndex(int colSize, int selectedIndex) {
      return selectedIndex + 1 >= colSize ? 0 : selectedIndex + 1;
   }

   private int getPreviousIndex(int colSize, int selectedIndex) {
      return selectedIndex == 0 ? colSize - 1 : selectedIndex - 1;
   }

   public void selectPreviousTagToRemove() {
      this.selectedTagToRemove = this.getPreviousIndex(this.getTagNames().size(), this.selectedTagToRemove);
   }

   public void selectNextTagToAdd() {
      this.selectedTagToAdd = this.getNextIndex(this.tagsToAdd.size(), this.selectedTagToAdd);
   }

   public void selectPreviousTagToAdd() {
      this.selectedTagToAdd = this.getPreviousIndex(this.tagsToAdd.size(), this.selectedTagToAdd);
   }

   private Optional<class_6862<class_1792>> getTagAtIndex(Set<class_6862<class_1792>> col, int index) {
      int curIndex = 0;

      for (class_6862<class_1792> tagName : col) {
         if (curIndex == index) {
            return Optional.of(tagName);
         }

         curIndex++;
      }

      return Optional.empty();
   }

   public boolean isAllowList() {
      return this.filterLogic.get().isAllowList();
   }

   public boolean shouldMatchDurability() {
      return this.filterLogic.get().shouldMatchDurability();
   }

   public boolean shouldMatchNbt() {
      return this.filterLogic.get().shouldMatchComponents();
   }

   public PrimaryMatch getPrimaryMatch() {
      return this.filterLogic.get().getPrimaryMatch();
   }

   public boolean shouldMatchAnyTag() {
      return this.filterLogic.get().shouldMatchAnyTag();
   }

   public void setAllowList(boolean isAllowList) {
      this.filterLogic.get().setAllowList(isAllowList);
      this.sendBooleanToServer("isAllowList", isAllowList);
   }

   private void sendBooleanToServer(String dataId, boolean value) {
      this.serverUpdater.sendDataToServer(() -> {
         class_2487 tag = new class_2487();
         tag.method_10556(dataId, value);
         tag.method_10582("parentTagKey", this.filterLogic.get().getAttributesComponent().getKey().method_29177().toString());
         return tag;
      });
   }

   protected void sendDataToServer(Supplier<class_2487> dataSupplier) {
      this.serverUpdater.sendDataToServer(() -> {
         class_2487 tag = dataSupplier.get();
         tag.method_10582("parentTagKey", this.filterLogic.get().getAttributesComponent().getKey().method_29177().toString());
         return tag;
      });
   }

   public void setMatchDurability(boolean matchDurability) {
      this.filterLogic.get().setMatchDurability(matchDurability);
      this.sendBooleanToServer("matchDurability", matchDurability);
   }

   public void setMatchNbt(boolean matchNbt) {
      this.filterLogic.get().setMatchComponents(matchNbt);
      this.sendBooleanToServer("matchNbt", matchNbt);
   }

   public void setPrimaryMatch(PrimaryMatch primaryMatch) {
      this.filterLogic.get().setPrimaryMatch(primaryMatch);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "primaryMatch", primaryMatch));
   }

   public void setMatchAnyTag(boolean matchAnyTag) {
      this.filterLogic.get().setMatchAnyTag(matchAnyTag);
      this.sendBooleanToServer("matchAnyTag", matchAnyTag);
   }

   public boolean handlePacket(class_2487 data) {
      if (this.isDifferentFilterLogicsData(data)) {
         return false;
      } else {
         for (String key : data.method_10541()) {
            switch (key) {
               case "isAllowList":
                  this.setAllowList(data.method_10577("isAllowList"));
                  return true;
               case "matchDurability":
                  this.setMatchDurability(data.method_10577("matchDurability"));
                  return true;
               case "matchNbt":
                  this.setMatchNbt(data.method_10577("matchNbt"));
                  return true;
               case "primaryMatch":
                  this.setPrimaryMatch(PrimaryMatch.fromName(data.method_10558("primaryMatch")));
                  return true;
               case "addTagName":
                  this.addTagName(class_6862.method_40092(class_7924.field_41197, class_2960.method_60654(data.method_10558("addTagName"))));
                  return true;
               case "removeTagName":
                  this.removeSelectedTag(class_6862.method_40092(class_7924.field_41197, class_2960.method_60654(data.method_10558("removeTagName"))));
                  return true;
               case "matchAnyTag":
                  this.setMatchAnyTag(data.method_10577("matchAnyTag"));
                  return true;
            }
         }

         return false;
      }
   }

   protected boolean isDifferentFilterLogicsData(class_2487 data) {
      return data.method_10545("parentTagKey")
         && !this.filterLogic.get().getAttributesComponent().getKey().method_29177().toString().equals(data.method_10558("parentTagKey"));
   }

   public class TagSelectionSlot extends class_1735 implements IFilterSlot {
      private class_1799 stack = class_1799.field_8037;
      private Runnable onUpdate = () -> {};

      public TagSelectionSlot() {
         super(new class_1277(0), 0, -1, -1);
      }

      public void setOnUpdate(Runnable onUpdate) {
         this.onUpdate = onUpdate;
      }

      public boolean method_7680(class_1799 stack) {
         return stack.method_7960() || stack.method_40133().findAny().isPresent();
      }

      public boolean method_7674(class_1657 player) {
         return false;
      }

      public class_1799 method_7677() {
         return this.stack;
      }

      public int method_7675() {
         return 1;
      }

      public class_1799 method_7671(int amount) {
         this.stack = class_1799.field_8037;
         return this.stack;
      }

      public boolean sophisticatedCore_isSameInventory(class_1735 other) {
         return false;
      }

      public void method_7673(class_1799 stack) {
         this.stack = stack;
         FilterLogicContainerBase.this.tagsToAdd.clear();
         FilterLogicContainerBase.this.tagsToAdd.addAll(stack.method_40133().toList());
         FilterLogicContainerBase.this.getTagNames().forEach(FilterLogicContainerBase.this.tagsToAdd::remove);
         FilterLogicContainerBase.this.selectedTagToAdd = 0;
         this.onUpdate.run();
      }

      public void method_7668() {
      }
   }
}
