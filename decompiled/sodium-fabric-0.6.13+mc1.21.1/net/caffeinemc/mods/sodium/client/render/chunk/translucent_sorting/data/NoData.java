package net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.data;

import net.caffeinemc.mods.sodium.client.render.chunk.translucent_sorting.SortType;
import net.minecraft.class_4076;

public class NoData extends TranslucentData {
   private final SortType reason;

   private NoData(class_4076 sectionPos, SortType reason) {
      super(sectionPos);
      this.reason = reason;
   }

   @Override
   public SortType getSortType() {
      return this.reason;
   }

   public static NoData forEmptySection(class_4076 sectionPos) {
      return new NoData(sectionPos, SortType.EMPTY_SECTION);
   }

   public static NoData forNoTranslucent(class_4076 sectionPos) {
      return new NoData(sectionPos, SortType.NO_TRANSLUCENT);
   }
}
