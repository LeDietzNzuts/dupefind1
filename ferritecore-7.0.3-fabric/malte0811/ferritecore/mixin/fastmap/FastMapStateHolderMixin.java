package malte0811.ferritecore.mixin.fastmap;

import com.google.common.collect.Table;
import it.unimi.dsi.fastutil.objects.Reference2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ObjectMap;
import java.util.Map;
import malte0811.ferritecore.ducks.FastMapStateHolder;
import malte0811.ferritecore.fastmap.FastMap;
import malte0811.ferritecore.impl.StateHolderImpl;
import net.minecraft.class_2688;
import net.minecraft.class_2769;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2688.class)
public abstract class FastMapStateHolderMixin<O, S> implements FastMapStateHolder<S> {
   @Mutable
   @Shadow
   @Final
   private Reference2ObjectArrayMap<class_2769<?>, Comparable<?>> field_24738;
   @Shadow
   private Table<class_2769<?>, Comparable<?>, S> field_24741;
   private int ferritecore_globalTableIndex;
   private FastMap<S> ferritecore_globalTable;

   @Redirect(
      method = {"setValue", "trySetValue"},
      at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Table;get(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", remap = false)
   )
   public Object getNeighborFromFastMap(Table<?, ?, ?> ignore, Object rowKey, Object columnKey) {
      return this.ferritecore_globalTable.with(this.ferritecore_globalTableIndex, (class_2769<?>)rowKey, columnKey);
   }

   @Overwrite
   public void method_28496(Map<Map<class_2769<?>, Comparable<?>>, S> states) {
      StateHolderImpl.populateNeighbors(states, this);
   }

   @Override
   public FastMap<S> getStateMap() {
      return this.ferritecore_globalTable;
   }

   @Override
   public int getStateIndex() {
      return this.ferritecore_globalTableIndex;
   }

   @Override
   public Reference2ObjectMap<class_2769<?>, Comparable<?>> getVanillaPropertyMap() {
      return this.field_24738;
   }

   @Override
   public void replacePropertyMap(Reference2ObjectMap<class_2769<?>, Comparable<?>> newMap) {
      this.field_24738 = (Reference2ObjectArrayMap<class_2769<?>, Comparable<?>>)newMap;
   }

   @Override
   public void setStateMap(FastMap<S> newValue) {
      this.ferritecore_globalTable = newValue;
   }

   @Override
   public void setStateIndex(int newValue) {
      this.ferritecore_globalTableIndex = newValue;
   }

   @Override
   public void setNeighborTable(Table<class_2769<?>, Comparable<?>, S> table) {
      this.field_24741 = table;
   }

   @Override
   public Table<class_2769<?>, Comparable<?>, S> getNeighborTable() {
      return this.field_24741;
   }
}
