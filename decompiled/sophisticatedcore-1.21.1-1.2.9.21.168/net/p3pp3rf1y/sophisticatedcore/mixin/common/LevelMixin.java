package net.p3pp3rf1y.sophisticatedcore.mixin.common;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2586;
import net.p3pp3rf1y.sophisticatedcore.extensions.block.entity.SophisticatedBlockEntity;
import net.p3pp3rf1y.sophisticatedcore.extensions.world.SophisticatedLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1937.class)
public abstract class LevelMixin implements class_1936, SophisticatedLevel {
   @Shadow
   private boolean field_9249;
   @Unique
   private final ArrayList<class_2586> sophisticatedCore_freshBlockEntities = new ArrayList<>();
   @Unique
   private final ArrayList<class_2586> sophisticatedCore_pendingFreshBlockEntities = new ArrayList<>();

   @Inject(
      method = "tickBlockEntities",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", shift = Shift.AFTER)
   )
   public void port_lib$pendingBlockEntities(CallbackInfo ci) {
      if (!this.sophisticatedCore_pendingFreshBlockEntities.isEmpty()) {
         this.sophisticatedCore_freshBlockEntities.addAll(this.sophisticatedCore_pendingFreshBlockEntities);
         this.sophisticatedCore_pendingFreshBlockEntities.clear();
      }
   }

   @Inject(method = "tickBlockEntities", at = @At(value = "INVOKE", target = "Ljava/util/List;isEmpty()Z"))
   public void port_lib$onBlockEntitiesLoad(CallbackInfo ci) {
      if (!this.sophisticatedCore_freshBlockEntities.isEmpty()) {
         this.sophisticatedCore_freshBlockEntities.forEach(SophisticatedBlockEntity::sophisticatedCore_onLoad);
         this.sophisticatedCore_freshBlockEntities.clear();
      }
   }

   @Unique
   @Override
   public void sophisticatedCore_addFreshBlockEntities(Collection<class_2586> beList) {
      if (this.field_9249) {
         this.sophisticatedCore_pendingFreshBlockEntities.addAll(beList);
      } else {
         this.sophisticatedCore_freshBlockEntities.addAll(beList);
      }
   }
}
