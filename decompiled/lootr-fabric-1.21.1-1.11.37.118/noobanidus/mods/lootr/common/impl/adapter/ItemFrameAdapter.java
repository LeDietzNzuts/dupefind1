package noobanidus.mods.lootr.common.impl.adapter;

import com.google.auto.service.AutoService;
import net.minecraft.class_1533;
import net.minecraft.class_1799;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinItemFrame;

@AutoService(ILootrItemFrameAdapter.class)
public class ItemFrameAdapter implements ILootrItemFrameAdapter<class_1533> {
   @Override
   public Class<class_1533> getAssignableClass() {
      return class_1533.class;
   }

   public class_1799 getItem(class_1533 entity) {
      return entity.method_6940();
   }

   public int getRotation(class_1533 object) {
      return object.method_6934();
   }

   public class_2350 getDirection(class_1533 entity) {
      return entity.method_5735();
   }

   public class_2338 getPos(class_1533 entity) {
      return entity.method_59940();
   }

   public boolean isFixed(class_1533 object) {
      return ((AccessorMixinItemFrame)object).lootr$isFixed();
   }

   public boolean isInvisible(class_1533 object) {
      return object.method_5767();
   }
}
