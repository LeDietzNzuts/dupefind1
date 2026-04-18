package net.caffeinemc.mods.lithium.common.block.entity;

import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_5562;

public record SleepUntilTimeBlockEntityTickInvoker(class_2586 sleepingBlockEntity, long sleepUntilTickExclusive, class_5562 delegate) implements class_5562 {
   public void method_31703() {
      long tickTime = this.sleepingBlockEntity.method_10997().method_8510();
      if (tickTime >= this.sleepUntilTickExclusive) {
         ((SleepingBlockEntity)this.sleepingBlockEntity).setTicker(this.delegate);
         this.delegate.method_31703();
      }
   }

   public boolean method_31704() {
      return this.sleepingBlockEntity.method_11015();
   }

   public class_2338 method_31705() {
      return this.sleepingBlockEntity.method_11016();
   }

   public String method_31706() {
      return class_2591.method_11033(this.sleepingBlockEntity.method_11017()).toString();
   }
}
