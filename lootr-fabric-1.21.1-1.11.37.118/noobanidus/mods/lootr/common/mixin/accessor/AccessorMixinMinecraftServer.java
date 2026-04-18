package noobanidus.mods.lootr.common.mixin.accessor;

import net.minecraft.class_32.class_5143;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftServer.class)
public interface AccessorMixinMinecraftServer {
   @Accessor("field_23784")
   class_5143 Lootr$getStorageSource();
}
