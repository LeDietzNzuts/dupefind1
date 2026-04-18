package com.talhanation.smallships.client.model;

import com.talhanation.smallships.world.entity.ship.GalleyEntity;
import com.talhanation.smallships.world.entity.ship.abilities.Paddleable;
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

public class GalleyModel extends ShipModel<GalleyEntity> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "galley_model"), "main");
   private final class_630 root;
   private final class_630 galley;
   private final class_630 chest1;
   private final class_630 chest2;
   private final class_630 chest3;
   private final class_630 chest4;
   private final class_630 steer;
   private final class_630 row_L_1;
   private final class_630 row_L_2;
   private final class_630 row_L_3;
   private final class_630 row_L_4;
   private final class_630 row_R_1;
   private final class_630 row_R_2;
   private final class_630 row_R_3;
   private final class_630 row_R_4;

   public GalleyModel(class_630 modelPart) {
      this.root = modelPart;
      this.galley = this.root.method_32086("Galley");
      this.chest1 = this.galley.method_32086("chest_1");
      this.chest2 = this.galley.method_32086("chest_2");
      this.chest3 = this.galley.method_32086("chest_3");
      this.chest4 = this.galley.method_32086("chest_4");
      this.steer = this.galley.method_32086("steer");
      this.row_L_1 = this.galley.method_32086("row_L_1");
      this.row_L_2 = this.galley.method_32086("row_L_2");
      this.row_L_3 = this.galley.method_32086("row_L_3");
      this.row_L_4 = this.galley.method_32086("row_L_4");
      this.row_R_1 = this.galley.method_32086("row_R_1");
      this.row_R_2 = this.galley.method_32086("row_R_2");
      this.row_R_3 = this.galley.method_32086("row_R_3");
      this.row_R_4 = this.galley.method_32086("row_R_4");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 Galley = partdefinition.method_32117("Galley", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 deck = Galley.method_32117("deck", class_5606.method_32108(), class_5603.method_32090(14.0F, 0.0F, 0.0F));
      class_5610 cube_r1 = deck.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(28, 0)
            .method_32098(-42.0F, -13.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(-42.0F, 0.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(33, 0)
            .method_32098(-53.0F, 0.0F, 4.0F, 8.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(33, 0)
            .method_32098(-53.0F, -6.0F, 4.0F, 8.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(33, 0)
            .method_32098(-64.0F, -3.5F, 4.0F, 11.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(15, 0)
            .method_32098(38.0F, -13.0F, 2.0F, 13.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(15, 0)
            .method_32098(38.0F, 0.0F, 2.0F, 13.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(18, 0)
            .method_32098(28.0F, -16.0F, 2.0F, 10.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(18, 0)
            .method_32098(28.0F, 0.0F, 2.0F, 10.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, 0.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -16.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 0.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -16.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-14.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 bottom = Galley.method_32117("bottom", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 cube_r2 = bottom.method_32117(
         "cube_r2",
         class_5606.method_32108().method_32101(8, 2).method_32098(-42.0F, 3.0F, -4.0F, 14.0F, 7.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r5_r1 = cube_r2.method_32117(
         "cube_r5_r1",
         class_5606.method_32108().method_32101(4, 1).method_32098(-11.6F, 0.8F, -3.0F, 10.0F, 7.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-42.0F, 5.5F, 0.0F, 0.0F, 0.0F, 1.5708F)
      );
      class_5610 cube_r4_r1 = cube_r2.method_32117(
         "cube_r4_r1",
         class_5606.method_32108().method_32101(4, 1).method_32098(-7.2F, -3.5F, -3.5F, 10.0F, 7.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(-42.0F, 5.5F, 0.0F, 0.0F, 0.0F, 0.6545F)
      );
      class_5610 cube_r3 = bottom.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(32.0F, -4.0F, -10.0F, 15.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(16.0F, -4.0F, -10.0F, 16.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 2.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -12.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -12.0F, -3.0F, 14.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, 2.0F, -3.0F, 14.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -12.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(34.0F, -12.0F, -3.0F, 17.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, 2.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(34.0F, 2.0F, -3.0F, 17.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(18.0F, 2.0F, -3.0F, 16.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(18.0F, -12.0F, -3.0F, 16.0F, 10.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 sides = Galley.method_32117(
         "sides",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(-43.0F, -11.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-43.0F, -11.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -11.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -17.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(13.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-2.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-28.0F, -11.0F, -19.0F, 11.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-17.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -11.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, 16.0F, 9.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, -19.0F, 9.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -17.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(13.0F, -11.0F, 16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-2.0F, -11.0F, 16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-17.0F, -11.0F, 16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-28.0F, -11.0F, 16.0F, 11.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r4 = sides.method_32117("cube_r4", class_5606.method_32108(), class_5603.method_32091(35.0F, -8.0F, -9.5F, 0.0F, 1.5708F, 0.0F));
      class_5610 cube_r5 = sides.method_32117(
         "cube_r5",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(3.5001F, -3.0F, 21.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.4999F, -3.0F, 21.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.5001F, 3.0F, 21.5F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.4999F, 3.0F, 21.5F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-8.4999F, 6.0F, 21.5F, 11.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.5001F, 6.0F, 21.5F, 12.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.5002F, -3.0F, -74.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.5002F, -9.0F, -74.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.4998F, -3.0F, -74.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.4998F, -9.0F, -74.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-20.5F, -8.0F, -3.5F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 mast_1 = Galley.method_32117(
         "mast_1",
         class_5606.method_32108()
            .method_32101(8, 0)
            .method_32098(-13.0F, -15.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-13.0F, -30.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-13.0F, -45.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-13.0F, -60.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-13.0F, -75.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32090(14.0F, -5.0F, -1.0F)
      );
      class_5610 mast_2 = mast_1.method_32117(
         "mast_2",
         class_5606.method_32108()
            .method_32101(8, 0)
            .method_32098(1.2957F, -39.0539F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, -52.0539F, -0.9F, 2.0F, 13.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, -24.0539F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, -9.0539F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, 5.9461F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, 20.9461F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(1.2957F, 35.9461F, -0.9F, 2.0F, 15.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(-11.0F, -65.5F, 3.4F, 0.0F, 0.0F, 0.7418F)
      );
      class_5610 cube_r6 = mast_1.method_32117(
         "cube_r6",
         class_5606.method_32108()
            .method_32101(8, 0)
            .method_32098(6.6905F, -13.6357F, -2.5F, 4.0F, 24.0F, 5.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(7.4905F, -23.3357F, -1.0F, 2.0F, 10.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(29.0F, 0.5F, 1.0F, 0.0F, 0.0F, 0.5672F)
      );
      class_5610 BannerStick = mast_1.method_32117(
         "BannerStick",
         class_5606.method_32108().method_32101(8, 0).method_32098(2.0F, -94.0F, -0.5F, 1.0F, 15.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(-14.0F, 4.0F, 1.0F)
      );
      class_5610 chest_1 = Galley.method_32117(
         "chest_1",
         class_5606.method_32108().method_32101(96, 38).method_32098(33.0F, -13.0F, -13.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32090(1.0F, 0.0F, 1.0F)
      );
      class_5610 cube_r7 = chest_1.method_32117(
         "cube_r7",
         class_5606.method_32108().method_32101(30, 55).method_32098(-2.75F, -4.25F, 6.75F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(27.75F, -11.75F, -8.25F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r8 = chest_1.method_32117(
         "cube_r8",
         class_5606.method_32108().method_32101(30, 55).method_32098(15.0001F, -24.5001F, -10.0001F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(19.0F, -15.5F, -18.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r9 = chest_1.method_32117(
         "cube_r9",
         class_5606.method_32108().method_32101(30, 55).method_32098(72.1001F, -19.0F, -17.4001F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.0F, 9.0F, -75.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r10 = chest_1.method_32117(
         "cube_r10",
         class_5606.method_32108().method_32101(96, 38).method_32098(-28.0F, -4.0F, 3.5F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(22.0F, -9.0F, -1.0F, 0.0F, 3.1416F, 0.0F)
      );
      class_5610 chest_4 = Galley.method_32117("chest_4", class_5606.method_32108(), class_5603.method_32090(42.0F, -11.5F, 9.0F));
      class_5610 cube_r11 = chest_4.method_32117(
         "cube_r11",
         class_5606.method_32108().method_32101(64, 29).method_32098(-3.0F, -1.5F, -4.0F, 6.0F, 5.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r12 = chest_4.method_32117(
         "cube_r12",
         class_5606.method_32108().method_32101(30, 55).method_32098(39.0F, -22.0F, -5.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 20.5F, -42.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r13 = chest_4.method_32117(
         "cube_r13",
         class_5606.method_32108().method_32101(50, 47).method_32098(38.0F, -17.0F, -9.25F, 7.0F, 3.0F, 13.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 20.5F, -42.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 chest_2 = Galley.method_32117("chest_2", class_5606.method_32108(), class_5603.method_32090(-55.0F, -15.5F, 30.0F));
      class_5610 cube_r14 = chest_2.method_32117(
         "cube_r14",
         class_5606.method_32108().method_32101(30, 55).method_32098(-21.0001F, -24.4999F, -9.9999F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r15 = chest_2.method_32117(
         "cube_r15",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(30.9999F, -19.0F, -14.9999F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(32.0F, -25.0F, -8.0F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(12.0F, 24.5F, -57.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r16 = chest_2.method_32117(
         "cube_r16",
         class_5606.method_32108().method_32101(96, 38).method_32098(-19.0F, -4.0F, 3.5F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 6.5F, -16.0F, 0.0F, 3.1416F, 0.0F)
      );
      class_5610 chest_3 = Galley.method_32117("chest_3", class_5606.method_32108(), class_5603.method_32090(37.3333F, -8.0F, -11.1667F));
      class_5610 cube_r17 = chest_3.method_32117(
         "cube_r17",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(33.0F, -19.0F, 53.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(31, 56)
            .method_32098(33.0F, -19.0F, 49.0F, 4.0F, 5.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-20.3333F, 17.0F, -33.8333F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r18 = chest_3.method_32117(
         "cube_r18",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, -49.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-29.3333F, -1.0F, 7.1667F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 steer = Galley.method_32117(
         "steer",
         class_5606.method_32108()
            .method_32101(4, 1)
            .method_32098(13.0F, 2.75F, -1.0F, 4.0F, 8.0F, 2.0F, new class_5605(0.0F))
            .method_32101(4, 1)
            .method_32098(9.0F, -0.25F, -1.0F, 4.0F, 11.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(38.0F, 3.25F, 0.0F)
      );
      class_5610 row_L_4 = Galley.method_32117(
         "row_L_4",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-12.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r19 = row_L_4.method_32117(
         "cube_r19",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_3 = Galley.method_32117(
         "row_L_3",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(1.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r20 = row_L_3.method_32117(
         "cube_r20",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_2 = Galley.method_32117(
         "row_L_2",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(16.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r21 = row_L_2.method_32117(
         "cube_r21",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 19.0F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, 2.0F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_1 = Galley.method_32117(
         "row_L_1",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r22 = row_L_1.method_32117(
         "cube_r22",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_1 = Galley.method_32117(
         "row_R_1",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.2059F, 11.1704F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.5F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r23 = row_R_1.method_32117(
         "cube_r23",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(2.0F, 13.6704F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(2.0F, -3.3296F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-2.5F, 8.0F, -16.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_2 = Galley.method_32117(
         "row_R_2",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.2059F, 11.1704F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(16.5F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r24 = row_R_2.method_32117(
         "cube_r24",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 13.6704F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, -3.3296F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 8.0F, -16.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_3 = Galley.method_32117(
         "row_R_3",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.2059F, 11.1704F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(1.5F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r25 = row_R_3.method_32117(
         "cube_r25",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 13.6704F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, -3.3296F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 8.0F, -16.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_4 = Galley.method_32117(
         "row_R_4",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.2059F, 11.1704F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-12.5F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r26 = row_R_4.method_32117(
         "cube_r26",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(4.0F, 13.6704F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(4.0F, -3.3296F, 6.7059F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-4.5F, 8.0F, -16.0F, 1.5708F, 0.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(GalleyEntity galleyEntity, float f, float g, float h, float i, float j) {
      this.chest1.field_3665 = galleyEntity.getInvFillState() >= 15;
      this.chest2.field_3665 = galleyEntity.getInvFillState() >= 30;
      this.chest3.field_3665 = galleyEntity.getInvFillState() >= 60;
      this.chest4.field_3665 = galleyEntity.getInvFillState() >= 90;
      this.steer.field_3675 = -galleyEntity.getRotSpeed() * 0.25F;
      galleyEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_1, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_2, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_3, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_4, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_1, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_2, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_3, f);
      galleyEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_4, f);
   }

   @NotNull
   public class_630 method_32008() {
      return this.root;
   }
}
