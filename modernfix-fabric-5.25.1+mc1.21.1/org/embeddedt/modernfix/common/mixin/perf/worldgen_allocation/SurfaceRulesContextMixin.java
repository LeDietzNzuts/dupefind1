package org.embeddedt.modernfix.common.mixin.perf.worldgen_allocation;

import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.class_1959;
import net.minecraft.class_2338;
import net.minecraft.class_6880;
import net.minecraft.class_2338.class_2339;
import org.embeddedt.modernfix.world.gen.PositionalBiomeGetter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "net/minecraft/world/level/levelgen/SurfaceRules$Context", priority = 100)
public class SurfaceRulesContextMixin {
   @Shadow
   private long field_35612;
   @Shadow
   private int field_35617;
   @Shadow
   private int field_35618;
   @Shadow
   private int field_35619;
   @Shadow
   private int field_35620;
   @Shadow
   private Supplier<class_6880<class_1959>> field_35614;
   @Shadow
   @Final
   private Function<class_2338, class_6880<class_1959>> field_35606;
   @Shadow
   @Final
   private class_2339 field_35613;

   @Overwrite
   public void method_39073(int stoneDepthAbove, int stoneDepthBelow, int waterHeight, int blockX, int blockY, int blockZ) {
      this.field_35612++;
      Supplier<class_6880<class_1959>> getter = this.field_35614;
      if (getter == null) {
         this.field_35614 = getter = new PositionalBiomeGetter(this.field_35606, this.field_35613);
      }

      ((PositionalBiomeGetter)getter).update(blockX, blockY, blockZ);
      this.field_35617 = blockY;
      this.field_35618 = waterHeight;
      this.field_35619 = stoneDepthBelow;
      this.field_35620 = stoneDepthAbove;
   }
}
