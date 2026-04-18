package noobanidus.mods.lootr.common.impl.type;

import net.minecraft.class_1299;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.data.DefaultBrushableLootFiller;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import org.jetbrains.annotations.Nullable;

public abstract class BrushableLootrType implements ILootrType {
   @Nullable
   @Override
   public class_1299<?> getReplacementEntity() {
      return null;
   }

   @Override
   public LootFiller getDefaultFiller() {
      return DefaultBrushableLootFiller.getInstance();
   }

   @Override
   public boolean canBeMarkedUnopened() {
      return false;
   }

   @Override
   public boolean canDropContentsWhenBroken() {
      return false;
   }

   @Override
   public boolean canDecay() {
      return false;
   }

   @Override
   public boolean canRefresh() {
      return false;
   }

   @Override
   public boolean displaysUnopenedParticle() {
      return false;
   }
}
