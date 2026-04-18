package dev.architectury.event.events.common;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import dev.architectury.event.EventResult;
import net.minecraft.class_1263;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1542;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_3222;
import net.minecraft.class_3966;
import net.minecraft.class_5321;
import net.minecraft.class_8779;
import net.minecraft.class_1297.class_5529;
import org.jetbrains.annotations.Nullable;

public interface PlayerEvent {
   Event<PlayerEvent.PlayerJoin> PLAYER_JOIN = EventFactory.createLoop();
   Event<PlayerEvent.PlayerQuit> PLAYER_QUIT = EventFactory.createLoop();
   Event<PlayerEvent.PlayerRespawn> PLAYER_RESPAWN = EventFactory.createLoop();
   Event<PlayerEvent.PlayerAdvancement> PLAYER_ADVANCEMENT = EventFactory.createLoop();
   Event<PlayerEvent.PlayerClone> PLAYER_CLONE = EventFactory.createLoop();
   Event<PlayerEvent.CraftItem> CRAFT_ITEM = EventFactory.createLoop();
   Event<PlayerEvent.SmeltItem> SMELT_ITEM = EventFactory.createLoop();
   Event<PlayerEvent.PickupItemPredicate> PICKUP_ITEM_PRE = EventFactory.createEventResult();
   Event<PlayerEvent.PickupItem> PICKUP_ITEM_POST = EventFactory.createLoop();
   Event<PlayerEvent.ChangeDimension> CHANGE_DIMENSION = EventFactory.createLoop();
   Event<PlayerEvent.DropItem> DROP_ITEM = EventFactory.createEventResult();
   Event<PlayerEvent.OpenMenu> OPEN_MENU = EventFactory.createLoop();
   Event<PlayerEvent.CloseMenu> CLOSE_MENU = EventFactory.createLoop();
   Event<PlayerEvent.FillBucket> FILL_BUCKET = EventFactory.createCompoundEventResult();
   Event<PlayerEvent.AttackEntity> ATTACK_ENTITY = EventFactory.createEventResult();

   public interface AttackEntity {
      EventResult attack(class_1657 var1, class_1937 var2, class_1297 var3, class_1268 var4, @Nullable class_3966 var5);
   }

   public interface ChangeDimension {
      void change(class_3222 var1, class_5321<class_1937> var2, class_5321<class_1937> var3);
   }

   public interface CloseMenu {
      void close(class_1657 var1, class_1703 var2);
   }

   public interface CraftItem {
      void craft(class_1657 var1, class_1799 var2, class_1263 var3);
   }

   public interface DropItem {
      EventResult drop(class_1657 var1, class_1542 var2);
   }

   public interface FillBucket {
      CompoundEventResult<class_1799> fill(class_1657 var1, class_1937 var2, class_1799 var3, @Nullable class_239 var4);
   }

   public interface OpenMenu {
      void open(class_1657 var1, class_1703 var2);
   }

   public interface PickupItem {
      void pickup(class_1657 var1, class_1542 var2, class_1799 var3);
   }

   public interface PickupItemPredicate {
      EventResult canPickup(class_1657 var1, class_1542 var2, class_1799 var3);
   }

   public interface PlayerAdvancement {
      void award(class_3222 var1, class_8779 var2);
   }

   public interface PlayerClone {
      void clone(class_3222 var1, class_3222 var2, boolean var3);
   }

   public interface PlayerJoin {
      void join(class_3222 var1);
   }

   public interface PlayerQuit {
      void quit(class_3222 var1);
   }

   public interface PlayerRespawn {
      void respawn(class_3222 var1, boolean var2, class_5529 var3);
   }

   public interface SmeltItem {
      void smelt(class_1657 var1, class_1799 var2);
   }
}
