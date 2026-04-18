package malte0811.ferritecore.mixin.accessors;

import net.minecraft.class_777;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_777.class)
public interface BakedQuadAccess {
   @Accessor
   @Mutable
   void setVertices(int[] var1);
}
