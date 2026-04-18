package malte0811.ferritecore.mixin.modelsides;

import java.util.List;
import java.util.Map;
import malte0811.ferritecore.impl.ModelSidesImpl;
import net.minecraft.class_1093;
import net.minecraft.class_2350;
import net.minecraft.class_777;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1093.class)
public class SimpleBakedModelMixin {
   @Shadow
   @Final
   @Mutable
   protected Map<class_2350, List<class_777>> field_5414;
   @Shadow
   @Final
   @Mutable
   protected List<class_777> field_5411;

   @Inject(method = "/<init>/", at = @At("TAIL"))
   private void minimizeFaceLists(CallbackInfo ci) {
      this.field_5411 = ModelSidesImpl.minimizeUnculled(this.field_5411);
      this.field_5414 = ModelSidesImpl.minimizeCulled(this.field_5414);
   }
}
