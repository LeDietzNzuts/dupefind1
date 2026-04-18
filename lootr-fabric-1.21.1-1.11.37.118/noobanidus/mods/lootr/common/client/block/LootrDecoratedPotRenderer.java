package noobanidus.mods.lootr.common.client.block;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_2960;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_5601;
import net.minecraft.class_5602;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;
import net.minecraft.class_7833;
import net.minecraft.class_8173;
import net.minecraft.class_827;
import net.minecraft.class_5614.class_5615;
import net.minecraft.class_8172.class_8837;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import noobanidus.mods.lootr.common.block.entity.LootrDecoratedPotBlockEntity;
import noobanidus.mods.lootr.common.client.ClientHooks;
import noobanidus.mods.lootr.common.integration.sherdsapi.SherdsIntegration;

public class LootrDecoratedPotRenderer implements class_827<LootrDecoratedPotBlockEntity> {
   public static final class_2960 DECORATED_POT_SHEET = class_2960.method_60656("textures/atlas/decorated_pot.png");
   private static final class_4730 DECORATED_POT = new class_4730(DECORATED_POT_SHEET, LootrAPI.rl("entity/loot_pot"));
   private static final class_4730 DECORATED_POT_OPENED = new class_4730(DECORATED_POT_SHEET, LootrAPI.rl("entity/loot_pot_open"));
   public static final class_5601 OPEN_POT_LAYER = new class_5601(LootrConstants.DECORATED_POT, "main");
   private final class_630 neck;
   private final class_630 frontSide;
   private final class_630 backSide;
   private final class_630 leftSide;
   private final class_630 rightSide;
   private final class_630 top;
   private final class_630 bottom;
   private final class_630 open;
   private final class_630 sherds;
   private static final Map<class_2960, class_4730> cachedMaterials = new HashMap<>();

