package noobanidus.mods.lootr.common.impl.type;

import com.google.auto.service.AutoService;
import net.minecraft.class_1299;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrType.class)
public class PotLootrType implements ILootrType {
   @Override
   public String getName() {
      return BuiltInLootrTypes.TYPE_POT;
   }

   @Nullable
   @Override
   public class_2248 getReplacementBlock() {
      return class_2246.field_42752;
   }

   @Nullable
   @Override
   public class_1299<?> getReplacementEntity() {
      return null;
   }

   @Override
   public boolean canDecay() {
      return false;
   }

   @Override
   public boolean canRefresh() {
      return false;
   }

   @Override
   public void callback() {
      BuiltInLootrTypes.POT = this;
   }
}
