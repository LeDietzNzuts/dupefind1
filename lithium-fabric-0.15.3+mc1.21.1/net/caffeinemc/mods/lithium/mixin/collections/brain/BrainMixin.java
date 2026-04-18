package net.caffeinemc.mods.lithium.mixin.collections.brain;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceLinkedOpenHashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.function.Supplier;
import net.minecraft.class_4095;
import net.minecraft.class_4140;
import net.minecraft.class_4141;
import net.minecraft.class_4168;
import net.minecraft.class_4831;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_4095.class)
public class BrainMixin {
   @Mutable
   @Shadow
   @Final
   private Map<class_4140<?>, Optional<? extends class_4831<?>>> field_18322;
   @Mutable
   @Shadow
   @Final
   private Map<?, ?> field_18323;
   @Shadow
   @Final
   @Mutable
   private Map<class_4168, Set<Pair<class_4140<?>, class_4141>>> field_18326;

   @Inject(
      method = "<init>(Ljava/util/Collection;Ljava/util/Collection;Lcom/google/common/collect/ImmutableList;Ljava/util/function/Supplier;)V",
      at = @At("RETURN")
   )
   private void reinitializeBrainCollections(
      Collection<?> memories, Collection<?> sensors, ImmutableList<?> memoryEntries, Supplier<?> codecSupplier, CallbackInfo ci
   ) {
      this.field_18322 = new Reference2ObjectOpenHashMap(this.field_18322);
      this.field_18323 = new Reference2ReferenceLinkedOpenHashMap(this.field_18323);
      this.field_18326 = new Object2ObjectOpenHashMap(this.field_18326);
   }

   @Redirect(method = "method_27075()V", at = @At(value = "INVOKE", target = "Ljava/util/Map;entrySet()Ljava/util/Set;"))
   private <K, V> Set<Entry<K, V>> redirectIterator(Map<K, V> instance) {
      return null;
   }

   @Redirect(method = "method_27075()V", at = @At(value = "INVOKE", target = "Ljava/util/Set;iterator()Ljava/util/Iterator;"))
   private Iterator<? extends Entry<?, ?>> redirectIterator(Set<Entry<class_4140<?>, Optional<? extends class_4831<?>>>> set) {
      return (Iterator<? extends Entry<?, ?>>)(this.field_18322 instanceof Reference2ObjectOpenHashMap<class_4140<?>, Optional<? extends class_4831<?>>> fastMap
         ? fastMap.reference2ObjectEntrySet().fastIterator()
         : this.field_18322.entrySet().iterator());
   }
}
