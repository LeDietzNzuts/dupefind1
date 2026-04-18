package malte0811.ferritecore.mixin.accessors;

import net.minecraft.class_251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_251.class)
public interface DiscreteVSAccess {
   @Accessor
   int getXSize();

   @Accessor
   int getYSize();

   @Accessor
   int getZSize();
}
