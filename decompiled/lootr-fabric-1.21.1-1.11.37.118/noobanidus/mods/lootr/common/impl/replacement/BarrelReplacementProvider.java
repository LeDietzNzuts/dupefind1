package noobanidus.mods.lootr.common.impl.replacement;

import com.google.auto.service.AutoService;
import net.minecraft.class_2248;
import net.minecraft.class_6862;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.api.replacement.ILootrBlockReplacementProvider;

@AutoService(ILootrBlockReplacementProvider.class)
public class BarrelReplacementProvider implements ILootrBlockReplacementProvider {
   @Override
   public class_6862<class_2248> getApplicableTag() {
      return LootrTags.Blocks.CONVERT_BARRELS;
   }

   @Override
   public class_2248 getBlock() {
      return LootrRegistry.getBarrelBlock();
   }
}
