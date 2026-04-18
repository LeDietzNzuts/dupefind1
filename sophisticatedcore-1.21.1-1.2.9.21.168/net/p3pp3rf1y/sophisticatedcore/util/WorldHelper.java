package net.p3pp3rf1y.sophisticatedcore.util;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_1922;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;

public class WorldHelper {
   private WorldHelper() {
   }

   public static Optional<class_2586> getBlockEntity(@Nullable class_1922 level, class_2338 pos) {
      return getBlockEntity(level, pos, class_2586.class);
   }

   public static <T> Optional<T> getLoadedBlockEntity(@Nullable class_1937 level, class_2338 pos, Class<T> teClass) {
      return level != null && level.method_8477(pos) ? getBlockEntity(level, pos, teClass) : Optional.empty();
   }

   public static <T> Optional<T> getBlockEntity(@Nullable class_1922 level, class_2338 pos, Class<T> teClass) {
      if (level == null) {
         return Optional.empty();
      } else {
         class_2586 be = level.method_8321(pos);
         return teClass.isInstance(be) ? Optional.of(teClass.cast(be)) : Optional.empty();
      }
   }

   public static void notifyBlockUpdate(class_2586 tile) {
      class_1937 level = tile.method_10997();
      if (level != null) {
         level.method_8413(tile.method_11016(), tile.method_11010(), tile.method_11010(), 3);
      }
   }
}
