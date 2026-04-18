package com.talhanation.smallships.client.model;

import com.talhanation.smallships.world.entity.ship.BriggEntity;
import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;

public class BriggModel extends ShipModel<BriggEntity> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "brigg_model"), "main");
   private final class_630 root;
   private final class_630 brigg;
   private final class_630 chest1;
   private final class_630 chest2;
   private final class_630 chest3;
   private final class_630 chest4;
   private final class_630 steer;

   public BriggModel(class_630 modelPart) {
      this.root = modelPart;
      this.brigg = this.root.method_32086("ModelBrigg");
      this.chest1 = this.brigg.method_32086("chest_1");
      this.chest2 = this.brigg.method_32086("chest_2");
      this.chest3 = this.brigg.method_32086("chest_3");
      this.chest4 = this.brigg.method_32086("chest_4");
      this.steer = this.brigg.method_32086("steer");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 ModelBrigg = partdefinition.method_32117(
         "ModelBrigg", class_5606.method_32108(), class_5603.method_32091(0.0F, 24.0F, -3.0F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 bottom_brigg = ModelBrigg.method_32117(
         "bottom_brigg",
         class_5606.method_32108()
            .method_32101(0, 12)
            .method_32098(-3.0F, -112.0F, -20.0F, 25.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-3.0F, -114.0F, 23.0F, 25.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-28.0F, -112.0F, -20.0F, 25.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-28.0F, -114.0F, 23.0F, 25.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-31.0F, -82.0F, -20.0F, 28.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-3.0F, -82.0F, -20.0F, 28.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-40.0F, -82.0F, -20.0F, 9.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-3.0F, -82.0F, 22.0F, 28.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(25.0F, -82.0F, 22.0F, 9.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-31.0F, -82.0F, 22.0F, 28.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-40.0F, -82.0F, 22.0F, 9.0F, 2.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r1 = bottom_brigg.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(25, 0)
            .method_32098(-0.5F, -35.0F, 38.9F, 2.0F, 12.0F, 2.0F, new class_5605(0.0F))
            .method_32101(25, 0)
            .method_32098(-0.5F, -23.0F, 38.9F, 2.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(25, 0)
            .method_32098(-0.5F, -6.0F, 38.9F, 2.0F, 13.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.5F, -80.669F, 28.9006F, -1.4399F, 0.0F, 0.0F)
      );
      class_5610 cube_r2 = bottom_brigg.method_32117(
         "cube_r2",
         class_5606.method_32108()
            .method_32101(25, 0)
            .method_32098(-0.5F, -41.0F, 5.0F, 2.0F, 8.0F, 2.0F, new class_5605(0.0F))
            .method_32101(25, 0)
            .method_32098(-0.5F, -33.0F, 5.0F, 2.0F, 18.0F, 2.0F, new class_5605(0.0F))
            .method_32101(25, 0)
            .method_32098(-0.5F, -15.0F, 5.0F, 2.0F, 19.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.5F, -80.669F, 28.9006F, -1.1345F, 0.0F, 0.0F)
      );
      class_5610 cube_r3 = bottom_brigg.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(0, 12)
            .method_32098(-18.0F, -4.5F, -68.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-18.0F, -4.5F, -122.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-18.0F, -4.5F, -95.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(-18.0F, -4.5F, -41.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(25.0F, -4.5F, -122.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(25.0F, -4.5F, -95.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(25.0F, -4.5F, -68.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 12)
            .method_32098(25.0F, -4.5F, -41.0F, 3.0F, 3.0F, 27.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(28.0F, -1.0F, -14.0F, 18.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -6.0F, -14.0F, 14.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -19.0F, -14.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -19.0F, -14.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(28.0F, -5.0F, -14.0F, 18.0F, 4.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(28.0F, -20.0F, -14.0F, 18.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -3.0F, -14.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 3)
            .method_32098(-28.0F, -22.0F, -14.0F, 16.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 3)
            .method_32098(-28.0F, 13.0F, -14.0F, 16.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 3)
            .method_32098(16.0F, 13.0F, -14.0F, 12.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(16.0F, -22.0F, -14.0F, 12.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-12.0F, -25.0F, -14.0F, 28.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-12.0F, 13.0F, -14.0F, 28.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -3.0F, -14.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, 0.0F, -14.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -19.0F, -14.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-39.0F, -15.0F, -11.0F, 24.0F, 12.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-39.0F, -3.0F, -11.0F, 24.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-15.0F, -3.0F, -11.0F, 24.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-15.0F, -16.0F, -11.0F, 24.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(9.0F, -16.0F, -11.0F, 22.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(9.0F, -3.0F, -11.0F, 22.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(31.0F, -3.0F, -11.0F, 14.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(31.0F, -16.0F, -11.0F, 14.0F, 13.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r4 = bottom_brigg.method_32117(
         "cube_r4",
         class_5606.method_32108().method_32101(4, 0).method_32098(-7.0F, -3.0F, -5.5F, 16.0F, 6.0F, 11.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.0F, -3.0F, -29.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r5 = bottom_brigg.method_32117(
         "cube_r5",
         class_5606.method_32108()
            .method_32101(0, 2)
            .method_32096()
            .method_32098(3.0F, 0.5F, -11.5F, 16.0F, 6.0F, 9.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(-3.5F, -13.5F, -50.0F, 3.1416F, -0.6545F, 1.5708F)
      );
      class_5610 cube_r6 = bottom_brigg.method_32117(
         "cube_r6",
         class_5606.method_32108()
            .method_32101(3, 0)
            .method_32098(42.0F, -15.0F, -2.5F, 6.0F, 15.0F, 11.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(22.0F, -6.0F, -2.5F, 20.0F, 6.0F, 11.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -6.0F, -2.5F, 10.0F, 6.0F, 11.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(10.0F, -6.0F, -2.5F, 12.0F, 6.0F, 11.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-20.0F, -6.0F, -2.5F, 20.0F, 6.0F, 11.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 chest_1 = ModelBrigg.method_32117("chest_1", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 cube_r7 = chest_1.method_32117(
         "cube_r7",
         class_5606.method_32108()
            .method_32101(31, 56)
            .method_32098(33.0F, -19.0F, 9.0F, 4.0F, 5.0F, 3.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(33.0F, -19.0F, 12.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(41.0F, -27.0F, 10.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(39.0F, -22.0F, -5.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(38.0F, -19.0F, -10.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r8 = chest_1.method_32117(
         "cube_r8",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-9.0F, -18.0F, 41.0F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r9 = chest_1.method_32117(
         "cube_r9",
         class_5606.method_32108().method_32101(64, 29).method_32098(-3.0F, -1.5F, -3.75F, 6.0F, 5.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.0F, -20.5F, 42.0F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r10 = chest_1.method_32117(
         "cube_r10",
         class_5606.method_32108().method_32101(50, 47).method_32098(38.0F, -17.0F, -9.25F, 7.0F, 3.0F, 13.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 chest_3 = ModelBrigg.method_32117(
         "chest_3", class_5606.method_32108(), class_5603.method_32091(-13.1667F, -18.9167F, -31.4167F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r11 = chest_3.method_32117(
         "cube_r11",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(33.0F, -19.0F, 5.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(38.0F, -27.0F, 9.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(34.0F, -19.0F, -1.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(6.1667F, 18.9167F, -40.5833F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r12 = chest_3.method_32117(
         "cube_r12",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-2.8333F, 0.9167F, 0.4167F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r13 = chest_3.method_32117(
         "cube_r13",
         class_5606.method_32108().method_32101(64, 29).method_32098(-3.0F, 1.5F, -3.75F, 6.0F, 5.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.1667F, -1.5833F, 1.4167F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 chest_4 = ModelBrigg.method_32117(
         "chest_4", class_5606.method_32108(), class_5603.method_32091(7.8333F, -18.9167F, -31.4167F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r14 = chest_4.method_32117(
         "cube_r14",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(33.0F, -19.0F, 8.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(38.0F, -27.0F, 9.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(34.0F, -19.0F, 2.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(6.1667F, 18.9167F, -40.5833F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r15 = chest_4.method_32117(
         "cube_r15",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-2.8333F, 0.9167F, 0.4167F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r16 = chest_4.method_32117(
         "cube_r16",
         class_5606.method_32108().method_32101(64, 29).method_32098(-3.0F, 1.5F, -3.75F, 6.0F, 5.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.1667F, -1.5833F, 1.4167F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 sides_brigg = ModelBrigg.method_32117(
         "sides_brigg",
         class_5606.method_32108().method_32101(0, 12).method_32098(25.0F, -82.0F, -20.0F, 9.0F, 2.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r17 = sides_brigg.method_32117(
         "cube_r17",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(16.0F, -25.0F, -20.0F, 12.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(37.0F, 13.0F, -22.0F, 9.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-12.0F, -28.0F, -20.0F, 12.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-12.0F, 19.0F, -20.0F, 12.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, 19.0F, -20.0F, 16.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 16.0F, -20.0F, 16.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(28.0F, -22.0F, -22.0F, 9.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(28.0F, 13.0F, -22.0F, 9.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(37.0F, -22.0F, -22.0F, 9.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(16.0F, 16.0F, -20.0F, 12.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -25.0F, -20.0F, 16.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -28.0F, -20.0F, 16.0F, 3.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, 13.0F, -22.0F, 14.0F, 3.0F, 8.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-45.0F, -2.0F, -24.0F, 3.0F, 15.0F, 10.0F, new class_5605(0.0F))
            .method_32101(10, 0)
            .method_32098(-45.0F, -19.0F, -24.0F, 3.0F, 15.0F, 10.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -22.0F, -22.0F, 14.0F, 3.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r18 = sides_brigg.method_32117(
         "cube_r18",
         class_5606.method_32108().method_32101(0, 0).method_32098(-12.5F, -2.0F, -3.0F, 25.0F, 4.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.0F, -12.5335F, 47.4516F, 0.0F, 0.1222F, 1.5708F)
      );
      class_5610 cube_r19 = sides_brigg.method_32117(
         "cube_r19",
         class_5606.method_32108().method_32101(0, 0).method_32098(-23.5F, -3.0F, -3.0F, 14.0F, 4.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -1.6155F, 46.111F, 0.0F, 0.1222F, 1.5708F)
      );
      class_5610 cube_r20 = sides_brigg.method_32117(
         "cube_r20",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(-16.0F, -10.0F, 3.0F, 22.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-8.0F, -6.5F, -3.0F, 14.0F, 4.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -24.5F, -3.0F, 12.0F, 10.0F, 6.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -2.5F, -3.0F, 12.0F, 10.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-11.5F, -17.0F, 48.0F, 0.0F, 0.1222F, 1.5708F)
      );
      class_5610 cube_r21 = sides_brigg.method_32117(
         "cube_r21",
         class_5606.method_32108()
            .method_32101(0, 4)
            .method_32098(-44.0F, -1.0F, -13.5F, 16.0F, 3.0F, 4.0F, new class_5605(0.0F))
            .method_32101(0, 7)
            .method_32098(13.0F, -1.0F, -13.5F, 15.0F, 3.0F, 4.0F, new class_5605(0.0F))
            .method_32101(0, 7)
            .method_32098(-2.0F, -1.0F, -13.5F, 15.0F, 3.0F, 4.0F, new class_5605(0.0F))
            .method_32101(0, 4)
            .method_32098(-28.0F, -1.0F, -13.5F, 26.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.5F, -13.5F, -46.0F, 3.1416F, -1.0996F, 1.5708F)
      );
      class_5610 cube_r22 = sides_brigg.method_32117(
         "cube_r22",
         class_5606.method_32108()
            .method_32101(3, 2)
            .method_32096()
            .method_32098(-13.7F, -2.5F, -17.0F, 7.0F, 6.0F, 7.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(-3.5F, -13.5F, -62.0F, 3.1416F, -0.0436F, 1.5708F)
      );
      class_5610 cube_r23 = sides_brigg.method_32117(
         "cube_r23",
         class_5606.method_32108()
            .method_32101(0, 2)
            .method_32096()
            .method_32098(0.0F, -4.5F, -21.0F, 13.0F, 5.0F, 9.0F, new class_5605(0.0F))
            .method_32106(false)
            .method_32101(0, 2)
            .method_32096()
            .method_32098(0.0F, 0.5F, -21.0F, 13.0F, 5.0F, 9.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(-3.5F, -13.5F, -62.0F, 3.1416F, -0.6545F, 1.5708F)
      );
      class_5610 cube_r24 = sides_brigg.method_32117(
         "cube_r24",
         class_5606.method_32108()
            .method_32101(0, 2)
            .method_32096()
            .method_32098(3.0F, -5.5F, -11.5F, 16.0F, 6.0F, 9.0F, new class_5605(0.0F))
            .method_32106(false),
         class_5603.method_32091(-3.5F, -13.5F, -50.0F, 3.1416F, -0.6545F, 1.5708F)
      );
      class_5610 BannerStick = ModelBrigg.method_32117("BannerStick", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 cube_r25 = BannerStick.method_32117(
         "cube_r25",
         class_5606.method_32108().method_32101(6, 0).method_32098(26.0F, -3.5F, -137.0F, 1.0F, 1.0F, 15.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, -1.5708F, -1.5708F, 0.0F)
      );
      class_5610 chest_2 = ModelBrigg.method_32117("chest_2", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 cube_r26 = chest_2.method_32117(
         "cube_r26",
         class_5606.method_32108()
            .method_32101(96, 38)
            .method_32098(-17.0F, -4.0F, 3.5F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F))
            .method_32101(96, 38)
            .method_32098(-8.5F, -4.0F, 3.5F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-9.0F, -18.0F, 41.0F, 0.0F, 3.1416F, 0.0F)
      );
      class_5610 cube_r27 = chest_2.method_32117(
         "cube_r27",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(30.0F, -25.0F, 2.0F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(32.0F, -25.0F, -5.0F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 steer = ModelBrigg.method_32117("steer", class_5606.method_32108(), class_5603.method_32090(-3.0F, -1.8071F, 48.6533F));
      class_5610 steer_r1 = steer.method_32117(
         "steer_r1",
         class_5606.method_32108().method_32101(0, 0).method_32098(-9.0F, -0.5F, 0.5F, 18.0F, 1.0F, 9.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, 0.1222F, 1.5708F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(BriggEntity briggEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.chest1.field_3665 = briggEntity.getInvFillState() >= 15;
      this.chest2.field_3665 = briggEntity.getInvFillState() >= 30;
      this.chest3.field_3665 = briggEntity.getInvFillState() >= 60;
      this.chest4.field_3665 = briggEntity.getInvFillState() >= 90;
      this.steer.field_3675 = -briggEntity.getRotSpeed() * 0.25F;
   }

   @NotNull
   public class_630 method_32008() {
      return this.root;
   }
}
