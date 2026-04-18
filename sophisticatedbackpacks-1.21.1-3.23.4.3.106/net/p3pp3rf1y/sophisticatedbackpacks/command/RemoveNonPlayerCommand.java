package net.p3pp3rf1y.sophisticatedbackpacks.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2561;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackStorage;

public class RemoveNonPlayerCommand {
   private RemoveNonPlayerCommand() {
   }

   static ArgumentBuilder<class_2168, ?> register() {
      return class_2170.method_9247("removeNonPlayer")
         .then(
            class_2170.method_9244("onlyWithEmptyInventory", BoolArgumentType.bool())
               .executes(context -> removeNonPlayerBackpacks((class_2168)context.getSource(), BoolArgumentType.getBool(context, "onlyWithEmptyInventory")))
         );
   }

   private static int removeNonPlayerBackpacks(class_2168 source, boolean onlyWithEmptyInventory) {
      int numberRemoved = BackpackStorage.get().removeNonPlayerBackpackContents(onlyWithEmptyInventory);
      source.method_9226(() -> class_2561.method_43469("commands.sophisticatedbackpacks.remove_non_player.success", new Object[]{numberRemoved}), false);
      return 0;
   }
}
