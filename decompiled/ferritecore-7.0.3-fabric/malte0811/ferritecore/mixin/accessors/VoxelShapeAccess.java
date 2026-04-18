package malte0811.ferritecore.mixin.accessors;

import net.minecraft.class_251;
import net.minecraft.class_265;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_265.class)
public interface VoxelShapeAccess {
   @Accessor
   class_251 getShape();

   @Accessor
   @Nullable
   class_265[] getFaces();

   @Accessor
   @Mutable
   void setShape(class_251 var1);

   @Accessor
   void setFaces(@Nullable class_265[] var1);
}
