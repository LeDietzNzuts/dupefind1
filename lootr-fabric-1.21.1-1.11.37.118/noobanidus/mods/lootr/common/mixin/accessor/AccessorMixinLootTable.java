package noobanidus.mods.lootr.common.mixin.accessor;

import java.util.Optional;
import net.minecraft.class_2960;
import net.minecraft.class_52;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(class_52.class)
public interface AccessorMixinLootTable {
   @Accessor("field_44892")
   Optional<class_2960> getRandomSequence();
}
