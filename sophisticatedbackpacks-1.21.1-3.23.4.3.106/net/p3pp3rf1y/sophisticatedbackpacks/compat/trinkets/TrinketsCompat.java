package net.p3pp3rf1y.sophisticatedbackpacks.compat.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketInventory;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Function;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_1263;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.p3pp3rf1y.sophisticatedbackpacks.backpack.BackpackItem;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedbackpacks.util.PlayerInventoryProvider;
import net.p3pp3rf1y.sophisticatedcore.compat.ICompat;

public class TrinketsCompat implements ICompat {
   private static final BackpackTrinket TRINKET_BACKPACK = new BackpackTrinket();
   private static final int TAGS_REFRESH_COOLDOWN = 100;
   private final Set<String> backpackTrinketIdentifiers = new CopyOnWriteArraySet<>();
   private long lastTagsRefresh = -1L;

   public static <T> T getFromTrinketInventory(class_1657 player, String identifier, Function<TrinketInventory, T> getFromHandler, T defaultValue) {
      return TrinketsApi.getTrinketComponent(player).map(comp -> {
         String[] identifiers = identifier.split("/");
         if (identifiers.length == 2 && comp.getInventory().containsKey(identifiers[0])) {
            Map<String, TrinketInventory> group = (Map<String, TrinketInventory>)comp.getInventory().get(identifiers[0]);
            if (group.containsKey(identifiers[1])) {
               return getFromHandler.apply(group.get(identifiers[1]));
            }
         }

         return defaultValue;
      }).orElse(defaultValue);
   }

   public static boolean isTrinketContainer(class_1263 container) {
      return container instanceof TrinketInventory;
   }

   public static String getIdentifierForSlot(class_1263 container) {
      return container instanceof TrinketInventory trinketInventory
         ? trinketInventory.getSlotType().getGroup() + "/" + trinketInventory.getSlotType().getName()
         : "";
   }

   public void init() {
      PlayerInventoryProvider.get()
         .addPlayerInventoryHandler(
            "trinkets",
            this::getTrinketTags,
            (player, identifier) -> getFromTrinketInventory(player, identifier, TrinketInventory::method_5439, 0),
            (player, identifier, slot) -> getFromTrinketInventory(player, identifier, ti -> ti.method_5438(slot), class_1799.field_8037),
            false,
            true,
            true,
            true
         );
   }

   public void setup() {
      for (Supplier<BackpackItem> backpack : ModItems.BACKPACKS) {
         TrinketsApi.registerTrinket((class_1792)backpack.get(), TRINKET_BACKPACK);
         if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            TrinketRendererRegistry.registerRenderer((class_1792)backpack.get(), TRINKET_BACKPACK);
         }
      }
   }

   private Set<String> getTrinketTags(class_1657 player, long gameTime) {
      if (this.lastTagsRefresh + 100L < gameTime) {
         this.lastTagsRefresh = gameTime;
         this.backpackTrinketIdentifiers.clear();
         TrinketsApi.getTrinketComponent(player).ifPresent(comp -> {
            class_1799 backpack = new class_1799((class_1935)ModItems.BACKPACK.get());

            for (Entry<String, Map<String, TrinketInventory>> group : comp.getInventory().entrySet()) {
               for (Entry<String, TrinketInventory> inventory : group.getValue().entrySet()) {
                  TrinketInventory trinketInventory = inventory.getValue();
                  SlotType slotType = trinketInventory.getSlotType();

                  for (int i = 0; i < trinketInventory.method_5439(); i++) {
                     SlotReference ref = new SlotReference(trinketInventory, i);
                     if (TrinketsApi.evaluatePredicateSet(slotType.getValidatorPredicates(), backpack, ref, player)) {
                        this.backpackTrinketIdentifiers.add(group.getKey() + "/" + inventory.getKey());
                     }
                  }
               }
            }
         });
      }

      return this.backpackTrinketIdentifiers;
   }
}
