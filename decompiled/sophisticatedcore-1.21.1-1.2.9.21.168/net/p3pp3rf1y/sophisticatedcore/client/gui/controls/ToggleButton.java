package net.p3pp3rf1y.sophisticatedcore.client.gui.controls;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_6382;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.GuiHelper;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.Position;
import net.p3pp3rf1y.sophisticatedcore.client.gui.utils.TextureBlitData;

public class ToggleButton<T extends Comparable<T>> extends Button {
   private final Map<T, ToggleButton.StateData> stateData;
   private final Supplier<T> getState;

   public ToggleButton(Position position, ButtonDefinition.Toggle<T> buttonDefinition, IntConsumer onClick, Supplier<T> getState) {
      super(position, buttonDefinition, onClick);
      this.stateData = buttonDefinition.getStateData();
      this.getState = getState;
   }

   @Override
   protected void renderWidget(class_332 guiGraphics, int mouseX, int mouseY, float partialTicks) {
      ToggleButton.StateData data = this.stateData.get(this.getState.get());
      GuiHelper.blit(guiGraphics, this.x, this.y, data.getTexture());
   }

   @Override
   public void renderTooltip(class_437 screen, class_332 guiGraphics, int mouseX, int mouseY) {
      if (this.method_25405(mouseX, mouseY)) {
         ToggleButton.StateData data = this.stateData.get(this.getState.get());
         GuiHelper.renderTooltip(screen, guiGraphics, this.getTooltip(data), mouseX, mouseY);
      }
   }

   protected List<class_2561> getTooltip(ToggleButton.StateData data) {
      return data.getTooltip();
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
   }

   public static class StateData {
      private final TextureBlitData texture;
      private final List<class_2561> tooltip;

      public StateData(TextureBlitData texture, List<class_2561> tooltip) {
         this.texture = texture;
         this.tooltip = tooltip;
      }

      public StateData(TextureBlitData texture, class_2561... tooltip) {
         this.texture = texture;
         this.tooltip = Arrays.stream(tooltip).collect(Collectors.toList());
      }

      public TextureBlitData getTexture() {
         return this.texture;
      }

      public List<class_2561> getTooltip() {
         return this.tooltip;
      }
   }
}
