package com.talhanation.smallships.client.model;

import com.talhanation.smallships.world.entity.ship.CogEntity;
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

public class CogModel extends ShipModel<CogEntity> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "cog_model"), "main");
   private final class_630 root;
   private final class_630 chest1;
   private final class_630 chest2;
   private final class_630 chest3;
   private final class_630 chest4;
   private final class_630 steer;

   public CogModel(class_630 modelPart) {
      this.root = modelPart;
      class_630 cog = this.root.method_32086("Cog");
      this.chest1 = cog.method_32086("chest_1");
      this.chest2 = cog.method_32086("chest_2");
      this.chest3 = cog.method_32086("chest_3");
      this.chest4 = cog.method_32086("chest_4");
      this.steer = cog.method_32086("steer");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 Cog = partdefinition.method_32117("Cog", class_5606.method_32108(), class_5603.method_32090(0.0F, 24.0F, 0.0F));
      class_5610 deck = Cog.method_32117("deck", class_5606.method_32108(), class_5603.method_32090(14.0F, 0.0F, 0.0F));
      class_5610 cube_r1 = deck.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(28, 0)
            .method_32098(28.0F, 0.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(28, 0)
            .method_32098(28.0F, -13.0F, 2.0F, 14.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, 0.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F))
            .method_32101(30, 0)
            .method_32098(-40.0F, -6.5F, 7.0F, 12.0F, 13.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-13.0F, -16.0F, 2.0F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-13.0F, 13.0F, 2.0F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 0.0F, 2.0F, 28.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -13.0F, 2.0F, 28.0F, 13.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, 16.0F, 2.0F, 18.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -19.0F, 2.0F, 18.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(0.0F, -16.0F, 2.0F, 28.0F, 16.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-14.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 bottom = Cog.method_32117(
         "bottom",
         class_5606.method_32108().method_32101(4, 1).method_32098(42.0F, -11.0F, -3.5F, 4.0F, 13.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r2 = bottom.method_32117(
         "cube_r2",
         class_5606.method_32108()
            .method_32101(4, 1)
            .method_32098(-3.0F, -13.5F, -3.5F, 7.0F, 7.0F, 7.0F, new class_5605(0.0F))
            .method_32101(4, 1)
            .method_32098(-3.0F, -6.5F, -3.5F, 7.0F, 13.0F, 7.0F, new class_5605(0.0F)),
         class_5603.method_32091(-28.44F, 2.4938F, 0.0F, 0.0F, 0.0F, -0.5236F)
      );
      class_5610 cube_r3 = bottom.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(38.0F, -2.0F, -10.0F, 4.0F, 4.0F, 8.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(16.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-6.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -4.0F, -10.0F, 22.0F, 8.0F, 7.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, 2.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(-28.0F, -12.0F, -3.0F, 22.0F, 10.0F, 5.0F, new class_5605(0.0F))
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
      class_5610 sides = Cog.method_32117(
         "sides",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(-28.0F, -11.0F, -16.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-13.0F, -11.0F, -19.0F, 14.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(0.0F, -11.0F, -22.0F, 18.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(18.0F, -11.0F, -19.0F, 10.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-28.0F, -11.0F, 13.0F, 15.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-13.0F, -11.0F, 16.0F, 14.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, -16.0F, 14.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -17.0F, -16.0F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -17.0F, -16.0F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -17.0F, 13.0F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(37.0F, -17.0F, 13.0F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-35.0F, -16.0F, -6.5F, 2.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-35.0F, -16.0F, 4.5F, 2.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-31.0F, -16.0F, 4.5F, 3.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-31.0F, -16.0F, -6.5F, 3.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-38.0F, -16.0F, 4.5F, 1.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-38.0F, -16.0F, -6.5F, 1.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(28.0F, -11.0F, 13.0F, 14.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(0.0F, -11.0F, 19.0F, 18.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(18.0F, -11.0F, 16.0F, 10.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r4 = sides.method_32117(
         "cube_r4",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(-23.0F, -3.0F, 6.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-6.0F, -8.0F, -75.0F, 3.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-11.0F, -8.0F, -75.0F, 3.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-16.0F, -8.0F, -75.0F, 3.0F, 4.0F, 2.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-23.0F, -9.0F, 6.0F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-1.5F, -9.0F, 6.5F, 5.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-14.0F, -9.0F, 6.5F, 9.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.5F, -3.0F, 6.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(35.0F, -8.0F, -9.5F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r5 = sides.method_32117(
         "cube_r5",
         class_5606.method_32108()
            .method_32101(8, 36)
            .method_32098(3.5F, -3.0F, 6.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 36)
            .method_32098(-9.5F, -3.0F, 6.5F, 13.0F, 6.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-20.5F, -8.0F, -3.5F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 mast_1 = Cog.method_32117(
         "mast_1",
         class_5606.method_32108()
            .method_32101(8, 0)
            .method_32098(-3.0F, -15.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-3.0F, -30.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-3.0F, -45.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-3.0F, -60.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F))
            .method_32101(8, 0)
            .method_32098(-3.0F, -75.0F, -0.5F, 3.0F, 15.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32090(14.0F, -5.0F, -1.0F)
      );
      class_5610 cube_r6 = mast_1.method_32117(
         "cube_r6",
         class_5606.method_32108().method_32101(8, 0).method_32098(-6.0F, -7.5F, -1.5F, 3.0F, 17.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-47.5F, -15.5F, 1.0F, 0.0F, 0.0F, -0.7854F)
      );
      class_5610 mast_oben = Cog.method_32117(
         "mast_oben",
         class_5606.method_32108()
            .method_32101(0, 0)
            .method_32098(9.0F, -69.0F, -16.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(9.0F, -69.0F, -32.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(9.0F, -69.0F, 0.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F))
            .method_32101(0, 0)
            .method_32098(9.0F, -69.0F, 16.0F, 2.0F, 2.0F, 16.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 chest_1 = Cog.method_32117("chest_1", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 chest_1_r1 = chest_1.method_32117(
         "chest_1_r1",
         class_5606.method_32108().method_32101(96, 38).method_32098(-13.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(37.0F, -9.0F, 0.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r7 = chest_1.method_32117(
         "cube_r7",
         class_5606.method_32108().method_32101(30, 55).method_32098(-0.25F, 3.65F, -0.25F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(27.75F, -11.75F, -8.25F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 cube_r8 = chest_1.method_32117(
         "cube_r8",
         class_5606.method_32108().method_32101(30, 55).method_32098(-1.0F, 5.5F, -10.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(19.0F, -15.5F, -18.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r9 = chest_1.method_32117(
         "cube_r9",
         class_5606.method_32108().method_32101(30, 55).method_32098(57.0F, -19.0F, 25.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(31.0F, 9.0F, -75.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r10 = chest_1.method_32117("cube_r10", class_5606.method_32108(), class_5603.method_32091(22.0F, -9.0F, -1.0F, 0.0F, 3.1416F, 0.0F));
      class_5610 chest_4 = Cog.method_32117("chest_4", class_5606.method_32108(), class_5603.method_32090(-23.0F, -11.5F, 9.0F));
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
      class_5610 chest_2 = Cog.method_32117("chest_2", class_5606.method_32108(), class_5603.method_32090(19.0F, -15.5F, 15.0F));
      class_5610 cube_r14 = chest_2.method_32117(
         "cube_r14",
         class_5606.method_32108().method_32101(30, 55).method_32098(0.0F, 6.5F, -10.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.0F, 0.0F, 1.5708F, -1.5708F, 0.0F)
      );
      class_5610 cube_r15 = chest_2.method_32117(
         "cube_r15",
         class_5606.method_32108()
            .method_32101(30, 55)
            .method_32098(57.0F, -19.0F, 14.0F, 4.0F, 5.0F, 4.0F, new class_5605(0.0F))
            .method_32101(30, 55)
            .method_32098(32.0F, -25.0F, -8.0F, 4.0F, 3.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(12.0F, 24.5F, -57.0F, 0.0F, -1.5708F, 0.0F)
      );
      class_5610 cube_r16 = chest_2.method_32117("cube_r16", class_5606.method_32108(), class_5603.method_32091(3.0F, 6.5F, -16.0F, 0.0F, 3.1416F, 0.0F));
      class_5610 cube_r16_r1 = cube_r16.method_32117(
         "cube_r16_r1",
         class_5606.method_32108().method_32101(96, 38).method_32098(4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new class_5605(0.0F)),
         class_5603.method_32091(-15.0F, 0.0F, 7.5F, 0.0F, 1.5708F, 0.0F)
      );
      class_5610 chest_3 = Cog.method_32117("chest_3", class_5606.method_32108(), class_5603.method_32090(51.3333F, -8.0F, -11.1667F));
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
      class_5610 steer = Cog.method_32117(
         "steer",
         class_5606.method_32108().method_32101(4, 1).method_32098(0.0F, -7.0F, -1.0F, 4.0F, 14.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(42.0F, 9.0F, 0.0F)
      );
      class_5610 BannerStick = Cog.method_32117(
         "BannerStick",
         class_5606.method_32108().method_32101(8, 0).method_32098(12.0F, -94.0F, -0.5F, 1.0F, 15.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, -1.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(@NotNull CogEntity cogEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      this.chest1.field_3665 = cogEntity.getInvFillState() >= 15;
      this.chest2.field_3665 = cogEntity.getInvFillState() >= 30;
      this.chest3.field_3665 = cogEntity.getInvFillState() >= 60;
      this.chest4.field_3665 = cogEntity.getInvFillState() >= 90;
      this.steer.field_3675 = -cogEntity.getRotSpeed() * 0.25F;
   }

   @NotNull
   public class_630 method_32008() {
      return this.root;
   }
}
