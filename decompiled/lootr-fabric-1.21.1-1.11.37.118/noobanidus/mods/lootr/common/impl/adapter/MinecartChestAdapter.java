package noobanidus.mods.lootr.common.impl.adapter;

import com.google.auto.service.AutoService;
import net.minecraft.class_1694;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrDataAdapter.class)
public class MinecartChestAdapter implements ILootrDataAdapter<class_1694> {
   @Override
   public Class<class_1694> getAssignableClass() {
      return class_1694.class;
   }

   public class_5321<class_52> getLootTable(class_1694 entity) {
      return entity.method_42276();
   }

   public long getLootSeed(class_1694 entity) {
      return entity.method_42277();
   }

   public void setLootTable(class_1694 entity, class_5321<class_52> table, long seed) {
      entity.method_7562(table, seed);
   }

   @Nullable
   public class_2371<class_1799> getInventoryCopy(class_1694 entity) {
      if (this.getLootTable(entity) != null) {
         return null;
      } else {
         class_2371<class_1799> result = class_2371.method_10213(entity.method_5439(), class_1799.field_8037);

         for (int i = 0; i < entity.method_5439(); i++) {
            result.set(i, entity.method_5438(i).method_7972());
         }

         return result;
      }
   }
}
