package net.caffeinemc.mods.lithium.mixin.collections.mob_spawning;

import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import net.caffeinemc.mods.lithium.common.util.collections.HashedReferenceList;
import net.minecraft.class_6008;
import net.minecraft.class_6012;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_6012.class)
public class WeightedRandomListMixin<E extends class_6008> {
   @Mutable
   @Shadow
   @Final
   private ImmutableList<E> field_29935;
   @Unique
   private List<E> entryHashList;

   @Inject(method = "<init>(Ljava/util/List;)V", at = @At("RETURN"))
   private void init(List<? extends E> entries, CallbackInfo ci) {
      this.entryHashList = (List<E>)(this.field_29935.size() > 4 ? Collections.unmodifiableList(new HashedReferenceList<>(this.field_29935)) : this.field_29935);
   }

   @Overwrite
   public List<E> method_34994() {
      return this.entryHashList;
   }
}
