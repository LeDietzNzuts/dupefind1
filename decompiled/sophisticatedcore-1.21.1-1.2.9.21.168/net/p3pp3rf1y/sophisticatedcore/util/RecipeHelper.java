package net.p3pp3rf1y.sophisticatedcore.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.class_1657;
import net.minecraft.class_1703;
import net.minecraft.class_1715;
import net.minecraft.class_1799;
import net.minecraft.class_1860;
import net.minecraft.class_1863;
import net.minecraft.class_1874;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_3917;
import net.minecraft.class_3955;
import net.minecraft.class_3956;
import net.minecraft.class_7923;
import net.minecraft.class_8566;
import net.minecraft.class_8786;
import net.minecraft.class_9694;
import net.minecraft.class_9695;
import net.minecraft.class_9696;
import net.p3pp3rf1y.sophisticatedcore.SophisticatedCore;
import org.jetbrains.annotations.Nullable;

public class RecipeHelper {
   private static final int MAX_FOLLOW_UP_COMPACTING_RECIPES = 30;
   private static RecipeHelper.RecipeCache clientCache;
   private static RecipeHelper.RecipeCache serverCache;

   private RecipeHelper() {
   }

   public static void setLevel(class_1937 l) {
      if (l instanceof class_3218) {
         serverCache = new RecipeHelper.RecipeCache(l);
      } else {
         clientCache = new RecipeHelper.RecipeCache(l);
      }
   }

   private static void runOnCache(Consumer<RecipeHelper.RecipeCache> consumer) {
      if (SophisticatedCore.isLogicalServerThread()) {
         if (serverCache != null) {
            consumer.accept(serverCache);
         }
      } else if (clientCache != null) {
         consumer.accept(clientCache);
      }
   }

   private static <T> T getFromCache(Function<RecipeHelper.RecipeCache, T> getter, T defaultValue) {
      if (SophisticatedCore.isLogicalServerThread()) {
         return serverCache == null ? defaultValue : getter.apply(serverCache);
      } else {
         return clientCache == null ? defaultValue : getter.apply(clientCache);
      }
   }

   public static void addRecipeChangeListener(Runnable runnable) {
      runOnCache(cache -> cache.addRecipeChangeListener(runnable));
   }

   public static void onRecipesUpdated(class_1863 manager) {
      runOnCache(cache -> {
         cache.clearCache();
         cache.recipeChangeListeners.notifyAllListeners();
      });
   }

   public static void onDataPackSync(class_3222 serverPlayer, boolean b) {
      runOnCache(cache -> {
         cache.clearCache();
         cache.recipeChangeListeners.notifyAllListeners();
      });
   }

   private static Optional<class_1937> getLevel() {
      return getFromCache(cache -> Optional.ofNullable(cache.level.get()), Optional.empty());
   }

   private static Set<RecipeHelper.CompactingShape> getCompactingShapes(class_1799 stack) {
      return getLevel()
         .map(
            w -> {
               Set<RecipeHelper.CompactingShape> compactingShapes = new HashSet<>();
               getCompactingShape(stack, w, 2, 2, RecipeHelper.CompactingShape.TWO_BY_TWO_UNCRAFTABLE, RecipeHelper.CompactingShape.TWO_BY_TWO)
                  .ifPresent(compactingShapes::add);
               getCompactingShape(stack, w, 3, 3, RecipeHelper.CompactingShape.THREE_BY_THREE_UNCRAFTABLE, RecipeHelper.CompactingShape.THREE_BY_THREE)
                  .ifPresent(compactingShapes::add);
               if (compactingShapes.isEmpty()) {
                  compactingShapes.add(RecipeHelper.CompactingShape.NONE);
               }

               return compactingShapes;
            }
         )
         .orElse(Collections.emptySet());
   }

