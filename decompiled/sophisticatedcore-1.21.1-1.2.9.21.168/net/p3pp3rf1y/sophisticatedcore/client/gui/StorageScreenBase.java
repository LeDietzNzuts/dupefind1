package net.p3pp3rf1y.sophisticatedcore.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.class_1058;
import net.minecraft.class_124;
import net.minecraft.class_1661;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1767;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_2813;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_465;
import net.minecraft.class_5481;
import net.minecraft.class_768;
import net.minecraft.class_7923;
import net.minecraft.class_9799;
import net.minecraft.class_4597.class_4598;
import net.p3pp3rf1y.sophisticatedcore.Config;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.Button;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinition;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonDefinitions;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.InventoryScrollPanel;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.TextBox;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ToggleButton;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.WidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.common.gui.SortBy;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageBackgroundProperties;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageInventorySlot;
import net.p3pp3rf1y.sophisticatedcore.common.gui.UpgradeContainerBase;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;
import net.p3pp3rf1y.sophisticatedcore.network.TransferFullSlotPayload;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.crafting.ICraftingUIPart;
import net.p3pp3rf1y.sophisticatedcore.util.CountAbbreviator;
import org.joml.Matrix4f;

public abstract class StorageScreenBase<S extends StorageContainerMenuBase<?>> extends class_465<S> implements InventoryScrollPanel.IInventoryScreen {
   public static final int ERROR_BACKGROUND_COLOR = -267386864;
   public static final int ERROR_BORDER_COLOR = class_1767.field_7964.method_7787();
   private static final int DISABLED_SLOT_COLOR = -1072689136;
   private static final int UPGRADE_TOP_HEIGHT = 7;
   private static final int UPGRADE_SLOT_HEIGHT = 16;
   private static final int UPGRADE_BOTTOM_HEIGHT = 6;
   public static final int UPGRADE_INVENTORY_OFFSET = 21;
   public static final int DISABLED_SLOT_X_POS = -2000;
   static final int SLOTS_Y_OFFSET = 17;
   static final int SLOTS_X_OFFSET = 7;
   public static final int ERROR_SLOT_COLOR = class_1767.field_7964.method_7787() & 16777215 | -1442840576;
   private static final int ERROR_TEXT_COLOR = class_1767.field_7964.method_7787();
   public static final int HEIGHT_WITHOUT_STORAGE_SLOTS = 114;
   private UpgradeSettingsTabControl settingsTabControl;
   private final int numberOfUpgradeSlots;
   @Nullable
   private Button sortButton = null;
   @Nullable
   private ToggleButton<SortBy> sortByButton = null;
   private InventoryScrollPanel inventoryScrollPanel = null;
   private final Set<ToggleButton<Boolean>> upgradeSwitches = new HashSet<>();
   private final Map<Integer, UpgradeInventoryPartBase<?>> inventoryParts = new LinkedHashMap<>();
   private static ICraftingUIPart craftingUIPart = ICraftingUIPart.NOOP;
   private static ISlotDecorationRenderer slotDecorationRenderer = (guiGraphics, slot) -> {};
   protected StorageBackgroundProperties storageBackgroundProperties;
   @Nullable
   private Button transferToStorageButton;
   @Nullable
   private Button transferToInventoryButton;
   private TextBox searchBox;
   private Predicate<class_1799> stackFilter = stack -> this.searchBox == null
      || this.searchBox.getValue().isEmpty()
      || !stack.method_7960() && stack.method_7964().getString().toLowerCase().contains(this.searchBox.getValue().toLowerCase());
   private int visibleSlotsCount;
   private boolean initializing = true;

   public static void setCraftingUIPart(ICraftingUIPart part) {
      craftingUIPart = part;
   }

   public static void setSlotDecorationRenderer(ISlotDecorationRenderer renderer) {
      slotDecorationRenderer = renderer;
   }

   protected StorageScreenBase(S menu, class_1661 playerInventory, class_2561 title) {
      super(menu, playerInventory, title);
      this.numberOfUpgradeSlots = ((StorageContainerMenuBase)this.method_17577()).getNumberOfUpgradeSlots();
      this.visibleSlotsCount = ((StorageContainerMenuBase)this.method_17577()).getNumberOfStorageInventorySlots();
      this.updateDimensionsAndSlotPositions(class_310.method_1551().method_22683().method_4502());
   }

   public ICraftingUIPart getCraftingUIAddition() {
      return craftingUIPart;
   }

   public void method_25410(class_310 minecraft, int width, int height) {
      this.updateDimensionsAndSlotPositions(height);
      super.method_25410(minecraft, width, height);
   }

   private void updateDimensionsAndSlotPositions(int height) {
      int displayableNumberOfRows = Math.min((height - 114) / 18, ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows());
      int newImageHeight = 114 + this.getStorageInventoryHeight(displayableNumberOfRows);
      this.storageBackgroundProperties = ((StorageContainerMenuBase)this.method_17577()).getNumberOfStorageInventorySlots()
               + ((StorageContainerMenuBase)this.method_17577()).getColumnsTaken() * ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows()
            <= 81
         ? StorageBackgroundProperties.REGULAR_9_SLOT
         : StorageBackgroundProperties.REGULAR_12_SLOT;
      this.field_2792 = this.storageBackgroundProperties.getSlotsOnLine() * 18 + 14;
      this.updateStorageSlotsPositions();
      if (displayableNumberOfRows < ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows()) {
         this.storageBackgroundProperties = this.storageBackgroundProperties == StorageBackgroundProperties.REGULAR_9_SLOT
            ? StorageBackgroundProperties.WIDER_9_SLOT
            : StorageBackgroundProperties.WIDER_12_SLOT;
         this.field_2792 += 6;
      }

      this.field_2779 = newImageHeight;
      this.field_25270 = this.field_2779 - 94;
      this.field_25269 = 8 + this.storageBackgroundProperties.getPlayerInventoryXOffset();
      this.updatePlayerSlotsPositions();
      this.updateUpgradeSlotsPositions();
      this.updateTransferButtonsPositions();
   }

   protected int getStorageInventoryHeight(int displayableNumberOfRows) {
      return displayableNumberOfRows * 18;
   }

   @Override
   public class_1735 getSlot(int slotIndex) {
      return ((StorageContainerMenuBase)this.method_17577()).method_7611(slotIndex);
   }

   protected void updateUpgradeSlotsPositions() {
      int yPosition = 6;

      for (int slotIndex = 0; slotIndex < this.numberOfUpgradeSlots; slotIndex++) {
         class_1735 slot = ((StorageContainerMenuBase)this.method_17577())
            .method_7611(((StorageContainerMenuBase)this.method_17577()).getFirstUpgradeSlot() + slotIndex);
         slot.field_7872 = yPosition;
         yPosition += 16;
      }
   }

