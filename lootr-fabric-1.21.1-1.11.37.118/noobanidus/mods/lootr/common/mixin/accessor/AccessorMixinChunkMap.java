package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_3193;
import net.minecraft.class_3898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_3898.class)
public interface AccessorMixinChunkMap {
   @Invoker("method_17264")
   Iterable<class_3193> lootr$getChunks();
}
