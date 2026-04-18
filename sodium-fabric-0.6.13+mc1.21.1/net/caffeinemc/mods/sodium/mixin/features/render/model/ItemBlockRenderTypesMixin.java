package net.caffeinemc.mods.sodium.mixin.features.render.model;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import java.util.Map;
import net.minecraft.class_1921;
import net.minecraft.class_2248;
import net.minecraft.class_3611;
import net.minecraft.class_4696;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_4696.class)
public class ItemBlockRenderTypesMixin {
   @Mutable
   @Shadow
   @Final
   private static Map<class_2248, class_1921> field_21469 = new Reference2ReferenceOpenHashMap(ItemBlockRenderTypesMixin.field_21469);
   @Mutable
   @Shadow
   @Final
   private static Map<class_3611, class_1921> field_21471 = new Reference2ReferenceOpenHashMap(ItemBlockRenderTypesMixin.field_21471);
}
