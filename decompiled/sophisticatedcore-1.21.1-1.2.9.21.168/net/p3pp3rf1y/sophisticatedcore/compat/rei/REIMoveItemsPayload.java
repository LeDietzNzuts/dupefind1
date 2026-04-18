package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import java.util.ArrayList;
import java.util.List;
import me.shedaniel.rei.api.client.registry.transfer.TransferHandler.Context;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessorRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.impl.common.transfer.NewInputSlotCrafter;
import me.shedaniel.rei.impl.common.transfer.InputSlotCrafter.NotEnoughMaterialsException;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_124;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1729;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_8710;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_8710.class_9154;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;

public class REIMoveItemsPayload implements class_8710 {
   public static final class_9154<REIMoveItemsPayload> TYPE = new class_9154(SophisticatedCore.getRL("rei_move_items"));
   public static final class_9139<class_9129, REIMoveItemsPayload> STREAM_CODEC = class_9139.method_56435(
      class_9135.field_48547, p -> p.shiftDown, class_9135.field_48556, p -> p.tag, REIMoveItemsPayload::new
   );
   private final boolean shiftDown;
   private final class_2487 tag;

   @Environment(EnvType.CLIENT)
   public REIMoveItemsPayload(
      Context context, boolean shiftDown, List<InputIngredient<class_1799>> inputs, Iterable<SlotAccessor> inputSlots, Iterable<SlotAccessor> inventorySlots
   ) {
      this.shiftDown = shiftDown;
      this.tag = save(context, inputs, inputSlots, inventorySlots);
   }

   public REIMoveItemsPayload(boolean shiftDown, class_2487 tag) {
      this.shiftDown = shiftDown;
      this.tag = tag;
   }

   public class_9154<? extends class_8710> method_56479() {
      return TYPE;
   }

   public static void handlePayload(REIMoveItemsPayload payload, net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking.Context context) {
      class_3222 player = context.player();
      class_1703 container = player.field_7512;

      try {
         boolean shift = payload.shiftDown;

         try {
            class_2487 nbt = payload.tag;
            int version = nbt.method_10550("Version");
            if (version != 1) {
               throw new IllegalStateException("Server and client REI protocol version mismatch! Server: 1, Client: " + version);
            }

            List<InputIngredient<class_1799>> inputs = readInputs(nbt.method_10554("Inputs", 10));
            List<SlotAccessor> input = readSlots(container, player, nbt.method_10554("InputSlots", 10));
            List<SlotAccessor> inventory = readSlots(container, player, nbt.method_10554("InventorySlots", 10));
            NewInputSlotCrafter<class_1703, class_1263> crafter = new NewInputSlotCrafter<class_1703, class_1263>(container, input, inventory, inputs) {
               protected void fillInputSlot(SlotAccessor slot, class_1799 toBeTakenStack) {
                  SlotAccessor takenSlot = this.takeInventoryStack(toBeTakenStack);
                  if (takenSlot != null) {
                     class_1799 takenStack = takenSlot.getItemStack().method_7972();
                     if (!takenStack.method_7960()) {
                        if (takenStack.method_7947() > 1) {
                           takenSlot.takeStack(1);
                        } else {
                           takenSlot.setItemStack(class_1799.field_8037);
                        }

                        takenStack.method_7939(1);
                        if (!slot.canPlace(takenStack)) {
                           return;
                        }

                        if (slot.getItemStack().method_7960()) {
                           slot.setItemStack(takenStack);
                        } else {
                           class_1799 stack = slot.getItemStack();
                           stack.method_7933(1);
                           slot.setItemStack(stack);
                        }
                     }
                  }
               }
            };
            crafter.fillInputSlots(player, shift);
         } catch (NotEnoughMaterialsException var11) {
            if (!(container instanceof class_1729)) {
               return;
            }
         } catch (IllegalStateException var12) {
            player.method_43496(class_2561.method_43471(var12.getMessage()).method_27692(class_124.field_1061));
         } catch (Exception var13) {
            player.method_43496(class_2561.method_43471(var13.getMessage()).method_27692(class_124.field_1061));
            var13.printStackTrace();
         }
      } catch (Exception var14) {
         var14.printStackTrace();
      }
   }

   @Environment(EnvType.CLIENT)
   private static class_2487 save(
      Context context, List<InputIngredient<class_1799>> inputs, Iterable<SlotAccessor> inputSlots, Iterable<SlotAccessor> inventorySlots
   ) {
      class_2487 tag = new class_2487();
      tag.method_10569("Version", 1);
      tag.method_10566("Inputs", saveInputs(inputs));
      tag.method_10566("InventorySlots", saveSlots(context, inventorySlots));
      tag.method_10566("InputSlots", saveSlots(context, inputSlots));
      return tag;
   }

   @Environment(EnvType.CLIENT)
   private static class_2520 saveSlots(Context context, Iterable<SlotAccessor> slots) {
      class_2499 tag = new class_2499();

      for (SlotAccessor slot : slots) {
         tag.add(SlotAccessorRegistry.getInstance().save(context.getMenu(), context.getMinecraft().field_1724, slot));
      }

      return tag;
   }

   @Environment(EnvType.CLIENT)
   private static class_2520 saveInputs(List<InputIngredient<class_1799>> inputs) {
      class_2499 tag = new class_2499();

      for (InputIngredient<class_1799> input : inputs) {
         class_2487 innerTag = new class_2487();
         innerTag.method_10566("Ingredient", EntryIngredients.ofItemStacks(input.get()).saveIngredient());
         innerTag.method_10569("Index", input.getIndex());
         tag.add(innerTag);
      }

      return tag;
   }

   private static List<SlotAccessor> readSlots(class_1703 menu, class_1657 player, class_2499 tag) {
      List<SlotAccessor> slots = new ArrayList<>();

      for (class_2520 t : tag) {
         slots.add(SlotAccessorRegistry.getInstance().read(menu, player, (class_2487)t));
      }

      return slots;
   }

   private static List<InputIngredient<class_1799>> readInputs(class_2499 tag) {
      List<InputIngredient<class_1799>> inputs = new ArrayList<>();

      for (class_2520 t : tag) {
         class_2487 compoundTag = (class_2487)t;
         InputIngredient<EntryStack<?>> stacks = InputIngredient.of(
            compoundTag.method_10550("Index"), EntryIngredient.read(compoundTag.method_10554("Ingredient", 10))
         );
         inputs.add(InputIngredient.withType(stacks, VanillaEntryTypes.ITEM));
      }

      return inputs;
   }
}
