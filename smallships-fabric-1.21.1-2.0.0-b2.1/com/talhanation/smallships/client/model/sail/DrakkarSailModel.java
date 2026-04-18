package com.talhanation.smallships.client.model.sail;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5601;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;

public class DrakkarSailModel extends SailModel {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "drakkar_sail_model"), "main");
   private final class_630 DrakkarSail;

   public DrakkarSailModel() {
      class_630 root = createBodyLayer().method_32109();
      this.DrakkarSail = root.method_32086("DrakkarSail");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 DrakkarSail = partdefinition.method_32117("DrakkarSail", class_5606.method_32108(), class_5603.method_32090(-14.0F, 25.0F, 0.0F));
      class_5610 Sail_4 = DrakkarSail.method_32117("Sail_4", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 segel_1_7 = Sail_4.method_32117("segel_1_7", class_5606.method_32108(), class_5603.method_32091(8.5F, -11.0F, 20.5F, 0.0F, 1.5708F, 0.0F));
      class_5610 cube_r87 = segel_1_7.method_32117(
         "cube_r87",
         class_5606.method_32108()
            .method_32101(86, 16)
            .method_32098(-60.0F, -39.0F, 16.6905F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(86, 13)
            .method_32098(-49.0F, -39.0F, 16.6905F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(86, 11)
            .method_32098(-38.0F, -39.0F, 16.6905F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(86, 19)
            .method_32098(-27.0F, -39.0F, 16.6905F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(86, 13)
            .method_32098(-16.0F, -39.0F, 16.6905F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -26.3067F, 36.3895F, 2.0595F, 0.0F, 0.0F)
      );
      class_5610 cube_r88 = segel_1_7.method_32117(
         "cube_r88",
         class_5606.method_32108()
            .method_32101(83, 13)
            .method_32098(-16.0F, -40.25F, 11.2888F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-27.0F, -40.25F, 11.2888F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 14)
            .method_32098(-38.0F, -40.25F, 11.2888F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-49.0F, -40.25F, 11.2888F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-60.0F, -40.25F, 11.2888F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -28.4794F, 33.5112F, 1.8762F, 0.0F, 0.0F)
      );
      class_5610 cube_r89 = segel_1_7.method_32117(
         "cube_r89",
         class_5606.method_32108()
            .method_32101(83, 21)
            .method_32098(-16.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 22)
            .method_32098(-60.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 22)
            .method_32098(-49.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(79, 14)
            .method_32098(-38.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(79, 14)
            .method_32098(-27.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -34.7644F, 33.9338F, 1.7453F, 0.0F, 0.0F)
      );
      class_5610 cube_r90 = segel_1_7.method_32117(
         "cube_r90",
         class_5606.method_32108()
            .method_32101(89, 5)
            .method_32098(-16.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 4)
            .method_32098(-60.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 10)
            .method_32098(-49.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 4)
            .method_32098(-38.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(-27.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -39.6813F, 33.5822F, 1.5272F, 0.0F, 0.0F)
      );
      class_5610 cube_r91 = segel_1_7.method_32117(
         "cube_r91",
         class_5606.method_32108()
            .method_32101(85, 16)
            .method_32098(-60.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(85, 19)
            .method_32098(-49.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(85, 20)
            .method_32098(-27.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(85, 17)
            .method_32098(-16.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(85, 20)
            .method_32098(-38.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -44.7113F, 34.1289F, 1.2217F, 0.0F, 0.0F)
      );
      class_5610 cube_r92 = segel_1_7.method_32117(
         "cube_r92",
         class_5606.method_32108()
            .method_32101(93, 22)
            .method_32098(-60.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 12)
            .method_32098(-49.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 5)
            .method_32098(-38.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 5)
            .method_32098(-27.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 11)
            .method_32098(-16.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -47.7441F, 35.6572F, 1.0908F, 0.0F, 0.0F)
      );
      class_5610 cube_r93 = segel_1_7.method_32117(
         "cube_r93",
         class_5606.method_32108()
            .method_32101(89, 4)
            .method_32098(-60.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 6)
            .method_32098(-49.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 4)
            .method_32098(-38.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 5)
            .method_32098(-16.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 5)
            .method_32098(-27.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -55.4356F, -0.4095F, 0.6545F, 0.0F, 0.0F)
      );
      class_5610 cube_r94 = segel_1_7.method_32117(
         "cube_r94",
         class_5606.method_32108()
            .method_32101(89, 6)
            .method_32098(-60.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 6)
            .method_32098(-49.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 11)
            .method_32098(-38.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 12)
            .method_32098(-27.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 6)
            .method_32098(-16.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -52.4943F, 39.4993F, 0.829F, 0.0F, 0.0F)
      );
      class_5610 cube_r95 = segel_1_7.method_32117(
         "cube_r95",
         class_5606.method_32108()
            .method_32101(93, 24)
            .method_32098(-60.0F, -34.7019F, 23.9822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 6)
            .method_32098(-49.0F, -34.7019F, 23.9822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 5)
            .method_32098(-38.0F, -34.7019F, 23.9822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 4)
            .method_32098(-27.0F, -34.7019F, 23.9822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(89, 4)
            .method_32098(-16.0F, -34.7019F, 23.9822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -20.7281F, 39.2678F, 2.2227F, 0.0F, 0.0F)
      );
      class_5610 rope_15 = segel_1_7.method_32117("rope_15", class_5606.method_32108(), class_5603.method_32091(38.0F, 0.0F, 18.0F, 1.3963F, 0.7941F, 1.7628F));
      class_5610 cube_r96 = rope_15.method_32117(
         "cube_r96",
         class_5606.method_32108()
            .method_32101(93, 22)
            .method_32098(43.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(34.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(26.5F, 0.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(26.5F, -1.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(21.5F, -0.5F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r97 = rope_15.method_32117(
         "cube_r97",
         class_5606.method_32108().method_32101(7, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -12.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r98 = rope_15.method_32117(
         "cube_r98",
         class_5606.method_32108().method_32101(12, 28).method_32098(1.5F, 1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 rope_16 = segel_1_7.method_32117("rope_16", class_5606.method_32108(), class_5603.method_32091(3.0F, 0.5F, 19.0F, 1.2654F, -0.7592F, -1.5708F));
      class_5610 cube_r99 = rope_16.method_32117(
         "cube_r99",
         class_5606.method_32108()
            .method_32101(93, 22)
            .method_32098(43.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(34.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(26.5F, 0.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(26.5F, -1.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(21.5F, -0.5F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r100 = rope_16.method_32117(
         "cube_r100",
         class_5606.method_32108().method_32101(7, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -12.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r101 = rope_16.method_32117(
         "cube_r101",
         class_5606.method_32108().method_32101(12, 28).method_32098(1.5F, 1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -0.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 Sail_3 = DrakkarSail.method_32117("Sail_3", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 segel_1_9 = Sail_3.method_32117("segel_1_9", class_5606.method_32108(), class_5603.method_32091(29.0F, 13.0F, -6.0F, 0.0F, 1.5708F, 0.0F));
      class_5610 segel_1_11 = segel_1_9.method_32117("segel_1_11", class_5606.method_32108(), class_5603.method_32090(-26.5F, -24.0F, -21.5F));
      class_5610 cube_r64 = segel_1_11.method_32117(
         "cube_r64",
         class_5606.method_32108()
            .method_32101(84, 5)
            .method_32098(-16.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-60.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-49.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-38.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-27.0F, -43.5F, 4.75F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -34.7644F, 33.9338F, 1.7453F, 0.0F, 0.0F)
      );
      class_5610 cube_r65 = segel_1_11.method_32117(
         "cube_r65",
         class_5606.method_32108()
            .method_32101(84, 6)
            .method_32098(-16.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-60.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 11)
            .method_32098(-49.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-38.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(-27.0F, -44.0887F, -4.8822F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -39.6813F, 33.5822F, 1.5272F, 0.0F, 0.0F)
      );
      class_5610 cube_r66 = segel_1_11.method_32117(
         "cube_r66",
         class_5606.method_32108()
            .method_32101(88, 24)
            .method_32098(-60.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-49.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-27.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 4)
            .method_32098(-16.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-38.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -44.7113F, 34.1289F, 1.2217F, 0.0F, 0.0F)
      );
      class_5610 cube_r67 = segel_1_11.method_32117(
         "cube_r67",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(-60.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 13)
            .method_32098(-49.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-38.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-27.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-16.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -47.7441F, 35.6572F, 1.0908F, 0.0F, 0.0F)
      );
      class_5610 cube_r68 = segel_1_11.method_32117(
         "cube_r68",
         class_5606.method_32108()
            .method_32101(84, 5)
            .method_32098(-60.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-38.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-16.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-27.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -55.4356F, -0.4095F, 0.6545F, 0.0F, 0.0F)
      );
      class_5610 cube_r69 = segel_1_11.method_32117(
         "cube_r69",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-60.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-38.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 13)
            .method_32098(-27.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -52.4943F, 39.4993F, 0.829F, 0.0F, 0.0F)
      );
      class_5610 rope_19 = segel_1_11.method_32117("rope_19", class_5606.method_32108(), class_5603.method_32091(38.0F, 0.0F, 18.0F, 1.5097F, 0.8552F, 1.7628F));
      class_5610 cube_r70 = rope_19.method_32117(
         "cube_r70",
         class_5606.method_32108()
            .method_32101(85, 17)
            .method_32098(44.5F, -0.5F, -17.5F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(20.5F, -0.5F, -17.5F, 5.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(25.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 16.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r71 = rope_19.method_32117(
         "cube_r71",
         class_5606.method_32108().method_32101(2, 21).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -27.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r72 = rope_19.method_32117(
         "cube_r72",
         class_5606.method_32108().method_32101(7, 29).method_32098(1.5F, 1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -15.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r73 = rope_19.method_32117(
         "cube_r73",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(26.5F, 0.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(26.5F, -1.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 6.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r74 = rope_19.method_32117(
         "cube_r74",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -0.5F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 rope_20 = segel_1_11.method_32117(
         "rope_20", class_5606.method_32108(), class_5603.method_32091(3.0F, 0.5F, 19.0F, 1.3422F, -0.8454F, -1.5566F)
      );
      class_5610 cube_r75 = rope_20.method_32117(
         "cube_r75",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(45.5F, -1.0F, -17.5F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(19.5F, -1.0F, -17.5F, 6.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(25.5F, -1.0F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 15.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r76 = rope_20.method_32117(
         "cube_r76",
         class_5606.method_32108().method_32101(2, 21).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -29.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r77 = rope_20.method_32117(
         "cube_r77",
         class_5606.method_32108().method_32101(7, 29).method_32098(1.5F, 1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -17.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r78 = rope_20.method_32117(
         "cube_r78",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(26.5F, 0.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(26.5F, -1.75F, -17.5F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, 4.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r79 = rope_20.method_32117(
         "cube_r79",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -1.0F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 sail_end4 = segel_1_11.method_32117("sail_end4", class_5606.method_32108(), class_5603.method_32091(-2.5F, -31.0F, -9.0F, 0.7854F, 0.0F, 0.0F));
      class_5610 cube_r80 = sail_end4.method_32117(
         "cube_r80",
         class_5606.method_32108()
            .method_32101(88, 24)
            .method_32098(-37.0F, -41.6933F, 18.2105F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-50.0F, -41.6933F, 18.2105F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(83, 11)
            .method_32098(-63.0F, -41.6933F, 18.2105F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(83, 14)
            .method_32098(-24.0F, -41.6933F, 18.2105F, 13.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-11.0F, -41.6933F, 18.2105F, 9.0F, 3.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(55.5F, -1.8067F, 44.7895F, 2.0595F, 0.0F, 0.0F)
      );
      class_5610 Sail_2 = DrakkarSail.method_32117("Sail_2", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 segel_1_6 = Sail_2.method_32117("segel_1_6", class_5606.method_32108(), class_5603.method_32091(29.0F, 13.0F, -6.0F, 0.0F, 1.5708F, 0.0F));
      class_5610 segel_1_8 = segel_1_6.method_32117("segel_1_8", class_5606.method_32108(), class_5603.method_32090(-26.5F, -24.0F, -21.5F));
      class_5610 cube_r43 = segel_1_8.method_32117(
         "cube_r43",
         class_5606.method_32108()
            .method_32101(88, 24)
            .method_32098(-60.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-49.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-27.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 4)
            .method_32098(-16.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-38.0F, -40.9587F, -18.0289F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -44.7113F, 34.1289F, 1.2217F, 0.0F, 0.0F)
      );
      class_5610 cube_r44 = segel_1_8.method_32117(
         "cube_r44",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(-60.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 13)
            .method_32098(-49.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-38.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-27.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-16.0F, -38.7859F, -21.6372F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -47.7441F, 35.6572F, 1.0908F, 0.0F, 0.0F)
      );
      class_5610 cube_r45 = segel_1_8.method_32117(
         "cube_r45",
         class_5606.method_32108()
            .method_32101(84, 5)
            .method_32098(-60.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-38.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-16.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-27.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -55.4356F, -0.4095F, 0.6545F, 0.0F, 0.0F)
      );
      class_5610 cube_r46 = segel_1_8.method_32117(
         "cube_r46",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-60.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-38.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 13)
            .method_32098(-27.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -52.4943F, 39.4993F, 0.829F, 0.0F, 0.0F)
      );
      class_5610 rope_11 = segel_1_8.method_32117("rope_11", class_5606.method_32108(), class_5603.method_32091(38.0F, 0.0F, 18.0F, 1.5001F, 0.9684F, 1.7506F));
      class_5610 cube_r47 = rope_11.method_32117(
         "cube_r47",
         class_5606.method_32108()
            .method_32101(85, 14)
            .method_32098(53.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 6)
            .method_32098(20.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(34.5237F, -0.4798F, -17.3901F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 16.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r48 = rope_11.method_32117(
         "cube_r48",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.6164F, 4.645F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -27.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r49 = rope_11.method_32117(
         "cube_r49",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.8664F, 7.895F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -15.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r50 = rope_11.method_32117(
         "cube_r50",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5237F, 0.7702F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5237F, -1.7298F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 6.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r51 = rope_11.method_32117(
         "cube_r51",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -0.5F, -17.4F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 rope_12 = segel_1_8.method_32117("rope_12", class_5606.method_32108(), class_5603.method_32091(3.0F, 0.5F, 19.0F, 1.3165F, -0.9301F, -1.5235F));
      class_5610 cube_r52 = rope_12.method_32117(
         "cube_r52",
         class_5606.method_32108()
            .method_32101(86, 6)
            .method_32098(54.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(19.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(33.5005F, -0.8459F, -19.492F, 12.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 15.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r53 = rope_12.method_32117(
         "cube_r53",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.4019F, 4.9799F, -3.0738F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -29.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r54 = rope_12.method_32117(
         "cube_r54",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.7553F, 7.9733F, -2.992F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -17.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r55 = rope_12.method_32117(
         "cube_r55",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5005F, 0.9041F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5005F, -1.5959F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, 4.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r56 = rope_12.method_32117(
         "cube_r56",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -0.875F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 sail_end3 = segel_1_8.method_32117("sail_end3", class_5606.method_32108(), class_5603.method_32091(-2.5F, -31.0F, -9.0F, 0.7854F, 0.0F, 0.0F));
      class_5610 cube_r57 = sail_end3.method_32117(
         "cube_r57",
         class_5606.method_32108()
            .method_32101(88, 24)
            .method_32098(-6.1F, -2.25F, -2.0F, 13.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-19.1F, -2.25F, -2.0F, 13.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(83, 11)
            .method_32098(-32.1F, -2.25F, -2.0F, 13.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(82, 6)
            .method_32098(6.9F, -2.25F, -2.0F, 13.0F, 4.0F, 4.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(19.9F, -2.25F, -2.0F, 9.0F, 4.0F, 4.0F, new class_5605(0.0F)),
         class_5603.method_32091(24.6F, -7.5398F, 5.0626F, -2.2166F, 0.0F, 0.0F)
      );
      class_5610 Sail_1 = DrakkarSail.method_32117("Sail_1", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 segel_1_2 = Sail_1.method_32117("segel_1_2", class_5606.method_32108(), class_5603.method_32091(29.0F, 13.0F, -6.0F, 0.0F, 1.5708F, 0.0F));
      class_5610 segel_1_5 = segel_1_2.method_32117("segel_1_5", class_5606.method_32108(), class_5603.method_32090(-26.5F, -24.0F, -21.5F));
      class_5610 cube_r25 = segel_1_5.method_32117(
         "cube_r25",
         class_5606.method_32108()
            .method_32101(84, 5)
            .method_32098(-60.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 5)
            .method_32098(-38.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-16.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(84, 6)
            .method_32098(-27.0F, -1.0F, -2.5F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -55.4356F, -0.4095F, 0.6545F, 0.0F, 0.0F)
      );
      class_5610 cube_r26 = segel_1_5.method_32117(
         "cube_r26",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-60.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-49.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 12)
            .method_32098(-38.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 11)
            .method_32098(-27.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.0F, -32.7857F, -32.1293F, 11.0F, 2.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(53.0F, -52.4943F, 39.4993F, 0.829F, 0.0F, 0.0F)
      );
      class_5610 rope_7 = segel_1_5.method_32117("rope_7", class_5606.method_32108(), class_5603.method_32091(38.0F, 0.0F, 18.0F, 1.4794F, 1.1163F, 1.7268F));
      class_5610 cube_r27 = rope_7.method_32117(
         "cube_r27",
         class_5606.method_32108()
            .method_32101(85, 13)
            .method_32098(53.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 6)
            .method_32098(20.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(34.5237F, -0.4798F, -17.3901F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 14.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r28 = rope_7.method_32117(
         "cube_r28",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.6164F, 4.645F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -29.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r29 = rope_7.method_32117(
         "cube_r29",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.8664F, 7.895F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -17.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r30 = rope_7.method_32117(
         "cube_r30",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5237F, 0.7702F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5237F, -1.7298F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 4.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r31 = rope_7.method_32117(
         "cube_r31",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -0.5F, -17.4F, 6.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 rope_8 = segel_1_5.method_32117("rope_8", class_5606.method_32108(), class_5603.method_32091(3.0F, 0.5F, 19.0F, 1.2348F, -1.0973F, -1.4276F));
      class_5610 cube_r32 = rope_8.method_32117(
         "cube_r32",
         class_5606.method_32108()
            .method_32101(85, 11)
            .method_32098(54.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(19.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(33.5005F, -0.8459F, -19.492F, 12.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 13.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r33 = rope_8.method_32117(
         "cube_r33",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.4019F, 4.9799F, -3.0738F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -31.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r34 = rope_8.method_32117(
         "cube_r34",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.7553F, 7.9733F, -2.992F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -19.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r35 = rope_8.method_32117(
         "cube_r35",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5005F, 0.9041F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5005F, -1.5959F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, 2.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r36 = rope_8.method_32117(
         "cube_r36",
         class_5606.method_32108().method_32101(88, 23).method_32098(21.5F, -0.875F, -17.5F, 6.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 sail_end2 = segel_1_5.method_32117("sail_end2", class_5606.method_32108(), class_5603.method_32091(-2.5F, -41.0F, -1.5F, 1.0472F, 0.0F, 0.0F));
      class_5610 cube_r102 = sail_end2.method_32117(
         "cube_r102",
         class_5606.method_32108()
            .method_32101(80, 20)
            .method_32098(-6.1F, -2.25F, -2.0F, 13.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(80, 18)
            .method_32098(-19.1F, -2.25F, -2.0F, 13.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(80, 18)
            .method_32098(-32.1F, -2.25F, -2.0F, 13.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(80, 17)
            .method_32098(6.9F, -2.25F, -2.0F, 13.0F, 5.0F, 5.0F, new class_5605(0.0F))
            .method_32101(88, 6)
            .method_32098(19.9F, -2.25F, -2.0F, 9.0F, 5.0F, 5.0F, new class_5605(0.0F)),
         class_5603.method_32091(24.6F, -7.5398F, 5.0626F, -2.2166F, 0.0F, 0.0F)
      );
      class_5610 Sail_0 = DrakkarSail.method_32117("Sail_0", class_5606.method_32108(), class_5603.method_32090(0.0F, 0.0F, 0.0F));
      class_5610 segel_1_3 = Sail_0.method_32117("segel_1_3", class_5606.method_32108(), class_5603.method_32091(29.0F, 13.0F, -6.0F, 0.0F, 1.5708F, 0.0F));
      class_5610 segel_1_4 = segel_1_3.method_32117("segel_1_4", class_5606.method_32108(), class_5603.method_32090(-26.5F, -24.0F, -21.5F));
      class_5610 rope_4 = segel_1_4.method_32117("rope_4", class_5606.method_32108(), class_5603.method_32091(38.0F, 0.0F, 18.0F, 1.4884F, 1.2464F, 1.6894F));
      class_5610 cube_r103 = rope_4.method_32117(
         "cube_r103",
         class_5606.method_32108()
            .method_32101(85, 17)
            .method_32098(53.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(86, 6)
            .method_32098(20.5237F, -0.4798F, -17.3901F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(34.5237F, -0.4798F, -17.3901F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 10.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r104 = rope_4.method_32117(
         "cube_r104",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.6164F, 4.645F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -33.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r105 = rope_4.method_32117(
         "cube_r105",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.8664F, 7.895F, -0.8901F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -21.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r106 = rope_4.method_32117(
         "cube_r106",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5237F, 0.7702F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5237F, -1.7298F, -17.3901F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r107 = rope_4.method_32117(
         "cube_r107",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(27.5F, -0.5F, -17.4F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(21.5F, -0.5F, -17.4F, 6.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 rope_5 = segel_1_4.method_32117("rope_5", class_5606.method_32108(), class_5603.method_32091(3.0F, 0.5F, 19.0F, 1.1491F, -1.1947F, -1.3336F));
      class_5610 cube_r108 = rope_5.method_32117(
         "cube_r108",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(54.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(19.5005F, -0.8459F, -19.492F, 14.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(33.5005F, -0.8459F, -19.492F, 12.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 9.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r109 = rope_5.method_32117(
         "cube_r109",
         class_5606.method_32108().method_32101(2, 21).method_32098(4.4019F, 4.9799F, -3.0738F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -35.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r110 = rope_5.method_32117(
         "cube_r110",
         class_5606.method_32108().method_32101(7, 29).method_32098(7.7553F, 7.9733F, -2.992F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -23.5F, 2.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r111 = rope_5.method_32117(
         "cube_r111",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(35.5005F, 0.9041F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(35.5005F, -1.5959F, -19.492F, 7.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(-0.5F, -1.5F, 19.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r112 = rope_5.method_32117(
         "cube_r112",
         class_5606.method_32108()
            .method_32101(88, 23)
            .method_32098(27.5F, -0.875F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 23)
            .method_32098(21.5F, -0.875F, -17.5F, 6.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 21.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 sail_end = segel_1_4.method_32117(
         "sail_end", class_5606.method_32108(), class_5603.method_32091(22.1F, -54.7898F, -1.4374F, 0.6545F, 0.0F, 0.0F)
      );
      class_5610 cube_r113 = sail_end.method_32117(
         "cube_r113",
         class_5606.method_32108()
            .method_32101(78, 16)
            .method_32098(-6.1F, -3.25F, -3.0F, 13.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(78, 16)
            .method_32098(-19.1F, -3.25F, -3.0F, 13.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(78, 16)
            .method_32098(-32.1F, -3.25F, -3.0F, 13.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(78, 17)
            .method_32098(6.9F, -3.25F, -3.0F, 13.0F, 6.0F, 6.0F, new class_5605(0.0F))
            .method_32101(90, 23)
            .method_32098(19.9F, -3.25F, -3.0F, 9.0F, 6.0F, 6.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 0.25F, 0.0F, -2.2166F, 0.0F, 0.0F)
      );
      class_5610 ropes = DrakkarSail.method_32117("ropes", class_5606.method_32108(), class_5603.method_32090(43.5F, -16.0F, 0.0F));
      class_5610 rope_13 = ropes.method_32117("rope_13", class_5606.method_32108(), class_5603.method_32091(14.0F, -3.0F, 0.0F, 0.0F, -1.5708F, -0.6981F));
      class_5610 cube_r81 = rope_13.method_32117(
         "cube_r81",
         class_5606.method_32108()
            .method_32101(93, 22)
            .method_32098(34.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(64.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(74.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(49.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(22.5F, 0.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(22.5F, -1.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(11.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 11.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r82 = rope_13.method_32117(
         "cube_r82",
         class_5606.method_32108().method_32101(7, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -22.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r83 = rope_13.method_32117(
         "cube_r83",
         class_5606.method_32108().method_32101(12, 28).method_32098(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -10.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 rope_14 = ropes.method_32117("rope_14", class_5606.method_32108(), class_5603.method_32091(-91.0F, -3.0F, 0.0F, 0.8203F, -1.5708F, 0.0F));
      class_5610 cube_r84 = rope_14.method_32117(
         "cube_r84",
         class_5606.method_32108()
            .method_32101(93, 22)
            .method_32098(34.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(79.5F, -0.5F, -17.5F, 10.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(89.5F, -0.5F, -17.5F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(64.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(49.5F, -0.5F, -17.5F, 15.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(22.5F, 0.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(22.5F, -1.75F, -17.5F, 11.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 22)
            .method_32098(11.5F, -0.5F, -17.5F, 9.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 11.5F, 17.0F, 0.0F, 0.0F, -1.5708F)
      );
      class_5610 cube_r85 = rope_14.method_32117(
         "cube_r85",
         class_5606.method_32108().method_32101(7, 20).method_32098(-1.75F, -1.75F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -22.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      class_5610 cube_r86 = rope_14.method_32117(
         "cube_r86",
         class_5606.method_32108().method_32101(12, 28).method_32098(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -10.5F, 0.0F, 0.0F, 0.0F, -2.3562F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(@NotNull Ship drakkar, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      switch (drakkar.<Byte>getData(Ship.SAIL_STATE)) {
         case 0:
            this.DrakkarSail.method_32086("Sail_0").field_3665 = true;
            this.DrakkarSail.method_32086("Sail_1").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_2").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_3").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_4").field_3665 = false;
            break;
         case 1:
            this.DrakkarSail.method_32086("Sail_0").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_1").field_3665 = true;
            this.DrakkarSail.method_32086("Sail_2").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_3").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_4").field_3665 = false;
            break;
         case 2:
            this.DrakkarSail.method_32086("Sail_0").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_1").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_2").field_3665 = true;
            this.DrakkarSail.method_32086("Sail_3").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_4").field_3665 = false;
            break;
         case 3:
            this.DrakkarSail.method_32086("Sail_0").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_1").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_2").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_3").field_3665 = true;
            this.DrakkarSail.method_32086("Sail_4").field_3665 = false;
            break;
         case 4:
            this.DrakkarSail.method_32086("Sail_0").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_1").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_2").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_3").field_3665 = false;
            this.DrakkarSail.method_32086("Sail_4").field_3665 = true;
      }
   }

   public void method_2828(class_4587 poseStack, class_4588 buffer, int packedLight, int packedOverlay, int color) {
      this.DrakkarSail.method_22699(poseStack, buffer, packedLight, packedOverlay, color);
   }
}
