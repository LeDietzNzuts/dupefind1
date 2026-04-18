package org.embeddedt.modernfix;

import com.google.common.cache.CacheLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.tuple.Pair;

public class FileWalker extends CacheLoader<Pair<Path, Integer>, List<Path>> {
   public static final FileWalker INSTANCE = new FileWalker();

   public List<Path> load(Pair<Path, Integer> key) throws Exception {
      List var3;
      try (Stream<Path> stream = Files.walk((Path)key.getLeft(), (Integer)key.getRight())) {
         var3 = stream.collect(Collectors.toList());
      }

      return var3;
   }
}
