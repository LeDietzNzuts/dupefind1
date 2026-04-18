package vectorwing.farmersdelight.client.renderer;

import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1767;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2478;
import net.minecraft.class_2508;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_3532;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4719;
import net.minecraft.class_4730;
import net.minecraft.class_5481;
import net.minecraft.class_5602;
import net.minecraft.class_746;
import net.minecraft.class_7833;
import net.minecraft.class_8242;
import net.minecraft.class_837;
import net.minecraft.class_327.class_6415;
import net.minecraft.class_5253.class_5254;
import net.minecraft.class_5614.class_5615;
import net.minecraft.class_837.class_4702;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.block.state.CanvasSign;
import vectorwing.farmersdelight.common.registry.ModAtlases;

public class CanvasSignRenderer extends class_837 {
   public static final class_243 TEXT_OFFSET = new class_243(0.0, 0.33333334F, 0.046666667F);
   private static final int OUTLINE_RENDER_DISTANCE = class_3532.method_34954(16);
   private final class_4702 signModel;
   private final class_327 font;

   public CanvasSignRenderer(class_5615 context) {
      super(context);
      this.signModel = new class_4702(context.method_32140(class_5602.method_32078(class_4719.field_21677)));
      this.font = context.method_32143();
   }

   public void method_23083(class_2625 blockEntity, float partialTick, class_4587 poseStack, class_4597 bufferSource, int packedLight, int packedOverlay) {
      class_2680 state = blockEntity.method_11010();
      class_2478 block = (class_2478)state.method_26204();
      class_4702 model = this.signModel;
      model.field_21531.field_3665 = block instanceof class_2508;
      class_1767 dye = null;
      if (block instanceof CanvasSign canvasSign) {
         dye = canvasSign.getBackgroundColor();
      }

      this.renderSignWithText(blockEntity, poseStack, bufferSource, packedLight, packedOverlay, state, block, dye, model);
   }

   protected void renderSignWithText(
      class_2625 signBlockEntity,
      class_4587 poseStack,
      class_4597 bufferSource,
      int packedLight,
      int packedOverlay,
      class_2680 state,
      class_2478 block,
      @Nullable class_1767 dye,
      class_3879 model
   ) {
      poseStack.method_22903();
      this.method_49918(poseStack, -block.method_49814(state), state);
      this.renderSign(poseStack, bufferSource, packedLight, packedOverlay, dye, model);
      this.method_45798(
         signBlockEntity.method_11016(),
         signBlockEntity.method_49853(),
         poseStack,
         bufferSource,
         packedLight,
         signBlockEntity.method_45469(),
         signBlockEntity.method_45470(),
         true
      );
      this.method_45798(
         signBlockEntity.method_11016(),
         signBlockEntity.method_49854(),
         poseStack,
         bufferSource,
         packedLight,
         signBlockEntity.method_45469(),
         signBlockEntity.method_45470(),
         false
      );
      poseStack.method_22909();
   }

