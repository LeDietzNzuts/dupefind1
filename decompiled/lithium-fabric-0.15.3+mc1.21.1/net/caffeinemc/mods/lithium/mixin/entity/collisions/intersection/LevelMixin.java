package net.caffeinemc.mods.lithium.mixin.entity.collisions.intersection;

import net.caffeinemc.mods.lithium.common.entity.LithiumEntityCollisions;
import net.minecraft.class_1297;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_1936 {
   public boolean method_8587(@Nullable class_1297 entity, class_238 box) {
      boolean ret = !LithiumEntityCollisions.doesBoxCollideWithBlocks((class_1937)this, entity, box);
      if (ret) {
         ret = !LithiumEntityCollisions.doesBoxCollideWithHardEntities(this, entity, box);
      }

      if (ret && entity != null) {
         ret = !LithiumEntityCollisions.doesBoxCollideWithWorldBorder(this, entity, box);
      }

      return ret;
   }
}
