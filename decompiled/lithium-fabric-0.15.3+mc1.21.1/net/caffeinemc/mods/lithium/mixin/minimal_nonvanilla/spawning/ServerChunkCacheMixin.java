package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.spawning;

import net.caffeinemc.mods.lithium.common.world.ChunkAwareEntityIterable;
import net.minecraft.class_1297;
import net.minecraft.class_3215;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3215.class)
public class ServerChunkCacheMixin {
   @Redirect(method = "method_14161()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3218;method_27909()Ljava/lang/Iterable;"))
   private Iterable<class_1297> iterateEntitiesChunkAware(class_3218 serverWorld) {
      return ((ChunkAwareEntityIterable)((PersistentEntitySectionManagerAccessor)((ServerLevelAccessor)serverWorld).getEntityManager()).getCache())
         .lithium$IterateEntitiesInTrackedSections();
   }
}
