package net.caffeinemc.mods.lithium.mixin.collections.entity_ticking;

import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.class_1297;
import net.minecraft.class_5574;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_5574.class)
public class EntityTickListMixin {
   @Shadow
   @Nullable
   private Int2ObjectMap<class_1297> field_27256;
   @Shadow
   private Int2ObjectMap<class_1297> field_27254;
   @Shadow
   private Int2ObjectMap<class_1297> field_27255;

   @Overwrite
   private void method_31789() {
      if (this.field_27256 == this.field_27254) {
         this.field_27255 = this.field_27254;
         this.field_27254 = ((Int2ObjectLinkedOpenHashMap)this.field_27254).clone();
      }
   }
}
