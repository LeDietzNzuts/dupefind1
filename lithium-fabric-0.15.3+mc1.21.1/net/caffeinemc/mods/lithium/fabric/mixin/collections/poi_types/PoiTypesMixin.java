package net.caffeinemc.mods.lithium.fabric.mixin.collections.poi_types;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.Map;
import net.minecraft.class_2680;
import net.minecraft.class_4158;
import net.minecraft.class_7477;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_7477.class)
public class PoiTypesMixin {
   @Mutable
   @Shadow
   @Final
   private static Map<class_2680, class_4158> field_39301 = new Reference2ReferenceOpenHashMap(PoiTypesMixin.field_39301);
}
