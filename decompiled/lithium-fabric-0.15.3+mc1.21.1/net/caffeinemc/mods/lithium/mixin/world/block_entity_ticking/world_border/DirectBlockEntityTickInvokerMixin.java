package net.caffeinemc.mods.lithium.mixin.world.block_entity_ticking.world_border;

import net.caffeinemc.mods.lithium.common.world.listeners.WorldBorderListenerOnce;
import net.minecraft.class_1923;
import net.minecraft.class_2338;
import net.minecraft.class_2784;
import net.minecraft.class_2789;
import net.minecraft.class_2818;
import net.minecraft.class_3194;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net/minecraft/class_2818$class_5563")
public abstract class DirectBlockEntityTickInvokerMixin implements WorldBorderListenerOnce {
   @Shadow(aliases = {"field_27223", "this$0"})
   @Final
   class_2818 field_27223;
   private byte worldBorderState = 0;

   @Shadow
   public abstract class_2338 method_31705();

   @Redirect(method = "method_31703()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2818;method_31724(Lnet/minecraft/class_2338;)Z"))
   private boolean cachedCanTickBlockEntity(class_2818 instance, class_2338 pos) {
      if (this.isInsideWorldBorder()) {
         return !(this.field_27223.method_12200() instanceof class_3218 serverWorld)
            ? true
            : this.field_27223.method_12225().method_14014(class_3194.field_44856) && serverWorld.method_37116(class_1923.method_37232(pos));
      } else {
         return false;
      }
   }

   private boolean isInsideWorldBorder() {
      if (this.worldBorderState == 0) {
         this.startWorldBorderCaching();
      }

      int worldBorderState = this.worldBorderState;
      return (worldBorderState & 3) == 3 ? (worldBorderState & 4) != 0 : this.field_27223.method_12200().method_8621().method_11952(this.method_31705());
   }

   private void startWorldBorderCaching() {
      this.worldBorderState = 1;
      class_2784 worldBorder = this.field_27223.method_12200().method_8621();
      worldBorder.method_11983(this);
      boolean isStationary = worldBorder.method_11968() == class_2789.field_12753;
      if (worldBorder.method_11952(this.method_31705())) {
         if (isStationary || worldBorder.method_11968() == class_2789.field_12754) {
            this.worldBorderState = (byte)(this.worldBorderState | 6);
         }
      } else if (isStationary || worldBorder.method_11968() == class_2789.field_12756) {
         this.worldBorderState = (byte)(this.worldBorderState | 2);
      }
   }

   @Override
   public void lithium$onWorldBorderShapeChange(class_2784 worldBorder) {
      this.worldBorderState = 0;
   }
}
