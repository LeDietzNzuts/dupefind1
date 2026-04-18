package noobanidus.mods.lootr.common.client.item;

import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5599;
import net.minecraft.class_756;
import net.minecraft.class_811;
import net.minecraft.class_824;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.block.entity.LootrDecoratedPotBlockEntity;

public class LootrDecoratedPotItemRenderer extends class_756 {
   private static LootrDecoratedPotItemRenderer INSTANCE = null;
   private final class_824 blockEntityRenderDispatcher;
   private final LootrDecoratedPotBlockEntity blockEntity;

   public LootrDecoratedPotItemRenderer(class_824 pBlockEntityRenderDispatcher, class_5599 pEntityModelSet) {
      super(pBlockEntityRenderDispatcher, pEntityModelSet);
      this.blockEntityRenderDispatcher = pBlockEntityRenderDispatcher;
      this.blockEntity = new LootrDecoratedPotBlockEntity(class_2338.field_10980, LootrRegistry.getDecoratedPotBlock().method_9564());
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

   public void method_3166(class_1799 p_108830_, class_811 p_270899_, class_4587 p_108832_, class_4597 p_108833_, int p_108834_, int p_108835_) {
      this.blockEntity.setFromItem(p_108830_);
      this.blockEntityRenderDispatcher.method_23077(this.blockEntity, p_108832_, p_108833_, p_108834_, p_108835_);
   }
}
