package malte0811.ferritecore.mixin.accessors;

import net.minecraft.class_251;
import net.minecraft.class_262;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_262.class)
public interface SubShapeAccess extends DiscreteVSAccess {
   @Accessor
   class_251 getParent();

   @Accessor
   int getStartX();

   @Accessor
   int getStartY();

   @Accessor
   int getStartZ();

   @Accessor
   int getEndX();

   @Accessor
   int getEndY();

   @Accessor
   int getEndZ();
}
