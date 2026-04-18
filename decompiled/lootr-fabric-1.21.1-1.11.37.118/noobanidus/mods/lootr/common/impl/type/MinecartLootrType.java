package noobanidus.mods.lootr.common.impl.type;

import com.google.auto.service.AutoService;
import net.minecraft.class_1299;
import net.minecraft.class_2248;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrType.class)
public class MinecartLootrType implements ILootrType {
   @Override
   public String getName() {
      return BuiltInLootrTypes.TYPE_MINECART;
   }

   @Nullable
   @Override
   public class_2248 getReplacementBlock() {
      return null;
   }

   @Nullable
   @Override
   public class_1299<?> getReplacementEntity() {
      return class_1299.field_6126;
   }

   @Override
   public void callback() {
      BuiltInLootrTypes.MINECART = this;
   }

   @Override
   public boolean isEntity() {
      return true;
   }
}
