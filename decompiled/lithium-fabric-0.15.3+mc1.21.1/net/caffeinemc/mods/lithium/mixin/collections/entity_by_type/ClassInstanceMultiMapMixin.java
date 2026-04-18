package net.caffeinemc.mods.lithium.mixin.collections.entity_by_type;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.class_3509;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3509.class)
public class ClassInstanceMultiMapMixin {
   @Mutable
   @Shadow
   @Final
   private Map<Class<?>, List<?>> field_15636;

   @Inject(method = "<init>(Ljava/lang/Class;)V", at = @At("RETURN"))
   private void init(Class<?> elementType, CallbackInfo ci) {
      this.field_15636 = new Reference2ReferenceOpenHashMap(this.field_15636);
   }
}
