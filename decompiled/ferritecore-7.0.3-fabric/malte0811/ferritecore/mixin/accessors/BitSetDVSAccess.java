package malte0811.ferritecore.mixin.accessors;

import java.util.BitSet;
import net.minecraft.class_244;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_244.class)
public interface BitSetDVSAccess extends DiscreteVSAccess {
   @Accessor
   BitSet getStorage();

   @Accessor
   int getXMin();

   @Accessor
   int getYMin();

   @Accessor
   int getZMin();

   @Accessor
   int getXMax();

   @Accessor
   int getYMax();

   @Accessor
   int getZMax();
}
