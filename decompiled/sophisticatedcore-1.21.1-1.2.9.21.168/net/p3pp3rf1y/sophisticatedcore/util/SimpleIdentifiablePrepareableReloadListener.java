package net.p3pp3rf1y.sophisticatedcore.util;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.class_2960;
import net.minecraft.class_3300;
import net.minecraft.class_3695;
import net.minecraft.class_4080;

public abstract class SimpleIdentifiablePrepareableReloadListener<T> extends class_4080<T> implements IdentifiableResourceReloadListener {
   private final class_2960 id;

   public SimpleIdentifiablePrepareableReloadListener(class_2960 id) {
      this.id = id;
   }

   public class_2960 getFabricId() {
      return this.id;
   }

   protected T method_18789(class_3300 resourceManager, class_3695 profiler) {
      return null;
   }
}
