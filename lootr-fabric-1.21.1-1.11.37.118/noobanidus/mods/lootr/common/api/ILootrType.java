package noobanidus.mods.lootr.common.api;

import net.minecraft.class_1263;
import net.minecraft.class_1299;
import net.minecraft.class_2248;
import net.minecraft.class_3218;
import noobanidus.mods.lootr.common.api.data.DefaultLootFiller;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.LootFiller;
import org.jetbrains.annotations.Nullable;

public interface ILootrType {
   String getName();

   @Nullable
   class_2248 getReplacementBlock();

   @Nullable
   class_1299<?> getReplacementEntity();

   default void callback() {
   }

   default LootFiller getDefaultFiller() {
      return DefaultLootFiller.getInstance();
   }

   default boolean canDecay() {
      return true;
   }

   default boolean canRefresh() {
      return true;
   }

   default boolean isEntity() {
      return false;
   }

   default boolean canBeMarkedUnopened() {
      return true;
   }

   default boolean canDropContentsWhenBroken() {
      return true;
   }

   default boolean displaysUnopenedParticle() {
      return true;
   }

   @Nullable
   default class_1263 getContainer(ILootrInfo info, class_3218 level) {
      if (this.isEntity() && this.getReplacementEntity() != null) {
         if (level.method_14190(info.getInfoUUID()) instanceof class_1263 container) {
            return container;
         }
      } else if (!this.isEntity() && level.method_8321(info.getInfoPos()) instanceof class_1263 container) {
         return container;
      }

      return null;
   }
}
