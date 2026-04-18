package net.caffeinemc.mods.lithium.mixin.collections.entity_filtering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import net.minecraft.class_3509;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_3509.class)
public class ClassInstanceMultiMapMixin<T> {
   @Shadow
   @Final
   private Map<Class<?>, List<T>> field_15636;
   @Shadow
   @Final
   private List<T> field_15635;

   @Overwrite
   public <S> Collection<S> method_15216(Class<S> type) {
      Collection<T> collection = this.field_15636.get(type);
      if (collection == null) {
         collection = this.createAllOfType(type);
      }

      return (Collection<S>)Collections.unmodifiableCollection(collection);
   }

   private <S> Collection<T> createAllOfType(Class<S> type) {
      List<T> list = new ArrayList<>();

      for (T allElement : this.field_15635) {
         if (type.isInstance(allElement)) {
            list.add(allElement);
         }
      }

      this.field_15636.put(type, list);
      return list;
   }
}
