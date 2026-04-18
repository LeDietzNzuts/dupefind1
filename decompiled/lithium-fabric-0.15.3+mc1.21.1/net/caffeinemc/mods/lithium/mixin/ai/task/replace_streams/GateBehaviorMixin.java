package net.caffeinemc.mods.lithium.mixin.ai.task.replace_streams;

import java.util.Set;
import net.caffeinemc.mods.lithium.common.ai.WeightedListIterable;
import net.minecraft.class_1309;
import net.minecraft.class_3218;
import net.minecraft.class_4095;
import net.minecraft.class_4103;
import net.minecraft.class_4140;
import net.minecraft.class_6032;
import net.minecraft.class_7893;
import net.minecraft.class_4097.class_4098;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4103.class)
public abstract class GateBehaviorMixin<E extends class_1309> implements class_7893<E> {
   @Shadow
   @Final
   private class_6032<class_7893<? super E>> field_18347;
   @Shadow
   @Final
   private Set<class_4140<?>> field_18344;
   @Shadow
   private class_4098 field_40993;

   @Overwrite
   public final void method_18923(class_3218 world, E entity, long time) {
      boolean hasOneTaskRunning = false;

      for (class_7893<? super E> task : WeightedListIterable.cast(this.field_18347)) {
         if (task.method_18921() == class_4098.field_18338) {
            task.method_18923(world, entity, time);
            hasOneTaskRunning |= task.method_18921() == class_4098.field_18338;
         }
      }

      if (!hasOneTaskRunning) {
         this.method_18925(world, entity, time);
      }
   }

   @Overwrite
   public final void method_18925(class_3218 world, E entity, long time) {
      this.field_40993 = class_4098.field_18337;

      for (class_7893<? super E> task : WeightedListIterable.cast(this.field_18347)) {
         if (task.method_18921() == class_4098.field_18338) {
            task.method_18925(world, entity, time);
         }
      }

      class_4095<?> brain = entity.method_18868();

      for (class_4140<?> module : this.field_18344) {
         brain.method_18875(module);
      }
   }
}