   protected void updateStorageSlotsPositions() {
      int yPosition = 18;
      this.visibleSlotsCount = 0;
      int slotIndex = 0;

      while (slotIndex < ((StorageContainerMenuBase)this.method_17577()).getNumberOfStorageInventorySlots()) {
         class_1735 slot = ((StorageContainerMenuBase)this.method_17577()).method_7611(slotIndex);
         int lineIndex = this.visibleSlotsCount % this.getSlotsOnLine();
         slotIndex++;
         if (this.stackFilter.test(slot.method_7677())) {
            slot.field_7873 = 8 + lineIndex * 18;
            slot.field_7872 = yPosition;
            this.visibleSlotsCount++;
            if (this.visibleSlotsCount % this.getSlotsOnLine() == 0) {
               yPosition += 18;
            }
         } else {
            slot.field_7873 = -2000;
         }
      }
   }

   @Override
   public Predicate<class_1799> getStackFilter() {
      return this.stackFilter;
   }

   protected void updatePlayerSlotsPositions() {
      int playerInventoryXOffset = this.storageBackgroundProperties.getPlayerInventoryXOffset();
      int yPosition = this.field_25270 + 12;

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            int slotIndex = j + i * 9;
            int xPosition = playerInventoryXOffset + 8 + j * 18;
            class_1735 slot = ((StorageContainerMenuBase)this.method_17577())
               .method_7611(((StorageContainerMenuBase)this.method_17577()).getInventorySlotsSize() - 36 + slotIndex);
            slot.field_7873 = xPosition;
            slot.field_7872 = yPosition;
         }

