package noobanidus.mods.lootr.fabric.client.item;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_5599;
import net.minecraft.class_756;
import net.minecraft.class_811;
import net.minecraft.class_824;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.block.entity.LootrDecoratedPotBlockEntity;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;

public class LootrDecoratedPotItemRenderer extends class_756 implements DynamicItemRenderer {
   private static LootrDecoratedPotItemRenderer INSTANCE = null;
   private final LootrDecoratedPotBlockEntity tile;
   private class_824 blockEntityRenderDispatcher;

   public LootrDecoratedPotItemRenderer(class_824 pBlockEntityRenderDispatcher, class_5599 pEntityModelSet) {
      super(pBlockEntityRenderDispatcher, pEntityModelSet);
      this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
      this.tile = new LootrDecoratedPotBlockEntity(class_2338.field_10980, LootrRegistry.getDecoratedPotBlock().method_9564());
   }

   public LootrDecoratedPotItemRenderer() {
      this(class_310.method_1551().method_31975(), class_310.method_1551().method_31974());
   }

   public static LootrDecoratedPotItemRenderer getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new LootrDecoratedPotItemRenderer();
      }

      return INSTANCE;
   }

   public void method_3166(class_1799 stack, class_811 mode, class_4587 matrixStack, class_4597 buffer, int combinedLight, int combinedOverlay) {
      this.getBlockEntityRenderDispatcher().method_23077(this.tile, matrixStack, buffer, combinedLight, combinedOverlay);
   }

   public void render(class_1799 stack, class_811 mode, class_4587 matrices, class_4597 vertexConsumers, int light, int overlay) {
      this.method_3166(stack, mode, matrices, vertexConsumers, light, overlay);
   }

   public void renderByMinecart(LootrChestMinecartEntity entity, class_4587 matrixStack, class_4597 buffer, int combinedLight) {
      boolean open = this.tile.isClientOpened();
      this.tile.setClientOpened(entity.isClientOpened());
      this.getBlockEntityRenderDispatcher().method_23077(this.tile, matrixStack, buffer, combinedLight, class_4608.field_21444);
      this.tile.setClientOpened(open);
   }

   private class_824 getBlockEntityRenderDispatcher() {
      if (this.blockEntityRenderDispatcher == null) {
         this.blockEntityRenderDispatcher = class_310.method_1551().method_31975();
      }

      return this.blockEntityRenderDispatcher;
   }
}
