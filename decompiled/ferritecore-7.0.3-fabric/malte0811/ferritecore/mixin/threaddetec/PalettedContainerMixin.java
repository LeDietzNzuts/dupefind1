package malte0811.ferritecore.mixin.threaddetec;

import malte0811.ferritecore.ducks.SmallThreadDetectable;
import malte0811.ferritecore.util.SmallThreadingDetector;
import net.minecraft.class_2841;
import net.minecraft.class_5798;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_2841.class)
public class PalettedContainerMixin implements SmallThreadDetectable {
   @Shadow
   @Final
   @Mutable
   private class_5798 field_36300;
   private byte ferritecore$threadingState = 0;

   @Inject(
      method = {
            "<init>(Lnet/minecraft/core/IdMap;Ljava/lang/Object;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;)V",
            "<init>(Lnet/minecraft/core/IdMap;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;Lnet/minecraft/world/level/chunk/PalettedContainer$Data;)V",
            "<init>(Lnet/minecraft/core/IdMap;Lnet/minecraft/world/level/chunk/PalettedContainer$Strategy;Lnet/minecraft/world/level/chunk/PalettedContainer$Configuration;Lnet/minecraft/util/BitStorage;Ljava/util/List;)V"
      },
      at = @At("TAIL")
   )
   public void redirectBuildThreadingDetector(CallbackInfo ci) {
      this.field_36300 = null;
   }

   @Overwrite
   public void method_12334() {
      SmallThreadingDetector.acquire(this, "PalettedContainer");
   }

   @Overwrite
   public void method_12335() {
      SmallThreadingDetector.release(this);
   }

   @Override
   public byte ferritecore$getState() {
      return this.ferritecore$threadingState;
   }

   @Override
   public void ferritecore$setState(byte newState) {
      this.ferritecore$threadingState = newState;
   }
}