         yPosition += 18;
      }

      yPosition += 4;

      for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
         int xPosition = playerInventoryXOffset + 8 + slotIndex * 18;
         class_1735 slot = ((StorageContainerMenuBase)this.method_17577())
            .method_7611(((StorageContainerMenuBase)this.method_17577()).getInventorySlotsSize() - 36 + 27 + slotIndex);
         slot.field_7873 = xPosition;
         slot.field_7872 = yPosition;
      }
   }

   protected void method_25426() {
      super.method_25426();
      this.updateInventoryScrollPanel();
      craftingUIPart.setStorageScreen(this);
      this.initUpgradeSettingsControl();
      this.initUpgradeInventoryParts();
      this.addUpgradeSwitches();
      ((StorageContainerMenuBase)this.method_17577()).setUpgradeChangeListener(c -> {
         this.updateStorageSlotsPositions();
         this.updatePlayerSlotsPositions();
         this.updateUpgradeSlotsPositions();
         this.updateInventoryScrollPanel();
         this.method_25396().remove(this.settingsTabControl);
         craftingUIPart.onCraftingSlotsHidden();
         this.initUpgradeSettingsControl();
         this.initUpgradeInventoryParts();
         this.addUpgradeSwitches();
      });
      if (this.shouldShowSortButtons()) {
         this.addSortButtons();
      }

      this.addTransferButtons();
      this.addSearchBox();
      this.initializing = false;
   }

   protected void addSearchBox() {
      SortButtonsPosition sortButtonsPosition = (SortButtonsPosition)Config.CLIENT.sortButtonsPosition.get();
      int x = 7;
      int xEnd = sortButtonsPosition == SortButtonsPosition.TITLE_LINE_RIGHT
         ? this.getSortButtonsPosition(sortButtonsPosition).x() - 1 - this.field_2776
         : this.field_2792 - 7;
      int width = xEnd - x;
      this.searchBox = new SearchBox(new Position(this.field_2776 + x, this.field_2800 + 5), new Dimension(width, 10), this);
      this.searchBox.setResponder(this::onSearchPhraseChange);
      if (((StorageContainerMenuBase)this.method_17577()).shouldKeepSearchPhrase()) {
         this.searchBox.setValue(((StorageContainerMenuBase)this.method_17577()).getSearchPhrase());
      }

      this.method_37063(this.searchBox);
   }

   private void onSearchPhraseChange(String searchPhrase) {
      if (!this.initializing) {
         ((StorageContainerMenuBase)this.method_17577()).setSearchPhrase(searchPhrase);
      }

      this.updateSearchFilter(searchPhrase);
      if (this.inventoryScrollPanel != null) {
         this.inventoryScrollPanel.resetScrollDistance();
         this.inventoryScrollPanel.updateSlotsPosition();
      } else {
         this.updateStorageSlotsPositions();
      }
   }

   private void updateSearchFilter(String searchPhrase) {
      if (searchPhrase.trim().isEmpty()) {
         this.stackFilter = stack -> true;
      } else {
         String[] searchTerms = searchPhrase.trim().split(" ");
         List<Predicate<class_1799>> filters = new ArrayList<>();

         for (String searchTerm : searchTerms) {
            if (searchTerm.startsWith("@")) {
               String modName = searchTerm.substring(1).toLowerCase();
               filters.add(stack -> modName.isEmpty() || class_7923.field_41178.method_10221(stack.method_7909()).method_12836().contains(modName));
            } else if (searchTerm.startsWith("#")) {
               String tooltipKeyword = searchTerm.substring(1).toLowerCase();
               filters.add(stack -> method_25408(this.field_22787, stack).stream().anyMatch(line -> line.getString().toLowerCase().contains(tooltipKeyword)));
            } else {
               filters.add(stack -> stack.method_7964().getString().toLowerCase().contains(searchTerm.toLowerCase()));
            }
         }

         this.stackFilter = stack -> !stack.method_7960() && filters.stream().allMatch(f -> f.test(stack));
      }
   }

   private void addTransferButtons() {
      this.transferToStorageButton = new StorageScreenBase.TransferButton(
         filterByContents -> ((StorageContainerMenuBase)this.method_17577()).transferItemsToStorage(filterByContents),
         ButtonDefinitions.TRANSFER_TO_STORAGE,
         ButtonDefinitions.TRANSFER_TO_STORAGE_FILTERED
      );
      this.method_37063(this.transferToStorageButton);
      this.transferToInventoryButton = new StorageScreenBase.TransferButton(
         filterByContents -> ((StorageContainerMenuBase)this.method_17577()).transferItemsToPlayerInventory(filterByContents),
         ButtonDefinitions.TRANSFER_TO_INVENTORY,
         ButtonDefinitions.TRANSFER_TO_INVENTORY_FILTERED
      );
      this.method_37063(this.transferToInventoryButton);
      this.updateTransferButtonsPositions();
   }

   protected boolean shouldShowSortButtons() {
      return true;
   }

   private void updateInventoryScrollPanel() {
      if (this.inventoryScrollPanel != null) {
         this.method_37066(this.inventoryScrollPanel);
      }

      int numberOfVisibleRows = this.getNumberOfVisibleRows();
      if (numberOfVisibleRows < ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows()) {
         this.inventoryScrollPanel = new InventoryScrollPanel(
            class_310.method_1551(),
            this,
            0,
            ((StorageContainerMenuBase)this.method_17577()).getNumberOfStorageInventorySlots(),
            this.getSlotsOnLine(),
            numberOfVisibleRows * 18,
            this.sophisticatedCore_getGuiTop() + 17,
            this.sophisticatedCore_getGuiLeft() + 7
         );
         this.method_37063(this.inventoryScrollPanel);
         this.inventoryScrollPanel.updateSlotsPosition();
      } else {
         this.inventoryScrollPanel = null;
      }
   }

   private void updateTransferButtonsPositions() {
      if (this.transferToStorageButton != null && this.transferToInventoryButton != null) {
         this.transferToStorageButton.setPosition(new Position(this.field_2776 + this.field_25269 + 137, this.field_2800 + this.field_25270 - 2));
         this.transferToInventoryButton.setPosition(new Position(this.field_2776 + this.field_25269 + 149, this.field_2800 + this.field_25270 - 2));
      }
   }

   private int getNumberOfVisibleRows() {
      return Math.min((this.field_2779 - 114) / 18, ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows());
   }

   public int getSlotsOnLine() {
      return this.storageBackgroundProperties.getSlotsOnLine() - ((StorageContainerMenuBase)this.method_17577()).getColumnsTaken();
   }

   private void initUpgradeInventoryParts() {
      this.inventoryParts.clear();
      if (((StorageContainerMenuBase)this.method_17577()).getColumnsTaken() != 0) {
         int numberOfVisibleRows = this.getNumberOfVisibleRows();
         int scrollBarOffset = numberOfVisibleRows < ((StorageContainerMenuBase)this.method_17577()).getNumberOfRows() ? 6 : 0;
         AtomicReference<Position> pos = new AtomicReference<>(new Position(7 + this.getSlotsOnLine() * 18 + scrollBarOffset, 17));
         int height = numberOfVisibleRows * 18;

         for (Entry<Integer, UpgradeContainerBase<?, ?>> entry : ((StorageContainerMenuBase)this.method_17577()).getUpgradeContainers().entrySet()) {
            UpgradeContainerBase<?, ?> container = entry.getValue();
            UpgradeGuiManager.getInventoryPart(entry.getKey(), container, pos.get(), height, this).ifPresent(part -> {
               this.inventoryParts.put(entry.getKey(), (UpgradeInventoryPartBase<?>)part);
               pos.set(new Position(pos.get().x() + 36, pos.get().y()));
            });
         }
      }
   }

   private void addUpgradeSwitches() {
      this.upgradeSwitches.clear();
      int switchTop = this.field_2800 + 8;

      for (int slot = 0; slot < this.numberOfUpgradeSlots; slot++) {
         if (((StorageContainerMenuBase)this.field_2797).canDisableUpgrade(slot)) {
            int finalSlot = slot;
            ToggleButton<Boolean> upgradeSwitch = new ToggleButton<>(
               new Position(this.field_2776 - 22, switchTop),
               ButtonDefinitions.UPGRADE_SWITCH,
               button -> ((StorageContainerMenuBase)this.method_17577())
                  .setUpgradeEnabled(finalSlot, !((StorageContainerMenuBase)this.method_17577()).getUpgradeEnabled(finalSlot)),
               () -> ((StorageContainerMenuBase)this.method_17577()).getUpgradeEnabled(finalSlot)
            );
            this.method_25429(upgradeSwitch);
            this.upgradeSwitches.add(upgradeSwitch);
         }

         switchTop += 16;
      }
   }

   private void addSortButtons() {
      SortButtonsPosition sortButtonsPosition = (SortButtonsPosition)Config.CLIENT.sortButtonsPosition.get();
      if (sortButtonsPosition != SortButtonsPosition.HIDDEN) {
         Position pos = this.getSortButtonsPosition(sortButtonsPosition);
         this.sortButton = new Button(new Position(pos.x(), pos.y()), ButtonDefinitions.SORT, button -> {
            if (button == 0) {
               ((StorageContainerMenuBase)this.method_17577()).sort();
               class_310.method_1551().field_1724.method_7353(class_2561.method_43470("Sorted"), true);
            }
         });
         this.method_25429(this.sortButton);
         this.sortByButton = new ToggleButton<>(new Position(pos.x() + 12, pos.y()), ButtonDefinitions.SORT_BY, button -> {
            if (button == 0) {
               ((StorageContainerMenuBase)this.method_17577()).setSortBy(((StorageContainerMenuBase)this.method_17577()).getSortBy().next());
            }
         }, () -> ((StorageContainerMenuBase)this.method_17577()).getSortBy());
         this.method_25429(this.sortByButton);
      }
   }

   private Position getSortButtonsPosition(SortButtonsPosition sortButtonsPosition) {
      return switch (sortButtonsPosition) {
         case BELOW_UPGRADES -> new Position(this.field_2776 - 21 - 2, this.field_2800 + this.getUpgradeHeightWithoutBottom() + 6 + 2);
         case BELOW_UPGRADE_TABS -> new Position(
            this.settingsTabControl.getX() + 2, this.settingsTabControl.getY() + Math.max(0, this.settingsTabControl.getHeight() + 2)
         );
         default -> new Position(this.field_2776 + this.field_2792 - 31, this.field_2800 + 4);
      };
   }

   private void initUpgradeSettingsControl() {
      this.settingsTabControl = new UpgradeSettingsTabControl(
         new Position(this.field_2776 + this.field_2792, this.field_2800 + 4), this, this.getStorageSettingsTabTooltip()
      );
      this.method_25429(this.settingsTabControl);
   }

   protected abstract String getStorageSettingsTabTooltip();

   public int getUpgradeHeight() {
      return this.getUpgradeHeightWithoutBottom() + 7;
   }

   protected int getUpgradeHeightWithoutBottom() {
      return 6 + this.numberOfUpgradeSlots * 16;
   }

   public Optional<class_768> getSortButtonsRectangle() {
      return this.sortButton != null && this.sortByButton != null
         ? GuiHelper.getPositiveRectangle(
            this.sortButton.getX(),
            this.sortButton.getY(),
            this.sortByButton.getX() + this.sortByButton.getWidth() - this.sortButton.getX(),
            this.sortByButton.getY() + this.sortByButton.getHeight() - this.sortButton.getY()
         )
         : Optional.empty();
   }

   public void method_25394(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      if (((StorageContainerMenuBase)this.field_2797).detectSettingsChangeAndReload()) {
         this.updateStorageSlotsPositions();
         this.updatePlayerSlotsPositions();
         this.updateInventoryScrollPanel();
         this.updateTransferButtonsPositions();
      }

      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, -20.0F);
      this.method_25420(guiGraphics, mouseX, mouseY, partialTicks);
      poseStack.method_22909();
      this.settingsTabControl.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      super.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      this.settingsTabControl.renderTooltip(this, guiGraphics, mouseX, mouseY);
      if (this.sortButton != null && this.sortByButton != null) {
         this.sortButton.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
         this.sortByButton.method_25394(guiGraphics, mouseX, mouseY, partialTicks);
      }

      this.upgradeSwitches.forEach(us -> us.method_25394(guiGraphics, mouseX, mouseY, partialTicks));
      this.renderErrorOverlay(guiGraphics);
      this.method_2380(guiGraphics, mouseX, mouseY);
   }

   public void method_52752(class_332 guiGraphics) {
      class_4587 pose = guiGraphics.method_51448();
      pose.method_22903();
      pose.method_46416(0.0F, 0.0F, -20.0F);
      super.method_52752(guiGraphics);
      pose.method_22909();
   }

   protected void method_2388(class_332 guiGraphics, int mouseX, int mouseY) {
      super.method_2388(guiGraphics, mouseX, mouseY);
      this.renderUpgradeInventoryParts(guiGraphics, mouseX, mouseY);
      this.renderUpgradeSlots(guiGraphics, mouseX, mouseY);
      if (this.inventoryScrollPanel == null) {
         this.renderStorageInventorySlots(guiGraphics, mouseX, mouseY);
      }
   }

   private void renderUpgradeInventoryParts(class_332 guiGraphics, int mouseX, int mouseY) {
      this.inventoryParts.values().forEach(ip -> ip.render(guiGraphics, mouseX, mouseY));
   }

   private void renderStorageInventorySlots(class_332 guiGraphics, int mouseX, int mouseY) {
      this.renderStorageInventorySlots(guiGraphics, mouseX, mouseY, true);
   }

   private void renderStorageInventorySlots(class_332 guiGraphics, int mouseX, int mouseY, boolean canShowHover) {
      for (int slotId = 0;
         slotId < ((StorageContainerMenuBase)this.field_2797).realInventorySlots.size()
            && slotId < ((StorageContainerMenuBase)this.method_17577()).getNumberOfStorageInventorySlots();
         slotId++
      ) {
         class_1735 slot = ((StorageContainerMenuBase)this.field_2797).realInventorySlots.get(slotId);
         this.method_2385(guiGraphics, slot);
         if (canShowHover && this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
            this.field_2787 = slot;
            GuiHelper.renderSlotHighlight(guiGraphics, slot.field_7873, slot.field_7872, 0, this.sophisticatedCore_getSlotColor(slotId));
         }
      }
   }

   private void renderUpgradeSlots(class_332 guiGraphics, int mouseX, int mouseY) {
      for (int slotId = 0; slotId < ((StorageContainerMenuBase)this.field_2797).upgradeSlots.size(); slotId++) {
         class_1735 slot = ((StorageContainerMenuBase)this.field_2797).upgradeSlots.get(slotId);
         if (slot.field_7873 != -2000) {
            this.method_2385(guiGraphics, slot);
            if (!slot.method_7682()) {
               this.renderSlotOverlay(guiGraphics, slot, -1072689136);
            }
         }

         if (this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
            this.field_2787 = slot;
            GuiHelper.renderSlotHighlight(guiGraphics, slot.field_7873, slot.field_7872, 0, this.sophisticatedCore_getSlotColor(slotId));
         }
      }
   }

   public void method_2385(class_332 guiGraphics, class_1735 slot) {
      int i = slot.field_7873;
      int j = slot.field_7872;
      class_1799 stackToRender = slot.method_7677();
      boolean flag = false;
      boolean rightClickDragging = slot == this.field_2777 && !this.field_2782.method_7960() && !this.field_2789;
      class_1799 carriedStack = ((StorageContainerMenuBase)this.method_17577()).method_34255();
      String stackCountText = null;
      if (((StorageContainerMenuBase)this.method_17577()).isInfiniteSlot(slot.field_7874)) {
         stackCountText = "∞";
      }

      if (slot == this.field_2777 && !this.field_2782.method_7960() && this.field_2789 && !stackToRender.method_7960()) {
         stackToRender = stackToRender.method_7972();
         stackToRender.method_7939(stackToRender.method_7947() / 2);
      } else if (this.field_2794 && this.field_2793.contains(slot) && !carriedStack.method_7960()) {
         if (this.field_2793.size() == 1) {
            return;
         }

         if (StorageContainerMenuBase.canItemQuickReplace(slot, carriedStack) && ((StorageContainerMenuBase)this.field_2797).method_7615(slot)) {
            flag = true;
            int slotStackCount = stackToRender.method_7960() ? 0 : stackToRender.method_7947();
            int renderCount = StorageContainerMenuBase.getQuickCraftPlaceCount(slot, this.field_2793.size(), this.field_2790, carriedStack) + slotStackCount;
            int slotLimit = stackToRender.method_7960() ? 64 : slot.method_7676(stackToRender);
            if (renderCount > slotLimit) {
               stackCountText = class_124.field_1054 + CountAbbreviator.abbreviate(slotLimit);
            }

            stackToRender = carriedStack.method_46651(renderCount);
         } else {
            this.field_2793.remove(slot);
            this.method_2379();
         }
      }

      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(0.0F, 0.0F, 100.0F);
      if (stackToRender.method_7960() && slot.method_7682()) {
         this.renderSlotBackground(guiGraphics, slot, i, j);
      } else if (!rightClickDragging) {
         this.renderStack(guiGraphics, i, j, stackToRender, flag, stackCountText);
         slotDecorationRenderer.renderDecoration(guiGraphics, slot);
      }

      poseStack.method_22909();
   }

   private void renderStack(class_332 guiGraphics, int x, int y, class_1799 itemstack, boolean flag, @Nullable String stackCountText) {
      if (flag) {
         guiGraphics.method_25294(x, y, x + 16, y + 16, -2130706433);
      }

      RenderSystem.enableDepthTest();
      guiGraphics.method_51427(itemstack, x, y);
      if (this.shouldUseSpecialCountRender(itemstack)) {
         guiGraphics.method_51432(this.field_22793, itemstack, x, y, "");
         if (stackCountText == null) {
            stackCountText = CountAbbreviator.abbreviate(itemstack.method_7947());
         }

         this.renderStackCount(guiGraphics, stackCountText, x, y);
      } else {
         guiGraphics.method_51432(this.field_22793, itemstack, x, y, stackCountText);
      }

      RenderSystem.disableDepthTest();
   }

   private void renderSlotBackground(class_332 guiGraphics, class_1735 slot, int i, int j) {
      Optional<class_1799> memorizedStack = ((StorageContainerMenuBase)this.method_17577()).getMemorizedStackInSlot(slot.field_7874);
      if (((StorageContainerMenuBase)this.method_17577()).isStorageInventorySlot(slot.field_7874)) {
         if (memorizedStack.isPresent()) {
            guiGraphics.method_51427(memorizedStack.get(), i, j);
            this.drawStackOverlay(guiGraphics, i, j);
            return;
         }

         if (!((StorageContainerMenuBase)this.method_17577()).getSlotFilterItem(slot.field_7874).method_7960()) {
            guiGraphics.method_51427(((StorageContainerMenuBase)this.method_17577()).getSlotFilterItem(slot.field_7874), i, j);
            this.drawStackOverlay(guiGraphics, i, j);
            return;
         }
      }

      Pair<class_2960, class_2960> pair = slot.method_7679();
      if (pair != null) {
         class_1058 textureatlassprite = (class_1058)this.field_22787.method_1549((class_2960)pair.getFirst()).apply((class_2960)pair.getSecond());
         guiGraphics.method_25298(i, j, 0, 16, 16, textureatlassprite);
      }
   }

   private void drawStackOverlay(class_332 guiGraphics, int x, int y) {
      guiGraphics.method_51448().method_22903();
      RenderSystem.enableBlend();
      RenderSystem.disableDepthTest();
      guiGraphics.method_25302(GuiHelper.GUI_CONTROLS, x, y, 77, 0, 16, 16);
      RenderSystem.enableDepthTest();
      RenderSystem.disableBlend();
      guiGraphics.method_51448().method_22909();
   }

   private boolean shouldUseSpecialCountRender(class_1799 itemstack) {
      return itemstack.method_7947() > 99;
   }

   private void renderSlotOverlay(class_332 guiGraphics, class_1735 slot, int slotColor) {
      this.renderSlotOverlay(guiGraphics, slot, slotColor, 0, 16);
   }

   private void renderSlotOverlay(class_332 guiGraphics, class_1735 slot, int slotColor, int yOffset, int height) {
      this.renderOverlay(guiGraphics, slotColor, slot.field_7873, slot.field_7872 + yOffset, 16, height);
   }

   public void renderOverlay(class_332 guiGraphics, int slotColor, int xPos, int yPos, int width, int height) {
      RenderSystem.disableDepthTest();
      RenderSystem.colorMask(true, true, true, false);
      guiGraphics.method_33284(xPos, yPos, xPos + width, yPos + height, 0, slotColor, slotColor);
      RenderSystem.colorMask(true, true, true, true);
      RenderSystem.enableDepthTest();
   }

   protected void method_2389(class_332 guiGraphics, float partialTicks, int mouseX, int mouseY) {
      int x = (this.field_22789 - this.field_2792) / 2;
      int y = (this.field_22790 - this.field_2779) / 2;
      this.drawInventoryBg(guiGraphics, x, y, this.storageBackgroundProperties.getTextureName());
      if (this.inventoryScrollPanel == null) {
         this.drawSlotBg(guiGraphics, x, y, this.visibleSlotsCount);
         this.drawSlotOverlays(guiGraphics);
      }

      this.drawUpgradeBackground(guiGraphics);
   }

   protected void drawSlotBg(class_332 guiGraphics, int x, int y, int visibleSlotsCount) {
      int slotsOnLine = this.getSlotsOnLine();
      int slotRows = visibleSlotsCount / slotsOnLine;
      int remainingSlots = visibleSlotsCount % slotsOnLine;
      GuiHelper.renderSlotsBackground(guiGraphics, x + 7, y + 17, slotsOnLine, slotRows, remainingSlots);
   }

   private void drawSlotOverlays(class_332 guiGraphics) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_46416(this.sophisticatedCore_getGuiLeft(), this.sophisticatedCore_getGuiTop(), 0.0F);

      for (int slotNumber = 0; slotNumber < ((StorageContainerMenuBase)this.field_2797).getNumberOfStorageInventorySlots(); slotNumber++) {
         List<Integer> colors = ((StorageContainerMenuBase)this.field_2797).getSlotOverlayColors(slotNumber);
         if (!colors.isEmpty()) {
            int stripeHeight = 16 / colors.size();
            int i = 0;

            for (int slotColor : colors) {
               int yOffset = i * stripeHeight;
               this.renderSlotOverlay(
                  guiGraphics,
                  ((StorageContainerMenuBase)this.field_2797).method_7611(slotNumber),
                  slotColor & 16777215 | 1342177280,
                  yOffset,
                  i == colors.size() - 1 ? 16 - yOffset : stripeHeight
               );
               i++;
            }
         }
      }

      poseStack.method_22909();
   }

   protected void method_2380(class_332 guiGraphics, int x, int y) {
      this.inventoryParts.values().forEach(part -> part.renderTooltip(this, guiGraphics, x, y));
      if (((StorageContainerMenuBase)this.method_17577()).method_34255().method_7960() && this.field_2787 != null) {
         if (this.field_2787.method_7681()) {
            super.method_2380(guiGraphics, x, y);
         } else if (this.field_2787 instanceof INameableEmptySlot emptySlot && emptySlot.hasEmptyTooltip()) {
            guiGraphics.method_51434(this.field_22793, Collections.singletonList(emptySlot.getEmptyTooltip()), x, y);
         }
      }

      if (this.sortButton != null) {
         this.sortButton.renderTooltip(this, guiGraphics, x, y);
      }

      if (this.sortByButton != null) {
         this.sortByButton.renderTooltip(this, guiGraphics, x, y);
      }

      if (this.transferToStorageButton != null) {
         this.transferToStorageButton.renderTooltip(this, guiGraphics, x, y);
      }

      if (this.transferToInventoryButton != null) {
         this.transferToInventoryButton.renderTooltip(this, guiGraphics, x, y);
      }

      if (this.searchBox != null) {
         this.searchBox.renderTooltip(this, guiGraphics, x, y);
      }
   }

   protected List<class_2561> method_51454(class_1799 itemStack) {
      List<class_2561> ret = method_25408(this.field_22787, itemStack);
      if (this.field_2787 != null && this.field_2787 instanceof StorageInventorySlot && this.field_2787.method_7675() != itemStack.method_7914()) {
         ret.add(
            class_2561.method_43469(
                  TranslationHelper.INSTANCE.translGuiTooltip("stack_count"),
                  new Object[]{
                     class_2561.method_43470(NumberFormat.getNumberInstance().format((long)itemStack.method_7947()))
                        .method_27692(class_124.field_1062)
                        .method_10852(class_2561.method_43470(" / ").method_27692(class_124.field_1080))
                        .method_10852(
                           class_2561.method_43470(NumberFormat.getNumberInstance().format((long)this.field_2787.method_7676(itemStack)))
                              .method_27692(class_124.field_1062)
                        )
                  }
               )
               .method_27692(class_124.field_1080)
         );
      }

      return ret;
   }

   public void drawInventoryBg(class_332 guiGraphics, int x, int y, class_2960 textureName) {
      StorageGuiHelper.renderStorageBackground(new Position(x, y), guiGraphics, textureName, this.field_2792, this.field_2779 - 114);
   }

   private void drawUpgradeBackground(class_332 guiGraphics) {
      if (this.numberOfUpgradeSlots != 0) {
         int heightWithoutBottom = this.getUpgradeHeightWithoutBottom();
         guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, this.field_2776 - 21, this.field_2800, 0.0F, 0.0F, 26, 4, 256, 256);
         guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, this.field_2776 - 21, this.field_2800 + 4, 0.0F, 4.0F, 25, heightWithoutBottom - 4, 256, 256);
         guiGraphics.method_25290(GuiHelper.GUI_CONTROLS, this.field_2776 - 21, this.field_2800 + heightWithoutBottom, 0.0F, 198.0F, 25, 6, 256, 256);
         boolean previousHasSwitch = false;

         for (int slot = 0; slot < this.numberOfUpgradeSlots; slot++) {
            if (((StorageContainerMenuBase)this.field_2797).canDisableUpgrade(slot)) {
               int y = this.field_2800 + 5 + slot * 16 + (previousHasSwitch ? 1 : 0);
               guiGraphics.method_25290(
                  GuiHelper.GUI_CONTROLS, this.field_2776 - 21 - 4, y, 0.0F, 204 + (previousHasSwitch ? 1 : 0), 7, 18 - (previousHasSwitch ? 1 : 0), 256, 256
               );
               previousHasSwitch = true;
            } else {
               previousHasSwitch = false;
            }
         }
      }
   }

   public UpgradeSettingsTabControl getUpgradeSettingsControl() {
      return this.settingsTabControl;
   }

   @Nullable
   public class_1735 method_2386(double mouseX, double mouseY) {
      for (int i = 0; i < ((StorageContainerMenuBase)this.field_2797).upgradeSlots.size(); i++) {
         class_1735 slot = ((StorageContainerMenuBase)this.field_2797).upgradeSlots.get(i);
         if (this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
            return slot;
         }
      }

      if (this.inventoryScrollPanel != null) {
         Optional<class_1735> result = this.inventoryScrollPanel.findSlot(mouseX, mouseY);
         if (result.isPresent()) {
            return result.get();
         } else {
            class_1735 slot = super.method_2386(mouseX, mouseY);
            return slot != null && !((StorageContainerMenuBase)this.field_2797).isStorageInventorySlot(slot.field_7874) ? slot : null;
         }
      } else {
         for (int ix = 0; ix < ((StorageContainerMenuBase)this.field_2797).realInventorySlots.size(); ix++) {
            class_1735 slot = ((StorageContainerMenuBase)this.field_2797).realInventorySlots.get(ix);
            if (this.method_2387(slot, mouseX, mouseY) && slot.method_7682()) {
               return slot;
            }
         }

         return super.method_2386(mouseX, mouseY);
      }
   }

   public boolean method_25406(double mouseX, double mouseY, int button) {
      for (UpgradeInventoryPartBase<?> inventoryPart : this.inventoryParts.values()) {
         if (inventoryPart.handleMouseReleased(mouseX, mouseY, button)) {
            return true;
         }
      }

      this.handleQuickMoveAll(mouseX, mouseY, button);
      return super.method_25406(mouseX, mouseY, button);
   }

   private void handleQuickMoveAll(double mouseX, double mouseY, int button) {
      class_1735 slot = this.method_2386(mouseX, mouseY);
      if (this.field_2783
         && !((StorageContainerMenuBase)this.method_17577()).method_34255().method_7960()
         && slot != null
         && button == 0
         && ((StorageContainerMenuBase)this.field_2797).method_7613(class_1799.field_8037, slot)
         && method_25442()
         && !this.field_2791.method_7960()) {
         for (class_1735 slot2 : ((StorageContainerMenuBase)this.field_2797).realInventorySlots) {
            this.tryQuickMoveSlot(button, slot, slot2);
         }
      }
   }

   private void tryQuickMoveSlot(int button, class_1735 slot, class_1735 slot2) {
      if (slot2.method_7674(this.field_22787.field_1724) && slot2.method_7681() && slot2.sophisticatedCore_isSameInventory(slot)) {
         class_1799 slotItem = slot2.method_7677();
         if (class_1799.method_31577(this.field_2791, slotItem)) {
            if (slotItem.method_7947() > slotItem.method_7914()) {
               PacketDistributor.sendToServer(new TransferFullSlotPayload(slot2.field_7874));
            } else {
               this.method_2383(slot2, slot2.field_7874, button, class_1713.field_7794);
            }
         }
      }
   }

   protected void method_2383(class_1735 slot, int slotNumber, int mouseButton, class_1713 type) {
      if (type == class_1713.field_7793
         && !((StorageContainerMenuBase)this.field_2797).getSlotUpgradeContainer(slot).map(c -> c.allowsPickupAll(slot)).orElse(true)) {
         type = class_1713.field_7790;
      }

      this.handleInventoryMouseClick(slotNumber, mouseButton, type);
   }

   private void handleInventoryMouseClick(int slotNumber, int mouseButton, class_1713 type) {
      StorageContainerMenuBase<?> menu = (StorageContainerMenuBase<?>)this.method_17577();
      List<class_1799> realInventoryItems = new ArrayList<>(menu.realInventorySlots.size());
      menu.realInventorySlots.forEach(slot -> realInventoryItems.add(slot.method_7677().method_7972()));
      List<class_1799> upgradeItems = new ArrayList<>(menu.upgradeSlots.size());
      menu.upgradeSlots.forEach(slot -> upgradeItems.add(slot.method_7677().method_7972()));
      menu.method_7593(slotNumber, mouseButton, type, this.field_22787.field_1724);
      Int2ObjectMap<class_1799> changedSlotIndexes = new Int2ObjectOpenHashMap();
      int inventorySlotsToCheck = Math.min(realInventoryItems.size() - 36, menu.getInventorySlotsSize() - 36);

      for (int i = 0; i < inventorySlotsToCheck; i++) {
         class_1799 itemstack = realInventoryItems.get(i);
         class_1799 slotStack = menu.method_7611(i).method_7677();
         if (!class_1799.method_7973(itemstack, slotStack)) {
            changedSlotIndexes.put(i, slotStack.method_7972());
         }
      }

      for (int ix = 0; ix < 36; ix++) {
         class_1799 itemstack = realInventoryItems.get(realInventoryItems.size() - 36 + ix);
         int slotIndex = menu.getInventorySlotsSize() - 36 + ix;
         class_1799 slotStack = menu.method_7611(slotIndex).method_7677();
         if (!class_1799.method_7973(itemstack, slotStack)) {
            changedSlotIndexes.put(slotIndex, slotStack.method_7972());
         }
      }

      int lastChecked = 0;

      int upgradeSlotsToCheck;
      for (upgradeSlotsToCheck = Math.min(menu.getUpgradeSlotsSize(), upgradeItems.size()); lastChecked < upgradeSlotsToCheck; lastChecked++) {
         class_1799 itemstack = upgradeItems.get(lastChecked);
         class_1799 slotStack = menu.method_7611(menu.getInventorySlotsSize() + lastChecked).method_7677();
         if (!class_1799.method_7973(itemstack, slotStack)) {
            break;
         }
      }

      for (int ixx = upgradeSlotsToCheck - 1; ixx >= lastChecked; ixx--) {
         class_1799 itemstack = upgradeItems.get(ixx);
         int slotIndex = menu.getInventorySlotsSize() + ixx;
         class_1799 slotStack = menu.method_7611(slotIndex).method_7677();
         if (!class_1799.method_7973(itemstack, slotStack)) {
            changedSlotIndexes.put(slotIndex, slotStack.method_7972());
         }
      }

      this.field_22787
         .field_1724
         .field_3944
         .method_52787(
            new class_2813(menu.field_7763, menu.method_37421(), slotNumber, mouseButton, type, menu.method_34255().method_7972(), changedSlotIndexes)
         );
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      class_1735 slot = this.method_2386(mouseX, mouseY);
      if (method_25442() && method_25441() && slot instanceof StorageInventorySlot && button == 0) {
         PacketDistributor.sendToServer(new TransferFullSlotPayload(slot.field_7874));
         return true;
      } else {
         class_364 focused = this.method_25399();
         if (focused != null && !focused.method_25405(mouseX, mouseY) && focused instanceof WidgetBase widgetBase) {
            widgetBase.method_25365(false);
         }

         return super.method_25402(mouseX, mouseY, button);
      }
   }

   public boolean method_25403(double mouseX, double mouseY, int button, double dragX, double dragY) {
      for (class_364 child : this.method_25396()) {
         if (child.method_25405(mouseX, mouseY) && child.method_25403(mouseX, mouseY, button, dragX, dragY)) {
            return true;
         }
      }

      class_1735 slot = this.method_2386(mouseX, mouseY);
      class_1799 itemstack = ((StorageContainerMenuBase)this.method_17577()).method_34255();
      if (!this.field_2794) {
         return super.method_25403(mouseX, mouseY, button, dragX, dragY);
      } else {
         if (slot != null
            && !itemstack.method_7960()
            && (itemstack.method_7947() > this.field_2793.size() || this.field_2790 == 2)
            && StorageContainerMenuBase.canItemQuickReplace(slot, itemstack)
            && slot.method_7680(itemstack)
            && ((StorageContainerMenuBase)this.field_2797).method_7615(slot)
            && this.isAllowedSlotCombination(slot, itemstack)) {
            this.field_2793.add(slot);
            this.method_2379();
         }

         return true;
      }
   }

   private boolean isAllowedSlotCombination(class_1735 slot, class_1799 carried) {
      return !this.field_2793.isEmpty() && carried.method_7909() instanceof UpgradeItemBase<?> upgradeItem && upgradeItem.getInventoryColumnsTaken() != 0
         ? this.field_2793.contains(slot)
            || !(this.field_2793.iterator().next() instanceof StorageContainerMenuBase.StorageUpgradeSlot)
               && !(slot instanceof StorageContainerMenuBase.StorageUpgradeSlot)
         : true;
   }

   protected boolean method_2381(double mouseX, double mouseY, int guiLeftIn, int guiTopIn, int mouseButton) {
      return super.method_2381(mouseX, mouseY, guiLeftIn, guiTopIn, mouseButton)
         && this.hasClickedOutsideOfUpgradeSlots(mouseX, mouseY)
         && this.hasClickedOutsideOfUpgradeSettings(mouseX, mouseY);
   }

   private boolean hasClickedOutsideOfUpgradeSettings(double mouseX, double mouseY) {
      return this.settingsTabControl.getTabRectangles().stream().noneMatch(r -> r.method_3318((int)mouseX, (int)mouseY));
   }

   private boolean hasClickedOutsideOfUpgradeSlots(double mouseX, double mouseY) {
      return !this.getUpgradeSlotsRectangle().map(r -> r.method_3318((int)mouseX, (int)mouseY)).orElse(false);
   }

   public Optional<class_768> getUpgradeSlotsRectangle() {
      return this.numberOfUpgradeSlots == 0
         ? Optional.empty()
         : GuiHelper.getPositiveRectangle(
            this.field_2776 - 21 - (!this.upgradeSwitches.isEmpty() ? 4 : 0),
            this.field_2800,
            21 + (!this.upgradeSwitches.isEmpty() ? 4 : 0),
            this.getUpgradeHeight()
         );
   }

   private void renderStackCount(class_332 guiGraphics, String count, int x, int y) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_22904(0.0, 0.0, 200.0);
      float scale = Math.min(1.0F, 16.0F / this.field_22793.method_1727(count));
      if (scale < 1.0F) {
         poseStack.method_22905(scale, scale, 1.0F);
      }

      guiGraphics.method_51433(
         this.field_22793,
         count,
         (int)((x + 19 - 2 - this.field_22793.method_1727(count) * scale) / scale),
         (int)((y + 6 + 3 + (1.0F / (scale * scale) - 1.0F)) / scale),
         16777215,
         true
      );
      poseStack.method_22909();
   }

   protected void method_2379() {
      class_1799 carriedStack = ((StorageContainerMenuBase)this.method_17577()).method_34255();
      if (!carriedStack.method_7960() && this.field_2794) {
         if (this.field_2790 == 2) {
            this.field_2803 = carriedStack.method_7914();
         } else {
            this.field_2803 = carriedStack.method_7947();

            for (class_1735 slot : this.field_2793) {
               class_1799 slotStack = slot.method_7677();
               int slotStackCount = slotStack.method_7960() ? 0 : slotStack.method_7947();
               int maxStackSize = slot.method_7676(carriedStack);
               int quickCraftPlaceCount = Math.min(
                  StorageContainerMenuBase.getQuickCraftPlaceCount(slot, this.field_2793.size(), this.field_2790, carriedStack) + slotStackCount, maxStackSize
               );
               this.field_2803 -= quickCraftPlaceCount - slotStackCount;
            }
         }
      }
   }

   private void renderErrorOverlay(class_332 guiGraphics) {
      ((StorageContainerMenuBase)this.field_2797)
         .getErrorUpgradeSlotChangeResult()
         .ifPresent(
            upgradeSlotChangeResult -> upgradeSlotChangeResult.getErrorMessage()
               .ifPresent(
                  overlayErrorMessage -> {
                     RenderSystem.disableDepthTest();
                     class_4587 poseStack = guiGraphics.method_51448();
                     poseStack.method_22903();
                     poseStack.method_46416(this.sophisticatedCore_getGuiLeft(), this.sophisticatedCore_getGuiTop(), 0.0F);
                     upgradeSlotChangeResult.errorUpgradeSlots()
                        .forEach(
                           slotIndex -> {
                              class_1735 upgradeSlot = ((StorageContainerMenuBase)this.field_2797)
                                 .method_7611(((StorageContainerMenuBase)this.field_2797).getFirstUpgradeSlot() + slotIndex);
                              GuiHelper.renderSlotHighlight(guiGraphics, upgradeSlot.field_7873, upgradeSlot.field_7872, 0, ERROR_SLOT_COLOR);
                           }
                        );
                     upgradeSlotChangeResult.errorInventorySlots().forEach(slotIndex -> {
                        class_1735 slot = ((StorageContainerMenuBase)this.field_2797).method_7611(slotIndex);
                        if (slot != null) {
                           GuiHelper.renderSlotHighlight(guiGraphics, slot.field_7873, slot.field_7872, 0, ERROR_SLOT_COLOR);
                        }
                     });
                     upgradeSlotChangeResult.errorInventoryParts().forEach(partIndex -> {
                        if (this.inventoryParts.size() > partIndex) {
                           UpgradeInventoryPartBase<?> inventoryPart = this.inventoryParts.get(partIndex);
                           if (inventoryPart != null) {
                              inventoryPart.renderErrorOverlay(guiGraphics);
                           }
                        }
                     });
                     poseStack.method_22909();
                     this.renderErrorMessage(guiGraphics, poseStack, overlayErrorMessage);
                  }
               )
         );
   }

   private void renderErrorMessage(class_332 guiGraphics, class_4587 matrixStack, class_2561 overlayErrorMessage) {
      matrixStack.method_22903();
      matrixStack.method_22904(this.field_22789 / 2.0F, (double)this.field_2800 + this.field_25270 + 4.0, 300.0);
      class_327 fontrenderer = class_310.method_1551().field_1772;
      int tooltipWidth = this.field_22793.method_27525(overlayErrorMessage);
      List<class_5481> wrappedTextLines = new ArrayList<>();
      int maxLineWidth = 260;
      if (tooltipWidth > maxLineWidth) {
         int wrappedTooltipWidth = 0;

         for (class_5481 line : this.field_22793.method_1728(overlayErrorMessage, maxLineWidth)) {
            int lineWidth = this.field_22793.method_30880(line);
            if (lineWidth > wrappedTooltipWidth) {
               wrappedTooltipWidth = lineWidth;
            }

            wrappedTextLines.add(line);
         }

         tooltipWidth = wrappedTooltipWidth;
      } else {
         wrappedTextLines.add(overlayErrorMessage.method_30937());
      }

      int tooltipHeight = 8;
      if (wrappedTextLines.size() > 1) {
         tooltipHeight += 2 + (wrappedTextLines.size() - 1) * 10;
      }

      Matrix4f matrix4f = matrixStack.method_23760().method_23761();
      float leftX = -tooltipWidth / 2.0F;
      GuiHelper.renderTooltipBackground(matrix4f, tooltipWidth, (int)leftX, 0, tooltipHeight, -267386864, ERROR_BORDER_COLOR, ERROR_BORDER_COLOR);
      class_4598 renderTypeBuffer = class_4597.method_22991(new class_9799(1536));
      matrixStack.method_22904(0.0, 0.0, 400.0);
      GuiHelper.writeTooltipLines(guiGraphics, wrappedTextLines, fontrenderer, leftX, 0, matrix4f, renderTypeBuffer, ERROR_TEXT_COLOR);
      renderTypeBuffer.method_22993();
      matrixStack.method_22909();
   }

   @Override
   public void renderInventorySlots(class_332 guiGraphics, int mouseX, int mouseY, boolean canShowHover) {
      this.renderStorageInventorySlots(guiGraphics, mouseX, mouseY, canShowHover);
   }

   @Override
   public boolean isMouseOverSlot(class_1735 slot, double mouseX, double mouseY) {
      return this.method_2387(slot, mouseX, mouseY);
   }

   public boolean method_2387(class_1735 slot, double mouseX, double mouseY) {
      return super.method_2387(slot, mouseX, mouseY) && this.getUpgradeSettingsControl().slotIsNotCoveredAt(slot, mouseX, mouseY);
   }

   @Override
   public int getTopY() {
      return this.sophisticatedCore_getGuiTop();
   }

   @Override
   public void drawSlotBg(class_332 guiGraphics, int visibleSlotsCount) {
      this.drawSlotBg(guiGraphics, (this.field_22789 - this.field_2792) / 2, (this.field_22790 - this.field_2779) / 2, visibleSlotsCount);
      this.drawSlotOverlays(guiGraphics);
   }

   @Override
   public int getLeftX() {
      return this.sophisticatedCore_getGuiLeft();
   }

   protected void method_37432() {
      super.method_37432();
      this.settingsTabControl.tick();
   }

   private class TransferButton extends Button {
      private final ButtonDefinition shiftDefinition;
      private final ButtonDefinition definition;

      public TransferButton(Consumer<Boolean> transferItems, ButtonDefinition shiftDefinition, ButtonDefinition definition) {
         super(new Position(StorageScreenBase.this.field_2776, StorageScreenBase.this.field_2800), definition, button -> {
            if (button == 0) {
               transferItems.accept(!class_437.method_25442());
            }
         });
         this.shiftDefinition = shiftDefinition;
         this.definition = definition;
      }

      @Override
      protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
         if (class_437.method_25442()) {
            GuiHelper.blit(guiGraphics, this.x, this.y, this.shiftDefinition.getForegroundTexture());
         } else {
            GuiHelper.blit(guiGraphics, this.x, this.y, this.definition.getForegroundTexture());
         }
      }

      @Override
      protected List<class_2561> getTooltip() {
         return class_437.method_25442() ? this.shiftDefinition.getTooltip() : this.definition.getTooltip();
      }
   }
}
