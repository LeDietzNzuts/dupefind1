package malte0811.ferritecore.mixin.dedupmultipart;

import java.util.List;
import java.util.function.Predicate;
import malte0811.ferritecore.impl.Deduplicator;
import net.minecraft.class_1087;
import net.minecraft.class_1095;
import net.minecraft.class_2680;
import net.minecraft.class_1095.class_1096;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1096.class)
public class MixinMultipartBuilder {
   @Redirect(method = "build", at = @At(value = "NEW", target = "(Ljava/util/List;)Lnet/minecraft/client/resources/model/MultiPartBakedModel;"))
   public class_1095 build(List<Pair<Predicate<class_2680>, class_1087>> selectors) {
      return Deduplicator.makeMultipartModel(selectors);
   }
}
