package net.p3pp3rf1y.sophisticatedcore.settings.main;

import java.util.List;
import java.util.Optional;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.controls.ButtonBase;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Dimension;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.UV;

public class ContextButton extends ButtonBase {
   public static final TextureBlitData LEFT_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(47, 0), new Dimension(16, 18));
   public static final TextureBlitData LEFT_BUTTON_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(29, 0), new Dimension(16, 18));
   public static final TextureBlitData MIDDLE_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(49, 0), new Dimension(14, 18));
   public static final TextureBlitData MIDDLE_BUTTON_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(31, 0), new Dimension(14, 18));
   public static final TextureBlitData RIGHT_BUTTON_HOVERED_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(49, 0), new Dimension(16, 18));
   public static final TextureBlitData RIGHT_BUTTON_BACKGROUND = new TextureBlitData(GuiHelper.GUI_CONTROLS, new UV(31, 0), new Dimension(16, 18));
   private final Supplier<class_2561> getTitle;
   private final Supplier<List<class_2561>> getTooltipKey;

   protected ContextButton(Position position, IntConsumer onClick, Supplier<class_2561> getTitle, Supplier<List<class_2561>> getTooltipKey) {
      super(position, new Dimension(62, 18), onClick);
      this.getTitle = getTitle;
      this.getTooltipKey = getTooltipKey;
   }

   @Override
   protected void renderBg(class_332 guiGraphics, class_310 minecraft, int mouseX, int mouseY) {
      if (this.method_25405(mouseX, mouseY)) {
         this.renderBackground(guiGraphics, LEFT_BUTTON_HOVERED_BACKGROUND, MIDDLE_BUTTON_HOVERED_BACKGROUND, RIGHT_BUTTON_HOVERED_BACKGROUND);
      } else {
         this.renderBackground(guiGraphics, LEFT_BUTTON_BACKGROUND, MIDDLE_BUTTON_BACKGROUND, RIGHT_BUTTON_BACKGROUND);
      }
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      super.renderTooltip(screen, guiGraphics, mouseX, mouseY);
      if (this.method_25405(mouseX, mouseY)) {
         guiGraphics.method_51437(this.minecraft.field_1772, this.getTooltipKey.get(), Optional.empty(), mouseX, mouseY);
      }
   }

   private void renderBackground(
      class_332 guiGraphics,
      TextureBlitData leftButtonHoveredBackground,
      TextureBlitData middleButtonHoveredBackground,
      TextureBlitData rightButtonHoveredBackground
   ) {
      int left = this.x;
      GuiHelper.blit(guiGraphics, left, this.y, leftButtonHoveredBackground);
      left += leftButtonHoveredBackground.getWidth();
      GuiHelper.blit(guiGraphics, left, this.y, middleButtonHoveredBackground);
      left += middleButtonHoveredBackground.getWidth();
      GuiHelper.blit(guiGraphics, left, this.y, middleButtonHoveredBackground);
      left += middleButtonHoveredBackground.getWidth();
      GuiHelper.blit(guiGraphics, left, this.y, rightButtonHoveredBackground);
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      guiGraphics.method_27534(this.minecraft.field_1772, this.getTitle.get(), this.x + this.getWidth() / 2, this.y - 4 + this.getHeight() / 2, -1);
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
      narrationElementOutput.method_37034(
         class_6381.field_33788, class_2561.method_43469("gui.sophisticatedcore.narrate.context_button", new Object[]{this.getTitle.get()})
      );
      narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("gui.sophisticatedcore.narrate.context_button.usage"));
   }
}
