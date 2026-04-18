package noobanidus.mods.lootr.common.api.filter;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.class_1799;
import net.minecraft.class_47;
import net.minecraft.class_5819;
import noobanidus.mods.lootr.common.api.data.LootFiller;

public interface ILootrFilter {
   int getPriority();

   String getName();

   boolean mutate(ObjectArrayList<class_1799> var1, LootFiller.LootFillerState var2, class_47 var3, class_5819 var4);

   default boolean mutate(ObjectArrayList<class_1799> toMutate, LootFiller.LootFillerState state, class_47 context) {
      return this.mutate(toMutate, state, context, context.method_294());
   }
}
