package malte0811.ferritecore.fastmap.table;

import com.google.common.collect.Table;
import net.minecraft.class_2769;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NeighborTableBase<S> implements Table<class_2769<?>, Comparable<?>, S> {
   protected static final String ISSUES_URL = "https://github.com/malte0811/FerriteCore/issues";

   public void clear() {
      crashOnModify();
   }

   public final S put(@NotNull class_2769<?> rowKey, @NotNull Comparable<?> columnKey, @NotNull S value) {
      return crashOnModify();
   }

   public final void putAll(@NotNull Table<? extends class_2769<?>, ? extends Comparable<?>, ? extends S> table) {
      crashOnModify();
   }

   public final S remove(@Nullable Object rowKey, @Nullable Object columnKey) {
      return crashOnModify();
   }

   private static <T> T crashOnModify() {
      throw new UnsupportedOperationException(
         "A mod tried to modify the state neighbor table directly. Please report this at https://github.com/malte0811/FerriteCore/issues"
      );
   }
}
