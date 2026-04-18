package dev.architectury.event.fabric;

import dev.architectury.event.events.client.ClientCommandRegistrationEvent;
import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.event.events.client.ClientTooltipEvent;
import dev.architectury.event.events.common.ChatEvent;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.event.events.common.LootEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import dev.architectury.impl.fabric.ChatComponentImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents.ClientStarted;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents.ClientStopping;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.EndWorldTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartTick;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.StartWorldTick;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarted;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStarting;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopped;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.ServerStopping;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents.Load;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents.Unload;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents.Modify;
import net.fabricmc.fabric.api.message.v1.ServerMessageDecoratorEvent;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents.AllowChatMessage;
import net.minecraft.class_7492;

public class EventHandlerImpl {
   @Environment(EnvType.CLIENT)
   public static void registerClient() {
      ClientLifecycleEvents.CLIENT_STARTED.register((ClientStarted)instance -> ClientLifecycleEvent.CLIENT_STARTED.invoker().stateChanged(instance));
      ClientLifecycleEvents.CLIENT_STOPPING.register((ClientStopping)instance -> ClientLifecycleEvent.CLIENT_STOPPING.invoker().stateChanged(instance));
      ClientTickEvents.START_CLIENT_TICK.register((StartTick)instance -> ClientTickEvent.CLIENT_PRE.invoker().tick(instance));
      ClientTickEvents.END_CLIENT_TICK.register((EndTick)instance -> ClientTickEvent.CLIENT_POST.invoker().tick(instance));
      ClientTickEvents.START_WORLD_TICK.register((StartWorldTick)instance -> ClientTickEvent.CLIENT_LEVEL_PRE.invoker().tick(instance));
      ClientTickEvents.END_WORLD_TICK.register((EndWorldTick)instance -> ClientTickEvent.CLIENT_LEVEL_POST.invoker().tick(instance));
      ItemTooltipCallback.EVENT
         .register(
            (ItemTooltipCallback)(itemStack, tooltipContext, tooltipFlag, list) -> ClientTooltipEvent.ITEM
               .invoker()
               .append(itemStack, list, tooltipContext, tooltipFlag)
         );
      HudRenderCallback.EVENT.register((HudRenderCallback)(graphics, tickDelta) -> ClientGuiEvent.RENDER_HUD.invoker().renderHud(graphics, tickDelta));
      ClientCommandRegistrationCallback.EVENT
         .register((ClientCommandRegistrationCallback)(dispatcher, access) -> ClientCommandRegistrationEvent.EVENT.invoker().register(dispatcher, access));
   }

   public static void registerCommon() {
      ServerLifecycleEvents.SERVER_STARTING.register((ServerStarting)instance -> LifecycleEvent.SERVER_BEFORE_START.invoker().stateChanged(instance));
      ServerLifecycleEvents.SERVER_STARTED.register((ServerStarted)instance -> LifecycleEvent.SERVER_STARTED.invoker().stateChanged(instance));
      ServerLifecycleEvents.SERVER_STOPPING.register((ServerStopping)instance -> LifecycleEvent.SERVER_STOPPING.invoker().stateChanged(instance));
      ServerLifecycleEvents.SERVER_STOPPED.register((ServerStopped)instance -> LifecycleEvent.SERVER_STOPPED.invoker().stateChanged(instance));
      ServerTickEvents.START_SERVER_TICK
         .register((net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartTick)instance -> TickEvent.SERVER_PRE.invoker().tick(instance));
      ServerTickEvents.END_SERVER_TICK
         .register((net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndTick)instance -> TickEvent.SERVER_POST.invoker().tick(instance));
      ServerTickEvents.START_WORLD_TICK
         .register((net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.StartWorldTick)instance -> TickEvent.SERVER_LEVEL_PRE.invoker().tick(instance));
      ServerTickEvents.END_WORLD_TICK
         .register((net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick)instance -> TickEvent.SERVER_LEVEL_POST.invoker().tick(instance));
      ServerWorldEvents.LOAD.register((Load)(server, world) -> LifecycleEvent.SERVER_LEVEL_LOAD.invoker().act(world));
      ServerWorldEvents.UNLOAD.register((Unload)(server, world) -> LifecycleEvent.SERVER_LEVEL_UNLOAD.invoker().act(world));
      CommandRegistrationCallback.EVENT
         .register(
            (CommandRegistrationCallback)(dispatcher, registry, selection) -> CommandRegistrationEvent.EVENT
               .invoker()
               .register(dispatcher, registry, selection)
         );
      UseItemCallback.EVENT.register((UseItemCallback)(player, world, hand) -> InteractionEvent.RIGHT_CLICK_ITEM.invoker().click(player, hand).asMinecraft());
      UseBlockCallback.EVENT
         .register(
            (UseBlockCallback)(player, world, hand, hitResult) -> InteractionEvent.RIGHT_CLICK_BLOCK
               .invoker()
               .click(player, hand, hitResult.method_17777(), hitResult.method_17780())
               .asMinecraft()
         );
      AttackBlockCallback.EVENT
         .register(
            (AttackBlockCallback)(player, world, hand, pos, face) -> InteractionEvent.LEFT_CLICK_BLOCK.invoker().click(player, hand, pos, face).asMinecraft()
         );
      AttackEntityCallback.EVENT
         .register(
            (AttackEntityCallback)(player, world, hand, entity, hitResult) -> PlayerEvent.ATTACK_ENTITY
               .invoker()
               .attack(player, world, entity, hand, hitResult)
               .asMinecraft()
         );
      LootTableEvents.MODIFY
         .register(
            (Modify)(key, tableBuilder, source) -> LootEvent.MODIFY_LOOT_TABLE
               .invoker()
               .modifyLootTable(key, new LootTableModificationContextImpl(tableBuilder), source.isBuiltin())
         );
      ServerMessageDecoratorEvent.EVENT.register(ServerMessageDecoratorEvent.CONTENT_PHASE, (class_7492)(player, component) -> {
         ChatEvent.ChatComponent chatComponent = new ChatComponentImpl(component);
         ChatEvent.DECORATE.invoker().decorate(player, chatComponent);
         return chatComponent.get();
      });
      ServerMessageEvents.ALLOW_CHAT_MESSAGE
         .register((AllowChatMessage)(message, sender, params) -> !ChatEvent.RECEIVED.invoker().received(sender, message.method_46291()).isFalse());
   }

   @Environment(EnvType.SERVER)
   public static void registerServer() {
   }
}
