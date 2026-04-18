package net.p3pp3rf1y.sophisticatedcore.common.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraft.class_124;
import net.minecraft.class_1657;
import net.minecraft.class_2461;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5218;
import net.minecraft.class_5250;
import net.minecraft.class_5455;
import net.minecraft.class_5625;
import net.minecraft.class_7403;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.inventory.InventoryHandler;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.SyncDatapackSettingsTemplatePayload;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;
import net.p3pp3rf1y.sophisticatedcore.settings.DatapackSettingsTemplateManager;
import net.p3pp3rf1y.sophisticatedcore.settings.ISettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsHandler;
import net.p3pp3rf1y.sophisticatedcore.settings.SettingsTemplateStorage;
import net.p3pp3rf1y.sophisticatedcore.settings.itemdisplay.ItemDisplaySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.settings.memory.MemorySettingsCategory;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;
import net.p3pp3rf1y.sophisticatedcore.util.NoopStorageWrapper;

public class TemplatePersistanceContainer {
   private static final Pattern EXPORT_FILE_NAME_PATTERN = Pattern.compile("[A-Za-z0-9/._\\-\\s]+");
   private static final int TOTAL_ORDINAL_SAVE_SLOTS = 10;
   private static final String ACTION_TAG = "action";
   private static final String SAVE_SLOT_TAG = "saveSlot";
   private static final String LOAD_SLOT_TAG = "loadSlot";
   static final String TEMPLATE_PERSISTANCE_TAG = "templatePersistance";
   private final SettingsContainerMenu<?> settingsContainer;
   private final class_5455 registryAccess;
   private final List<TemplatePersistanceContainer.IPersistanceSlot> saveSlots = new ArrayList<>();
   private int saveSlotIndex = 0;
   private final List<TemplatePersistanceContainer.IPersistanceSlot> loadSlots = new ArrayList<>();
   private int loadSlotIndex = -1;
   @Nullable
   private TemplatePersistanceContainer.TemplateSettingsHandler selectedTemplate;
   private Runnable onSlotsRefreshed = () -> {};

   public TemplatePersistanceContainer(SettingsContainerMenu<?> settingsContainer, class_5455 registryAccess) {
      this.settingsContainer = settingsContainer;
      this.registryAccess = registryAccess;
      this.initSlots();
   }

   public void setOnSlotsRefreshed(Runnable onSlotsRefreshed) {
      this.onSlotsRefreshed = onSlotsRefreshed;
   }

   private void initSlots() {
      this.saveSlots.clear();

      int i;
      for (i = 0; i < this.getNumberOfSaves(); i++) {
         this.saveSlots.add(new TemplatePersistanceContainer.OrdinalPersistanceSlot(i + 1));
      }

      if (i < 9) {
         this.saveSlots.add(new TemplatePersistanceContainer.OrdinalPersistanceSlot(i + 1));
      }

      this.getNamedSaves().forEach(name -> this.saveSlots.add(new TemplatePersistanceContainer.NamedPersistanceSlot(name)));
      this.saveSlots.add(new TemplatePersistanceContainer.EditNamePersistanceSlot(""));
      this.loadSlots.clear();

      for (int var2 = 0; var2 < this.getNumberOfSaves(); var2++) {
         this.loadSlots.add(new TemplatePersistanceContainer.OrdinalPersistanceSlot(var2 + 1));
      }

      this.getNamedSaves().forEach(name -> this.loadSlots.add(new TemplatePersistanceContainer.NamedPersistanceSlot(name)));
      DatapackSettingsTemplateManager.getTemplates()
         .forEach(
            (datapackName, templates) -> templates.forEach(
               (templateName, templateNbt) -> this.loadSlots.add(new TemplatePersistanceContainer.DatapackSlot(datapackName, templateName))
            )
         );
      if (this.loadSlotIndex == -1 && !this.loadSlots.isEmpty()) {
         this.loadSlotIndex = 0;
      } else if (this.loadSlotIndex != -1 && this.loadSlots.isEmpty()) {
         this.loadSlotIndex = -1;
      }

      this.updateSelectedTemplate();
      this.onSlotsRefreshed.run();
   }

