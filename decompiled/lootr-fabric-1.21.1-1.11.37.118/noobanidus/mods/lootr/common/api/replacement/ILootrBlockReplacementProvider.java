package noobanidus.mods.lootr.common.api.replacement;

import java.util.function.Function;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_6862;
import org.jetbrains.annotations.Nullable;

public interface ILootrBlockReplacementProvider extends Function<class_2248, class_2248> {
   default int getPriority() {
      return 0;
   }

   class_6862<class_2248> getApplicableTag();

   class_2248 getBlock();

   @Nullable
   default class_2248 apply(class_2248 block) {
      if (block == this.getBlock()) {
         return null;
      } else {
         class_2680 defaultState = block.method_9564();
         return !defaultState.method_26215() && defaultState.method_26164(this.getApplicableTag()) ? this.getBlock() : null;
      }
   }

   default class_2680 copyTypeSpecificProperties(class_2680 from, class_2680 to) {
      return to;
   }
}
