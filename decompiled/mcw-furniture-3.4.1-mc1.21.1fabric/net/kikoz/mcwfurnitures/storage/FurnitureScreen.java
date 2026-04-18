package net.kikoz.mcwfurnitures.storage;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.class_1661;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import net.minecraft.class_465;
import net.minecraft.class_757;

public class FurnitureScreen extends class_465<FurnitureScreenHandler> {
   private static final class_2960 TEXTURE = class_2960.method_60655("mcwfurnitures", "textures/gui/generic_27.png");

   public FurnitureScreen(FurnitureScreenHandler handler, class_1661 inventory, class_2561 title) {
      super(handler, inventory, title);
   }

   protected void method_2389(class_332 matrices, float delta, int mouseX, int mouseY) {
      RenderSystem.setShader(class_757::method_34542);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShaderTexture(0, TEXTURE);
      int x = (this.field_22789 - this.field_2792) / 2;
      int y = (this.field_22790 - this.field_2779) / 2;
      matrices.method_25302(TEXTURE, x, y, 0, 0, this.field_2792, this.field_2779);
   }

   public void method_25394(class_332 matrices, int mouseX, int mouseY, float delta) {
      this.method_25420(matrices, 0, 0, 0.0F);
      super.method_25394(matrices, mouseX, mouseY, delta);
      this.method_2380(matrices, mouseX, mouseY);
   }

   protected void method_25426() {
      super.method_25426();
   }
}
