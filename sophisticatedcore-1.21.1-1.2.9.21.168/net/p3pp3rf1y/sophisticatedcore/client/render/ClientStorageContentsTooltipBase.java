package net.p3pp3rf1y.sophisticatedcore.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.minecraft.class_124;
import net.minecraft.class_1799;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_4587;
import net.minecraft.class_5250;
import net.minecraft.class_5684;
import net.minecraft.class_746;
import net.p3pp3rf1y.sophisticatedcore.api.IStorageWrapper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TranslationHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;
import net.p3pp3rf1y.sophisticatedcore.fluid.FluidUtil;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.CountAbbreviator;
import net.p3pp3rf1y.sophisticatedcore.util.InventoryHelper;

public abstract class ClientStorageContentsTooltipBase implements class_5684 {
   private static final int REFRESH_INTERVAL = 20;
   private static final String STORAGE_ITEM = "storage";
   protected static long lastRequestTime = 0L;
   @Nullable
   private static UUID storageUuid = null;
   private static List<IUpgradeWrapper> upgrades = new ArrayList<>();
   private static List<class_1799> sortedContents = new ArrayList<>();
   private static final List<class_2561> tooltipLines = new ArrayList<>();
   private static int height = 0;
   private static int width = 0;
   private static boolean shouldRefreshContents = true;
   private static final TextureBlitData UPGRADE_ON = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(4, 128), Dimension.RECTANGLE_4_10);
   private static final TextureBlitData UPGRADE_OFF = new TextureBlitData(GuiHelper.ICONS, Dimension.SQUARE_256, new UV(0, 128), Dimension.RECTANGLE_4_10);
   private static final int MAX_STACKS_ON_LINE = 9;
   private static final int DEFAULT_STACK_WIDTH = 18;
   private static final int COUNT_PADDING = 2;

   public static void refreshContents() {
      shouldRefreshContents = true;
   }

   private void initContents(class_746 player, IStorageWrapper wrapper) {
      UUID newUuid = wrapper.getContentsUuid().orElse(null);
      if (storageUuid == null && newUuid != null || storageUuid != null && !storageUuid.equals(newUuid)) {
         this.setLastRequestTime(0L);
         storageUuid = newUuid;
         this.setShouldRefreshContents(true);
      }

      if (storageUuid != null) {
         this.requestContents(player, wrapper);
      }

      this.refreshContents(wrapper);
   }

   protected void setLastRequestTime(long lastRequestTime) {
      ClientStorageContentsTooltipBase.lastRequestTime = lastRequestTime;
   }

   protected long getLastRequestTime() {
      return lastRequestTime;
   }

   private void requestContents(class_746 player, IStorageWrapper wrapper) {
      if (this.getLastRequestTime() + 20L < player.method_37908().method_8510()) {
         this.setLastRequestTime(player.method_37908().method_8510());
         wrapper.getContentsUuid().ifPresent(this::sendInventorySyncRequest);
      }
   }

   protected abstract void sendInventorySyncRequest(UUID var1);

   private void refreshContents(IStorageWrapper wrapper) {
      if (this.shouldRefreshContents()) {
         this.setShouldRefreshContents(false);
         sortedContents.clear();
         upgrades.clear();
         tooltipLines.clear();
         if (storageUuid != null) {
            wrapper.onContentsNbtUpdated();
            sortedContents = InventoryHelper.getCompactedStacksSortedByCount(wrapper.getInventoryHandler());
            upgrades = new ArrayList<>(wrapper.getUpgradeHandler().getSlotWrappers().values());
            this.addMultiplierTooltip(wrapper);
            this.addFluidTooltip(wrapper);
            this.addEnergyTooltip(wrapper);
         }

         if (upgrades.isEmpty() && sortedContents.isEmpty()) {
            tooltipLines.add(class_2561.method_43471(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".empty").method_27692(class_124.field_1054));
         }

         this.calculateHeight();
         this.calculateWidth();
      }
   }

   protected void setShouldRefreshContents(boolean shouldRefreshContents) {
      ClientStorageContentsTooltipBase.shouldRefreshContents = shouldRefreshContents;
   }

   protected boolean shouldRefreshContents() {
      return shouldRefreshContents;
   }

   private void calculateWidth() {
      int upgradesWidth = this.calculateUpgradesWidth();
      int contentsWidth = this.calculateContentsWidth();
      int tooltipContentsWidth = this.calculateTooltipLinesWidth();
      int stacksWidth = Math.max(upgradesWidth, contentsWidth);
      width = Math.max(stacksWidth, tooltipContentsWidth);
   }

   private int calculateTooltipLinesWidth() {
      return tooltipLines.stream().map(this::getTooltipWidth).max(Comparator.naturalOrder()).orElse(0);
   }

   private int calculateUpgradesWidth() {
      int upgradesWidth = 0;

      for (IUpgradeWrapper upgradeWrapper : upgrades) {
         upgradesWidth += (upgradeWrapper.canBeDisabled() ? 4 : 0) + 18;
      }

      return upgradesWidth;
   }

   private int calculateContentsWidth() {
      class_327 fontRenderer = class_310.method_1551().field_1772;
      int contentsWidth = 0;

      for (int i = 0; i < sortedContents.size() && i < 9; i++) {
         int countWidth = this.getStackCountWidth(fontRenderer, sortedContents.get(i));
         contentsWidth += Math.max(countWidth, 18);
      }

      return contentsWidth;
   }

   private int getStackCountWidth(class_327 fontRenderer, class_1799 stack) {
      return fontRenderer.method_1727(CountAbbreviator.abbreviate(stack.method_7947())) + 2;
   }

   private int getTooltipWidth(class_2561 component) {
      return class_310.method_1551().field_1772.method_30880(component.method_30937());
   }

   private void addMultiplierTooltip(IStorageWrapper wrapper) {
      double multiplier = wrapper.getInventoryHandler().getStackSizeMultiplier();
      if (multiplier > 1.0) {
         DecimalFormat df = new DecimalFormat("0.###");
         tooltipLines.add(
            class_2561.method_43469(
                  TranslationHelper.INSTANCE.translItemTooltip("storage") + ".stack_multiplier",
                  new Object[]{class_2561.method_43470(df.format(multiplier)).method_27692(class_124.field_1068)}
               )
               .method_27692(class_124.field_1060)
         );
      }
   }

   private void addEnergyTooltip(IStorageWrapper wrapper) {
      wrapper.getEnergyStorage()
         .ifPresent(
            energyStorage -> tooltipLines.add(
               class_2561.method_43469(
                     this.getEnergyTooltipTranslation(),
                     new Object[]{class_2561.method_43470(CountAbbreviator.abbreviate((int)energyStorage.getAmount())).method_27692(class_124.field_1068)}
                  )
                  .method_27692(class_124.field_1061)
            )
         );
   }

   protected String getEnergyTooltipTranslation() {
      return TranslationHelper.INSTANCE.translItemTooltip("storage") + ".energy";
   }

   private void addFluidTooltip(IStorageWrapper wrapper) {
      wrapper.getFluidHandler()
         .ifPresent(
            fluidHandler -> {
               for (StorageView<FluidVariant> view : fluidHandler) {
                  if (view.isResourceBlank()) {
                     tooltipLines.add(class_2561.method_43471(this.getEmptyFluidTooltipTranslation()).method_27692(class_124.field_1078));
                  } else {
                     tooltipLines.add(
                        class_2561.method_43469(
                           this.getFluidTooltipTranslation(),
                           new Object[]{
                              class_2561.method_43470(CountAbbreviator.abbreviate(FluidUtil.toBuckets(view.getAmount()))).method_27692(class_124.field_1068),
                              ((class_5250)FluidVariantAttributes.getName((FluidVariant)view.getResource())).method_27692(class_124.field_1078)
                           }
                        )
                     );
                  }
               }
            }
         );
   }

   protected String getFluidTooltipTranslation() {
      return TranslationHelper.INSTANCE.translItemTooltip("storage") + ".fluid";
   }

   protected String getEmptyFluidTooltipTranslation() {
      return TranslationHelper.INSTANCE.translItemTooltip("storage") + ".fluid_empty";
   }

   private void calculateHeight() {
      int upgradesHeight = upgrades.isEmpty() ? 0 : 32;
      int inventoryHeight = sortedContents.isEmpty() ? 0 : 12 + (1 + (sortedContents.size() - 1) / 9) * 20;
      int totalHeight = upgradesHeight + inventoryHeight + tooltipLines.size() * 10;
      height = totalHeight > 0 ? totalHeight : 12;
   }

   public int method_32664(class_327 font) {
      return width;
   }

   public int method_32661() {
      return height;
   }

   protected void renderTooltip(IStorageWrapper wrapper, class_327 font, int leftX, int topY, class_332 guiGraphics) {
      class_310 minecraft = class_310.method_1551();
      class_746 player = minecraft.field_1724;
      if (player != null) {
         this.initContents(player, wrapper);
         this.renderComponent(font, leftX, topY, guiGraphics, minecraft);
      }
   }

   private void renderComponent(class_327 font, int leftX, int topY, class_332 guiGraphics, class_310 minecraft) {
      for (class_2561 tooltipLine : tooltipLines) {
         topY = this.renderTooltipLine(guiGraphics, leftX, topY, font, tooltipLine);
      }

      this.renderContentsTooltip(minecraft, font, leftX, topY, guiGraphics);
   }

   private void renderContentsTooltip(class_310 minecraft, class_327 font, int leftX, int topY, class_332 guiGraphics) {
      if (!upgrades.isEmpty()) {
         topY = this.renderTooltipLine(
            guiGraphics,
            leftX,
            topY,
            font,
            class_2561.method_43471(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".upgrades").method_27692(class_124.field_1054)
         );
         topY = this.renderUpgrades(guiGraphics, leftX, topY);
      }

      if (!sortedContents.isEmpty()) {
         topY = this.renderTooltipLine(
            guiGraphics,
            leftX,
            topY,
            font,
            class_2561.method_43471(TranslationHelper.INSTANCE.translItemTooltip("storage") + ".inventory").method_27692(class_124.field_1054)
         );
         this.renderContents(minecraft, leftX, topY, guiGraphics, font);
      }
   }

   private int renderTooltipLine(class_332 guiGraphics, int leftX, int topY, class_327 font, class_2561 tooltip) {
      class_4587 poseStack = guiGraphics.method_51448();
      poseStack.method_22903();
      poseStack.method_22904(0.0, 0.0, 200.0);
      guiGraphics.method_27535(font, tooltip, leftX, topY, 16777215);
      poseStack.method_22904(0.0, 0.0, -200.0);
      poseStack.method_22909();
      return topY + 10;
   }

   private int renderUpgrades(class_332 guiGraphics, int leftX, int topY) {
      int x = leftX;

      for (IUpgradeWrapper upgradeWrapper : upgrades) {
         if (upgradeWrapper.canBeDisabled()) {
            RenderSystem.disableDepthTest();
            GuiHelper.blit(guiGraphics, x, topY + 3, upgradeWrapper.isEnabled() ? UPGRADE_ON : UPGRADE_OFF);
            x += 4;
         }

         guiGraphics.method_51427(upgradeWrapper.getUpgradeStack(), x, topY);
         x += 18;
      }

      return topY + 20;
   }

   private void renderContents(class_310 minecraft, int leftX, int topY, class_332 guiGraphics, class_327 font) {
      int x = leftX;

      for (int i = 0; i < sortedContents.size(); i++) {
         int y = topY + i / 9 * 20;
         if (i % 9 == 0) {
            x = leftX;
         }

         class_1799 stack = sortedContents.get(i);
         int stackWidth = Math.max(this.getStackCountWidth(minecraft.field_1772, stack), 18);
         int xOffset = stackWidth - 18;
         guiGraphics.method_51427(stack, x + xOffset, y);
         guiGraphics.method_51432(font, stack, x + xOffset, y, CountAbbreviator.abbreviate(stack.method_7947()));
         x += stackWidth;
      }
   }
}
