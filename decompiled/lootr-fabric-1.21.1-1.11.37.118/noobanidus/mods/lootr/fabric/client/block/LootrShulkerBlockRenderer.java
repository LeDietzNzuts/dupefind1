package noobanidus.mods.lootr.fabric.client.block;

import java.util.UUID;
import net.minecraft.class_1921;
import net.minecraft.class_2350;
import net.minecraft.class_2480;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_5602;
import net.minecraft.class_602;
import net.minecraft.class_630;
import net.minecraft.class_827;
import net.minecraft.class_5614.class_5615;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.block.entity.LootrShulkerBlockEntity;

public class LootrShulkerBlockRenderer implements class_827<LootrShulkerBlockEntity> {
   public static final class_4730 MATERIAL = new class_4730(class_4722.field_21704, LootrAPI.rl("shulker"));
   public static final class_4730 MATERIAL2 = new class_4730(class_4722.field_21704, LootrAPI.rl("shulker_opened"));
   public static final class_4730 MATERIAL3 = new class_4730(class_4722.field_21704, LootrAPI.rl("old_shulker"));
   public static final class_4730 MATERIAL4 = new class_4730(class_4722.field_21704, LootrAPI.rl("old_shulker_opened"));
   private final class_602<?> model;
   private UUID playerId;

   public LootrShulkerBlockRenderer(class_5615 context) {
      this.model = new class_602(context.method_32140(class_5602.field_27596));
   }

   protected class_4730 getMaterial(LootrShulkerBlockEntity blockEntity) {
      if (LootrAPI.isVanillaTextures()) {
         return class_4722.field_21710;
      } else if (class_310.method_1551().field_1724 == null) {
         return MATERIAL2;
      } else if (!blockEntity.hasClientOpened(class_310.method_1551().field_1724.method_5667())) {
         return LootrAPI.isOldTextures() ? MATERIAL3 : MATERIAL;
      } else {
         return LootrAPI.isOldTextures() ? MATERIAL4 : MATERIAL2;
      }
   }

   public void render(
      LootrShulkerBlockEntity pBlockEntity, float pPartialTicks, class_4587 pMatrixStack, class_4597 pBuffer, int pCombinedLight, int pCombinedOverlay
   ) {
      class_2350 direction = class_2350.field_11036;
      if (pBlockEntity.method_11002()) {
         class_2680 blockstate = pBlockEntity.method_10997().method_8320(pBlockEntity.method_11016());
         if (blockstate.method_26204() instanceof class_2480) {
            direction = (class_2350)blockstate.method_11654(class_2480.field_11496);
         }
      }

      class_4730 material = this.getMaterial(pBlockEntity);
      pMatrixStack.method_22903();
      pMatrixStack.method_22904(0.5, 0.5, 0.5);
      pMatrixStack.method_22905(0.9995F, 0.9995F, 0.9995F);
      pMatrixStack.method_22907(direction.method_23224());
      pMatrixStack.method_22905(1.0F, -1.0F, -1.0F);
      pMatrixStack.method_22904(0.0, -1.0, 0.0);
      class_630 modelpart = this.model.method_2829();
      modelpart.method_2851(0.0F, 24.0F - pBlockEntity.getProgress(pPartialTicks) * 0.5F * 16.0F, 0.0F);
      modelpart.field_3675 = 270.0F * pBlockEntity.getProgress(pPartialTicks) * (float) (Math.PI / 180.0);
      class_4588 vertexconsumer = material.method_24145(pBuffer, class_1921::method_23578);
      this.model.method_60879(pMatrixStack, vertexconsumer, pCombinedLight, pCombinedOverlay);
      pMatrixStack.method_22909();
   }
}
