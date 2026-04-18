package net.caffeinemc.mods.lithium.mixin.ai.pathing;

import net.caffeinemc.mods.lithium.common.ai.pathing.BlockStatePathingCache;
import net.caffeinemc.mods.lithium.common.world.blockview.SingleBlockBlockView;
import net.minecraft.class_14;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_7;
import net.minecraft.class_9316;
import net.minecraft.class_4970.class_4971;
import org.apache.commons.lang3.Validate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4971.class)
public abstract class BlockStateBaseMixin implements BlockStatePathingCache {
   private class_7 pathNodeType = null;
   private class_7 pathNodeTypeNeighbor = null;

   @Override
   public void lithium$initializePathNodeTypeCache() {
      this.pathNodeType = null;
      this.pathNodeTypeNeighbor = null;
      class_2680 state = this.method_26233();
      SingleBlockBlockView singleBlockBlockView = SingleBlockBlockView.of(state, class_2338.field_10980);

      try {
         this.pathNodeType = (class_7)Validate.notNull(
            class_14.method_58(singleBlockBlockView, class_2338.field_10980), "Block has no common path node type!", new Object[0]
         );
      } catch (ClassCastException | SingleBlockBlockView.SingleBlockViewException var5) {
         this.pathNodeType = null;
      }

      try {
         this.pathNodeTypeNeighbor = class_14.method_59(new class_9316(singleBlockBlockView, null), 1, 1, 1, null);
         if (this.pathNodeTypeNeighbor == null) {
            this.pathNodeTypeNeighbor = class_7.field_7;
         }
      } catch (NullPointerException | ClassCastException | SingleBlockBlockView.SingleBlockViewException var4) {
         this.pathNodeTypeNeighbor = null;
      }
   }

   @Override
   public class_7 lithium$getPathNodeType() {
      return this.pathNodeType;
   }

   @Override
   public class_7 lithium$getNeighborPathNodeType() {
      return this.pathNodeTypeNeighbor;
   }

   @Shadow
   protected abstract class_2680 method_26233();

   @Shadow
   public abstract class_2248 method_26204();
}
