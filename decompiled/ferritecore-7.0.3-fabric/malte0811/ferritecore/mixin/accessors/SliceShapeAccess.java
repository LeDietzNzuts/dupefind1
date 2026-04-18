package malte0811.ferritecore.mixin.accessors;

import net.minecraft.class_263;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_263.class)
public interface SliceShapeAccess extends VoxelShapeAccess {
   @Accessor
   class_265 getDelegate();

   @Accessor
   class_2351 getAxis();
}
