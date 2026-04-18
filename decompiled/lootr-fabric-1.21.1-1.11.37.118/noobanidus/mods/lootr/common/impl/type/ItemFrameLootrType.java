package noobanidus.mods.lootr.common.impl.type;

import com.google.auto.service.AutoService;
import net.minecraft.class_1299;
import net.minecraft.class_2248;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.jetbrains.annotations.Nullable;

@AutoService(ILootrType.class)
public class ItemFrameLootrType implements ILootrType {
   @Override
   public String getName() {
      return BuiltInLootrTypes.TYPE_ITEM_FRAME;
   }

   @Nullable
   @Override
   public class_2248 getReplacementBlock() {
      return null;
   }

   @Nullable
   @Override
   public class_1299<?> getReplacementEntity() {
      return LootrRegistry.getItemFrame();
   }

   @Override
   public void callback() {
      BuiltInLootrTypes.ITEM_FRAME = this;
   }

   @Override
   public boolean isEntity() {
      return true;
   }

   @Override
   public boolean canDecay() {
      return false;
   }

   @Override
   public boolean canRefresh() {
      return false;
   }
}