   public void handlePacket(class_2487 data) {
      if (data.method_10545("action")) {
         String action = data.method_10558("action");
         switch (action) {
            case "saveTemplate":
               this.saveTemplate(data.method_10558("slotName"));
               break;
            case "loadTemplate":
               this.loadTemplate();
               break;
            case "exportTemplate":
               this.exportTemplate(data.method_10558("fileName"));
         }
      }

      if (data.method_10545("saveSlot")) {
         this.scrollSaveSlot(data.method_10577("saveSlot"));
      } else if (data.method_10545("loadSlot")) {
         this.scrollLoadSlot(data.method_10577("loadSlot"));
      }
   }

   private void sendDataToServer(Supplier<class_2487> compoundSupplier) {
      this.settingsContainer.sendDataToServer(() -> {
         class_2487 compound = new class_2487();
         compound.method_10566("templatePersistance", (class_2520)compoundSupplier.get());
         return compound;
      });
   }

   private class_1657 getPlayer() {
      return this.settingsContainer.getPlayer();
   }

   public void loadTemplate() {
      if (this.selectedTemplate != null) {
         this.settingsContainer
            .getStorageWrapper()
            .getSettingsHandler()
            .getSettingsCategories()
            .values()
            .forEach(
               category -> this.overwriteCategory(
                  category.getClass(), (ISettingsCategory<?>)category, this.selectedTemplate.getTypeCategory((Class<ISettingsCategory<?>>)category.getClass())
               )
            );
         this.sendDataToServer(() -> NBTHelper.putString(new class_2487(), "action", "loadTemplate"));
         if (this.getPlayer().method_37908().method_8608()) {
            this.getPlayer()
               .method_7353(
                  class_2561.method_43469(
                     TranslationHelper.INSTANCE.translSettingsMessage("load_template"), new Object[]{this.loadSlots.get(this.loadSlotIndex).getSlotName()}
                  ),
                  false
               );
         }
      }
   }

   private <T extends ISettingsCategory<T>> void overwriteCategory(
      Class<T> categoryClazz, ISettingsCategory<?> currentCategory, ISettingsCategory<?> otherCategory
   ) {
      ((ISettingsCategory<ISettingsCategory<?>>)currentCategory).overwriteWith(otherCategory);
   }

   public void saveTemplate(String slotName) {
      SettingsTemplateStorage settingsTemplateStorage = SettingsTemplateStorage.get();
      TemplatePersistanceContainer.IPersistanceSlot saveSlot = this.saveSlots.get(this.saveSlotIndex);
      saveSlot.setSlotName(slotName);
      saveSlot.persistTo(this.getPlayer(), settingsTemplateStorage, this.settingsContainer.getStorageWrapper().getSettingsHandler().getNbt().method_10553());
      this.sendDataToServer(() -> NBTHelper.putString(NBTHelper.putString(new class_2487(), "action", "saveTemplate"), "slotName", slotName));
      this.initSlots();
      this.moveSaveSlotIndexTo(saveSlot.getSlotName());
      if (this.getPlayer().method_37908().method_8608()) {
         this.getPlayer()
            .method_7353(
               class_2561.method_43469(TranslationHelper.INSTANCE.translSettingsMessage("save_template"), new Object[]{saveSlot.getSlotName()}), false
            );
      }
   }

   private void moveSaveSlotIndexTo(String slotName) {
      for (int i = 0; i < this.saveSlots.size(); i++) {
         if (this.saveSlots.get(i).getSlotName().equals(slotName)) {
            this.saveSlotIndex = i;
            break;
         }
      }
   }

