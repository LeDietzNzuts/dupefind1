package vectorwing.farmersdelight.client.gui;

import net.minecraft.class_1767;
import net.minecraft.class_2508;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_332;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_4730;
import net.minecraft.class_498;
import net.minecraft.class_7833;
import net.minecraft.class_837;
import net.minecraft.class_837.class_4702;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class CanvasSignEditScreen extends class_498 {
   @Nullable
   protected class_4702 signModel;
   @Nullable
   protected class_1767 dye;
   protected final boolean isFrontText;

   public CanvasSignEditScreen(class_2625 signBlockEntity, boolean isFront, boolean isTextFilteringEnabled) {
      super(signBlockEntity, isFront, isTextFilteringEnabled);
      if (signBlockEntity.method_11010().method_26204() instanceof CanvasSign canvasSign) {
         this.dye = canvasSign.getBackgroundColor();
      }

      this.isFrontText = isFront;
   }

   protected void method_25426() {
      super.method_25426();
      this.signModel = class_837.method_32157(this.field_22787.method_31974(), this.field_40426);
   }

   protected void method_45656(class_332 guiGraphics, class_2680 state) {
      if (this.signModel != null) {
         boolean flag = state.method_26204() instanceof class_2508;
         guiGraphics.method_51448().method_46416(0.0F, 31.0F, 0.0F);
         if (!this.isFrontText) {
            guiGraphics.method_51448().method_22907(class_7833.field_40716.rotationDegrees(180.0F));
         }

         guiGraphics.method_51448().method_22905(62.500004F, 62.500004F, -62.500004F);
         class_4730 material = ModAtlases.getCanvasSignMaterial(this.dye);
         class_4588 vertexconsumer = material.method_24145(guiGraphics.method_51450(), this.signModel::method_23500);
         this.signModel.field_21531.field_3665 = flag;
         this.signModel.field_27756.method_22698(guiGraphics.method_51448(), vertexconsumer, 15728880, class_4608.field_21444);
      }
   }
}
