package vectorwing.farmersdelight.common.mixin.refabricated;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import vectorwing.farmersdelight.common.block.TomatoVineBlock;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.item.SkilletItem;
import vectorwing.farmersdelight.common.item.enchantment.BackstabbingEnchantment;

@Mixin(class_1309.class)
public abstract class LivingEntityMixin extends class_1297 {
   public LivingEntityMixin(class_1299<?> entityType, class_1937 level) {
      super(entityType, level);
   }

   @ModifyVariable(method = "method_5643(Lnet/minecraft/class_1282;F)Z", at = @At("HEAD"), argsOnly = true)
   private float handleBackstabbingDamage(float original, class_1282 source) {
      if (original > 0.0F) {
         SkilletItem.SkilletEvents.playSkilletAttackSound((class_1309)this, source);
         return BackstabbingEnchantment.BackstabbingEvent.onKnifeBackstab((class_1309)this, source, original);
      } else {
         return original;
      }
   }

   @ModifyExpressionValue(
      method = "method_6101()Z",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2680;method_26164(Lnet/minecraft/class_6862;)Z")
   )
   private boolean onlyAllowTomatoClimbingWhilstRopelogged(boolean original, @Local class_2338 pos, @Local class_2680 state) {
      return original && !(state.method_26204() instanceof TomatoVineBlock tomato && !tomato.isLadder(state, this.method_37908(), pos, (class_1309)this));
   }

   @ModifyVariable(method = "method_6005(DDD)V", at = @At("HEAD"), ordinal = 0, argsOnly = true)
   private double handleKnifeKnockback(double strength) {
      return KnifeItem.KnifeEvents.onKnifeKnockback(strength, (class_1309)this);
   }
}
