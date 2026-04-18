package org.embeddedt.modernfix.common.mixin.perf.worldgen_allocation;

import net.minecraft.class_6686.class_6694;
import net.minecraft.class_6686.class_6702;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(
   targets = {
         "net/minecraft/world/level/levelgen/SurfaceRules$BiomeConditionSource$1BiomeCondition",
         "net/minecraft/world/level/levelgen/SurfaceRules$StoneDepthCheck$1StoneDepthCondition",
         "net/minecraft/world/level/levelgen/SurfaceRules$VerticalGradientConditionSource$1VerticalGradientCondition",
         "net/minecraft/world/level/levelgen/SurfaceRules$WaterConditionSource$1WaterCondition",
         "net/minecraft/world/level/levelgen/SurfaceRules$YConditionSource$1YCondition"
   }
)
public abstract class SurfaceRulesMixin extends class_6702 {
   protected SurfaceRulesMixin(class_6694 context) {
      super(context);
   }

   public boolean method_39069() {
      return this.method_39074();
   }
}
