package noobanidus.mods.lootr.common.impl.type;

import com.google.auto.service.AutoService;
import net.minecraft.class_1263;
import net.minecraft.class_1299;
import net.minecraft.class_2248;
import net.minecraft.class_3218;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrType.class)
public class SimpleLootrType implements ILootrType {
   @Override
   public String getName() {
      return BuiltInLootrTypes.TYPE_SIMPLE;
   }

   @Nullable
   @Override
   public class_2248 getReplacementBlock() {
      return null;
   }

   @Nullable
   @Override
   public class_1299<?> getReplacementEntity() {
      return null;
   }

   @Override
   public void callback() {
      BuiltInLootrTypes.SIMPLE = this;
   }

   @Nullable
   @Override
   public class_1263 getContainer(ILootrInfo info, class_3218 level) {
      return level.method_8321(info.getInfoPos()) instanceof class_1263 container ? container : null;
   }
}
