package vectorwing.farmersdelight.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import java.util.Random;
import net.minecraft.class_1294;
import net.minecraft.class_1324;
import net.minecraft.class_1657;
import net.minecraft.class_1702;
import net.minecraft.class_1928;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_5134;
import net.minecraft.class_9779;
import net.minecraft.class_9080.class_9081;
import org.jetbrains.annotations.NotNull;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class HUDOverlays {
   public static int healthIconsOffset = 39;
   public static int foodIconsOffset = 39;
   private static final class_2960 MOD_ICONS_TEXTURE = class_2960.method_60655("farmersdelight", "textures/gui/fd_icons.png");

   @Deprecated
   public static void register() {
   }

   public static void drawNourishmentOverlay(class_1702 foodData, class_310 minecraft, class_332 graphics, int right, int top, boolean naturalHealing) {
      float saturation = foodData.method_7589();
      int foodLevel = foodData.method_7586();
      int ticks = minecraft.field_1705.method_1738();
      Random rand = new Random();
      rand.setSeed(ticks * 312871);
      RenderSystem.enableBlend();

      for (int j = 0; j < 10; j++) {
         int x = right - j * 8 - 9;
         int y = top;
         if (saturation <= 0.0F && ticks % (foodLevel * 3 + 1) == 0) {
            y = top + (rand.nextInt(3) - 1);
         }

         graphics.method_25302(MOD_ICONS_TEXTURE, x, y, 0, 0, 9, 9);
         float effectiveHungerOfBar = foodData.method_7586() / 2.0F - j;
         int naturalHealingOffset = naturalHealing ? 18 : 0;
         if (effectiveHungerOfBar >= 1.0F) {
            graphics.method_25302(MOD_ICONS_TEXTURE, x, y, 18 + naturalHealingOffset, 0, 9, 9);
         } else if (effectiveHungerOfBar >= 0.5) {
            graphics.method_25302(MOD_ICONS_TEXTURE, x, y, 9 + naturalHealingOffset, 0, 9, 9);
         }
      }

      RenderSystem.disableBlend();
   }

   public static void drawComfortOverlay(class_1657 player, class_310 minecraft, class_332 graphics, int left, int top) {
      int ticks = minecraft.field_1705.method_1738();
      Random rand = new Random();
      rand.setSeed(ticks * 312871);
      int health = class_3532.method_15386(player.method_6032());
      float absorb = class_3532.method_15386(player.method_6067());
      class_1324 attrMaxHealth = player.method_5996(class_5134.field_23716);
      float healthMax = (float)attrMaxHealth.method_6194();
      int regen = -1;
      if (player.method_6059(class_1294.field_5924)) {
         regen = ticks % 25;
      }

      int healthRows = class_3532.method_15386((healthMax + absorb) / 2.0F / 10.0F);
      int rowHeight = Math.max(10 - (healthRows - 2), 3);
      int comfortSheen = ticks % 50;
      int comfortHeartFrame = comfortSheen % 2;
      int[] textureWidth = new int[]{5, 9};
      RenderSystem.enableBlend();
      int healthMaxSingleRow = class_3532.method_15386(Math.min(healthMax, 20.0F) / 2.0F);
      int leftHeightOffset = (healthRows - 1) * rowHeight;

      for (int i = 0; i < healthMaxSingleRow; i++) {
         int column = i % 10;
         int x = left + column * 8;
         int y = top + leftHeightOffset;
         if (health <= 4) {
            y += rand.nextInt(2);
         }

         if (i == regen) {
            y -= 2;
         }

         if (column == comfortSheen / 2) {
            graphics.method_25302(MOD_ICONS_TEXTURE, x, y, 0, 9, textureWidth[comfortHeartFrame], 9);
         }

         if (column == comfortSheen / 2 - 1 && comfortHeartFrame == 0) {
            graphics.method_25302(MOD_ICONS_TEXTURE, x + 5, y, 5, 9, 4, 9);
         }
      }

      RenderSystem.disableBlend();
   }

   public abstract static class BaseOverlay implements class_9081 {
      public abstract void render(class_310 var1, class_1657 var2, class_332 var3, int var4, int var5, int var6, int var7);

      public final void render(@NotNull class_332 guiGraphics, @NotNull class_9779 deltaTracker) {
         class_310 minecraft = class_310.method_1551();
         if (minecraft.field_1724 != null && this.shouldRenderOverlay(minecraft, minecraft.field_1724, guiGraphics, minecraft.field_1705.method_1738())) {
            int top = guiGraphics.method_51443();
            int left = guiGraphics.method_51421() / 2 - 91;
            int right = guiGraphics.method_51421() / 2 + 91;
            this.render(minecraft, minecraft.field_1724, guiGraphics, left, right, top, minecraft.field_1705.method_1738());
         }
      }

      public boolean shouldRenderOverlay(class_310 minecraft, class_1657 player, class_332 guiGraphics, int guiTicks) {
         return !minecraft.field_1690.field_1842 && minecraft.field_1761 != null && minecraft.field_1761.method_2908();
      }
   }

   public static class ComfortOverlay extends HUDOverlays.BaseOverlay {
      public static final class_2960 ID = class_2960.method_60655("farmersdelight", "comfort");
      public static final HUDOverlays.ComfortOverlay INSTANCE = new HUDOverlays.ComfortOverlay();

      @Override
      public void render(class_310 minecraft, class_1657 player, class_332 guiGraphics, int left, int right, int top, int guiTicks) {
         class_1702 stats = player.method_7344();
         boolean isPlayerEligibleForComfort = stats.method_7589() == 0.0F && player.method_7317() && !player.method_6059(class_1294.field_5924);
         if (player.method_6112(ModEffects.COMFORT) != null && isPlayerEligibleForComfort) {
            HUDOverlays.drawComfortOverlay(player, minecraft, guiGraphics, left, top - HUDOverlays.healthIconsOffset);
         }
      }

      @Override
      public boolean shouldRenderOverlay(class_310 mc, class_1657 player, class_332 guiGraphics, int guiTicks) {
         return !super.shouldRenderOverlay(mc, player, guiGraphics, guiTicks) ? false : Configuration.COMFORT_HEALTH_OVERLAY.get();
      }
   }

   public static class NourishmentOverlay extends HUDOverlays.BaseOverlay {
      public static final class_2960 ID = class_2960.method_60655("farmersdelight", "nourishment");
      public static final HUDOverlays.NourishmentOverlay INSTANCE = new HUDOverlays.NourishmentOverlay();

      @Override
      public void render(class_310 minecraft, class_1657 player, class_332 guiGraphics, int left, int right, int top, int guiTicks) {
         class_1702 stats = player.method_7344();
         boolean isPlayerHealingWithSaturation = player.method_37908().method_8450().method_8355(class_1928.field_19395)
            && player.method_7317()
            && stats.method_7586() >= 18;
         if (player.method_6112(ModEffects.NOURISHMENT) != null) {
            HUDOverlays.drawNourishmentOverlay(stats, minecraft, guiGraphics, right, top - HUDOverlays.foodIconsOffset, isPlayerHealingWithSaturation);
         }
      }

      @Override
      public boolean shouldRenderOverlay(class_310 mc, class_1657 player, class_332 guiGraphics, int guiTicks) {
         return !super.shouldRenderOverlay(mc, player, guiGraphics, guiTicks) ? false : Configuration.NOURISHED_HUNGER_OVERLAY.get();
      }
   }
}
