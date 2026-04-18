package cn.enaium.onekeyminer.util

import java.util.HashSet
import java.util.LinkedList
import java.util.Queue
import net.minecraft.class_1937
import net.minecraft.class_2338
import net.minecraft.class_2960

public fun getName(identifier: class_2960): String {
   val var10000: java.lang.String = identifier.method_12836();
   var var10001: java.lang.String = identifier.method_12832();
   val var3: java.lang.String = identifier.method_12832();
   var10001 = var10001.substring(StringsKt.lastIndexOf$default(var3, "/", 0, false, 6, null) + 1);
   return "$var10000:$var10001";
}

public fun getName(world: class_1937, blockPos: class_2338): String {
   val var10000: class_2960 = world.method_8320(blockPos).method_26204().method_26162().method_29177();
   return getName(var10000);
}

public fun findBlocks(world: class_1937, centerPos: class_2338, limit: Int): Set<class_2338> {
   val blockList: java.util.Set = new HashSet();
   val centerBlockName: java.lang.String = getName(world, centerPos);
   val queue: Queue = new LinkedList();
   val visited: java.util.Set = new HashSet();
   queue.offer(centerPos);

   while (!queue.isEmpty() && blockList.size() < limit) {
      val queueSize: Int = queue.size();

      for (int i = 0; i < queueSize && blockList.size() < limit; i++) {
         val currentPos: class_2338 = queue.poll() as class_2338;
         if (!(getName(world, currentPos) == centerBlockName)) {
            visited.add(currentPos);
         } else {
            blockList.add(currentPos);
            visited.add(currentPos);

            for (int xOffset = -1; xOffset < 2; xOffset++) {
               for (int yOffset = -1; yOffset < 2; yOffset++) {
                  for (int zOffset = -1; zOffset < 2; zOffset++) {
                     if (xOffset != 0 || yOffset != 0 || zOffset != 0) {
                        if (_Assertions.ENABLED) {
                        }

                        val adjacentPos: class_2338 = currentPos.method_10069(xOffset, yOffset, zOffset);
                        if (!visited.contains(adjacentPos)) {
                           queue.offer(adjacentPos);
                           visited.add(adjacentPos);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   return blockList;
}
