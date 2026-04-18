package noobanidus.mods.lootr.common.mixin.accessor;

import java.util.Map;
import net.minecraft.class_18;
import net.minecraft.class_26;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_26.class)
public interface AccessorMixinDimensionDataStorage {
   @Accessor("field_134")
   Map<String, class_18> getCache();
}
