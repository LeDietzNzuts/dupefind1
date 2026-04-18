package noobanidus.mods.lootr.common.impl.adapter;

import com.google.auto.service.AutoService;
import net.minecraft.class_1799;
import net.minecraft.class_2371;
import net.minecraft.class_2621;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrDataAdapter.class)
public class RandomizableContainerBlockEntityAdapter implements ILootrDataAdapter<class_2621> {
   @Override
   public Class<class_2621> getAssignableClass() {
      return class_2621.class;
   }

   public class_5321<class_52> getLootTable(class_2621 entity) {
      return entity.method_54869();
   }

   public long getLootSeed(class_2621 entity) {
      return entity.method_54870();
   }

   public void setLootTable(class_2621 entity, @Nullable class_5321<class_52> table, long seed) {
      entity.method_54867(table, seed);
   }

   @Nullable
   public class_2371<class_1799> getInventoryCopy(class_2621 entity) {
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

   @Override
   public int priority() {
      return -100;
   }
}
