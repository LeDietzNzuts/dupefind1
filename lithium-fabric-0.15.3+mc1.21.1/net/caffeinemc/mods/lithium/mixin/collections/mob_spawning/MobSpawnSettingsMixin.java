package net.caffeinemc.mods.lithium.mixin.collections.mob_spawning;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_5483;
import net.minecraft.class_6012;
import net.minecraft.class_5483.class_1964;
import net.minecraft.class_5483.class_5265;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_5483.class)
public class MobSpawnSettingsMixin {
   @Mutable
   @Shadow
   @Final
   private Map<class_1311, class_6012<class_1964>> field_26405;

   @Inject(method = "<init>(FLjava/util/Map;Ljava/util/Map;)V", at = @At("RETURN"))
   private void reinit(
      float creatureSpawnProbability, Map<class_1311, class_6012<class_1964>> spawners, Map<class_1299<?>, class_5265> spawnCosts, CallbackInfo ci
   ) {
      Map<class_1311, class_6012<class_1964>> spawns = Maps.newEnumMap(class_1311.class);

      for (Entry<class_1311, class_6012<class_1964>> entry : this.field_26405.entrySet()) {
         spawns.put(entry.getKey(), entry.getValue());
      }

      this.field_26405 = spawns;
   }
}
