package org.embeddedt.modernfix.common.mixin.perf.state_definition_construct;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import net.minecraft.class_2688;
import net.minecraft.class_2689;
import net.minecraft.class_2769;
import org.embeddedt.modernfix.annotation.RequiresMod;
import org.embeddedt.modernfix.blockstate.FakeStateMap;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(class_2689.class)
@RequiresMod("ferritecore")
public class StateDefinitionMixin<O, S extends class_2688<O, S>> {
   @Shadow
   @Final
   private ImmutableSortedMap<String, class_2769<?>> field_12316;

   @ModifyVariable(method = "<init>", at = @At(value = "STORE", ordinal = 0), ordinal = 1, index = 8)
   private Map<Map<class_2769<?>, Comparable<?>>, S> useArrayMap(Map<Map<class_2769<?>, Comparable<?>>, S> in) {
      int numStates = 1;
      UnmodifiableIterator var3 = this.field_12316.values().iterator();

      while (var3.hasNext()) {
         class_2769<?> prop = (class_2769<?>)var3.next();
         numStates *= prop.method_11898().size();
      }

      return new FakeStateMap<>(numStates);
   }
}
