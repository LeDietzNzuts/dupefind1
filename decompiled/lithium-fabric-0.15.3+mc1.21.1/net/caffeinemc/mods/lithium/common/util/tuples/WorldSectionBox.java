package net.caffeinemc.mods.lithium.common.util.tuples;

import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_3532;
import net.minecraft.class_4076;

public record WorldSectionBox(class_1937 world, int chunkX1, int chunkY1, int chunkZ1, int chunkX2, int chunkY2, int chunkZ2) {
   public static WorldSectionBox entityAccessBox(class_1937 world, class_238 box) {
      int minX = class_4076.method_32204(box.field_1323 - 2.0);
      int minY = class_4076.method_32204(box.field_1322 - 4.0);
      int minZ = class_4076.method_32204(box.field_1321 - 2.0);
      int maxX = class_4076.method_32204(box.field_1320 + 2.0) + 1;
      int maxY = class_4076.method_32204(box.field_1325) + 1;
      int maxZ = class_4076.method_32204(box.field_1324 + 2.0) + 1;
      return new WorldSectionBox(world, minX, minY, minZ, maxX, maxY, maxZ);
   }

   public static WorldSectionBox relevantExpandedBlocksBox(class_1937 world, class_238 box) {
      int minX = class_4076.method_18675(class_3532.method_15357(box.field_1323) - 1);
      int minY = class_4076.method_18675(class_3532.method_15357(box.field_1322) - 1);
      int minZ = class_4076.method_18675(class_3532.method_15357(box.field_1321) - 1);
      int maxX = class_4076.method_18675(class_3532.method_15357(box.field_1320) + 1) + 1;
      int maxY = class_4076.method_18675(class_3532.method_15357(box.field_1325) + 1) + 1;
      int maxZ = class_4076.method_18675(class_3532.method_15357(box.field_1324) + 1) + 1;
      return new WorldSectionBox(world, minX, minY, minZ, maxX, maxY, maxZ);
   }

   public static WorldSectionBox relevantFluidBox(class_1937 world, class_238 box) {
      int minX = class_4076.method_18675(class_3532.method_15357(box.field_1323));
      int minY = class_4076.method_18675(class_3532.method_15357(box.field_1322));
      int minZ = class_4076.method_18675(class_3532.method_15357(box.field_1321));
      int maxX = class_4076.method_18675(class_3532.method_15357(box.field_1320)) + 1;
      int maxY = class_4076.method_18675(class_3532.method_15357(box.field_1325)) + 1;
      int maxZ = class_4076.method_18675(class_3532.method_15357(box.field_1324)) + 1;
      return new WorldSectionBox(world, minX, minY, minZ, maxX, maxY, maxZ);
   }

   public int numSections() {
      return (this.chunkX2 - this.chunkX1) * (this.chunkY2 - this.chunkY1) * (this.chunkZ2 - this.chunkZ1);
   }

   public boolean matchesRelevantBlocksBox(class_238 box) {
      return class_4076.method_18675(class_3532.method_15357(box.field_1323) - 1) == this.chunkX1
         && class_4076.method_18675(class_3532.method_15357(box.field_1322) - 1) == this.chunkY1
         && class_4076.method_18675(class_3532.method_15357(box.field_1321) - 1) == this.chunkZ1
         && class_4076.method_18675(class_3532.method_15384(box.field_1320) + 1) + 1 == this.chunkX2
         && class_4076.method_18675(class_3532.method_15384(box.field_1325) + 1) + 1 == this.chunkY2
         && class_4076.method_18675(class_3532.method_15384(box.field_1324) + 1) + 1 == this.chunkZ2;
   }
}
