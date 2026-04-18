package dev.architectury.hooks.item.tool.fabric;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.class_1794;
import net.minecraft.class_1838;
import net.minecraft.class_2248;
import net.minecraft.class_2680;

public class HoeItemHooksImpl {
   public static void addTillable(class_2248 input, Predicate<class_1838> predicate, Consumer<class_1838> action, Function<class_1838, class_2680> newState) {
      if (class_1794.field_8023 instanceof ImmutableMap) {
         class_1794.field_8023 = new HashMap(class_1794.field_8023);
      }

      class_1794.field_8023.put(input, new Pair(predicate, (Consumer<class_1838>)useOnContext -> {
         action.accept(useOnContext);
         class_2680 state = newState.apply(useOnContext);
         useOnContext.method_8045().method_8652(useOnContext.method_8037(), state, 11);
      }));
   }
}
