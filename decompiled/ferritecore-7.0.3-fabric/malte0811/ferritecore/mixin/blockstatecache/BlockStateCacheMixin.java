package malte0811.ferritecore.mixin.blockstatecache;

import malte0811.ferritecore.ducks.BlockStateCacheAccess;
import net.minecraft.class_265;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase$Cache")
public class BlockStateCacheMixin implements BlockStateCacheAccess {
   @Shadow
   @Final
   @Mutable
   protected class_265 field_19360;
   @Shadow
   @Final
   @Mutable
   @Nullable
   class_265[] field_16560;
   @Shadow
   @Final
   @Mutable
   private boolean[] field_19429;

   @Override
   public class_265 getCollisionShape() {
      return this.field_19360;
   }

   @Override
   public void setCollisionShape(class_265 newShape) {
      this.field_19360 = newShape;
   }

   @Override
   public class_265[] getOcclusionShapes() {
      return this.field_16560;
   }

   @Override
   public void setOcclusionShapes(@Nullable class_265[] newShapes) {
      this.field_16560 = newShapes;
   }

   @Override
   public boolean[] getFaceSturdy() {
      return this.field_19429;
   }

   @Override
   public void setFaceSturdy(boolean[] newFaceSturdyArray) {
      this.field_19429 = newFaceSturdyArray;
   }
}
