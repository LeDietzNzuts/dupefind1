package net.caffeinemc.mods.sodium.mixin.features.model;

import java.util.Collections;
import java.util.List;
import net.minecraft.class_1087;
import net.minecraft.class_1097;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_5819;
import net.minecraft.class_6008;
import net.minecraft.class_777;
import net.minecraft.class_6008.class_6010;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(class_1097.class)
public class WeightedBakedModelMixin {
   @Shadow
   @Final
   private List<class_6010<class_1087>> field_5434;
   @Shadow
   @Final
   private int field_5433;

   @Unique
   private static <T extends class_6008> T getAt(List<T> pool, int totalWeight) {
      int i = 0;
      int len = pool.size();

      while (i < len) {
         T weighted = (T)pool.get(i++);
         totalWeight -= weighted.method_34979().method_34976();
         if (totalWeight < 0) {
            return weighted;
         }
      }

      return null;
   }

   @Overwrite
   public List<class_777> method_4707(@Nullable class_2680 state, @Nullable class_2350 face, class_5819 random) {
      class_6010<class_1087> quad = getAt(this.field_5434, Math.abs((int)random.method_43055()) % this.field_5433);
      return quad != null ? ((class_1087)quad.comp_2542()).method_4707(state, face, random) : Collections.emptyList();
   }
}
