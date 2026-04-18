package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.settings.main.MainSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.nosort.NoSortSettingsCategory;

public abstract class SettingsHandler {
   protected class_2487 contentsNbt;
   protected final Runnable markContentsDirty;
   protected final Map<String, ISettingsCategory<?>> settingsCategories = new LinkedHashMap<>();
   private final Map<Class<?>, List<?>> interfaceCategories = new HashMap<>();
   private final Map<Class<? extends ISettingsCategory<?>>, ISettingsCategory<?>> typeCategories = new HashMap<>();

   protected SettingsHandler(
      class_2487 contentsNbt, Runnable markContentsDirty, Supplier<InventoryHandler> inventoryHandlerSupplier, Supplier<RenderInfo> renderInfoSupplier
   ) {
      this.contentsNbt = contentsNbt;
      this.markContentsDirty = markContentsDirty;
      this.addSettingsCategories(inventoryHandlerSupplier, renderInfoSupplier, this.getSettingsNbtFromContentsNbt(contentsNbt));
   }

   protected abstract class_2487 getSettingsNbtFromContentsNbt(class_2487 var1);

   private void addSettingsCategories(Supplier<InventoryHandler> inventoryHandlerSupplier, Supplier<RenderInfo> renderInfoSupplier, class_2487 settingsNbt) {
      this.addSettingsCategory(settingsNbt, this.getGlobalSettingsCategoryName(), this.markContentsDirty, this::instantiateGlobalSettingsCategory);
      this.addSettingsCategory(settingsNbt, "no_sort", this.markContentsDirty, NoSortSettingsCategory::new);
      this.addSettingsCategory(
         settingsNbt, "memory", this.markContentsDirty, (categoryNbt, saveNbt) -> new MemorySettingsCategory(inventoryHandlerSupplier, categoryNbt, saveNbt)
      );
      this.addItemDisplayCategory(inventoryHandlerSupplier, renderInfoSupplier, settingsNbt);
   }

   protected abstract void addItemDisplayCategory(Supplier<InventoryHandler> var1, Supplier<RenderInfo> var2, class_2487 var3);

   public abstract String getGlobalSettingsCategoryName();

   public abstract ISettingsCategory<?> instantiateGlobalSettingsCategory(class_2487 var1, Consumer<class_2487> var2);

   public MainSettingsCategory<?> getGlobalSettingsCategory() {
      return this.getTypeCategory(MainSettingsCategory.class);
   }

   protected void addSettingsCategory(
      class_2487 settingsNbt,
      String categoryName,
      Runnable markContentsDirty,
      BiFunction<class_2487, Consumer<class_2487>, ISettingsCategory<?>> instantiateCategory
   ) {
      ISettingsCategory<?> category = instantiateCategory.apply(settingsNbt.method_10562(categoryName), tag -> {
         this.saveCategoryNbt(settingsNbt, categoryName, tag);
         markContentsDirty.run();
      });
      this.settingsCategories.put(categoryName, category);
      this.addTypeCategory(category);
   }

   private <T extends ISettingsCategory<T>> void addTypeCategory(ISettingsCategory<?> category) {
      this.typeCategories.put((Class<? extends ISettingsCategory<?>>)category.getClass(), category);
   }

   protected abstract void saveCategoryNbt(class_2487 var1, String var2, class_2487 var3);

   public Map<String, ISettingsCategory<?>> getSettingsCategories() {
      return this.settingsCategories;
   }

   public <T> List<T> getCategoriesThatImplement(Class<T> categoryClass) {
      return (List<T>)this.interfaceCategories.computeIfAbsent(categoryClass, this::getListOfWrappersThatImplement);
   }

   public <T extends ISettingsCategory<?>> T getTypeCategory(Class<T> categoryClazz) {
      return (T)this.typeCategories.get(categoryClazz);
   }

   private <T> List<T> getListOfWrappersThatImplement(Class<T> uc) {
      List<T> ret = new ArrayList<>();

      for (ISettingsCategory<?> category : this.settingsCategories.values()) {
         if (uc.isInstance(category)) {
            ret.add((T)category);
         }
      }

      return ret;
   }

   public class_2487 getNbt() {
      return this.getSettingsNbtFromContentsNbt(this.contentsNbt);
   }

   public void reloadFrom(class_2487 contentsNbt) {
      class_2487 settingsNbt = this.getSettingsNbtFromContentsNbt(contentsNbt);
      this.getSettingsCategories().forEach((categoryName, category) -> category.reloadFrom(settingsNbt.method_10562(categoryName)));
   }
}
