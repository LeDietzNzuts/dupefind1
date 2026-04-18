package net.caffeinemc.mods.lithium.common.entity.item;

import it.unimi.dsi.fastutil.HashCommon;
import it.unimi.dsi.fastutil.Hash.Strategy;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenCustomHashMap;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangePublisher;
import net.caffeinemc.mods.lithium.common.util.change_tracking.ChangeSubscriber;
import net.minecraft.class_1542;
import net.minecraft.class_1799;
import net.minecraft.class_7927;
import net.minecraft.class_7927.class_7928;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemEntityList extends AbstractList<class_1542> implements ChangeSubscriber.CountChangeSubscriber<class_1542> {
   private static final Strategy<class_1799> STRATEGY = new Strategy<class_1799>() {
      public int hashCode(class_1799 itemStack) {
         return HashCommon.mix(class_1799.method_57355(itemStack));
      }

      public boolean equals(class_1799 itemStack, class_1799 otherItemStack) {
         return itemStack == otherItemStack || itemStack != null && otherItemStack != null && class_1799.method_31577(itemStack, otherItemStack);
      }
   };
   public static final int UPGRADE_THRESHOLD = 10;
   private final ArrayList<class_1542> delegate;
   private final ArrayList<class_1542> delegateWithNulls;
   private final Object2ReferenceOpenCustomHashMap<class_1799, IntArrayList> elementsByCategory;
   private final Object2ReferenceOpenCustomHashMap<class_1799, IntArrayList> maxHalfFullElementsByCategory;
   private final IntOpenHashSet tempUncategorizedElements;

   public ItemEntityList(ArrayList<class_1542> delegate) {
      this.delegate = delegate;
      this.delegateWithNulls = new ArrayList<>(delegate);
      this.elementsByCategory = new Object2ReferenceOpenCustomHashMap(STRATEGY);
      this.maxHalfFullElementsByCategory = new Object2ReferenceOpenCustomHashMap(STRATEGY);
      this.tempUncategorizedElements = new IntOpenHashSet();

      for (int i = 0; i < this.delegateWithNulls.size(); i++) {
         class_1542 element = this.delegateWithNulls.get(i);
         this.addToCategories(element, i, false);
         this.subscribeElement(element, i);
      }
   }

   protected void markElementAsOutdated(class_1542 element, int index) {
      boolean added = this.tempUncategorizedElements.add(index);
      if (added) {
         this.removeFromCategories(element, index);
      }
   }

   protected void processOutdated() {
      if (!this.tempUncategorizedElements.isEmpty()) {
         this.tempUncategorizedElements.forEach(index -> {
            class_1542 element = this.delegateWithNulls.get(index);
            if (element != null) {
               if (element.method_6983().method_7960()) {
                  this.delegateWithNulls.set(index, null);
                  this.unsubscribeElement(element);
               } else {
                  this.addToCategories(element, index, true);
               }
            }
         });
         this.tempUncategorizedElements.clear();
      }
   }

   @Override
   public int size() {
      return this.delegate.size();
   }

   @Override
   public boolean isEmpty() {
      return this.delegate.isEmpty();
   }

   @Override
   public boolean contains(Object o) {
      return this.delegate.contains(o);
   }

   @NotNull
   @Override
   public Object[] toArray() {
      return this.delegate.toArray();
   }

   @NotNull
   @Override
   public <U> U[] toArray(U @NotNull [] a) {
      return this.delegate.toArray(a);
   }

   public boolean add(class_1542 element) {
      this.processOutdated();
      if (element.method_6983().method_7960()) {
         this.delegateWithNulls.add(null);
      } else {
         int index = this.delegateWithNulls.size();
         this.delegateWithNulls.add(element);
         this.addToCategories(element, index, false);
         this.subscribeElement(element, index);
      }

      return this.delegate.add(element);
   }

   private void addToCategories(class_1542 element, int index, boolean insertionSort) {
      class_1799 stack = element.method_6983();
      if (!stack.method_7960()) {
         class_1799 key = addToCategoryList(index, stack, null, this.elementsByCategory, insertionSort);
         if (isMaxHalfFull(stack)) {
            addToCategoryList(index, stack, key, this.maxHalfFullElementsByCategory, insertionSort);
         }
      }
   }

   private static class_1799 addToCategoryList(
      int index, class_1799 stack, @Nullable class_1799 key, Object2ReferenceOpenCustomHashMap<class_1799, IntArrayList> categoryMap, boolean insertionSort
   ) {
      if (stack.method_7960()) {
         return key;
      } else {
         IntArrayList categoryList = (IntArrayList)categoryMap.get(stack);
         if (categoryList == null) {
            if (key == null) {
               key = stack.method_7972();
            }

            categoryList = new IntArrayList();
            categoryMap.put(key, categoryList);
         }

         if (insertionSort) {
            int binarySearchIndex = Collections.binarySearch(categoryList, index);
            binarySearchIndex = -(binarySearchIndex + 1);
            categoryList.add(binarySearchIndex, index);
         } else {
            categoryList.add(index);
         }

         return key;
      }
   }

   private void subscribeElement(class_1542 element, int index) {
      ((ChangePublisher)element).lithium$subscribe(this, index);
   }

   private static boolean isMaxHalfFull(class_1799 stack) {
      int count = stack.method_7947();
      int maxCount = stack.method_7914();
      return isMaxHalfFull(count, maxCount);
   }

   private static boolean isMaxHalfFull(int count, int maxCount) {
      return count * 2 <= maxCount;
   }

   @Override
   public boolean remove(Object o) {
      boolean remove = this.delegate.remove(o);
      if (remove && o instanceof class_1542 element) {
         this.processOutdated();
         this.removeElement(element);
      }

      return remove;
   }

   public class_1542 remove(int index) {
      class_1542 element = this.delegate.remove(index);
      if (element != null) {
         this.processOutdated();
         this.removeElement(element);
      }

      return element;
   }

   private void removeElement(class_1542 element) {
      if (!element.method_6983().method_7960()) {
         int index = this.unsubscribeElement(element);
         if (index == this.delegateWithNulls.size() - 1) {
            class_1542 remove = this.delegateWithNulls.remove(index);
            if (remove != element) {
               throw new IllegalStateException("Element mismatch, expected " + element + " but got " + remove);
            }
         } else {
            this.delegateWithNulls.set(index, null);
         }

         this.removeFromCategories(element, index);
      }

      int size = this.delegateWithNulls.size();
      if (size > 64 && size > this.delegate.size() * 2) {
         this.reinitialize();
      }
   }

   private void reinitialize() {
      for (class_1542 element : this.delegate) {
         this.unsubscribeElement(element);
      }

      this.tempUncategorizedElements.clear();
      this.delegateWithNulls.clear();
      this.elementsByCategory.clear();
      this.maxHalfFullElementsByCategory.clear();
      int i = 0;

      for (int j = 0; i < this.delegate.size(); i++) {
         class_1542 element = this.delegate.get(i);
         if (!element.method_6983().method_7960()) {
            this.delegateWithNulls.add(element);
            this.addToCategories(element, j, false);
            this.subscribeElement(element, j);
            j++;
         }
      }
   }

   private int unsubscribeElement(class_1542 element) {
      return ((ChangePublisher)element).lithium$unsubscribe(this);
   }

   private void removeFromCategories(class_1542 element, int index) {
      class_1799 stack = element.method_6983();
      if (!stack.method_7960()) {
         removeFromCategoryList(this.elementsByCategory, stack, index);
         if (isMaxHalfFull(stack)) {
            removeFromCategoryList(this.maxHalfFullElementsByCategory, stack, index);
         }
      }
   }

   private static void removeFromCategoryList(Object2ReferenceOpenCustomHashMap<class_1799, IntArrayList> elementsByCategory, class_1799 stack, int index) {
      IntArrayList categoryList = (IntArrayList)elementsByCategory.get(stack);
      if (categoryList != null) {
         categoryList.rem(index);
         if (categoryList.isEmpty()) {
            elementsByCategory.remove(stack);
         }
      }
   }

   @Override
   public boolean containsAll(@NotNull Collection<?> c) {
      return this.delegate.containsAll(c);
   }

   @Override
   public void clear() {
      for (class_1542 element : this.delegate) {
         this.unsubscribeElement(element);
      }

      this.delegate.clear();
      this.tempUncategorizedElements.clear();
      this.delegateWithNulls.clear();
      this.elementsByCategory.clear();
      this.maxHalfFullElementsByCategory.clear();
   }

   @Override
   public boolean equals(Object o) {
      return this.delegate.equals(o);
   }

   @Override
   public int hashCode() {
      return this.delegate.hashCode();
   }

   public class_1542 get(int index) {
      return this.delegate.get(index);
   }

   public class_1542 set(int i, class_1542 newElement) {
      class_1542 previousElement = this.delegate.set(i, newElement);
      if (previousElement != newElement) {
         this.processOutdated();
         int index;
         if (previousElement.method_6983().method_7960()) {
            if (this.delegateWithNulls.size() != this.delegate.size()) {
               this.reinitialize();
               return previousElement;
            }

            index = i;
         } else {
            index = this.unsubscribeElement(previousElement);
            this.removeFromCategories(previousElement, index);
         }

         if (newElement.method_6983().method_7960()) {
            this.delegateWithNulls.set(index, null);
            return previousElement;
         }

         class_1542 replaced = this.delegateWithNulls.set(index, newElement);
         this.addToCategories(newElement, index, true);
         this.subscribeElement(newElement, index);
         if (replaced != null && replaced != previousElement && !replaced.method_6983().method_7960()) {
            throw new IllegalStateException("Element mismatch, expected " + previousElement + " but got " + replaced);
         }
      }

      return previousElement;
   }

   @Override
   public int indexOf(Object o) {
      return this.delegate.indexOf(o);
   }

   @Override
   public int lastIndexOf(Object o) {
      return this.delegate.lastIndexOf(o);
   }

   @Override
   public <U> U[] toArray(IntFunction<U[]> generator) {
      return this.delegate.toArray(generator);
   }

   @Override
   public Stream<class_1542> stream() {
      return this.delegate.stream();
   }

   @Override
   public Stream<class_1542> parallelStream() {
      return this.delegate.parallelStream();
   }

   @Override
   public void forEach(Consumer<? super class_1542> action) {
      this.delegate.forEach(action);
   }

   public void lithium$notify(@Nullable class_1542 publisher, int subscriberData) {
      this.markElementAsOutdated(publisher, subscriberData);
   }

   public void lithium$forceUnsubscribe(class_1542 publisher, int subscriberData) {
      this.markElementAsOutdated(publisher, subscriberData);
   }

   public void lithium$notifyCount(class_1542 element, int index, int newCount) {
      this.processOutdated();
      class_1799 stack = element.method_6983();
      if (newCount <= 0) {
         this.removeFromCategories(element, index);
      } else {
         boolean wasMaxHalfFull = isMaxHalfFull(stack);
         boolean isMaxHalfFull = isMaxHalfFull(newCount, stack.method_7914());
         if (wasMaxHalfFull != isMaxHalfFull) {
            if (isMaxHalfFull) {
               addToCategoryList(index, stack, null, this.maxHalfFullElementsByCategory, true);
            } else {
               removeFromCategoryList(this.maxHalfFullElementsByCategory, stack, index);
            }
         }
      }
   }

   public class_7928 consumeForEntityStacking(class_1542 searchingEntity, class_7927<class_1542> itemEntityConsumer) {
      this.processOutdated();
      class_1799 stack = searchingEntity.method_6983();
      int count = stack.method_7947();
      int maxCount = stack.method_7914();
      return count * 2 >= maxCount
         ? this.consumeElements(itemEntityConsumer, (IntArrayList)this.maxHalfFullElementsByCategory.get(stack))
         : this.consumeElements(itemEntityConsumer, (IntArrayList)this.elementsByCategory.get(stack));
   }

   private class_7928 consumeElements(class_7927<class_1542> elementConsumer, IntArrayList categoryList) {
      if (categoryList == null) {
         return class_7928.field_41283;
      } else {
         int expectedModCount = this.modCount;
         int size = categoryList.size();

         for (int i = 0; i < size; i++) {
            if (expectedModCount != this.modCount) {
               throw new ConcurrentModificationException("Collection was modified during iteration!");
            }

            class_1542 element = this.delegateWithNulls.get(categoryList.getInt(i));
            class_7928 next = elementConsumer.accept(element);
            if (next != class_7928.field_41283) {
               return next;
            }
         }

         return class_7928.field_41283;
      }
   }
}
