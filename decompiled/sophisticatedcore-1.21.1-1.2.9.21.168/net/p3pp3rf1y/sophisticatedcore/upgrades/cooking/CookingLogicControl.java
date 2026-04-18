package net.p3pp3rf1y.sophisticatedcore.upgrades.cooking;

import java.util.List;
import net.minecraft.class_1735;
import net.minecraft.class_1874;
import net.minecraft.class_1937;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.CompositeWidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ProgressBar;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.WidgetBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;

public class CookingLogicControl<T extends class_1874> extends CompositeWidgetBase<WidgetBase> {
   private static final TextureBlitData FURNACE_BACKGROUND = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(29, 202), new Dimension(68, 54)
   );
   private static final TextureBlitData COOK_PROGRESS = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(100, 239), new Dimension(22, 16)
   );
   private static final TextureBlitData BURN_PROGRESS = new TextureBlitData(
      GuiHelper.GUI_CONTROLS, Dimension.SQUARE_256, new UV(99, 225), new Dimension(14, 14)
   );
   private final CookingLogicContainer<T> cookingLogicContainer;

   public CookingLogicControl(Position position, CookingLogicContainer<T> cookingLogicContainer) {
      super(position, new Dimension(68, 54));
      this.cookingLogicContainer = cookingLogicContainer;
      this.addChild(new ProgressBar(new Position(this.x + 19, this.y + 18), COOK_PROGRESS, this::getCookProgress, ProgressBar.ProgressDirection.LEFT_RIGHT));
      this.addChild(new ProgressBar(new Position(this.x + 1, this.y + 20), BURN_PROGRESS, this::getBurnProgress, ProgressBar.ProgressDirection.BOTTOM_UP));
   }

   private float getBurnProgress() {
      return this.cookingLogicContainer.isBurning(class_310.method_1551().field_1687)
         ? this.getProgress(this.cookingLogicContainer.getBurnTimeFinish(), this.cookingLogicContainer.getBurnTimeTotal())
         : 0.0F;
   }

   private float getCookProgress() {
      return this.cookingLogicContainer.isCooking()
         ? this.getProgress(this.cookingLogicContainer.getCookTimeFinish(), this.cookingLogicContainer.getCookTimeTotal())
         : 0.0F;
   }

   private float getProgress(long finishTime, int timeTotal) {
      class_1937 level = class_310.method_1551().field_1687;
      return level == null ? 0.0F : 1.0F - (float)Math.max(finishTime - level.method_8510(), 0L) / timeTotal;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      GuiHelper.blit(guiGraphics, this.x, this.y, FURNACE_BACKGROUND);
   }

   public void moveSlotsToView(int screenGuiLeft, int screenGuiTop) {
      List<class_1735> smeltingSlots = this.cookingLogicContainer.getCookingSlots();
      this.positionSlot(smeltingSlots.get(0), screenGuiLeft, screenGuiTop, 1, 1);
      this.positionSlot(smeltingSlots.get(2), screenGuiLeft, screenGuiTop, 47, 19);
      this.positionSlot(smeltingSlots.get(1), screenGuiLeft, screenGuiTop, 1, 37);
   }

   private void positionSlot(class_1735 slot, int screenGuiLeft, int screenGuiTop, int xOffset, int yOffset) {
      slot.field_7873 = this.x - screenGuiLeft + xOffset;
      slot.field_7872 = this.y - screenGuiTop + yOffset;
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }
}
