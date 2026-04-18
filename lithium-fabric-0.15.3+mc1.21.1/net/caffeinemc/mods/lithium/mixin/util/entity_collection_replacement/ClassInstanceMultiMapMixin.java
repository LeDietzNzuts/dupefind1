package net.caffeinemc.mods.lithium.mixin.util.entity_collection_replacement;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.caffeinemc.mods.lithium.common.entity.TypeFilterableListInternalAccess;
import net.minecraft.class_3509;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(class_3509.class)
public abstract class ClassInstanceMultiMapMixin<T> extends AbstractCollection<T> implements TypeFilterableListInternalAccess<T> {
   @Shadow
   @Final
   private Map<Class<?>, List<T>> field_15636;

   @Shadow
   public abstract <S> Collection<S> method_15216(Class<S> var1);

   @Override
   public <S extends T> List<S> lithium$getOrCreateAllOfTypeRaw(Class<S> type) {
      List<S> s = this.field_15636.get(type);
      if (s == null) {
         this.method_15216(type);
         s = this.field_15636.get(type);
      }

      return s;
   }

   @Override
   public <S extends T> List<S> lithium$replaceCollectionAndGet(Class<S> type, Function<ArrayList<S>, List<S>> listCtor) {
      List<T> oldList = this.field_15636.get(type);
      List<S> newList = listCtor.apply((ArrayList<S>)oldList);
      this.field_15636.put(type, newList);
      return newList;
   }

   @Override
   public <S extends T> List<S> lithium$replaceCollectionAndGet(Class<S> type, ArrayList<S> list) {
      this.field_15636.put(type, list);
      return list;
   }
}
