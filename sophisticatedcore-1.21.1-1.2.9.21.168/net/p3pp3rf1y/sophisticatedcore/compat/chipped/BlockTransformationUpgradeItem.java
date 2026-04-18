package net.p3pp3rf1y.sophisticatedcore.compat.chipped;

import earth.terrarium.chipped.common.recipes.ChippedRecipe;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_3956;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeCountLimitConfig;
import net.p3pp3rf1y.sophisticatedcore.upgrades.IUpgradeItem;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeType;

public class BlockTransformationUpgradeItem extends UpgradeItemBase<BlockTransformationUpgradeWrapper> {
   private static final UpgradeType<BlockTransformationUpgradeWrapper> TYPE = new UpgradeType<>(BlockTransformationUpgradeWrapper::new);
   private final Supplier<class_3956<ChippedRecipe>> getRecipeType;

   public BlockTransformationUpgradeItem(Supplier<class_3956<ChippedRecipe>> getRecipeType, IUpgradeCountLimitConfig upgradeTypeLimitConfig) {
      super(upgradeTypeLimitConfig);
      this.getRecipeType = getRecipeType;
   }

   @Override
   public UpgradeType<BlockTransformationUpgradeWrapper> getType() {
      return TYPE;
   }

   @Override
   public List<IUpgradeItem.UpgradeConflictDefinition> getUpgradeConflicts() {
      return List.of();
   }

   public class_3956<ChippedRecipe> getRecipeType() {
      return this.getRecipeType.get();
   }
}
