package noobanidus.mods.lootr.common.impl.adapter;

import com.google.auto.service.AutoService;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8174;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinBrushableBlockEntity;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrDataAdapter.class)
public class BrushableBlockEntityAdapter implements ILootrDataAdapter<class_8174> {
   @Override
   public Class<class_8174> getAssignableClass() {
      return class_8174.class;
   }

   public class_5321<class_52> getLootTable(class_8174 entity) {
      return ((AccessorMixinBrushableBlockEntity)entity).lootr$getLootTable();
   }

   public long getLootSeed(class_8174 entity) {
      return ((AccessorMixinBrushableBlockEntity)entity).lootr$getLootTableSeed();
   }

   public void setLootTable(class_8174 entity, class_5321<class_52> table, long seed) {
      entity.method_49216(table, seed);
   }

   @Nullable
   public class_2371<class_1799> getInventoryCopy(class_8174 entity) {
      if (this.getLootTable(entity) == null) {
         class_2371<class_1799> result = class_2371.method_10213(1, class_1799.field_8037);
         result.set(0, entity.method_49225().method_7972());
         return result;
      } else {
         return null;
      }
   }
}
