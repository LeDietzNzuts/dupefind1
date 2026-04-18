package net.caffeinemc.mods.lithium.mixin.alloc.entity_tracker;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Set;
import net.minecraft.class_5629;
import net.minecraft.class_3898.class_3208;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_3208.class)
public class ChunkMap$TrackedEntityMixin {
   @Redirect(
      method = "<init>(Lnet/minecraft/class_3898;Lnet/minecraft/class_1297;IIZ)V",
      require = 0,
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Sets;newIdentityHashSet()Ljava/util/Set;", remap = false)
   )
   private Set<class_5629> useFasterCollection() {
      return new ReferenceOpenHashSet();
   }
}
