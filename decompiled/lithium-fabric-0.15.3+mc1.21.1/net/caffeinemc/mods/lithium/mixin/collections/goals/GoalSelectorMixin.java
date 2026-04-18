package net.caffeinemc.mods.lithium.mixin.collections.goals;

import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.util.Set;
import java.util.function.Supplier;
import net.minecraft.class_1355;
import net.minecraft.class_3695;
import net.minecraft.class_4135;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1355.class)
public abstract class GoalSelectorMixin {
   @Mutable
   @Shadow
   @Final
   private Set<class_4135> field_6461;

   @Inject(method = "<init>(Ljava/util/function/Supplier;)V", at = @At("RETURN"))
   private void reinit(Supplier<class_3695> supplier, CallbackInfo ci) {
      this.field_6461 = new ObjectLinkedOpenHashSet(this.field_6461);
   }
}
