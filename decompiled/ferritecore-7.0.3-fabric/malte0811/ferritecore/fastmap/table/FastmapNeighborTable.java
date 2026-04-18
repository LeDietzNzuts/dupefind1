package malte0811.ferritecore.fastmap.table;

import com.google.common.collect.Tables;
import com.google.common.collect.Table.Cell;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import malte0811.ferritecore.ducks.FastMapStateHolder;
import malte0811.ferritecore.fastmap.FastMap;
import net.minecraft.class_2769;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FastmapNeighborTable<S> extends NeighborTableBase<S> {
   private final FastMapStateHolder<S> owner;

   public FastmapNeighborTable(FastMapStateHolder<S> owner) {
      this.owner = owner;
   }

   public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
      if (columnKey instanceof Comparable && rowKey instanceof class_2769<?> rowProperty) {
         Comparable<?> valueInState = this.owner.getStateMap().getValue(this.owner.getStateIndex(), (class_2769<Comparable<?>>)rowProperty);
         return valueInState != null && !valueInState.equals(columnKey) ? rowProperty.method_11898().contains(columnKey) : false;
      } else {
         return false;
      }
   }

   public boolean containsRow(@Nullable Object rowKey) {
      return rowKey instanceof class_2769<?> rowProperty ? this.owner.getStateMap().getValue(this.owner.getStateIndex(), rowProperty) != null : false;
   }

   public boolean containsColumn(@Nullable Object columnKey) {
      FastMap<S> map = this.owner.getStateMap();

      for (int i = 0; i < map.numProperties(); i++) {
         Entry<class_2769<?>, Comparable<?>> entry = map.getEntry(i, this.owner.getStateIndex());
         if (!entry.getValue().equals(columnKey) && entry.getKey().method_11898().contains(columnKey)) {
            return true;
         }
      }

      return false;
   }

   public boolean containsValue(@Nullable Object value) {
      if (value == null) {
         return false;
      } else {
         FastMap<S> map = this.owner.getStateMap();

         for (int propIndex = 0; propIndex < map.numProperties(); propIndex++) {
            if (this.isNeighbor(map.getKey(propIndex).getProperty(), value)) {
               return true;
            }
         }

         return false;
      }
   }

   private <T extends Comparable<T>> boolean isNeighbor(class_2769<T> prop, Object potentialNeighbor) {
      FastMap<S> map = this.owner.getStateMap();
      T valueInState = map.getValue(this.owner.getStateIndex(), prop);

      for (T neighborValue : prop.method_11898()) {
         if (!neighborValue.equals(valueInState)) {
            S neighbor = map.with(this.owner.getStateIndex(), prop, valueInState);
            if (potentialNeighbor.equals(neighbor)) {
               return true;
            }
         }
      }

      return false;
   }

   public S get(@Nullable Object rowKey, @Nullable Object columnKey) {
      return rowKey instanceof class_2769<?> rowProperty && columnKey instanceof Comparable<?> colComparable
         ? this.owner.getStateMap().with(this.owner.getStateIndex(), rowProperty, colComparable)
         : null;
   }

   public boolean isEmpty() {
      return this.owner.getStateMap().isSingleState();
   }

   public int size() {
      int numNeighbors = 0;

      for (int i = 0; i < this.owner.getStateMap().numProperties(); i++) {
         numNeighbors += this.owner.getStateMap().getKey(i).numValues();
      }

      return numNeighbors;
   }

   public Map<Comparable<?>, S> row(@NotNull class_2769<?> rowKey) {
      Map<Comparable<?>, S> rowMap = new HashMap<>();
      Comparable<?> contained = this.owner.getStateMap().getValue(this.owner.getStateIndex(), (class_2769<Comparable<?>>)rowKey);

      for (Comparable<?> val : rowKey.method_11898()) {
         if (!val.equals(contained)) {
            rowMap.put(val, this.owner.getStateMap().with(this.owner.getStateIndex(), rowKey, val));
         }
      }

      return rowMap;
   }

   public Map<class_2769<?>, S> column(@NotNull Comparable<?> columnKey) {
      FastMap<S> map = this.owner.getStateMap();
      int index = this.owner.getStateIndex();
      Map<class_2769<?>, S> rowMap = new HashMap<>();

      for (int i = 0; i < map.numProperties(); i++) {
         class_2769<?> rowKey = map.getKey(i).getProperty();
         Comparable<?> contained = map.getValue(index, (class_2769<Comparable<?>>)rowKey);

         for (Comparable<?> val : rowKey.method_11898()) {
            if (!val.equals(contained) && val.equals(columnKey)) {
               rowMap.put(rowKey, map.with(index, rowKey, val));
            }
         }
      }

      return rowMap;
   }

   public Set<Cell<class_2769<?>, Comparable<?>, S>> cellSet() {
      FastMap<S> map = this.owner.getStateMap();
      int index = this.owner.getStateIndex();
      Set<Cell<class_2769<?>, Comparable<?>, S>> rowMap = new HashSet<>();

      for (int i = 0; i < map.numProperties(); i++) {
         class_2769<?> rowKey = map.getKey(i).getProperty();
         Comparable<?> contained = map.getValue(index, (class_2769<Comparable<?>>)rowKey);

         for (Comparable<?> val : rowKey.method_11898()) {
            if (!val.equals(contained)) {
               rowMap.add(Tables.immutableCell(rowKey, val, map.with(index, rowKey, val)));
            }
         }
      }

      return rowMap;
   }

   public Set<class_2769<?>> rowKeySet() {
      return this.owner.getVanillaPropertyMap().keySet();
   }

   public Set<Comparable<?>> columnKeySet() {
      FastMap<S> map = this.owner.getStateMap();
      Set<Comparable<?>> rowMap = new HashSet<>();

      for (int i = 0; i < map.numProperties(); i++) {
         class_2769<?> rowKey = map.getKey(i).getProperty();
         Comparable<?> contained = map.getValue(this.owner.getStateIndex(), (class_2769<Comparable<?>>)rowKey);

         for (Comparable<?> val : rowKey.method_11898()) {
            if (!val.equals(contained)) {
               rowMap.add(val);
            }
         }
      }

      return rowMap;
   }

   public Collection<S> values() {
      FastMap<S> map = this.owner.getStateMap();
      int index = this.owner.getStateIndex();
      Set<S> rowMap = new HashSet<>();

      for (int i = 0; i < map.numProperties(); i++) {
         class_2769<?> rowKey = map.getKey(i).getProperty();
         Comparable<?> contained = map.getValue(index, (class_2769<Comparable<?>>)rowKey);

         for (Comparable<?> val : rowKey.method_11898()) {
            if (!val.equals(contained)) {
               rowMap.add(map.with(index, rowKey, val));
            }
         }
      }

      return rowMap;
   }

   public Map<class_2769<?>, Map<Comparable<?>, S>> rowMap() {
      FastMap<S> map = this.owner.getStateMap();
      Map<class_2769<?>, Map<Comparable<?>, S>> rowMap = new HashMap<>();

      for (int i = 0; i < map.numProperties(); i++) {
         class_2769<?> rowKey = map.getKey(i).getProperty();
         rowMap.put(rowKey, this.row(rowKey));
      }

      return rowMap;
   }

   public Map<Comparable<?>, Map<class_2769<?>, S>> columnMap() {
      Map<class_2769<?>, Map<Comparable<?>, S>> rowMap = this.rowMap();
      Map<Comparable<?>, Map<class_2769<?>, S>> colMap = new HashMap<>();

      for (Entry<class_2769<?>, Map<Comparable<?>, S>> entry : rowMap.entrySet()) {
         for (Entry<Comparable<?>, S> innerEntry : entry.getValue().entrySet()) {
            colMap.computeIfAbsent(innerEntry.getKey(), $ -> new HashMap<>()).put(entry.getKey(), innerEntry.getValue());
         }
      }

      return colMap;
   }
}
