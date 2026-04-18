package net.caffeinemc.mods.lithium.mixin.ai.task.memory_change_counting;

import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap.Entry;
import java.util.Map;
import net.caffeinemc.mods.lithium.common.ai.MemoryModificationCounter;
import net.minecraft.class_1309;
import net.minecraft.class_4095;
import net.minecraft.class_4097;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4097.class)
public class BehaviorMixin<E extends class_1309> {
   @Mutable
   @Shadow
   @Final
   protected Map<class_4140<?>, class_4141> field_19291;
   @Unique
   private long cachedMemoryModCount = -1L;
   @Unique
   private boolean cachedHasRequiredMemoryState;

   @Inject(method = "<init>(Ljava/util/Map;II)V", at = @At("RETURN"))
   private void init(Map<class_4140<?>, class_4141> map, int int_1, int int_2, CallbackInfo ci) {
      this.field_19291 = new Reference2ObjectOpenHashMap(map);
   }

   @Overwrite
   public boolean method_19546(E entity) {
      class_4095<?> brain = entity.method_18868();
      long modCount = ((MemoryModificationCounter)brain).lithium$getModCount();
      if (this.cachedMemoryModCount == modCount) {
         return this.cachedHasRequiredMemoryState;
      } else {
         this.cachedMemoryModCount = modCount;
         ObjectIterator<Entry<class_4140<?>, class_4141>> fastIterator = ((Reference2ObjectOpenHashMap)this.field_19291)
            .reference2ObjectEntrySet()
            .fastIterator();

         while (fastIterator.hasNext()) {
            Entry<class_4140<?>, class_4141> entry = (Entry<class_4140<?>, class_4141>)fastIterator.next();
            if (!brain.method_18876((class_4140)entry.getKey(), (class_4141)entry.getValue())) {
               return this.cachedHasRequiredMemoryState = false;
            }
         }

         return this.cachedHasRequiredMemoryState = true;
      }
   }
}
