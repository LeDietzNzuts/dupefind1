package dev.architectury.hooks.item.tool;

import dev.architectury.hooks.item.tool.fabric.HoeItemHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.class_1838;
import net.minecraft.class_2248;
import net.minecraft.class_2680;

public final class HoeItemHooks {
   private HoeItemHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static void addTillable(class_2248 input, Predicate<class_1838> predicate, Consumer<class_1838> action, Function<class_1838, class_2680> newState) {
      HoeItemHooksImpl.addTillable(input, predicate, action, newState);
   }
}
