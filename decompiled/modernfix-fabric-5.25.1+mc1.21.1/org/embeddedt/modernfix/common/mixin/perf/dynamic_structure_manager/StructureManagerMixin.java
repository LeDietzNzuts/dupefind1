package org.embeddedt.modernfix.common.mixin.perf.dynamic_structure_manager;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mojang.datafixers.DataFixer;
import java.util.Map;
import java.util.Optional;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_3300;
import net.minecraft.class_3485;
import net.minecraft.class_3499;
import net.minecraft.class_7871;
import net.minecraft.class_32.class_5143;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_3485.class)
public class StructureManagerMixin {
   @Shadow
   @Final
   @Mutable
   private Map<class_2960, Optional<class_3499>> field_15513;

   @Inject(method = "<init>", at = @At("RETURN"))
   private void makeStructuresSafe(class_3300 arg, class_5143 arg2, DataFixer dataFixer, class_7871<class_2248> arg3, CallbackInfo ci) {
      Cache<class_2960, Optional<class_3499>> structureCache = CacheBuilder.newBuilder().softValues().build();
      this.field_15513 = structureCache.asMap();
   }
}
