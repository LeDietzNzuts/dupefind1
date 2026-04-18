package noobanidus.mods.lootr.common.integration.jade;

import net.minecraft.class_2246;
import net.minecraft.class_2680;
import noobanidus.mods.lootr.common.api.LootrTags;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class LootrWailaPlugin implements IWailaPlugin {
   public void registerClient(IWailaClientRegistration registration) {
      registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
         if (accessor instanceof BlockAccessor blockAccessor) {
            class_2680 newState;
            if (blockAccessor.getBlockState().method_26164(LootrTags.Blocks.SANDS)) {
               newState = class_2246.field_10102.method_9564();
            } else {
               if (!blockAccessor.getBlockState().method_26164(LootrTags.Blocks.GRAVELS)) {
                  return accessor;
               }

               newState = class_2246.field_10255.method_9564();
            }

            return registration.blockAccessor().from(blockAccessor).blockState(newState).build();
         } else {
            return accessor;
         }
      });
   }
}
