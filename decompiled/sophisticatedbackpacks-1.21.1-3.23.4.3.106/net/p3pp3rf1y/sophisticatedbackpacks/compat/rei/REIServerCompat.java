package net.p3pp3rf1y.sophisticatedbackpacks.compat.rei;

import java.util.function.Supplier;
import me.shedaniel.rei.api.common.entry.comparison.EntryComparator;
import me.shedaniel.rei.api.common.entry.comparison.ItemComparatorRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_9323;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import net.minecraft.class_9323.class_9324;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;

public class REIServerCompat implements REIServerPlugin {
   public double getPriority() {
      return 0.0;
   }

   public void registerItemComparators(ItemComparatorRegistry registry) {
      EntryComparator<class_1799> colorTag = (context, stack) -> {
         IBackpackWrapper wrapper = BackpackWrapper.fromStack(stack);
         class_9324 builder = class_9323.method_57827();
         builder.method_57840((class_9331)ModCoreDataComponents.MAIN_COLOR.get(), wrapper.getMainColor());
         builder.method_57840((class_9331)ModCoreDataComponents.ACCENT_COLOR.get(), wrapper.getAccentColor());
         return EntryComparator.component(new class_9331[0]).hash(context, new class_9335(builder.method_57838()));
      };
      registry.register(colorTag, (class_1792[])ModItems.BACKPACKS.stream().map(Supplier::get).toArray(BackpackItem[]::new));
   }
}