   private static Optional<RecipeHelper.CompactingShape> getCompactingShape(
      class_1799 stack, class_1937 w, int width, int height, RecipeHelper.CompactingShape uncraftableShape, RecipeHelper.CompactingShape shape
   ) {
      RecipeHelper.CompactingResult compactingResult = getCompactingResult(stack, w, width, height);
      if (!compactingResult.getResult().method_7960()) {
         if (class_1799.method_31577(stack, compactingResult.getResult())) {
            return Optional.empty();
         } else if (isPartOfCompactingLoop(stack, compactingResult.getResult(), w)) {
            return Optional.empty();
         } else {
            return uncompactMatchesItem(compactingResult.getResult(), w, stack, width * height) ? Optional.of(uncraftableShape) : Optional.of(shape);
         }
      } else {
         return Optional.empty();
      }
   }

   private static boolean isPartOfCompactingLoop(class_1799 firstCompacted, class_1799 firstCompactResult, class_1937 w) {
      int iterations = 0;
      Set<Integer> compactedItemHashes = new HashSet<>();
      Queue<class_1799> itemsToCompact = new LinkedList<>();
      itemsToCompact.add(firstCompactResult);

      while (!itemsToCompact.isEmpty()) {
         class_1799 itemToCompact = itemsToCompact.poll();
         class_1799 compactingResultStack = getCompactingResult(itemToCompact, w, 2, 2).getResult();
         if (!compactingResultStack.method_7960()) {
            if (class_1799.method_31577(compactingResultStack, firstCompacted)) {
               return true;
            }

            if (compactedItemHashes.contains(class_1799.method_57355(compactingResultStack))) {
               return false;
            }

            itemsToCompact.add(compactingResultStack);
         }

         compactingResultStack = getCompactingResult(itemToCompact, w, 3, 3).getResult();
         if (!compactingResultStack.method_7960()) {
            if (class_1799.method_31577(compactingResultStack, firstCompacted)) {
               return true;
            }

            if (compactedItemHashes.contains(class_1799.method_57355(compactingResultStack))) {
               return false;
            }

            itemsToCompact.add(compactingResultStack);
         }

         compactedItemHashes.add(class_1799.method_57355(itemToCompact));
         if (++iterations > 30) {
            return true;
         }
      }

      return false;
   }

   private static boolean uncompactMatchesItem(class_1799 itemToUncompact, class_1937 w, class_1799 itemToMatch, int count) {
      for (class_1799 uncompactResult : getUncompactResultItems(w, itemToUncompact)) {
         if (class_1799.method_31577(uncompactResult, itemToMatch) && uncompactResult.method_7947() == count) {
            return true;
         }
      }

      return false;
   }

   public static RecipeHelper.UncompactingResult getUncompactingResult(class_1799 uncompactedItem) {
      return getFromCache(
         cache -> cache.getUncompactingResults()
            .computeIfAbsent(
               class_1799.method_57355(uncompactedItem),
               k -> getLevel()
                  .map(
                     w -> {
                        for (class_1799 uncompactResultItem : getUncompactResultItems(w, uncompactedItem)) {
                           if (uncompactResultItem.method_7947() == 9) {
                              if (class_1799.method_31577(getCompactingResult(uncompactResultItem, 3, 3).getResult(), uncompactedItem)) {
                                 return new RecipeHelper.UncompactingResult(uncompactResultItem, RecipeHelper.CompactingShape.THREE_BY_THREE_UNCRAFTABLE);
                              }
                           } else if (uncompactResultItem.method_7947() == 4
                              && class_1799.method_31577(getCompactingResult(uncompactResultItem, 2, 2).getResult(), uncompactedItem)) {
                              return new RecipeHelper.UncompactingResult(uncompactResultItem, RecipeHelper.CompactingShape.TWO_BY_TWO_UNCRAFTABLE);
                           }
                        }

                        return RecipeHelper.UncompactingResult.EMPTY;
                     }
                  )
                  .orElse(RecipeHelper.UncompactingResult.EMPTY)
            ),
         RecipeHelper.UncompactingResult.EMPTY
      );
   }

   private static List<class_1799> getUncompactResultItems(class_1937 w, class_1799 itemToUncompact) {
      class_8566 craftingInventory = getFilledCraftingInventory(itemToUncompact, 1, 1);
      return safeGetRecipesFor(class_3956.field_17545, craftingInventory.method_59961(), w)
         .stream()
         .map(r -> ((class_3955)r.comp_1933()).method_8116(craftingInventory.method_59961(), w.method_30349()))
         .toList();
   }

