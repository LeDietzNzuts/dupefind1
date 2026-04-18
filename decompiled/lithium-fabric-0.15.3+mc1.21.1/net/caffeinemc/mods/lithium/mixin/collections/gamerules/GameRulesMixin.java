package net.caffeinemc.mods.lithium.mixin.collections.gamerules;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.class_1928;
import net.minecraft.class_1928.class_4313;
import net.minecraft.class_1928.class_4315;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1928.class)
public class GameRulesMixin {
   @Mutable
   @Shadow
   @Final
   private Map<class_4313<?>, class_4315<?>> field_9196;

   @Inject(method = "<init>()V", at = @At("RETURN"))
   private void reinitializeMap(CallbackInfo ci) {
      this.field_9196 = new Object2ObjectOpenHashMap(this.field_9196);
   }

   @Inject(method = "<init>(Ljava/util/Map;)V", at = @At("RETURN"))
   private void reinitializeMap(Map<?, ?> rules, CallbackInfo ci) {
      this.field_9196 = new Object2ObjectOpenHashMap(this.field_9196);
   }
}
