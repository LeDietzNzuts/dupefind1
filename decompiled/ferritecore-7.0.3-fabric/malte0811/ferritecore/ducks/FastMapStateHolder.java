package malte0811.ferritecore.ducks;

import com.google.common.collect.Table;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import malte0811.ferritecore.fastmap.FastMap;
import net.minecraft.class_2769;

public interface FastMapStateHolder<S> {
   FastMap<S> getStateMap();

   void setStateMap(FastMap<S> var1);

   int getStateIndex();

   void setStateIndex(int var1);

   Reference2ObjectMap<class_2769<?>, Comparable<?>> getVanillaPropertyMap();

   void replacePropertyMap(Reference2ObjectMap<class_2769<?>, Comparable<?>> var1);

   void setNeighborTable(Table<class_2769<?>, Comparable<?>, S> var1);

   Table<class_2769<?>, Comparable<?>, S> getNeighborTable();
}
