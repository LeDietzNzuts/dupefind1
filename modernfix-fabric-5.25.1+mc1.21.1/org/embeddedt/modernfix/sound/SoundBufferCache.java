package org.embeddedt.modernfix.sound;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2960;
import net.minecraft.class_4231;

public class SoundBufferCache extends LinkedHashMap<class_2960, CompletableFuture<class_4231>> {
   @Override
   protected boolean removeEldestEntry(Entry<class_2960, CompletableFuture<class_4231>> eldest) {
      return super.removeEldestEntry(eldest);
   }
}
