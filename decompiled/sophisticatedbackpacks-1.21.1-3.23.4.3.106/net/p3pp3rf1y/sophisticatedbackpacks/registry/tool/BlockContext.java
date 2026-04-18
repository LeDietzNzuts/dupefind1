package net.p3pp3rf1y.sophisticatedbackpacks.registry.tool;

import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2680;

public class BlockContext {
   private final class_1937 level;
   private final class_2680 state;
   private final class_2248 block;
   private final class_2338 pos;

   public BlockContext(class_1937 level, class_2680 state, class_2248 block, class_2338 pos) {
      this.level = level;
      this.state = state;
      this.block = block;
      this.pos = pos;
   }

   public class_1937 getLevel() {
      return this.level;
   }

   public class_2680 getState() {
      return this.state;
   }

   public class_2248 getBlock() {
      return this.block;
   }

   public class_2338 getPos() {
      return this.pos;
   }
}
