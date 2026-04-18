package noobanidus.mods.lootr.common.command;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import net.minecraft.class_124;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1923;
import net.minecraft.class_1937;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2172;
import net.minecraft.class_2181;
import net.minecraft.class_2186;
import net.minecraft.class_2232;
import net.minecraft.class_2248;
import net.minecraft.class_2262;
import net.minecraft.class_2277;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_2383;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2564;
import net.minecraft.class_2583;
import net.minecraft.class_2586;
import net.minecraft.class_2621;
import net.minecraft.class_2624;
import net.minecraft.class_2680;
import net.minecraft.class_2741;
import net.minecraft.class_2754;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_2861;
import net.minecraft.class_2960;
import net.minecraft.class_3193;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3312;
import net.minecraft.class_3341;
import net.minecraft.class_52;
import net.minecraft.class_5251;
import net.minecraft.class_5321;
import net.minecraft.class_7225;
import net.minecraft.class_7924;
import net.minecraft.class_9240;
import net.minecraft.class_2818.class_2819;
import net.minecraft.class_32.class_5143;
import net.minecraft.class_7225.class_7874;
import net.minecraft.server.MinecraftServer;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.command.ILootrCommandExtension;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.block.LootrBarrelBlock;
import noobanidus.mods.lootr.common.block.LootrChestBlock;
import noobanidus.mods.lootr.common.block.LootrShulkerBlock;
import noobanidus.mods.lootr.common.block.entity.BlockEntityTicker;
import noobanidus.mods.lootr.common.block.entity.LootrInventoryBlockEntity;
import noobanidus.mods.lootr.common.data.DataStorage;
import noobanidus.mods.lootr.common.data.LootrInventory;
import noobanidus.mods.lootr.common.data.LootrSavedData;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import noobanidus.mods.lootr.common.impl.LootrServiceRegistry;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinBaseContainerBlockEntity;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinChunkMap;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinMinecraftServer;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public class CommandLootr {
   private static List<class_5321<class_52>> tables = null;
   private static List<String> tableNames = null;
   private static final Pattern REGEX = Pattern.compile("^r\\.(-?[0-9]+)\\.(-?[0-9]+)\\.mca$");
   private static final FilenameFilter MCA_FILTER = (file, fileName) -> fileName.endsWith(".mca");

   private static List<class_5321<class_52>> getTables(MinecraftServer server) {
      if (tables == null) {
         tables = server.method_58576()
            .method_58289()
            .method_46759(class_7924.field_50079)
            .<Stream<class_5321<class_52>>>map(class_7225::method_46754)
            .orElse(Stream.of())
            .toList();
         tableNames = tables.stream().map(o -> o.method_29177().toString()).toList();
      }

      return tables;
   }

   private static List<String> getProfiles() {
      MinecraftServer server = LootrAPI.getServer();
      if (server == null) {
         return Collections.emptyList();
      } else {
         class_3312 cache = server.method_3793();
         return (List<String>)(cache == null ? Collections.emptyList() : Lists.newArrayList(cache.field_14312.keySet()));
      }
   }

   private static List<String> getTableNames(MinecraftServer server) {
      getTables(server);
      return tableNames;
   }

   public static void createBlock(class_2168 c, @Nullable class_2248 block, @Nullable class_5321<class_52> incomingTable) {
      class_1937 world = c.method_9225();
      class_243 incomingPos = c.method_9222();
      class_2338 pos = new class_2338((int)incomingPos.field_1352, (int)incomingPos.field_1351, (int)incomingPos.field_1350);
      class_5321<class_52> table;
      if (incomingTable == null) {
         table = getTables(c.method_9211()).get(world.method_8409().method_43048(getTables(c.method_9211()).size()));
      } else {
         table = incomingTable;
      }

      if (block == null) {
         LootrChestMinecartEntity cart = new LootrChestMinecartEntity(world, pos.method_10263() + 0.5, pos.method_10264() + 0.5, pos.method_10260() + 0.5);
         class_1297 e = c.method_9228();
         if (e != null) {
            cart.method_36456(e.method_36454());
         }

         cart.method_7562(table, world.method_8409().method_43055());
         world.method_8649(cart);
         c.method_9226(
            () -> class_2561.method_43469(
               "lootr.commands.summon",
               new Object[]{
                  class_2564.method_10885(
                     class_2561.method_43469("lootr.commands.blockpos", new Object[]{pos.method_10263(), pos.method_10264(), pos.method_10260()})
                        .method_10862(class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1060)).method_10982(true))
                  ),
                  table.toString()
               }
            ),
            false
         );
      } else {
         class_2680 placementState = block.method_9564();
         class_1297 e = c.method_9228();
         if (e != null) {
            class_2754<class_2350> prop = null;
            class_2350 dir = class_2350.method_10159(e)[0].method_10153();
            if (placementState.method_28498(LootrBarrelBlock.field_16320)) {
               prop = LootrBarrelBlock.field_16320;
            } else if (placementState.method_28498(LootrChestBlock.field_10768)) {
               prop = LootrChestBlock.field_10768;
               dir = e.method_5735().method_10153();
            } else if (placementState.method_28498(LootrShulkerBlock.field_11496)) {
               prop = LootrShulkerBlock.field_11496;
            }

            if (prop != null) {
               placementState = (class_2680)placementState.method_11657(prop, dir);
            }
         }

         world.method_8652(pos, placementState, 2);
         ILootrBlockEntity var13 = LootrAPI.resolveBlockEntity(world.method_8321(pos));
         if (var13 instanceof ILootrBlockEntity) {
            var13.setLootTableInternal(table, world.method_8409().method_43055());
         }

         c.method_9226(
            () -> class_2561.method_43469(
               "lootr.commands.create",
               new Object[]{
                  class_2561.method_43471(block.method_9539()),
                  class_2564.method_10885(
                     class_2561.method_43469("lootr.commands.blockpos", new Object[]{pos.method_10263(), pos.method_10264(), pos.method_10260()})
                        .method_10862(class_2583.field_24360.method_27703(class_5251.method_27718(class_124.field_1060)).method_10982(true))
                  ),
                  table.toString()
               }
            ),
            false
         );
      }
   }

   static class_2371<class_1799> copyItemList(class_2371<class_1799> reference) {
      class_2371<class_1799> contents = class_2371.method_10213(reference.size(), class_1799.field_8037);

      for (int i = 0; i < reference.size(); i++) {
         contents.set(i, ((class_1799)reference.get(i)).method_7972());
      }

      return contents;
   }

   public static void register(CommandDispatcher<class_2168> dispatcher) {
      dispatcher.register(builder((LiteralArgumentBuilder<class_2168>)class_2170.method_9247("lootr").requires(p -> p.method_9259(2))));
   }

   private static RequiredArgumentBuilder<class_2168, class_2960> suggestTables() {
      return class_2170.method_9244("table", class_2232.method_9441())
         .suggests((c, build) -> class_2172.method_9265(getTableNames(((class_2168)c.getSource()).method_9211()), build));
   }

   private static RequiredArgumentBuilder<class_2168, String> suggestProfiles() {
      return class_2170.method_9244("profile", StringArgumentType.string()).suggests((c, build) -> class_2172.method_9265(getProfiles(), build));
   }

   public static LiteralArgumentBuilder<class_2168> builder(LiteralArgumentBuilder<class_2168> builder) {
      builder.executes(
         c -> {
            ((class_2168)c.getSource())
               .method_9226(
                  () -> class_2561.method_43469(
                     "lootr.commands.usage", new Object[]{class_2561.method_43470(LootrServiceRegistry.getCommandExtensionsString())}
                  ),
                  false
               );
            return 1;
         }
      );

      for (ILootrCommandExtension extension : LootrServiceRegistry.getCommandExtensions()) {
         builder.then(((LiteralArgumentBuilder)class_2170.method_9247(extension.getId()).executes(c -> {
            createBlock((class_2168)c.getSource(), extension.getBlock(), null);
            return 1;
         })).then(suggestTables().executes(c -> {
            createBlock((class_2168)c.getSource(), extension.getBlock(), class_5321.method_29179(class_7924.field_50079, class_2232.method_9443(c, "table")));
            return 1;
         })));
      }

      builder.then(
         ((LiteralArgumentBuilder)class_2170.method_9247("clear").executes(c -> {
               ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Must provide player name."), true);
               return 1;
            }))
            .then(
               suggestProfiles()
                  .executes(
                     c -> {
                        String playerName = StringArgumentType.getString(c, "profile");
                        Optional<GameProfile> opt_profile = Objects.requireNonNull(((class_2168)c.getSource()).method_9211().method_3793())
                           .method_14515(playerName);
                        if (opt_profile.isEmpty()) {
                           ((class_2168)c.getSource())
                              .method_9213(class_2561.method_43470("Invalid player name: " + playerName + ", profile not found in the cache."));
                           return 0;
                        } else {
                           GameProfile profile = opt_profile.get();
                           ((class_2168)c.getSource())
                              .method_9226(
                                 () -> class_2561.method_43470(
                                    LootrAPI.clearPlayerLoot(profile.getId())
                                       ? "Cleared stored inventories for " + playerName
                                       : "No stored inventories for " + playerName + " to clear"
                                 ),
                                 true
                              );
                           return 1;
                        }
                     }
                  )
            )
      );
      builder.then(((LiteralArgumentBuilder)class_2170.method_9247("cart").executes(c -> {
         createBlock((class_2168)c.getSource(), null, null);
         return 1;
      })).then(suggestTables().executes(c -> {
         createBlock((class_2168)c.getSource(), null, class_5321.method_29179(class_7924.field_50079, class_2232.method_9443(c, "table")));
         return 1;
      })));
      builder.then(
         class_2170.method_9247("custom-chest-old")
            .executes(
               c -> {
                  class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
                  class_1937 level = ((class_2168)c.getSource()).method_9225();
                  class_2680 state = level.method_8320(pos);
                  if (!state.method_26164(LootrTags.Blocks.CUSTOM_ELIGIBLE)) {
                     pos = pos.method_10074();
                     state = level.method_8320(pos);
                  }

                  if (!state.method_26164(LootrTags.Blocks.CUSTOM_ELIGIBLE)) {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on the container you wish to convert."), false);
                  } else {
                     class_2586 blockEntity = level.method_8321(pos);
                     if (!(blockEntity instanceof class_2624 container)) {
                        ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on the container you wish to convert."), false);
                        return 0;
                     }

                     class_2371 reference = ((AccessorMixinBaseContainerBlockEntity)blockEntity).invokeGetItems();
                     class_2680 newState = updateBlockState(state, LootrRegistry.getInventoryBlock().method_9564());
                     class_2371 custom = copyItemList(reference);
                     level.method_8544(pos);
                     level.method_8501(pos, newState);
                     if (level.method_8321(pos) instanceof LootrInventoryBlockEntity inventory) {
                        inventory.setInfoReferenceInventory(custom);
                        inventory.method_5431();
                     } else {
                        ((class_2168)c.getSource())
                           .method_9226(() -> class_2561.method_43470("Unable to convert chest, BlockState is not a Lootr Inventory block."), false);
                     }
                  }

                  return 1;
               }
            )
      );
      builder.then(((LiteralArgumentBuilder)class_2170.method_9247("open_as").executes(c -> {
         ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Must provide player name."), true);
         return 1;
      })).then(suggestProfiles().executes(c -> {
         String playerName = StringArgumentType.getString(c, "profile");
         Optional<GameProfile> opt_profile = Objects.requireNonNull(((class_2168)c.getSource()).method_9211().method_3793()).method_14515(playerName);
         if (opt_profile.isEmpty()) {
            ((class_2168)c.getSource()).method_9213(class_2561.method_43470("Invalid player name: " + playerName + ", profile not found in the cache."));
            return 0;
         } else {
            GameProfile profile = opt_profile.get();
            class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
            class_1937 level = ((class_2168)c.getSource()).method_9225();
            class_2586 te = level.method_8321(pos);
            if (!(LootrAPI.resolveBlockEntity(te) instanceof ILootrBlockEntity)) {
               pos = pos.method_10074();
               te = level.method_8321(pos);
            }

            ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(te);
            if (ibe == null) {
               ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on a valid Lootr container."), false);
               return 0;
            } else {
               LootrSavedData data = DataStorage.getData(ibe);
               if (data == null) {
                  ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("No Lootr data found for this container."), false);
                  return 0;
               } else {
                  LootrInventory inventory = data.getInventory(profile.getId());
                  if (inventory == null) {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("No stored inventory for " + playerName + " found."), true);
                     return 0;
                  } else {
                     class_3222 player = ((class_2168)c.getSource()).method_44023();
                     if (player == null) {
                        ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Command can only be executed by a player"), false);
                        return 0;
                     } else {
                        player.method_17355(inventory);
                        return 1;
                     }
                  }
               }
            }
         }
      })));
      builder.then(((LiteralArgumentBuilder)class_2170.method_9247("open_as_uuid").executes(c -> {
         ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Must provide player UUID."), true);
         return 1;
      })).then(class_2170.method_9244("uuid", StringArgumentType.string()).executes(c -> {
         String uuid = StringArgumentType.getString(c, "uuid");

         UUID id;
         try {
            id = UUID.fromString(uuid);
         } catch (IllegalArgumentException var10) {
            ((class_2168)c.getSource()).method_9213(class_2561.method_43470("Invalid UUID: " + uuid));
            return 0;
         }

         class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
         class_1937 level = ((class_2168)c.getSource()).method_9225();
         class_2586 te = level.method_8321(pos);
         if (!(LootrAPI.resolveBlockEntity(te) instanceof ILootrBlockEntity)) {
            pos = pos.method_10074();
            te = level.method_8321(pos);
         }

         ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(te);
         if (ibe == null) {
            ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on a valid Lootr container."), false);
            return 0;
         } else {
            LootrSavedData data = DataStorage.getData(ibe);
            if (data == null) {
               ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("No Lootr data found for this container."), true);
               return 0;
            } else {
               LootrInventory inventory = data.getInventory(id);
               if (inventory == null) {
                  ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("No stored inventory for " + id + " found."), true);
                  return 0;
               } else {
                  class_3222 player = ((class_2168)c.getSource()).method_44023();
                  if (player == null) {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Command can only be executed by a player"), false);
                     return 0;
                  } else {
                     player.method_17355(inventory);
                     return 1;
                  }
               }
            }
         }
      })));
      builder.then(
         class_2170.method_9247("id")
            .executes(
               c -> {
                  class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
                  class_1937 world = ((class_2168)c.getSource()).method_9225();
                  class_2586 te = world.method_8321(pos);
                  if (!(LootrAPI.resolveBlockEntity(te) instanceof ILootrBlockEntity)) {
                     pos = pos.method_10074();
                     te = world.method_8321(pos);
                  }

                  ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(te);
                  if (ibe == null) {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on a valid Lootr container."), false);
                  } else {
                     ((class_2168)c.getSource())
                        .method_9226(
                           () -> class_2561.method_43470("The ID of this inventory is: ").method_10852(class_2564.method_47523(ibe.getInfoUUID().toString())),
                           false
                        );
                  }

                  return 1;
               }
            )
      );
      builder.then(
         class_2170.method_9247("refresh")
            .executes(
               c -> {
                  class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
                  class_1937 level = ((class_2168)c.getSource()).method_9225();
                  class_2586 be = level.method_8321(pos);
                  if (!(LootrAPI.resolveBlockEntity(be) instanceof ILootrBlockEntity)) {
                     pos = pos.method_10074();
                     be = level.method_8321(pos);
                  }

                  ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(be);
                  if (ibe != null) {
                     LootrAPI.setRefreshing(ibe);
                     ((class_2168)c.getSource())
                        .method_9226(
                           () -> class_2561.method_43470(
                              "Container with ID " + ibe.getInfoUUID() + " has been set to refresh with a delay of " + LootrAPI.getRefreshValue()
                           ),
                           false
                        );
                  } else {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on a valid Lootr container."), false);
                  }

                  return 1;
               }
            )
      );
      builder.then(
         class_2170.method_9247("decay")
            .executes(
               c -> {
                  class_2338 pos = class_2338.method_49638(((class_2168)c.getSource()).method_9222());
                  class_1937 level = ((class_2168)c.getSource()).method_9225();
                  class_2586 be = level.method_8321(pos);
                  if (!(LootrAPI.resolveBlockEntity(be) instanceof ILootrBlockEntity)) {
                     pos = pos.method_10074();
                     be = level.method_8321(pos);
                  }

                  ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(be);
                  if (ibe != null) {
                     LootrAPI.setDecaying(ibe);
                     ((class_2168)c.getSource())
                        .method_9226(
                           () -> class_2561.method_43470(
                              "Container with ID " + ibe.getInfoUUID() + " has been set to decay with a delay of " + LootrAPI.getDecayValue()
                           ),
                           false
                        );
                  } else {
                     ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470("Please stand on a valid Lootr container."), false);
                  }

                  return 1;
               }
            )
      );
      builder.then(
         class_2170.method_9247("openers")
            .then(
               class_2170.method_9244("location", class_2277.method_9737())
                  .executes(
                     c -> {
                        class_2338 position = class_2277.method_9734(c, "location").method_9704((class_2168)c.getSource());
                        class_1937 world = ((class_2168)c.getSource()).method_9225();
                        class_2586 blockEntity = world.method_8321(position);
                        ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(blockEntity);
                        if (ibe != null) {
                           Set<UUID> openers = ibe.getActualOpeners();
                           if (openers != null) {
                              ((class_2168)c.getSource())
                                 .method_9226(
                                    () -> class_2561.method_43470(
                                       "BlockEntity at location " + position + " has " + openers.size() + " openers. UUIDs as follows:"
                                    ),
                                    true
                                 );

                              for (UUID uuid : openers) {
                                 Optional<GameProfile> prof = Objects.requireNonNull(((class_2168)c.getSource()).method_9211().method_3793())
                                    .method_14512(uuid);
                                 ((class_2168)c.getSource())
                                    .method_9226(
                                       () -> class_2561.method_43470("UUID: " + uuid + ", user profile: " + (prof.isPresent() ? prof.get().getName() : "null")),
                                       true
                                    );
                              }
                           }
                        } else {
                           ((class_2168)c.getSource())
                              .method_9226(() -> class_2561.method_43470("No Lootr block entity exists at location: " + position), false);
                        }

                        return 1;
                     }
                  )
            )
      );
      builder.then(class_2170.method_9247("custom-map").then(class_2170.method_9244("level", class_2181.method_9288()).executes(c -> {
         class_3218 levelKey = class_2181.method_9289(c, "level");
         CustomConvertJob.start(((class_2168)c.getSource()).method_9211(), levelKey, getAllChunkPositions(levelKey), (class_2168)c.getSource());
         return 1;
      })));
      builder.then(class_2170.method_9247("custom-chest").then(class_2170.method_9244("target", class_2262.method_9698()).executes(c -> {
         class_2338 pos = class_2262.method_9696(c, "target");
         class_3218 level = ((class_2168)c.getSource()).method_9225();
         Consumer<String> consumer = message -> ((class_2168)c.getSource()).method_9226(() -> class_2561.method_43470(message), true);
         return convertToCustom(pos, level, consumer, ((class_2168)c.getSource()).method_30497()) ? 1 : 0;
      })));
      builder.then(
         class_2170.method_9247("custom-area-old")
            .then(class_2170.method_9244("from", class_2262.method_9698()).then(class_2170.method_9244("to", class_2262.method_9698()).executes(context -> {
               class_3341 bounds = class_3341.method_34390(class_2262.method_9696(context, "from"), class_2262.method_9696(context, "to"));
               class_1923 start = new class_1923(new class_2338(bounds.method_35415(), bounds.method_35416(), bounds.method_35417()));
               class_1923 stop = new class_1923(new class_2338(bounds.method_35418(), bounds.method_35419(), bounds.method_35420()));
               List<class_1923> positions = new ArrayList<>();

               for (int x = start.field_9181; x <= stop.field_9181; x++) {
                  for (int z = start.field_9180; z <= stop.field_9180; z++) {
                     positions.add(new class_1923(x, z));
                  }
               }

               class_3218 level = ((class_2168)context.getSource()).method_9225();
               Consumer<String> consumer = message -> ((class_2168)context.getSource()).method_9226(() -> class_2561.method_43470(message), true);

               for (class_1923 chunkPos : positions) {
                  class_2818 chunk = level.method_8497(chunkPos.field_9181, chunkPos.field_9180);
                  List<class_2338> convertableBlocks = new ArrayList<>();

                  for (class_2338 pos : chunk.method_12021()) {
                     if (bounds.method_14662(pos)) {
                        convertableBlocks.add(pos);
                     }
                  }

                  if (!convertableBlocks.isEmpty()) {
                     for (class_2338 posx : convertableBlocks) {
                        convertToCustom(posx, level, consumer, ((class_2168)context.getSource()).method_30497());
                     }
                  }
               }

               return 1;
            })))
      );
      builder.then(
         class_2170.method_9247("cclear")
            .then(
               class_2170.method_9244("entities", class_2186.method_9306())
                  .executes(
                     c -> {
                        for (class_1297 e : class_2186.method_9317(c, "entities")) {
                           if (e instanceof class_1657 player) {
                              String name = player.method_5820();
                              ((class_2168)c.getSource())
                                 .method_9226(
                                    () -> class_2561.method_43470(
                                       DataStorage.clearInventories(player.method_5667())
                                          ? "Cleared stored inventories for " + name
                                          : "No stored inventories for " + name + " to clear"
                                    ),
                                    true
                                 );
                           }
                        }

                        return 1;
                     }
                  )
            )
      );
      builder.then(
         class_2170.method_9247("cull")
            .executes(
               c -> {
                  ((class_2168)c.getSource())
                     .method_9226(() -> class_2561.method_43470("Going to asynchronously cull " + DataStorage.cullInventories() + " inventories."), true);
                  return 1;
               }
            )
      );
      builder.then(
         class_2170.method_9247("force_chunk")
            .executes(
               c -> {
                  class_1923 pos = new class_1923(class_2338.method_49638(((class_2168)c.getSource()).method_9222()));
                  class_3218 level = ((class_2168)c.getSource()).method_9225();
                  if (!(level.method_14178().method_12121(pos.field_9181, pos.field_9180, class_2806.field_12803, false) instanceof class_2818 levelChunk)) {
                     ((class_2168)c.getSource())
                        .method_9213(class_2561.method_43470("Chunk at " + pos.field_9181 + ", " + pos.field_9180 + " is not loaded somehow!"));
                     return 0;
                  } else {
                     for (class_2586 be : levelChunk.method_12214().values()) {
                        ((class_2168)c.getSource())
                           .method_9226(
                              () -> class_2561.method_43470("Forcing BlockEntity[" + be + "] at " + be.method_11016() + " to be added to the queue."), false
                           );
                        BlockEntityTicker.addEntity(be, level, pos);
                     }

                     return 1;
                  }
               }
            )
      );
      builder.then(
         class_2170.method_9247("force_radius")
            .then(
               class_2170.method_9244("radius", IntegerArgumentType.integer(1))
                  .executes(
                     c -> {
                        int radius;
                        try {
                           radius = IntegerArgumentType.getInteger(c, "radius");
                        } catch (NumberFormatException var11) {
                           ((class_2168)c.getSource()).method_9213(class_2561.method_43470("Radius must be an integer."));
                           return 0;
                        }

                        class_3218 level = ((class_2168)c.getSource()).method_9225();
                        class_1923 center = new class_1923(class_2338.method_49638(((class_2168)c.getSource()).method_9222()));

                        for (int x = center.field_9181 - radius; x <= center.field_9181 + radius; x++) {
                           for (int z = center.field_9180 - radius; z <= center.field_9180 + radius; z++) {
                              class_1923 pos = new class_1923(x, z);
                              if (level.method_14178().method_12121(pos.field_9181, pos.field_9180, class_2806.field_12803, false) instanceof class_2818 levelChunk
                                 )
                               {
                                 for (class_2586 be : levelChunk.method_12214().values()) {
                                    ((class_2168)c.getSource())
                                       .method_9226(
                                          () -> class_2561.method_43470(
                                             "Forcing BlockEntity[" + be + "] at " + be.method_11016() + " to be added to the queue."
                                          ),
                                          false
                                       );
                                    BlockEntityTicker.addEntity(be, level, pos);
                                 }
                              }
                           }
                        }

                        return 1;
                     }
                  )
            )
      );
      builder.then(
         class_2170.method_9247("force_all")
            .executes(
               c -> {
                  class_3218 level = ((class_2168)c.getSource()).method_9225();

                  for (class_3193 holder : ((AccessorMixinChunkMap)level.method_14178().field_17254).lootr$getChunks()) {
                     if (holder.method_20384()) {
                        holder.method_20385();
                        if (holder.method_60471() instanceof class_2818 levelChunk) {
                           class_1923 pos = levelChunk.method_12004();

                           for (class_2586 be : levelChunk.method_12214().values()) {
                              ((class_2168)c.getSource())
                                 .method_9226(
                                    () -> class_2561.method_43470("Forcing BlockEntity[" + be + "] at " + be.method_11016() + " to be added to the queue."),
                                    false
                                 );
                              BlockEntityTicker.addEntity(be, level, pos);
                           }
                        }
                     }
                  }

                  return 1;
               }
            )
      );
      builder.then(
         class_2170.method_9247("custom-area-old")
            .then(
               class_2170.method_9244("from", class_2262.method_9698())
                  .then(
                     class_2170.method_9244("to", class_2262.method_9698())
                        .executes(
                           context -> {
                              class_3341 bounds = class_3341.method_34390(class_2262.method_9696(context, "from"), class_2262.method_9696(context, "to"));
                              class_1923 start = new class_1923(new class_2338(bounds.method_35415(), bounds.method_35416(), bounds.method_35417()));
                              class_1923 stop = new class_1923(new class_2338(bounds.method_35418(), bounds.method_35419(), bounds.method_35420()));
                              List<class_1923> positions = new ArrayList<>();

                              for (int x = start.field_9181; x <= stop.field_9181; x++) {
                                 for (int z = start.field_9180; z <= stop.field_9180; z++) {
                                    positions.add(new class_1923(x, z));
                                 }
                              }

                              class_3218 level = ((class_2168)context.getSource()).method_9225();

                              for (class_1923 chunkPos : positions) {
                                 class_2818 chunk = level.method_8497(chunkPos.field_9181, chunkPos.field_9180);
                                 List<class_2338> convertableBlocks = new ArrayList<>();

                                 for (class_2338 pos : chunk.method_12021()) {
                                    if (bounds.method_14662(pos)) {
                                       convertableBlocks.add(pos);
                                    }
                                 }

                                 if (!convertableBlocks.isEmpty()) {
                                    for (class_2338 posx : convertableBlocks) {
                                       class_2586 blockEntity = chunk.method_12201(posx, class_2819.field_12860);
                                       if (blockEntity instanceof class_2624
                                          && !(blockEntity instanceof class_2621 lootContainer && lootContainer.method_54869() != null)) {
                                          class_2680 state = blockEntity.method_11010();
                                          if (!state.method_26164(LootrTags.Blocks.CUSTOM_ELIGIBLE)
                                             && !blockEntity.method_11017().method_53254().method_40220(LootrTags.BlockEntity.CUSTOM_INELIGIBLE)) {
                                             class_2371<class_1799> reference = ((AccessorMixinBaseContainerBlockEntity)blockEntity).invokeGetItems();
                                             class_2680 newState = updateBlockState(state, LootrRegistry.getInventoryBlock().method_9564());
                                             class_2371<class_1799> custom = copyItemList(reference);
                                             level.method_8544(posx);
                                             level.method_8501(posx, newState);
                                             if (level.method_8321(posx) instanceof LootrInventoryBlockEntity inventory) {
                                                inventory.setInfoReferenceInventory(custom);
                                                inventory.method_5431();
                                             } else {
                                                ((class_2168)context.getSource())
                                                   .method_9213(
                                                      class_2561.method_43470(
                                                         "Unable to convert chest at '" + posx + "', BlockState is not a Lootr Inventory block."
                                                      )
                                                   );
                                             }
                                          }
                                       }
                                    }
                                 }
                              }

                              return 1;
                           }
                        )
                  )
            )
      );
      return builder;
   }

   private static List<class_1923> getAllChunkPositions(class_3218 level) {
      class_5143 storage = ((AccessorMixinMinecraftServer)level.method_8503()).Lootr$getStorageSource();
      class_9240 regionstorageinfo = new class_9240(storage.method_27005(), level.method_27983(), "lootr");
      Path path = storage.method_27424(level.method_27983()).resolve("region");
      File[] directoryListing = path.toFile().listFiles(MCA_FILTER);
      if (directoryListing == null) {
         return List.of();
      } else {
         List<class_1923> allGeneratedChunkPositions = new ArrayList<>();

         for (File potentialRegionFile : directoryListing) {
            Matcher matcher = REGEX.matcher(potentialRegionFile.getName());
            if (matcher.matches()) {
               int i = Integer.parseInt(matcher.group(1)) << 5;
               int j = Integer.parseInt(matcher.group(2)) << 5;
               List<class_1923> regionChunks = Lists.newArrayList();

               try {
                  class_2861 regionfile = new class_2861(regionstorageinfo, potentialRegionFile.toPath(), path, true);

                  try {
                     for (int k = 0; k < 32; k++) {
                        for (int l = 0; l < 32; l++) {
                           class_1923 chunkpos = new class_1923(k + i, l + j);
                           if (regionfile.method_21879(chunkpos)) {
                              regionChunks.add(chunkpos);
                           }
                        }
                     }

                     if (!regionChunks.isEmpty()) {
                        allGeneratedChunkPositions.addAll(regionChunks);
                     }
                  } catch (Throwable var19) {
                     try {
                        regionfile.close();
                     } catch (Throwable var18) {
                        var19.addSuppressed(var18);
                     }

                     throw var19;
                  }

                  regionfile.close();
               } catch (Throwable var20) {
                  LootrAPI.LOG.error("Failed to read chunks from region file {}", potentialRegionFile.toPath(), var20);
               }
            }
         }

         return allGeneratedChunkPositions;
      }
   }

   static class_2680 updateBlockState(class_2680 oldState, class_2680 newState) {
      if (oldState.method_28498(class_2741.field_12525) && newState.method_28498(class_2741.field_12525)) {
         newState = (class_2680)newState.method_11657(class_2741.field_12525, (class_2350)oldState.method_11654(class_2741.field_12525));
      }

      if (oldState.method_28498(class_2383.field_11177) && newState.method_28498(class_2383.field_11177)) {
         newState = (class_2680)newState.method_11657(class_2383.field_11177, (class_2350)oldState.method_11654(class_2383.field_11177));
      }

      if (oldState.method_28498(class_2741.field_12508) && newState.method_28498(class_2741.field_12508)) {
         newState = (class_2680)newState.method_11657(class_2741.field_12508, (Boolean)oldState.method_11654(class_2741.field_12508));
      }

      return newState;
   }

   public static boolean convertToCustom(class_2338 pos, class_3218 level, Consumer<String> c, class_7874 provider) {
      class_2586 blockEntity = level.method_8321(pos);
      if (LootrAPI.resolveBlockEntity(blockEntity) instanceof ILootrBlockEntity) {
         c.accept("The block at " + pos + " is already a Lootr container.");
         return false;
      } else if (blockEntity == null) {
         c.accept("The block at " + pos + " doesn't have a block entity.");
         return false;
      } else {
         ILootrDataAdapter<class_2586> adapter = LootrAPI.getAdapter(blockEntity);
         if (adapter != null) {
            class_5321<class_52> table = adapter.getLootTable(blockEntity);
            if (table != null) {
               c.accept("The block at " + pos + " has a loot table.");
               return false;
            }

            class_2371<class_1799> custom = adapter.getInventoryCopy(blockEntity);
            if (custom == null) {
               c.accept("Could not obtain inventory of block at " + pos + ".");
               return false;
            }

            if (custom.isEmpty()) {
               c.accept("The block at " + pos + " has an empty inventory, cannot convert.");
               return false;
            }

            class_2680 state = level.method_8320(pos);
            class_2680 newState = LootrAPI.replacementBlockState(state);
            if (newState == null) {
               c.accept("The block at " + pos + " has no eligible replacement block state.");
               return false;
            }

            adapter.clear(blockEntity);
            level.method_8652(pos, newState, 3);
            class_2586 newBlockEntity = level.method_8321(pos);
            ILootrBlockEntity exception = LootrAPI.resolveBlockEntity(newBlockEntity);
            if (!(exception instanceof ILootrBlockEntity)) {
               c.accept("The block at " + pos + " did not successfully convert.");
               return false;
            }

            ILootrBlockEntity newInventory = exception;

            try {
               newInventory.setInfoReferenceInventory(custom);
               newInventory.markChanged();
            } catch (NotImplementedException var13) {
               c.accept("The block at " + pos + " was converted, but the inventory could not be transferred.");
               return false;
            }
         }

         return true;
      }
   }
}