   public LootrDecoratedPotRenderer(class_5615 context) {
      class_630 modelPart = context.method_32140(class_5602.field_42882);
      this.neck = modelPart.method_32086("neck");
      this.top = modelPart.method_32086("top");
      this.bottom = modelPart.method_32086("bottom");
      class_630 modelPart2 = context.method_32140(class_5602.field_42883);
      this.frontSide = modelPart2.method_32086("front");
      this.backSide = modelPart2.method_32086("back");
      this.leftSide = modelPart2.method_32086("left");
      this.rightSide = modelPart2.method_32086("right");
      class_630 modelPart3 = context.method_32140(OPEN_POT_LAYER);
      this.open = modelPart3.method_32086("open");
      this.sherds = modelPart3.method_32086("sherds");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 sherds = partdefinition.method_32117("sherds", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      sherds.method_32117(
         "angled_sherd1_r1",
         class_5606.method_32108().method_32101(17, 21).method_32098(-0.5F, -0.5F, -4.5F, 5.0F, 1.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.5F, -3.5F, 1.5F, 0.0F, 0.0F, 0.829F)
      );
      sherds.method_32117(
         "sherd2_r1",
         class_5606.method_32108().method_32101(14, 19).method_32098(-4.5F, -0.5F, -4.5F, 9.0F, 1.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-1.0F, -1.5F, 0.5F, 0.0F, 0.1309F, 0.0F)
      );
      sherds.method_32117(
         "sherd1_r1",
         class_5606.method_32108().method_32101(4, 16).method_32098(-4.5F, -0.5F, -3.5F, 10.0F, 1.0F, 11.0F, new class_5605(0.0F)),
         class_5603.method_32091(-1.5F, -0.5F, -1.5F, 0.0F, -0.3054F, 0.0F)
      );
      partdefinition.method_32117(
         "open",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(-4.0F, -0.5F, -4.0F, 8.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 5)
            .method_32098(-2.8257F, 2.4924F, -3.0F, 6.0F, 1.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-1.75F, -5.0F, 0.0F, 0.0F, -0.1309F, -0.0873F)
      );
      return class_5607.method_32110(meshdefinition, 64, 32);
   }

   private static class_4730 getSideMaterial(class_1799 item) {
      if (!item.method_7960()) {
         class_2960 customSide = SherdsIntegration.getCustomSideTexture(item);
         if (customSide != null) {
            return cachedMaterials.computeIfAbsent(customSide, rl -> new class_4730(DECORATED_POT_SHEET, rl.method_45138("entity/decorated_pot/")));
         }

         class_4730 material = class_4722.method_49341(class_8173.method_49206(item.method_7909()));
         if (material != null) {
            return material;
         }
      }

      return class_4722.field_51915;
   }

   public void render(LootrDecoratedPotBlockEntity decoratedPotBlockEntity, float f, class_4587 poseStack, class_4597 multiBufferSource, int i, int j) {
      class_1657 player = ClientHooks.getPlayer();
      if (player != null) {
         boolean opened = decoratedPotBlockEntity.hasClientOpened(player.method_5667());
         poseStack.method_22903();
         class_2350 direction = decoratedPotBlockEntity.getDirection();
         poseStack.method_22904(0.5, 0.0, 0.5);
         poseStack.method_22907(class_7833.field_40716.rotationDegrees(180.0F - direction.method_10144()));
         if (!opened) {
            poseStack.method_22904(-0.5, 0.0, -0.5);
            class_8837 wobbleStyle = decoratedPotBlockEntity.lastWobbleStyle;
            if (wobbleStyle != null && decoratedPotBlockEntity.method_10997() != null) {
               float g = ((float)(decoratedPotBlockEntity.method_10997().method_8510() - decoratedPotBlockEntity.wobbleStartedAtTick) + f)
                  / wobbleStyle.field_46666;
               if (g >= 0.0F && g <= 1.0F) {
                  if (wobbleStyle == class_8837.field_46664) {
                     float h = 0.015625F;
                     float k = g * (float) (Math.PI * 2);
                     float l = -1.5F * (class_3532.method_15362(k) + 0.5F) * class_3532.method_15374(k / 2.0F);
                     poseStack.method_49278(class_7833.field_40714.rotation(l * 0.015625F), 0.5F, 0.0F, 0.5F);
                     float m = class_3532.method_15374(k);
                     poseStack.method_49278(class_7833.field_40718.rotation(m * 0.015625F), 0.5F, 0.0F, 0.5F);
                  } else {
                     float h = class_3532.method_15374(-g * 3.0F * (float) Math.PI) * 0.125F;
                     float k = 1.0F - g;
                     poseStack.method_49278(class_7833.field_40716.rotation(h * k), 0.5F, 0.0F, 0.5F);
                  }
               }
            }

            class_4588 vertexConsumer = DECORATED_POT.method_24145(multiBufferSource, class_1921::method_23572);
            this.neck.method_22698(poseStack, vertexConsumer, i, j);
            this.top.method_22698(poseStack, vertexConsumer, i, j);
            this.bottom.method_22698(poseStack, vertexConsumer, i, j);
            PotDecorationsAdapter potDecorations = decoratedPotBlockEntity.getDecorations();
            this.renderSide(this.frontSide, poseStack, multiBufferSource, i, j, getSideMaterial(potDecorations.front()));
            this.renderSide(this.backSide, poseStack, multiBufferSource, i, j, getSideMaterial(potDecorations.back()));
            this.renderSide(this.leftSide, poseStack, multiBufferSource, i, j, getSideMaterial(potDecorations.left()));
            this.renderSide(this.rightSide, poseStack, multiBufferSource, i, j, getSideMaterial(potDecorations.right()));
         } else {
            poseStack.method_22905(1.0F, -1.0F, -1.0F);
            class_4588 vertexConsumer = DECORATED_POT_OPENED.method_24145(multiBufferSource, class_1921::method_23572);
            this.open.method_22698(poseStack, vertexConsumer, i, j);
            this.sherds.method_22698(poseStack, vertexConsumer, i, j);
         }

         poseStack.method_22909();
      }
   }

   private void renderSide(class_630 modelPart, class_4587 poseStack, class_4597 multiBufferSource, int i, int j, class_4730 material) {
      modelPart.method_22698(poseStack, material.method_24145(multiBufferSource, class_1921::method_23572), i, j);
   }
}
