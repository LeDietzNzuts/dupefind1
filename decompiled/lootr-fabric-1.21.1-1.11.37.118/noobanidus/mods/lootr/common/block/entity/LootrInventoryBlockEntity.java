package noobanidus.mods.lootr.common.block.entity;

import com.google.auto.service.AutoService;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrBlockEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;

public class LootrInventoryBlockEntity extends LootrChestBlockEntity {
   public LootrInventoryBlockEntity(class_2338 pWorldPosition, class_2680 pBlockState) {
      super(LootrRegistry.getInventoryBlockEntity(), pWorldPosition, pBlockState);
   }

   @Override
   protected void method_11049(class_1937 level, class_2338 pos, class_2680 state, int p_155868_, int p_155869_) {
      super.method_11049(level, pos, state, p_155868_, p_155869_);
      if (LootrAPI.isCustomTrapped() && p_155868_ != p_155869_) {
         class_2248 block = state.method_26204();
         level.method_8452(pos, block);
         level.method_8452(pos.method_10074(), block);
      }
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.INVENTORY;
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return true;
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.INVENTORY;
   }

   @AutoService(ILootrBlockEntityConverter.class)
   public static class DefaultBlockEntityConverter implements ILootrBlockEntityConverter<LootrInventoryBlockEntity> {
      public ILootrBlockEntity apply(LootrInventoryBlockEntity blockEntity) {
         return blockEntity;
      }

      @Override
      public class_2591<?> getBlockEntityType() {
         return LootrRegistry.getInventoryBlockEntity();
      }
   }
}
