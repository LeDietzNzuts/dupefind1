package net.caffeinemc.mods.sodium.mixin.features.options.render_layers;

import net.caffeinemc.mods.sodium.client.SodiumClientMod;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2350;
import net.minecraft.class_2397;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_5365;
import net.minecraft.class_4970.class_2251;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(class_2397.class)
public class LeavesBlockMixin extends class_2248 {
   public LeavesBlockMixin() {
      super(class_2251.method_9630(class_2246.field_10124));
      throw new AssertionError("Mixin constructor called!");
   }

   public boolean method_9522(class_2680 state, class_2680 stateFrom, class_2350 direction) {
      return SodiumClientMod.options().quality.leavesQuality.isFancy((class_5365)class_310.method_1551().field_1690.method_42534().method_41753())
         ? super.method_9522(state, stateFrom, direction)
         : stateFrom.method_26204() instanceof class_2397 || super.method_9522(state, stateFrom, direction);
   }
}
