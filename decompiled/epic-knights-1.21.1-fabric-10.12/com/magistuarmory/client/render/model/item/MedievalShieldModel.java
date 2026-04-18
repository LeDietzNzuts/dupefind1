package com.magistuarmory.client.render.model.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class MedievalShieldModel extends class_3879 {
   private final class_630 root;
   private final class_630[] plate;
   private final class_630 handle;

   public MedievalShieldModel(class_630 root) {
      super(class_1921::method_23576);
      this.root = root;
      this.plate = new class_630[]{root.method_32086("plate")};
      this.handle = root.method_32086("handle");
   }

   public class_630[] plate() {
      return this.plate;
   }

   public class_630 handle() {
      return this.handle;
   }

   public void method_2828(class_4587 pose, class_4588 vertexconsumer, int i, int j, int color) {
      this.root.method_22699(pose, vertexconsumer, i, j, color);
   }
}
