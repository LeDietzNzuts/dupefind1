package ewewukek.musketmod;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents.EndWorldTick;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents.AllowEnchanting;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.util.TriState;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_2338;
import net.minecraft.class_2378;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3264;
import net.minecraft.class_3300;
import net.minecraft.class_3695;
import net.minecraft.class_3902;
import net.minecraft.class_7706;
import net.minecraft.class_7923;
import net.minecraft.class_1299.class_1300;
import net.minecraft.class_3302.class_4045;

public class MusketMod implements ModInitializer {
   public static final String MODID = "musketmod";
   public static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("musketmod.txt");

   public static class_2960 resource(String path) {
      return class_2960.method_60655("musketmod", path);
   }

   public void onInitialize() {
      Config.load();
      Items.registerDataComponentTypes((path, component) -> class_2378.method_10230(class_7923.field_49658, resource(path), component));
      Items.register((path, item) -> class_2378.method_10230(class_7923.field_41178, resource(path), item));
      ItemGroupEvents.modifyEntriesEvent(class_7706.field_40202)
         .register((ModifyEntries)entries -> Items.addToCreativeTab(class_7706.field_40202, item -> entries.method_45421(item)));
      ItemGroupEvents.modifyEntriesEvent(class_7706.field_41062)
         .register((ModifyEntries)entries -> Items.addToCreativeTab(class_7706.field_41062, item -> entries.method_45421(item)));
      Sounds.register(sound -> class_2378.method_10230(class_7923.field_41172, sound.method_14833(), sound));
      BulletEntity.register((string, entityType) -> class_2378.method_10230(class_7923.field_41177, resource(string), entityType));
      EnchantmentEvents.ALLOW_ENCHANTING
         .register((AllowEnchanting)(enchantment, stack, context) -> VanillaHelper.canEnchant(enchantment, stack) ? TriState.TRUE : TriState.DEFAULT);
      ServerTickEvents.END_WORLD_TICK.register((EndWorldTick)world -> DeferredDamage.apply());
      PayloadTypeRegistry.playS2C().register(SmokeEffectPacket.TYPE, SmokeEffectPacket.CODEC);
      ResourceManagerHelper.get(class_3264.field_14190)
         .registerReloadListener(
            new IdentifiableResourceReloadListener() {
               public class_2960 getFabricId() {
                  return MusketMod.resource("reload");
               }

               public CompletableFuture<Void> method_25931(
                  class_4045 stage,
                  class_3300 resourceManager,
                  class_3695 preparationsProfiler,
                  class_3695 reloadProfiler,
                  Executor backgroundExecutor,
                  Executor gameExecutor
               ) {
                  return stage.method_18352(class_3902.field_17274).thenRunAsync(() -> Config.load(), gameExecutor);
               }
            }
         );
   }

   public static void disableVelocityUpdate(class_1300<?> builder) {
      builder.alwaysUpdateVelocity(false);
   }

   public static void sendSmokeEffect(class_3218 level, class_243 origin, class_243 direction) {
      SmokeEffectPacket packet = SmokeEffectPacket.fromVec3(origin, direction);
      class_2338 blockPos = class_2338.method_49637(origin.field_1352, origin.field_1351, origin.field_1350);

      for (class_3222 serverPlayer : PlayerLookup.tracking(level, blockPos)) {
         ServerPlayNetworking.send(serverPlayer, packet);
      }
   }
}
