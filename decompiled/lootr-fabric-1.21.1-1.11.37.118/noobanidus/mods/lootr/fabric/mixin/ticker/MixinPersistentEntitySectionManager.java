package noobanidus.mods.lootr.fabric.mixin.ticker;

import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_3218;
import net.minecraft.class_3738;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5568;
import net.minecraft.class_5579;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.api.LootrTags;
import noobanidus.mods.lootr.common.api.PlatformAPI;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.entity.LootrChestMinecartEntity;
import noobanidus.mods.lootr.common.entity.LootrItemFrame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_5579.class)
public class MixinPersistentEntitySectionManager {
   @Inject(method = "method_31820", at = @At("HEAD"), cancellable = true)
   private void LootrAddEntity(class_5568 entityAccess, boolean bl, CallbackInfoReturnable<Boolean> cir) {
      if (!LootrAPI.isDisabled()) {
         if (entityAccess instanceof class_1297 entity) {
            if (entity.method_37908() instanceof class_3218 level && !level.method_8608()) {
               if (!LootrAPI.isDimensionBlocked(level.method_27983())) {
                  if (LootrAPI.shouldConvertStructureItemFrames()
                     && entity.method_5864().method_20210(LootrTags.Entity.CONVERT_ITEM_FRAMES)
                     && entity.method_5752().contains(LootrConstants.CAN_CONVERT_TAG)) {
                     ILootrItemFrameAdapter<class_1297> adapter = LootrAPI.getItemFrameAdapter(entity);
                     if (adapter == null) {
                        LootrAPI.LOG.error("No item frame adapter found for entity '{}' even though it is tagged for conversion.", entity);
                        return;
                     }

                     if (!adapter.isFixed(entity) && !adapter.isInvisible(entity)) {
                        class_1799 contained = adapter.getItem(entity);
                        if (!contained.method_7960() && !contained.method_31573(LootrTags.Items.ITEM_FRAME_CONVERT_BLACKLIST)) {
                           LootrItemFrame newItemFrame = new LootrItemFrame(level.method_8410(), adapter.getPos(entity), adapter.getDirection(entity));
                           newItemFrame.lootrSetItem(contained);
                           PlatformAPI.copyEntityData(adapter, entity, newItemFrame);
                           cir.setReturnValue(false);
                           cir.cancel();
                           level.method_8503().method_18858(new class_3738(0, () -> level.method_8649(newItemFrame)));
                        }
                     }
                  } else if (entity.method_5864().method_20210(LootrTags.Entity.CONVERT_ENTITIES)) {
                     ILootrDataAdapter<class_1297> adapterx = LootrAPI.getAdapter(entity);
                     if (adapterx == null) {
                        LootrAPI.LOG.error("No adapter found for entity '{}' even though it is tagged for conversion.", entity);
                        return;
                     }

                     class_5321<class_52> lootTable = adapterx.getLootTable(entity);
                     if (lootTable == null || LootrAPI.isLootTableBlacklisted(lootTable)) {
                        return;
                     }

                     long seed = adapterx.getLootSeed(entity);
                     LootrAPI.preProcess(level, entity, lootTable, seed);
                     LootrChestMinecartEntity lootrCart = new LootrChestMinecartEntity(
                        entity.method_37908(), entity.method_23317(), entity.method_23318(), entity.method_23321()
                     );
                     PlatformAPI.copyEntityData(adapterx, entity, lootrCart);
                     LootrAPI.postProcess(level, lootrCart, lootTable, seed);
                     cir.setReturnValue(false);
                     cir.cancel();
                     level.method_8503().method_18858(new class_3738(0, () -> level.method_8649(lootrCart)));
                  }
               }
            }
         }
      }
   }
}