   public static RecipeHelper.CompactingResult getCompactingResult(class_1799 stack, RecipeHelper.CompactingShape shape) {
      if (shape == RecipeHelper.CompactingShape.TWO_BY_TWO_UNCRAFTABLE || shape == RecipeHelper.CompactingShape.TWO_BY_TWO) {
         return getCompactingResult(stack, 2, 2);
      } else {
         return shape != RecipeHelper.CompactingShape.THREE_BY_THREE_UNCRAFTABLE && shape != RecipeHelper.CompactingShape.THREE_BY_THREE
            ? RecipeHelper.CompactingResult.EMPTY
            : getCompactingResult(stack, 3, 3);
      }
   }

   public static RecipeHelper.CompactingResult getCompactingResult(class_1799 stack, int width, int height) {
      return getLevel().map(w -> getCompactingResult(stack, w, width, height)).orElse(RecipeHelper.CompactingResult.EMPTY);
   }

   private static RecipeHelper.CompactingResult getCompactingResult(class_1799 stack, class_1937 level, int width, int height) {
      return getFromCache(cache -> getCompactingResult(stack, level, width, height, cache.getCompactingResults()), RecipeHelper.CompactingResult.EMPTY);
   }

   private static RecipeHelper.CompactingResult getCompactingResult(
      class_1799 stack, class_1937 level, int width, int height, Map<RecipeHelper.CompactedItem, RecipeHelper.CompactingResult> cachedCompactingResults
   ) {
      RecipeHelper.CompactedItem compactedItem = new RecipeHelper.CompactedItem(stack, width, height);
      if (cachedCompactingResults.containsKey(compactedItem)) {
         return cachedCompactingResults.get(compactedItem);
      } else {
         class_8566 craftingInventory = getFilledCraftingInventory(stack, width, height);
         List<class_8786<class_3955>> compactingRecipes = safeGetRecipesFor(class_3956.field_17545, craftingInventory.method_59961(), level);
         if (compactingRecipes.isEmpty()) {
            cachedCompactingResults.put(compactedItem, RecipeHelper.CompactingResult.EMPTY);
            return RecipeHelper.CompactingResult.EMPTY;
         } else if (compactingRecipes.size() == 1) {
            return cacheAndGetCompactingResult(compactedItem, (class_3955)((class_8786)compactingRecipes.getFirst()).comp_1933(), craftingInventory);
         } else {
            for (class_8786<class_3955> recipeHolder : compactingRecipes) {
               class_3955 recipe = (class_3955)recipeHolder.comp_1933();
               class_1799 result = recipe.method_8116(craftingInventory.method_59961(), level.method_30349());
               if (uncompactMatchesItem(result, level, stack, width * height)) {
                  return cacheAndGetCompactingResult(compactedItem, recipe, craftingInventory, result);
               }
            }

            return cacheAndGetCompactingResult(compactedItem, (class_3955)((class_8786)compactingRecipes.getFirst()).comp_1933(), craftingInventory);
         }
      }
   }

   private static RecipeHelper.CompactingResult cacheAndGetCompactingResult(
      RecipeHelper.CompactedItem compactedItem, class_3955 recipe, class_8566 craftingInventory
   ) {
      return getLevel()
         .map(
            level -> cacheAndGetCompactingResult(
               compactedItem, recipe, craftingInventory, recipe.method_8116(craftingInventory.method_59961(), level.method_30349())
            )
         )
         .orElse(RecipeHelper.CompactingResult.EMPTY);
   }

