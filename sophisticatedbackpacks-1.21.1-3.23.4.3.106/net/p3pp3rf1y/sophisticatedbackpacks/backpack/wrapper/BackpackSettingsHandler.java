package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedbackpacks.settings.BackpackMainSettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsHandler;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;

public class BackpackSettingsHandler extends SettingsHandler {
   public static final String SETTINGS_TAG = "settings";

   public BackpackSettingsHandler(IStorageWrapper backpackWrapper, class_2487 backpackContentsNbt, Runnable markBackpackContentsDirty) {
      super(backpackContentsNbt, markBackpackContentsDirty, backpackWrapper::getInventoryHandler, backpackWrapper::getRenderInfo);
   }

   public void copyTo(SettingsHandler settingsHandler) {
      if (this.contentsNbt.method_10545("settings")) {
         settingsHandler.getNbt().method_10566("settings", this.contentsNbt.method_10580("settings"));
      }
   }

   protected class_2487 getSettingsNbtFromContentsNbt(class_2487 contentsNbt) {
      return contentsNbt.method_10562("settings");
   }

   protected void addItemDisplayCategory(Supplier<InventoryHandler> inventoryHandlerSupplier, Supplier<RenderInfo> renderInfoSupplier, class_2487 settingsNbt) {
      this.addSettingsCategory(
         settingsNbt,
         "item_display",
         this.markContentsDirty,
         (categoryNbt, saveNbt) -> new ItemDisplaySettingsCategory(
            inventoryHandlerSupplier,
            renderInfoSupplier,
            categoryNbt,
            saveNbt,
            1,
            () -> (MemorySettingsCategory)this.getTypeCategory(MemorySettingsCategory.class)
         )
      );
   }

   protected void saveCategoryNbt(class_2487 settingsNbt, String categoryName, class_2487 tag) {
      settingsNbt.method_10566(categoryName, tag);
      this.contentsNbt.method_10566("settings", settingsNbt);
   }

   public String getGlobalSettingsCategoryName() {
      return "backpackGlobal";
   }

   public ISettingsCategory<?> instantiateGlobalSettingsCategory(class_2487 categoryNbt, Consumer<class_2487> saveNbt) {
      return new BackpackMainSettingsCategory(categoryNbt, saveNbt);
   }

   public BackpackMainSettingsCategory getGlobalSettingsCategory() {
      return (BackpackMainSettingsCategory)this.getTypeCategory(BackpackMainSettingsCategory.class);
   }
}
