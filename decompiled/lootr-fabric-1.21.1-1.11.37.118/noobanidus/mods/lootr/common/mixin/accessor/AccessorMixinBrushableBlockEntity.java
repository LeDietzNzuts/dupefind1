package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8174;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_8174.class)
public interface AccessorMixinBrushableBlockEntity {
   @Accessor("field_42814")
   class_5321<class_52> lootr$getLootTable();

   @Accessor("field_42815")
   long lootr$getLootTableSeed();
}
