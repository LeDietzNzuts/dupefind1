package dev.architectury.hooks.fabric;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.class_3283;
import net.minecraft.class_3285;

public class PackRepositoryHooksImpl {
   public static void addSource(class_3283 repository, class_3285 source) {
      List<class_3285> set = new ArrayList<>(repository.field_14227);
      set.add(source);
      repository.field_14227 = new HashSet<>(set);
   }
}
