package net.caffeinemc.mods.lithium.common.util;

import net.minecraft.class_4076;
import net.minecraft.class_5539;

public class Pos {
   public static class BlockCoord {
      public static int getYSize(class_5539 view) {
         return view.method_31605();
      }

      public static int getMinY(class_5539 view) {
         return view.method_31607();
      }

      public static int getMaxYInclusive(class_5539 view) {
         return view.method_31600() - 1;
      }

      public static int getMaxYExclusive(class_5539 view) {
         return view.method_31600();
      }

      public static int getMaxInSectionCoord(int sectionCoord) {
         return 15 + getMinInSectionCoord(sectionCoord);
      }

      public static int getMaxYInSectionIndex(class_5539 view, int sectionIndex) {
         return getMaxInSectionCoord(Pos.SectionYCoord.fromSectionIndex(view, sectionIndex));
      }

      public static int getMinInSectionCoord(int sectionCoord) {
         return class_4076.method_18688(sectionCoord);
      }

      public static int getMinYInSectionIndex(class_5539 view, int sectionIndex) {
         return getMinInSectionCoord(Pos.SectionYCoord.fromSectionIndex(view, sectionIndex));
      }
   }

   public static class ChunkCoord {
      public static int fromBlockCoord(int blockCoord) {
         return class_4076.method_18675(blockCoord);
      }

      public static int fromBlockSize(int i) {
         return i >> 4;
      }
   }

   public static class SectionYCoord {
      public static int getNumYSections(class_5539 view) {
         return view.method_32890();
      }

      public static int getMinYSection(class_5539 view) {
         return view.method_32891();
      }

      public static int getMaxYSectionInclusive(class_5539 view) {
         return view.method_31597() - 1;
      }

      public static int getMaxYSectionExclusive(class_5539 view) {
         return view.method_31597();
      }

      public static int fromSectionIndex(class_5539 view, int sectionCoord) {
         return sectionCoord + getMinYSection(view);
      }

      public static int fromBlockCoord(int blockCoord) {
         return class_4076.method_18675(blockCoord);
      }
   }

   public static class SectionYIndex {
      public static int getNumYSections(class_5539 view) {
         return view.method_32890();
      }

      public static int getMinYSectionIndex(class_5539 view) {
         return 0;
      }

      public static int getMaxYSectionIndexInclusive(class_5539 view) {
         return view.method_32890() - 1;
      }

      public static int getMaxYSectionIndexExclusive(class_5539 view) {
         return view.method_32890();
      }

      public static int fromSectionCoord(class_5539 view, int sectionCoord) {
         return sectionCoord - view.method_32891();
      }

      public static int fromBlockCoord(class_5539 view, int blockCoord) {
         return fromSectionCoord(view, class_4076.method_18675(blockCoord));
      }
   }
}
