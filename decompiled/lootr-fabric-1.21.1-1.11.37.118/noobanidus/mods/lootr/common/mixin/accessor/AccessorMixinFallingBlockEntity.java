package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_1540;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_1540.class)
public interface AccessorMixinFallingBlockEntity {
   @Accessor("field_7188")
   void lootr$setBlockState(class_2680 var1);
}
