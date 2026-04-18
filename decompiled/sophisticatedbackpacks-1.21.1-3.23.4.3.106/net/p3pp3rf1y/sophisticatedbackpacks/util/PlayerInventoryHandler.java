package net.p3pp3rf1y.sophisticatedbackpacks.util;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiFunction;
import net.minecraft.class_1657;
import net.minecraft.class_1799;

public class PlayerInventoryHandler {
   public static final Set<String> SINGLE_IDENTIFIER = Collections.singleton("");
   private final BiFunction<class_1657, Long, Set<String>> identifiersGetter;
   private final PlayerInventoryHandler.SlotCountGetter slotCountGetter;
   private final PlayerInventoryHandler.SlotStackGetter slotStackGetter;
   private final boolean visibleInGui;
   private final boolean ownRenderer;
   private final boolean accessibleByAnotherPlayer;

   public PlayerInventoryHandler(
      BiFunction<class_1657, Long, Set<String>> identifiersGetter,
      PlayerInventoryHandler.SlotCountGetter slotCountGetter,
      PlayerInventoryHandler.SlotStackGetter slotStackGetter,
      boolean visibleInGui,
      boolean ownRenderer,
      boolean accessibleByAnotherPlayer
   ) {
      this.identifiersGetter = identifiersGetter;
      this.slotCountGetter = slotCountGetter;
      this.slotStackGetter = slotStackGetter;
      this.visibleInGui = visibleInGui;
      this.ownRenderer = ownRenderer;
      this.accessibleByAnotherPlayer = accessibleByAnotherPlayer;
   }

   public int getSlotCount(class_1657 player, String identifier) {
      return this.slotCountGetter.getSlotCount(player, identifier);
   }

   public class_1799 getStackInSlot(class_1657 player, String identifier, int slot) {
      return this.slotStackGetter.getStackInSlot(player, identifier, slot);
   }

   public boolean isVisibleInGui() {
      return this.visibleInGui;
   }

   public Set<String> getIdentifiers(class_1657 player, long gameTime) {
      return this.identifiersGetter.apply(player, gameTime);
   }

   public boolean hasItsOwnRenderer() {
      return this.ownRenderer;
   }

   public boolean isAccessibleByAnotherPlayer() {
      return this.accessibleByAnotherPlayer;
   }

   public interface SlotCountGetter {
      int getSlotCount(class_1657 var1, String var2);
   }

   public interface SlotStackGetter {
      class_1799 getStackInSlot(class_1657 var1, String var2, int var3);
   }
}
