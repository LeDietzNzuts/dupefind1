package noobanidus.mods.lootr.common.api.adapter;

import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_3829;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import org.jetbrains.annotations.Nullable;

public non-sealed interface ILootrDataAdapter<T> extends ILootrAdapter<T> {
   @Nullable
   class_5321<class_52> getLootTable(T var1);

   long getLootSeed(T var1);

   void setLootTable(T var1, @Nullable class_5321<class_52> var2, long var3);

   default boolean hasCopyableComponentsViaItem(T entity) {
      return false;
   }

   @Nullable
   default class_2371<class_1799> getInventoryCopy(T entity) {
      return null;
   }

   default void clear(T entity) {
      class_3829.method_16825(entity);
      this.setLootTable(entity, null, 0L);
   }
}
