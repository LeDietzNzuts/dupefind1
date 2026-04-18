package noobanidus.mods.lootr.common.impl.type;

import com.google.auto.service.AutoService;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrType.class)
public class GravelLootrType extends BrushableLootrType {
   @Override
   public String getName() {
      return BuiltInLootrTypes.TYPE_GRAVEL;
   }

   @Nullable
   @Override
   public class_2248 getReplacementBlock() {
      return class_2246.field_10255;
   }

   @Override
   public void callback() {
      BuiltInLootrTypes.GRAVEL = this;
   }
}