   protected void method_49918(class_4587 poseStack, float angle, class_2680 state) {
      poseStack.method_46416(0.5F, 0.75F * this.method_51272(), 0.5F);
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(angle));
      if (!(state.method_26204() instanceof class_2508)) {
         poseStack.method_46416(0.0F, -0.3125F, -0.4375F);
      }
   }

   protected void renderSign(class_4587 poseStack, class_4597 bufferSource, int packedLight, int packedOverlay, @Nullable class_1767 dye, class_3879 model) {
      poseStack.method_22903();
      float rootScale = this.method_51272();
      poseStack.method_22905(rootScale, -rootScale, -rootScale);
      class_4730 material = this.getCanvasSignMaterial(dye);
      class_4588 vertexConsumer = material.method_24145(bufferSource, model::method_23500);
      this.method_45793(poseStack, packedLight, packedOverlay, model, vertexConsumer);
      poseStack.method_22909();
   }

   protected void method_45793(class_4587 poseStack, int packedLight, int packedOverlay, class_3879 model, class_4588 vertexConsumer) {
      class_4702 signModel = (class_4702)model;
      signModel.field_27756.method_22698(poseStack, vertexConsumer, packedLight, packedOverlay);
   }

   protected void method_45798(
      class_2338 pos,
      class_8242 text,
      class_4587 poseStack,
      class_4597 bufferSource,
      int packedLight,
      int textLineHeight,
      int maxTextLineWidth,
      boolean isFrontText
   ) {
      poseStack.method_22903();
      this.translateSignText(poseStack, isFrontText, this.method_45790());
      class_5481[] formattedCharSequenceList = text.method_49868(class_310.method_1551().method_33883(), component -> {
         List<class_5481> list = this.font.method_1728(component, maxTextLineWidth);
         return list.isEmpty() ? class_5481.field_26385 : list.get(0);
      });
      int darkColor;
      int baseColor;
      boolean hasOutline;
      int light;
      if (text.method_49856()) {
         darkColor = getDarkColor(text, true);
         baseColor = text.method_49872().method_16357();
         hasOutline = isOutlineVisible(pos, baseColor);
         light = 15728880;
      } else {
         darkColor = getDarkColor(text, false);
         baseColor = darkColor;
         hasOutline = false;
         light = packedLight;
      }

      int verticalOffset = 2 * textLineHeight + this.getCustomVerticalOffset();

      for (int i = 0; i < 4; i++) {
         class_5481 formattedCharSequence = formattedCharSequenceList[i];
         float x = -this.font.method_30880(formattedCharSequence) / 2;
         float y = i * textLineHeight - verticalOffset;
         if (hasOutline) {
            this.font.method_37296(formattedCharSequence, x, y, baseColor, darkColor, poseStack.method_23760().method_23761(), bufferSource, light);
         } else {
            this.font
               .method_22942(
                  formattedCharSequence, x, y, baseColor, false, poseStack.method_23760().method_23761(), bufferSource, class_6415.field_33995, 0, light
               );
         }
      }

      poseStack.method_22909();
   }

   private void translateSignText(class_4587 poseStack, boolean isFrontText, class_243 pos) {
      if (!isFrontText) {
         poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F));
      }

      float textScale = 0.015625F * this.method_51273();
      poseStack.method_22904(pos.field_1352, pos.field_1351, pos.field_1350);
      poseStack.method_22905(textScale, -textScale, textScale);
   }

   public static boolean isOutlineVisible(class_2338 pos, int textColor) {
      if (textColor == class_1767.field_7963.method_16357()) {
         return true;
      } else {
         class_310 minecraft = class_310.method_1551();
         class_746 localPlayer = minecraft.field_1724;
         if (localPlayer != null && minecraft.field_1690.method_31044().method_31034() && localPlayer.method_31550()) {
            return true;
         } else {
            class_1297 entity = minecraft.method_1560();
            return entity != null && entity.method_5707(class_243.method_24953(pos)) < OUTLINE_RENDER_DISTANCE;
         }
      }
   }

   protected static int getDarkColor(class_8242 text, boolean isOutlineVisible) {
      int textColor = text.method_49872().method_16357();
      if (textColor == class_1767.field_7963.method_16357() && text.method_49856()) {
         return -988212;
      } else {
         double brightness = isOutlineVisible ? 0.4 : 0.6;
         int red = (int)(class_5254.method_27765(textColor) * brightness);
         int green = (int)(class_5254.method_27766(textColor) * brightness);
         int blue = (int)(class_5254.method_27767(textColor) * brightness);
         return class_5254.method_27764(0, red, green, blue);
      }
   }

   class_243 method_45790() {
      return TEXT_OFFSET;
   }

   public int getCustomVerticalOffset() {
      return -1;
   }

   public class_4730 getCanvasSignMaterial(@Nullable class_1767 dyeColor) {
      return ModAtlases.getCanvasSignMaterial(dyeColor);
   }
}
