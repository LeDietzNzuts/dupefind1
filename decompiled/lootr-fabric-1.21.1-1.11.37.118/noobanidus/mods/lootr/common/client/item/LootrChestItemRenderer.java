package noobanidus.mods.lootr.common.client.item;

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
import noobanidus.mods.lootr.common.block.entity.LootrChestBlockEntity;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;

public class LootrChestItemRenderer extends class_756 {
   private static LootrChestItemRenderer INSTANCE = null;
   private final class_824 blockEntityRenderDispatcher;
   private final LootrChestBlockEntity blockEntity;

   public LootrChestItemRenderer(class_824 pBlockEntityRenderDispatcher, class_5599 pEntityModelSet) {
      super(pBlockEntityRenderDispatcher, pEntityModelSet);
      this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
      this.blockEntity = new LootrChestBlockEntity(class_2338.field_10980, LootrRegistry.getChestBlock().method_9564());
   }

   public LootrChestItemRenderer() {
      this(class_310.method_1551().method_31975(), class_310.method_1551().method_31974());
   }

   public static LootrChestItemRenderer getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new LootrChestItemRenderer();
      }

      return INSTANCE;
   }

   public void method_3166(class_1799 p_108830_, class_811 p_270899_, class_4587 p_108832_, class_4597 p_108833_, int p_108834_, int p_108835_) {
      this.blockEntityRenderDispatcher.method_23077(this.blockEntity, p_108832_, p_108833_, p_108834_, p_108835_);
   }

   public void renderByMinecart(LootrChestMinecartEntity entity, class_4587 matrixStack, class_4597 buffer, int combinedLight) {
      boolean open = this.blockEntity.isClientOpened();
      this.blockEntity.setClientOpened(entity.isClientOpened());
      this.blockEntityRenderDispatcher.method_23077(this.blockEntity, matrixStack, buffer, combinedLight, class_4608.field_21444);
      this.blockEntity.setClientOpened(open);
   }
}
