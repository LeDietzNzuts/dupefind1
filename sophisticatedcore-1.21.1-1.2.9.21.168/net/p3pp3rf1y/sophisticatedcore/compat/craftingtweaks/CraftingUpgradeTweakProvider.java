package net.p3pp3rf1y.sophisticatedcore.compat.craftingtweaks;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import net.blay09.mods.craftingtweaks.api.CraftingGrid;
import net.blay09.mods.craftingtweaks.api.CraftingGridBuilder;
import net.blay09.mods.craftingtweaks.api.CraftingGridProvider;
import net.blay09.mods.craftingtweaks.api.GridBalanceHandler;
import net.blay09.mods.craftingtweaks.api.GridTransferHandler;
import net.minecraft.class_1263;
import net.minecraft.class_1277;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.p3pp3rf1y.sophisticatedcore.common.gui.ICraftingContainer;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;

public class CraftingUpgradeTweakProvider implements CraftingGridProvider {
   public String getModId() {
      return "sophisticatedcore";
   }

   public boolean handles(class_1703 abstractContainerMenu) {
      return abstractContainerMenu instanceof StorageContainerMenuBase;
   }

   public void buildCraftingGrids(CraftingGridBuilder builder, class_1703 containerMenu) {
      if (containerMenu instanceof StorageContainerMenuBase<?> storageContainer) {
         builder.addGrid(getCraftingGridStart(storageContainer), getCraftingGridSize(storageContainer))
            .clearHandler((craftingGrid, player, menu, forced) -> this.clearGrid(player, menu, forced))
            .rotateHandler((craftingGrid, player, menu, reverse) -> this.rotateGrid(menu, reverse))
            .balanceHandler(new CraftingUpgradeTweakProvider.StorageCraftingGridBalanceHandler())
            .transferHandler(new CraftingUpgradeTweakProvider.StorageCraftingGridTransferHandler())
            .hideAllTweakButtons();
      }
   }

   public void clearGrid(class_1657 player, class_1703 menu, boolean forced) {
      if (menu instanceof StorageContainerMenuBase<?> storageContainer) {
         getCraftMatrix(storageContainer).ifPresent(craftMatrix -> {
            int start = getCraftingGridStart(storageContainer);
            int size = getCraftingGridSize(storageContainer);

            for (int i = start; i < start + size; i++) {
               int slotIndex = storageContainer.method_7611(i).method_34266();
               class_1799 itemStack = craftMatrix.method_5438(slotIndex);
               if (!itemStack.method_7960()) {
                  class_1799 returnStack = itemStack.method_7972();
                  player.method_31548().method_7394(returnStack);
                  craftMatrix.method_5447(slotIndex, returnStack.method_7947() == 0 ? class_1799.field_8037 : returnStack);
                  if (returnStack.method_7947() > 0 && forced) {
                     player.method_7328(returnStack, false);
                     craftMatrix.method_5447(slotIndex, class_1799.field_8037);
                  }
               }
            }

            storageContainer.method_7623();
         });
      }
   }

   private int rotateSlotId(int slotId, boolean counterClockwise) {
      if (!counterClockwise) {
         switch (slotId) {
            case 0:
               return 1;
            case 1:
               return 2;
            case 2:
               return 5;
            case 3:
               return 0;
            case 4:
            default:
               break;
            case 5:
               return 8;
            case 6:
               return 3;
            case 7:
               return 6;
            case 8:
               return 7;
         }
      } else {
         switch (slotId) {
            case 0:
               return 3;
            case 1:
               return 0;
            case 2:
               return 1;
            case 3:
               return 6;
            case 4:
            default:
               break;
            case 5:
               return 2;
            case 6:
               return 7;
            case 7:
               return 8;
            case 8:
               return 5;
         }
      }

      return 0;
   }

   private boolean ignoresSlotId(int slotId) {
      return slotId == 4;
   }

   private void rotateGrid(class_1703 containerMenu, boolean counterClockwise) {
      if (containerMenu instanceof StorageContainerMenuBase<?> storageContainer) {
         getCraftMatrix(storageContainer).ifPresent(craftMatrix -> {
            int start = getCraftingGridStart(storageContainer);
            int size = getCraftingGridSize(storageContainer);
            class_1263 matrixClone = new class_1277(size);

            for (int i = 0; i < size; i++) {
               int slotIndex = storageContainer.method_7611(start + i).method_34266();
               matrixClone.method_5447(i, craftMatrix.method_5438(slotIndex));
            }

            for (int var10 = 0; var10 < size; var10++) {
               if (!this.ignoresSlotId(var10)) {
                  int slotIndex = containerMenu.method_7611(start + this.rotateSlotId(var10, counterClockwise)).method_34266();
                  craftMatrix.method_5447(slotIndex, matrixClone.method_5438(var10));
               }
            }

            storageContainer.method_7623();
         });
      }
   }

