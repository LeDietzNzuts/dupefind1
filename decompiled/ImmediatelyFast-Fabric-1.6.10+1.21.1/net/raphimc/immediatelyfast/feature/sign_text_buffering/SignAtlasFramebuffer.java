package net.raphimc.immediatelyfast.feature.sign_text_buffering;

import net.minecraft.class_1044;
import net.minecraft.class_276;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import org.lwjgl.opengl.GL11C;

public class SignAtlasFramebuffer extends class_276 implements AutoCloseable {
   public static final int ATLAS_SIZE = 4096;
   private final class_2960 textureId;
   private final SignAtlasFramebuffer.Slot rootSlot;

   public SignAtlasFramebuffer() {
      super(false);
      this.method_1234(4096, 4096, class_310.field_1703);
      class_310.method_1551().method_1522().method_1235(true);
      this.textureId = class_2960.method_60655("immediatelyfast", "sign_atlas/" + this.field_1475);
      class_310.method_1551().method_1531().method_4616(this.textureId, new SignAtlasFramebuffer.FboTexture());
      this.rootSlot = new SignAtlasFramebuffer.Slot(null, 0, 0, 4096, 4096);
   }

   @Override
   public void close() {
      this.method_1238();
      class_310.method_1551().method_1522().method_1235(true);
   }

   public SignAtlasFramebuffer.Slot findSlot(int width, int height) {
      return this.rootSlot.findSlot(width, height);
   }

   public void clear() {
      this.method_1230(class_310.field_1703);
      class_310.method_1551().method_1522().method_1235(true);
      this.rootSlot.subSlot1 = null;
      this.rootSlot.subSlot2 = null;
   }

   public class_2960 getTextureId() {
      return this.textureId;
   }

   private class FboTexture extends class_1044 {
      public void method_4625(class_3300 manager) {
      }

      public void method_4528() {
      }

      public int method_4624() {
         return SignAtlasFramebuffer.this.field_1475;
      }
   }

   public class Slot {
      public final int x;
      public final int y;
      public final int width;
      public final int height;
      public final SignAtlasFramebuffer.Slot parentSlot;
      public SignAtlasFramebuffer.Slot subSlot1;
      public SignAtlasFramebuffer.Slot subSlot2;
      public boolean occupied;

      public Slot(final SignAtlasFramebuffer.Slot parentSlot, final int x, final int y, final int width, final int height) {
         this.parentSlot = parentSlot;
         this.x = x;
         this.y = y;
         this.width = width;
         this.height = height;
      }

      public void markFree() {
         if (this.subSlot1 != null || this.subSlot2 != null) {
            throw new UnsupportedOperationException("Cannot mark slot as free if it has sub slots");
         } else if (!this.occupied) {
            throw new UnsupportedOperationException("Cannot mark slot as free if it is not occupied");
         } else {
            this.occupied = false;
            removeUnoccupiedSubSlots(this);
            GL11C.glScissor(this.x, 4096 - this.y - this.height, this.width, this.height);
            GL11C.glEnable(3089);
            SignAtlasFramebuffer.this.method_1230(class_310.field_1703);
            GL11C.glDisable(3089);
            class_310.method_1551().method_1522().method_1235(true);
         }
      }

      public SignAtlasFramebuffer.Slot findSlot(int width, int height) {
         if (this.subSlot1 != null && this.subSlot2 != null) {
            SignAtlasFramebuffer.Slot slot = this.subSlot1.findSlot(width, height);
            if (slot == null) {
               slot = this.subSlot2.findSlot(width, height);
            }

            return slot;
         } else if (this.occupied) {
            return null;
         } else if (width > this.width || height > this.height) {
            return null;
         } else if (width == this.width && height == this.height) {
            this.occupied = true;
            return this;
         } else {
            int k = this.width - width;
            int l = this.height - height;
            if (k > l) {
               this.subSlot1 = SignAtlasFramebuffer.this.new Slot(this, this.x, this.y, width, this.height);
               this.subSlot2 = SignAtlasFramebuffer.this.new Slot(this, this.x + width, this.y, this.width - width, this.height);
            } else {
               this.subSlot1 = SignAtlasFramebuffer.this.new Slot(this, this.x, this.y, this.width, height);
               this.subSlot2 = SignAtlasFramebuffer.this.new Slot(this, this.x, this.y + height, this.width, this.height - height);
            }

            return this.subSlot1.findSlot(width, height);
         }
      }

      private static void removeUnoccupiedSubSlots(SignAtlasFramebuffer.Slot slot) {
         if (slot != null) {
            removeUnoccupiedSubSlots(slot.parentSlot);
            boolean subSlot1Unoccupied = slot.subSlot1 != null && !hasOccupiedSlot(slot.subSlot1);
            boolean subSlot2Unoccupied = slot.subSlot2 != null && !hasOccupiedSlot(slot.subSlot2);
            if (subSlot1Unoccupied && subSlot2Unoccupied) {
               slot.subSlot1 = null;
               slot.subSlot2 = null;
            }
         }
      }

      private static boolean hasOccupiedSlot(SignAtlasFramebuffer.Slot slot) {
         if (slot == null) {
            return false;
         } else {
            return slot.occupied ? true : hasOccupiedSlot(slot.subSlot1) || hasOccupiedSlot(slot.subSlot2);
         }
      }
   }
}
