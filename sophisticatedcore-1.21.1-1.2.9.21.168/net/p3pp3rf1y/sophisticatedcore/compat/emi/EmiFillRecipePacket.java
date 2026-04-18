package net.p3pp3rf1y.sophisticatedcore.compat.emi;

import com.google.common.collect.Lists;
import dev.emi.emi.runtime.EmiLog;
import java.util.ArrayList;
import java.util.List;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public record EmiFillRecipePacket(int syncId, int action, List<Integer> slots, List<Integer> crafting, int output, List<class_1799> stacks)
   implements class_8710 {
   public static final class_9154<EmiFillRecipePacket> TYPE = new class_9154(SophisticatedCore.getRL("emi_fill_recipe"));
   public static final class_9139<class_9129, EmiFillRecipePacket> STREAM_CODEC = class_9139.method_58025(
      class_9135.field_49675,
      EmiFillRecipePacket::syncId,
      class_9135.field_49675,
      EmiFillRecipePacket::action,
      class_9135.field_49675.method_56433(class_9135.method_56363()),
      EmiFillRecipePacket::slots,
      class_9135.field_49675.method_56433(class_9135.method_56363()),
      EmiFillRecipePacket::crafting,
      class_9135.field_49675,
      EmiFillRecipePacket::output,
      class_1799.field_49269,
      EmiFillRecipePacket::stacks,
      EmiFillRecipePacket::new
   );

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(EmiFillRecipePacket payload, Context context) {
      if (payload.slots != null && payload.crafting != null) {
         class_3222 player = context.player();
         class_1703 handler = player.field_7512;
         if (handler != null && handler.field_7763 == payload.syncId && handler instanceof StorageContainerMenuBase<?> container) {
            ArrayList slots = Lists.newArrayList();
            ArrayList crafting = Lists.newArrayList();
            class_1735 output = null;

            for (int i : payload.slots) {
               if (i < 0 || i >= container.getTotalSlotsNumber()) {
                  EmiLog.error("Client requested fill but passed input slots don't exist, aborting");
                  return;
               }

               slots.add(container.method_7611(i));
            }

            for (int i : payload.crafting) {
               if (i >= 0 && i < container.getTotalSlotsNumber()) {
                  crafting.add(container.method_7611(i));
               } else {
                  crafting.add(null);
               }
            }

            if (payload.output != -1 && payload.output >= 0 && payload.output < container.getTotalSlotsNumber()) {
               output = container.method_7611(payload.output);
            }

            if (crafting.size() >= payload.stacks.size()) {
               List<class_1799> rubble = Lists.newArrayList();

               for (class_1735 s : crafting) {
                  if (s != null && s.method_7674(player) && !s.method_7677().method_7960()) {
                     rubble.add(s.method_7677().method_7972());
                     s.method_53512(class_1799.field_8037);
                  }
               }

               try {
                  int ix = 0;

                  while (true) {
                     if (ix >= payload.stacks.size()) {
                        if (output != null) {
                           if (payload.action == 1) {
                              handler.method_7593(output.field_7874, 0, class_1713.field_7790, player);
                           } else if (payload.action == 2) {
                              handler.method_7593(output.field_7874, 0, class_1713.field_7794, player);
                              return;
                           }

                           return;
                        }

                        return;
                     }

                     class_1799 stack = payload.stacks.get(ix);
                     if (!stack.method_7960()) {
                        int gotten = grabMatching(player, slots, rubble, crafting, stack);
                        if (gotten != stack.method_7947()) {
                           if (gotten > 0) {
                              stack.method_7939(gotten);
                              player.method_31548().method_7398(stack);
                           }
                           break;
                        }

                        class_1735 sx = (class_1735)crafting.get(ix);
                        if (sx != null && sx.method_7680(stack) && stack.method_7947() <= sx.method_7675()) {
                           sx.method_53512(stack);
                        } else {
                           player.method_31548().method_7398(stack);
                        }
                     }

                     ix++;
                  }
               } finally {
                  for (class_1799 stack : rubble) {
                     player.method_31548().method_7398(stack);
                  }
               }
            }
         } else {
            EmiLog.warn("Client requested fill but screen handler has changed, aborting");
         }
      } else {
         EmiLog.error("Client requested fill but passed input and crafting slot information was invalid, aborting");
      }
   }

   private static int grabMatching(class_1657 player, List<class_1735> slots, List<class_1799> rubble, List<class_1735> crafting, class_1799 stack) {
      int amount = stack.method_7947();
      int grabbed = 0;

      for (int i = 0; i < rubble.size(); i++) {
         if (grabbed >= amount) {
            return grabbed;
         }

         class_1799 r = rubble.get(i);
         if (class_1799.method_31577(stack, r)) {
            int wanted = amount - grabbed;
            if (r.method_7947() <= wanted) {
               grabbed += r.method_7947();
               rubble.remove(i);
               i--;
            } else {
               grabbed = amount;
               r.method_7939(r.method_7947() - wanted);
            }
         }
      }

      for (class_1735 s : slots) {
         if (grabbed >= amount) {
            return grabbed;
         }

         if (!crafting.contains(s) && s.method_7674(player)) {
            class_1799 st = s.method_7677();
            if (class_1799.method_31577(stack, st)) {
               int wanted = amount - grabbed;
               if (st.method_7947() <= wanted) {
                  grabbed += st.method_7947();
                  s.method_53512(class_1799.field_8037);
               } else {
                  grabbed = amount;
                  st.method_7939(st.method_7947() - wanted);
                  s.method_53512(st);
               }
            }
         }
      }

      return grabbed;
   }
}
