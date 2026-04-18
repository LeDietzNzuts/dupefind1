package vectorwing.farmersdelight.common;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.Set;
import net.minecraft.class_1646;
import net.minecraft.class_1792;
import net.minecraft.class_1935;
import net.minecraft.class_2315;
import vectorwing.farmersdelight.common.registry.ModItems;

public class CommonSetup {
   public static void init() {
      registerDispenserBehaviors();
      registerItemSetAdditions();
   }

   public static void registerDispenserBehaviors() {
      class_2315.method_58681((class_1935)ModItems.ROTTEN_TOMATO.get());
   }

   public static void registerItemSetAdditions() {
      Set<class_1792> newWantedItems = Sets.newHashSet(
         new class_1792[]{
            ModItems.CABBAGE.get(),
            ModItems.TOMATO.get(),
            ModItems.ONION.get(),
            ModItems.RICE.get(),
            ModItems.CABBAGE_SEEDS.get(),
            ModItems.TOMATO_SEEDS.get(),
            ModItems.RICE_PANICLE.get()
         }
      );
      newWantedItems.addAll(class_1646.field_18527);
      class_1646.field_18527 = ImmutableSet.copyOf(newWantedItems);
      HashMap<class_1792, Integer> foodPoints = new HashMap<>(class_1646.field_18526);
      foodPoints.put(ModItems.CABBAGE.get(), 1);
      foodPoints.put(ModItems.TOMATO.get(), 1);
      foodPoints.put(ModItems.ONION.get(), 1);
      foodPoints.put(ModItems.RICE.get(), 2);
      class_1646.field_18526 = foodPoints;
   }
}
