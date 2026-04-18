package com.talhanation.smallships.client.model;

import com.talhanation.smallships.world.entity.ship.DrakkarEntity;
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

public class DrakkarModel extends ShipModel<DrakkarEntity> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "drakkar_model"), "main");
   private final class_630 root;
   private final class_630 drakkar;
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

   public DrakkarModel(class_630 modelPart) {
      this.root = modelPart;
      this.drakkar = this.root.method_32086("Drakkar");
      this.chest1 = this.drakkar.method_32086("chest_1");
      this.chest2 = this.drakkar.method_32086("chest_2");
      this.chest3 = this.drakkar.method_32086("chest_3");
      this.chest4 = this.drakkar.method_32086("chest_4");
      this.steer = this.drakkar.method_32086("steer");
      this.row_L_1 = this.drakkar.method_32086("row_L_1");
      this.row_L_2 = this.drakkar.method_32086("row_L_2");
      this.row_L_3 = this.drakkar.method_32086("row_L_3");
      this.row_L_4 = this.drakkar.method_32086("row_L_4");
      this.row_R_1 = this.drakkar.method_32086("row_R_1");
      this.row_R_2 = this.drakkar.method_32086("row_R_2");
      this.row_R_3 = this.drakkar.method_32086("row_R_3");
      this.row_R_4 = this.drakkar.method_32086("row_R_4");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 Drakkar = partdefinition.method_32117("Drakkar", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 deck = Drakkar.method_32117("deck", class_5606.method_32108(), class_5603.method_32090(14.0F, 0.0F, 0.0F));
      class_5610 cube_r1 = deck.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(28, 0)
            .method_32098(-55.0F, -13.0F, 2.0F, 13.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(-55.0F, 0.0F, 2.0F, 13.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(-42.0F, 0.0F, 2.0F, 14.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(-42.0F, -16.0F, 2.0F, 14.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(28.0F, -13.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(28.0F, 0.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
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
      class_5610 bottom = Drakkar.method_32117("bottom", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 cube_r2 = bottom.method_32117(
         "cube_r2",
         class_5606.method_32108().method_32101(8, 2).method_32098(-56.0F, 3.0F, -4.0F, 14.0F, 7.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r7_r1 = cube_r2.method_32117(
         "cube_r7_r1",
         class_5606.method_32108()
            .method_32101(7, 4)
            .method_32098(-1.6F, 4.8F, -1.5F, 6.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 2)
            .method_32098(-26.6F, 0.8F, -3.0F, 25.0F, 7.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(38.0F, 5.5F, 0.0F, -3.1416F, 0.0F, 1.5708F)
      );
      class_5610 cube_r6_r1 = cube_r2.method_32117(
         "cube_r6_r1",
         class_5606.method_32108().method_32101(5, 2).method_32098(-3.5F, -5.5F, -2.5F, 5.0F, 5.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(48.3F, -18.6F, 0.0F, -3.1416F, 0.0F, 0.7854F)
      );
      class_5610 cube_r5_r1 = cube_r2.method_32117(
         "cube_r5_r1",
         class_5606.method_32108().method_32101(4, 1).method_32098(-7.2F, -3.5F, -3.5F, 10.0F, 7.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(38.0F, 5.5F, 0.0F, 3.1416F, 0.0F, 2.4871F)
      );
      class_5610 cube_r7_r2 = cube_r2.method_32117(
         "cube_r7_r2",
         class_5606.method_32108().method_32101(5, 2).method_32098(-2.85F, -10.425F, -2.5F, 7.0F, 12.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(-67.3F, -17.1F, 0.0F, 0.0F, 0.0F, 0.829F)
      );
      class_5610 cube_r5_r2 = cube_r2.method_32117(
         "cube_r5_r2",
         class_5606.method_32108().method_32101(0, 0).method_32098(-26.6F, 0.8F, -3.0F, 25.0F, 7.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(-56.0F, 5.5F, 0.0F, 0.0F, 0.0F, 1.5708F)
      );
      class_5610 cube_r4_r1 = cube_r2.method_32117(
         "cube_r4_r1",
         class_5606.method_32108().method_32101(4, 1).method_32098(-7.2F, -3.5F, -3.5F, 10.0F, 7.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(-56.0F, 5.5F, 0.0F, 0.0F, 0.0F, 0.6545F)
      );
      class_5610 cube_r3 = bottom.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(16.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -4.0F, -10.0F, 14.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 2.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -12.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, -12.0F, -3.0F, 14.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-42.0F, 2.0F, -3.0F, 14.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-55.0F, -12.0F, -3.0F, 13.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-55.0F, 2.0F, -3.0F, 13.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -12.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(18.0F, -12.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, 2.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(18.0F, 2.0F, -3.0F, 24.0F, 10.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 sides = Drakkar.method_32117(
         "sides",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(-56.0F, -11.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-43.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-56.0F, -11.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-43.0F, -11.0F, 16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(13.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-2.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-28.0F, -11.0F, -19.0F, 11.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-17.0F, -11.0F, -19.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
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
            .method_32098(3.5001F, -3.0F, 35.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.4999F, -3.0F, 35.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.5001F, 3.0F, 34.5F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.4999F, 3.0F, 34.5F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-8.4999F, 6.0F, 34.5F, 11.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.5001F, 6.0F, 34.5F, 12.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.5002F, -3.0F, -65.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(3.4998F, -3.0F, -65.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-20.5F, -8.0F, -3.5F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 steer = Drakkar.method_32117(
         "steer",
         class_5606.method_32108()
            .method_32101(4, 1)
            .method_32098(4.0F, 2.75F, -1.0F, 4.0F, 8.0F, 2.0F, new class_5605(0.0F))
            .method_32101(4, 1)
            .method_32098(0.0F, -0.25F, -1.0F, 4.0F, 11.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(45.75F, -0.75F, 0.0F)
      );
      class_5610 row_L_4 = Drakkar.method_32117(
         "row_L_4",
         class_5606.method_32108().method_32101(33, 3).method_32098(-1.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-32.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r20 = row_L_4.method_32117(
         "cube_r20",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-11.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-11.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(11.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_3 = Drakkar.method_32117(
         "row_L_3",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-13.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r21 = row_L_3.method_32117(
         "cube_r21",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-11.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-11.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(10.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_2 = Drakkar.method_32117(
         "row_L_2",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(5.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r22 = row_L_2.method_32117(
         "cube_r22",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-7.0F, 19.0F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-7.0F, 2.0F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(6.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_L_1 = Drakkar.method_32117(
         "row_L_1",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.5F, -0.5F, -28.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(24.5F, -12.0F, -18.0F, 0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r23 = row_L_1.method_32117(
         "cube_r23",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-3.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-3.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(2.5F, 9.0F, -23.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_1 = Drakkar.method_32117(
         "row_R_1",
         class_5606.method_32108().method_32101(33, 3).method_32098(-3.0F, -0.5F, 19.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(25.0F, -12.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r24 = row_R_1.method_32117(
         "cube_r24",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-5.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-5.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(4.0F, 9.0F, -13.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_2 = Drakkar.method_32117(
         "row_R_2",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.0F, -1.5F, 19.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(5.0F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r25 = row_R_2.method_32117(
         "cube_r25",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-3.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-3.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 8.0F, -13.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_3 = Drakkar.method_32117(
         "row_R_3",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.0F, -1.5F, 19.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-14.0F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r26 = row_R_3.method_32117(
         "cube_r26",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-3.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-3.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 8.0F, -13.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 row_R_4 = Drakkar.method_32117(
         "row_R_4",
         class_5606.method_32108().method_32101(33, 3).method_32098(-2.0F, -1.5F, 19.0F, 5.0F, 1.0F, 10.0F, new class_5605(0.0F)),
         class_5603.method_32091(-30.0F, -11.0F, 18.0F, -0.2618F, 0.0F, 0.0F)
      );
      class_5610 cube_r27 = row_R_4.method_32117(
         "cube_r27",
         class_5606.method_32108()
            .method_32101(9, 0)
            .method_32098(-3.0F, 18.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F))
            .method_32101(9, 0)
            .method_32098(-3.0F, 1.5F, 8.0F, 1.0F, 17.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 8.0F, -13.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 mast_1 = Drakkar.method_32117(
         "mast_1",
         class_5606.method_32108()
            .method_32101(8, 0)
            .method_32098(-6.0F, -15.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-6.0F, -30.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-6.0F, -45.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-6.0F, -60.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-6.0F, -75.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-6.0F, -81.0F, -0.5F, 3.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32090(2.0F, -5.0F, -1.0F)
      );
      class_5610 cube_r6 = mast_1.method_32117("cube_r6", class_5606.method_32108(), class_5603.method_32091(-47.5F, -15.5F, 1.0F, 0.0F, 0.0F, -0.7854F));
      class_5610 BannerStick = Drakkar.method_32117(
         "BannerStick",
         class_5606.method_32108().method_32101(8, 0).method_32098(9.0F, -100.0F, -0.5F, 1.0F, 15.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(-12.0F, -1.0F, 0.0F)
      );
      class_5610 mast_oben = Drakkar.method_32117(
         "mast_oben",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(6.0F, -69.0F, -16.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(6.0F, -69.0F, -32.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(6.0F, -69.0F, 0.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(6.0F, -69.0F, 16.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F)),
         class_5603.method_32090(-12.0F, 0.0F, 0.0F)
      );
      class_5610 chest_1 = Drakkar.method_32117(
         "chest_1",
         class_5606.method_32108().method_32101(96, 38).method_32098(-25.0F, -13.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32090(55.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r7 = chest_1.method_32117(
         "cube_r7",
         class_5606.method_32108().method_32101(30, 55).method_32098(-11.7498F, -4.25F, -51.25F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(27.75F, -11.75F, -8.25F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r8 = chest_1.method_32117(
         "cube_r8",
         class_5606.method_32108().method_32101(30, 55).method_32098(8.0001F, 40.5F, -10.0001F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(19.0F, -15.5F, -18.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r9 = chest_1.method_32117(
         "cube_r9",
         class_5606.method_32108().method_32101(30, 55).method_32098(81.0001F, -19.0F, 54.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.0F, 9.0F, -75.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r10 = chest_1.method_32117(
         "cube_r10",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-17.0001F, -9.0F, -8.5002F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 chest_2 = Drakkar.method_32117("chest_2", class_5606.method_32108(), class_5603.method_32090(86.0F, -15.5F, 15.0F));
      class_5610 cube_r14 = chest_2.method_32117(
         "cube_r14",
         class_5606.method_32108().method_32101(30, 55).method_32098(-18.9997F, 90.5F, -10.0003F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r15 = chest_2.method_32117(
         "cube_r15",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(42.0003F, -19.0F, 102.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(49.0002F, -25.0F, 58.9999F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(12.0F, 24.5F, -57.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r16 = chest_2.method_32117(
         "cube_r16",
         class_5606.method_32108().method_32101(96, 38).method_32098(48.0001F, -4.0F, -13.4995F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.0F, 6.5F, -16.0F, 0.0F, 3.1416F, 0.0F)
      );
      class_5610 chest_3 = Drakkar.method_32117("chest_3", class_5606.method_32108(), class_5603.method_32090(24.3333F, -8.0F, 0.8333F));
      class_5610 cube_r17 = chest_3.method_32117(
         "cube_r17",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(31.0F, -19.0F, 45.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(31, 56)
            .method_32098(33.0F, -19.0F, 13.0F, 4.0F, 5.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-20.3333F, 17.0F, -33.8333F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r18 = chest_3.method_32117(
         "cube_r18",
         class_5606.method_32108().method_32101(96, 38).method_32098(-5.0F, -4.0F, -49.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-29.3333F, -1.0F, 7.1667F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 chest_4 = Drakkar.method_32117(
         "chest_4",
         class_5606.method_32108().method_32101(96, 38).method_32098(-39.0F, -13.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32090(-15.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r11 = chest_4.method_32117(
         "cube_r11",
         class_5606.method_32108().method_32101(30, 55).method_32098(-11.7498F, -4.25F, -64.25F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(27.75F, -11.75F, -8.25F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r12 = chest_4.method_32117(
         "cube_r12",
         class_5606.method_32108().method_32101(30, 55).method_32098(8.0001F, 45.5F, -10.0001F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(19.0F, -15.5F, -18.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r13 = chest_4.method_32117(
         "cube_r13",
         class_5606.method_32108().method_32101(30, 55).method_32098(81.0001F, -19.0F, 57.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.0F, 9.0F, -75.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r19 = chest_4.method_32117(
         "cube_r19",
         class_5606.method_32108().method_32101(96, 38).method_32098(-4.0F, -4.0F, 15.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-17.0001F, -9.0F, -8.5002F, 0.0F, -1.5708F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(DrakkarEntity drakkarEntity, float f, float g, float h, float i, float j) {
      this.chest1.field_3665 = drakkarEntity.getInvFillState() >= 15;
      this.chest2.field_3665 = drakkarEntity.getInvFillState() >= 30;
      this.chest3.field_3665 = drakkarEntity.getInvFillState() >= 60;
      this.chest4.field_3665 = drakkarEntity.getInvFillState() >= 90;
      this.steer.field_3675 = -drakkarEntity.getRotSpeed() * 0.25F;
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_1, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_2, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_3, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.LEFT, this.row_L_4, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_1, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_2, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_3, f);
      drakkarEntity.animatePaddle(Paddleable.PaddleSide.RIGHT, this.row_R_4, f);
   }

   @NotNull
   public class_630 method_32008() {
      return this.root;
   }
}
