package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import java.util.Collections;
import java.util.List;
import me.shedaniel.clothconfig2.api.ScissorsHandler;
import me.shedaniel.clothconfig2.api.ScissorsScreen;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1041;
import net.minecraft.class_310;
import org.jetbrains.annotations.ApiStatus.Internal;

@Environment(EnvType.CLIENT)
@Internal
public final class ScissorsHandlerImpl implements ScissorsHandler {
   @Internal
   public static final ScissorsHandler INSTANCE = new ScissorsHandlerImpl();
   private final List<Rectangle> scissorsAreas = Lists.newArrayList();

   @Override
   public void clearScissors() {
      this.scissorsAreas.clear();
      this.applyScissors();
   }

   @Override
   public List<Rectangle> getScissorsAreas() {
      return Collections.unmodifiableList(this.scissorsAreas);
   }

   @Override
   public void scissor(Rectangle rectangle) {
      this.scissorsAreas.add(rectangle);
      this.applyScissors();
   }

   @Override
   public void removeLastScissor() {
      if (!this.scissorsAreas.isEmpty()) {
         this.scissorsAreas.remove(this.scissorsAreas.size() - 1);
      }

      this.applyScissors();
   }

   @Override
   public void applyScissors() {
      if (!this.scissorsAreas.isEmpty()) {
         Rectangle r = this.scissorsAreas.get(0).clone();

         for (int i = 1; i < this.scissorsAreas.size(); i++) {
            Rectangle r1 = this.scissorsAreas.get(i);
            if (!r.intersects(r1)) {
               if (class_310.method_1551().field_1755 instanceof ScissorsScreen) {
                  this._applyScissor(((ScissorsScreen)class_310.method_1551().field_1755).handleScissor(new Rectangle()));
               } else {
                  this._applyScissor(new Rectangle());
               }

               return;
            }

            r.setBounds(r.intersection(r1));
         }

         r.setBounds(Math.min(r.x, r.x + r.width), Math.min(r.y, r.y + r.height), Math.abs(r.width), Math.abs(r.height));
         if (class_310.method_1551().field_1755 instanceof ScissorsScreen) {
            this._applyScissor(((ScissorsScreen)class_310.method_1551().field_1755).handleScissor(r));
         } else {
            this._applyScissor(r);
         }
      } else if (class_310.method_1551().field_1755 instanceof ScissorsScreen) {
         this._applyScissor(((ScissorsScreen)class_310.method_1551().field_1755).handleScissor(null));
      } else {
         this._applyScissor(null);
      }
   }

   public void _applyScissor(Rectangle r) {
      if (r != null) {
         GlStateManager._enableScissorTest();
         if (r.isEmpty()) {
            GlStateManager._scissorBox(0, 0, 0, 0);
         } else {
            class_1041 window = class_310.method_1551().method_22683();
            double scaleFactor = window.method_4495();
            GlStateManager._scissorBox(
               (int)(r.x * scaleFactor),
               (int)((window.method_4502() - r.height - r.y) * scaleFactor),
               (int)(r.width * scaleFactor),
               (int)(r.height * scaleFactor)
            );
         }
      } else {
         GlStateManager._disableScissorTest();
      }
   }
}
