package net.caffeinemc.mods.lithium.mixin.chunk.entity_class_groups;

import net.caffeinemc.mods.lithium.common.client.ClientWorldAccessor;
import net.minecraft.class_1297;
import net.minecraft.class_5582;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_638.class)
public class ClientLevelMixin implements ClientWorldAccessor {
   @Shadow
   @Final
   private class_5582<class_1297> field_27734;

   @Override
   public class_5582<class_1297> lithium$getEntityManager() {
      return this.field_27734;
   }
}
