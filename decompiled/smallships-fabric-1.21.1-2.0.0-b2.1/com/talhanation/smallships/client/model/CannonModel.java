package com.talhanation.smallships.client.model;

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
import net.minecraft.class_583;
import net.minecraft.class_630;
import org.jetbrains.annotations.NotNull;

public class CannonModel extends class_583<Ship> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("smallships", "cannon_model"), "main");
   private final class_630 Cannon;
   private final class_630 Lauf;

   public CannonModel() {
      class_630 root = createBodyLayer().method_32109();
      this.Cannon = root.method_32086("Cannon");
      this.Lauf = this.Cannon.method_32086("Lauf");
   }

   public static class_5607 createBodyLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 Cannon = partdefinition.method_32117("Cannon", class_5606.method_32108(), class_5603.method_32090(0.0F, 26.4F, -2.0F));
      class_5610 AchseFront = Cannon.method_32117(
         "AchseFront",
         class_5606.method_32108()
            .method_32101(81, 42)
            .method_32098(-7.404F, -1.152F, -1.04F, 15.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-8.504F, 1.448F, -1.04F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(93, 34)
            .method_32098(-8.404F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(89, 34)
            .method_32098(-8.404F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(-8.304F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(92, 33)
            .method_32098(-8.404F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(7.496F, 1.448F, -1.04F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(93, 34)
            .method_32098(7.346F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(89, 34)
            .method_32098(7.346F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(7.446F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(92, 33)
            .method_32098(7.346F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(-9.304F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 53)
            .method_32098(7.296F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.404F, -4.848F, -4.96F)
      );
      class_5610 cube_r1 = AchseFront.method_32117(
         "cube_r1",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 cube_r2 = AchseFront.method_32117(
         "cube_r2",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -2.3562F, 0.0F, 0.0F)
      );
      class_5610 cube_r3 = AchseFront.method_32117(
         "cube_r3",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 3.1416F, 0.0F, 0.0F)
      );
      class_5610 cube_r4 = AchseFront.method_32117(
         "cube_r4",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 2.3562F, 0.0F, 0.0F)
      );
      class_5610 cube_r5 = AchseFront.method_32117(
         "cube_r5",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -1.5708F, 0.0F, 0.0F)
      );
      class_5610 cube_r6 = AchseFront.method_32117(
         "cube_r6",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -0.7854F, 0.0F, 0.0F)
      );
      class_5610 cube_r7 = AchseFront.method_32117(
         "cube_r7",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 0.7854F, 0.0F, 0.0F)
      );
      class_5610 AchseBack = Cannon.method_32117(
         "AchseBack",
         class_5606.method_32108()
            .method_32101(81, 42)
            .method_32098(-7.404F, -1.152F, -1.04F, 15.0F, 2.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-8.504F, 1.448F, -1.04F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(93, 34)
            .method_32098(-8.404F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(89, 34)
            .method_32098(-8.404F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(-8.304F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(-9.304F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(93, 53)
            .method_32098(7.296F, -0.452F, -0.54F, 2.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(92, 33)
            .method_32098(-8.404F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(7.496F, 1.448F, -1.04F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(93, 34)
            .method_32098(7.346F, -1.452F, -0.54F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(89, 34)
            .method_32098(7.346F, 0.548F, -1.54F, 1.0F, 1.0F, 3.0F, new class_5605(0.0F))
            .method_32101(93, 48)
            .method_32098(7.446F, -1.452F, -1.54F, 1.0F, 2.0F, 1.0F, new class_5605(0.0F))
            .method_32101(92, 33)
            .method_32098(7.346F, -0.452F, 0.46F, 1.0F, 1.0F, 1.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.404F, -4.848F, 9.04F)
      );
      class_5610 cube_r8 = AchseBack.method_32117(
         "cube_r8",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 1.5708F, 0.0F, 0.0F)
      );
      class_5610 cube_r9 = AchseBack.method_32117(
         "cube_r9",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -2.3562F, 0.0F, 0.0F)
      );
      class_5610 cube_r10 = AchseBack.method_32117(
         "cube_r10",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 3.1416F, 0.0F, 0.0F)
      );
      class_5610 cube_r11 = AchseBack.method_32117(
         "cube_r11",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 2.3562F, 0.0F, 0.0F)
      );
      class_5610 cube_r12 = AchseBack.method_32117(
         "cube_r12",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -1.5708F, 0.0F, 0.0F)
      );
      class_5610 cube_r13 = AchseBack.method_32117(
         "cube_r13",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, -0.7854F, 0.0F, 0.0F)
      );
      class_5610 cube_r14 = AchseBack.method_32117(
         "cube_r14",
         class_5606.method_32108()
            .method_32101(83, 10)
            .method_32098(-0.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F))
            .method_32101(83, 10)
            .method_32098(-16.5F, 1.4F, -1.0F, 1.0F, 1.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(7.996F, 0.048F, -0.04F, 0.7854F, 0.0F, 0.0F)
      );
      class_5610 Lauf = Cannon.method_32117(
         "Lauf",
         class_5606.method_32108()
            .method_32101(80, 0)
            .method_32098(-2.5F, 2.5F, -1.0F, 5.0F, 2.0F, 11.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, -2.5F, -1.0F, 5.0F, 5.0F, 11.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, -2.5F, 9.2F, 5.0F, 5.0F, 3.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-1.5F, -1.4F, 10.3F, 3.0F, 3.0F, 3.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-1.0F, -1.0F, 11.2F, 2.0F, 2.0F, 3.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, -4.5F, -1.0F, 5.0F, 2.0F, 11.0F, new class_5605(0.0F))
            .method_32101(88, 0)
            .method_32098(-4.5F, -2.5F, -1.0F, 2.0F, 5.0F, 11.0F, new class_5605(0.0F))
            .method_32101(88, 0)
            .method_32098(2.5F, -2.5F, -1.0F, 2.0F, 5.0F, 11.0F, new class_5605(0.0F))
            .method_32101(78, 3)
            .method_32098(1.5F, -2.5F, -10.0F, 2.0F, 5.0F, 9.0F, new class_5605(0.0F))
            .method_32101(78, 3)
            .method_32098(-3.5F, -2.5F, -10.0F, 2.0F, 5.0F, 9.0F, new class_5605(0.0F))
            .method_32101(78, 3)
            .method_32098(1.5F, -2.0F, -17.0F, 1.0F, 4.0F, 8.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, -3.5F, -10.0F, 5.0F, 2.0F, 9.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.0F, 1.5F, -17.0F, 4.0F, 1.0F, 8.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.0F, -2.5F, -17.0F, 4.0F, 1.0F, 8.0F, new class_5605(0.0F))
            .method_32101(101, 9)
            .method_32098(-2.5F, -2.0F, -17.0F, 1.0F, 4.0F, 8.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.0F, -2.75F, -18.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.0F, 1.75F, -18.0F, 4.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(78, 3)
            .method_32098(1.75F, -2.0F, -18.0F, 1.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(101, 9)
            .method_32098(-2.75F, -2.0F, -18.0F, 1.0F, 4.0F, 1.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, 1.5F, -10.0F, 5.0F, 2.0F, 9.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.5F, -13.4F, -2.0F, -0.1309F, 0.0F, 0.0F)
      );
      class_5610 cube_r15 = Lauf.method_32117(
         "cube_r15",
         class_5606.method_32108().method_32101(88, 0).method_32098(0.0F, -2.5F, -1.5F, 2.0F, 5.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(-3.5F, 0.0F, 11.1F, 0.0F, 0.7418F, 0.0F)
      );
      class_5610 cube_r16 = Lauf.method_32117(
         "cube_r16",
         class_5606.method_32108().method_32101(88, 0).method_32098(-2.0F, -2.5F, -1.4F, 2.0F, 5.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(3.5F, 0.0F, 11.0F, 0.0F, -0.7418F, 0.0F)
      );
      class_5610 cube_r17 = Lauf.method_32117(
         "cube_r17",
         class_5606.method_32108()
            .method_32101(80, 0)
            .method_32098(-2.5F, -0.425F, -1.75F, 5.0F, 2.0F, 3.0F, new class_5605(0.0F))
            .method_32101(80, 0)
            .method_32098(-2.5F, -0.425F, -1.75F, 5.0F, 2.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, -3.0F, 11.0F, -0.7418F, 0.0F, 0.0F)
      );
      class_5610 cube_r18 = Lauf.method_32117(
         "cube_r18",
         class_5606.method_32108().method_32101(80, 0).method_32098(-2.5F, -1.6F, -1.1F, 5.0F, 2.0F, 3.0F, new class_5605(0.0F)),
         class_5603.method_32091(0.0F, 3.5F, 10.5F, 0.733F, 0.0F, 0.0F)
      );
      class_5610 Sides = Cannon.method_32117(
         "Sides",
         class_5606.method_32108()
            .method_32101(79, 4)
            .method_32098(6.0F, -13.9F, -8.25F, 1.0F, 8.0F, 1.0F, new class_5605(0.0F))
            .method_32101(88, 0)
            .method_32098(-6.0F, -9.1F, 11.8F, 13.0F, 1.0F, 1.0F, new class_5605(0.0F))
            .method_32101(73, 38)
            .method_32098(-6.5F, -9.0F, -8.0F, 2.0F, 3.0F, 10.0F, new class_5605(0.0F))
            .method_32101(71, 37)
            .method_32098(-6.5F, -9.0F, 2.0F, 2.0F, 5.0F, 11.0F, new class_5605(0.0F))
            .method_32101(82, 32)
            .method_32098(-6.5F, -11.0F, -8.0F, 2.0F, 2.0F, 17.0F, new class_5605(0.0F))
            .method_32101(96, 34)
            .method_32098(-6.5F, -14.0F, -8.0F, 2.0F, 3.0F, 13.0F, new class_5605(0.0F))
            .method_32101(74, 38)
            .method_32098(5.5F, -9.0F, 2.0F, 2.0F, 5.0F, 11.0F, new class_5605(0.0F))
            .method_32101(73, 39)
            .method_32098(5.5F, -9.0F, -8.0F, 2.0F, 3.0F, 10.0F, new class_5605(0.0F))
            .method_32101(84, 33)
            .method_32098(5.5F, -11.0F, -8.0F, 2.0F, 2.0F, 17.0F, new class_5605(0.0F))
            .method_32101(96, 34)
            .method_32098(5.5F, -14.0F, -8.0F, 2.0F, 3.0F, 13.0F, new class_5605(0.0F))
            .method_32101(79, 4)
            .method_32098(-6.0F, -13.9F, -8.25F, 1.0F, 8.0F, 1.0F, new class_5605(0.0F))
            .method_32101(81, 42)
            .method_32098(-6.0F, -7.0F, 2.0F, 13.0F, 2.0F, 4.0F, new class_5605(0.0F))
            .method_32101(81, 42)
            .method_32098(-6.0F, -10.0F, -7.0F, 13.0F, 3.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32090(0.0F, 0.0F, 0.0F)
      );
      class_5610 cube_r19 = Sides.method_32117(
         "cube_r19",
         class_5606.method_32108().method_32101(88, 0).method_32098(-2.0F, 1.9F, -3.7F, 16.0F, 2.0F, 2.0F, new class_5605(0.0F)),
         class_5603.method_32091(-5.5F, -15.0F, 5.0F, 0.7418F, 0.0F, 0.0F)
      );
      return class_5607.method_32110(meshdefinition, 128, 64);
   }

   public void setupAnim(@NotNull Ship entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
   }

   public void setLaufPitch(float angleInDeg) {
      this.Lauf.field_3654 = (float)Math.toRadians(angleInDeg);
   }

   public void method_2828(class_4587 poseStack, class_4588 buffer, int packedLight, int packedOverlay, int color) {
      this.Cannon.method_22699(poseStack, buffer, packedLight, packedOverlay, color);
   }
}
