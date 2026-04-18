package malte0811.ferritecore.mixin.predicates;

import com.google.common.base.Splitter;
import java.util.function.Predicate;
import malte0811.ferritecore.impl.KeyValueConditionImpl;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_818;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_818.class, priority = 2000)
public class KeyValueConditionMixin {
   @Shadow
   @Final
   private String field_4333;
   @Shadow
   @Final
   private String field_4332;
   @Shadow
   @Final
   private static Splitter field_4334;

   @Overwrite
   public Predicate<class_2680> getPredicate(class_2689<class_2248, class_2680> stateContainer) {
      return KeyValueConditionImpl.getPredicate(stateContainer, this.field_4333, this.field_4332, field_4334);
   }
}
