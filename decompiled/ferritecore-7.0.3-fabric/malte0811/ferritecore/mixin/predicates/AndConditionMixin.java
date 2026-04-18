package malte0811.ferritecore.mixin.predicates;

import java.util.function.Predicate;
import malte0811.ferritecore.impl.Deduplicator;
import malte0811.ferritecore.util.PredicateHelper;
import net.minecraft.class_2248;
import net.minecraft.class_2680;
import net.minecraft.class_2689;
import net.minecraft.class_812;
import net.minecraft.class_815;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = class_812.class, priority = 2000)
public class AndConditionMixin {
   @Shadow
   @Final
   private Iterable<? extends class_815> field_4324;

   @Overwrite
   public Predicate<class_2680> getPredicate(class_2689<class_2248, class_2680> stateContainer) {
      return Deduplicator.and(PredicateHelper.toCanonicalList(this.field_4324, stateContainer));
   }
}
