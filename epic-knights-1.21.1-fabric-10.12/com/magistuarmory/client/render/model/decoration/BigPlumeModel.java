package com.magistuarmory.client.render.model.decoration;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1309;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_630;

@Environment(EnvType.CLIENT)
public class BigPlumeModel<T extends class_1309> extends ArmorDecorationModel<T> {
   public BigPlumeModel(class_630 root) {
      super(root);
   }

   public static class_5607 createLayer() {
      class_5609 meshdefinition = new class_5609();
      class_5610 partdefinition = meshdefinition.method_32111();
      class_5610 head = partdefinition.method_32117("head", class_5606.method_32108(), class_5603.field_27701);
      head.method_32117(
         "Plume4",
         class_5606.method_32108()
            .method_32101(12, 18)
            .method_32096()
            .method_32098(4.2F, -12.6F, 6.8F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.0016F, -0.2731F, 1.0016F)
      );
      head.method_32117(
         "Plume12",
         class_5606.method_32108().method_32101(0, 23).method_32098(3.7F, 5.9F, 8.0F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.8652F, -0.1367F, -0.5918F)
      );
      head.method_32117(
         "Plume7",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-5.7F, -16.8F, 3.4F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.0016F, 0.1367F, 2.4586F)
      );
      head.method_32117(
         "Plume21",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-2.9F, -14.8F, 2.8F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.3203F, 0.6829F, 0.0F)
      );
      head.method_32117(
         "Plume1",
         class_5606.method_32108()
            .method_32101(0, 23)
            .method_32096()
            .method_32098(2.5F, -6.8F, 9.7F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.3643F, 0.0F, 0.0F)
      );
      head.method_32117(
         "Plume0",
         class_5606.method_32108().method_32101(24, 17).method_32098(1.1F, -11.9F, 3.0F, 2.0F, 3.0F, 2.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.4114F, 0.0911F, -0.6829F)
      );
      head.method_32117(
         "Plume2",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-0.6F, -15.6F, 2.7F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.1383F, -0.0911F, 1.2748F)
      );
      head.method_32117(
         "Plume11",
         class_5606.method_32108().method_32101(0, 23).method_32098(2.5F, 4.5F, 12.1F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.3643F, 0.0F, 0.0F)
      );
      head.method_32117(
         "Plume27",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-0.6F, -14.6F, 5.2F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.9561F, 0.3643F, -0.1367F)
      );
      head.method_32117(
         "Plume8",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-1.5F, -14.6F, 3.0F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.5026F, -0.2276F, -0.9561F)
      );
      head.method_32117(
         "Plume28",
         class_5606.method_32108().method_32101(0, 23).method_32098(2.3F, 5.7F, 7.2F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.4098F, 0.182F, -1.4114F)
      );
      head.method_32117(
         "Plume19",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-1.5F, -15.4F, 0.3F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.5026F, 0.4554F, 0.3643F)
      );
      head.method_32117(
         "Plume5",
         class_5606.method_32108()
            .method_32101(12, 25)
            .method_32096()
            .method_32098(-0.6F, -15.5F, 5.2F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.8652F, 0.1367F, 0.4554F)
      );
      head.method_32117(
         "Plume14",
         class_5606.method_32108().method_32101(0, 23).method_32098(6.6F, 1.7F, 6.5F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.5463F, -0.5009F, -1.457F)
      );
      head.method_32117(
         "Plume22",
         class_5606.method_32108().method_32101(0, 23).method_32098(-7.1F, 2.3F, 2.7F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -0.0911F, 0.6829F, -2.6407F)
      );
      head.method_32117(
         "Plume17",
         class_5606.method_32108()
            .method_32101(0, 23)
            .method_32096()
            .method_32098(3.4F, 5.4F, 2.1F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 1.639F, -0.4554F, 0.2731F)
      );
      head.method_32117(
         "Plume24",
         class_5606.method_32108().method_32101(0, 23).method_32098(-1.4F, 4.3F, 12.6F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F)),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, 0.3643F, 0.0456F, 0.0911F)
      );
      head.method_32117(
         "Plume13",
         class_5606.method_32108()
            .method_32101(12, 17)
            .method_32096()
            .method_32098(-2.2F, -16.7F, 1.3F, 3.0F, 3.0F, 3.0F, new class_5605(0.5F))
            .method_32106(false),
         class_5603.method_32091(-4.0F, -8.0F, -4.0F, -1.9124F, 0.0911F, -0.4554F)
      );
      partdefinition.method_32117("hat", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("body", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_arm", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("right_leg", class_5606.method_32108(), class_5603.field_27701);
      partdefinition.method_32117("left_leg", class_5606.method_32108(), class_5603.field_27701);
      return class_5607.method_32110(meshdefinition, 64, 32);
   }
}