   public void scrollSaveSlot(boolean next) {
      this.saveSlotIndex += next ? 1 : -1;
      if (this.saveSlotIndex < 0) {
         this.saveSlotIndex = this.saveSlots.size() - 1;
      }

      if (this.saveSlotIndex >= this.saveSlots.size()) {
         this.saveSlotIndex = 0;
      }

      this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "saveSlot", next));
   }

   public void scrollLoadSlot(boolean next) {
      if (this.loadSlots.isEmpty()) {
         this.loadSlotIndex = -1;
      } else {
         this.loadSlotIndex += next ? 1 : -1;
         if (this.loadSlotIndex < 0) {
            this.loadSlotIndex = this.loadSlots.size() - 1;
         }

         if (this.loadSlotIndex >= this.loadSlots.size()) {
            this.loadSlotIndex = 0;
         }

         this.updateSelectedTemplate();
         this.sendDataToServer(() -> NBTHelper.putBoolean(new class_2487(), "loadSlot", next));
      }
   }

   private void updateSelectedTemplate() {
      if (this.loadSlotIndex > -1 && this.loadSlotIndex < this.loadSlots.size()) {
         class_2487 settingsTag = this.loadSlots.get(this.loadSlotIndex).getSettingsNbt(this.getPlayer(), SettingsTemplateStorage.get());
         this.selectedTemplate = new TemplatePersistanceContainer.TemplateSettingsHandler(settingsTag, this.registryAccess) {
            @Override
            protected SettingsHandler getCurrentSettingsHandler() {
               return TemplatePersistanceContainer.this.settingsContainer.getStorageWrapper().getSettingsHandler();
            }
         };
      }
   }

   public class_5250 getSaveSlotTooltipName() {
      return this.saveSlots.get(this.saveSlotIndex).getSlotTooltipName();
   }

   public class_5250 getLoadSlotTooltipName() {
      return this.loadSlots.get(this.loadSlotIndex).getSlotTooltipName();
   }

   public int getLoadSlot() {
      return this.loadSlotIndex;
   }

   private int getNumberOfSaves() {
      return SettingsTemplateStorage.get().getPlayerTemplates(this.settingsContainer.getPlayer()).size();
   }

   private List<String> getNamedSaves() {
      return new ArrayList<>(SettingsTemplateStorage.get().getPlayerNamedTemplates(this.settingsContainer.getPlayer()).keySet());
   }

   public Optional<TemplatePersistanceContainer.TemplateSettingsHandler> getSelectedTemplate() {
      return Optional.ofNullable(this.selectedTemplate);
   }

   public boolean showsTextbox() {
      return this.saveSlots.get(this.saveSlotIndex).showsTextbox();
   }

   public void refreshTemplateSlots() {
      this.initSlots();
      this.onSlotsRefreshed.run();
   }

   public void exportTemplate(String fileName) {
      if (fileName.isEmpty()) {
         this.getPlayer()
            .method_7353(
               class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsMessage("export_template.empty_name")).method_27692(class_124.field_1061),
               false
            );
      } else {
         Matcher matcher = EXPORT_FILE_NAME_PATTERN.matcher(fileName);
         if (!matcher.matches()) {
            this.getPlayer()
               .method_7353(
                  class_2561.method_43469(
                        TranslationHelper.INSTANCE.translSettingsMessage("export_template.invalid_characters"),
                        new Object[]{findNonMatchingCharacters(matcher, fileName)}
                     )
                     .method_27692(class_124.field_1061),
                  false
               );
         } else {
            fileName = fileName.replace(' ', '_');
            fileName = fileName.toLowerCase(Locale.ROOT);
            this.sendDataToServer(() -> NBTHelper.putString(NBTHelper.putString(new class_2487(), "action", "exportTemplate"), "fileName", fileName));
            if (this.getPlayer() instanceof class_3222 serverPlayer) {
               class_3218 serverLevel = serverPlayer.method_51469();
               Path datapacksDir = serverLevel.method_8503().method_27050(class_5218.field_24186);
               String playersFolder = this.getPlayer().method_5820().toLowerCase(Locale.ROOT) + "_soph_templates";
               Path datapackRoot = datapacksDir.resolve(playersFolder);
               Path templatesDir = datapackRoot.resolve("data/" + playersFolder + "/sophisticated_settingstemplates");
               if (!initDatapackStructure(datapackRoot, templatesDir)) {
                  return;
               }

               Path exportPath = templatesDir.resolve(fileName + ".snbt");
               class_2487 settingsNbt = this.settingsContainer.getStorageWrapper().getSettingsHandler().getNbt().method_10553();

               try {
                  class_2461.method_32234(class_7403.field_39439, exportPath, new class_5625().method_32283(settingsNbt));
               } catch (IOException var13) {
                  SophisticatedCore.LOGGER.error("Error writing template export", var13);
                  return;
               }

               DatapackSettingsTemplateManager.putTemplate(playersFolder, fileName, settingsNbt);
               PacketDistributor.sendToPlayer(serverPlayer, new SyncDatapackSettingsTemplatePayload(playersFolder, fileName, settingsNbt));
               this.initSlots();
               this.getPlayer()
                  .method_7353(
                     class_2561.method_43469(
                        TranslationHelper.INSTANCE.translSettingsMessage("export_template"),
                        new Object[]{serverLevel.method_8503().method_27050(class_5218.field_24188).relativize(exportPath)}
                     ),
                     false
                  );
            }
         }
      }
   }

   public static String findNonMatchingCharacters(Matcher matcher, String input) {
      StringBuilder nonMatchingCharacters = new StringBuilder();

      for (int i = 0; i < input.length(); i++) {
         char c = input.charAt(i);
         if (!matcher.reset(String.valueOf(c)).matches()) {
            nonMatchingCharacters.append(c);
         }
      }

      return nonMatchingCharacters.toString();
   }

   public boolean templateHasTooManySlots() {
      return this.selectedTemplate != null
         && this.selectedTemplate
            .getSettingsCategories()
            .values()
            .stream()
            .anyMatch(category -> category.isLargerThanNumberOfSlots(this.settingsContainer.getStorageWrapper().getInventoryHandler().getSlotCount()));
   }

   private static boolean initDatapackStructure(Path datapackRoot, Path templatesDir) {
      try {
         Files.createDirectories(templatesDir);
      } catch (IOException var5) {
         SophisticatedCore.LOGGER.error("Error creating directory for template export", var5);
         return false;
      }

      Path packMcmetaFile = datapackRoot.resolve("pack.mcmeta");
      if (!Files.exists(packMcmetaFile)) {
         try {
            Files.writeString(
               packMcmetaFile,
               "{\n    \"pack\": {\n        \"pack_format\": 15,\n        \"description\": \"Sophisticated Settings Templates data pack\"\n    }\n}\n"
            );
         } catch (IOException var4) {
            SophisticatedCore.LOGGER.error("Error creating pack.mcmeta for template export", var4);
            return false;
         }
      }

      return true;
   }

   public Optional<String> getLoadSlotSource() {
      return this.loadSlots.get(this.loadSlotIndex).getSlotSource();
   }

   private static class DatapackSlot implements TemplatePersistanceContainer.IPersistanceSlot {
      private final String datapackName;
      private final String templateName;

      public DatapackSlot(String datapackName, String templateName) {
         this.datapackName = datapackName;
         this.templateName = templateName;
      }

      @Override
      public String getName() {
         return "datapack";
      }

      @Override
      public String getSlotName() {
         return this.templateName;
      }

      @Override
      public class_2487 getSettingsNbt(class_1657 player, SettingsTemplateStorage settingsTemplateStorage) {
         return DatapackSettingsTemplateManager.getTemplateNbt(this.datapackName, this.templateName).orElseGet(class_2487::new);
      }

      @Override
      public Optional<String> getSlotSource() {
         return Optional.of(this.datapackName);
      }
   }

   private static class EditNamePersistanceSlot extends TemplatePersistanceContainer.NamedPersistanceSlot {
      public EditNamePersistanceSlot(String slotName) {
         super(slotName);
      }

      @Override
      public boolean showsTextbox() {
         return true;
      }

      @Override
      public void setSlotName(String slotName) {
         this.slotName = slotName;
      }

      @Override
      public class_5250 getSlotTooltipName() {
         return class_2561.method_43471(TranslationHelper.INSTANCE.translSettingsButton("save_template.custom_name_slot"));
      }
   }

   private interface IPersistanceSlot {
      String getName();

      String getSlotName();

      default void serialize(class_2487 tag) {
         tag.method_10582("name", this.getName());
      }

      default void persistTo(class_1657 player, SettingsTemplateStorage settingsTemplateStorage, class_2487 settingsCopy) {
      }

      default boolean showsTextbox() {
         return false;
      }

      default void setSlotName(String slotName) {
      }

      default class_5250 getSlotTooltipName() {
         return class_2561.method_43470(this.getSlotName());
      }

      class_2487 getSettingsNbt(class_1657 var1, SettingsTemplateStorage var2);

      default Optional<String> getSlotSource() {
         return Optional.empty();
      }
   }

   private static class NamedPersistanceSlot implements TemplatePersistanceContainer.IPersistanceSlot {
      protected String slotName;

      public NamedPersistanceSlot(String slotName) {
         this.slotName = slotName;
      }

      @Override
      public String getName() {
         return "named";
      }

      @Override
      public String getSlotName() {
         return this.slotName;
      }

      @Override
      public void serialize(class_2487 tag) {
         TemplatePersistanceContainer.IPersistanceSlot.super.serialize(tag);
         tag.method_10582("slot", this.slotName);
      }

      @Override
      public void persistTo(class_1657 player, SettingsTemplateStorage settingsTemplateStorage, class_2487 settingsCopy) {
         settingsTemplateStorage.putPlayerNamedTemplate(player, this.slotName, settingsCopy);
      }

      @Override
      public class_2487 getSettingsNbt(class_1657 player, SettingsTemplateStorage settingsTemplateStorage) {
         return settingsTemplateStorage.getPlayerNamedTemplates(player).getOrDefault(this.slotName, new class_2487());
      }
   }

   private static class OrdinalPersistanceSlot implements TemplatePersistanceContainer.IPersistanceSlot {
      private final int slot;

      public OrdinalPersistanceSlot(int slot) {
         this.slot = slot;
      }

      @Override
      public String getName() {
         return "ordinal";
      }

      @Override
      public String getSlotName() {
         return String.valueOf(this.slot);
      }

      @Override
      public void serialize(class_2487 tag) {
         TemplatePersistanceContainer.IPersistanceSlot.super.serialize(tag);
         tag.method_10569("slot", this.slot);
      }

      @Override
      public void persistTo(class_1657 player, SettingsTemplateStorage settingsTemplateStorage, class_2487 settingsCopy) {
         settingsTemplateStorage.putPlayerTemplate(player, this.slot, settingsCopy);
      }

      @Override
      public class_2487 getSettingsNbt(class_1657 player, SettingsTemplateStorage settingsTemplateStorage) {
         return settingsTemplateStorage.getPlayerTemplates(player).getOrDefault(this.slot, new class_2487());
      }
   }

   public abstract static class TemplateSettingsHandler extends SettingsHandler {
      protected TemplateSettingsHandler(class_2487 contentsNbt, class_5455 registryAccess) {
         super(contentsNbt, () -> {}, NoopStorageWrapper.INSTANCE::getInventoryHandler, NoopStorageWrapper.INSTANCE::getRenderInfo);
      }

      protected abstract SettingsHandler getCurrentSettingsHandler();

      @Override
      protected class_2487 getSettingsNbtFromContentsNbt(class_2487 contentsNbt) {
         return contentsNbt;
      }

      @Override
      protected void addItemDisplayCategory(
         Supplier<InventoryHandler> inventoryHandlerSupplier, Supplier<RenderInfo> renderInfoSupplier, class_2487 settingsNbt
      ) {
         int itemNumberLimit = this.getCurrentSettingsHandler().getTypeCategory(ItemDisplaySettingsCategory.class).getItemNumberLimit();
         this.addSettingsCategory(
            settingsNbt,
            "item_display",
            this.markContentsDirty,
            (categoryNbt, saveNbt) -> new ItemDisplaySettingsCategory(
               inventoryHandlerSupplier, renderInfoSupplier, categoryNbt, saveNbt, itemNumberLimit, () -> this.getTypeCategory(MemorySettingsCategory.class)
            )
         );
      }

      @Override
      public String getGlobalSettingsCategoryName() {
         return this.getCurrentSettingsHandler().getGlobalSettingsCategoryName();
      }

      @Override
      public ISettingsCategory<?> instantiateGlobalSettingsCategory(class_2487 categoryNbt, Consumer<class_2487> saveNbt) {
         return this.getCurrentSettingsHandler().instantiateGlobalSettingsCategory(categoryNbt, saveNbt);
      }

      @Override
      protected void saveCategoryNbt(class_2487 settingsNbt, String categoryName, class_2487 tag) {
      }
   }
}
