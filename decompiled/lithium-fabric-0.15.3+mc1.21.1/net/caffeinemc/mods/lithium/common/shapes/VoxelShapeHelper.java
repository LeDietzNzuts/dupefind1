package net.caffeinemc.mods.lithium.common.shapes;

import it.unimi.dsi.fastutil.doubles.Double2IntMap;
import it.unimi.dsi.fastutil.doubles.Double2IntOpenHashMap;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleComparators;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import it.unimi.dsi.fastutil.doubles.DoubleOpenHashSet;
import java.util.BitSet;
import java.util.List;
import java.util.Optional;
import net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.collisions.empty_space.ArrayVoxelShapeInvoker;
import net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.collisions.empty_space.BitSetDiscreteVoxelShapeAccessor;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_244;
import net.minecraft.class_265;
import net.minecraft.class_2350.class_2351;

public class VoxelShapeHelper {
   public static Optional<class_243> getClosestPointTo(class_243 target, class_265 collidingShape, List<class_238> boxes) {
      DoubleOpenHashSet xPoints = new DoubleOpenHashSet();
      DoubleOpenHashSet yPoints = new DoubleOpenHashSet();
      DoubleOpenHashSet zPoints = new DoubleOpenHashSet();
      xPoints.addAll(collidingShape.method_1109(class_2351.field_11048));
      yPoints.addAll(collidingShape.method_1109(class_2351.field_11052));
      zPoints.addAll(collidingShape.method_1109(class_2351.field_11051));
      double minX = collidingShape.method_1091(class_2351.field_11048);
      double maxX = collidingShape.method_1105(class_2351.field_11048);
      double minY = collidingShape.method_1091(class_2351.field_11052);
      double maxY = collidingShape.method_1105(class_2351.field_11052);
      double minZ = collidingShape.method_1091(class_2351.field_11051);
      double maxZ = collidingShape.method_1105(class_2351.field_11051);

      for (class_238 box : boxes) {
         if (box.field_1323 > minX) {
            xPoints.add(box.field_1323);
         }

         if (box.field_1320 < maxX) {
            xPoints.add(box.field_1320);
         }

         if (box.field_1322 > minY) {
            yPoints.add(box.field_1322);
         }

         if (box.field_1325 < maxY) {
            yPoints.add(box.field_1325);
         }

         if (box.field_1321 > minZ) {
            zPoints.add(box.field_1321);
         }

         if (box.field_1324 < maxZ) {
            zPoints.add(box.field_1324);
         }
      }

      DoubleArrayList xList = new DoubleArrayList(xPoints);
      DoubleList yList = new DoubleArrayList(yPoints);
      DoubleList zList = new DoubleArrayList(zPoints);
      xList.sort(DoubleComparators.NATURAL_COMPARATOR);
      yList.sort(DoubleComparators.NATURAL_COMPARATOR);
      zList.sort(DoubleComparators.NATURAL_COMPARATOR);
      Double2IntMap xIndex = new Double2IntOpenHashMap();
      Double2IntMap yIndex = new Double2IntOpenHashMap();
      Double2IntMap zIndex = new Double2IntOpenHashMap();

      for (int i = 0; i < xList.size(); i++) {
         xIndex.put(xList.getDouble(i), i);
      }

      for (int i = 0; i < yList.size(); i++) {
         yIndex.put(yList.getDouble(i), i);
      }

      for (int i = 0; i < zList.size(); i++) {
         zIndex.put(zList.getDouble(i), i);
      }

      int xSize = xList.size() - 1;
      int ySize = yList.size() - 1;
      int zSize = zList.size() - 1;
      class_244 bitSetVoxelSet = new class_244(xSize, ySize, zSize);
      bitSetVoxelSet.method_1049(0, 0, 0);
      bitSetVoxelSet.method_1049(xSize, ySize, zSize);
      BitSet bitSet = ((BitSetDiscreteVoxelShapeAccessor)bitSetVoxelSet).getField_1359();
      bitSet.clear();
      initVoxelSet(bitSet, collidingShape, boxes, xList, yList, zList, xIndex, yIndex, zIndex, xSize, ySize, zSize);
      class_265 shape = ArrayVoxelShapeInvoker.init(bitSetVoxelSet, xList, yList, zList);
      return shape.method_33661(target);
   }

   private static void initVoxelSet(
      BitSet voxelSet,
      class_265 collidingShape,
      List<class_238> boxes,
      DoubleArrayList xList,
      DoubleList yList,
      DoubleList zList,
      Double2IntMap xIndex,
      Double2IntMap yIndex,
      Double2IntMap zIndex,
      int xSize,
      int ySize,
      int zSize
   ) {
      for (class_238 collidingBox : collidingShape.method_1090()) {
         int minX = xIndex.get(collidingBox.field_1323);
         int maxX = xIndex.get(collidingBox.field_1320);
         int minY = yIndex.get(collidingBox.field_1322);
         int maxY = yIndex.get(collidingBox.field_1325);
         int minZ = zIndex.get(collidingBox.field_1321);
         int maxZ = zIndex.get(collidingBox.field_1324);

         for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
               for (int z = minZ; z < maxZ; z++) {
                  voxelSet.set(getIndex(x, y, z, xSize, ySize, zSize), true);
               }
            }
         }
      }

      BitSet remove = new BitSet(voxelSet.size());

      for (class_238 box : boxes) {
         int minX = xIndex.getOrDefault(box.field_1323, 0);
         int maxX = xIndex.getOrDefault(box.field_1320, xSize);
         int minY = yIndex.getOrDefault(box.field_1322, 0);
         int maxY = yIndex.getOrDefault(box.field_1325, ySize);
         int minZ = zIndex.getOrDefault(box.field_1321, 0);
         int maxZ = zIndex.getOrDefault(box.field_1324, zSize);

         for (int x = minX; x < maxX; x++) {
            for (int y = minY; y < maxY; y++) {
               for (int z = minZ; z < maxZ; z++) {
                  remove.set(getIndex(x, y, z, xSize, ySize, zSize));
               }
            }
         }
      }

      voxelSet.andNot(remove);
   }

   private static int getIndex(int x, int y, int z, int sizeX, int sizeY, int sizeZ) {
      return (x * sizeY + y) * sizeZ + z;
   }
}
