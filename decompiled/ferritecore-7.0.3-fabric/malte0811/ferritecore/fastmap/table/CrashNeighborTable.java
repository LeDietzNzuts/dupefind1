package malte0811.ferritecore.fastmap.table;

import com.google.common.collect.Table.Cell;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2769;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CrashNeighborTable<S> extends NeighborTableBase<S> {
   private static final CrashNeighborTable<?> INSTANCE = new CrashNeighborTable();

   public static <S> CrashNeighborTable<S> getInstance() {
      return (CrashNeighborTable<S>)INSTANCE;
   }

   private CrashNeighborTable() {
   }

   public boolean contains(@Nullable Object rowKey, @Nullable Object columnKey) {
      return crashOnAccess();
   }

   public boolean containsRow(@Nullable Object rowKey) {
      return crashOnAccess();
   }

   public boolean containsColumn(@Nullable Object columnKey) {
      return crashOnAccess();
   }

   public boolean containsValue(@Nullable Object value) {
      return crashOnAccess();
   }

   public S get(@Nullable Object rowKey, @Nullable Object columnKey) {
      return crashOnAccess();
   }

   public boolean isEmpty() {
      return crashOnAccess();
   }

   public int size() {
      return crashOnAccess();
   }

   public Map<Comparable<?>, S> row(@NotNull class_2769<?> rowKey) {
      return crashOnAccess();
   }

   public Map<class_2769<?>, S> column(@NotNull Comparable<?> columnKey) {
      return crashOnAccess();
   }

   public Set<Cell<class_2769<?>, Comparable<?>, S>> cellSet() {
      return crashOnAccess();
   }

   public Set<class_2769<?>> rowKeySet() {
      return crashOnAccess();
   }

   public Set<Comparable<?>> columnKeySet() {
      return crashOnAccess();
   }

   public Collection<S> values() {
      return crashOnAccess();
   }

   public Map<class_2769<?>, Map<Comparable<?>, S>> rowMap() {
      return crashOnAccess();
   }

   public Map<Comparable<?>, Map<class_2769<?>, S>> columnMap() {
      return crashOnAccess();
   }

   private static <T> T crashOnAccess() {
      throw new UnsupportedOperationException(
         "A mod tried to access the state neighbor table directly. Please report this at https://github.com/malte0811/FerriteCore/issues. As a temporary workaround you can enable \"populateNeighborTable\" in the FerriteCore config"
      );
   }
}