   private static Optional<class_1263> getCraftMatrix(StorageContainerMenuBase<?> container) {
      return getOpenCraftingContainer(container).map(ICraftingContainer::getCraftMatrix);
   }

   public boolean requiresServerSide() {
      return true;
   }

   private static Optional<ICraftingContainer> getOpenCraftingContainer(StorageContainerMenuBase<?> container) {
      return container.getOpenContainer().flatMap(c -> c instanceof ICraftingContainer craftingContainer ? Optional.of(craftingContainer) : Optional.empty());
   }

   private static int getCraftingGridStart(StorageContainerMenuBase<?> container) {
      return getOpenCraftingContainer(container).map(cc -> {
         List<class_1735> recipeSlots = cc.getRecipeSlots();
         return !recipeSlots.isEmpty() ? recipeSlots.get(0).field_7874 : 0;
      }).orElse(0);
   }

   private static int getCraftingGridSize(StorageContainerMenuBase<?> container) {
      return getOpenCraftingContainer(container).isPresent() ? 9 : 0;
   }

   private static class StorageCraftingGridBalanceHandler implements GridBalanceHandler<class_1703> {
      public void balanceGrid(CraftingGrid grid, class_1657 player, class_1703 menu) {
         if (menu instanceof StorageContainerMenuBase<?> storageContainer) {
            CraftingUpgradeTweakProvider.getCraftMatrix(storageContainer).ifPresent(craftMatrix -> {
               ArrayListMultimap<String, Integer> itemMap = ArrayListMultimap.create();
               Multiset<String> itemCount = HashMultiset.create();
               int start = CraftingUpgradeTweakProvider.getCraftingGridStart(storageContainer);
               int size = CraftingUpgradeTweakProvider.getCraftingGridSize(storageContainer);

               for (int i = start; i < start + size; i++) {
                  int slotIndex = menu.method_7611(i).method_34266();
                  class_1799 itemStack = craftMatrix.method_5438(slotIndex);
                  if (!itemStack.method_7960() && itemStack.method_7914() > 1) {
                     class_2960 registryName = class_7923.field_41178.method_10221(itemStack.method_7909());
                     String key = Objects.toString(registryName);
                     key = key + "@" + itemStack.method_57380();
                     itemMap.put(key, slotIndex);
                     itemCount.add(key, itemStack.method_7947());
                  }
               }

               for (String key : itemMap.keySet()) {
                  List<Integer> balanceList = itemMap.get(key);
                  int totalCount = itemCount.count(key);
                  int countPerStack = totalCount / balanceList.size();
                  int restCount = totalCount % balanceList.size();

                  for (int slotIndex : balanceList) {
                     class_1799 itemStack = craftMatrix.method_5438(slotIndex);
                     itemStack.method_7939(countPerStack);
                     craftMatrix.method_5447(slotIndex, itemStack);
                  }

                  int idx = 0;

                  while (restCount > 0) {
                     int slotIndex = balanceList.get(idx);
                     class_1799 itemStack = craftMatrix.method_5438(slotIndex);
                     if (itemStack.method_7947() < itemStack.method_7914()) {
                        itemStack.method_7933(1);
                        craftMatrix.method_5447(slotIndex, itemStack);
                        restCount--;
                     }

                     if (++idx >= balanceList.size()) {
                        idx = 0;
                     }
                  }
               }

               menu.method_7623();
            });
         }
      }

      public void spreadGrid(CraftingGrid grid, class_1657 player, class_1703 menu) {
         if (menu instanceof StorageContainerMenuBase<?> storageContainer) {
            CraftingUpgradeTweakProvider.getCraftMatrix(storageContainer).ifPresent(craftMatrix -> {
               boolean emptyBiggestSlot;
               do {
                  class_1799 biggestSlotStack = null;
                  int biggestSlotSize = 1;
                  int biggestSlotIndex = -1;
                  int start = CraftingUpgradeTweakProvider.getCraftingGridStart(storageContainer);
                  int size = CraftingUpgradeTweakProvider.getCraftingGridSize(storageContainer);

                  for (int i = start; i < start + size; i++) {
                     int slotIndex = menu.method_7611(i).method_34266();
                     class_1799 itemStack = craftMatrix.method_5438(slotIndex);
                     if (!itemStack.method_7960() && itemStack.method_7947() > biggestSlotSize) {
                        biggestSlotStack = itemStack;
                        biggestSlotSize = itemStack.method_7947();
                        biggestSlotIndex = slotIndex;
                     }
                  }

                  if (biggestSlotStack == null) {
                     return;
                  }

                  emptyBiggestSlot = false;

                  for (int ix = start; ix < start + size; ix++) {
                     int slotIndex = menu.method_7611(ix).method_34266();
                     class_1799 itemStack = craftMatrix.method_5438(slotIndex);
                     if (itemStack.method_7960()) {
                        if (biggestSlotStack.method_7947() > 1) {
                           craftMatrix.method_5447(slotIndex, biggestSlotStack.method_7971(1));
                           craftMatrix.method_5447(biggestSlotIndex, biggestSlotStack);
                        } else {
                           emptyBiggestSlot = true;
                        }
                     }
                  }
               } while (emptyBiggestSlot);

               this.balanceGrid(grid, player, menu);
            });
         }
      }
   }