   private static RecipeHelper.CompactingResult cacheAndGetCompactingResult(
      RecipeHelper.CompactedItem compactedItem, class_3955 recipe, class_8566 craftingInventory, class_1799 result
   ) {
      List<class_1799> remainingItems = new ArrayList<>();
      recipe.method_8111(craftingInventory.method_59961()).forEach(stack -> {
         if (!stack.method_7960()) {
            remainingItems.add(stack);
         }
      });
      RecipeHelper.CompactingResult compactingResult = new RecipeHelper.CompactingResult(result, remainingItems);
      return getFromCache(cache -> {
         if (!result.method_7960()) {
            cache.getCompactingResults().put(compactedItem, compactingResult);
         }

         return compactingResult;
      }, compactingResult);
   }

   private static class_8566 getFilledCraftingInventory(class_1799 stack, int width, int height) {
      class_8566 craftinginventory = new class_1715(new class_1703(null, -1) {
         public class_1799 method_7601(class_1657 player, int index) {
            return class_1799.field_8037;
         }

         public boolean method_7597(class_1657 playerIn) {
            return false;
         }
      }, width, height);

      for (int i = 0; i < craftinginventory.method_5439(); i++) {
         craftinginventory.method_5447(i, stack.method_46651(1));
      }

      return craftinginventory;
   }

   public static <T extends class_1874> Optional<class_8786<T>> getCookingRecipe(class_1799 stack, class_3956<T> recipeType) {
      return getLevel().flatMap(w -> safeGetRecipeFor(recipeType, new class_9696(stack), w, null));
   }

   public static Set<RecipeHelper.CompactingShape> getItemCompactingShapes(class_1799 stack) {
      return getFromCache(cache -> cache.getItemCompactingShapes(stack), Collections.emptySet());
   }

   public static <I extends class_9695, T extends class_1860<I>> List<class_8786<T>> getRecipesOfType(class_3956<T> recipeType, I inventory) {
      return getLevel().map(w -> w.method_8433().method_17877(recipeType, inventory, w)).orElse(Collections.emptyList());
   }

   public static <I extends class_9695, T extends class_1860<I>> Optional<class_8786<T>> safeGetRecipeFor(
      class_3956<T> recipeType, I inventory, @Nullable class_2960 recipeId
   ) {
      return getLevel().flatMap(w -> safeGetRecipeFor(recipeType, inventory, w, recipeId));
   }

   public static <I extends class_9695, T extends class_1860<I>> Optional<class_8786<T>> safeGetRecipeFor(
      class_3956<T> recipeType, I inventory, class_1937 level, @Nullable class_2960 recipeId
   ) {
      try {
         return level.method_8433().method_42299(recipeType, inventory, level, recipeId);
      } catch (Exception var5) {
         SophisticatedCore.LOGGER.error("Error while getting recipe ", var5);
         return Optional.empty();
      }
   }

   public static <I extends class_9694, T extends class_1860<I>> List<class_8786<T>> safeGetRecipesFor(class_3956<T> recipeType, I inventory, class_1937 level) {
      try {
         return level.method_8433().method_17877(recipeType, inventory, level);
      } catch (Exception var4) {
         SophisticatedCore.LOGGER.error("Error while getting recipe ", var4);
         return Collections.emptyList();
      }
   }

   private static class CompactedItem {
      private final class_1799 item;
      private final int itemHash;
      private final int width;
      private final int height;

      private CompactedItem(class_1799 item, int width, int height) {
         this.item = item.method_46651(1);
         this.width = width;
         this.height = height;
         this.itemHash = class_1799.method_57355(item);
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (o != null && this.getClass() == o.getClass()) {
            RecipeHelper.CompactedItem that = (RecipeHelper.CompactedItem)o;
            return this.width == that.width && this.height == that.height && class_1799.method_31577(this.item, that.item);
         } else {
            return false;
         }
      }

      @Override
      public int hashCode() {
         return Objects.hash(this.itemHash, this.width, this.height);
      }
   }

   public static class CompactingResult {
      public static final RecipeHelper.CompactingResult EMPTY = new RecipeHelper.CompactingResult(class_1799.field_8037, Collections.emptyList());
      private final class_1799 result;
      private final List<class_1799> remainingItems;

      public CompactingResult(class_1799 result, List<class_1799> remainingItems) {
         this.result = result;
         this.remainingItems = remainingItems;
      }

