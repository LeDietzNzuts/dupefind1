package noobanidus.mods.lootr.fabric.client.block;

import net.minecraft.class_1921;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4597;
import net.minecraft.class_4722;
import net.minecraft.class_4730;
import net.minecraft.class_5602;
import net.minecraft.class_630;
import net.minecraft.class_7833;
import net.minecraft.class_826;
import net.minecraft.class_5614.class_5615;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.block.entity.LootrChestBlockEntity;

public class LootrChestBlockRenderer<T extends LootrChestBlockEntity & ILootrBlockEntity> extends class_826<T> {
   public static final class_4730 MATERIAL = new class_4730(class_4722.field_21709, LootrAPI.rl("chest"));
   public static final class_4730 MATERIAL2 = new class_4730(class_4722.field_21709, LootrAPI.rl("chest_opened"));
   public static final class_4730 MATERIAL3 = new class_4730(class_4722.field_21709, LootrAPI.rl("chest_trapped"));
   public static final class_4730 MATERIAL4 = new class_4730(class_4722.field_21709, LootrAPI.rl("chest_trapped_opened"));
   public static final class_4730 OLD_MATERIAL = new class_4730(class_4722.field_21709, LootrAPI.rl("old_chest"));
   public static final class_4730 OLD_MATERIAL2 = new class_4730(class_4722.field_21709, LootrAPI.rl("old_chest_opened"));
   public static final class_4730 OLD_MATERIAL3 = new class_4730(class_4722.field_21709, LootrAPI.rl("old_chest_trapped"));
   public static final class_4730 OLD_MATERIAL4 = new class_4730(class_4722.field_21709, LootrAPI.rl("old_chest_trapped_opened"));
   private final class_630 lid;
   private final class_630 bottom;
   private final class_630 lock;

   public LootrChestBlockRenderer(class_5615 context) {
      super(context);
      class_630 modelPart = context.method_32140(class_5602.field_27689);
      this.bottom = modelPart.method_32086("bottom");
      this.lid = modelPart.method_32086("lid");
      this.lock = modelPart.method_32086("lock");
   }

   public void render(T blockEntity, float partialTick, class_4587 poseStack, class_4597 bufferSource, int packedLight, int packedOverlay) {
      class_1937 level = blockEntity.method_10997();
      class_2680 blockState = level != null
         ? blockEntity.method_11010()
         : (class_2680)class_2246.field_10034.method_9564().method_11657(class_2281.field_10768, class_2350.field_11035);
      poseStack.method_22903();
      float f = ((class_2350)blockState.method_11654(class_2281.field_10768)).method_10144();
      poseStack.method_22904(0.5, 0.5, 0.5);
      poseStack.method_22907(class_7833.field_40716.rotationDegrees(-f));
      poseStack.method_22904(-0.5, -0.5, -0.5);
      float g = blockEntity.method_11274(partialTick);
      g = 1.0F - g;
      g = 1.0F - g * g * g;
      class_4730 material = this.getMaterial(blockEntity);
      class_4588 vertexConsumer = material.method_24145(bufferSource, class_1921::method_23576);
      this.render(poseStack, vertexConsumer, this.lid, this.lock, this.bottom, g, packedLight, packedOverlay);
      poseStack.method_22909();
   }

   private void render(
      class_4587 poseStack,
      class_4588 consumer,
      class_630 lidPart,
      class_630 lockPart,
      class_630 bottomPart,
      float lidAngle,
      int packedLight,
      int packedOverlay
   ) {
      lidPart.field_3654 = -(lidAngle * (float) (Math.PI / 2));
      lockPart.field_3654 = lidPart.field_3654;
      lidPart.method_22698(poseStack, consumer, packedLight, packedOverlay);
      lockPart.method_22698(poseStack, consumer, packedLight, packedOverlay);
      bottomPart.method_22698(poseStack, consumer, packedLight, packedOverlay);
   }

   protected class_4730 getMaterial(T blockEntity) {
      if (LootrAPI.isVanillaTextures()) {
         return class_4722.method_24062(blockEntity, class_2745.field_12569, false);
      } else if (class_310.method_1551().field_1724 == null) {
         return LootrAPI.isOldTextures() ? OLD_MATERIAL2 : MATERIAL2;
      } else {
         boolean trapped = blockEntity.method_11017().method_53254().method_40220(LootrTags.BlockEntity.TRAPPED);
         if (blockEntity.hasClientOpened(class_310.method_1551().field_1724.method_5667())) {
            if (LootrAPI.isOldTextures()) {
               return trapped ? OLD_MATERIAL4 : OLD_MATERIAL2;
            } else {
               return trapped ? MATERIAL4 : MATERIAL2;
            }
         } else if (LootrAPI.isOldTextures()) {
            return trapped ? OLD_MATERIAL3 : OLD_MATERIAL;
         } else {
            return trapped ? MATERIAL3 : MATERIAL;
         }
      }
   }
}
