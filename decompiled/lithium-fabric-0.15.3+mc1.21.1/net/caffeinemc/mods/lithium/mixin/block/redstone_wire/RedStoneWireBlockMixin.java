package net.caffeinemc.mods.lithium.mixin.block.redstone_wire;

import net.caffeinemc.mods.lithium.common.util.DirectionConstants;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2457;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2818;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2457.class)
public class RedStoneWireBlockMixin extends class_2248 {
   private static final int MIN = 0;
   private static final int MAX = 15;
   private static final int MAX_WIRE = 14;

   public RedStoneWireBlockMixin(class_2251 settings) {
      super(settings);
   }

   @Inject(method = "method_27842(Lnet/minecraft/class_1937;Lnet/minecraft/class_2338;)I", cancellable = true, at = @At("HEAD"))
   private void getReceivedPowerFaster(class_1937 world, class_2338 pos, CallbackInfoReturnable<Integer> cir) {
      cir.setReturnValue(this.getReceivedPower(world, pos));
   }

   private int getReceivedPower(class_1937 world, class_2338 pos) {
      class_2818 chunk = world.method_8500(pos);
      int power = 0;

      for (class_2350 dir : DirectionConstants.VERTICAL) {
         class_2338 side = pos.method_10093(dir);
         class_2680 neighbor = chunk.method_8320(side);
         if (!neighbor.method_26215() && !neighbor.method_27852(this)) {
            power = Math.max(power, this.getPowerFromVertical(world, side, neighbor, dir));
            if (power >= 15) {
               return 15;
            }
         }
      }

      class_2338 up = pos.method_10084();
      boolean checkWiresAbove = !chunk.method_8320(up).method_26212(world, up);

      for (class_2350 dirx : DirectionConstants.HORIZONTAL) {
         power = Math.max(power, this.getPowerFromSide(world, pos.method_10093(dirx), dirx, checkWiresAbove));
         if (power >= 15) {
            return 15;
         }
      }

      return power;
   }

   private int getPowerFromVertical(class_1937 world, class_2338 pos, class_2680 state, class_2350 toDir) {
      int power = state.method_26195(world, pos, toDir);
      if (power >= 15) {
         return 15;
      } else {
         return state.method_26212(world, pos) ? Math.max(power, this.getStrongPowerTo(world, pos, toDir.method_10153())) : power;
      }
   }

   private int getPowerFromSide(class_1937 world, class_2338 pos, class_2350 toDir, boolean checkWiresAbove) {
      class_2818 chunk = world.method_8500(pos);
      class_2680 state = chunk.method_8320(pos);
      if (state.method_27852(this)) {
         return (Integer)state.method_11654(class_2741.field_12511) - 1;
      } else {
         int power = state.method_26195(world, pos, toDir);
         if (power >= 15) {
            return 15;
         } else {
            if (state.method_26212(world, pos)) {
               power = Math.max(power, this.getStrongPowerTo(world, pos, toDir.method_10153()));
               if (power >= 15) {
                  return 15;
               }

               if (checkWiresAbove && power < 14) {
                  class_2338 up = pos.method_10084();
                  class_2680 aboveState = chunk.method_8320(up);
                  if (aboveState.method_27852(this)) {
                     power = Math.max(power, (Integer)aboveState.method_11654(class_2741.field_12511) - 1);
                  }
               }
            } else if (power < 14) {
               class_2338 down = pos.method_10074();
               class_2680 belowState = chunk.method_8320(down);
               if (belowState.method_27852(this)) {
                  power = Math.max(power, (Integer)belowState.method_11654(class_2741.field_12511) - 1);
               }
            }

            return power;
         }
      }
   }

   private int getStrongPowerTo(class_1937 world, class_2338 pos, class_2350 ignore) {
      int power = 0;

      for (class_2350 dir : DirectionConstants.ALL) {
         if (dir != ignore) {
            class_2338 side = pos.method_10093(dir);
            class_2680 neighbor = world.method_8320(side);
            if (!neighbor.method_26215() && !neighbor.method_27852(this)) {
               power = Math.max(power, neighbor.method_26203(world, side, dir));
               if (power >= 15) {
                  return 15;
               }
            }
         }
      }

      return power;
   }
}
