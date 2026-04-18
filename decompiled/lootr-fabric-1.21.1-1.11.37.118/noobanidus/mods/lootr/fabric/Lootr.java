package noobanidus.mods.lootr.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.fabric.config.LootrConfigInit;
import noobanidus.mods.lootr.fabric.event.LootrEventsInit;
import noobanidus.mods.lootr.fabric.impl.LootrAPIImpl;
import noobanidus.mods.lootr.fabric.impl.LootrRegistryImpl;
import noobanidus.mods.lootr.fabric.impl.PlatformAPIImpl;
import noobanidus.mods.lootr.fabric.init.ModAdvancements;
import noobanidus.mods.lootr.fabric.init.ModBlockEntities;
import noobanidus.mods.lootr.fabric.init.ModBlocks;
import noobanidus.mods.lootr.fabric.init.ModEntities;
import noobanidus.mods.lootr.fabric.init.ModItems;
import noobanidus.mods.lootr.fabric.init.ModLoot;
import noobanidus.mods.lootr.fabric.init.ModParticles;
import noobanidus.mods.lootr.fabric.init.ModStats;
import noobanidus.mods.lootr.fabric.init.ModTabs;
import noobanidus.mods.lootr.fabric.network.LootrNetworkingInit;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketCloseContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenCart;
import noobanidus.mods.lootr.fabric.network.to_client.PacketOpenContainer;
import noobanidus.mods.lootr.fabric.network.to_client.PacketPerformBreakEffect;
import noobanidus.mods.lootr.fabric.network.to_client.PacketRefreshSection;
import noobanidus.mods.lootr.fabric.network.to_server.PacketRequestUpdate;

public class Lootr implements ModInitializer {
   public void onInitialize() {
      LootrAPI.INSTANCE = new LootrAPIImpl();
      LootrRegistry.INSTANCE = new LootrRegistryImpl();
      PlatformAPI.INSTANCE = new PlatformAPIImpl();
      PayloadTypeRegistry.playS2C().register(PacketOpenCart.TYPE, PacketOpenCart.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PacketCloseCart.TYPE, PacketCloseCart.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PacketOpenContainer.TYPE, PacketOpenContainer.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PacketCloseContainer.TYPE, PacketCloseContainer.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PacketRefreshSection.TYPE, PacketRefreshSection.STREAM_CODEC);
      PayloadTypeRegistry.playS2C().register(PacketPerformBreakEffect.TYPE, PacketPerformBreakEffect.STREAM_CODEC);
      PayloadTypeRegistry.playC2S().register(PacketRequestUpdate.TYPE, PacketRequestUpdate.STREAM_CODEC);
      LootrNetworkingInit.register();
      LootrConfigInit.registerConfig();
      ModItems.registerItems();
      ModBlocks.registerBlocks();
      ModTabs.registerTabs();
      ModBlockEntities.registerBlockEntities();
      ModEntities.registerEntities();
      ModLoot.registerLoot();
      LootrEventsInit.registerEvents();
      ModStats.registerStats();
      ModAdvancements.registerAdvancements();
      ModParticles.register();
   }
}
