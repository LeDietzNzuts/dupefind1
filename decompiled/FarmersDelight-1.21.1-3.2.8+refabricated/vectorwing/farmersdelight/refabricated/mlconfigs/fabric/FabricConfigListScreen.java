package vectorwing.farmersdelight.refabricated.mlconfigs.fabric;

import java.util.List;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.class_1799;
import net.minecraft.class_2558;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_4185;
import net.minecraft.class_4265;
import net.minecraft.class_437;
import net.minecraft.class_5244;
import net.minecraft.class_6379;
import net.minecraft.class_2558.class_2559;
import net.minecraft.class_4265.class_4266;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.refabricated.mlconfigs.ModConfigHolder;

public class FabricConfigListScreen extends class_437 {
   protected final class_437 parent;
   protected final ModConfigHolder[] configs;
   @Nullable
   protected final class_2960 background;
   private final class_1799 mainIcon;
   private final String modId;
   private final String modURL;
   protected FabricConfigListScreen.ConfigList list;

   public FabricConfigListScreen(
      String modId, class_1799 mainIcon, class_2561 displayName, @Nullable class_2960 background, class_437 parent, ModConfigHolder... specs
   ) {
      super(displayName);
      this.parent = parent;
      this.configs = specs;
      this.background = background;
      this.mainIcon = mainIcon;
      this.modId = modId;
      this.modURL = (String)((ModContainer)FabricLoader.getInstance().getModContainer(modId).get()).getMetadata().getContact().get("homepage").orElse(null);
   }

   protected void method_25426() {
      this.list = new FabricConfigListScreen.ConfigList(this.field_22787, this.field_22789, this.field_22790, 32, 40, this.configs);
      this.method_37063(this.list);
      this.addExtraButtons();
   }

   protected void addExtraButtons() {
      this.method_37063(
         class_4185.method_46430(class_5244.field_24334, button -> this.field_22787.method_1507(this.parent))
            .method_46434(this.field_22789 / 2 - 155 + 160, this.field_22790 - 29, 150, 20)
            .method_46431()
      );
   }

   public void method_25432() {
   }

   public void method_25394(class_332 graphics, int mouseX, int mouseY, float partialTick) {
      super.method_25394(graphics, mouseX, mouseY, partialTick);
      graphics.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 15, 16777215);
      if (this.modURL != null && this.isMouseWithin(this.field_22789 / 2 - 90, 8, 180, 18, mouseX, mouseY)) {
         graphics.method_51447(
            this.field_22793,
            this.field_22793.method_1728(class_2561.method_43469("gui.farmersdelight.open_mod_page", new Object[]{this.modId}), 200),
            mouseX,
            mouseY
         );
      }

      int titleWidth = this.field_22793.method_27525(this.field_22785) + 35;
      graphics.method_51445(this.mainIcon, this.field_22789 / 2 + titleWidth / 2 - 17, 10);
      graphics.method_51445(this.mainIcon, this.field_22789 / 2 - titleWidth / 2, 10);
   }

   private boolean isMouseWithin(int x, int y, int width, int height, int mouseX, int mouseY) {
      return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
   }

   public boolean method_25402(double mouseX, double mouseY, int button) {
      if (this.modURL != null && this.isMouseWithin(this.field_22789 / 2 - 90, 8, 180, 18, (int)mouseX, (int)mouseY)) {
         class_2583 style = class_2583.field_24360.method_10958(new class_2558(class_2559.field_11749, this.modURL));
         this.method_25430(style);
         return true;
      } else {
         return super.method_25402(mouseX, mouseY, button);
      }
   }

   public void method_25419() {
      this.field_22787.method_1507(this.parent);
   }

   protected class ConfigButton extends class_4266<FabricConfigListScreen.ConfigButton> {
      private final List<class_339> children;

      private ConfigButton(class_339 widget) {
         this.children = List.of(widget);
      }

      protected ConfigButton(ModConfigHolder spec, int width, int buttonWidth) {
         this(
            class_4185.method_46430(
                  class_2561.method_43470(spec.getFileName()),
                  b -> class_310.method_1551().method_1507(spec.makeScreen(FabricConfigListScreen.this, FabricConfigListScreen.this.background))
               )
               .method_46434(width / 2 - buttonWidth / 2, 0, buttonWidth, 20)
               .method_46431()
         );
      }

      public void method_25343(
         class_332 graphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean isMouseOver, float partialTick
      ) {
         this.children.forEach(button -> {
            button.method_46419(top);
            button.method_25394(graphics, mouseX, mouseY, partialTick);
         });
      }

      public List<? extends class_364> method_25396() {
         return this.children;
      }

      public List<? extends class_6379> method_37025() {
         return this.children;
      }
   }

   protected class ConfigList extends class_4265<FabricConfigListScreen.ConfigButton> {
      public ConfigList(class_310 minecraft, int width, int height, int y0, int itemHeight, ModConfigHolder... specs) {
         super(minecraft, width, height, y0, itemHeight);
         this.field_22744 = true;

         for (ModConfigHolder s : specs) {
            this.method_25321(FabricConfigListScreen.this.new ConfigButton(s, this.field_22758, this.method_25322()));
         }
      }

      public int method_25322() {
         return 260;
      }

      protected int method_25329() {
         return super.method_25329() + 32;
      }
   }
}
