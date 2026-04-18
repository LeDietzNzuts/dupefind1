package com.natamus.youritemsaresafe_common_fabric.events;

import com.natamus.collective_common_fabric.features.PlayerHeadCacheFeature;
import com.natamus.collective_common_fabric.functions.CompareBlockFunctions;
import com.natamus.collective_common_fabric.functions.DataFunctions;
import com.natamus.collective_common_fabric.functions.TaskFunctions;
import com.natamus.collective_common_fabric.functions.TileEntityFunctions;
import com.natamus.youritemsaresafe_common_fabric.config.ConfigHandler;
import com.natamus.youritemsaresafe_common_fabric.data.Constants;
import com.natamus.youritemsaresafe_common_fabric.util.Util;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1282;
import net.minecraft.class_1299;
import net.minecraft.class_1304;
import net.minecraft.class_1531;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2551;
import net.minecraft.class_2561;
import net.minecraft.class_2595;
import net.minecraft.class_2625;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3222;

public class DeathEvent {
   public static void onPlayerDeath(class_3222 player, class_1282 damageSource, float damageAmount) {
      class_1937 level = player.method_37908();
      if (!level.field_9236) {
         String playerName = player.method_5477().getString();
         List<class_1799> itemStacks = Util.getInventoryItems(player);
         int totalItemCount = 0;

         for (class_1799 itemStack : itemStacks) {
            if (!itemStack.method_7960()) {
               totalItemCount++;
            }
         }

         if (!ConfigHandler.createArmorStand) {
            for (class_1304 slotType : Constants.slotTypes) {
               if (!player.method_6118(slotType).method_7960()) {
                  totalItemCount++;
               }
            }

            if (!player.method_6118(class_1304.field_6169).method_7960()) {
               totalItemCount++;
            }
         }

         if (totalItemCount != 0) {
            class_2338 deathPos = player.method_24515().method_33096((int)Math.ceil(player.method_19538().field_1351)).method_10062();
            if (deathPos.method_10074().method_10264() >= level.method_31607()
               && CompareBlockFunctions.isAirOrOverwritableBlock(level.method_8320(deathPos.method_10074()).method_26204())) {
               deathPos = deathPos.method_10074().method_10062();
            }

            boolean isVoidDeath = deathPos.method_10264() < level.method_31607() - 32;
            if (isVoidDeath) {
               if (!ConfigHandler.createChestAboveVoid) {
                  return;
               }

               int y;
               for (y = level.method_31607(); y < level.method_31600(); y++) {
                  class_2338 possiblePos = new class_2338(deathPos.method_10263(), y, deathPos.method_10260());
                  if (CompareBlockFunctions.isAirOrOverwritableBlock(level.method_8320(possiblePos).method_26204())) {
                     deathPos = possiblePos.method_10062();
                     break;
                  }
               }

               if (ConfigHandler.createVoidPlatform) {
                  deathPos = deathPos.method_10084().method_10062();

                  for (class_2338 platformPos : class_2338.method_10094(
                     deathPos.method_10263() - 1, y, deathPos.method_10260() - 1, deathPos.method_10263() + 1, y, deathPos.method_10260() + 1
                  )) {
                     if (CompareBlockFunctions.isAirOrOverwritableBlock(level.method_8320(platformPos).method_26204())) {
                        level.method_8652(platformPos, class_2246.field_10445.method_9564(), 3);
                     }
                  }
               }
            } else {
               if (Constants.inventoryTotemModIsLoaded) {
                  for (class_1799 inventoryStack : itemStacks) {
                     if (inventoryStack.method_7909().equals(class_1802.field_8288)) {
                        return;
                     }
                  }
               }

               if (player.method_6047().method_7909().equals(class_1802.field_8288) || player.method_6079().method_7909().equals(class_1802.field_8288)) {
                  return;
               }
            }

            if (ConfigHandler.mustHaveItemsInInventoryForCreation
               && (ConfigHandler.needChestMaterials || ConfigHandler.needArmorStandMaterials || ConfigHandler.needSignMaterials)) {
               if (ConfigHandler.createArmorStand && ConfigHandler.addPlayerHeadToArmorStand && !player.method_6118(class_1304.field_6169).method_7960()) {
                  totalItemCount++;
               }

               int stoneleft = 1;
               int planksleft = 0;
               if (ConfigHandler.needChestMaterials) {
                  planksleft += 8;
                  if (totalItemCount > 27) {
                     planksleft += 8;
                  }

                  planksleft = Util.processChestCheck(itemStacks, planksleft);
               }

               if (ConfigHandler.createArmorStand && ConfigHandler.needArmorStandMaterials) {
                  planksleft += 3;
               }

               if (ConfigHandler.createSignWithPlayerName && ConfigHandler.needSignMaterials) {
                  planksleft += 7;
               }

               if (ConfigHandler.ignoreStoneMaterialNeed) {
                  stoneleft = 0;
               }

               int planksneeded = planksleft;
               int stoneneeded = stoneleft;
               if (planksleft > 0) {
                  planksleft = Util.processLogCheck(itemStacks, planksleft);
               }

               if (planksleft > 0) {
                  planksleft = Util.processPlankCheck(itemStacks, planksleft);
               }

               if (planksleft > 0) {
                  planksleft = Util.processChestCheck(itemStacks, planksleft);
               }

               if (planksleft > 0) {
                  Util.failureMessage(player, planksleft, stoneleft, planksneeded, stoneleft);
                  return;
               }

               if (stoneleft > 0) {
                  stoneleft = Util.processStoneCheck(itemStacks, stoneleft);
               }

               if (stoneleft > 0) {
                  stoneleft = Util.processSlabCheck(itemStacks, stoneleft);
               }

               if (stoneleft > 0) {
                  Util.failureMessage(player, planksleft, stoneleft, planksneeded, stoneneeded);
                  return;
               }
            }

            List<class_1304> localSlotTypes = new ArrayList<>(Constants.slotTypes);
            class_1531 armourStand;
            if (ConfigHandler.createArmorStand) {
               class_1799 helmetStack = null;
               armourStand = new class_1531(class_1299.field_6131, level);
               if (ConfigHandler.addPlayerHeadToArmorStand) {
                  class_1799 headStack = PlayerHeadCacheFeature.getPlayerHeadStackFromCache(player);
                  if (headStack == null) {
                     headStack = PlayerHeadCacheFeature.getPlayerHeadStackFromCache((class_3218)level, player.method_5477().getString());
                  }

                  if (headStack != null) {
                     if (!player.method_6118(class_1304.field_6169).method_7960()) {
                        helmetStack = player.method_6118(class_1304.field_6169).method_7972();
                        player.method_5673(class_1304.field_6169, class_1799.field_8037);
                     }

                     armourStand.method_5673(class_1304.field_6169, headStack);
                     localSlotTypes.remove(class_1304.field_6169);
                  }
               }

               for (class_1304 slotTypex : localSlotTypes) {
                  class_1799 slotStack = player.method_6118(slotTypex).method_7972();
                  if (!slotStack.method_7960() && !Util.hasCurseOfVanishing(slotStack)) {
                     armourStand.method_5673(slotTypex, slotStack);
                     player.method_5673(slotTypex, class_1799.field_8037);
                  }
               }

               itemStacks = Util.getInventoryItems(player);
               if (helmetStack != null && !Util.hasCurseOfVanishing(helmetStack)) {
                  itemStacks.add(helmetStack);
               }
            } else {
               armourStand = null;

               for (class_1304 slotTypexx : localSlotTypes) {
                  if (!slotTypexx.equals(class_1304.field_6173)) {
                     class_1799 slotStack = player.method_6118(slotTypexx).method_7972();
                     if (!slotStack.method_7960() && !Util.hasCurseOfVanishing(slotStack)) {
                        itemStacks.add(slotStack);
                        player.method_5673(slotTypexx, class_1799.field_8037);
                     }
                  }
               }
            }

            class_2338 finalDeathPos = deathPos;
            List<class_1799> finalItemStacks = itemStacks;
            TaskFunctions.enqueueCollectiveTask(
               level.method_8503(),
               () -> {
                  int chestCount = 1;
                  class_2680 chestState = (class_2680)class_2246.field_10034.method_9564().method_11657(class_2281.field_10768, class_2350.field_11035);
                  class_2595 chestEntity = new class_2595(finalDeathPos, chestState);
                  level.method_8652(finalDeathPos, chestState, 3);
                  level.method_8438(chestEntity);
                  class_2338 deathPosUp = new class_2338(finalDeathPos.method_10263(), finalDeathPos.method_10264() + 1, finalDeathPos.method_10260());
                  class_2595 chestEntityTwo = new class_2595(deathPosUp, chestState);
                  int i = 0;

                  for (class_1799 itemStackx : finalItemStacks) {
                     if (!itemStackx.method_7960()) {
                        if (i < 27) {
                           chestEntity.method_5447(i, itemStackx.method_7972());
                           itemStackx.method_7939(0);
                        } else if (i >= 27) {
                           if (chestCount == 1) {
                              chestCount++;
                              level.method_8652(deathPosUp, chestState, 3);
                              level.method_8438(chestEntityTwo);
                           }

                           if (i - 27 > 26) {
                              break;
                           }

                           chestEntityTwo.method_5447(i - 27, itemStackx.method_7972());
                           itemStackx.method_7939(0);
                        }

                        i++;
                     }
                  }

                  if (armourStand != null) {
                     armourStand.method_5814(finalDeathPos.method_10263() + 0.5, finalDeathPos.method_10264() + chestCount, finalDeathPos.method_10260() + 0.5);
                     armourStand.method_5841()
                        .method_12778(class_1531.field_7107, DataFunctions.setBit((Byte)armourStand.method_5841().method_12789(class_1531.field_7107), 4, true));
                     level.method_8649(armourStand);
                  }

                  Util.successMessage(player);
                  if (ConfigHandler.createSignWithPlayerName) {
                     class_2338 signPos = finalDeathPos.method_10072().method_10062();
                     level.method_8501(signPos, (class_2680)class_2246.field_10187.method_9564().method_11657(class_2551.field_11726, class_2350.field_11035));
                     if (!(level.method_8321(signPos) instanceof class_2625 signBlockEntity)) {
                        return;
                     }

                     signBlockEntity.method_49840(signBlockEntity.method_49853().method_49857(1, class_2561.method_43470(playerName)), true);
                     TileEntityFunctions.updateTileEntity(level, signPos, signBlockEntity);
                  }
               },
               1
            );
         }
      }
   }
}