      public class_1799 getResult() {
         return this.result;
      }

      public List<class_1799> getRemainingItems() {
         return this.remainingItems;
      }
   }

   public static enum CompactingShape {
      NONE(false, 0),
      THREE_BY_THREE(false, 9),
      TWO_BY_TWO(false, 4),
      THREE_BY_THREE_UNCRAFTABLE(true, 9),
      TWO_BY_TWO_UNCRAFTABLE(true, 4);

      private final int numberOfIngredients;
      private final boolean uncraftable;

      private CompactingShape(boolean uncraftable, int numberOfIngredients) {
         this.uncraftable = uncraftable;
         this.numberOfIngredients = numberOfIngredients;
      }

      public boolean isUncraftable() {
         return this.uncraftable;
      }

      public int getNumberOfIngredients() {
         return this.numberOfIngredients;
      }
   }

   private static class RecipeCache {
      private final Cache<Integer, Set<RecipeHelper.CompactingShape>> itemCompactingShapes = CacheBuilder.newBuilder()
         .expireAfterAccess(10L, TimeUnit.MINUTES)
         .build();
      private final Map<RecipeHelper.CompactedItem, RecipeHelper.CompactingResult> compactingResults = new HashMap<>();
      private final Map<Integer, RecipeHelper.UncompactingResult> uncompactingResults = new HashMap<>();
      private final RecipeHelper.RecipeChangeListenerList recipeChangeListeners = new RecipeHelper.RecipeChangeListenerList();
      private final WeakReference<class_1937> level;

      public RecipeCache(class_1937 level) {
         this.level = new WeakReference<>(level);
      }

      public void addRecipeChangeListener(Runnable runnable) {
         this.recipeChangeListeners.add(runnable);
      }

      public Map<Integer, RecipeHelper.UncompactingResult> getUncompactingResults() {
         return this.uncompactingResults;
      }

      public Map<RecipeHelper.CompactedItem, RecipeHelper.CompactingResult> getCompactingResults() {
         return this.compactingResults;
      }

      public Set<RecipeHelper.CompactingShape> getItemCompactingShapes(class_1799 stack) {
         int hash = class_1799.method_57355(stack);
         Set<RecipeHelper.CompactingShape> compactingShapes = (Set<RecipeHelper.CompactingShape>)this.itemCompactingShapes.getIfPresent(hash);
         if (compactingShapes == null) {
            SophisticatedCore.LOGGER
               .debug(
                  "Compacting shapes not found in cache for \"{}\" - querying recipes to get these", class_7923.field_41178.method_10221(stack.method_7909())
               );
            compactingShapes = RecipeHelper.getCompactingShapes(stack);
            this.itemCompactingShapes.put(hash, compactingShapes);
         }

         return compactingShapes;
      }

      private void clearCache() {
         this.compactingResults.clear();
         this.uncompactingResults.clear();
         this.itemCompactingShapes.invalidateAll();
      }
   }

   private static class RecipeChangeListenerList {
      private final List<WeakReference<Runnable>> list = Collections.synchronizedList(new ArrayList<>());

      public void add(Runnable runnable) {
         this.list.add(new WeakReference<>(runnable));
      }

      public void notifyAllListeners() {
         this.list.removeIf(ref -> {
            Runnable runnable = ref.get();
            if (runnable != null) {
               runnable.run();
               return false;
            } else {
               return true;
            }
         });
      }
   }

   public static class UncompactingResult {
      public static final RecipeHelper.UncompactingResult EMPTY = new RecipeHelper.UncompactingResult(class_1799.field_8037, RecipeHelper.CompactingShape.NONE);
      private final class_1799 result;
      private final RecipeHelper.CompactingShape compactUsingShape;

      public UncompactingResult(class_1799 result, RecipeHelper.CompactingShape compactUsingShape) {
         this.result = result.method_46651(1);
         this.compactUsingShape = compactUsingShape;
      }

      public class_1799 getResult() {
         return this.result;
      }

      public RecipeHelper.CompactingShape getCompactUsingShape() {
         return this.compactUsingShape;
      }
   }
}
