package noobanidus.mods.lootr.common.impl.adapter;

import com.google.auto.service.AutoService;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_8172;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrDataAdapter.class)
public class DecoratedPotBlockEntityAdapter implements ILootrDataAdapter<class_8172> {
   @Override
   public Class<class_8172> getAssignableClass() {
      return class_8172.class;
   }

   @Nullable
   public class_5321<class_52> getLootTable(class_8172 entity) {
      return entity.method_54869();
   }

   public long getLootSeed(class_8172 entity) {
      return entity.method_54870();
   }

   public void setLootTable(class_8172 entity, class_5321<class_52> table, long seed) {
      entity.method_54867(table, seed);
   }

   public boolean hasCopyableComponentsViaItem(class_8172 entity) {
      return LootrAPI.getDecorationsAdapter(entity) != PotDecorationsAdapter.EMPTY;
   }

   @Nullable
   public class_2371<class_1799> getInventoryCopy(class_8172 entity) {
      if (this.getLootTable(entity) == null) {
         class_2371<class_1799> result = class_2371.method_10213(1, class_1799.field_8037);
         result.set(0, entity.method_54079().method_7972());
         return result;
      } else {
         return null;
      }
   }
}
