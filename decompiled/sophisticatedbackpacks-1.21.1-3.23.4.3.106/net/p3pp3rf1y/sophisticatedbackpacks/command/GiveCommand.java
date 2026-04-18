package net.p3pp3rf1y.sophisticatedbackpacks.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import java.util.Collection;
import java.util.UUID;
import net.minecraft.class_1542;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2186;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_7923;
import net.minecraft.class_9334;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackAccessLogger;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.BackpackWrapper;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper.IBackpackWrapper;
import net.p3pp3rf1y.sophisticatedcore.util.RandHelper;

public class GiveCommand {
   private GiveCommand() {
   }

   static ArgumentBuilder<class_2168, ?> register() {
      return class_2170.method_9247("give")
         .then(
            class_2170.method_9244("targets", class_2186.method_9308())
               .then(
                  class_2170.method_9244("backpackUuid", BackpackUUIDArgumentType.backpackUuid())
                     .executes(
                        context -> giveBackpack(
                           (class_2168)context.getSource(), (UUID)context.getArgument("backpackUuid", UUID.class), class_2186.method_9312(context, "targets")
                        )
                     )
               )
         );
   }

   private static int giveBackpack(class_2168 source, UUID backpackUuid, Collection<class_3222> players) {
      BackpackAccessLogger.getBackpackLog(backpackUuid)
         .ifPresent(
            alr -> {
               class_1792 item = (class_1792)class_7923.field_41178.method_10223(alr.getBackpackItemRegistryName());
               class_1799 backpack = new class_1799(item);
               if (!backpack.method_7964().getString().equals(alr.getBackpackName())) {
                  backpack.sophisticatedCore_set(class_9334.field_49631, class_2561.method_43470(alr.getBackpackName()));
               }

               IBackpackWrapper backpackWrapper = BackpackWrapper.fromStack(backpack);
               backpackWrapper.setColors(alr.getClothColor(), alr.getTrimColor());
               backpackWrapper.setColumnsTaken(alr.getColumnsTaken(), false);
               backpackWrapper.setContentsUuid(backpackUuid);
               players.forEach(p -> giveBackpackToPlayer(backpack, p));
               if (players.size() == 1) {
                  source.method_9226(
                     () -> class_2561.method_43469("commands.sophisticatedbackpacks.give.success", new Object[]{players.iterator().next().method_5476()}), true
                  );
               } else {
                  source.method_9226(() -> class_2561.method_43469("commands.sophisticatedbackpacks.give.success", new Object[]{players.size()}), true);
               }
            }
         );
      return 0;
   }

   private static void giveBackpackToPlayer(class_1799 backpack, class_3222 p) {
      boolean flag = p.method_31548().method_7394(backpack);
      if (flag && backpack.method_7960()) {
         backpack.method_7939(1);
         class_1542 itemEntity = p.method_7328(backpack, false);
         if (itemEntity != null) {
            itemEntity.method_6987();
         }

         p.method_37908()
            .method_43128(
               null,
               p.method_23317(),
               p.method_23318(),
               p.method_23321(),
               class_3417.field_15197,
               class_3419.field_15248,
               0.2F,
               (RandHelper.getRandomMinusOneToOne(p.method_59922()) * 0.7F + 1.0F) * 2.0F
            );
         p.field_7498.method_7623();
      } else {
         class_1542 itementity = p.method_7328(backpack, false);
         if (itementity != null) {
            itementity.method_6975();
            itementity.method_6981(p);
         }
      }

      class_1542 itemEntity = p.method_7328(backpack, false);
      if (itemEntity != null) {
         itemEntity.method_6987();
      }
   }
}
