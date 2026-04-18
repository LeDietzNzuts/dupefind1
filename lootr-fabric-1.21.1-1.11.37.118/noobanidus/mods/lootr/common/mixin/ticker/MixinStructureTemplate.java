package noobanidus.mods.lootr.common.mixin.ticker;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_3499;
import net.minecraft.class_5425;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.entity.LootrItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(class_3499.class)
public class MixinStructureTemplate {
   @WrapOperation(
      method = {"lambda$placeEntities$5", "lambda$addEntitiesToWorld$5", "method_17917"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_5425;method_30771(Lnet/minecraft/class_1297;)V")
   )
   private static void lootr$AddEntitiesToWorldInject(class_5425 level, class_1297 entity, Operation<Void> original) {
      if (LootrAPI.shouldConvertStructureItemFrames()
         && entity.method_5864().method_20210(LootrTags.Entity.CONVERT_ITEM_FRAMES)
         && !entity.method_5752().contains(LootrConstants.CAN_CONVERT_TAG)) {
         ILootrItemFrameAdapter<class_1297> adapter = LootrAPI.getItemFrameAdapter(entity);
         if (adapter != null && !adapter.isFixed(entity) && !adapter.isInvisible(entity)) {
            class_1799 contained = adapter.getItem(entity);
            if (!contained.method_7960() && !contained.method_31573(LootrTags.Items.ITEM_FRAME_CONVERT_BLACKLIST)) {
               LootrItemFrame newItemFrame = new LootrItemFrame(level.method_8410(), adapter.getPos(entity), adapter.getDirection(entity));
               newItemFrame.lootrSetItem(contained);
               PlatformAPI.copyEntityData(adapter, entity, newItemFrame);
               level.method_30771(newItemFrame);
               return;
            }
         }
      }

      original.call(new Object[]{level, entity});
   }
}