   private static class StorageCraftingGridTransferHandler implements GridTransferHandler<class_1703> {
      public class_1799 putIntoGrid(CraftingGrid craftingGrid, class_1657 player, class_1703 menu, int slotId, class_1799 itemStack) {
         return menu instanceof StorageContainerMenuBase<?> storageContainer
            ? CraftingUpgradeTweakProvider.getCraftMatrix(storageContainer).map(craftMatrix -> {
               class_1799 craftStack = craftMatrix.method_5438(slotId);
               if (!craftStack.method_7960()) {
                  if (class_1799.method_31577(craftStack, itemStack)) {
                     int spaceLeft = Math.min(craftMatrix.method_5444(), craftStack.method_7914()) - craftStack.method_7947();
                     if (spaceLeft > 0) {
                        class_1799 splitStack = itemStack.method_7971(Math.min(spaceLeft, itemStack.method_7947()));
                        craftStack.method_7933(splitStack.method_7947());
                        if (itemStack.method_7947() <= 0) {
                           return class_1799.field_8037;
                        }
                     }
                  }
               } else {
                  class_1799 transferStack = itemStack.method_7971(Math.min(itemStack.method_7947(), craftMatrix.method_5444()));
                  craftMatrix.method_5447(slotId, transferStack);
               }

               return itemStack.method_7947() <= 0 ? class_1799.field_8037 : itemStack;
            }).orElse(itemStack)
            : itemStack;
      }

      public boolean transferIntoGrid(CraftingGrid craftingGrid, class_1657 player, class_1703 menu, class_1735 fromSlot) {
         return menu instanceof StorageContainerMenuBase<?> storageContainer
            ? CraftingUpgradeTweakProvider.getCraftMatrix(storageContainer).map(craftMatrix -> {
               int start = CraftingUpgradeTweakProvider.getCraftingGridStart(storageContainer);
               int size = CraftingUpgradeTweakProvider.getCraftingGridSize(storageContainer);
               class_1799 itemStack = fromSlot.method_7677();
               if (itemStack.method_7960()) {
                  return false;
               } else {
                  int firstEmptySlot = -1;

                  for (int i = start; i < start + size; i++) {
                     int slotIndex = menu.method_7611(i).method_34266();
                     class_1799 craftStack = craftMatrix.method_5438(slotIndex);
                     if (!craftStack.method_7960()) {
                        if (class_1799.method_31577(craftStack, itemStack)) {
                           int spaceLeft = Math.min(craftMatrix.method_5444(), craftStack.method_7914()) - craftStack.method_7947();
                           if (spaceLeft > 0) {
                              class_1799 splitStack = itemStack.method_7971(Math.min(spaceLeft, itemStack.method_7947()));
                              craftStack.method_7933(splitStack.method_7947());
                              if (itemStack.method_7947() <= 0) {
                                 return true;
                              }
                           }
                        }
                     } else if (firstEmptySlot == -1) {
                        firstEmptySlot = slotIndex;
                     }
                  }

                  if (itemStack.method_7947() > 0 && firstEmptySlot != -1) {
                     class_1799 transferStack = itemStack.method_7971(Math.min(itemStack.method_7947(), craftMatrix.method_5444()));
                     craftMatrix.method_5447(firstEmptySlot, transferStack);
                     return true;
                  } else {
                     return false;
                  }
               }
            }).orElse(false)
            : false;
      }

      public boolean canTransferFrom(class_1657 player, class_1703 menu, class_1735 sourceSlot, CraftingGrid craftingGrid) {
         return !(menu instanceof StorageContainerMenuBase<?> storageContainer)
            ? false
            : sourceSlot.method_7674(player) && sourceSlot.field_7874 < storageContainer.realInventorySlots.size();
      }
   }
}
