package dev.architectury.hooks.level.entity;

import net.minecraft.class_1297;
import net.minecraft.class_3726;
import net.minecraft.class_3727;
import org.jetbrains.annotations.Nullable;

public final class EntityHooks {
   private EntityHooks() {
   }

   @Nullable
   public static class_1297 fromCollision(class_3726 ctx) {
      return ctx instanceof class_3727 ? ((class_3727)ctx).method_32480() : null;
   }
}
