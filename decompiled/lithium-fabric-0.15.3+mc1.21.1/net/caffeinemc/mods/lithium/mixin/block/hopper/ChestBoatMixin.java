package net.caffeinemc.mods.lithium.mixin.block.hopper;

import net.caffeinemc.mods.lithium.common.tracking.entity.ToggleableMovementTracker;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_5569;
import net.minecraft.class_7264;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = class_7264.class, priority = 2000)
public abstract class ChestBoatMixin extends class_1297 {
   public ChestBoatMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Intrinsic
   public void method_5842() {
      this.tickRidingSummarizeMovementNotifications();
   }

   @Unique
   private void tickRidingSummarizeMovementNotifications() {
      class_5569 changeListener = ((EntityAccessor)this).getChangeListener();
      if (changeListener instanceof ToggleableMovementTracker toggleableMovementTracker) {
         class_243 beforeTickPos = this.method_19538();
         int beforeMovementNotificationMask = toggleableMovementTracker.lithium$setNotificationMask(0);
         super.method_5842();
         toggleableMovementTracker.lithium$setNotificationMask(beforeMovementNotificationMask);
         if (!beforeTickPos.equals(this.method_19538())) {
            changeListener.method_31749();
         }
      } else {
         super.method_5842();
      }
   }
}
